package com.smartown.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartown.controller.mission.MissionController;
import com.smartown.yitian.gogo.R;

/**
 * Created by Tiger on 2015-11-09.
 */
public abstract class BaseNotifyFragment extends BaseFragment {

    LinearLayout failLayout, nodataLayout, loadingLayout;
    FrameLayout contentLayout;

    Button reloadButton;

    TextView nodataTextView, loadingTextView;

    View contentView;

    @Override
    public void onDestroy() {
        MissionController.cancelMissions(getActivity());
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_notify, null);
        failLayout = (LinearLayout) view.findViewById(R.id.base_fragment_fail_layout);
        nodataLayout = (LinearLayout) view.findViewById(R.id.base_fragment_nodata_layout);
        loadingLayout = (LinearLayout) view.findViewById(R.id.base_fragment_loading_layout);
        contentLayout = (FrameLayout) view.findViewById(R.id.base_fragment_contnet_layout);

        reloadButton = (Button) view.findViewById(R.id.base_fragment_fail_reload);

        nodataTextView = (TextView) view.findViewById(R.id.base_fragment_nodata_text);
        loadingTextView = (TextView) view.findViewById(R.id.base_fragment_loading_text);

        /**
         * set contentView
         */
        if (contentView != null) {
            contentLayout.removeAllViews();
            contentLayout.addView(contentView);
        }

        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reLoad();
            }
        });

        return view;
    }

    /**
     * use in subClass's onCreate();
     *
     * @param layoutId
     */
    protected void setContentView(int layoutId) {
        contentView = layoutInflater.inflate(layoutId, null);
    }

    /**
     * bind subClass views;
     *
     * @param viewId
     */
    protected View findViewById(int viewId) {
        return contentView.findViewById(viewId);
    }

    /**
     * reLoad on loading fail
     */
    protected void reLoad() {

    }

    /**
     * network mission start
     */
    protected void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        failLayout.setVisibility(View.GONE);
        nodataLayout.setVisibility(View.GONE);
    }

    /**
     * network mission finished
     */
    protected void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
        failLayout.setVisibility(View.GONE);
        nodataLayout.setVisibility(View.GONE);
    }

    /**
     * network mission finished on fail
     */
    protected void missionFailed() {
        loadingLayout.setVisibility(View.GONE);
        failLayout.setVisibility(View.VISIBLE);
        nodataLayout.setVisibility(View.GONE);
    }

    /**
     * network mission finished but not contain useable data
     */
    protected void missionNodata() {
        loadingLayout.setVisibility(View.GONE);
        failLayout.setVisibility(View.GONE);
        nodataLayout.setVisibility(View.VISIBLE);
    }

}
