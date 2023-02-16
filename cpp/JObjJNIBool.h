#pragma once
#include "JObjectBase.h"
#include "jni.h"

class JObjJNIBool : public JObjectBase {
  public:
    JObjJNIBool(JNIEnv* lEnv, jobject lJObject);
    ~JObjJNIBool() = default;

    void setValuesInJObject(const bool* pBool);
    void getValuesOutJObject(bool* pBool);
};
