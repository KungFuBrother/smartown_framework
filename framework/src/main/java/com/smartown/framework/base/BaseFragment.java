package com.smartown.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;

/**
 * Created by Tiger on 2015-11-09.
 */
public abstract class BaseFragment extends Fragment {

    protected LayoutInflater layoutInflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutInflater = LayoutInflater.from(getActivity());
    }

    /**
     * 数据初始化
     */
    protected abstract void init();

    /**
     * 绑定UI
     */
    protected abstract void findViews();

    /**
     * 初始化UI
     */
    protected abstract void initViews();

    /**
     * 注册监听UI
     */
    protected abstract void registerViews();

    /**
     * jump ui
     *
     * @param fragment
     */
    protected void jump(String fragment) {
        jump(fragment, "");
    }

    protected void jump(String fragment, String title) {
        jump(fragment, title, null);
    }

    protected void jump(String fragment, String title, Bundle bundle) {
        Intent intent = new Intent(getActivity(), FragmentContainerActivity.class);
        if (!TextUtils.isEmpty(fragment)) {
            intent.putExtra("fragment", fragment);
        }
        if (!TextUtils.isEmpty(title)) {
            intent.putExtra("title", title);
        }
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }

}
