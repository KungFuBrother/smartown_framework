package com.smartown.framework;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.L;
import com.smartown.framework.tool.ApplicationTool;
import com.smartown.yitian.gogo.R;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UpdateConfig;

/**
 * Created by Tiger on 2015-11-10.
 */
public class SmartownApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationTool.init(this);
        ApplicationTool.setDebugMode(true);
        initUmeng();
        initImageLoader();
    }

    private void initUmeng() {
        //Edit in AndroidManifest.xml
        //AnalyticsConfig.setAppkey(this,"564143c067e58e7902003900");
        //AnalyticsConfig.setChannel("Tencent");
        //session超时
        MobclickAgent.setSessionContinueMillis(60 * 1000);
        //账号统计
//        MobclickAgent.onProfileSignIn("userid");
//        MobclickAgent.onProfileSignIn("QQ","userid");
        MobclickAgent.onProfileSignOff();
        /**
         * 禁止默认的页面统计方式，这样将不会再自动统计Activity。
         * 然后需要做两步集成：
         1. 使用 onResume 和 onPause 方法统计时长, 这和基本统计中的情况一样(针对Activity)
         2. 使用 onPageStart 和 onPageEnd 方法统计页面(针对页面,页面可能是Activity 也可能是Fragment或View)
         */
        MobclickAgent.openActivityDurationTrack(false);
        /** 设置是否对日志信息进行加密, 默认false(不加密). */
        AnalyticsConfig.enableEncrypt(true);
        MobclickAgent.setDebugMode(true);
        /**
         * 自动更新SDK配置
         * 仅在WiFi环境下提示更新
         */
        UmengUpdateAgent.setUpdateOnlyWifi(true);
        /**
         * 打开日志输出，发布应用时请去掉
         */
        UpdateConfig.setDebug(true);
        /**
         * 检测集成是否成功
         */
        UmengUpdateAgent.setUpdateCheckConfig(false);
    }

    private void initImageLoader() {
        L.writeDebugLogs(false);
        L.writeLogs(false);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading_default)
                .showImageForEmptyUri(R.drawable.loading_default)
                .showImageOnFail(R.drawable.loading_default)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(500))
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .diskCacheExtraOptions(480, 320, null)
                .threadPoolSize(5)
                .build();
        ImageLoader.getInstance().init(configuration);
    }

}
