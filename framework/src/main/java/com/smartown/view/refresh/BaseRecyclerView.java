package com.smartown.view.refresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.smartown.tool.ApplicationTool;
import com.smartown.ui.adapter.BaseAdapter;

/**
 * Created by Tiger on 2015-11-17.
 */
public class BaseRecyclerView extends RecyclerView {

    OnLoadByPageListener onLoadByPageListener;
    boolean loadByPage = false;

    public BaseRecyclerView(Context context) {
        super(context);
        addOnScrollListener(onScrollListener);
    }

    public BaseRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addOnScrollListener(onScrollListener);
    }

    public BaseRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addOnScrollListener(onScrollListener);
    }

    private RecyclerView.OnScrollListener onScrollListener = new OnScrollListener() {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (loadByPage) {
                if (onLoadByPageListener != null) {
                    ApplicationTool.log("RecyclerView", "onScrollStateChanged");
                    //所有item总数
                    int totalCount = recyclerView.getLayoutManager().getItemCount();
                    //可见的item总数
                    int visibleCount = recyclerView.getLayoutManager().getChildCount();
                    //第一个可见item的位置
                    int firstVisiblePosition = recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0));
                    if (firstVisiblePosition + visibleCount >= totalCount) {
                        if (onLoadByPageListener.getLoadState() == OnLoadByPageListener.STATE_WAITING) {
                            startLoading();
                        }
                    }
                }
            }
        }

    };

    public void startLoading() {
        if (onLoadByPageListener != null) {
            ApplicationTool.log("RecyclerView", "startLoading");
            BaseAdapter adapter = (BaseAdapter) getAdapter();
            adapter.setIsLoading(true);
            onLoadByPageListener.startLoading();
        }
    }

    public void finishLoading() {
        if (onLoadByPageListener != null) {
            BaseAdapter adapter = (BaseAdapter) getAdapter();
            adapter.setIsLoading(false);
            onLoadByPageListener.finishLoading();
        }
    }

    public void setLoadByPage(boolean loadByPage) {
        this.loadByPage = loadByPage;
    }

    public void setOnLoadByPageListener(OnLoadByPageListener onLoadByPageListener) {
        this.onLoadByPageListener = onLoadByPageListener;
    }
}
