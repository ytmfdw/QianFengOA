package oa.qianfeng.com.oa.model;

import oa.qianfeng.com.oa.impl.OnGetDataListener;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public interface IMessageModel {
    public String getTitle();

    public void loadData(OnGetDataListener<String> listener);
}
