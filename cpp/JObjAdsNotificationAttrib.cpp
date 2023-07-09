#include "JObjAdsNotificationAttrib.h"

JObjAdsNotificationAttrib::JObjAdsNotificationAttrib(JNIEnv* lEnv,
                                                     jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

void JObjAdsNotificationAttrib::setValuesInJObject(
    AdsNotificationAttrib* pAdsNotificationAttrib) {
    setJObjectValue("mCbLength",
                    static_cast<jlong>(pAdsNotificationAttrib->cbLength));

    // determine mNTransMode
    int lTransMode = 0;
    switch (pAdsNotificationAttrib->nTransMode) {
    case ADSTRANS_NOTRANS:
        lTransMode = 0;
        break;
    case ADSTRANS_CLIENTCYCLE:
        lTransMode = 1;
        break;
    case ADSTRANS_CLIENT1REQ:
        lTransMode = 2;
        break;
    case ADSTRANS_SERVERCYCLE:
        lTransMode = 3;
        break;
    case ADSTRANS_SERVERONCHA:
        lTransMode = 4;
        break;
    default:
        break;
    }

    setJObjectValue("mNTransMode", static_cast<jint>(lTransMode));
    setJObjectValue("mNMaxDelay",
                    static_cast<jlong>(pAdsNotificationAttrib->nMaxDelay));
    setJObjectValue("mNCycleTime",
                    static_cast<jlong>(pAdsNotificationAttrib->nCycleTime));
    setJObjectValue("mDwChangeFilter",
                    static_cast<jlong>(pAdsNotificationAttrib->dwChangeFilter));
}

void JObjAdsNotificationAttrib::getValuesOutJObject(
    AdsNotificationAttrib* pAdsNotificationAttrib) {
    unsigned long lULong = 0;
    getJObjectValue("mCbLength", &lULong);
    pAdsNotificationAttrib->cbLength = lULong;

    // determine mNTransMode
    int lInt = 0;
    getJObjectValue("mNTransMode", &lInt);
    ADSTRANSMODE lADSTRANSMODE = ADSTRANS_NOTRANS;
    switch (lInt) {
    case 0:
        lADSTRANSMODE = ADSTRANS_NOTRANS;
        break;
    case 1:
        lADSTRANSMODE = ADSTRANS_CLIENTCYCLE;
        break;
    case 2:
        lADSTRANSMODE = ADSTRANS_CLIENT1REQ;
        break;
    case 3:
        lADSTRANSMODE = ADSTRANS_SERVERCYCLE;
        break;
    case 4:
        lADSTRANSMODE = ADSTRANS_SERVERONCHA;
        break;
    default:
        break;
    }

    pAdsNotificationAttrib->nTransMode = lADSTRANSMODE;

    getJObjectValue("mNMaxDelay", &lULong);
    pAdsNotificationAttrib->nMaxDelay = lULong;

    getJObjectValue("mNCycleTime", &lULong);
    pAdsNotificationAttrib->nCycleTime = lULong;

    getJObjectValue("mDwChangeFilter", &lULong);
    pAdsNotificationAttrib->dwChangeFilter = lULong;
}
