package oa.qianfeng.com.oa.view;

import oa.qianfeng.com.oa.entity.LeaveBean;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface IAskView {

    public void showLoading();

    public void dissmissLoading();

    public void setupViews(LeaveBean bean);

    public LeaveBean getLeaveBean();

    public void showSuccess(String str);

    public void showFaild();
}
