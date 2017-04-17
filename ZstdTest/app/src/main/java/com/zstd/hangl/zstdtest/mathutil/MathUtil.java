package com.zstd.hangl.zstdtest.mathutil;

/**
 * @author Guoliang Han (hanguoliang@didichuxing.com)
 * @since 17/4/13
 */

public class MathUtil {

    //libmath.so
    static {
        System.loadLibrary("mathutil");
    }

    public static native int add(int a, int b);


    public static native int reduce(int x, int y);

}
