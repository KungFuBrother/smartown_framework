package com.smartown.library.ui.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartown.application.R;

/**
 * Created by Tiger on 2015-11-09.
 */
public abstract class BaseNotifyActivity extends BaseActivity {

    private Toolbar toolbar;
    private LinearLayout loadingLayout;
    private FrameLayout contentLayout;

    private TextView loadingTextView;

    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_notify);
        toolbar = (Toolbar) findViewById(R.id.base_activity_toolbar);
        contentLayout = (FrameLayout) findViewById(R.id.base_activity_contnet_layout);
        loadingLayout = (LinearLayout) findViewById(R.id.base_activity_loading_layout);
        loadingTextView = (TextView) findViewById(R.id.base_activity_loading_text);
        configToolbar();
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * use in subClass's onCreate();after super.onCreate();
     *
     * @param layoutId
     */
    protected void initContentView(int layoutId) {
        contentView = layoutInflater.inflate(layoutId, null);
        if (contentView != null) {
            contentLayout.removeAllViews();
            contentLayout.addView(contentView);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected void configToolbar() {
    }

    /**
     * network mission start
     */
    protected void showLoading() {
        loadingTextView.setText("请稍候...");
        loadingLayout.setVisibility(View.VISIBLE);
    }

    protected void showLoading(String s) {
        loadingTextView.setText(s);
        loadingLayout.setVisibility(View.VISIBLE);
    }

    /**
     * network mission finished
     */
    protected void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

}
