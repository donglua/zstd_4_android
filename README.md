# zstd4android
A demo android project to use zstd . zstd shared Library files built in Android Studio IDE with ndk build tools.

## build native shared library
In AS Terminal window, cd your Project root dir, execute 'ndk-build NDKPROJECTPATH=./ NDKAPPLICATIONMK=./jni/Application.mk', if ndk-build cmd can't be found, you should point out the abosolute path of ndk-build special. like /path/to/ndkbuildtoolsroot/ndk-build NDKPROJECTPATH=./ NDKAPPLICATION_MK=./jni/Application.mk,

➜ ZstdTest /Users/didi/Library/Android/sdk/ndk-bundle/ndk-build NDKPROJECTPATH=./ NDKAPPLICATIONMK=./jni/Application.mk

If build successfully, you can locate .so files under /PROJECTROOT/libs/ABI/libxxx.so. you can change the libxxx.so file name by modify Andorid.mk under dgczstd/ dir(I put jni file under this folder) with change LOCALMODULE's value, mine is   LOCALMODULE := dgc_zstd.

## introduction
My Android project is based on @luben's zstd-jni project, which git repo located at https://github.com/luben/zstd-jni. The java source files .c and .h files all come from zstd-jni project without any change but Native.java, in Native.java I change the way to load .so library. I've tried to use the zstd-1.1.4 jar lib which you can find in maven_central with gradle import into my android project, but I found it not suitable for andorid project, your will catch some crash exception when use, so I create this android project and rebuild zstd native shared library for andorid use. If you want to zstd in your andorid project, maybe this can give your some help. Thanks for @luben's help when I use zstd.
