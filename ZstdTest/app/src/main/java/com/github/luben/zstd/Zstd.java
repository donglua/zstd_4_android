package com.github.luben.zstd;

import com.github.luben.zstd.util.Native;

import java.nio.ByteBuffer;
import java.util.Arrays;


public class Zstd {

    static {
        Native.load();
    }


    /**
     * fuck this
     *
     * */
    /**
    JNIEXPORT jlong JNICALL Java_com_github_luben_zstd_Zstd_decompressedSize
            (JNIEnv *env, jclass obj, jbyteArray src) {
        size_t size = (size_t)(0-ZSTD_error_memory_allocation);
        jsize src_size = (*env)->GetArrayLength(env, src);
        void *src_buff = (*env)->GetPrimitiveArrayCritical(env, src, NULL);
        if (src_buff == NULL) goto E1;
        size = ZSTD_getDecompressedSize(src_buff, (size_t) src_size);
        (*env)->ReleasePrimitiveArrayCritical(env, src, src_buff, 0);
        E1: return size;
    }
    **/

    /**
     * Compresses buffer 'src' into buffer 'dst'.
     *
     * Destination buffer should be sized to handle worst cases situations (input
     * data not compressible). Worst case size evaluation is provided by function
     * ZSTD_compressBound().
     *
     * @param dst the destination buffer
     * @param src the source buffer
     * @param level compression level
     * @return  the number of bytes written into buffer 'dst' or an error code if
     *          it fails (which can be tested using ZSTD_isError())
     */
    public static native long compress  (byte[] dst, byte[] src, int level);

    /**
     * Compresses buffer 'src' into buffer 'dst'.
     *
     * Destination buffer should be sized to handle worst cases situations (input
     * data not compressible). Worst case size evaluation is provided by function
     * ZSTD_compressBound().
     *
     * @param dst the destination buffer
     * @param src the source buffer
     * @param level compression level
     * @return  the number of bytes written into buffer 'dst' or an error code if
     *          it fails (which can be tested using ZSTD_isError())
     */
    public static native long compressDirectByteBuffer  (ByteBuffer dst, int dstOffset, int dstSize, ByteBuffer src, int srcOffset, int srcSize, int level);

   /**
     * Compresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to handle worst cases situations (input
     * data not compressible). Worst case size evaluation is provided by function
     * ZSTD_compressBound().
     *
     * @param dst the destination buffer
     * @param dstOffset the start offset of 'dst'
     * @param src the source buffer
     * @param srcOffset the start offset of 'src'
     * @param length the length of 'src'
     * @param dict the dictionary buffer
     * @param level compression level
     * @return  the number of bytes written into buffer 'dst' or an error code if
     *          it fails (which can be tested using ZSTD_isError())
     */
    public static native long compressUsingDict (byte[] dst, int dstOffset, byte[] src, int srcOffset, int length, byte[] dict, int level);

   /**
     * Compresses direct byte buffer 'src' into direct byte buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to handle worst cases situations (input
     * data not compressible). Worst case size evaluation is provided by function
     * ZSTD_compressBound().
     *
     * @param dst the destination buffer
     * @param dstOffset the start offset of 'dst'
     * @param dstSize size of 'dst'
     * @param src the source buffer
     * @param srcOffset the start offset of 'src'
     * @param srcSize the length of 'src'
     * @param dict the dictionary buffer
     * @param level compression level
     * @return  the number of bytes written into buffer 'dst' or an error code if
     *          it fails (which can be tested using ZSTD_isError())
     */
    public static native long compressDirectByteBufferUsingDict (ByteBuffer dst, int dstOffset, int dstSize, ByteBuffer src, int srcOffset, int srcSize, byte[] dict, int level);

    /**
     * Compresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to handle worst cases situations (input
     * data not compressible). Worst case size evaluation is provided by function
     * ZSTD_compressBound().
     *
     * @param dst the destination buffer
     * @param dstOffset the start offset of 'dst'
     * @param src the source buffer
     * @param srcOffset the start offset of 'src'
     * @param length the length of 'src'
     * @param dict the dictionary
     * @return  the number of bytes written into buffer 'dst' or an error code if
     *          it fails (which can be tested using ZSTD_isError())
     */
    public static native long compressFastDict(byte[] dst, int dstOffset, byte[] src, int srcOffset, int length, ZstdDictCompress dict);

