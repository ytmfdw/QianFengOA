package oa.qianfeng.com.oa.presenter;

import android.os.Message;

import java.util.List;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.entity.LeaveBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.model.ILeaveModel;
import oa.qianfeng.com.oa.model.LeaveModel;
import oa.qianfeng.com.oa.utils.Constant;
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

    public void loadData(int type) {
        model.loadData(type, new OnGetDataListener<List<LeaveBean>>() {
            @Override
            public void onGetDataSuccess(List<LeaveBean> value) {
                view.dismissLoading();
                view.setListBean(value);
            }

            @Override
            public void onGetDataFaild() {
                view.dismissLoading();
                view.showFaild("获取数据失败");
            }
        });
    }

    public void delLeave(final LeaveBean bean) {
        view.showLoading();
        model.delLeave(bean, new OnGetDataListener<String>() {
            @Override
            public void onGetDataSuccess(String value) {
                view.showSuccess(value);
                loadData(bean.leaveType);
                Message msg = Message.obtain();
                msg.what = Constant.MSG_GET_MSGDATA;
                QFApp.getInstence().getMsgBus().post(msg);
            }

            @Override
            public void onGetDataFaild() {
                view.dismissLoading();
                view.showFaild("删除失败");
            }
        });
    }
}
