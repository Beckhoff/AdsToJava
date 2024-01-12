#pragma once
#include "jni.h"

// ADSCallbacks
JavaVM* jvm;         // The java vm we are running
jobject javaSinkObj; // the java object to delegate calls

// global jclass definitions to use when ClassLoader is unavailable
// (e.g. when callbacks are redirected via native c code via AdsRouterCallback
// or AdsStateCallback)
jclass ljclass_glAmsAddrRef;
jclass ljclass_glAdsNotifHeader;
jclass ljclass_glJNILong;

jmethodID mid_AdsStateCallback = 0;
jmethodID mid_AdsRouterCallback = 0;

extern "C" {
// AddLocalRoute
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAddLocalRoute(
    JNIEnv* env, jobject obj,
    jobject lj_AmsNetId, // AMS NetId of ADS server
    jstring lj_IpAddr);  // IP address, where the ADS server can be found

// DelLocalRoute
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllDelLocalRoute(
    JNIEnv* env, jobject obj,
    jobject lj_AmsNetId); // AMS NetId of ADS server

// SetLocalAddress
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllSetLocalAddress(
    JNIEnv* env, jobject obj,
    jobject lj_AmsNetId); // AMS NetId of ADS server

// AdsGetDllVersion
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsGetDllVersion(
    JNIEnv* env, jobject obj, jobject lResultDllVersion);

// AdsPortOpen
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsPortOpen(JNIEnv* env,
                                                                 jobject obj);

// AdsPortClose
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsPortClose(JNIEnv* env,
                                                                  jobject obj);

// AdsGetLocalAddress
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsGetLocalAddress(
    JNIEnv* env, jobject obj, jobject lj_AmsAddr);

// Historically important, needs to be untouched for backwards compatibility

// AdsGetLocalAddressn
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsGetLocalAddressn(
    JNIEnv* env, jobject obj, jobject lj_AmsAddr);

// AdsSyncReadReq
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadReq(
    JNIEnv* env, jobject obj,
    jobject lj_AmsAddr,   // AMS address of ADS server
    jlong lj_indexGroup,  // index group in ADS server interface
    jlong lj_indexOffset, // index offset in ADS server interface
    jlong lj_length,      // count of bytes to read
    jobject lj_pData);    // pointer to the client buffer

// AdsSyncReadReqEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadReqEx(
    JNIEnv* env, jobject obj,
    jobject lj_AmsAddr,       // AMS address of ADS server
    jlong lj_indexGroup,      // index group in ADS server interface
    jlong lj_indexOffset,     // index offset in ADS server interface
    jlong lj_length,          // count of bytes to read
    jobject lj_pData,         // pointer to the client buffer
    jobject lj_lengthReturn); // count of bytes read

// AdsSyncWriteReq jbyteArray
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteReqArray(
    JNIEnv* env, jobject obj,
    jobject lj_AmsAddr,   // AMS address of ADS server
    jlong lj_indexGroup,  // index group in ADS server interface
    jlong lj_indexOffset, // index offset in ADS server interface
    jlong lj_length,      // count of bytes to read
    jbyteArray lj_pData); // pointer to the client buffer

// AdsSyncWriteReq
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteReq(
    JNIEnv* env, jobject obj,
    jobject lj_AmsAddr,   // AMS address of ADS server
    jlong lj_indexGroup,  // index group in ADS server interface
    jlong lj_indexOffset, // index offset in ADS server interface
    jlong lj_length,      // count of bytes to read
    jobject lj_pData);    // pointer to the client buffer

// AdsSyncReadWriteReq
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadWriteReq(
    JNIEnv* env, jobject obj,
    jobject lj_AmsAddr,     // AMS address of ADS server
    jlong lj_indexGroup,    // index group in ADS server interface
    jlong lj_indexOffset,   // index offset in ADS server interface
    jlong lj_cbReadLength,  // count of bytes to read
    jobject lj_pReadData,   // pointer to the client buffer
    jlong lj_cbWriteLength, // count of bytes to write
    jobject lj_pWriteData); // pointer to the client buffer

// AdsSyncReadWriteReqEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadWriteReqEx(
    JNIEnv* env, jobject obj,
    jobject lj_AmsAddr,       // AMS address of ADS server
    jlong lj_indexGroup,      // index group in ADS server interface
    jlong lj_indexOffset,     // index offset in ADS server interface
    jlong lj_cbReadLength,    // count of bytes to read
    jobject lj_pReadData,     // pointer to the client buffer
    jlong lj_cbWriteLength,   // count of bytes to write
    jobject lj_pWriteData,    // pointer to the client buffer
    jobject lj_lengthReturn); // count to bytes read

// AdsSyncReadStateReq
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadStateReq(
    JNIEnv* env, jobject obj,
    jobject lj_AmsAddr,       // AMS address of ADS server
    jobject lj_nAdsState,     // pointer to client buffer
    jobject lj_nDeviceState); // pointer to the client buffer

// AdsSyncReadDeviceInfoReq
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadDeviceInfoReq(
    JNIEnv* env, jobject obj,
    jobject lj_AmsAddr,   // AMS address of ADS server
    jobject lj_pDevName,  // fixed length string (16 Byte)
    jobject lj_pVersion); // client buffer to store server version

// AdsSyncWriteControlReq
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteControlReq(
    JNIEnv* env, jobject obj,
    jobject lj_AmsAddr,  // AMS address of ADS server
    jint lj_adsState,    // index group in ADS server interface
    jint lj_deviceState, // index offset in ADS server interface
    jlong lj_length,     // count of bytes to write
    jobject lj_pData);   // pointer to the client buffer

// AdsSyncSetTimeout
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncSetTimeout(
    JNIEnv* env, jobject obj,
    jlong lj_nMs); // Set timeout in ms

// AdsSyncGetTimeout
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncGetTimeout(
    JNIEnv* env, jobject obj,
    jobject lj_pMs); // Get timeout in ms

// AdsAmsRegisterRouterNotification
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsAmsRegisterRouterNotification(
    JNIEnv* env, jobject obj);

// AdsAmsUnRegisterRouterNotification
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsAmsUnRegisterRouterNotification(
    JNIEnv* env, jobject obj);

// AdsSyncAddDeviceNotificationReq
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncAddDeviceNotificationReq(
    JNIEnv* env, jobject obj,
    jobject lj_pAddr,          // AMS address of ADS server
    jlong lj_indexGroup,       // index group in ADS server interface
    jlong lj_indexOffset,      // index offset in ADS server interface
    jobject lj_pNoteAttrib,    // attributes of notification request
    jlong lj_hUser,            // user handle
    jobject lj_pNotification); // pointer to notification handle (return value)

// AdsSyncDelDeviceNotificationReq
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncDelDeviceNotificationReq(
    JNIEnv* env, jobject obj,
    jobject lj_pAddr,          // AMS address of ADS server
    jobject lj_hNotification); // notification handle

// callDllAdsDoInitCallbacks
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllDoInitCallbacks(
    JNIEnv* env, jobject obj, jobject aSinkObj);

// AdsDoInitDll
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllDoInitDll(JNIEnv* env,
                                                               jobject obj);

// AdsDoWhenUnloadDll
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllDoWhenUnloadDll(
    JNIEnv* env, jobject obj);

// AdsAmsPortEnabled
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsAmsPortEnabled(
    JNIEnv* env, jobject obj, jobject bEnabled);

// AdsGetLastError
// JNIEXPORT jlong JNICALL
// Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsGetLastError(
//  JNIEnv *env, jobject obj);

///////////////////////////////////////////////////////////////////////////
// extended methods

// AdsPortOpenEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsPortOpenEx(JNIEnv* env,
                                                                   jobject obj);

// AdsPortCloseEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsPortCloseEx(
    JNIEnv* env, jobject obj,
    jlong lj_nPort); // AMS port of ADS client

// AdsGetLocalAddressEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsGetLocalAddressEx(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,      // AMS port of ADS client
    jobject lj_AmsAddr); // AMS address of ADS server

// AdsSyncWriteReq
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteReqEx(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,       // AMS port of ADS client
    jobject lj_AmsAddr,   // AMS address of ADS server
    jlong lj_indexGroup,  // index group in ADS server interface
    jlong lj_indexOffset, // index offset in ADS server interface
    jlong lj_length,      // count of bytes to read
    jobject lj_pData);    // pointer to the client buffer

