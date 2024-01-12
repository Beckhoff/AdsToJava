#pragma once
#include "JObjAmsNetId.h"
#include "JObjectBase.h"
#include "StdAfx.h"
#include "jni.h"

class JObjAmsAddr : public JObjectBase {
  public:
    JObjAmsAddr(JNIEnv* lEnv, jobject lJObject);
    ~JObjAmsAddr() = default;

    void setValuesInJObject(const AmsAddr* pAddr);
    void getValuesOutJObject(PAmsAddr pAddr);
};
