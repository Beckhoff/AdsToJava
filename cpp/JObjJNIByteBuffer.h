#pragma once
#include "JObjectBase.h"
#include "jni.h"

class JObjJNIByteBuffer : public JObjectBase {
  public:
    JObjJNIByteBuffer(JNIEnv* lEnv, jobject lJObject);
    ~JObjJNIByteBuffer();

    void setValuesInJObject(unsigned char* pByteArray);
    void getValuesOutJObject(unsigned char* pByteArray);
};
