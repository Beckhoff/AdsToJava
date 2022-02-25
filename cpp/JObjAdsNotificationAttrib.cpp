#include "JObjAdsNotificationAttrib.h"

JObjAdsNotificationAttrib::JObjAdsNotificationAttrib(JNIEnv* lEnv,
                                                     jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

JObjAdsNotificationAttrib::~JObjAdsNotificationAttrib() = default;

void JObjAdsNotificationAttrib::setValuesInJObject(
    AdsNotificationAttrib* pAdsNotificationAttrib) {
    setJObjectValue("mCbLength",
                    static_cast<jlong>(pAdsNotificationAttrib->cbLength));

    // determine mNTransMode
    int lTransMode;
    switch (pAdsNotificationAttrib->nTransMode) {
    case ADSTRANS_NOTRANS:
        lTransMode = 0;
        break;
    case ADSTRANS_CLIENTCYCLE:
        lTransMode = 1;
        break;
#ifdef POSIX
    case ADSTRANS_CLIENT1REQ:
#else
    case ADSTRANS_CLIENTONCHA: // value 2 was renamed
#endif
        lTransMode = 2;
        break;
    case ADSTRANS_SERVERCYCLE:
        lTransMode = 3;
        break;
    case ADSTRANS_SERVERONCHA:
        lTransMode = 4;
        break;
    default:
        lTransMode = 0;
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
    unsigned long lULong;
    getJObjectValue("mCbLength", &lULong);
    pAdsNotificationAttrib->cbLength = lULong;

    // determine mNTransMode
    int lInt;
    getJObjectValue("mNTransMode", &lInt);
    ADSTRANSMODE lADSTRANSMODE;
    switch (lInt) {
    case 0:
        lADSTRANSMODE = ADSTRANS_NOTRANS;
        break;
    case 1:
        lADSTRANSMODE = ADSTRANS_CLIENTCYCLE;
        break;
    case 2:
#ifdef POSIX
        lADSTRANSMODE = ADSTRANS_CLIENT1REQ;
#else
        lADSTRANSMODE = ADSTRANS_CLIENTONCHA; // ADSTRANS_CLIENT1REQ was renamed
#endif
        break;
    case 3:
        lADSTRANSMODE = ADSTRANS_SERVERCYCLE;
        break;
    case 4:
        lADSTRANSMODE = ADSTRANS_SERVERONCHA;
        break;
    default:
        lADSTRANSMODE = ADSTRANS_NOTRANS;
    }

    pAdsNotificationAttrib->nTransMode = lADSTRANSMODE;

    getJObjectValue("mNMaxDelay", &lULong);
    pAdsNotificationAttrib->nMaxDelay = lULong;

    getJObjectValue("mNCycleTime", &lULong);
    pAdsNotificationAttrib->nCycleTime = lULong;

    getJObjectValue("mDwChangeFilter", &lULong);
    pAdsNotificationAttrib->dwChangeFilter = lULong;
}
