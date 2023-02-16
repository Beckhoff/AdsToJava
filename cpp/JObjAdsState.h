#pragma once
#include "JObjectBase.h"
#include "StdAfx.h"
#include "jni.h"

class JObjAdsState : public JObjectBase {
  public:
    JObjAdsState(JNIEnv* lEnv, jobject lJObject);
    ~JObjAdsState() = default;

    void setValuesInJObject(const unsigned short* pAdsState);
    void getValuesOutJObject(unsigned short* pAdsState);
};
