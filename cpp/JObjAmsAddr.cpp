#include "JObjAmsAddr.h"
#include <limits>
#include <string>

JObjAmsAddr::JObjAmsAddr(JNIEnv* lEnv, jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

void JObjAmsAddr::setValuesInJObject(const AmsAddr* pAddr) {
    setJObjectValue("mPort", static_cast<jint>(pAddr->port));

    // set both versions of the AmsNetId.
    // on failure, only abort the current version and try the other one.
    jfieldID netId_field =
        mEnv->GetFieldID(mJClass, "mNetId", "Lde/beckhoff/jni/tcads/AmsNetId;");

    jobject netId_obj = mEnv->GetObjectField(mJObject, netId_field);
    if (netId_obj != nullptr) {
        JObjAmsNetId ljObjAmsNetId(mEnv, netId_obj);
        ljObjAmsNetId.setValuesInJObject(&(pAddr->netId));
    }

    for (size_t i = 0; i < sizeof(pAddr->netId.b) / sizeof(pAddr->netId.b[0]);
         ++i) {
        setJObjectValue(("mNetIdPart" + std::to_string(i)).c_str(),
                        static_cast<jchar>(pAddr->netId.b[i]));
    }
}

void JObjAmsAddr::getValuesOutJObject(PAmsAddr pAddr) {
    int lInt = 0;
    getJObjectValue("mPort", &lInt);
    pAddr->port = (lInt < std::numeric_limits<unsigned short>::min() ||
                   lInt > std::numeric_limits<unsigned short>::max())
                      ? 0
                      : static_cast<unsigned short>(lInt);

    // get the AmsNetId values either from the AmsNetId object or the char
    // members of AmsAddr. use the latter approach only if the first one
    // fails.

    jfieldID netId_field =
        mEnv->GetFieldID(mJClass, "mNetId", "Lde/beckhoff/jni/tcads/AmsNetId;");

    jobject netId_obj = mEnv->GetObjectField(mJObject, netId_field);
    if (netId_obj != nullptr) {
        JObjAmsNetId ljObjAmsNetId(mEnv, netId_obj);
        ljObjAmsNetId.getValuesOutJObject(&(pAddr->netId));
    } else {
        for (size_t i = 0; i < sizeof(pAddr->netId.b) / sizeof(unsigned char);
             ++i) {
            char lChar = '\0';
            getJObjectValue(("mNetIdPart" + std::to_string(i)).c_str(), &lChar);
            pAddr->netId.b[i] = lChar;
        }
    }
}
