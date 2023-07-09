#include "StdAfx.h"
#include "TcAdsAPI.h"

#include "jni.h"

#include "AdsToJava.h"
#include "JObjAdsDevName.h"
#include "JObjAdsNotificationAttrib.h"
#include "JObjAdsNotificationHeader.h"
#include "JObjAdsState.h"
#include "JObjAdsVersion.h"
#include "JObjAmsAddr.h"

#include "JObjJNIBool.h"
#include "JObjJNIByteBuffer.h"
#include "JObjJNILong.h"
#include <array>
#include <limits>
#include <memory>
#include <string>
#include <vector>
#ifndef POSIX
#include <assert.h>
#endif

const size_t DEVICE_NAME_MAX_LEN =
    16; // https://infosys.beckhoff.com/english.php?content=../content/1033/tcadsamsspec/html/tcadsamsspec_adscmd_readdeviceinfo.htm&id=1313400676238164711

#ifdef POSIX
__attribute__((destructor)) void destructor() {
    // free resources of libTcAdsDll.so during a crash
    AdsPortClose();
}
#else
BOOL APIENTRY DllMain(HANDLE hModule [[maybe_unused]],
                      uint32_t ul_reason_for_call,
                      LPVOID lpReserved [[maybe_unused]]) {
    switch (ul_reason_for_call) {
    // case DLL_PROCESS_ATTACH: // AdsPortClose was also executed on
    // DLL_PROCESS_ATTACH but this was removed after reading
    // https://learn.microsoft.com/en-us/windows/win32/dlls/dynamic-link-library-best-practices
    case DLL_PROCESS_DETACH:
        // free resources of TcAdsDll.dll during a crash
        AdsPortClose();
        break;
    default:
        break;
    }
    return TRUE;
}
#endif

// AdsDoInitDll
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllDoInitDll(
    JNIEnv* env [[maybe_unused]], jobject obj [[maybe_unused]]) {
#ifdef POSIX
    // there is no equivalent of GetFileVersionInfoSize on this platform
    const int major = ADSTOJAVA_FILE_VERSION_MAJOR;
#else
    int major = 0;
    int minor = 0;

    DWORD verHandle = 0;
    uint32_t size = 0;
    unsigned char* lpBuffer = nullptr;
    uint32_t verSize = GetFileVersionInfoSize("AdsToJava-3.dll", &verHandle);

    if (verSize != 0) {
        std::string verData(verSize, '\0');

        if (GetFileVersionInfo("AdsToJava-3.dll", verHandle, verSize,
                               verData.data())) {
            if (VerQueryValue(verData.data(), "\\", (void FAR* FAR*)&lpBuffer,
                              &size)) {
                if (size) {
                    VS_FIXEDFILEINFO* verInfo = (VS_FIXEDFILEINFO*)lpBuffer;
                    if (verInfo->dwSignature == 0xfeef04bd) {
                        major = HIWORD(verInfo->dwFileVersionMS);
                        minor = LOWORD(verInfo->dwFileVersionMS);

                        assert(major == ADSTOJAVA_FILE_VERSION_MAJOR);
                        assert(minor == ADSTOJAVA_FILE_VERSION_MINOR);
                    }
                }
            }
        }
    }
#endif

    return static_cast<jlong>(major);
}

// AdsDoWhenUnloadDll
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllDoWhenUnloadDll(
    JNIEnv* env, jobject obj [[maybe_unused]]) -> jlong {
    env->DeleteGlobalRef(javaSinkObj);

    return (JNI_TRUE);
}

//////////////////////////////////////////////
// Forwarding of Adsdll.dll callbacks to java

// ADS-State Callback-Function
void ADSSTDCALL AdsStateCallback(AmsAddr* pAddr,
                                 AdsNotificationHeader* pNotification,
                                 ADS_UINT32_OR_ULONG hUser) {
    // attach to the current running thread
    JNIEnv* env = nullptr;
    jvm->AttachCurrentThread(reinterpret_cast<void**>(&env), jvm);

    // create an AmsAddr object within the java vm.
    // use it to store the notifications AMS address
    // jclass ljclass_AmsAddr = env->FindClass("de/beckhoff/jni/tcads/AmsAddr");
    jmethodID lmid_AmsAddr_init =
        env->GetMethodID(ljclass_glAmsAddrRef, "<init>", "()V");
    jobject ljobj_AmsAddr =
        env->NewObject(ljclass_glAmsAddrRef, lmid_AmsAddr_init);

    JObjAmsAddr lObjAmsAddr(env, ljobj_AmsAddr);
    lObjAmsAddr.setValuesInJObject(pAddr);

    // create an AdsNotificationHeader object within the java vm.
    // use it to store the notifications data
    const jint sampleSize = static_cast<jint>(pNotification->cbSampleSize);

    jmethodID lmid_AdsNotificationHeader_init =
        env->GetMethodID(ljclass_glAdsNotifHeader, "<init>", "(I)V");
    jobject ljobj_AdsNotificationHeader = env->NewObject(
        ljclass_glAdsNotifHeader, lmid_AdsNotificationHeader_init, sampleSize);

    JObjAdsNotificationHeader lObjAdsNotificationHeader(
        env, ljobj_AdsNotificationHeader);
    lObjAdsNotificationHeader.setValuesInJObject(pNotification);

    // create a buffer of type JNILong within the java vm.
    // use it to store the notifications user-id
    // jclass ljclass_JNILong = env->FindClass("de/beckhoff/jni/JNILong");
    jmethodID lmid_JNILong_init =
        env->GetMethodID(ljclass_glJNILong, "<init>", "()V");
    jobject ljobj_JNILong =
        env->NewObject(ljclass_glJNILong, lmid_JNILong_init);

    JObjJNILong lObjJNILong(env, ljobj_JNILong);
    auto hUserCpy = static_cast<unsigned long>(hUser);
    lObjJNILong.setValuesInJObject(&hUserCpy);

    // call the designated callback method within the java vm
    env->CallVoidMethod(javaSinkObj, mid_AdsStateCallback, ljobj_AmsAddr,
                        ljobj_AdsNotificationHeader, ljobj_JNILong);

    jvm->DetachCurrentThread();
}

