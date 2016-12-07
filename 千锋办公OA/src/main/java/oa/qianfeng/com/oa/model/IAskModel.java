package oa.qianfeng.com.oa.model;

import java.util.Map;

import oa.qianfeng.com.oa.impl.OnGetDataListener;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface IAskModel {
    public void loadData(OnGetDataListener<Map<String, Object>> listener);
}
