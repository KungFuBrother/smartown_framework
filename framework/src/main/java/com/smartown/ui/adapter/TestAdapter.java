package com.smartown.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.smartown.ui.viewholder.LoadingViewHolder;
import com.smartown.ui.viewholder.TestViewHolder;
import com.smartown.yitian.gogo.R;

/**
 * Created by Tiger on 2015-11-17.
 */
public class TestAdapter extends BaseAdapter {

    int dataCount = 20;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == 2) {
            return new LoadingViewHolder(layoutInflater.inflate(R.layout.item_footer_loading, parent, false));
        }
        return new TestViewHolder(layoutInflater.inflate(R.layout.item_string_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == dataCount) {
            return;
        }
        TestViewHolder viewHolder = (TestViewHolder) holder;
        viewHolder.getTextView().setText("position" + position);
    }

    @Override
    public int getItemCount() {
        if (isLoading) {
            return dataCount + 1;
        }
        return dataCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == dataCount) {
            return 2;
        }
        return 1;
    }

}
