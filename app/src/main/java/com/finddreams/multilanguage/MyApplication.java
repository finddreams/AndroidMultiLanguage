package com.finddreams.multilanguage;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import com.finddreams.languagelib.MultiLanguageUtil;

/**
 * Created by lx on 17-10-26.
 */

public class MyApplication extends Application{

    private Application mApplication;


    @Override
    protected void attachBaseContext(Context base) {
        MultiLanguageUtil.saveSystemCurrentLanguage(base);
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(base));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MultiLanguageUtil.getInstance(this).setConfiguration();
    }


}
