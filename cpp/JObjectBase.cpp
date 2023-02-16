#include "JObjectBase.h"
#include "jni.h"

JObjectBase::JObjectBase(JNIEnv* lEnv, jobject lJObject)
    : mEnv(lEnv), mJObject(lJObject), mJClass(lEnv->GetObjectClass(lJObject)) {}

void JObjectBase::setJObjectValue(const char* lFieldName, jboolean lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "Z");
    if (lj_FieldId != nullptr) {
        mEnv->SetBooleanField(mJObject, lj_FieldId, lValue);
    }
}

void JObjectBase::setJObjectValue(const char* lFieldName, jbyte lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "B");
    if (lj_FieldId != nullptr) {
        mEnv->SetByteField(mJObject, lj_FieldId, lValue);
    }
}

void JObjectBase::setJObjectValue(const char* lFieldName, jchar lValue) {
    // C++  unsigned char 0 to 255
    // C++  char          128 to 127
    // Java char          all unicode characters (2byte)

    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "C");
    if (lj_FieldId != nullptr) {
        mEnv->SetCharField(mJObject, lj_FieldId, lValue);
    }
}

void JObjectBase::setJObjectValue(const char* lFieldName, jshort lValue) {
    // C++   unsigned short  0 to 65,535
    // C++   short           32,768 to 32,767
    // Java  short           -215...215-1

    // C++   unsigned short  --> Int (Java)
    // Java  int             -2^31...2^31-1

    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "S");
    if (lj_FieldId != nullptr) {
        mEnv->SetShortField(mJObject, lj_FieldId, lValue);
    }
}

void JObjectBase::setJObjectValue(const char* lFieldName, jint lValue) {
    // C++  int              System dependent
    // C++  int64_t          -2^63...2^63-1
    // Java int              -2^31...2^31-1

    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "I");
    if (lj_FieldId != nullptr) {
        mEnv->SetIntField(mJObject, lj_FieldId, lValue);
    }
}

void JObjectBase::setJObjectValue(const char* lFieldName, jlong lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "J");
    if (lj_FieldId != nullptr) {
        mEnv->SetLongField(mJObject, lj_FieldId, lValue);
    }
}

void JObjectBase::setJObjectValue(const char* lFieldName, jfloat lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "F");
    if (lj_FieldId != nullptr) {
        mEnv->SetFloatField(mJObject, lj_FieldId, lValue);
    }
}

void JObjectBase::setJObjectValue(const char* lFieldName, jdouble lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "D");
    if (lj_FieldId != nullptr) {
        mEnv->SetDoubleField(mJObject, lj_FieldId, lValue);
    }
}

void JObjectBase::setJObjectValueString(const char* lFieldName,
                                        const char** lValue) {
    jfieldID lj_FieldId =
        mEnv->GetFieldID(mJClass, lFieldName, "Ljava/lang/String;");
    if (lj_FieldId != nullptr) {
        mEnv->SetObjectField(mJObject, lj_FieldId, mEnv->NewStringUTF(*lValue));
    }
}

void JObjectBase::getJObjectValue(const char* lFieldName, bool* lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "Z");
    if (lj_FieldId != nullptr) {
        const jboolean lJValue = mEnv->GetBooleanField(mJObject, lj_FieldId);
        *lValue = (lJValue != 0);
    }
}

void JObjectBase::getJObjectValue(const char* lFieldName,
                                  unsigned char* lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "B");
    if (lj_FieldId != nullptr) {
        *lValue = static_cast<unsigned char>(
            mEnv->GetByteField(mJObject, lj_FieldId));
    }
}

void JObjectBase::getJObjectValue(const char* lFieldName, char* lValue) {
    // C++  unsigned char 0 to 255
    // C++  char          128 to 127
    // Java char          all unicode characters (2byte)

    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "C");

    if (lj_FieldId != nullptr) {
        *lValue = static_cast<char>(mEnv->GetCharField(mJObject, lj_FieldId));
    }
}

void JObjectBase::getJObjectValue(const char* lFieldName, short* lValue) {
    // C++  unsigned short 0 to 65,535
    // C++  short          32,768 to 32,767
    // Java short          -2^15...2^15-1

    // C++  unsigned short --> int in Java
    // Java int            -2^31...2^31-1

    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "S");
    if (lj_FieldId != nullptr) {
        *lValue = static_cast<short>(mEnv->GetShortField(mJObject, lj_FieldId));
    }
}

void JObjectBase::getJObjectValue(const char* lFieldName, int* lValue) {
    // C++  int            System dependent
    // C++  int64_t        -2^63...2^63-1
    // Java int            -2^31...2^31-1

    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "I");
    if (lj_FieldId != nullptr) {
        *lValue = static_cast<int>(mEnv->GetIntField(mJObject, lj_FieldId));
    }
}

#ifdef _MSC_VER // clang treats long and int64_t as the same type and gives a
                // redefinition error
