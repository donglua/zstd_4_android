package com.github.luben.zstd.util;

import android.util.Log;

public enum Native {
    ;

    private static final String libname = "libzstd";

    private static String osName() {
        String os = System.getProperty("os.name").toLowerCase().replace(' ', '_');
        if (os.startsWith("win")){
            return "win";
        } else if (os.startsWith("mac")) {
            return "darwin";
        } else {
            return os;
        }
    }

    private static String osArch() {
        return System.getProperty("os.arch");
    }

    private static String libExtension() {
        if (osName().contains("os_x") || osName().contains("darwin")) {
            return "dylib";
         } else if (osName().contains("win")) {
            return "dll";
        } else {
            return "so";
        }
    }

    private static String resourceName() {
        String resName = "/" + osName() + "/" + osArch() + "/" + libname + "." + libExtension();
        Log.d("hangl_debug", "native lib res name will be " + resName);
        return resName;
    }

    private static boolean loaded = false;

    public static synchronized boolean isLoaded() {
        return loaded;
    }

    public static synchronized void load() {
        if (loaded) {
            return;
        }

        /**
         * load(String)
         * This should be of the form {@code /path/to/library/libMyLibrary.so}.
         *
         * loadLibrary(String)
         * loadLibrary(MyLibrary);
         * */

        System.loadLibrary("dgc_zstd");
        loaded = true;

        //        String resourceName = resourceName();
        //
        //        Log.d("hangl_debug", "resource name is " + resourceName);
        //        String osArch = osArch();
        //
        //        Context context = ContextHandler.getInstance().getContextRef();
        //        String targetso = libname + "_" + osArch + ".so";
        //        InputStream is = null;
        //        if (context != null) {
        //            try {
        //                //is = context.getAssets().open(resourceName);
        //                //is = context.getAssets().open("libzstd.so");
        //                //String targetso = libname + "_" + osArch + ".so";
        //                is = context.getAssets().open(targetso);
        //            } catch (IOException e) {
        //                e.printStackTrace();
        //            }
        //        }
        //
        //        Log.d("hangl_debug", "in load() context == "+context+" is return " + is);
        //
        //        //is = Native.class.getResourceAsStream(resourceName);
        //        if (is == null) {
        ////            throw new UnsupportedOperationException("Unsupported OS/arch, cannot find " +
        ////                    resourceName + ". Please try building from source.");
        //            throw new UnsupportedOperationException("Unsupported OS/arch, cannot find " +
        //                    targetso + ". Please try building from source.");
        //        }
        //        File tempLib;
        //        try {
        //            tempLib = File.createTempFile(libname, "." + libExtension());
        //            // copy to tempLib
        //            FileOutputStream out = new FileOutputStream(tempLib);
        //            try {
        //                byte[] buf = new byte[4096];
        //                while (true) {
        //                    int read = is.read(buf);
        //                    if (read == -1) {
        //                        break;
        //                    }
        //                    out.write(buf, 0, read);
        //                }
        //                try {
        //                    out.close();
        //                    out = null;
        //                } catch (IOException e) {
        //                    // ignore
        //                }
        //                Log.d("hangl_debug", "temp lib path = " + tempLib.getPath() + " temp lib name = " + tempLib.getName());
        //                System.load(tempLib.getAbsolutePath());
        //                loaded = true;
        //            } finally {
        //                try {
        //                    if (out != null) {
        //                        out.close();
        //                    }
        //                } catch (IOException e) {
        //                  // ignore
        //                }
        //                if (tempLib != null && tempLib.exists()) {
        //                    if (!loaded) {
        //                        tempLib.delete();
        //                    } else {
        //                        // try to delete on exit, does it work on Windows?
        //                        tempLib.deleteOnExit();
        //                    }
        //                }
        //            }
        //        } catch (IOException e) {
        //            throw new ExceptionInInitializerError("Cannot unpack " + libname);
        //        }
    }
}
