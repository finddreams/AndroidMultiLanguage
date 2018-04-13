package com.finddreams.multilanguage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.finddreams.languagelib.MultiLanguageUtil;
import com.finddreams.languagelib.OnChangeLanguageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MultiLanguageUtil.getInstance().setConfiguration();
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        EventBus.getDefault().register(this);
    }
    public void openLandScape(View view){
        startActivity(new Intent(this,LandScapeActivity.class));

    }
    public void openWebView(View view){
        startActivity(new Intent(this,WebViewActivity.class));
    }
    public void openSettingLanguage(View view){
        startActivity(new Intent(this,SetLanguageActivity.class));
    }
    public void openOther(View view){
        startActivity(new Intent(this,OtherActivity.class));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeLanguageEvent(OnChangeLanguageEvent event){
        Log.d("onchange","ChangeLanguage");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
