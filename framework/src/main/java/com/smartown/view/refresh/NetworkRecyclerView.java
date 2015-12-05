package com.smartown.view.refresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.smartown.ui.adapter.BaseAdapter;

/**
 * Created by Tiger on 2015-11-17.
 */
public class NetworkRecyclerView extends SwipeRefreshLayout {

    //child view to show contents
    RecyclerView recyclerView;

    //load-by-page listener
    OnLoadByPageListener onLoadByPageListener;
    //enable load-by-page
    boolean loadByPage = false;

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {

                case RecyclerView.SCROLL_STATE_DRAGGING:
                    ImageLoader.getInstance().pause();
                    break;

                case RecyclerView.SCROLL_STATE_IDLE:
                    ImageLoader.getInstance().resume();
                    break;

                case RecyclerView.SCROLL_STATE_SETTLING:
                    ImageLoader.getInstance().resume();
                    if (!isRefreshing()) {
                        if (loadByPage) {
                            if (onLoadByPageListener != null) {
                                if (onLoadByPageListener.getLoadState() == OnLoadByPageListener.STATE_WAITING) {
                                    startLoading();
                                }
                            }
                        }
                    }
                    break;
            }
//            if (!isRefreshing()) {
//                if (loadByPage) {
//                    if (onLoadByPageListener != null) {
//                        ApplicationTool.log("RecyclerView", "onScrollStateChanged");
//                        //所有item总数
//                        int totalCount = recyclerView.getLayoutManager().getItemCount();
//                        //可见的item总数
//                        int visibleCount = recyclerView.getLayoutManager().getChildCount();
//                        //第一个可见item的位置
//                        int firstVisiblePosition = recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0));
//                        if (firstVisiblePosition + visibleCount >= totalCount) {
//                            if (onLoadByPageListener.getLoadState() == OnLoadByPageListener.STATE_WAITING) {
//                                startLoading();
//                            }
//                        }
//                    }
//                }
//            }
        }

    };

    public NetworkRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public NetworkRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        super.setRefreshing(refreshing);
        //enable load-by-page after refreshed.
        setLoadByPage(true);
    }

    private void initView(Context context) {
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        recyclerView.setLayoutParams(layoutParams);
        recyclerView.addOnScrollListener(onScrollListener);
        addView(recyclerView);
    }

    /**
     * start load-by-page
     */
    public void startLoading() {
        if (onLoadByPageListener != null) {
            BaseAdapter adapter = (BaseAdapter) recyclerView.getAdapter();
            adapter.setIsLoading(true);
            setEnabled(false);
            onLoadByPageListener.startLoading();
        }
    }

    /**
     * finish load-by-page
     */
    public void finishLoading() {
        if (onLoadByPageListener != null) {
            BaseAdapter adapter = (BaseAdapter) recyclerView.getAdapter();
            adapter.setIsLoading(false);
            setEnabled(true);
            onLoadByPageListener.finishLoading();
        }
    }

    /**
     * turn on/off load-by-page
     */
    public void setLoadByPage(boolean loadByPage) {
        this.loadByPage = loadByPage;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    public void setOnLoadByPageListener(OnLoadByPageListener onLoadByPageListener) {
        this.onLoadByPageListener = onLoadByPageListener;
    }

    /**
     * only List or Grid is supported
     *
     * @param layoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

}
