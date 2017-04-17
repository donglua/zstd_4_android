package com.zstd.hangl.zstdtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.com.github.luben.zstd.hangl.zstdtest.R;
import com.github.luben.zstd.Zstd;
import com.zstd.hangl.zstdtest.mathutil.MathUtil;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private String tag = "hangl_debug";
    TextView testTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testTv = (TextView) findViewById(R.id.hello_word_tv);
        testTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == R.id.hello_word_tv) {
            String testJson = "{\"name\":\"line_location\", \"location\":\"1231231231231231231231231231231323123123\"}";
            byte[] encoded = Zstd.compress(testJson.getBytes());

            //int bound = Zstd.

            Log.d(tag, "in onClick encoded result is " + encoded);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int result = MathUtil.add(11, 22);
        Log.d("hangl_debug", "add result is " + result);

        result = MathUtil.reduce(11, 22);
        Log.d("hangl_debug", "reduce result is " + result);
    }
}
