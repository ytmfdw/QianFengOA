package oa.qianfeng.com.oa.utils;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class Constant {
    //===========================动画===================
    /**
     * 左进右出动画
     */
    public final static int ANIM_LEFT_IN_RIGHT_OUT = 0;
    /**
     * 右进左出动画
     */
    public final static int ANIM_RIGHT_IN_LEFT_OUT = 1;

    //====================共享参数===================
    public final static String SHARED_NAME = "shared_utils";
    /**
     * 共享参数 user key
     */
    public final static String SHARED_KEY_USER = "user";
    public final static String SHARED_KEY_AUTOLOGIN = "autoLogin";
    public final static String SHARED_KEY_SAVEPASSWORD = "savePassword";


    //==============消息================
    /**
     * 更新界面消息
     */
    public final static int MSG_UPDATE_UI = 0x1000;
    //=================加班、请假、补签


    /**
     * 加班
     *
     * 请求reason:value=
     */
    public final static int TYPE_OVERTIME = 1;
    /**
     * 请假
     */
    public final static int TYPE_LEFAVE = 2;
    /**
     * 补签
     */
    public final static int TYPE_SIGN = 3;

    //请假状态
    /**
     * 已批准
     */
    public final static int STATE_ALLOWED = 1;
    /**
     * 待批复
     */
    public final static int STATE_WAIT = 2;


    /**
     * 提交成功
     */
    public final static int SUBMMIT_SUCCESS = 100;

    /**
     * 取消提交
     */
    public final static int SUBMMIT_CANCEL = 0;

    /**
     * Map的key-->bean
     */
    public final static String MAP_KEY_BEAN = "bean";
    /**
     * Map的key-->领导
     */
    public final static String MAP_KEY_LIST_BOSS = "list_boss";
    /**
     * Map的key-->请假事由
     */
    public final static String MAP_KEY_LIST_REASON = "list_reason";
}
