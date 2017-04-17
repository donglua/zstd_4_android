LOCAL_PATH := $(call my-dir)

subdirs := $(addprefix $(LOCAL_PATH)/,$(addsuffix /Android.mk, \
		zstd_libfinal \
		dgc_zstd   \
	))

include $(subdirs)