    /**
     * Compresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to handle worst cases situations (input
     * data not compressible). Worst case size evaluation is provided by function
     * ZSTD_compressBound().
     *
     * @param dst the destination buffer
     * @param dstOffset the start offset of 'dst'
     * @param dstSize the size of 'dst'
     * @param src the source buffer
     * @param srcOffset the start offset of 'src'
     * @param srcSize the length of 'src'
     * @param dict the dictionary
     * @return  the number of bytes written into buffer 'dst' or an error code if
     *          it fails (which can be tested using ZSTD_isError())
     */
    public static native long compressDirectByteBufferFastDict(ByteBuffer dst, int dstOffset, int dstSize, ByteBuffer src, int srcOffset, int srcSize, ZstdDictCompress dict);


    /**
     * Decompresses buffer 'src' into buffer 'dst'.
     *
     * Destination buffer should be sized to be larger of equal to the originalSize
     *
     * @param dst the destination buffer
     * @param src the source buffer
     * @return the number of bytes decompressed into destination buffer (originalSize)
     *          or an errorCode if it fails (which can be tested using ZSTD_isError())
     *
     */
    public static native long decompress(byte[] dst, byte[] src);

    /**
     * Decompresses buffer 'src' into buffer 'dst'.
     *
     * Destination buffer should be sized to be larger of equal to the originalSize
     *
     * @param dst the destination buffer
     * @param src the source buffer
     * @return the number of bytes decompressed into destination buffer (originalSize)
     *          or an errorCode if it fails (which can be tested using ZSTD_isError())
     *
     */
    public static native long decompressDirectByteBuffer(ByteBuffer dst, int dstOffset, int dstSize, ByteBuffer src, int srcOffset, int srcSize);

    /**
     * Decompresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to be larger of equal to the originalSize
     *
     * @param dst the destination buffer
     * @param dstOffset the start offset of 'dst'
     * @param src the source buffer
     * @param srcOffset the start offset of 'src'
     * @param length the length of 'src'
     * @param dict the dictionary buffer
     * @return the number of bytes decompressed into destination buffer (originalSize)
     *          or an errorCode if it fails (which can be tested using ZSTD_isError())
     *
     */
    public static native long decompressUsingDict(byte[] dst, int dstOffset, byte[] src, int srcOffset, int length, byte[] dict);

    /**
     * Decompresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to be larger of equal to the originalSize
     *
     * @param dst the destination buffer
     * @param dstOffset the start offset of 'dst'
     * @param dstSize size of 'dst'
     * @param src the source buffer
     * @param srcOffset the start offset of 'src'
     * @param srcSize the  size of 'src'
     * @param dict the dictionary buffer
     * @return the number of bytes decompressed into destination buffer (originalSize)
     *          or an errorCode if it fails (which can be tested using ZSTD_isError())
     *
     */
    public static native long decompressDirectByteBufferUsingDict(ByteBuffer dst, int dstOffset, int dstSize, ByteBuffer src, int srcOffset, int srcSize, byte[] dict);


    /**
     * Decompresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to be larger of equal to the originalSize
     *
     * @param dst the destination buffer
     * @param dstOffset the start offset of 'dst'
     * @param src the source buffer
     * @param srcOffset the start offset of 'src'
     * @param length the length of 'src'
     * @param dict the dictionary
     * @return the number of bytes decompressed into destination buffer (originalSize)
     *          or an errorCode if it fails (which can be tested using ZSTD_isError())
     *
     */
    public static native long decompressFastDict(byte[] dst, int dstOffset, byte[] src, int srcOffset, int length, ZstdDictDecompress dict);

    /**
     * Decompresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to be larger of equal to the originalSize
     *
     * @param dst the destination buffer
     * @param dstOffset the start offset of 'dst'
     * @param dstSize the size of 'dst'
     * @param src the source buffer
     * @param srcOffset the start offset of 'src'
     * @param srcSize the size of 'src'
     * @param dict the dictionary
     * @return the number of bytes decompressed into destination buffer (originalSize)
     *          or an errorCode if it fails (which can be tested using ZSTD_isError())
     *
     */
    public static native long decompressDirectByteBufferFastDict(ByteBuffer dst, int dstOffset, int dstSize, ByteBuffer src, int srcOffset, int srcSize, ZstdDictDecompress dict);


    /* Utility methods */

