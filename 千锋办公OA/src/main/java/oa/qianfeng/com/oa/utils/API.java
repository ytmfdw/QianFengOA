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

    /**
     * 申请URL，提交申请
     *
     * @Query("type") String type
     * <p/>
     * type=work      加班
     * <p/>
     * type=leave                       请假
     * <p/>
     * type=check                       补签
     */
    public static final String URL_ASK = "oa.php/Atten/Askforleave";

    /**
     * 删除未批复的申请
     * ?ID="+id+"&user_id="+user_id;
     * get请求
     * ID
     * user_id
     */
    public static final String URL_DEL_LEAVE = "oa.php/Atten/delLeave";

    /**
     * 编辑申请
     * <p/>
     * http://oa.1000phone.net/oa.php/Atten/eidtAskforleave?user_id=15040&ID=10077&type=
     */
    public static final String URL_ASK_EDIT = "oa.php/Atten/eidtAskforleave?";

}
