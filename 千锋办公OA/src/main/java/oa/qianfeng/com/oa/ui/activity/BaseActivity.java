package oa.qianfeng.com.oa.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.LinearLayout;

import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.utils.Constant;
import oa.qianfeng.com.oa.utils.DisplayUtils;
import oa.qianfeng.com.oa.utils.IntentUtils;

/**
 * Created by Administrator on 2016/11/30.
 */
public abstract class BaseActivity extends AppCompatActivity {

    int animType = Constant.ANIM_LEFT_IN_RIGHT_OUT;

    boolean isKitkat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 沉浸 状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 沉浸 虚拟按键
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            isKitkat = true;
        }
        super.onCreate(savedInstanceState);
        setLayout();
        setToolBar();
        initViews();
        //没有ActionBar
//        getSupportActionBar().hide();
        animType = getIntent().getIntExtra(IntentUtils.INTENT_KEY_ANIMTYPE, Constant.ANIM_LEFT_IN_RIGHT_OUT);
    }

    /**
     * 初始化界面
     */
    public void initViews() {
    }

    /**
     * setContentView
     * 初始化界面
     */
    public void setLayout() {
    }

    /**
     * 在setContentView之后调用
     */
    public void setToolBar() {
        if (isKitkat) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar == null) {
                return;
            }
            int statuesHeight = DisplayUtils.getStatusHeight(this);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
            params.height = params.height + statuesHeight;
            toolbar.setPadding(0, statuesHeight, 0, 0);
            toolbar.setLayoutParams(params);
            //设置底部
        }
    }

    ;


    @Override
    public void startActivity(Intent intent) {
        intent.putExtra(IntentUtils.INTENT_KEY_ANIMTYPE, Constant.ANIM_LEFT_IN_RIGHT_OUT);
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    @Override
    public void finish() {
        super.finish();
        switch (animType) {
            case Constant.ANIM_LEFT_IN_RIGHT_OUT: {
//                overridePendingTransition(0, 0);
            }
            break;
            case Constant.ANIM_RIGHT_IN_LEFT_OUT: {
                overridePendingTransition(0, R.anim.slide_out_to_right);
            }
            break;
        }
    }
}
