package app.test.ui.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Tiger on 2015-11-17.
 */
public class RefreshableScrollView extends SwipeRefreshLayout {
    /**
     * 不适用下拉刷新和加载更多
     */
    public final static int TYPE_DISABLE = 1;
    /**
     * 只使用下拉刷新
     */
    public final static int TYPE_REFRESH = 2;
    /**
     * 下拉刷新及加载更多
     */
    public final static int TYPE_BOTH = 3;
    /**
     * 设置刷新类型
     */
    private int loadType = TYPE_DISABLE;

    private OnLoadMoreListener onLoadMoreListener;

    public RefreshableScrollView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        super.setRefreshing(refreshing);
    }

    private void initView(Context context) {
        ScrollView scrollView = new ScrollView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        scrollView.setLayoutParams(layoutParams);
//        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                ApplicationTool.log("onScrollChange", scrollX + "," + scrollY + "," + oldScrollX + "," + oldScrollY);
//            }
//        });
        addView(scrollView);
    }

    public void setLoadType(int loadType) {
        this.loadType = loadType;
    }

}
