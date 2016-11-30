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

    public void saveUser(UserBean user) {
        shared.edit().putString(Constant.SHARED_KEY_USER, user.toString()).commit();
    }

    public UserBean getUser() {
        String value = shared.getString(Constant.SHARED_KEY_USER, null);
        if (value == null) {
            return null;
        }
        try {
            String[] arr = value.split(":");
            UserBean user = new UserBean();
            user.account = arr[0];
            user.password = arr[1];
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
