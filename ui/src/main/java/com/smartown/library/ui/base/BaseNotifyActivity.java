package com.smartown.library.ui.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartown.application.R;
import com.smartown.library.tool.Tools;

/**
 * Created by Tiger on 2015-11-09.
 */
public abstract class BaseNotifyActivity extends BaseActivity {

    private LinearLayout titleLayout;
    private ImageView backButton;
    private TextView titleTextView;
    private LinearLayout actionButtonLayout;
    private LinearLayout loadingLayout;
    private FrameLayout contentLayout;

    private TextView loadingTextView;

    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_notify);

        titleLayout = (LinearLayout) findViewById(R.id.base_activity_title_layout);
        backButton = (ImageView) findViewById(R.id.base_activity_title_back);
        titleTextView = (TextView) findViewById(R.id.base_activity_title_lable);
        actionButtonLayout = (LinearLayout) findViewById(R.id.base_activity_title_action);
        contentLayout = (FrameLayout) findViewById(R.id.base_activity_contnet_layout);
        loadingLayout = (LinearLayout) findViewById(R.id.base_activity_loading_layout);
        loadingTextView = (TextView) findViewById(R.id.base_activity_loading_text);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void initContentView(int layoutId) {
        contentView = layoutInflater.inflate(layoutId, null);
        if (contentView != null) {
            contentLayout.removeAllViews();
            contentLayout.addView(contentView);
        }
    }

    public void showLoading() {
        loadingTextView.setText("请稍候...");
        loadingLayout.setVisibility(View.VISIBLE);
    }

    public void showLoading(String s) {
        loadingTextView.setText(s);
        loadingLayout.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    public void setOnBackButtonClickListener(View.OnClickListener onClickListener) {
        backButton.setOnClickListener(onClickListener);
    }

    public void addTitleTextButton(String text, View.OnClickListener onClickListener) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        textView.setPadding(Tools.getInstance().dip2px(8), 0, Tools.getInstance().dip2px(8), 0);
        textView.setText(text);
        textView.setMinWidth(Tools.getInstance().dip2px(56));
//        textView.setBackgroundResource(R.drawable.selector_trans_divider);
        textView.setTextColor(Color.argb(255, 255, 255, 255));
        textView.setGravity(Gravity.CENTER);
        textView.setOnClickListener(onClickListener);
        actionButtonLayout.addView(textView);
    }

    public void addTitleImageButton(int imageResId, String tag, View.OnClickListener onClickListener) {
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(Tools.getInstance().dip2px(56), LinearLayout.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setTag(tag);
//        imageView.setBackgroundResource(R.drawable.selector_trans_divider);
        imageView.setImageResource(imageResId);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setOnClickListener(onClickListener);
        actionButtonLayout.addView(imageView);
    }

}
