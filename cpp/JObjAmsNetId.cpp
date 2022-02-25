#include "JObjAmsNetId.h"

JObjAmsNetId::JObjAmsNetId(JNIEnv* lEnv, jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

JObjAmsNetId::~JObjAmsNetId() = default;

void JObjAmsNetId::setValuesInJObject(PAmsNetId pNetId) {
    setJObjectArray("mB", &pNetId->b[0], true);
}

void JObjAmsNetId::getValuesOutJObject(PAmsNetId pNetId) {
    getJObjectArray("mB", &pNetId->b[0], true);
}