// TwinCAT-Router Callback-Function
void ADSSTDCALL AdsRouterCallback(LONG nReason) {
    // attach to the current running thread
    JNIEnv* env = nullptr;
    jvm->AttachCurrentThread(reinterpret_cast<void**>(&env), jvm);

    // create a buffer of type JNILong within the java vm.
    // use it to store the nReason value
    jmethodID lmid_JNILong_init =
        env->GetMethodID(ljclass_glJNILong, "<init>", "()V");
    jobject ljobj_JNILong =
        env->NewObject(ljclass_glJNILong, lmid_JNILong_init);

    JObjJNILong lObjJNILong(env, ljobj_JNILong);
    const long nReasonCpy = static_cast<long>(nReason);
    lObjJNILong.setValuesInJObject(&nReasonCpy);

    // call the designated callback method within the java vm
    env->CallVoidMethod(javaSinkObj, mid_AdsRouterCallback, ljobj_JNILong);

    jvm->DetachCurrentThread();
}

// callDllAdsDoInitCallbacks
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllDoInitCallbacks(
    JNIEnv* env, jobject obj [[maybe_unused]], jobject lj_CallbackObject)
    -> jlong {
    // Get a reference of the global callback object. That object stores
    // information about the callback-method invoked by ads-callbacks
    javaSinkObj = env->NewGlobalRef(lj_CallbackObject);
    env->GetJavaVM(&jvm);

    // Create a global reference to all classes used in either AdsStateCallback
    // or AdsRouterCallback. Since these two functions are called without java
    // context the ClassLoader does not work when multiple threads are used
    jclass ljclass_AmsAddr = env->FindClass("de/beckhoff/jni/tcads/AmsAddr");
    ljclass_glAmsAddrRef =
        static_cast<jclass>(env->NewGlobalRef(ljclass_AmsAddr));
    jclass ljclass_AdsNotifHeader =
        env->FindClass("de/beckhoff/jni/tcads/AdsNotificationHeader");
    ljclass_glAdsNotifHeader =
        static_cast<jclass>(env->NewGlobalRef(ljclass_AdsNotifHeader));
    jclass ljclass_JNILong = env->FindClass("de/beckhoff/jni/JNILong");
    ljclass_glJNILong = static_cast<jclass>(env->NewGlobalRef(ljclass_JNILong));

    // infer class type of lj_CallbackObject
    jclass cls = env->GetObjectClass(lj_CallbackObject);

    // infer the callback-method if for state callbacks and store it
    mid_AdsStateCallback = env->GetMethodID(
        cls, "doAdsStateCallback",
        "(Lde/beckhoff/jni/tcads/AmsAddr;Lde/beckhoff/jni/tcads/"
        "AdsNotificationHeader;Lde/beckhoff/jni/JNILong;)V");

    // Infer the callback-method if for router callbacks and store it
    mid_AdsRouterCallback = env->GetMethodID(cls, "doAdsRouterCallback",
                                             "(Lde/beckhoff/jni/JNILong;)V");

    return JNI_TRUE;
}

//////////////////////////////////////////////
///////////////////////////// Adsdll.dll-calls

// AdsGetDllVersion
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsGetDllVersion(
    JNIEnv* env, jobject obj [[maybe_unused]], jobject ljobj_ResultDllVersion)
    -> jlong {
    ads_i32 version = AdsGetDllVersion();

    // convert the result and assign it to the according java parameter
    static_assert(sizeof(version) == sizeof(AdsVersion),
                  "invalid reinterpret_cast");
    auto* pDLLVersion = reinterpret_cast<AdsVersion*>(&version);
    JObjAdsVersion lJObjAdsVersion(env, ljobj_ResultDllVersion);
    lJObjAdsVersion.setValuesInJObject(pDLLVersion);

    return 0;
}

// AdsPortOpen
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsPortOpen(
    JNIEnv* env [[maybe_unused]], jobject obj [[maybe_unused]]) -> jlong {
    const long nPort = AdsPortOpen();
    return static_cast<jlong>(nPort);
}

// AdsPortClose
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsPortClose(
    JNIEnv* env [[maybe_unused]], jobject obj [[maybe_unused]]) -> jlong {
    const long nErr = AdsPortClose();
    return static_cast<jlong>(nErr);
}

// AdsGetLocalAddress
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsGetLocalAddress(
    JNIEnv* env, jobject obj [[maybe_unused]], jobject lj_AmsAddr) -> jlong {
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    // ADS call
    const long nErr = AdsGetLocalAddress(pAddr);

    // Convert the result and assign it to the according java parameter
    // Set the AmsAddr
    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.setValuesInJObject(pAddr);

    return static_cast<jlong>(nErr);
}

// Historically important, needs to be untouched for backwards compatibility
// AdsGetLocalAddressn
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsGetLocalAddressn(
    JNIEnv* env, jobject obj, jobject lj_AmsAddr) -> jlong {
    // call the new typo-free version of this method
    return Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsGetLocalAddress(
        env, obj, lj_AmsAddr);
}

// AdsSyncReadReq
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadReq(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_AmsAddr,        // AMS address of ADS server
    jlong lj_indexGroup,       // index group in ADS server interface
    jlong lj_indexOffset,      // index offset in ADS server interface
    jlong lj_length,           // count of bytes to read
    jobject lj_pData) -> jlong // pointer to the client buffer
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    const long lcpp_indexGroup = static_cast<long>(lj_indexGroup);
    const long lcpp_indexOffset = static_cast<long>(lj_indexOffset);
    const long lcpp_length = static_cast<long>(lj_length);
    std::vector<unsigned char> lcpp_DataByteArray(std::max(lcpp_length, 1L),
                                                  0x00);

    // ADS call
    const long nErr = AdsSyncReadReq(pAddr, lcpp_indexGroup, lcpp_indexOffset,
                                     lcpp_length, lcpp_DataByteArray.data());

    // Convert the result and assign it to the according java parameter
    // Set the response buffer
    JObjJNIByteBuffer lJObjJNIByteBuffer(env, lj_pData);
    lJObjJNIByteBuffer.setValuesInJObject(lcpp_DataByteArray.data());

    return static_cast<jlong>(nErr);
}

