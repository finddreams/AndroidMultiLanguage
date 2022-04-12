package com.finddreams.base;

import android.content.Context;
import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.finddreams.languagelib.MultiLanguageUtil;

public class BaseActivity extends AppCompatActivity {

    protected String TAG = getClass().getSimpleName();

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.e(TAG, "attachBaseContext");
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
        //app杀进程启动后会调用Activity attachBaseContext
        MultiLanguageUtil.getInstance().setConfiguration(newBase);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.e(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }
}
