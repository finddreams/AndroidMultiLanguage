package com.finddreams.multilanguage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.finddreams.languagelib.OnChangeLanguageEvent;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

public class MainActivity extends BaseActivity {

    private Button btn_openwebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_openwebview = findViewById(R.id.btn_openwebview);
        EventBus.getDefault().register(this);
        TinkerPatch.with().fetchDynamicConfig(new ConfigRequestCallback() {
            @Override
            public void onSuccess(HashMap<String, String> hashMap) {
                Log.d("tag",hashMap.toString());
            }
            @Override
            public void onFail(Exception e) {
            }
        }, false);
    }
    public void openLandScape(View view){
        startActivity(new Intent(this,LandScapeActivity.class));

    }
    public void openWebView(View view){
//        int i=5/0;
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
        btn_openwebview.setText("ChangeLanguage");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