// AdsSyncWriteReq jbyteArray
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteReqArray(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_AmsAddr,           // AMS address of ADS server
    jlong lj_indexGroup,          // index group in ADS server interface
    jlong lj_indexOffset,         // index offset in ADS server interface
    jlong lj_length,              // count of bytes to write
    jbyteArray lj_pData) -> jlong // pointer to the client buffer
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    const long lcpp_indexGroup = static_cast<long>(lj_indexGroup);
    const long lcpp_indexOffset = static_cast<long>(lj_indexOffset);
    const long lcpp_length = static_cast<long>(lj_length);
    std::vector<unsigned char> lcpp_DataByteArray;
    lcpp_DataByteArray.resize(std::max(lcpp_length, 1L), 0x00);

    // write data into lcpp_DataByteArray (bytewise copying)
    jbyte* lj_pDataElements = env->GetByteArrayElements(lj_pData, nullptr);
    for (int i = 0; i < lcpp_length; i++) {
        lcpp_DataByteArray[i] = static_cast<unsigned char>(lj_pDataElements[i]);
    }

    // ADS call
    const long nErr = AdsSyncWriteReq(pAddr, lcpp_indexGroup, lcpp_indexOffset,
                                      lcpp_length, lcpp_DataByteArray.data());

    env->ReleaseByteArrayElements(lj_pData, lj_pDataElements, JNI_ABORT);

    return static_cast<jlong>(nErr);
}

// AdsSyncWriteReq jobject
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteReq(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_AmsAddr,        // AMS address of ADS server
    jlong lj_indexGroup,       // index group in ADS server interface
    jlong lj_indexOffset,      // index offset in ADS server interface
    jlong lj_length,           // count of bytes to write
    jobject lj_pData) -> jlong // pointer to the client buffer
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    const long lcpp_indexGroup = static_cast<long>(lj_indexGroup);
    const long lcpp_indexOffset = static_cast<long>(lj_indexOffset);
    const long lcpp_length = static_cast<long>(lj_length);

    std::vector<unsigned char> lcpp_DataByteArray(std::max(lcpp_length, 1L),
                                                  0x00);

    // get the write buffer
    JObjJNIByteBuffer lJObjJNIByteBuffer(env, lj_pData);
    lJObjJNIByteBuffer.getValuesOutJObject(lcpp_DataByteArray.data());

    // ADS call
    const long nErr = AdsSyncWriteReq(pAddr, lcpp_indexGroup, lcpp_indexOffset,
                                      lcpp_length, lcpp_DataByteArray.data());

    env->DeleteLocalRef(lj_pData);
    env->DeleteGlobalRef(lj_pData);

    return static_cast<jlong>(nErr);
}

// AdsSyncReadWriteReq
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadWriteReq(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_AmsAddr,             // AMS address of ADS server
    jlong lj_indexGroup,            // index group in ADS server interface
    jlong lj_indexOffset,           // index offset in ADS server interface
    jlong lj_cbReadLength,          // count of bytes to read
    jobject lj_pReadData,           // pointer to the client buffer
    jlong lj_cbWriteLength,         // count of bytes to write
    jobject lj_pWriteData) -> jlong // pointer to the client buffer
{
    // convert the parameters to cpp value types and make the ADS call
    long nErr = 0;
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    const long lcpp_indexGroup = static_cast<long>(lj_indexGroup);
    const long lcpp_indexOffset = static_cast<long>(lj_indexOffset);
    const long lcpp_ReadLength = static_cast<long>(lj_cbReadLength);
    const long lcpp_WriteLength = static_cast<long>(lj_cbWriteLength);

    // write data in lcpp_DataByteArrayWrite
    std::vector<unsigned char> lcpp_DataByteArrayWrite(
        std::max(lcpp_WriteLength, 1L), 0x00);
    JObjJNIByteBuffer lJObjJNIByteBufferWrite(env, lj_pWriteData);
    lJObjJNIByteBufferWrite.getValuesOutJObject(lcpp_DataByteArrayWrite.data());

    // ADS call
    std::vector<unsigned char> lcpp_DataByteArrayRead(
        std::max(lcpp_ReadLength, 1L), 0x00);
    nErr =
        AdsSyncReadWriteReq(pAddr, lcpp_indexGroup, lcpp_indexOffset,
                            lcpp_ReadLength, lcpp_DataByteArrayRead.data(),
                            lcpp_WriteLength, lcpp_DataByteArrayWrite.data());

    // Convert the result and assign it to the according java parameter
    // Set the response buffer
    JObjJNIByteBuffer lJObjJNIByteBufferRead(env, lj_pReadData);
    lJObjJNIByteBufferRead.setValuesInJObject(lcpp_DataByteArrayRead.data());

    return static_cast<jlong>(nErr);
}

// AdsSyncReadStateReq
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadStateReq(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_AmsAddr,               // AMS address of ADS server
    jobject lj_nAdsState,             // out pointer to client buffer
    jobject lj_nDeviceState) -> jlong // out pointer to the client buffer
{
    // convert the parameters to cpp value types and make the ADS call
    long nErr = 0;
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    // ADS call
    unsigned short nAdsState = 0;
    unsigned short nDeviceState = 0;
    nErr = AdsSyncReadStateReq(pAddr, &nAdsState, &nDeviceState);

    // Convert the result and assign it to the according java parameter
    // Set the AdsState
    JObjAdsState lJObjAdsState(env, lj_nAdsState);
    lJObjAdsState.setValuesInJObject(&nAdsState);

    // set the AdsState
    JObjAdsState lJObjAdsDeviceState(env, lj_nDeviceState);
    lJObjAdsDeviceState.setValuesInJObject(&nDeviceState);

    return static_cast<jlong>(nErr);
}

