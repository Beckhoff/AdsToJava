#include "JObjAdsNotificationHeader.h"

JObjAdsNotificationHeader::JObjAdsNotificationHeader(JNIEnv* lEnv,
                                                     jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

JObjAdsNotificationHeader::~JObjAdsNotificationHeader() = default;

void JObjAdsNotificationHeader::setValuesInJObject(
    AdsNotificationHeader* pAdsNotificationHeader) {
    setJObjectValue("mHNotification",
                    static_cast<jlong>(pAdsNotificationHeader->hNotification));
    setJObjectValue("mNTimeStamp",
                    static_cast<jlong>(pAdsNotificationHeader->nTimeStamp));
    setJObjectArray("data", &pAdsNotificationHeader->data[0], false);
}
