package com.smartown.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartown.base.BaseActivity;
import com.smartown.yitian.gogo.R;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    LayoutInflater layoutInflater;
    Toolbar toolbar;
    GridView tabView;
    List<Tab> tabs;
    TabAdapter tabAdapter;
    int currentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        findViews();
    }

    @Override
    protected void init() {
        layoutInflater = LayoutInflater.from(this);
        tabs = new ArrayList<>();
        tabs.add(new Tab(R.drawable.home_main_normal, R.drawable.home_main_selected, "首页", new Fragment()));
        tabs.add(new Tab(R.drawable.home_yitgogo_normal, R.drawable.home_yitgogo_selected, "易商城", new Fragment()));
        tabs.add(new Tab(R.drawable.home_local_normal, R.drawable.home_local_selected, "易商圈", new Fragment()));
        tabs.add(new Tab(R.drawable.home_score_normal, R.drawable.home_score_selected, "云商城", new Fragment()));
        tabs.add(new Tab(R.drawable.home_user_normal, R.drawable.home_user_selected, "我", new Fragment()));
        tabAdapter = new TabAdapter();
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
    protected void findViews() {
        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        tabView = (GridView) findViewById(R.id.activity_main_tabs);
        initViews();
        registerViews();
    }

    @Override
    protected void initViews() {
        toolbar.setTitle(tabs.get(0).getLable());
        setSupportActionBar(toolbar);
        tabView.setNumColumns(tabs.size());
        tabView.setAdapter(tabAdapter);
        selectTab(0);
    }

    @Override
    protected void registerViews() {
        tabView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectTab(i);
            }
        });
    }

    private void selectTab(int id) {
        if (id == currentId) {
            return;
        }
        toolbar.setTitle(tabs.get(id).getLable());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (currentId > -1 & currentId < tabs.size()) {
            if (tabs.get(currentId).getFragment().isAdded()) {
                transaction.hide(tabs.get(currentId).getFragment());
            }
        }
        if (!tabs.get(id).getFragment().isAdded()) {
            transaction.add(R.id.activity_main_content, tabs.get(id).getFragment());
        } else {
            transaction.show(tabs.get(id).getFragment());
        }
        transaction.commit();
        currentId = id;
        tabAdapter.notifyDataSetChanged();
    }

    public class Tab {

        int iconId = R.drawable.ic_arrow_back_white_24dp;
        int iconSelectedId = R.drawable.ic_arrow_back_white_24dp;
        String lable = "";
        Fragment fragment = new Fragment();

        public Tab(int iconId, int iconSelectedId, String lable, Fragment fragment) {
            this.iconId = iconId;
            this.iconSelectedId = iconSelectedId;
            this.lable = lable;
            this.fragment = fragment;
        }

        public int getIconId() {
            return iconId;
        }

        public int getIconSelectedId() {
            return iconSelectedId;
        }

        public String getLable() {
            return lable;
        }

        public Fragment getFragment() {
            return fragment;
        }

    }

    public class TabAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Override
        public Object getItem(int i) {
            return tabs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = new ViewHolder();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.item_main_tab, null);
                viewHolder.iconImageView = (ImageView) view.findViewById(R.id.main_tab_icon);
                viewHolder.lableTextView = (TextView) view.findViewById(R.id.main_tab_lable);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Tab tab = tabs.get(i);
            if (currentId == i) {
                viewHolder.iconImageView.setImageResource(tab.getIconSelectedId());
                viewHolder.lableTextView.setTextColor(getResources().getColor(R.color.color_primary));
            } else {
                viewHolder.iconImageView.setImageResource(tab.getIconId());
                viewHolder.lableTextView.setTextColor(getResources().getColor(R.color.black_9f));
            }
            viewHolder.lableTextView.setText(tab.getLable());
            return view;
        }

        public class ViewHolder {
            ImageView iconImageView;
            TextView lableTextView;
        }

    }

}
