#include "JObjAdsVersion.h"

JObjAdsVersion::JObjAdsVersion(JNIEnv* lEnv, jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

void JObjAdsVersion::setValuesInJObject(AdsVersion* pAdsVersion) {
    setJObjectValue("mVersion", static_cast<jchar>(pAdsVersion->version));
    setJObjectValue("mRevision", static_cast<jchar>(pAdsVersion->revision));
    setJObjectValue("mBuild", static_cast<jshort>(pAdsVersion->build));
}

void JObjAdsVersion::getValuesOutJObject(AdsVersion* pAdsVersion) {
    char lChar = '\0';

    getJObjectValue("mVersion", &lChar);
    pAdsVersion->version = lChar;

    getJObjectValue("mRevision", &lChar);
    pAdsVersion->revision = lChar;

    short lShort = 0;
    getJObjectValue("mBuild", &lShort);
    pAdsVersion->build = lShort;
}