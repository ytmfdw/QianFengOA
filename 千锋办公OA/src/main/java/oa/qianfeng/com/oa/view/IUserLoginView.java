package oa.qianfeng.com.oa.view;

/**
 * Created by Administrator on 2016/11/30.
 */
public interface IUserLoginView {
    public void setUserName(String name);

    public void setUserPasswrod(String passrod);

    public void showLoading();

    public void dismissLoading();
}
