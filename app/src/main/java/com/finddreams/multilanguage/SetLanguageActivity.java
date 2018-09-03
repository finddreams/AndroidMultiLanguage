package com.finddreams.multilanguage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.finddreams.languagelib.LanguageType;
import com.finddreams.languagelib.MultiLanguageUtil;

/**
 * 设置语言页面
 */
public class SetLanguageActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rl_followsytem;
    private RelativeLayout rl_simplified_chinese;
    private RelativeLayout rl_traditional_chinese;
    private RelativeLayout rl_english;
    private ImageView iv_english;
    private ImageView iv_followsystem;
    private ImageView iv_simplified_chinese;
    private ImageView iv_traditional_chinese;
    private int savedLanguageType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_language);
        setTitle(R.string.setting_language_title);
        initViews();
    }

    private void initViews() {
        rl_followsytem = findViewById(R.id.rl_followsytem);
        rl_simplified_chinese = findViewById(R.id.rl_simplified_chinese);
        rl_traditional_chinese = findViewById(R.id.rl_traditional_chinese);
        rl_english = findViewById(R.id.rl_english);
        iv_followsystem = findViewById(R.id.iv_followsystem);
        iv_english = findViewById(R.id.iv_english);
        iv_simplified_chinese = findViewById(R.id.iv_simplified_chinese);
        iv_traditional_chinese = findViewById(R.id.iv_traditional_chinese);
        rl_followsytem.setOnClickListener(this);
        rl_simplified_chinese.setOnClickListener(this);
        rl_traditional_chinese.setOnClickListener(this);
        rl_english.setOnClickListener(this);
        savedLanguageType = MultiLanguageUtil.getInstance().getLanguageType();
        if (savedLanguageType == LanguageType.LANGUAGE_FOLLOW_SYSTEM) {
            setFollowSytemVisible();
        } else if (savedLanguageType == LanguageType.LANGUAGE_CHINESE_TRADITIONAL) {
            setTraditionalVisible();
        } else if (savedLanguageType == LanguageType.LANGUAGE_EN) {
            setEnglishVisible();
        } else if (savedLanguageType == LanguageType.LANGUAGE_CHINESE_SIMPLIFIED) {
            setSimplifiedVisible();
        } else {
            setSimplifiedVisible();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        int selectedLanguage = 0;
        switch (id) {
            case R.id.rl_followsytem:
                setFollowSytemVisible();
                selectedLanguage = LanguageType.LANGUAGE_FOLLOW_SYSTEM;
                break;
            case R.id.rl_simplified_chinese:
                setSimplifiedVisible();
                selectedLanguage = LanguageType.LANGUAGE_CHINESE_SIMPLIFIED;

                break;
            case R.id.rl_traditional_chinese:
                setTraditionalVisible();
                selectedLanguage = LanguageType.LANGUAGE_CHINESE_TRADITIONAL;

                break;
            case R.id.rl_english:
                setEnglishVisible();
                selectedLanguage = LanguageType.LANGUAGE_EN;
                break;
        }
        MultiLanguageUtil.getInstance().updateLanguage(selectedLanguage);
        Intent intent = new Intent(SetLanguageActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
//        if (selectedLanguage == LanguageType.LANGUAGE_FOLLOW_SYSTEM) {
////            System.exit(0);
//        }
    }

    private void setSimplifiedVisible() {
        iv_followsystem.setVisibility(View.GONE);
        iv_english.setVisibility(View.GONE);
        iv_simplified_chinese.setVisibility(View.VISIBLE);
        iv_traditional_chinese.setVisibility(View.GONE);
    }

    private void setEnglishVisible() {
        iv_followsystem.setVisibility(View.GONE);
        iv_english.setVisibility(View.VISIBLE);
        iv_simplified_chinese.setVisibility(View.GONE);
        iv_traditional_chinese.setVisibility(View.GONE);
    }

    private void setTraditionalVisible() {
        iv_followsystem.setVisibility(View.GONE);
        iv_english.setVisibility(View.GONE);
        iv_simplified_chinese.setVisibility(View.GONE);
        iv_traditional_chinese.setVisibility(View.VISIBLE);
    }

    private void setFollowSytemVisible() {
        iv_followsystem.setVisibility(View.VISIBLE);
        iv_english.setVisibility(View.GONE);
        iv_simplified_chinese.setVisibility(View.GONE);
        iv_traditional_chinese.setVisibility(View.GONE);
    }
}
