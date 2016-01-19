package com.smartown.app.activity;

import android.os.Bundle;

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

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void registerViews() {

    }
}
