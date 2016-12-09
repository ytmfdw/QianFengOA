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

    public void loadData(LeaveBean bean, int type, OnGetDataListener<Map<String, Object>> listener) {
        if (bean == null) {
            model.loadData(type, listener);
        } else {
            model.loadEditData(bean, listener);
        }
    }


    public void postAsk(LeaveBean bean) {
        view.showLoading();
        if (bean.state == 2) {
            //待批复状态，是编辑状态
            model.postEditData(bean, new OnGetDataListener<String>() {
                @Override
                public void onGetDataSuccess(String value) {
                    view.dismissLoading();
                    view.showSuccess(value);
                }

                @Override
                public void onGetDataFaild() {
                    view.dismissLoading();
                    view.showFaild();
                }
            });
        } else {
            model.postData(bean, new OnGetDataListener<String>() {
                @Override
                public void onGetDataSuccess(String value) {
                    view.dismissLoading();
                    view.showSuccess(value);
                }

                @Override
                public void onGetDataFaild() {
                    view.dismissLoading();
                    view.showFaild();
                }
            });
        }

    }

}
