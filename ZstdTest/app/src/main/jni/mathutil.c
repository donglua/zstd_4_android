#include<jni.h>
#include<stdio.h>
//#include "../tmp_classes/com_zstd_hangl_zstdtest_mathutil_MathUtil.h"

JNIEXPORT jint JNICALL Java_com_zstd_hangl_zstdtest_mathutil_MathUtil_add
  (JNIEnv *env, jclass jc, jint x, jint y) {
    return x + y;
}


JNIEXPORT jint JNICALL Java_com_zstd_hangl_zstdtest_mathutil_MathUtil_reduce
  (JNIEnv *env, jclass jc, jint x, jint y) {
    return x - y;
}
