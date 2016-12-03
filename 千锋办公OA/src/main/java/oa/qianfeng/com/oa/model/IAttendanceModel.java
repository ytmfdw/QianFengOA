package oa.qianfeng.com.oa.model;

import oa.qianfeng.com.oa.entity.KaoQinAllBean;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public interface IAttendanceModel {
    public String getTitle();

    public String getDetail(KaoQinAllBean all);

    public KaoQinAllBean getAllBean();

    public void setAllBean(KaoQinAllBean all);

}
