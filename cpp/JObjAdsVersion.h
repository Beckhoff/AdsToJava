#pragma once
#include "JObjectBase.h"
#include "StdAfx.h"
#include "jni.h"

class JObjAdsVersion : public JObjectBase {
  public:
    JObjAdsVersion(JNIEnv* lEnv, jobject lJObject);
    ~JObjAdsVersion();

    void setValuesInJObject(AdsVersion* pAdsVersion);
    void getValuesOutJObject(AdsVersion* pAdsVersion);
};
