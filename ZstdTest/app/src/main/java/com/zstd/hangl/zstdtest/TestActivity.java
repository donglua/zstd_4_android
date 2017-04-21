package com.zstd.hangl.zstdtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.com.github.luben.zstd.hangl.zstdtest.R;
import com.github.luben.zstd.Zstd;
import com.github.luben.zstd.ZstdDictCompress;
import com.github.luben.zstd.ZstdDictDecompress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

import static com.github.luben.zstd.Zstd.compress;
import static com.github.luben.zstd.Zstd.compressBound;
import static com.github.luben.zstd.Zstd.decompress;
import static com.github.luben.zstd.Zstd.decompressedSize;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private String tag = "hangl_debug";
    TextView testTv;
    TextView compressTv;

    String testJson = "{\"name\":\"line_location\", \"location\":\"116.419233,39.802936;116.420043,39.803031;116.420236,39.803006;116.420639,39.803071;116.421035,39.803146;116.42103726086,39.803146347213;116.421888,39.803277;116.422328,39.80336;116.422672,39.80341;\"}";
    String configQuery = "{\n" +
            "  \"config\": {\n" +
            "    \"feedback\": {\n" +
            "      \"enable\": 1\n" +
            "    },\n" +
            "    \"operation\": {\n" +
            "      \"horn_url\": \"http://static.galileo.xiaojukeji.com/static/tms/galileo/z/homeoperation/index.html\"\n" +
            "    },\n" +
            "    \"share\": {\n" +
            "      \"line\": {\n" +
            "        \"title\": \"等车不再焦躁，上班不怕迟到，生活可以更美好\",\n" +
            "        \"ntitle\": \"不再慌张赶公交，每天节省10分钟，吃个早餐刚刚好\",\n" +
            "        \"content\": \"滴滴公交，随时知道车在哪\",\n" +
            "        \"url\": \"https://gongjiao.xiaojukeji.com/realtime\",\n" +
            "        \"icon\": \"http://static.galileo.xiaojukeji.com/static/tms/galileo/busshare_link_icon.PNG\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"remind\": {\n" +
            "      \"speed_up\": 180,\n" +
            "      \"warn_time\": 60,\n" +
            "      \"slow_loop\": 30,\n" +
            "      \"fast_loop\": 5,\n" +
            "      \"enable\": 1\n" +
            "    },\n" +
            "    \"omega_id\": \"d23dc7d71e7d122951d1b9f04890f8e1\"\n" +
            "  },\n" +
            "  \"server_time\": 1492605625,\n" +
            "  \"display_error\": \"\",\n" +
            "  \"errno\": 0,\n" +
            "  \"errmsg\": \"\"\n" +
            "}";


    String testData = "{\"config\":{\"feedback\":{\"enable\":1},\"smooth\":{\"icon\":\"\",\"icon_passed\":\"\",\"polling_interval\":6,\"bubble_img\":\"\",\"bubble_url\":\"\"},\"operation\":{\"horn_url\":\"http:\\/\\/static.galileo.xiaojukeji.com\\/static\\/tms\\/galileo\\/z\\/homeoperation\\/index.html\"},\"share\":{\"line\":{\"title\":\"\\u4e0d\\u518d\\u614c\\u5f20\\u8d76\\u516c\\u4ea4\\uff0c\\u6bcf\\u5929\\u8282\\u770110\\u5206\\u949f\\uff0c\\u5403\\u4e2a\\u65e9\\u9910\\u521a\\u521a\\u597d\",\"ntitle\":\"\\u7b49\\u8f66\\u4e0d\\u518d\\u7126\\u8e81\\uff0c\\u4e0a\\u73ed\\u4e0d\\u6015\\u8fdf\\u5230\\uff0c\\u751f\\u6d3b\\u53ef\\u4ee5\\u66f4\\u7f8e\\u597d\",\"content\":\"\\u6ef4\\u6ef4\\u516c\\u4ea4\\uff0c\\u968f\\u65f6\\u77e5\\u9053\\u8f66\\u5728\\u54ea\",\"url\":\"https:\\/\\/gongjiao.xiaojukeji.com\\/realtime\",\"icon\":\"http:\\/\\/static.galileo.xiaojukeji.com\\/static\\/tms\\/galileo\\/busshare_link_icon.PNG\"}},\"omega_id\":\"d5c52e911e1605f1bb3e1c8d6b261bfb\"},\"server_time\":1492609031,\"display_error\":\"\",\"errno\":0,\"errmsg\":\"\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testTv = (TextView) findViewById(R.id.hello_word_tv);
        testTv.setOnClickListener(this);

        compressTv = (TextView) findViewById(R.id.compress_configquery);
        compressTv.setOnClickListener(this);

        findViewById(R.id.compress_configquery_comparewithserver).setOnClickListener(this);
        findViewById(R.id.compress_configquery_withdict).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.compress_configquery) {

            //First test:
            byte[] srcBytes = configQuery.getBytes();
            byte[] compressedBytes = Zstd.compress(srcBytes);
            Log.d(tag, "compressedBytes = " + new String(compressedBytes));

            int srcSize = configQuery.length();
            int deSize = (int)Zstd.findDecompressedSize(srcBytes);
            Log.d(tag, "deSize = " + deSize);
            byte[] decompressBytes = new byte[deSize];
            Zstd.decompress(decompressBytes, compressedBytes);
            Log.d(tag, "decompressBytes = " + new String(decompressBytes));


            Log.d(tag, "compress data with dict=======================");
            InputStream is = null;
            try {
                is = getAssets().open("2.dict");
                byte[] dictStr = readBytes3(is);
                Log.d(tag, new String(dictStr));
                byte[] dictCompressedData = Zstd.compress(srcBytes, new ZstdDictCompress(dictStr, 1));
                Log.d(tag, "dict compressed == " + new String(dictCompressedData));

            } catch (IOException e) {
                e.printStackTrace();
            }




        } else if (vId == R.id.hello_word_tv) {



            try {
                InputStream is = getAssets().open("2.dict");
                byte[] dictStr = readBytes3(is);
                Log.d(tag, new String(dictStr));
                byte[] dictCompressed = Zstd.compress(dictStr);
                Log.d(tag, "dict compressed == " + new String(dictCompressed));

                long oSize = decompressedSize(dictCompressed);
                long oFindSize = Zstd.findDecompressedSize(dictCompressed);
                byte[] sourceDict = Zstd.decompress(dictCompressed, (int)oSize);
                Log.d(tag, "original size = " + oSize + " &&&& oFindSize = " + oFindSize);
                Log.d(tag, "original dict data == " + new String(sourceDict) );


                //try to compress data with dict
                long compressedSize = compressBound(testJson.getBytes().length);
                byte[] dataCompressedWithDict = new byte[(int)compressedSize];
                Zstd.compress(dataCompressedWithDict, testJson.getBytes(), sourceDict, 1);
                Log.d(tag, ">>>>>>>>after compress with dict return " + new String(dataCompressedWithDict));


                int decompressSize = (int) decompressedSize(dataCompressedWithDict);
                Log.d(tag, "decompressSize = " + decompressSize);
                byte[] decompressJson = new byte[decompressSize];
                long decompressJsonSize = Zstd.decompress(decompressJson, dataCompressedWithDict, sourceDict);
                Log.d(tag, "decompressJsonSize = " + decompressJsonSize + " decompress json = " + new String(decompressJson));
                //Zstd.decompress






            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] encoded = compress(testJson.getBytes());

            Log.d(tag, "in onClick encoded result is " + encoded);

            byte[] dst = new byte[1000];
            long result = decompress(dst, encoded);
            Log.d(tag, "11111decompress data return  " + new String(dst) + " decompress length = " + result);



            dst = new byte[testJson.length()];
            result = decompress(dst, encoded);
            Log.d(tag, "22222second decompress data return  " + new String(dst) + " decompress length = " + result);

            long decompressSize = decompressedSize(encoded);
            Log.d(tag, "333333decompressSize = " + decompressSize + " original size = "+ testJson.length());

            byte[] test3 = Zstd.decompress(encoded, (int)decompressSize);
            String resultStr = new String(test3);
            Log.d(tag, "333333third decompress data return " + resultStr);
        } else if(vId == R.id.compress_configquery_comparewithserver) {
            try {
                InputStream iiss = getAssets().open("compress.data");
                //ByteArrayInputStream bin = new ByteArrayInputStream(iiss);
                byte[] bytesFromYL = readBytes3(iiss);
                Log.d(tag, "bytes length = " + bytesFromYL.length);

                int decompressed_size = (int) Zstd.decompressedSize(bytesFromYL);
                int decompressed_size_find = (int) Zstd.findDecompressedSize(bytesFromYL);
                Log.d(tag, "bytes length = " + bytesFromYL.length + " decompressed_size = " + decompressed_size
                    + " decompressed_size_find = " + decompressed_size_find);

                if (decompressed_size <= 0) {
                    decompressed_size = 128 * 1024;
                }
                Log.d(tag, "decompressed_size = " + decompressed_size);



                byte[] decompressFromYl = Zstd.decompress(bytesFromYL, decompressed_size);
                Log.d(tag, "decompressFromYl == " + new String(decompressFromYl));










//                if (decompressFromYl.length > 0) {
//                    return;
//                }












                //String compressedTest = inputStream2String(iiss);

                InputStream iiss2 = getAssets().open("compress2.data");
                byte[] compressedTest2 = readBytes3(iiss2);

                //Log.d(tag, "compressedTest == " + compressedTest + "\n compressedTest2 = " + compressedTest2);

                //Log.d(tag, "compressTest bytes[] " + compressedTest.getBytes().length + " compresssTest2 bytes[] " + compressedTest2.getBytes().length);

                int boundSize = (int) Zstd.compressBound(testData.getBytes().length);
                byte[] javaResult = Zstd.compress(testData.getBytes());
                Log.d(tag, "compress in java will return " + new String(javaResult) + " javaResult length = " + javaResult.length
                    + " boundSize = " + boundSize);

                byte[] javaResult2 = new byte[boundSize];
                Zstd.compress(javaResult2, testData.getBytes(), 1);
                Log.d(tag, "compress with level 1 return " + new String(javaResult2));


//                int cSize = (int) Zstd.decompressedSize(compressedTest.getBytes());
//                bytes
                int cSize = (int) Zstd.decompressedSize(bytesFromYL);
                int cSize2 = (int) Zstd.decompressedSize(compressedTest2);
                int cSize3 = (int) Zstd.decompressedSize(javaResult);
                Log.d(tag, "cSize = " + cSize + "Size2 = " + cSize2 + " cSize3 = " + cSize3);
                Log.d(tag, "testData.getBytes() length = " + testData.getBytes().length + " and testData size = " + testData.length());

                if (cSize <= 0 ) {
                    cSize = testData.length();
                }
                Log.d(tag, "cSize will set to = " + cSize);

//                byte[] deBytes = Zstd.decompress(compressedTest.getBytes(), cSize);
//                Log.d(tag, "after decompress data == " + new String(deBytes));

                byte[] deBytes = Zstd.decompress(javaResult, cSize3);

                Log.d(tag, "22after decompress java data == " + new String(deBytes));

                byte[] deBytesTry = Zstd.decompress(bytesFromYL, 10000);
                Log.d(tag, "after decompress data == " + new String(deBytesTry));


//                byte[] dictCompressedData = Zstd.compress(srcBytes, new ZstdDictCompress(dictStr.getBytes(), 1));
//                Log.d(tag, "dict compressed == " + new String(dictCompressedData));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (vId == R.id.compress_configquery_withdict) {

            byte[] srcBytes = testData.getBytes();
            Log.d(tag, "src bytes length is ====== " + srcBytes.length);
            InputStream is = null;
            try {
                is = getAssets().open("2.dict");
                byte[] dictBytes = readBytes3(is);
                String dict = new String(dictBytes);
                Log.d(tag, "dictBytes length = "+dictBytes.length+"dict content is " + dict);
                String md5 = MD5(dict);
                Log.d(tag, "dict md5 == " + md5);

                byte[] dictCompressedData = Zstd.compress(srcBytes, new ZstdDictCompress(dictBytes, 1));
                Log.d(tag, "compress string with dict return == " + new String(dictCompressedData));

                int originalSize = (int) Zstd.decompressedSize(dictCompressedData);
                Log.d(tag, "originalSize(decompressedSize) = " + originalSize);

                ZstdDictDecompress dictDecompress = new ZstdDictDecompress(dictBytes);
                byte[] json = Zstd.decompress(dictCompressedData, dictDecompress, originalSize);
                Log.d(tag, "decompress with dict return json " + new String(json));


                int  testSize = 128 * 1024;
                Log.d(tag, "---------------------------another try with large original size ("+ 128 * 1024+")---------------------------");
                json = Zstd.decompress(dictCompressedData, dictDecompress, testSize);
                Log.d(tag, "decompress with dict return json " + new String(json));


                //========================
                InputStream interfaceStream = getAssets().open("interface_dict_compress.data");
                byte[] testByteYl = readBytes3(interfaceStream);
                Log.d(tag, "testByteYl length = " + testByteYl.length);

                int ooSize = (int) Zstd.decompressedSize(testByteYl);
                int ooSizeFind = (int) Zstd.findDecompressedSize(testByteYl);
                Log.d(tag, "ooSize = " + ooSize + " ooSizeFind = " + ooSizeFind);
                int size = ooSizeFind > 0 ? ooSizeFind : (ooSize > 0 ? ooSize : 128 * 1024);
                Log.d(tag, "size = " + size);
                byte[] decompressBytes = Zstd.decompress(testByteYl, dictDecompress, ooSizeFind);
                Log.d(tag, "after decompress get result " + new String(decompressBytes));


            } catch (IOException e) {
                e.printStackTrace();
            }








        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        //        int result = MathUtil.add(11, 22);
        //        Log.d("hangl_debug", "add result is " + result);
        //
        //        result = MathUtil.reduce(11, 22);
        //        Log.d("hangl_debug", "reduce result is " + result);

        int length = configQuery.length();
        Log.d("hangl_debug", "in onResume() length == " + length);
    }


//    public static String inputStream2String(InputStream in_st) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(in_st));
//        StringBuffer buffer = new StringBuffer();
//        String line = "";
//        while ((line = in.readLine()) != null){
//            buffer.append(line);
//        }
//        return buffer.toString();
//    }

    public static byte[] readBytes3(InputStream in) throws IOException {
//        BufferedInputStream bufin = new BufferedInputStream(in);
//        int buffSize = 1024;
//        ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);
//
//        byte[] temp = new byte[buffSize];
//        int size = 0;
//        while ((size = bufin.read(temp)) != -1) {
//            out.write(temp, 0, size);
//        }
//        bufin.close();
//
//        byte[] content = out.toByteArray();
//        return content;
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = in.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;

    }


    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public final static String caculateMd5(String pwd) {
        //用于加密的字符
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = pwd.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            //返回经过加密后的字符串
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }



}
