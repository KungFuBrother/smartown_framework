package app.test.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.smartown.yitian.gogo.R;

/**
 * Created by Tiger on 2015-11-17.
 */
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
