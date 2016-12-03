package oa.qianfeng.com.oa.presenter;

import oa.qianfeng.com.oa.model.AttendanceModel;
import oa.qianfeng.com.oa.model.IAttendanceModel;
import oa.qianfeng.com.oa.view.IAttendanceView;

/**
 * Created by Administrator on 2016/12/1.
 */
public class MinePresenter {
    IAttendanceView view;
    IAttendanceModel model;

    public MinePresenter(IAttendanceView view) {
        this.view = view;
        model = new AttendanceModel();
    }

    public void setTitle() {
        view.setTitle();
    }
}
