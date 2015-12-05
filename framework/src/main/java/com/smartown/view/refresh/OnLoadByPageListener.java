package com.smartown.view.refresh;

public abstract class OnLoadByPageListener {

    public final static int STATE_WAITING = 0;
    public final static int STATE_LOADING = 1;

    private int loadState = STATE_WAITING;

    protected abstract void onLoading();

    public void startLoading() {
        loadState = STATE_LOADING;
        onLoading();
    }

    public void finishLoading() {
        loadState = STATE_WAITING;
    }

    public int getLoadState() {
        return loadState;
    }
}
