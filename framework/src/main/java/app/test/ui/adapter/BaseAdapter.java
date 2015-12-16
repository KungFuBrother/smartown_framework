package app.test.ui.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Tiger on 2015-11-17.
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    boolean isLoading = false;

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
        notifyDataSetChanged();
    }
}