    /**
     * Return the original size of a compressed buffer (if known)
     *
     * @param src the compressed buffer
     * @return the number of bytes of the original buffer
     *         0 if the original size is now known
     */
    public static native long decompressedSize(byte[] src);


    /**
     * Return the original size of a compressed buffer (if known)
     *
     * @param src the compressed buffer
     * @return the number of bytes of the original buffer
     *         0 if the original size is now known
     *
     *         add by hangl
     *         decompressedSize() always return ZERO!!! fuck it
     */
    public static native long findDecompressedSize(byte[] src);

    /**
     * Return the original size of a compressed buffer (if known)
     *
     * @param src the compressed buffer
     * @return the number of bytes of the original buffer
     *         0 if the original size is now known
     */
    public static native long decompressedDirectByteBufferSize(ByteBuffer src, int srcPosition, int srcSize);

    /**
     * Maximum size of the compressed data
     *
     * @param srcSize the size of the data to be compressed
     * @return the maximum size of the compressed data
     */
    public static native long    compressBound(long srcSize);

    /**
     * Error handling
     *
     * @param code return code/size
     * @return if the return code signals an error
     */

    public static native boolean isError(long code);
    public static native String getErrorName(long code);

    /**
     * Creates a new dictionary to tune a kind of samples
     *
     * @param samples the samples buffer array
     * @param dictBuffer the new dictionary buffer
     * @return the number of bytes into buffer 'dictBuffer' or an error code if
     *          it fails (which can be tested using ZSTD_isError())
     */
    public static native long trainFromBuffer(byte[][] samples, byte[] dictBuffer);

    /* Constants from the zstd_static header */
    public static native int magicNumber();
    public static native int windowLogMin();
    public static native int windowLogMax();
    public static native int chainLogMin();
    public static native int chainLogMax();
    public static native int hashLogMin();
    public static native int hashLogMax();
    public static native int searchLogMin();
    public static native int searchLogMax();
    public static native int searchLengthMin();
    public static native int searchLengthMax();
    public static native int targetLengthMin();
    public static native int targetLengthMax();
    public static native int frameHeaderSizeMin();
    public static native int frameHeaderSizeMax();
    public static native int blockSizeMax();

    /* Convenience methods */

    /**
     * Compresses the data in buffer 'src' using defaul compression level
     *
     * @param src the source buffer
     * @return byte array with the compressed data
     */
    public static byte[] compress(byte[] src) {
        return compress(src, 1);
    }

