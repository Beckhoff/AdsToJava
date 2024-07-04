#include "JObjAmsNetId.h"
#include "JObjectBase.h"
#include "StdAfx.h"
#include "jni.h"
#ifdef USE_OPENSOURCE_ADSLIB
#include "standalone/AdsDef.h"
#else
#include "TcAdsAPI.h"
#endif

JObjAmsNetId::JObjAmsNetId(JNIEnv* lEnv, jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

void JObjAmsNetId::setValuesInJObject(const AmsNetId* pNetId) {
    setJObjectArray("mB", &pNetId->b[0], true);
}

void JObjAmsNetId::getValuesOutJObject(PAmsNetId pNetId) {
    getJObjectArray("mB", &pNetId->b[0], true);
}
