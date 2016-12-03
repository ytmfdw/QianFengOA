package oa.qianfeng.com.oa.view;

import java.util.List;

import oa.qianfeng.com.oa.entity.KaoQinAllBean;
import oa.qianfeng.com.oa.entity.KaoQinBean;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public interface IAttendanceView {

    public void showLoading();

    public void dismissLoading();

    public void setTitle();

    public void initViews();

    public void showDetail(KaoQinAllBean all);

    public void setData(List<KaoQinBean> data);

    public void setDetail(KaoQinAllBean all);
}
