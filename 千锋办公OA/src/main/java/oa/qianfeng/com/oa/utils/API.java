package oa.qianfeng.com.oa.utils;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public class API {
    /**
     * 主机地址
     */
    public static final String URL_BASE = "http://oa.1000phone.net/";
    /**
     * 登录
     */
    public static final String URL_LOGIN = "oa.php/Admin/login/";
    /**
     * 查看考勤
     */
    public static final String URL_CLOCK = "oa.php/Group/PerAttRecords/p/{page}";

    /**
     * 我的界面
     */
    public static final String URL_MINE = "oa.php/User/editUserInfo";

}
