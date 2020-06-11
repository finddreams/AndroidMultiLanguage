package com.baidu.common;

import android.os.Bundle;
import android.widget.Toast;

import com.didichuxing.common.R;
import com.finddreams.base.BaseActivity;

public class CommonMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_common);
        Toast.makeText(this, getString(R.string.common), Toast.LENGTH_SHORT).show();
    }
}