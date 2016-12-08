package oa.qianfeng.com.oa.view;

import java.util.List;

import oa.qianfeng.com.oa.entity.LeaveBean;
import oa.qianfeng.com.oa.widget.EmptyRecyclerView;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface ILeaveView {


    public void initRecyView(EmptyRecyclerView rv);

    public void setTitle(String type);

    public void init();

    public void showLoading();

    public void dismissLoading();

    public void showSuccess(String str);

    public void showFaild(String str);

    public void setListBean(List<LeaveBean> list);
}
