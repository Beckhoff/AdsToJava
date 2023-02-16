#include "JObjAdsState.h"

JObjAdsState::JObjAdsState(JNIEnv* lEnv, jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

void JObjAdsState::setValuesInJObject(const unsigned short* pAdsState) {
    setJObjectValue("mState", static_cast<jshort>(*pAdsState));
}

void JObjAdsState::getValuesOutJObject(unsigned short* pAdsState) {
    short lShort = 0;
    getJObjectValue("mState", &lShort);
    *pAdsState = lShort;
}