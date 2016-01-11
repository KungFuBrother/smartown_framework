package com.smartown.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.smartown.framework.R;
import com.smartown.framework.base.BaseActivity;

/**
 * Created by Tiger on 2016-01-11.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jump(Fragment.class.getName());
    }

    @Override
    protected void init() {

    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void registerViews() {

    }
}
