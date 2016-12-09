package oa.qianfeng.com.oa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.TextView;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.entity.UserBean;
import oa.qianfeng.com.oa.impl.LoginService;
import oa.qianfeng.com.oa.utils.API;
import oa.qianfeng.com.oa.utils.L;
import oa.qianfeng.com.oa.utils.SharedUtils;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/11/30.
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (SharedUtils.getInstances().isAutoLogin()) {
            //自动登录
            UserBean user = SharedUtils.getInstances().getUser();
            if (user != null) {
                login(user);
            } else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(WelcomeActivity.this, LoginActivty.class);
                        startActivity(intent);
                        finish();
                    }
                }, 2000);
            }
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivty.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);

        }
    }

    private void login(final UserBean user) {
        OkHttpClient okHttpClient = QFApp.getInstence().getHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(okHttpClient)
                .build();
        LoginService ls = retrofit.create(LoginService.class);
        Call<String> loginCall = ls.login(user.account, user.password);
        loginCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String value = response.body().trim();
                L.d("登录结果：value=" + value);
                String reg = "[^\u4e00-\u9fa5]";
                value = value.replaceAll(reg, "");
                L.d("登录结果：value=" + value);
                if (!value.contains("失败") && !value.contains("删除")) {
                    //登录成功，跳转到主页
                    QFApp.user = user;
                    //保存user
                    //跳转到主页
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, LoginActivty.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivty.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
