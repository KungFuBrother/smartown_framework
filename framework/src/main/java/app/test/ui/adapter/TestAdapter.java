package app.test.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartown.yitian.gogo.R;

/**
 * Created by Tiger on 2015-11-17.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    public class TestViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public TestViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_list_string_text);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    @Override
    public TestAdapter.TestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new TestViewHolder(layoutInflater.inflate(R.layout.item_string_list, parent, false));
    }

    @Override
    public void onBindViewHolder(TestAdapter.TestViewHolder holder, int position) {
        holder.getTextView().setText("position" + position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

}
