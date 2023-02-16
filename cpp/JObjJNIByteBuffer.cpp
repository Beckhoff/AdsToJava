#include "JObjJNIByteBuffer.h"

JObjJNIByteBuffer::JObjJNIByteBuffer(JNIEnv* lEnv, jobject lJObject)
    : JObjectBase(lEnv, lJObject) {}

void JObjJNIByteBuffer::setValuesInJObject(unsigned char* pByteArray) {
    setJObjectArray("mByteArray", pByteArray, false);
}

void JObjJNIByteBuffer::getValuesOutJObject(unsigned char* pByteArray) {
    getJObjectArray("mByteArray", pByteArray, false);
}