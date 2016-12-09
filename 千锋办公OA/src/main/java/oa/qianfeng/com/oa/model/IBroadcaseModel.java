package oa.qianfeng.com.oa.model;

import oa.qianfeng.com.oa.entity.BroadcastBean;
import oa.qianfeng.com.oa.entity.MsgBroadcastBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;

/**
 * Created by Administrator on 2016/12/9.
 */
public interface IBroadcaseModel {
    public void loadData(MsgBroadcastBean bean, OnGetDataListener<BroadcastBean> listener);
}