// AdsSyncWriteReqEx jbyteArray
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteReqExArray(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,       // AMS port of ADS client
    jobject lj_AmsAddr,   // AMS address of ADS server
    jlong lj_indexGroup,  // index group in ADS server interface
    jlong lj_indexOffset, // index offset in ADS server interface
    jlong lj_length,      // count of bytes to read
    jbyteArray lj_pData); // pointer to the client buffer

// AdsSyncWriteReqEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteReqExArray__I(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,       // AMS port of ADS client
    jobject lj_AmsAddr,   // AMS address of ADS server
    jlong lj_indexGroup,  // index group in ADS server interface
    jlong lj_indexOffset, // index offset in ADS server interface
    jlong lj_length,      // count of bytes to read
    jobject lj_pData);    // pointer to the client buffer

// AdsSyncReadReqEx2
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadReqEx2(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,           // AMS port of ADS client
    jobject lj_AmsAddr,       // AMS address of ADS server
    jlong lj_indexGroup,      // index group in ADS server interface
    jlong lj_indexOffset,     // index offset in ADS server interface
    jlong lj_length,          // count of bytes to read
    jobject lj_pData,         // pointer to the client buffer
    jobject lj_lengthReturn); // count of bytes read

// AdsSyncReadWriteReqEx2
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadWriteReqEx2(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,           // AMS port of ADS client
    jobject lj_AmsAddr,       // AMS address of ADS server
    jlong lj_indexGroup,      // index group in ADS server interface
    jlong lj_indexOffset,     // index offset in ADS server interface
    jlong lj_cbReadLength,    // count of bytes to read
    jobject lj_pReadData,     // pointer to the client buffer
    jlong lj_cbWriteLength,   // count of bytes to write
    jobject lj_pWriteData,    // pointer to the client buffer
    jobject lj_lengthReturn); // count of bytes read

// AdsSyncReadDeviceInfoReqEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadDeviceInfoReqEx(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,       // AMS port of ADS client
    jobject lj_AmsAddr,   // AMS address of ADS server
    jobject lj_pDevName,  // fixed length string (16 Byte)
    jobject lj_pVersion); // client buffer to store server version

// AdsSyncWriteControlReqEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncWriteControlReqEx(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,      // AMS port of ADS client
    jobject lj_AmsAddr,  // AMS address of ADS server
    jint lj_adsState,    // index group in ADS server interface
    jint lj_deviceState, // index offset in ADS server interface
    jlong lj_length,     // count of bytes to write
    jobject lj_pData);   // pointer to the client buffer

// AdsSyncReadStateReqEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncReadStateReqEx(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,           // AMS port of ADS client
    jobject lj_AmsAddr,       // AMS address of ADS server
    jobject lj_nAdsState,     // pointer to client buffer
    jobject lj_nDeviceState); // pointer to the client buffer

// AdsSyncSetTimeoutEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncSetTimeoutEx(
    JNIEnv* env, jobject obj,
    jlong lj_nPort, // AMS port of ADS client
    jlong lj_nMs);  // Set timeout in ms

// AdsSyncGetTimeoutEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncGetTimeoutEx(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,  // AMS port of ADS client
    jobject lj_nMs); // Get timeout in ms

// AdsSyncAddDeviceNotificationReqEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncAddDeviceNotificationReqEx(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,            // AMS port of ADS client
    jobject lj_pAddr,          // AMS address of ADS server
    jlong lj_indexGroup,       // index group in ADS server interface
    jlong lj_indexOffset,      // index offset in ADS server interface
    jobject lj_pNoteAttrib,    // attributes of notification request
    jlong lj_hUser,            // user handle
    jobject lj_pNotification); // pointer to notification handle (return value)

// AdsSyncDelDeviceNotificationReqEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsSyncDelDeviceNotificationReqEx(
    JNIEnv* env, jobject obj,
    jlong lj_nPort,            // AMS port of ADS client
    jobject lj_pAddr,          // AMS address of ADS server
    jobject lj_hNotification); // notification handle

// AdsAmsPortEnabledEx
JNIEXPORT jlong JNICALL
Java_de_beckhoff_jni_tcads_AdsCallDllFunction_callDllAdsAmsPortEnabledEx(
    JNIEnv* env, jobject obj,
    jlong lj_nPort, // AMS port of ADS client
    jobject bEnabled);
}