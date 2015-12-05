package com.smartown.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.WindowManager;
import android.widget.ImageView;

import com.smartown.base.BaseNotifyActivity;
import com.smartown.ui.adapter.TestAdapter;
import com.smartown.view.refresh.NetworkRecyclerView;
import com.smartown.view.refresh.OnLoadByPageListener;
import com.smartown.yitian.gogo.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

public class EntranceActivity extends BaseNotifyActivity {

    NetworkRecyclerView recyclerView;
    ImageView imageView;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                recyclerView.setRefreshing(false);
                return;
            }
            recyclerView.finishLoading();
            recyclerView.setLoadByPage(false);
        }
    };

    @Override
    protected void onCreate(Bundle bundle) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(bundle);
        initContentView(R.layout.activity_entrance);
        init();
        findViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        MobclickAgent.onPageStart(EntranceActivity.class.getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        MobclickAgent.onPageEnd(EntranceActivity.class.getName());
    }

    @Override
    protected void init() {
        UmengUpdateAgent.update(this);
    }

    @Override
    protected void findViews() {
//        imageView = (ImageView) findViewsById(R.id.entrance_activity_image);
        recyclerView = (NetworkRecyclerView) initViewById(R.id.entrance_activity_test);
        initViews();
        registerViews();
    }

    @Override
    protected void initViews() {
//        startActivity(new Intent(EntranceActivity.this, MainActivity.class));
        recyclerView.setLoadByPage(true);
        recyclerView.setAdapter(new TestAdapter());

//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void registerViews() {
        recyclerView.setOnLoadByPageListener(new OnLoadByPageListener() {
            @Override
            protected void onLoading() {
                handler.sendEmptyMessageDelayed(0, 5000);
            }
        });
        recyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        });
    }

}
