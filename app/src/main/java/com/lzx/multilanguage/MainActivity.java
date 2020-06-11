package com.lzx.multilanguage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.common.CommonMainActivity;
import com.baidu.tool.ToolsMainActivity;
import com.finddreams.base.BaseActivity;
import com.finddreams.languagelib.OnChangeLanguageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {

    Button toolBtn, commonBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        EventBus.getDefault().register(this);

        toolBtn = findViewById(R.id.btn_openTool);
        commonBtn = findViewById(R.id.btn_openCommon);

        toolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ToolsMainActivity.class));
            }
        });
        commonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CommonMainActivity.class));

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, getString(R.string.service_create), Toast.LENGTH_SHORT).show();
    }

    public void openLandScape(View view) {
        startActivity(new Intent(this, LandScapeActivity.class));

    }

    public void openWebView(View view) {
        startActivity(new Intent(this, WebViewActivity.class));
    }

    public void openSettingLanguage(View view) {
        startActivity(new Intent(this, SetLanguageActivity.class));
    }

    public void openOther(View view) {
        startActivity(new Intent(this, OtherActivity.class));
    }

    public void openTool(View view) {
        startActivity(new Intent(this, ToolsMainActivity.class));
    }

    public void openCommon(View view) {
        startActivity(new Intent(this, CommonMainActivity.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeLanguageEvent(OnChangeLanguageEvent event) {
        Log.d("onchange", "ChangeLanguage");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
