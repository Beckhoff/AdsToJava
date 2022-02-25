#include "JObjAdsVersion.h"

JObjAdsVersion::JObjAdsVersion(JNIEnv* lEnv, jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

JObjAdsVersion::~JObjAdsVersion() = default;

void JObjAdsVersion::setValuesInJObject(AdsVersion* pAdsVersion) {
    setJObjectValue("mVersion", static_cast<jchar>(pAdsVersion->version));
    setJObjectValue("mRevision", static_cast<jchar>(pAdsVersion->revision));
    setJObjectValue("mBuild", static_cast<jshort>(pAdsVersion->build));
}

void JObjAdsVersion::getValuesOutJObject(AdsVersion* pAdsVersion) {
    short lShort;
    char lChar;

    getJObjectValue("mVersion", &lChar);
    pAdsVersion->version = lChar;

    getJObjectValue("mRevision", &lChar);
    pAdsVersion->revision = lChar;

    getJObjectValue("mBuild", &lShort);
    pAdsVersion->build = lShort;
}