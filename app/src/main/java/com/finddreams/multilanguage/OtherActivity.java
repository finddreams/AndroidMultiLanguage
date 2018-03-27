package com.finddreams.multilanguage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 测试语言是否被重置
 */
public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        setTitle(R.string.app_name);

    }
}
