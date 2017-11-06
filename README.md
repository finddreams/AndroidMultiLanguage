# AndroidMultiLanguage
完美实现Android的多语言切换，国际化
应该是WebView在使用过程中会设置语言 , 比如说你在中国打开Facebook的网站 , 你的网页就是中文的 , 如果是美国就是英文的 , 这可能就是原因 .


    在使用到WebView的页面的onPause中 , 重新设置语言.

    @Override
    protected void onPause() {
        super.onPause();
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (isChina) {
            configuration.locale = Locale.CHINA;
        } else {
            configuration.locale = Locale.ENGLISH;
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }
