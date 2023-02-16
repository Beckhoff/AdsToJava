#include "JObjAdsDevName.h"

JObjAdsDevName::JObjAdsDevName(JNIEnv* lEnv, jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

void JObjAdsDevName::setValuesInJObject(const char** pAdsDevName) {
    setJObjectValueString("mDevName", pAdsDevName);
}

void JObjAdsDevName::getValuesOutJObject(const char** pAdsDevName) {
    getJObjectValueString("mDevName", pAdsDevName);
}
