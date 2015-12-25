package app.test;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.smartown.framework.base.BaseNotifyFragment;
import com.smartown.framework.mission.MissionController;
import com.smartown.framework.mission.MissionMessage;
import com.smartown.framework.mission.Request;
import com.smartown.framework.mission.RequestListener;
import com.smartown.framework.mission.RequestMessage;
import com.smartown.framework.widget.OnLoadMoreListener;
import com.smartown.framework.widget.RefreshableRecyclerView;
import com.smartown.yitian.gogo.R;

import app.test.ui.adapter.TestAdapter;

/**
 * Created by Tiger on 2015-12-16.
 */
public class TestFragment extends BaseNotifyFragment {

    RefreshableRecyclerView refreshableRecyclerView;

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
        refreshableRecyclerView = (RefreshableRecyclerView) initViewById(R.id.test_list);
        initViews();
        registerViews();
    }

    @Override
    protected void initViews() {
        refreshableRecyclerView.getRecyclerView().setAdapter(new TestAdapter());
        refreshableRecyclerView.setCanLoadMore(true);
    }

    @Override
    protected void registerViews() {
        refreshableRecyclerView.getSwipeRefreshLayout().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        refreshableRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            protected void onLoadMore() {
                loadMore();
            }
        });
    }

    private void refresh() {
        Request request = new Request();
        request.setUrl("http://www.google.com");
        MissionController.startRequestMission(getActivity(), request, new RequestListener() {
            @Override
            protected void onStart() {
                hideLoading();
                refreshableRecyclerView.getSwipeRefreshLayout().setRefreshing(true);
            }

            @Override
            protected void onFail(MissionMessage missionMessage) {
                missionFailed();
            }

            @Override
            protected void onSuccess(RequestMessage requestMessage) {

            }

            @Override
            protected void onFinish() {
                refreshableRecyclerView.getSwipeRefreshLayout().setRefreshing(false);
            }
        });
    }

    private void loadMore() {
        Request request = new Request();
        request.setUrl("http://www.google.com");
        MissionController.startRequestMission(getActivity(), request, new RequestListener() {
            @Override
            protected void onStart() {
                showLoading();
            }

            @Override
            protected void onFail(MissionMessage missionMessage) {
                missionFailed();
            }

            @Override
            protected void onSuccess(RequestMessage requestMessage) {

            }

            @Override
            protected void onFinish() {
                hideLoading();
                refreshableRecyclerView.finishLoadMore();
            }
        });
    }

    @Override
    protected void reLoad() {
        super.reLoad();
        refresh();
    }

}
