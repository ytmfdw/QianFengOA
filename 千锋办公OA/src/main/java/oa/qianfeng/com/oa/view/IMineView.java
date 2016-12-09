package oa.qianfeng.com.oa.view;

import oa.qianfeng.com.oa.entity.MineBean;

/**
 * Created by Administrator on 2016/12/1 0001.
 */
public interface IMineView extends IBaseView {

    public void setMineData(MineBean mine);

    public void setTitle(String str);

    public void initViews();
}
