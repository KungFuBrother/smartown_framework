package com.smartown.framework.tool;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Tiger on 2015-11-10.
 */
public class ApplicationTool {

    private Toast toast;

    //show loginfo
    private boolean debugMode = true;

    //screen info
    private double scale;
    private int screenWidth = 0, screenHeight = 0;

    //application info
    private int versionCode = 0;
    private String versionName = "";
    private String packageName = "";

    //application info
    private String publicHost = "http://yitos.net";
    private String cloudHost = "http://gcapi.suning.com";
    private String moneyHost = "http://pay.yitos.net";

//    String IP_PUBLIC = "http://42.96.249.111";
//    String IP_SUNING = "http://58.240.86.161";
//    String IP_MONEY = "http://115.28.252.7";

//    String IP_PUBLIC = "http://192.168.8.8:8050";
//    String IP_SUNING = "http://58.240.86.161";
//    String IP_MONEY = "http://192.168.8.8:82";

    private static ApplicationTool instance;

    public static ApplicationTool getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new ApplicationTool(context);
    }

    public ApplicationTool(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);

        scale = context.getResources().getDisplayMetrics().density;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;

        try {
            packageName = context.getPackageName();
            PackageManager manager = context.getPackageManager();
            PackageInfo packageInfo = manager.getPackageInfo(packageName, 0);
            versionCode = packageInfo.versionCode;
            versionName = packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToast(String text) {
        toast.setText(text);
        toast.show();
    }

    public void setDebugMode(boolean enable) {
        debugMode = enable;
    }

    public void log(String tag, String info) {
        if (debugMode) {
            Log.i(tag, info);
        }
    }

    public int dip2px(float dipValue) {
        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPublicHost() {
        return publicHost;
    }

    public String getCloudHost() {
        return cloudHost;
    }

    public String getMoneyHost() {
        return moneyHost;
    }

}