    /**
     * Compresses the data in buffer 'src'
     *
     * @param src the source buffer
     * @param level compression level
     * @return byte array with the compressed data
     */
    public static byte[] compress(byte[] src, int level) {
        long maxDstSize = compressBound(src.length);
        if (maxDstSize > Integer.MAX_VALUE) {
            throw new RuntimeException("Max output size is greater than MAX_INT");
        }
        byte[] dst = new byte[(int) maxDstSize];
        long size = compress(dst, src, level);
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }
        return Arrays.copyOfRange(dst, 0, (int) size);
    }

    /**
     * Compresses the data in buffer 'srcBuf'
     *
     * @param dstBuf the destination buffer.  must be direct.  It is assumed that the position() of this buffer marks the offset
     *               at which the compressed data are to be written, and that the capacity() of this buffer is the maximum
     *               compressed data size to allow.
     *               <p>
     *               When this method returns successfully, dstBuf's position() will be set to its current position() plus the
     *               compressed size of the data.
     *               </p>
     * @param srcBuf the source buffer.  must be direct.  It is assumed that the position() of this buffer marks the beginning of the
     *               uncompressed data to be compressed, and that the limit() of this buffer marks its end.
     *               <p>
     *               When this method returns successfully, srcBuf's position() will be set to its limit().
     *               </p>
     * @param level compression level
     * @return the size of the compressed data
     */
    public static int compress(ByteBuffer dstBuf, ByteBuffer srcBuf, int level) {
        if (!srcBuf.isDirect()) {
            throw new IllegalArgumentException("srcBuf must be a direct buffer");
        }

        if (!dstBuf.isDirect()) {
            throw new IllegalArgumentException("dstBuf must be a direct buffer");
        }

        long size = compressDirectByteBuffer(dstBuf, //compress into dstBuf
                dstBuf.position(), //write compressed data starting at offset position()
                dstBuf.capacity() - dstBuf.position(), //write no more than capacity() - position() bytes
                srcBuf, //read data to compress from srcBuf
                srcBuf.position(), //start reading at position()
                srcBuf.limit() - srcBuf.position(), //read limit() - position() bytes
                level); //use this compression level
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }
        srcBuf.position(srcBuf.limit());
        dstBuf.position(dstBuf.position() + (int)size);
        return (int)size;
    }

    /**
     * Compresses the data in buffer 'srcBuf'
     *
     * @param srcBuf the source buffer.  must be direct.  It is assumed that the position() of this buffer marks the beginning of the
     *               uncompressed data to be compressed, and that the limit() of this buffer marks its end.
     *               <p>
     *               When this method returns successfully, srcBuf's position() will be set to its limit().
     *               </p>
     * @param level compression level
     * @return A newly allocated direct ByteBuffer containing the compressed data.
     */
    public static ByteBuffer compress(ByteBuffer srcBuf, int level) {
        if (!srcBuf.isDirect()) {
            throw new IllegalArgumentException("srcBuf must be a direct buffer");
        }

        long maxDstSize = Zstd.compressBound((long)(srcBuf.limit() - srcBuf.position()));
        if (maxDstSize > Integer.MAX_VALUE) {
            throw new RuntimeException("Max output size is greater than MAX_INT");
        }

        ByteBuffer dstBuf = ByteBuffer.allocateDirect((int)maxDstSize);

        long size = compressDirectByteBuffer(dstBuf, //compress into dstBuf
                0, //starting at offset 0
                (int)maxDstSize, //writing no more than maxDstSize
                srcBuf, //read data to be compressed from srcBuf
                srcBuf.position(), //start reading at offset position()
                srcBuf.limit() - srcBuf.position(), //read limit() - position() bytes
                level); //use this compression level
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }
        srcBuf.position(srcBuf.limit());

        dstBuf.limit((int)size);
        //Since we allocated the buffer ourselves, we know it cannot be used to hold any further compressed data,
        //so leave the position at zero where the caller surely wants it

        return dstBuf;
    }

    /**
     * Compresses the data in buffer 'src'
     *
     * @param src the source buffer
     * @param dict dictionary to use
     * @return byte array with the compressed data
     */
    public static byte[] compress(byte[] src, ZstdDictCompress dict) {
        long maxDstSize = compressBound(src.length);
        if (maxDstSize > Integer.MAX_VALUE) {
            throw new RuntimeException("Max output size is greater than MAX_INT");
        }
        byte[] dst = new byte[(int) maxDstSize];
        long size = compressFastDict(dst,0,src,0,src.length, dict);
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }
        return Arrays.copyOfRange(dst, 0, (int) size);
    }

   /**
     * Compresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * @deprecated
     * Use compress(dst, src, dict, level) instead
     */
    @Deprecated
    public static long compressUsingDict(byte[] dst, byte[] src, byte[] dict, int level) {
	return compressUsingDict(dst, 0, src, 0, src.length, dict, level);
    }

   /**
     * Compresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to handle worst cases situations (input
     * data not compressible). Worst case size evaluation is provided by function
     * ZSTD_compressBound().
     *
     * @param dst the destination buffer
     * @param src the source buffer
     * @param dict the dictionary buffer
     * @param level compression level
     * @return  the number of bytes written into buffer 'dst' or an error code if
     *          it fails (which can be tested using ZSTD_isError())
     */
    public static long compress(byte[] dst, byte[] src, byte[] dict, int level) {
	return compressUsingDict(dst, 0, src, 0, src.length, dict, level);
    }

   /**
     * Compresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to handle worst cases situations (input
     * data not compressible). Worst case size evaluation is provided by function
     * ZSTD_compressBound().
     *
     * @param dst the destination buffer
     * @param src the source buffer
     * @param dict the dictionary buffer
     * @param level compression level
     * @return  the number of bytes written into buffer 'dst' or an error code if
     *          it fails (which can be tested using ZSTD_isError())
     */
    public static int compress(ByteBuffer dstBuff, ByteBuffer srcBuff, byte[] dict, int level) {
        if (!srcBuff.isDirect()) {
            throw new IllegalArgumentException("srcBuf must be a direct buffer");
        }

        if (!dstBuff.isDirect()) {
            throw new IllegalArgumentException("dstBuf must be a direct buffer");
        }

        long size = compressDirectByteBufferUsingDict(
                dstBuff, //compress into dstBuf
                dstBuff.position(), //starting at offset 0
                dstBuff.capacity() - dstBuff.position(), //write no more than capacity() - position() bytes
                srcBuff, //read data to be compressed from srcBuf
                srcBuff.position(), //start reading at offset position()
                srcBuff.limit() - srcBuff.position(), //read limit() - position() bytes
                dict, // use dictionary
                level); //use this compression level
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }
        srcBuff.position(srcBuff.limit());

        dstBuff.limit((int)size);
        //Since we allocated the buffer ourselves, we know it cannot be used to hold any further compressed data,
        //so leave the position at zero where the caller surely wants it

        return (int) size;
    }

   /**
     * Compresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to handle worst cases situations (input
     * data not compressible). Worst case size evaluation is provided by function
     * ZSTD_compressBound().
     *
     * @param src the source buffer
     * @param dict the dictionary buffer
     * @param level compression level
     * @return  compressed direct byte buffer
     */
    public static ByteBuffer compress(ByteBuffer srcBuff, byte[] dict, int level) {
        if (!srcBuff.isDirect()) {
            throw new IllegalArgumentException("srcBuf must be a direct buffer");
        }

        long maxDstSize = Zstd.compressBound((long)(srcBuff.limit() - srcBuff.position()));
        if (maxDstSize > Integer.MAX_VALUE) {
            throw new RuntimeException("Max output size is greater than MAX_INT");
        }

        ByteBuffer dstBuff = ByteBuffer.allocateDirect((int)maxDstSize);

        long size = compressDirectByteBufferUsingDict(
                dstBuff, //compress into dstBuf
                0, //starting at offset 0
                (int)maxDstSize, //writing no more than maxDstSize
                srcBuff, //read data to be compressed from srcBuf
                srcBuff.position(), //start reading at offset position()
                srcBuff.limit() - srcBuff.position(), //read limit() - position() bytes
                dict, // use dictionary
                level); //use this compression level
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }
        srcBuff.position(srcBuff.limit());

        dstBuff.limit((int)size);
        //Since we allocated the buffer ourselves, we know it cannot be used to hold any further compressed data,
        //so leave the position at zero where the caller surely wants it

        return dstBuff;
    }

    /**
     * Compresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to handle worst cases situations (input
     * data not compressible). Worst case size evaluation is provided by function
     * ZSTD_compressBound().
     *
     * @param dst the destination buffer
     * @param src the source buffer
     * @param dict the dictionary buffer
     * @return  the number of bytes written into buffer 'dst' or an error code if
     *          it fails (which can be tested using ZSTD_isError())
     */
    public static int compress(ByteBuffer dstBuff, ByteBuffer srcBuff, ZstdDictCompress dict) {
        if (!srcBuff.isDirect()) {
            throw new IllegalArgumentException("srcBuf must be a direct buffer");
        }

        if (!dstBuff.isDirect()) {
            throw new IllegalArgumentException("dstBuf must be a direct buffer");
        }

        long size = compressDirectByteBufferFastDict(
                dstBuff, //compress into dstBuf
                dstBuff.position(), //starting at offset 0
                dstBuff.capacity() - dstBuff.position(), //write no more than capacity() - position() bytes
                srcBuff, //read data to be compressed from srcBuf
                srcBuff.position(), //start reading at offset position()
                srcBuff.limit() - srcBuff.position(), //read limit() - position() bytes
                dict // use dictionary
                ); // the compression level is part of the dictionary
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }
        srcBuff.position(srcBuff.limit());

        dstBuff.limit((int)size);
        //Since we allocated the buffer ourselves, we know it cannot be used to hold any further compressed data,
        //so leave the position at zero where the caller surely wants it

        return (int) size;
    }

   /**
     * Compresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to handle worst cases situations (input
     * data not compressible). Worst case size evaluation is provided by function
     * ZSTD_compressBound().
     *
     * @param src the source buffer
     * @param dict the dictionary buffer
     * @return  compressed direct byte buffer
     */
    public static ByteBuffer compress(ByteBuffer srcBuff, ZstdDictCompress dict) {
        if (!srcBuff.isDirect()) {
            throw new IllegalArgumentException("srcBuf must be a direct buffer");
        }

        long maxDstSize = Zstd.compressBound((long)(srcBuff.limit() - srcBuff.position()));
        if (maxDstSize > Integer.MAX_VALUE) {
            throw new RuntimeException("Max output size is greater than MAX_INT");
        }

        ByteBuffer dstBuff = ByteBuffer.allocateDirect((int)maxDstSize);

        long size = compressDirectByteBufferFastDict(
                dstBuff, //compress into dstBuf
                0, //starting at offset 0
                (int)maxDstSize, //writing no more than maxDstSize
                srcBuff, //read data to be compressed from srcBuf
                srcBuff.position(), //start reading at offset position()
                srcBuff.limit() - srcBuff.position(), //read limit() - position() bytes
                dict // use dictionary
                ); // the compression level is part of the dictionary
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }
        srcBuff.position(srcBuff.limit());

        dstBuff.limit((int)size);
        //Since we allocated the buffer ourselves, we know it cannot be used to hold any further compressed data,
        //so leave the position at zero where the caller surely wants it

        return dstBuff;
    }

    /**
     * Decompress data
     *
     * @param src the source buffer
     * @param originalSize the maximum size of the uncompressed data
     * @return byte array with the decompressed data
     */
    public static byte[] decompress(byte[] src, int originalSize) {
        byte[] dst = new byte[originalSize];
        long size = decompress(dst, src);
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }
        if (size != originalSize) {
            return Arrays.copyOfRange(dst, 0, (int) size);
        } else {
            return dst;
        }
    }

    /**
     * Decompress data
     *
     * @param dstBuf the destination buffer.  must be direct.  It is assumed that the position() of this buffer marks the offset
     *               at which the decompressed data are to be written, and that the capacity() of this buffer is the maximum
     *               decompressed data size to allow.
     *               <p>
     *               When this method returns successfully, dstBuf's position() will be set to its current position() plus the
     *               decompressed size of the data.
     *               </p>
     * @param srcBuf the source buffer.  must be direct.  It is assumed that the position() of this buffer marks the beginning of the
     *               compressed data to be decompressed, and that the limit() of this buffer marks its end.
     *               <p>
     *               When this method returns successfully, srcBuf's position() will be set to its limit().
     *               </p>
     * @return the size of the decompressed data.
     */
    public static int decompress(ByteBuffer dstBuf, ByteBuffer srcBuf) {
        if (!srcBuf.isDirect()) {
            throw new IllegalArgumentException("srcBuf must be a direct buffer");
        }

        if (!dstBuf.isDirect()) {
            throw new IllegalArgumentException("dstBuf must be a direct buffer");
        }

        long size = decompressDirectByteBuffer(dstBuf, //decompress into dstBuf
                dstBuf.position(), //write decompressed data at offset position()
                dstBuf.capacity() - dstBuf.position(), //write no more than capacity() - position()
                srcBuf, //read compressed data from srcBuf
                srcBuf.position(), //read starting at offset position()
                srcBuf.limit() - srcBuf.position()); //read no more than limit() - position()
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }

        srcBuf.position(srcBuf.limit());
        dstBuf.position(dstBuf.position() + (int)size);
        return (int)size;
    }

    /**
     * Decompress data
     *
     * @param srcBuf the source buffer.  must be direct.  It is assumed that the position() of this buffer marks the beginning of the
     *               compressed data to be decompressed, and that the limit() of this buffer marks its end.
     *               <p>
     *               When this method returns successfully, srcBuf's position() will be set to its limit().
     *               </p>
     * @param originalSize the maximum size of the uncompressed data
     * @return A newly-allocated ByteBuffer containing the decompressed data.  The position() of this buffer will be 0,
     *          and the limit() will be the size of the decompressed data.  In other words the buffer is ready to be used for
     *          reading.  Note that this is different behavior from the other decompress() overload which takes as a parameter
     *          the destination ByteBuffer.
     */
    public static ByteBuffer decompress(ByteBuffer srcBuf, int originalSize) {
        if (!srcBuf.isDirect()) {
            throw new IllegalArgumentException("srcBuf must be a direct buffer");
        }

        ByteBuffer dstBuf = ByteBuffer.allocateDirect(originalSize);
        long size = decompressDirectByteBuffer(dstBuf, 0, originalSize, srcBuf, srcBuf.position(), srcBuf.limit());
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }

        srcBuf.position(srcBuf.limit());
        //Since we allocated the buffer ourselves, we know it cannot be used to hold any further decompressed data,
        //so leave the position at zero where the caller surely wants it
        //dstBuf.position(dstBuf.position() + (int)size);
        return dstBuf;
    }

    /**
     * Decompress data
     *
     * @param src the source buffer
     * @param dict dictionary to use
     * @param originalSize the maximum size of the uncompressed data
     * @return byte array with the decompressed data
     */
    public static byte[] decompress(byte[] src, ZstdDictDecompress dict, int originalSize) {
        byte[] dst = new byte[originalSize];
        long size = decompressFastDict(dst, 0, src, 0, src.length, dict);
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }
        if (size != originalSize) {
            return Arrays.copyOfRange(dst, 0, (int) size);
        } else {
            return dst;
        }
    }

    /**
     * Decompresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * @deprecated
     * Use decompress(dst, src, dict) instead
     */
    @Deprecated
    public static long decompressUsingDict(byte[] dst, byte[] src, byte[] dict) {
	return decompressUsingDict(dst, 0, src, 0, src.length, dict);
    }

    /**
     * Decompresses buffer 'src' into buffer 'dst' with dictionary.
     *
     * Destination buffer should be sized to be larger of equal to the originalSize
     *
     * @param dst the destination buffer
     * @param src the source buffer
     * @param dict the dictionary buffer
     * @return the number of bytes decompressed into destination buffer (originalSize)
     *          or an errorCode if it fails (which can be tested using ZSTD_isError())
     */
    public static long decompress(byte[] dst, byte[] src, byte[] dict) {
	return decompressUsingDict(dst, 0, src, 0, src.length, dict);
    }

    /**
     * Return the original size of a compressed buffer (if known)
     *
     * @param srcBuf the compressed buffer.  must be direct.  It is assumed that the position() of this buffer marks the beginning of the
     *               compressed data whose decompressed size is being queried, and that the limit() of this buffer marks its
     *               end.
     * @return the number of bytes of the original buffer
     *         0 if the original size is not known
     */
    public static long decompressedSize(ByteBuffer srcBuf) {
        return decompressedDirectByteBufferSize(srcBuf, srcBuf.position(), srcBuf.limit() - srcBuf.position());
    }

    /**
     * Decompress data
     *
     * @param dstBuff the destination buffer.  must be direct.  It is assumed that the position() of this buffer marks the offset
     *               at which the decompressed data are to be written, and that the capacity() of this buffer is the maximum
     *               decompressed data size to allow.
     *               <p>
     *               When this method returns successfully, dstBuff's position() will be set to its current position() plus the
     *               decompressed size of the data.
     *               </p>
     * @param srcBuff the source buffer.  must be direct.  It is assumed that the position() of this buffer marks the beginning of the
     *               compressed data to be decompressed, and that the limit() of this buffer marks its end.
     *               <p>
     *               When this method returns successfully, srcBuff's position() will be set to its limit().
     *               </p>
     * @param dict   the dictionary buffer to use for compression
     * @return the size of the decompressed data.
     */
    public static int decompress(ByteBuffer dstBuff, ByteBuffer srcBuff, byte[] dict) {
        if (!srcBuff.isDirect()) {
            throw new IllegalArgumentException("srcBuff must be a direct buffer");
        }

        if (!dstBuff.isDirect()) {
            throw new IllegalArgumentException("dstBuff must be a direct buffer");
        }

        long size = decompressDirectByteBufferUsingDict(dstBuff, //decompress into dstBuf
                dstBuff.position(), //write decompressed data at offset position()
                dstBuff.capacity() - dstBuff.position(), //write no more than capacity() - position()
                srcBuff, //read compressed data from srcBuf
                srcBuff.position(), //read starting at offset position()
                srcBuff.limit() - srcBuff.position(), //read no more than limit() - position()
                dict);
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }

        srcBuff.position(srcBuff.limit());
        dstBuff.position(dstBuff.position() + (int)size);
        return (int) size;
    }

    /**
     * Decompress data
     *
     * @param srcBuff the source buffer.  must be direct.  It is assumed that the position() of this buffer marks the beginning of the
     *               compressed data to be decompressed, and that the limit() of this buffer marks its end.
     *               <p>
     *               When this method returns successfully, srcBuff's position() will be set to its limit().
     *               </p>
     * @param originalSize the maximum size of the uncompressed data
     * @param dict   the dictionary used in the compression
     * @return A newly-allocated ByteBuffer containing the decompressed data.  The position() of this buffer will be 0,
     *          and the limit() will be the size of the decompressed data.  In other words the buffer is ready to be used for
     *          reading.  Note that this is different behavior from the other decompress() overload which takes as a parameter
     *          the destination ByteBuffer.
     */
    public static ByteBuffer decompress(ByteBuffer srcBuff, int originalSize, byte[] dict) {
        if (!srcBuff.isDirect()) {
            throw new IllegalArgumentException("srcBuff must be a direct buffer");
        }

        ByteBuffer dstBuff = ByteBuffer.allocateDirect(originalSize);
        long size = decompressDirectByteBufferUsingDict(dstBuff, 0, originalSize, srcBuff, srcBuff.position(), srcBuff.limit(), dict);
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }

        srcBuff.position(srcBuff.limit());
        //Since we allocated the buffer ourselves, we know it cannot be used to hold any further decompressed data,
        //so leave the position at zero where the caller surely wants it
        return dstBuff;
    }

    /**
     * Decompress data
     *
     * @param dstBuff the destination buffer.  must be direct.  It is assumed that the position() of this buffer marks the offset
     *               at which the decompressed data are to be written, and that the capacity() of this buffer is the maximum
     *               decompressed data size to allow.
     *               <p>
     *               When this method returns successfully, dstBuff's position() will be set to its current position() plus the
     *               decompressed size of the data.
     *               </p>
     * @param srcBuff the source buffer.  must be direct.  It is assumed that the position() of this buffer marks the beginning of the
     *               compressed data to be decompressed, and that the limit() of this buffer marks its end.
     *               <p>
     *               When this method returns successfully, srcBuff's position() will be set to its limit().
     *               </p>
     * @param dict   the dictionary buffer to use for compression
     * @return the size of the decompressed data.
     */
    public static int decompress(ByteBuffer dstBuff, ByteBuffer srcBuff, ZstdDictDecompress dict) {
        if (!srcBuff.isDirect()) {
            throw new IllegalArgumentException("srcBuff must be a direct buffer");
        }

        if (!dstBuff.isDirect()) {
            throw new IllegalArgumentException("dstBuff must be a direct buffer");
        }

        long size = decompressDirectByteBufferFastDict(dstBuff, //decompress into dstBuf
                dstBuff.position(), //write decompressed data at offset position()
                dstBuff.capacity() - dstBuff.position(), //write no more than capacity() - position()
                srcBuff, //read compressed data from srcBuf
                srcBuff.position(), //read starting at offset position()
                srcBuff.limit() - srcBuff.position(), //read no more than limit() - position()
                dict);
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }

        srcBuff.position(srcBuff.limit());
        dstBuff.position(dstBuff.position() + (int)size);
        return (int) size;
    }

    /**
     * Decompress data
     *
     * @param srcBuff the source buffer.  must be direct.  It is assumed that the position() of this buffer marks the beginning of the
     *               compressed data to be decompressed, and that the limit() of this buffer marks its end.
     *               <p>
     *               When this method returns successfully, srcBuff's position() will be set to its limit().
     *               </p>
     * @param originalSize the maximum size of the uncompressed data
     * @param dict   the dictionary used in the compression
     * @return A newly-allocated ByteBuffer containing the decompressed data.  The position() of this buffer will be 0,
     *          and the limit() will be the size of the decompressed data.  In other words the buffer is ready to be used for
     *          reading.  Note that this is different behavior from the other decompress() overload which takes as a parameter
     *          the destination ByteBuffer.
     */
    public static ByteBuffer decompress(ByteBuffer srcBuff, int originalSize, ZstdDictDecompress dict) {
        if (!srcBuff.isDirect()) {
            throw new IllegalArgumentException("srcBuff must be a direct buffer");
        }

        ByteBuffer dstBuff = ByteBuffer.allocateDirect(originalSize);
        long size = decompressDirectByteBufferFastDict(dstBuff, 0, originalSize, srcBuff, srcBuff.position(), srcBuff.limit(), dict);
        if (isError(size)) {
            throw new RuntimeException(getErrorName(size));
        }

        srcBuff.position(srcBuff.limit());
        //Since we allocated the buffer ourselves, we know it cannot be used to hold any further decompressed data,
        //so leave the position at zero where the caller surely wants it
        return dstBuff;
    }

}
