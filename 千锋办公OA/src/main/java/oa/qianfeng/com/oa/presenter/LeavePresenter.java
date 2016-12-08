package oa.qianfeng.com.oa.presenter;

import java.util.List;

import oa.qianfeng.com.oa.entity.LeaveBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.model.ILeaveModel;
import oa.qianfeng.com.oa.model.LeaveModel;
import oa.qianfeng.com.oa.view.ILeaveView;

/**
 * Created by Administrator on 2016/12/6.
 */
public class LeavePresenter {
    ILeaveModel model;
    ILeaveView view;

    public LeavePresenter(ILeaveView view) {
        this.view = view;
        model = new LeaveModel();
    }

    public void init(int type) {
        setTitle(type);
        view.init();
    }

    public void setTitle(int type) {
        view.setTitle(model.getTitle(type));
    }

    public void loadData(int type, OnGetDataListener<List<LeaveBean>> listener) {
        model.loadData(type, listener);
    }
}