// AdsSyncReadDeviceInfoReq
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadDeviceInfoReq(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_AmsAddr,           // AMS address of ADS server
    jobject lj_pDevName,          // fixed length string (16 Byte)
    jobject lj_pVersion) -> jlong // client buffer to store server version
{
    // convert the parameters to cpp value types and make the ADS call
    long nErr = 0;
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    // ADS call
    std::string devName(DEVICE_NAME_MAX_LEN, '\0');
    AdsVersion version;
    nErr = AdsSyncReadDeviceInfoReq(pAddr, devName.data(), &version);

    // Convert the result and assign it to the according java parameter
    // Set the DeviceName
    JObjAdsDevName lJObjAdsDevName(env, lj_pDevName);
    const char* pDevNameCpy = devName.data();
    lJObjAdsDevName.setValuesInJObject(&pDevNameCpy);

    // set the AdsVersion
    JObjAdsVersion lJObjAdsVersion(env, lj_pVersion);
    lJObjAdsVersion.setValuesInJObject(&version);

    return static_cast<jlong>(nErr);
}

// AdsSyncWriteControlReq
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteControlReq(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_AmsAddr,        // AMS address of ADS server
    jint lj_adsState,          // index group in ADS server interface
    jint lj_deviceState,       // index offset in ADS server interface
    jlong lj_length,           // count of bytes to write
    jobject lj_pData) -> jlong // pointer to the client buffer
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    if (lj_adsState < std::numeric_limits<unsigned short>::min() ||
        lj_adsState > std::numeric_limits<unsigned short>::max()) {
        return ADSERR_DEVICE_INVALIDSTATE;
    }
    auto lcpp_adsState = static_cast<unsigned short>(lj_adsState);

    if (lj_deviceState < std::numeric_limits<unsigned short>::min() ||
        lj_deviceState > std::numeric_limits<unsigned short>::max()) {
        return ADSERR_DEVICE_INVALIDSTATE;
    }
    auto lcpp_deviceState = static_cast<unsigned short>(lj_deviceState);

    const long lcpp_length = static_cast<long>(lj_length);
    std::vector<unsigned char> lcpp_DataByteArray(std::max(lcpp_length, 1L),
                                                  0x00);

    // get the write buffer
    JObjJNIByteBuffer lJObjJNIByteBuffer(env, lj_pData);
    lJObjJNIByteBuffer.getValuesOutJObject(lcpp_DataByteArray.data());

    // ADS call
    const long nErr =
        AdsSyncWriteControlReq(pAddr, lcpp_adsState, lcpp_deviceState,
                               lcpp_length, lcpp_DataByteArray.data());

    return static_cast<jlong>(nErr);
}

// AdsSyncGetTimeout
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncGetTimeout(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_pMs) -> jlong // Get timeout in ms
{
    // ADS call
    LONG data = 0;
    const long nErr = AdsSyncGetTimeout(&data);

    // Convert the result and assign it to the according java parameter
    // Set the milliseconds
    JObjJNILong ljObjJNILong(env, lj_pMs);
    const long dataCpy = static_cast<long>(data);
    ljObjJNILong.setValuesInJObject(&dataCpy);

    return static_cast<jlong>(nErr);
}

// AdsSyncSetTimeout
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncSetTimeout(
    JNIEnv* env [[maybe_unused]], jobject obj [[maybe_unused]],
    jlong lj_nMs) -> jlong // Set timeout in ms
{
    // ADS call
    const long nErr = static_cast<jlong>(
        AdsSyncSetTimeout(static_cast<ADS_INT32_OR_LONG>(lj_nMs)));
    return nErr;
}

// AdsSyncAddDeviceNotificationReq
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncAddDeviceNotificationReq(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_pAddr,       // AMS address of ADS server
    jlong lj_indexGroup,    // index group in ADS server interface
    jlong lj_indexOffset,   // index offset in ADS server interface
    jobject lj_pNoteAttrib, // attributes of notification request
    jlong lj_hUser,         // user handle
    jobject lj_pNotification)
    -> jlong // pointer to notification handle (return value)
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;
    AdsNotificationAttrib lcpp_adsNotificationAttrib;

    JObjAmsAddr lJObjAmsAddr(env, lj_pAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    // get the AdsNotificationAttrib
    JObjAdsNotificationAttrib lJObjAdsNotificationAttrib(env, lj_pNoteAttrib);
    lJObjAdsNotificationAttrib.getValuesOutJObject(&lcpp_adsNotificationAttrib);

    // ADS call
    ULONG hNotification = 0;
    PAdsNotificationFuncEx callback = AdsStateCallback;
    const long nErr = AdsSyncAddDeviceNotificationReq(
        pAddr, static_cast<LONG>(lj_indexGroup),
        static_cast<LONG>(lj_indexOffset), &lcpp_adsNotificationAttrib,
        callback, static_cast<uint32_t>(lj_hUser), &hNotification);

    // Convert the result and assign it to the according java parameter
    // Set the notification number
    JObjJNILong lJObjJNILong(env, lj_pNotification);
    auto hNotificationCpy = static_cast<unsigned long>(hNotification);
    lJObjJNILong.setValuesInJObject(&hNotificationCpy);

    return static_cast<jlong>(nErr);
}

