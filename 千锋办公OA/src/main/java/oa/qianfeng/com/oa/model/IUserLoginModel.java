package oa.qianfeng.com.oa.model;

import android.content.Context;

import oa.qianfeng.com.oa.entity.UserBean;

/**
 * Created by Administrator on 2016/11/30.
 */
public interface IUserLoginModel {
    /**
     * 保存用户
     *
     * @param user
     */
    public void saveUser(UserBean user);

    /**
     * 获取用户
     *
     * @param context
     * @return
     */
    public UserBean getUser(Context context);

    public String getUserName(Context context);

    public String getUserPassword(Context context);
}
