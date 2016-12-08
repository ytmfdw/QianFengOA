package oa.qianfeng.com.oa.model;

import java.util.Map;

import oa.qianfeng.com.oa.entity.LeaveBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface IAskModel {
    public void loadData(int type, OnGetDataListener<Map<String, Object>> listener);

    public void postData(LeaveBean bean, OnGetDataListener<String> listener);

}
