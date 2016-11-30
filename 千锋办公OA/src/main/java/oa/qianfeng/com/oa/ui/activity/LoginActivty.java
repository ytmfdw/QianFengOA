package oa.qianfeng.com.oa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.entity.UserBean;
import oa.qianfeng.com.oa.impl.OnLoginListener;
import oa.qianfeng.com.oa.presenter.UserLoginPresenter;
import oa.qianfeng.com.oa.view.IUserLoginView;

/**
 * 登录界面
 */
public class LoginActivty extends BaseNetActivity implements IUserLoginView, OnLoginListener {


    @butterknife.BindView(R.id.et_name)
    android.widget.EditText etName;
    @butterknife.BindView(R.id.et_password)
    android.widget.EditText etPassword;

    UserLoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new UserLoginPresenter(this, this);
        //初始化界面
        presenter.setUser();

    }

    @Override
    public void setUserName(String name) {
        etName.setText(name);
    }

    @Override
    public void setUserPasswrod(String passrod) {
        etPassword.setText(passrod);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    public void doLogin(View view) {
        showLoading();
        UserBean user = new UserBean();
        user.name = etName.getText().toString();
        user.password = etPassword.getText().toString();
        presenter.login(user, this);
    }

    @Override
    public void loginSuccess(UserBean user) {
        dismissLoading();
        Toast.makeText(this, "登录成功!", Toast.LENGTH_LONG).show();
        //保存user
        presenter.saveUser(user);
        //跳转到主页
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void loginFailed() {
        dismissLoading();
        Toast.makeText(this, "登录失败!", Toast.LENGTH_LONG).show();
    }
}
