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
     * 退出登录
     */
    public static final String URL_LOGINOUT = "oa.php/Admin/logout";
    /**
     * 查看考勤
     */
    public static final String URL_CLOCK = "oa.php/Group/PerAttRecords/p/{page}";

    /**
     * 我的界面
     */
    public static final String URL_MINE = "oa.php/User/editUserInfo";

    /**
     * 公告消息
     */
    public static final String URL_MESSAGE = "oa.php/Index/index";

    /**
     * 公告详情
     */
    public static final String URL_BROADCAST_DETAIL = "oa.php/Index/broadcast_detail";

    /**
     * 请假、加班、补签列表
     */
    public static final String URL_LEAVE = "oa.php/Atten/LeaveList";

}
