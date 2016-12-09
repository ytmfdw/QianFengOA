package oa.qianfeng.com.oa.utils;

import android.content.Context;
import android.content.SharedPreferences;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.entity.UserBean;

/**
 * Created by Administrator on 2016/10/18 0018.
 */
public class SharedUtils {
    private static SharedPreferences shared;
    private static SharedUtils utils;

    private SharedUtils() {
    }

    public static SharedUtils getInstances() {
        if (shared == null) {
            shared = QFApp.getInstence().getSharedPreferences(Constant.SHARED_NAME, Context.MODE_PRIVATE);
        }
        if (utils == null) {
            utils = new SharedUtils();
        }
        return utils;
    }

    public boolean isSavePassword() {
        return shared.getBoolean(Constant.SHARED_KEY_SAVEPASSWORD, false);
    }

    public void setSavePassword(boolean flag) {
        shared.edit().putBoolean(Constant.SHARED_KEY_SAVEPASSWORD, flag).commit();
    }

    public boolean isAutoLogin() {
        return shared.getBoolean(Constant.SHARED_KEY_AUTOLOGIN, false);
    }

    public void setAutoLogin(boolean flag) {
        shared.edit().putBoolean(Constant.SHARED_KEY_AUTOLOGIN, flag).commit();
    }

    public void saveUser(UserBean user) {
        if (isSavePassword()) {
            shared.edit().putString(Constant.SHARED_KEY_USER, user.toString()).commit();
        } else {
            //只保存账号
            shared.edit().putString(Constant.SHARED_KEY_USER, user.account).commit();
        }
    }

    public UserBean getUser() {
        String value = shared.getString(Constant.SHARED_KEY_USER, null);
        if (value == null) {
            return null;
        }
        try {
            String[] arr = value.split(":");
            UserBean user = new UserBean();
            if (arr.length == 1) {
                user.account = arr[0];
                user.password = "";
            } else if (arr.length == 2) {
                user.account = arr[0];
                user.password = arr[1];
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否是第一次运行申请列表
     *
     * @return
     */
    public boolean isFirstRunLeavList() {
        return shared.getBoolean(Constant.SHARED_KEY_FIRST_LEAVE, true);
    }

    /**
     * 保存第一次启动申请列表
     *
     * @param flag
     */
    public void saveFirstRunLeaveList(boolean flag) {
        shared.edit().putBoolean(Constant.SHARED_KEY_FIRST_LEAVE, flag).commit();
    }

}
