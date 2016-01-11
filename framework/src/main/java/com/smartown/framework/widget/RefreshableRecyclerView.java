package com.smartown.framework.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.smartown.framework.R;

/**
 * Created by Tiger on 2015-12-17.
 */
public class RefreshableRecyclerView extends FrameLayout {

    private boolean canLoadMore = false;
    private OnLoadMoreListener onLoadMoreListener;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayout loadMoreLayout;

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
                    break;
            }
            if (canLoadMore) {
                if (!swipeRefreshLayout.isRefreshing()) {
                    if (!isLoadingMore()) {
                        //所有item总数
                        int totalCount = recyclerView.getLayoutManager().getItemCount();
                        //可见的item总数
                        int visibleCount = recyclerView.getLayoutManager().getChildCount();
                        //第一个可见item的位置
                        int firstVisiblePosition = recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0));
                        if (firstVisiblePosition + visibleCount >= totalCount) {
                            if (onLoadMoreListener.getLoadState() == OnLoadMoreListener.STATE_WAITING) {
                                startLoadMore();
                            }
                        }
                    }
                }
            }
        }

    };

    public RefreshableRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshableRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshableRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.widegt_refershable_recyclerview, null);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshable_swipe_refresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.refreshable_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addOnScrollListener(onScrollListener);
        loadMoreLayout = (LinearLayout) view.findViewById(R.id.refreshable_loading_more);

        addView(view);
    }

    public boolean isLoadingMore() {
        if (onLoadMoreListener == null) {
            return false;
        }
        return onLoadMoreListener.getLoadState() == OnLoadMoreListener.STATE_LOADING;
    }

    private void startLoadMore() {
        if (onLoadMoreListener == null) {
            return;
        }
        swipeRefreshLayout.setEnabled(false);
        loadMoreLayout.setVisibility(View.VISIBLE);
        onLoadMoreListener.startLoadMore();
    }

    public void finishLoadMore() {
        if (onLoadMoreListener == null) {
            return;
        }
        swipeRefreshLayout.setEnabled(true);
        loadMoreLayout.setVisibility(View.GONE);
        onLoadMoreListener.finishLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

}
