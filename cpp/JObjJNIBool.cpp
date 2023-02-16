#include "JObjJNIBool.h"

JObjJNIBool::JObjJNIBool(JNIEnv* lEnv, jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

void JObjJNIBool::setValuesInJObject(const bool* pBool) {
    setJObjectValue("mBool", static_cast<jboolean>(*pBool));
}

void JObjJNIBool::getValuesOutJObject(bool* pBool) {
    getJObjectValue("mBool", pBool);
}