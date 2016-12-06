package oa.qianfeng.com.oa.model;

import java.util.List;

import oa.qianfeng.com.oa.entity.LeaveBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface ILeaveModel {
    public String getTitle(int type);

    public void loadData(OnGetDataListener<List<LeaveBean>> listener);
}
