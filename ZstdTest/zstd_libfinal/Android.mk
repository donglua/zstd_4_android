#LOCAL_PATH := $(call my-dir)
LIB_PATH := $(LOCAL_PATH)/zstd_libfinal
include $(CLEAR_VARS)
LOCAL_MODULE := libzstd
#LOCAL_C_INCLUDES 可选变量，表示头文件的搜索路径。默认的头文件的搜索路径是LOCAL_PATH目录。
LOCAL_C_INCLUDES := $(LIB_PATH) \
                $(LIB_PATH)/common \
                $(LIB_PATH)/compress \
                $(LIB_PATH)/decompress \
                $(LIB_PATH)/dictBuilder \
                $(LIB_PATH)/legacy
LOCAL_SRC_FILES := $(LIB_PATH)/common/zstd_common.c    \
                $(LIB_PATH)/common/xxhash.c    \
                $(LIB_PATH)/common/threading.c    \
                $(LIB_PATH)/common/pool.c    \
                $(LIB_PATH)/common/fse_decompress.c    \
                $(LIB_PATH)/common/error_private.c    \
                $(LIB_PATH)/common/entropy_common.c    \
                $(LIB_PATH)/compress/zstdmt_compress.c    \
                $(LIB_PATH)/compress/zstd_compress.c    \
                $(LIB_PATH)/compress/huf_compress.c    \
                $(LIB_PATH)/compress/fse_compress.c    \
                $(LIB_PATH)/decompress/huf_decompress.c    \
                $(LIB_PATH)/decompress/zstd_decompress.c    \
                $(LIB_PATH)/dictBuilder/cover.c    \
                $(LIB_PATH)/dictBuilder/divsufsort.c    \
                $(LIB_PATH)/dictBuilder/zdict.c    \
                $(LIB_PATH)/legacy/zstd_v04.c    \
                $(LIB_PATH)/legacy/zstd_v05.c    \
                $(LIB_PATH)/legacy/zstd_v06.c    \
                $(LIB_PATH)/legacy/zstd_v07.c
include $(BUILD_STATIC_LIBRARY)