#include "JObjAdsState.h"

JObjAdsState::JObjAdsState(JNIEnv* lEnv, jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

JObjAdsState::~JObjAdsState() = default;

void JObjAdsState::setValuesInJObject(const unsigned short* pAdsState) {
    setJObjectValue("mState", static_cast<jshort>(*pAdsState));
}

void JObjAdsState::getValuesOutJObject(unsigned short* pAdsState) {
    short lShort;
    getJObjectValue("mState", &lShort);
    *pAdsState = lShort;
}