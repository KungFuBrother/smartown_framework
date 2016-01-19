package com.smartown.library.ui.base;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartown.application.R;

/**
 * Created by Tiger on 2015-11-09.
 */
public abstract class BaseNotifyActivity extends BaseActivity {

    private LinearLayout loadingLayout;
    private FrameLayout contentLayout;

    private TextView loadingTextView;

    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_notify);
        loadingLayout = (LinearLayout) findViewById(R.id.base_activity_loading_layout);
        contentLayout = (FrameLayout) findViewById(R.id.base_activity_contnet_layout);
        loadingTextView = (TextView) findViewById(R.id.base_activity_loading_text);
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

    /**
     * bind subClass views;
     *
     * @param viewId
     */
    protected View initViewById(int viewId) {
        return contentView.findViewById(viewId);
    }

    /**
     * network mission start
     */
    protected void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    /**
     * network mission finished
     */
    protected void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

}
