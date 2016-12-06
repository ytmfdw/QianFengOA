package oa.qianfeng.com.oa.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.R;
import oa.qianfeng.com.oa.entity.UserBean;
import oa.qianfeng.com.oa.impl.OnLoginListener;
import oa.qianfeng.com.oa.presenter.UserLoginPresenter;
import oa.qianfeng.com.oa.utils.SharedUtils;
import oa.qianfeng.com.oa.view.IUserLoginView;

/**
 * 登录界面
 */
public class LoginActivty extends BaseNetActivity implements IUserLoginView, OnLoginListener {


    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_password)
    EditText etPassword;

    UserLoginPresenter presenter;
    @BindView(R.id.cb_save)
    CheckBox cbSave;
    @BindView(R.id.cb_auto)
    CheckBox cbAuto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new UserLoginPresenter(this, this);
        //初始化界面
//        presenter.setUser();
        presenter.initViews();

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

    @Override
    public void setSavePassword(boolean falg) {
        cbSave.setChecked(falg);
    }

    @Override
    public void setAutoLogin(boolean falg) {
        cbAuto.setChecked(falg);
    }

    public void doLogin(View view) {
        showLoading();
        UserBean user = new UserBean();
        user.account = etName.getText().toString();
        user.password = etPassword.getText().toString();
        presenter.login(user, this);
    }

    @Override
    public void loginSuccess(UserBean user) {
        dismissLoading();
        Toast.makeText(this, "登录成功!", Toast.LENGTH_LONG).show();
        //保存一份到app中
        QFApp.user = user;
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

    @OnClick({R.id.cb_save, R.id.cb_auto})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_save:
                SharedUtils.getInstances().setSavePassword(cbSave.isChecked());
                break;
            case R.id.cb_auto:
                SharedUtils.getInstances().setAutoLogin(cbAuto.isChecked());
                break;
        }
    }
}
