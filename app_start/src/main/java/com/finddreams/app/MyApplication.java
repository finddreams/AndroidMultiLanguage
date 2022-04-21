package com.finddreams.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.finddreams.languagelib.MultiLanguageUtil;

/**
 * Created by lx on 17-10-26.
 */

public class MyApplication extends Application{

    private Application mApplication;
    protected String TAG = getClass().getSimpleName();


    @Override
    protected void attachBaseContext(Context base) {
        Log.e(TAG, "attachBaseContext");
        MultiLanguageUtil.getInstance().saveSystemCurrentLanguage(base);
        super.attachBaseContext(base);
        //app刚启动getApplicationContext()为空
        MultiLanguageUtil.getInstance().setConfiguration(getApplicationContext());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //app刚启动不一定调用onConfigurationChanged
        Log.e(TAG, "onConfigurationChanged");
        MultiLanguageUtil.getInstance().setConfiguration(getApplicationContext());
    }


}
