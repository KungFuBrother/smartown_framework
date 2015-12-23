package com.smartown.framework.mission;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class Request {

    boolean saveCookie = false;
    boolean useCookie = false;

    String url = "";

    List<RequestParam> requestParams = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUseCookie() {
        return useCookie;
    }

    public void setUseCookie(boolean useCookie) {
        this.useCookie = useCookie;
    }

    public boolean isSaveCookie() {
        return saveCookie;
    }

    public void setSaveCookie(boolean saveCookie) {
        this.saveCookie = saveCookie;
    }

    public List<RequestParam> getRequestParams() {
        return requestParams;
    }

    public void addRequestParam(String key, String value) {
        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
            return;
        }
        requestParams.add(new RequestParam(key, value));
    }

    public void addRequestParam(List<RequestParam> params) {
        if (params != null) {
            requestParams.addAll(params);
        }
    }

}
