#include "JObjAdsNotificationHeader.h"
#include "JObjectBase.h"
#include "jni.h"
#include "jni_md.h"
#ifdef USE_OPENSOURCE_ADSLIB
#include "standalone/AdsDef.h"
#else
#include "TcAdsAPI.h"
#endif
#include <cstdint>

JObjAdsNotificationHeader::JObjAdsNotificationHeader(JNIEnv* lEnv,
                                                     jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

void JObjAdsNotificationHeader::setValuesInJObject(
    const AdsNotificationHeader* pAdsNotificationHeader) {
    setJObjectValue("mHNotification",
                    static_cast<jlong>(pAdsNotificationHeader->hNotification));
    setJObjectValue("mNTimeStamp",
                    static_cast<jlong>(pAdsNotificationHeader->nTimeStamp));
    const auto* data_ptr = reinterpret_cast<const uint8_t*>(
                               &pAdsNotificationHeader->cbSampleSize) +
                           sizeof(pAdsNotificationHeader->cbSampleSize);
    setJObjectArray("data", data_ptr, false);
}