// AdsSyncDelDeviceNotificationReq
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncDelDeviceNotificationReq(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_pAddr,                  // AMS address of ADS server
    jobject lj_hNotification) -> jlong // notification handle
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_pAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    // get the notification number
    JObjJNILong lJObjJNILong(env, lj_hNotification);
    unsigned long hNotification = 0;
    lJObjJNILong.getValuesOutJObject(&hNotification);
    auto hNotificationCpy = static_cast<ULONG>(hNotification);

    // ADS call
    const long nErr = AdsSyncDelDeviceNotificationReq(pAddr, hNotificationCpy);
    return static_cast<jlong>(nErr);
}

// AdsAmsRegisterRouterNotification
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsAmsRegisterRouterNotification(
    JNIEnv* env [[maybe_unused]], jobject obj [[maybe_unused]]) -> jlong {
    // ADS call
    PAmsRouterNotificationFuncEx callback = AdsRouterCallback;
    const long nErr = AdsAmsRegisterRouterNotification(callback);
    return static_cast<jlong>(nErr);
}

// AdsAmsUnRegisterRouterNotification
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsAmsUnRegisterRouterNotification(
    JNIEnv* env [[maybe_unused]], jobject obj [[maybe_unused]]) -> jlong {
    // ADS call
    const long nErr = AdsAmsUnRegisterRouterNotification();
    return static_cast<jlong>(nErr);
}

// AdsAmsPortEnabled
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsAmsPortEnabled(
    JNIEnv* env, jobject obj [[maybe_unused]], jobject lj_pEnabled) -> jlong {
    // ADS call
    ads_bool data = 0;
    const long nErr = AdsAmsPortEnabled(&data);

    // Convert the result and assign it to the according java parameter
    // Set the response buffer
    JObjJNIBool ljObjJNIBool(env, lj_pEnabled);
    auto bData = static_cast<bool>(data);
    ljObjJNIBool.setValuesInJObject(&bData);

    return static_cast<jlong>(nErr);
}

// Extended (ex) ADS multithreading functions

// AdsPortOpenEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsPortOpenEx(
    JNIEnv* env [[maybe_unused]], jobject obj [[maybe_unused]]) -> jlong {
    // ADS call
    const long nPort = AdsPortOpenEx();
    return static_cast<jlong>(nPort);
}

// AdsPortCloseEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsPortCloseEx(
    JNIEnv* env [[maybe_unused]], jobject obj [[maybe_unused]],
    jlong lj_nPort) -> jlong // AMS port of ADS client
{
    // convert the parameters to cpp value types and make the ADS call
    const auto lcpp_nPort = static_cast<ADS_INT32_OR_LONG>(lj_nPort);

    // ADS call
    const long nErr = AdsPortCloseEx(lcpp_nPort);
    return static_cast<jlong>(nErr);
}

// AdsGetLocalAddressEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsGetLocalAddressEx(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort,              // AMS port of ADS client
    jobject lj_AmsAddr) -> jlong // AMS address of ADS server
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;
    const auto lcpp_nPort = static_cast<ADS_INT32_OR_LONG>(lj_nPort);

    // ADS call
    const long nErr = AdsGetLocalAddressEx(lcpp_nPort, pAddr);

    // Convert the result and assign it to the according java parameter
    // Set the AmsAddr
    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.setValuesInJObject(pAddr);

    return static_cast<jlong>(nErr);
}

// AdsSyncReadReqEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadReqEx(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_AmsAddr,               // AMS address of ADS server
    jlong lj_indexGroup,              // index group in ADS server interface
    jlong lj_indexOffset,             // index offset in ADS server interface
    jlong lj_length,                  // count of bytes to read
    jobject lj_pData,                 // pointer to the client buffer
    jobject lj_lengthReturn) -> jlong // count of bytes read
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    const long lcpp_indexGroup = static_cast<long>(lj_indexGroup);
    const long lcpp_indexOffset = static_cast<long>(lj_indexOffset);
    const long lcpp_length = static_cast<long>(lj_length);
    std::vector<unsigned char> lcpp_DataByteArray(std::max(lcpp_length, 1L),
                                                  0x00);
    ULONG lcpp_pcbReturn = 0;

    // ADS call
    const long nErr =
        AdsSyncReadReqEx(pAddr, lcpp_indexGroup, lcpp_indexOffset, lcpp_length,
                         lcpp_DataByteArray.data(), &lcpp_pcbReturn);

    // Convert the result and assign it to the according java parameter
    // Set the response buffer
    JObjJNIByteBuffer lJObjJNIByteBuffer(env, lj_pData);
    lJObjJNIByteBuffer.setValuesInJObject(lcpp_DataByteArray.data());

    // set the response buffers length
    JObjJNILong ljObjJNILong(env, lj_lengthReturn);
    auto lcpp_pcbReturnCpy = static_cast<unsigned long>(lcpp_pcbReturn);
    ljObjJNILong.setValuesInJObject(&lcpp_pcbReturnCpy);

    return static_cast<jlong>(nErr);
}

// AdsSyncReadReqEx2
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadReqEx2(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort,                   // AMS port of ADS client
    jobject lj_AmsAddr,               // AMS address of ADS server
    jlong lj_indexGroup,              // index group in ADS server interface
    jlong lj_indexOffset,             // index offset in ADS server interface
    jlong lj_length,                  // count of bytes to read
    jobject lj_pData,                 // pointer to the client buffer
    jobject lj_lengthReturn) -> jlong // count of bytes read
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    const auto lcpp_nPort = static_cast<ADS_INT32_OR_LONG>(lj_nPort);
    const long lcpp_indexGroup = static_cast<long>(lj_indexGroup);
    const long lcpp_indexOffset = static_cast<long>(lj_indexOffset);
    const long lcpp_length = static_cast<long>(lj_length);
    ULONG lcpp_pcbReturn = 0;
    std::vector<unsigned char> lcpp_DataByteArray(std::max(lcpp_length, 1L),
                                                  0x00);

    // ADS call
    const long nErr = AdsSyncReadReqEx2(
        lcpp_nPort, pAddr, lcpp_indexGroup, lcpp_indexOffset, lcpp_length,
        lcpp_DataByteArray.data(), &lcpp_pcbReturn);

    // Convert the result and assign it to the according java parameter
    // Set the response buffer
    JObjJNIByteBuffer lJObjJNIByteBuffer(env, lj_pData);
    lJObjJNIByteBuffer.setValuesInJObject(lcpp_DataByteArray.data());

    // set the response buffers length
    JObjJNILong ljObjJNILong(env, lj_lengthReturn);
    auto lcpp_pcbReturnCpy = static_cast<unsigned long>(lcpp_pcbReturn);
    ljObjJNILong.setValuesInJObject(&lcpp_pcbReturnCpy);

    return static_cast<jlong>(nErr);
}

// AdsSyncWriteReqEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteReqEx(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort,            // AMS port of ADS client
    jobject lj_AmsAddr,        // AMS address of ADS server
    jlong lj_indexGroup,       // index group in ADS server interface
    jlong lj_indexOffset,      // index offset in ADS server interface
    jlong lj_length,           // count of bytes to read
    jobject lj_pData) -> jlong // pointer to the client buffer
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    const auto lcpp_nPort = static_cast<ADS_INT32_OR_LONG>(lj_nPort);
    const long lcpp_indexGroup = static_cast<long>(lj_indexGroup);
    const long lcpp_indexOffset = static_cast<long>(lj_indexOffset);
    const long lcpp_length = static_cast<long>(lj_length);
    std::vector<unsigned char> lcpp_DataByteArray(std::max(lcpp_length, 1L),
                                                  0x00);

    // get the write buffer
    JObjJNIByteBuffer lJObjJNIByteBuffer(env, lj_pData);
    lJObjJNIByteBuffer.getValuesOutJObject(lcpp_DataByteArray.data());

    // ADS call
    const long nErr =
        AdsSyncWriteReqEx(lcpp_nPort, pAddr, lcpp_indexGroup, lcpp_indexOffset,
                          lcpp_length, lcpp_DataByteArray.data());

    return static_cast<jlong>(nErr);
}

// AdsSyncWriteReqEx jbyteArray
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteReqExArray(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort,               // AMS port of ADS client
    jobject lj_AmsAddr,           // AMS address of ADS server
    jlong lj_indexGroup,          // index group in ADS server interface
    jlong lj_indexOffset,         // index offset in ADS server interface
    jlong lj_length,              // count of bytes to write
    jbyteArray lj_pData) -> jlong // pointer to the client buffer
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    const auto lcpp_nPort = static_cast<ADS_INT32_OR_LONG>(lj_nPort);
    const long lcpp_indexGroup = static_cast<long>(lj_indexGroup);
    const long lcpp_indexOffset = static_cast<long>(lj_indexOffset);
    const long lcpp_length = static_cast<long>(lj_length);
    std::vector<unsigned char> lcpp_DataByteArray;
    lcpp_DataByteArray.reserve(lcpp_length);

    // Write Data in lcpp_DataByteArray
    jbyte* lj_pDataElements = env->GetByteArrayElements(lj_pData, nullptr);
    for (int i = 0; i < lcpp_length; i++) {
        lcpp_DataByteArray.push_back(
            static_cast<unsigned char>(lj_pDataElements[i]));
    }

    // ADS call
    const long nErr =
        AdsSyncWriteReqEx(lcpp_nPort, pAddr, lcpp_indexGroup, lcpp_indexOffset,
                          lcpp_length, lcpp_DataByteArray.data());

    env->ReleaseByteArrayElements(lj_pData, lj_pDataElements, JNI_ABORT);

    return static_cast<jlong>(nErr);
}

// AdsSyncReadWriteReqEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadWriteReqEx(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jobject lj_AmsAddr,               // AMS address of ADS server
    jlong lj_indexGroup,              // index group in ADS server interface
    jlong lj_indexOffset,             // index offset in ADS server interface
    jlong lj_cbReadLength,            // count of bytes to read
    jobject lj_pReadData,             // pointer to the client buffer
    jlong lj_cbWriteLength,           // count of bytes to write
    jobject lj_pWriteData,            // pointer to the client buffer
    jobject lj_lengthReturn) -> jlong // count of bytes read
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    const long lcpp_indexGroup = static_cast<long>(lj_indexGroup);
    const long lcpp_indexOffset = static_cast<long>(lj_indexOffset);
    const long lcpp_ReadLength = static_cast<long>(lj_cbReadLength);
    const long lcpp_WriteLength = static_cast<long>(lj_cbWriteLength);
    ULONG lcpp_pcbReturn = 0;

    // Write Data in lcpp_DataByteArrayWrite
    // Get the write buffer
    std::vector<unsigned char> lcpp_DataByteArrayWrite(
        std::max(lcpp_WriteLength, 1L), 0x00);
    JObjJNIByteBuffer lJObjJNIByteBufferWrite(env, lj_pWriteData);
    lJObjJNIByteBufferWrite.getValuesOutJObject(lcpp_DataByteArrayWrite.data());

    // ADS call
    std::vector<unsigned char> lcpp_DataByteArrayRead(
        std::max(lcpp_ReadLength, 1L), 0x00);
    const long nErr = AdsSyncReadWriteReqEx(
        pAddr, lcpp_indexGroup, lcpp_indexOffset, lcpp_ReadLength,
        lcpp_DataByteArrayRead.data(), lcpp_WriteLength,
        lcpp_DataByteArrayWrite.data(), &lcpp_pcbReturn);

    // Convert the result and assign it to the according java parameter
    // Set the response buffer
    JObjJNIByteBuffer lJObjJNIByteBufferRead(env, lj_pReadData);
    lJObjJNIByteBufferRead.setValuesInJObject(lcpp_DataByteArrayRead.data());

    // set the response buffers length
    JObjJNILong ljObjJNILong(env, lj_lengthReturn);
    auto lcpp_pcbReturnCpy = static_cast<unsigned long>(lcpp_pcbReturn);
    ljObjJNILong.setValuesInJObject(&lcpp_pcbReturnCpy);

    return static_cast<jlong>(nErr);
}

