package oa.qianfeng.com.oa.impl;

import java.util.List;

import oa.qianfeng.com.oa.entity.KaoQinAllBean;
import oa.qianfeng.com.oa.entity.KaoQinBean;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public interface OnLoadDataListener {
    public void getDataSuccess(KaoQinAllBean all, List<KaoQinBean> bean);

    public void getDataFaild();
}
