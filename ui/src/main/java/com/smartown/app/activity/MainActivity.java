package com.smartown.app.activity;

import android.os.Bundle;
import android.view.View;

import com.smartown.application.R;
import com.smartown.library.mission.MissionController;
import com.smartown.library.mission.MissionMessage;
import com.smartown.library.mission.Request;
import com.smartown.library.mission.RequestListener;
import com.smartown.library.mission.RequestMessage;
import com.smartown.library.ui.base.BaseNotifyActivity;

/**
 * Created by Tiger on 2016-01-11.
 */
public class MainActivity extends BaseNotifyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(R.layout.activity_main);
        init();
        findViews();
    }

    @Override
    protected void init() {
        Request request = new Request();
        request.setUrl("http://www.google.com");
        MissionController.startRequestMission(this, request, new RequestListener() {
            @Override
            protected void onStart() {
                showLoading();
            }

            @Override
            protected void onFail(MissionMessage missionMessage) {

            }

            @Override
            protected void onSuccess(RequestMessage requestMessage) {

            }

            @Override
            protected void onFinish() {
                hideLoading();
            }
        });
    }

    @Override
    protected void findViews() {
        initViews();
        registerViews();
    }

    @Override
    protected void initViews() {
        setTitle("首页");
        addTitleTextButton("test", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addTitleImageButton(R.mipmap.ic_launcher, "", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void registerViews() {

    }
}