// AdsSyncReadWriteReqEx2
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadWriteReqEx2(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort,                   // AMS port of ADS client
    jobject lj_AmsAddr,               // AMS address of ADS server
    jlong lj_indexGroup,              // index group in ADS server interface
    jlong lj_indexOffset,             // index offset in ADS server interface
    jlong lj_cbReadLength,            // count of bytes to read
    jobject lj_pReadData,             // pointer to the client buffer
    jlong lj_cbWriteLength,           // count of bytes to write
    jobject lj_pWriteData,            // pointer to the client buffer
    jobject lj_lengthReturn) -> jlong // count of bytes read
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    const auto lcpp_nPort = static_cast<ADS_INT32_OR_LONG>(lj_nPort);
    const long lcpp_indexGroup = static_cast<long>(lj_indexGroup);
    const long lcpp_indexOffset = static_cast<long>(lj_indexOffset);
    const long lcpp_ReadLength = static_cast<long>(lj_cbReadLength);
    const long lcpp_WriteLength = static_cast<long>(lj_cbWriteLength);
    ULONG lcpp_pcbReturn = 0;

    // Write Data in lcpp_DataByteArrayWrite
    // Get the write buffer
    std::vector<unsigned char> lcpp_DataByteArrayWrite(
        std::max(lcpp_WriteLength, 1L), 0x00);
    JObjJNIByteBuffer lJObjJNIByteBufferWrite(env, lj_pWriteData);
    lJObjJNIByteBufferWrite.getValuesOutJObject(lcpp_DataByteArrayWrite.data());

    // ADS call
    std::vector<unsigned char> lcpp_DataByteArrayRead(
        std::max(lcpp_ReadLength, 1L), 0x00);
    const long nErr = AdsSyncReadWriteReqEx2(
        lcpp_nPort, pAddr, lcpp_indexGroup, lcpp_indexOffset, lcpp_ReadLength,
        lcpp_DataByteArrayRead.data(), lcpp_WriteLength,
        lcpp_DataByteArrayWrite.data(), &lcpp_pcbReturn);

    // Convert the result and assign it to the according java parameter
    // Set the response buffer
    JObjJNIByteBuffer lJObjJNIByteBufferRead(env, lj_pReadData);
    lJObjJNIByteBufferRead.setValuesInJObject(lcpp_DataByteArrayRead.data());

    // set the response buffers length
    JObjJNILong ljObjJNILong(env, lj_lengthReturn);
    auto lcpp_pcbReturnCpy = static_cast<unsigned long>(lcpp_pcbReturn);
    ljObjJNILong.setValuesInJObject(&lcpp_pcbReturnCpy);

    return static_cast<jlong>(nErr);
}

// AdsSyncReadStateReqEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadStateReqEx(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort,                   // AMS port of ADS client
    jobject lj_AmsAddr,               // AMS address of ADS server
    jobject lj_nAdsState,             // out pointer to client buffer
    jobject lj_nDeviceState) -> jlong // out pointer to the client buffer
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;
    const auto lcpp_nPort = static_cast<ADS_INT32_OR_LONG>(lj_nPort);

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    // ADS call
    unsigned short nAdsState = 0;
    unsigned short nDeviceState = 0;
    const long nErr =
        AdsSyncReadStateReqEx(lcpp_nPort, pAddr, &nAdsState, &nDeviceState);

    // Convert the result and assign it to the according java parameter
    // Set the AdsState
    JObjAdsState lJObjAdsState(env, lj_nAdsState);
    lJObjAdsState.setValuesInJObject(&nAdsState);

    // set the DeviceState
    JObjAdsState lJObjAdsDeviceState(env, lj_nDeviceState);
    lJObjAdsDeviceState.setValuesInJObject(&nDeviceState);

    return static_cast<jlong>(nErr);
}

// AdsSyncReadDeviceInfoReqEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadDeviceInfoReqEx(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort,               // AMS port of ADS client
    jobject lj_AmsAddr,           // AMS address of ADS server
    jobject lj_pDevName,          // fixed length string (16 Byte)
    jobject lj_pVersion) -> jlong // client buffer to store server version
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;
    const auto lcpp_nPort = static_cast<ADS_INT32_OR_LONG>(lj_nPort);
    AdsVersion version;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    // ADS call
    std::string devName(DEVICE_NAME_MAX_LEN, '\0');
    const long nErr =
        AdsSyncReadDeviceInfoReqEx(lcpp_nPort, pAddr, devName.data(), &version);

    // Convert the result and assign it to the according java parameter
    // Set the DeviceName
    JObjAdsDevName lJObjAdsDevName(env, lj_pDevName);
    const char* pDevNameCpy = devName.c_str();
    lJObjAdsDevName.setValuesInJObject(&pDevNameCpy);

    // set the AdsVersion
    JObjAdsVersion lJObjAdsVersion(env, lj_pVersion);
    lJObjAdsVersion.setValuesInJObject(&version);

    return static_cast<jlong>(nErr);
}

// AdsSyncWriteControlReqEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteControlReqEx(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort,            // AMS port of ADS client
    jobject lj_AmsAddr,        // AMS address of ADS server
    jint lj_adsState,          // index group in ADS server interface
    jint lj_deviceState,       // index offset in ADS server interface
    jlong lj_length,           // count of bytes to write
    jobject lj_pData) -> jlong // pointer to the client buffer
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;

    JObjAmsAddr lJObjAmsAddr(env, lj_AmsAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    const auto lcpp_nPort = static_cast<ADS_INT32_OR_LONG>(lj_nPort);

    if (lj_adsState < std::numeric_limits<unsigned short>::min() ||
        lj_adsState > std::numeric_limits<unsigned short>::max()) {
        return ADSERR_DEVICE_INVALIDSTATE;
    }
    auto lcpp_adsState = static_cast<unsigned short>(lj_adsState);

    if (lj_deviceState < std::numeric_limits<unsigned short>::min() ||
        lj_deviceState > std::numeric_limits<unsigned short>::max()) {
        return ADSERR_DEVICE_INVALIDSTATE;
    }
    auto lcpp_deviceState = static_cast<unsigned short>(lj_deviceState);

    const long lcpp_length = static_cast<long>(lj_length);
    std::vector<unsigned char> lcpp_DataByteArray(std::max(lcpp_length, 1L),
                                                  0x00);

    // get the write buffer
    JObjJNIByteBuffer lJObjJNIByteBuffer(env, lj_pData);
    lJObjJNIByteBuffer.getValuesOutJObject(lcpp_DataByteArray.data());

    // ADS call
    const long nErr = AdsSyncWriteControlReqEx(lcpp_nPort, pAddr, lcpp_adsState,
                                               lcpp_deviceState, lcpp_length,
                                               lcpp_DataByteArray.data());

    return static_cast<jlong>(nErr);
}

// AdsSyncGetTimeoutEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncGetTimeoutEx(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort,          // AMS port of ADS client
    jobject lj_pMs) -> jlong // Get timeout in ms
{
    // ADS call
    LONG data = 0;
    const long nErr =
        AdsSyncGetTimeoutEx(static_cast<ADS_INT32_OR_LONG>(lj_nPort), &data);

    // Convert the result and assign it to the according java parameter
    // Get the milliseconds
    JObjJNILong ljObjJNILong(env, lj_pMs);
    auto dataCpy = static_cast<unsigned long>(data);
    ljObjJNILong.setValuesInJObject(&dataCpy);

    return static_cast<jlong>(nErr);
}

// AdsSyncSetTimeoutEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncSetTimeoutEx(
    JNIEnv* env [[maybe_unused]], jobject obj [[maybe_unused]],
    jlong lj_nPort,        // AMS port of ADS client
    jlong lj_nMs) -> jlong // Set timeout in ms
{
    // ADS call
    return static_cast<jlong>(
        AdsSyncSetTimeoutEx(static_cast<ADS_INT32_OR_LONG>(lj_nPort),
                            static_cast<ADS_INT32_OR_LONG>(lj_nMs)));
}

// AdsSyncAddDeviceNotificationReqEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncAddDeviceNotificationReqEx(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort,         // AMS port of ADS client
    jobject lj_pAddr,       // AMS address of ADS server
    jlong lj_indexGroup,    // index group in ADS server interface
    jlong lj_indexOffset,   // index offset in ADS server interface
    jobject lj_pNoteAttrib, // attributes of notification request
    jlong lj_hUser,         // user handle
    jobject lj_pNotification)
    -> jlong // pointer to notification handle (return value)
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;
    AdsNotificationAttrib lcpp_adsNotificationAttrib;
    const auto lcpp_nPort = static_cast<ADS_INT32_OR_LONG>(lj_nPort);

    JObjAmsAddr lJObjAmsAddr(env, lj_pAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    // get the AdsNotificationAttribute
    JObjAdsNotificationAttrib lJObjAdsNotificationAttrib(env, lj_pNoteAttrib);
    lJObjAdsNotificationAttrib.getValuesOutJObject(&lcpp_adsNotificationAttrib);

    // ADS call
    ULONG hNotification = 0;
    PAdsNotificationFuncEx callback = AdsStateCallback;
    const long nErr = AdsSyncAddDeviceNotificationReqEx(
        lcpp_nPort, pAddr, static_cast<LONG>(lj_indexGroup),
        static_cast<LONG>(lj_indexOffset), &lcpp_adsNotificationAttrib,
        callback, static_cast<uint32_t>(lj_hUser), &hNotification);

    // Convert the result and assign it to the according java parameter
    // Set the notification number
    JObjJNILong lJObjJNILong(env, lj_pNotification);
    auto hNotificationCpy = static_cast<unsigned long>(hNotification);
    lJObjJNILong.setValuesInJObject(&hNotificationCpy);

    return static_cast<jlong>(nErr);
}

// AdsSyncDelDeviceNotificationReqEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncDelDeviceNotificationReqEx(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort,                    // AMS port of ADS client
    jobject lj_pAddr,                  // AMS address of ADS server
    jobject lj_hNotification) -> jlong // notification handle
{
    // convert the parameters to cpp value types and make the ADS call
    AmsAddr Addr;
    PAmsAddr pAddr = &Addr;
    long hNotification = 0;
    const auto lcpp_nPort = static_cast<ADS_INT32_OR_LONG>(lj_nPort);

    JObjAmsAddr lJObjAmsAddr(env, lj_pAddr);
    lJObjAmsAddr.getValuesOutJObject(pAddr);

    // get the notification number
    JObjJNILong lJObjJNILong(env, lj_hNotification);
    lJObjJNILong.getValuesOutJObject(&hNotification);

    // ADS call
    return static_cast<jlong>(
        AdsSyncDelDeviceNotificationReqEx(lcpp_nPort, pAddr, hNotification));
}

// AdsAmsPortEnabledEx
JNIEXPORT auto JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsAmsPortEnabledEx(
    JNIEnv* env, jobject obj [[maybe_unused]],
    jlong lj_nPort, // AMS port of ADS client
    jobject lj_pEnabled) -> jlong {
    // ADS call
    ads_bool data = 0;
    const long nErr =
        AdsAmsPortEnabledEx(static_cast<ADS_INT32_OR_LONG>(lj_nPort), &data);

    // Convert the result and assign it to the according java parameter
    // Get the response buffer
    JObjJNIBool ljObjJNIBool(env, lj_pEnabled);
    auto bData = static_cast<bool>(data);
    ljObjJNIBool.setValuesInJObject(&bData);

    return static_cast<jlong>(nErr);
}
