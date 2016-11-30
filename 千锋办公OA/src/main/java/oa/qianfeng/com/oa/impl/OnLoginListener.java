package oa.qianfeng.com.oa.impl;

import oa.qianfeng.com.oa.entity.UserBean;

/**
 * Created by Administrator on 2016/11/30.
 */
public interface OnLoginListener {
    public void loginSuccess(UserBean user);

    public void loginFailed();
}
