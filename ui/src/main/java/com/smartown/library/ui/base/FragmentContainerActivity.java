package com.smartown.library.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.smartown.application.R;
import com.umeng.analytics.MobclickAgent;

public class FragmentContainerActivity extends BaseNotifyActivity {

    private String fragment = "";
    private String title = "";
    private Bundle bundle = new Bundle();
    private Fragment contentFragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        findViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("fragment")) {
                fragment = intent.getStringExtra("fragment");
            }
            if (intent.hasExtra("title")) {
                title = intent.getStringExtra("title");
            }
            if (intent.hasExtra("bundle")) {
                bundle = intent.getBundleExtra("bundle");
            }
        }
        if (!TextUtils.isEmpty(fragment)) {
            try {
                contentFragment = (Fragment) Class.forName(fragment).newInstance();
                if (bundle != null) {
                    contentFragment.setArguments(bundle);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void findViews() {
        initViews();
        registerViews();
    }

    @Override
    protected void initViews() {
        setTitle(title);
        if (contentFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.base_activity_contnet_layout, contentFragment).commit();
        }
    }

    @Override
    protected void registerViews() {

    }

}
