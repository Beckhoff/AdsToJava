#pragma once
#include "JObjectBase.h"
#include "StdAfx.h"
#include "jni.h"

class JObjAmsNetId : public JObjectBase {
  public:
    JObjAmsNetId(JNIEnv* lEnv, jobject lJObject);
    ~JObjAmsNetId() = default;

    void setValuesInJObject(const AmsNetId* pNetId);
    void getValuesOutJObject(PAmsNetId pNetId);
};
