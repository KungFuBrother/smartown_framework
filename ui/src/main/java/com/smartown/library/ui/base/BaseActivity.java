package com.smartown.library.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;

import com.smartown.library.mission.MissionController;

public abstract class BaseActivity extends AppCompatActivity {

    protected LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutInflater = LayoutInflater.from(this);
    }

    @Override
    protected void onDestroy() {
        MissionController.cancelMissions(this);
        super.onDestroy();
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

    protected void jump(String fragment) {
        jump(fragment, "");
    }

    protected void jump(String fragment, String title) {
        jump(fragment, title, null);
    }

    protected void jump(String fragment, String title, Bundle bundle) {
        Intent intent = new Intent(BaseActivity.this, FragmentContainerActivity.class);
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
