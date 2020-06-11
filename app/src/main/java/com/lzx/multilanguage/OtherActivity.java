package com.lzx.multilanguage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.finddreams.base.BaseActivity;
import com.finddreams.multilanguage.R;

/**
 * 测试语言是否被重置
 */
public class OtherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        setTitle(R.string.app_name);
        Toast.makeText(this, getString(R.string.test), Toast.LENGTH_SHORT).show();

    }
}
