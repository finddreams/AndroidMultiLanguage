# AndroidMultiLanguage
APP实现多语言（国际化）过程，兼容Android 7.0、8.0，解决了原项目切换过程中的语言切换不生效问题

# AndroidX切换多语言失效解决方案

参考：https://blog.csdn.net/KingsleyCheng/article/details/104862591

主要是在基类里面添加以下方法：

<<<<<<< HEAD
```
=======
'
>>>>>>> 4b272e1... Update README.md
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
<<<<<<< HEAD
```

# 版本说明

**1.1**

支持Androidx

**1.0**

=======
'

# 版本说明

1.1
支持Androidx

1.0
>>>>>>> 4b272e1... Update README.md
优化代码
