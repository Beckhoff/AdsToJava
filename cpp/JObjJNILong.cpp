#include "JObjJNILong.h"

JObjJNILong::JObjJNILong(JNIEnv* lEnv, jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

void JObjJNILong::setValuesInJObject(const long* pLong) {
    setJObjectValue("mLong", static_cast<jlong>(*pLong));
}

void JObjJNILong::getValuesOutJObject(long* pLong) {
    getJObjectValue("mLong", pLong);
}

void JObjJNILong::setValuesInJObject(const unsigned long* pULong) {
    setJObjectValue("mLong", static_cast<jlong>(*pULong));
}

void JObjJNILong::getValuesOutJObject(unsigned long* pULong) {
    getJObjectValue("mLong", pULong);
}
