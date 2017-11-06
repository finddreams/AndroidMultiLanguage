package com.finddreams.multilanguage;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;


import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

/**
 * 多语言切换的帮助类
 */
public class LanguageUtil {

    private static final String TAG = "LanguageUtil";
    private static LanguageUtil sInstacne;
    private Context mContext;
    public static final int LANGUAGE_FOLLOW_SYSTEM = 4; //跟随系统
    public static final int LANGUAGE_NO_SET = 0; //没有设置语言
    public static final int LANGUAGE_ENGLISH = 1;    //英文
    public static final int LANGUAGE_SIMPLE_CHINISE = 2; //简体
    public static final int LANGUAGE_TRADITIONAL_CHINESE = 3;  //繁体
    public static final String SAVE_LANGUAGE = "save_language";

    public static void init(Context mContext) {
        if (sInstacne == null) {
            synchronized (LanguageUtil.class) {
                if (sInstacne == null) {
                    sInstacne = new LanguageUtil(mContext);
                }
            }
        }
    }

    public static LanguageUtil getInstance() {
        if (sInstacne == null) {
            throw new IllegalStateException("You must be init LanguageUtil first");
        }
        return sInstacne;
    }

    private LanguageUtil(Context context) {
        this.mContext = context;
    }

    public void setConfiguration() {
        Locale targetLocale = getTragetLocale();
        Configuration configuration = mContext.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(targetLocale);
        } else {
            configuration.locale = targetLocale;
        }
        Resources resources = mContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        resources.updateConfiguration(configuration, dm);//语言更换生效的代码!
    }

    /**
     * 如果不是英文、简体中文、繁体中文，默认返回繁体中文
     *
     * @return
     */
    private Locale getTragetLocale() {
        int userType = CommSharedUtil.getInstance(mContext).getInt(LanguageUtil.SAVE_LANGUAGE, 0);
        int languageType = 0;
        if (userType == LanguageUtil.LANGUAGE_NO_SET) {
            Locale sysType = getSysLocale();
            if (sysType.equals(Locale.ENGLISH)) {
                languageType = LANGUAGE_ENGLISH;
                return Locale.TRADITIONAL_CHINESE;
            } else if (sysType.equals(Locale.SIMPLIFIED_CHINESE)) {
                languageType = LANGUAGE_SIMPLE_CHINISE;
                return Locale.SIMPLIFIED_CHINESE;
            } else if (sysType.equals(Locale.TRADITIONAL_CHINESE)) {
                languageType = LANGUAGE_TRADITIONAL_CHINESE;
                return Locale.TRADITIONAL_CHINESE;
            } else if (TextUtils.equals(sysType.getLanguage(), Locale.CHINA.getLanguage())) { //zh
                if (TextUtils.equals(sysType.getCountry(), Locale.CHINA.getCountry())) {  //适配华为mate9  zh_CN_#Hans
                    languageType = LANGUAGE_SIMPLE_CHINISE;
                    return Locale.SIMPLIFIED_CHINESE;
                }
            } else {
                languageType = LANGUAGE_TRADITIONAL_CHINESE;
                return Locale.TRADITIONAL_CHINESE;
            }
        } else if (userType == LanguageUtil.LANGUAGE_ENGLISH) {
            languageType = LANGUAGE_ENGLISH;
            return Locale.ENGLISH;
        } else if (userType == LanguageUtil.LANGUAGE_SIMPLE_CHINISE) {
            languageType = LANGUAGE_SIMPLE_CHINISE;
            return Locale.SIMPLIFIED_CHINESE;
        } else if (userType == LanguageUtil.LANGUAGE_TRADITIONAL_CHINESE) {
            languageType = LANGUAGE_TRADITIONAL_CHINESE;
            return Locale.TRADITIONAL_CHINESE;
        }
        Log.e(TAG, "getTragetLocale" + userType + languageType);
        getSystemLangue(getSysLocale());
        CommSharedUtil.getInstance(mContext).putInt(LanguageUtil.SAVE_LANGUAGE, languageType);
        return Locale.TRADITIONAL_CHINESE;
    }

    private String getSystemLangue(Locale locale) {
        return locale.getLanguage() + "_" + locale.getCountry();

    }

    //6.0以上获取方式需要特殊处理一下
    public Locale getSysLocale() {
        if (Build.VERSION.SDK_INT < 24) {
            return mContext.getResources().getConfiguration().locale;
        } else {
            return mContext.getResources().getConfiguration().getLocales().get(0);
        }
    }

    /**
     * 更新语言
     *
     * @param languageType
     */
    public void updateLanguage(int languageType) {
        CommSharedUtil.getInstance(mContext).putInt(LanguageUtil.SAVE_LANGUAGE, languageType);
        LanguageUtil.getInstance().setConfiguration();
        EventBus.getDefault().post(new OnChangeLanguageEvent(languageType));
    }

    //    public String getLanguageName() {
//        int languageType = CommSharedUtil.getInt(LanguageUtil.SAVE_LANGUAGE);
//        if (languageType == LanguageType.ENGLISH) {
//            return mContext.getString(R.string.settings_language_english);
//        } else if (languageType == LanguageType.SIMPLE_CHINISE) {
//            return mContext.getString(R.string.settings_language_simple_chinise);
//        } else if (languageType == LanguageType.TRADITIONAL_CHINESE) {
//            return mContext.getString(R.string.settings_language_traditional_chinise);
//        }
//
//        return mContext.getString(R.string.settings_language_follow_system);
//    }
    public int getLanguageType() {
        int userType = CommSharedUtil.getInstance(mContext).getInt(LanguageUtil.SAVE_LANGUAGE, 0);
        if (userType == LanguageUtil.LANGUAGE_FOLLOW_SYSTEM) {  //跟随系统或者没有进行语言设置   都直接跟系统走!!!
            Locale sysType = LanguageUtil.getInstance().getSysLocale();
            if (sysType.equals(Locale.SIMPLIFIED_CHINESE)) {
                return LANGUAGE_SIMPLE_CHINISE;
            } else if (sysType.equals(Locale.TRADITIONAL_CHINESE)) {
                return LANGUAGE_TRADITIONAL_CHINESE;
            } else {
                return LANGUAGE_TRADITIONAL_CHINESE;
            }
        } else if (userType == LanguageUtil.LANGUAGE_SIMPLE_CHINISE) {
            return LANGUAGE_SIMPLE_CHINISE;
        } else if (userType == LanguageUtil.LANGUAGE_TRADITIONAL_CHINESE) {
            return LANGUAGE_TRADITIONAL_CHINESE;
        } else if (userType == LanguageUtil.LANGUAGE_NO_SET) {
            Locale sysType = getSysLocale();
            if (sysType.equals(Locale.ENGLISH)) {
                return LANGUAGE_TRADITIONAL_CHINESE;
            } else if (sysType.equals(Locale.SIMPLIFIED_CHINESE)) {
                return LANGUAGE_SIMPLE_CHINISE;
            } else if (sysType.equals(Locale.TRADITIONAL_CHINESE)) {
                return LANGUAGE_TRADITIONAL_CHINESE;
            } else if (TextUtils.equals(sysType.getLanguage(), Locale.CHINA.getLanguage())) { //zh
                if (TextUtils.equals(sysType.getCountry(), Locale.CHINA.getCountry())) {//cn
                    return LANGUAGE_SIMPLE_CHINISE;
                }
            } else {
                return LANGUAGE_TRADITIONAL_CHINESE;
            }
        }
        Log.e(TAG, "getLanguageType" + userType);
        return userType;
    }
}
