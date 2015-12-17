package app.test.activity;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.hellojni.HelloJni;
import com.smartown.framework.base.BaseNotifyActivity;
import com.smartown.framework.tool.ApplicationTool;
import com.smartown.yitian.gogo.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

public class EntranceActivity extends BaseNotifyActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(bundle);
//        jump(TestFragment.class.getName());
        initContentView(R.layout.activity_entrance);
        init();
        findViews();
//        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        MobclickAgent.onPageStart(EntranceActivity.class.getName());
        ApplicationTool.showToast(HelloJni.stringFromJNI());
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        MobclickAgent.onPageEnd(EntranceActivity.class.getName());
    }

    @Override
    protected void init() {
        UmengUpdateAgent.update(this);
    }

    @Override
    protected void findViews() {
        initViews();
        registerViews();
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void registerViews() {
    }

}
