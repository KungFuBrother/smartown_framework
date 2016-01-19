package com.smartown.library.ui.widget;

public abstract class OnLoadMoreListener {

    public final static int STATE_WAITING = 0;
    public final static int STATE_LOADING = 1;

    private int loadState = STATE_WAITING;

    protected abstract void onLoadMore();

    public void startLoadMore() {
        loadState = STATE_LOADING;
        onLoadMore();
    }

    public void finishLoadMore() {
        loadState = STATE_WAITING;
    }

    public int getLoadState() {
        return loadState;
    }
}
