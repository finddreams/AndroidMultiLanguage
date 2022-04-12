package com.lzx.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.finddreams.languagelib.MultiLanguageUtil;

/**
 * 系统语言变化监听器
 *
 * @author lizhixian
 * @date 2018/9/10
 */
public class LanguageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_LOCALE_CHANGED)) {
            int languageType = MultiLanguageUtil.getInstance().getLanguageType(context);
            Log.d("LanguageReceiver", "languageType -> " + languageType);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
