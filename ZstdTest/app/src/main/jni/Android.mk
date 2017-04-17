LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := mathutil
LOCAL_SRC_FILES := mathutil.c

include $(BUILD_SHARED_LIBRARY)