void JObjectBase::getJObjectValue(const char* lFieldName, long* lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "J");
    if (lj_FieldId != nullptr) {
        *lValue = (long)mEnv->GetLongField(mJObject, lj_FieldId);
    }
}
#endif

void JObjectBase::getJObjectValue(const char* lFieldName,
                                  unsigned long* lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "J");
    if (lj_FieldId != nullptr) {
        *lValue = static_cast<unsigned long>(
            mEnv->GetLongField(mJObject, lj_FieldId));
    }
}

void JObjectBase::getJObjectValue(const char* lFieldName, int64_t* lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "J");
    if (lj_FieldId != nullptr) {
        *lValue =
            static_cast<int64_t>(mEnv->GetLongField(mJObject, lj_FieldId));
    }
}

void JObjectBase::getJObjectValue(const char* lFieldName, float* lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "F");
    if (lj_FieldId != nullptr) {
        *lValue = static_cast<float>(mEnv->GetFloatField(mJObject, lj_FieldId));
    }
}

void JObjectBase::getJObjectValue(const char* lFieldName, double* lValue) {
    jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "D");
    if (lj_FieldId != nullptr) {
        *lValue =
            static_cast<double>(mEnv->GetDoubleField(mJObject, lj_FieldId));
    }
}

void JObjectBase::getJObjectValueString(const char* lFieldName,
                                        const char** lValue) {
    jfieldID lj_FieldId =
        mEnv->GetFieldID(mJClass, lFieldName, "Ljava/lang/String;");
    if (lj_FieldId != nullptr) {
        // get the jstring representation of the current ObjectField
        auto* ljString =
            static_cast<jstring>(mEnv->GetObjectField(mJObject, lj_FieldId));

        // get the char* representation of the jstring value
        const char* lText = mEnv->GetStringUTFChars(ljString, nullptr);
        *lValue = lText;
    }
}

////Setter (Array)
void JObjectBase::setJObjectArray(const char* lFieldName,
                                  const unsigned char* lValue,
                                  bool isJCharArray) {
    if (!isJCharArray) {
        jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "[B");
        if (lj_FieldId != nullptr) {
            auto* ljbyteArray = static_cast<jbyteArray>(
                mEnv->GetObjectField(mJObject, lj_FieldId));

            const jsize arr_size = mEnv->GetArrayLength(ljbyteArray);
            jbyte* lj_pDataElements =
                mEnv->GetByteArrayElements(ljbyteArray, nullptr);

            // assign new values to elements
            for (int i = 0; i < arr_size; i++) {
                lj_pDataElements[i] = static_cast<jbyte>(lValue[i]);
            }

            mEnv->ReleaseByteArrayElements(ljbyteArray, lj_pDataElements, 0);
        }
    } else {
        jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "[C");
        if (lj_FieldId != nullptr) {
            auto* ljcharArray = static_cast<jcharArray>(
                mEnv->GetObjectField(mJObject, lj_FieldId));

            const jsize arr_size = mEnv->GetArrayLength(ljcharArray);
            jchar* lj_pDataElements =
                mEnv->GetCharArrayElements(ljcharArray, nullptr);

            // assign new values to elements
            for (int i = 0; i < arr_size; i++) {
                lj_pDataElements[i] = static_cast<jchar>(lValue[i]);
            }

            mEnv->ReleaseCharArrayElements(ljcharArray, lj_pDataElements, 0);
        }
    }
}

void JObjectBase::getJObjectArray(const char* lFieldName, unsigned char* lValue,
                                  bool isJCharArray) {
    if (!isJCharArray) {
        jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "[B");
        if (lj_FieldId != nullptr) {
            auto* ljbyteArray = static_cast<jbyteArray>(
                mEnv->GetObjectField(mJObject, lj_FieldId));

            const jsize arr_size = mEnv->GetArrayLength(ljbyteArray);
            jbyte* lj_pDataElements =
                mEnv->GetByteArrayElements(ljbyteArray, nullptr);

            // assign new values from elements
            for (int i = 0; i < arr_size; i++) {
                lValue[i] = static_cast<unsigned char>(lj_pDataElements[i]);
            }

            mEnv->ReleaseByteArrayElements(ljbyteArray, lj_pDataElements, 0);
        }
    } else {
        jfieldID lj_FieldId = mEnv->GetFieldID(mJClass, lFieldName, "[C");
        if (lj_FieldId != nullptr) {
            auto* ljcharArray = static_cast<jcharArray>(
                mEnv->GetObjectField(mJObject, lj_FieldId));

            const jsize arr_size = mEnv->GetArrayLength(ljcharArray);
            jchar* lj_pDataElements =
                mEnv->GetCharArrayElements(ljcharArray, nullptr);

            // assign new values from elements
            for (int i = 0; i < arr_size; i++) {
                lValue[i] = static_cast<unsigned char>(lj_pDataElements[i]);
            }

            mEnv->ReleaseCharArrayElements(ljcharArray, lj_pDataElements, 0);
        }
    }
}
