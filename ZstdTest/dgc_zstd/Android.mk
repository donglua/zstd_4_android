#LOCAL_PATH := $(call my-dir)
LOCAL_SRC_PATH := $(LOCAL_PATH)/dgc_zstd
LIB_PATH := $(LOCAL_PATH)/zstd_libfinal
include $(CLEAR_VARS)
LOCAL_MODULE := dgc_zstd
#LOCAL_C_INCLUDES 可选变量，表示头文件的搜索路径。默认的头文件的搜索路径是LOCAL_PATH目录。

LOCAL_SRC_FILES := $(LOCAL_SRC_PATH)/jni_zstd.c \
                $(LOCAL_SRC_PATH)/jni_zdict.c \
                $(LOCAL_SRC_PATH)/jni_outputstream_zstd.c \
                $(LOCAL_SRC_PATH)/jni_inputstream_zstd.c \
                $(LOCAL_SRC_PATH)/jni_fast_zstd.c \
                $(LOCAL_SRC_PATH)/jni_directbufferdecompress_zstd.c \
                $(LOCAL_SRC_PATH)/jni_directbuffercompress_zstd.c

LOCAL_STATIC_LIBRARIES := libzstd

LOCAL_C_INCLUDES := $(LIB_PATH) \
                $(LIB_PATH)/common \
                $(LIB_PATH)/compress \
                $(LIB_PATH)/decompress \
                $(LIB_PATH)/dictBuilder \
                $(LIB_PATH)/legacy

LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)