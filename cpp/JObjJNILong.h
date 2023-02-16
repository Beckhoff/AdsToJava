#pragma once
#include "JObjectBase.h"
#include "jni.h"

class JObjJNILong : public JObjectBase {
  public:
    JObjJNILong(JNIEnv* lEnv, jobject lJObject);
    ~JObjJNILong() = default;

    void setValuesInJObject(const long* pLong);
    void getValuesOutJObject(long* pLong);

    void setValuesInJObject(const unsigned long* pULong);
    void getValuesOutJObject(unsigned long* pULong);
};
