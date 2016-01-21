package com.smartown.library.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

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

    protected abstract void init();

    protected abstract void findViews();

    protected abstract void initViews();

    protected abstract void registerViews();

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

    protected BaseNotifyActivity getContainerActivity() {
        return (BaseNotifyActivity) getActivity();
    }

    protected void showLoading() {
        getContainerActivity().showLoading();
    }

    protected void showLoading(String s) {
        getContainerActivity().showLoading(s);
    }

    protected void hideLoading() {
        getContainerActivity().hideLoading();
    }

    protected void setTitle(String title) {
        getContainerActivity().setTitle(title);
    }

    protected void setOnBackButtonClickListener(View.OnClickListener onClickListener) {
        getContainerActivity().setOnBackButtonClickListener(onClickListener);
    }

    protected void addTitleTextButton(String text, View.OnClickListener onClickListener) {
        getContainerActivity().addTitleTextButton(text, onClickListener);
    }

    protected void addTitleImageButton(int imageResId, String tag, View.OnClickListener onClickListener) {
        getContainerActivity().addTitleImageButton(imageResId, tag, onClickListener);
    }

}
