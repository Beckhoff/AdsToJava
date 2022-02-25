#pragma once
#include "JObjectBase.h"
#include "StdAfx.h"
#include "jni.h"

class JObjAdsNotificationAttrib : public JObjectBase {
  public:
    JObjAdsNotificationAttrib(JNIEnv* lEnv, jobject lJObject);
    ~JObjAdsNotificationAttrib();

    void setValuesInJObject(AdsNotificationAttrib* pAdsNotificationAttrib);
    void getValuesOutJObject(AdsNotificationAttrib* pAdsNotificationAttrib);
};
