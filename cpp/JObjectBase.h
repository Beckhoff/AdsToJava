#pragma once
#include "jni.h"
#include <stdint.h>

class JObjectBase {
  protected:
    JNIEnv* mEnv;
    jobject mJObject;
    jclass mJClass;

  public:
    JObjectBase(JNIEnv* lEnv, jobject lJObject);
    ~JObjectBase() = default;

    // set native Value
    void setJObjectValue(const char* lFieldName, jboolean lValue);
    void setJObjectValue(const char* lFieldName, jbyte lValue);

    void setJObjectValue(const char* lFieldName, jchar lValue);
    void setJObjectValue(const char* lFieldName, jshort lValue);
    void setJObjectValue(const char* lFieldName, jint lValue);
    void setJObjectValue(const char* lFieldName, jlong lValue);

    void setJObjectValue(const char* lFieldName, jfloat lValue);
    void setJObjectValue(const char* lFieldName, jdouble lValue);

    // get native Value
    void getJObjectValue(const char* lFieldName, bool* lValue);
    void getJObjectValue(const char* lFieldName, unsigned char* lValue);

    void getJObjectValue(const char* lFieldName, char* lValue);
    void getJObjectValue(const char* lFieldName, short* lValue);
    void getJObjectValue(const char* lFieldName, int* lValue);

#ifdef _MSC_VER // clang treats long and int64_t as the same type and gives a
                // redefinition error
    void getJObjectValue(const char* lFieldName, long* lValue);
#endif

    void getJObjectValue(const char* lFieldName, unsigned long* lValue);
    void getJObjectValue(const char* lFieldName, int64_t* lValue);

    void getJObjectValue(const char* lFieldName, float* lValue);
    void getJObjectValue(const char* lFieldName, double* lValue);

    // get/set native arrays
    void setJObjectArray(const char* lFieldName, const unsigned char* lValue,
                         bool isJCharArray);
    void getJObjectArray(const char* lFieldName, unsigned char* lValue,
                         bool isJCharArray);

    // get/set native strings
    void setJObjectValueString(const char* lFieldName, const char** lValue);
    void getJObjectValueString(const char* lFieldName, const char** lValue);

    jstring getJStringFromPChar(char* lText);
};
