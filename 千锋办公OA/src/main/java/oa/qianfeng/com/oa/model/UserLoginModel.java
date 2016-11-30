package oa.qianfeng.com.oa.model;

import android.content.Context;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.entity.UserBean;
import oa.qianfeng.com.oa.utils.SharedUtils;

/**
 * Created by Administrator on 2016/11/30.
 */
public class UserLoginModel implements IUserLoginModel {
    @Override
    public void saveUser(UserBean user) {
        SharedUtils.getInstances().saveUser(user);
    }

    @Override
    public UserBean getUser(Context context) {
        return SharedUtils.getInstances().getUser();
    }

    @Override
    public String getUserName(Context context) {
        UserBean user = getUser(context);
        if (user != null) {
            return user.name;
        }
        return "";
    }

    @Override
    public String getUserPassword(Context context) {
        UserBean user = getUser(context);
        if (user != null) {
            return user.password;
        }
        return "";
    }
}
