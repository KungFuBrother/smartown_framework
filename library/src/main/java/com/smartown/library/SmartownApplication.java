package com.smartown.library;

import android.app.Application;

import com.smartown.library.tool.Tools;

/**
 * Created by Tiger on 2015-11-10.
 */
public class SmartownApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Tools.init(this);
        Tools.getInstance().setDebugMode(true);
    }

}
