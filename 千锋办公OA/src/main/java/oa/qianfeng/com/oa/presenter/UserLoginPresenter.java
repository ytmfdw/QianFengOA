package oa.qianfeng.com.oa.presenter;

import android.content.Context;
import android.util.Log;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.entity.UserBean;
import oa.qianfeng.com.oa.impl.LoginService;
import oa.qianfeng.com.oa.impl.OnLoginListener;
import oa.qianfeng.com.oa.model.IUserLoginModel;
import oa.qianfeng.com.oa.model.UserLoginModel;
import oa.qianfeng.com.oa.utils.API;
import oa.qianfeng.com.oa.view.IUserLoginView;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/11/30.
 */
public class UserLoginPresenter {
    private IUserLoginView view;
    private IUserLoginModel model;

    Context context;

    public UserLoginPresenter(Context context, IUserLoginView view) {
        this.view = view;
        this.context = context;
        model = new UserLoginModel();
    }

    public void saveUser(UserBean user) {
        model.saveUser(user);
    }

    /**
     * 界面显示用户账号和密码
     */
    public void setUser() {
        view.setUserName(model.getUserName(context));
        view.setUserPasswrod(model.getUserPassword(context));
    }

    public UserBean getUser() {
        return model.getUser(context);
    }

    /**
     * 登录
     *
     * @param user
     */
    public void login(final UserBean user, final OnLoginListener listener) {
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
                String reg = "[^\u4e00-\u9fa5]";
                value = value.replaceAll(reg, "");
                Log.d("ytmfdw", "登录结果：value=" + value);
                if (!value.contains("失败") && !value.contains("删除")) {
                    if (listener != null) {
                        listener.loginSuccess(user);
                    }
                } else {
                    listener.loginFailed();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.loginFailed();
            }
        });
    }
}
