package oa.qianfeng.com.oa.presenter;

import java.util.Map;

import oa.qianfeng.com.oa.entity.LeaveBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.model.AskModel;
import oa.qianfeng.com.oa.model.IAskModel;
import oa.qianfeng.com.oa.view.IAskView;

/**
 * Created by Administrator on 2016/12/7.
 */
public class AskPresenter {
    IAskView view;
    IAskModel model;

    public AskPresenter(IAskView view) {
        this.view = view;
        model = new AskModel();
        if (view.getLeaveBean() != null) {
            view.setupViews(view.getLeaveBean());
        }
    }

    public void loadData(int type, OnGetDataListener<Map<String, Object>> listener) {
        model.loadData(type, listener);
    }



    public void postAsk(LeaveBean bean) {
        view.showLoading();
        model.postData(bean, new OnGetDataListener<String>() {
            @Override
            public void onGetDataSuccess(String value) {
                view.dissmissLoading();
                view.showSuccess(value);
            }

            @Override
            public void onGetDataFaild() {
                view.dissmissLoading();
                view.showFaild();
            }
        });
    }

}
