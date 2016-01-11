package com.smartown.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.smartown.framework.R;
import com.umeng.analytics.MobclickAgent;

public class FragmentContainerActivity extends BaseActivity {

    private Toolbar toolbar;

    private String fragment = "";
    private String title = "";
    private Bundle bundle = new Bundle();
    private Fragment contentFragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
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
        toolbar = (Toolbar) findViewById(R.id.activity_fragment_toolbar);
        initViews();
        registerViews();
    }

    @Override
    protected void initViews() {
        if (!TextUtils.isEmpty(title)) {
            toolbar.setTitle(title);
        } else {
            toolbar.setTitle("返回");
        }
        setSupportActionBar(toolbar);
        if (contentFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_fragment_content, contentFragment).commit();
        }
    }

    @Override
    protected void registerViews() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_fragment, menu);
//        return true;
//    }

}
