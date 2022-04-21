# AndroidMultiLanguage
APP实现多语言（国际化）过程，兼容Android 7.0、8.0，解决了原项目切换过程中的语言切换不生效问题

# AndroidX切换多语言失效解决方案


主要是在基类里面添加以下方法：


```

@Override
public void applyOverrideConfiguration(Configuration overrideConfiguration) {
    // 兼容androidX在部分手机切换语言失败问题
    if (overrideConfiguration != null) {
        int uiMode = overrideConfiguration.uiMode;
        overrideConfiguration.setTo(getBaseContext().getResources().getConfiguration());
        overrideConfiguration.uiMode = uiMode;
    }
    super.applyOverrideConfiguration(overrideConfiguration);
}

```
