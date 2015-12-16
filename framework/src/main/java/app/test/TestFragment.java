package app.test;

import android.os.Bundle;

import com.smartown.framework.base.BaseNotifyFragment;
import com.smartown.framework.mission.MissionController;
import com.smartown.framework.mission.MissionMessage;
import com.smartown.framework.mission.Request;
import com.smartown.framework.mission.RequestListener;
import com.smartown.framework.mission.RequestMessage;
import com.smartown.framework.tool.ApplicationTool;
import com.smartown.yitian.gogo.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Tiger on 2015-12-16.
 */
public class TestFragment extends BaseNotifyFragment {

    PtrClassicFrameLayout ptrClassicFrameLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(R.layout.fragment_test);
        findViews();
    }

    @Override
    protected void init() {

    }

    @Override
    protected void findViews() {
        ptrClassicFrameLayout = (PtrClassicFrameLayout) initViewById(R.id.test_refresh);
        initViews();
        registerViews();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void registerViews() {
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Request request = new Request();
                request.setUrl("http://www.baidu.com", "");
                MissionController.startNetworkMission(getActivity(), request, new RequestListener() {

                    @Override
                    protected void onStart() {
                        showLoading();
                    }

                    @Override
                    protected void onFail(MissionMessage missionMessage) {

                    }

                    @Override
                    protected void onSuccess(RequestMessage requestMessage) {
                        ApplicationTool.log("RequestMessage", requestMessage.getResult());
                        ptrClassicFrameLayout.refreshComplete();
                    }

                    @Override
                    protected void onFinish() {
                        hideLoading();
                    }

                });
            }
        });
    }

}
