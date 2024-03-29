#pragma once
#include "JObjectBase.h"
#include "StdAfx.h"
#include "jni.h"

class JObjAdsNotificationHeader : public JObjectBase {
  public:
    JObjAdsNotificationHeader(JNIEnv* lEnv, jobject lJObject);
    ~JObjAdsNotificationHeader() = default;

    void
    setValuesInJObject(const AdsNotificationHeader* pAdsNotificationHeader);
};
