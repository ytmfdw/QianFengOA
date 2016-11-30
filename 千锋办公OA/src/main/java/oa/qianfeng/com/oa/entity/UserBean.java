package oa.qianfeng.com.oa.entity;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
public class UserBean {

    public String name;
    public String id;

    public String account;
    public String password;

    @Override
    public String toString() {
        return account + ":" + password;
    }
}
