package oa.qianfeng.com.oa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import oa.qianfeng.com.oa.utils.Constant;
import oa.qianfeng.com.oa.utils.IntentUtils;

/**
 * Created by Administrator on 2016/11/30.
 */
public class BaseActivity extends AppCompatActivity {

    int animType = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //没有ActionBar
        getSupportActionBar().hide();
        animType = getIntent().getIntExtra(IntentUtils.INTENT_KEY_ANIMTYPE, Constant.ANIM_LEFT_IN_RIGHT_OUT);
    }

    @Override
    public void startActivity(Intent intent) {
        intent.putExtra(IntentUtils.INTENT_KEY_ANIMTYPE, Constant.ANIM_LEFT_IN_RIGHT_OUT);
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        switch (animType) {
            case Constant.ANIM_LEFT_IN_RIGHT_OUT: {
                overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);
            }
            break;
            case Constant.ANIM_RIGHT_IN_LEFT_OUT: {
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
            break;
        }
    }
}
