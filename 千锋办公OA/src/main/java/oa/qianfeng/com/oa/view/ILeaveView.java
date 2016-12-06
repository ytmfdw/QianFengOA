package oa.qianfeng.com.oa.view;

import oa.qianfeng.com.oa.widget.EmptyRecyclerView;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface ILeaveView {
    public void initRecyView(EmptyRecyclerView rv);

    public void setTitle(String type);

    public void init();
}
