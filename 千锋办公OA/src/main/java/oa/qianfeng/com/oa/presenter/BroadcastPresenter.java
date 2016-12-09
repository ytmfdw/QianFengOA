package oa.qianfeng.com.oa.presenter;

import oa.qianfeng.com.oa.entity.BroadcastBean;
import oa.qianfeng.com.oa.entity.MsgBroadcastBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.model.BroadcasdModel;
import oa.qianfeng.com.oa.model.IBroadcaseModel;
import oa.qianfeng.com.oa.view.IBroadcasdView;

/**
 * Created by Administrator on 2016/12/9.
 */
public class BroadcastPresenter implements OnGetDataListener<BroadcastBean> {
    IBroadcasdView view;
    IBroadcaseModel model;

    public BroadcastPresenter(IBroadcasdView view) {
        this.view = view;
        model = new BroadcasdModel();

        view.setupViews();
    }

    public void loadData(MsgBroadcastBean bean) {
        view.showLoading();
        model.loadData(bean, this);
    }

    @Override
    public void onGetDataSuccess(BroadcastBean value) {
        view.dismissLoading();
        view.setData(value);
    }

    @Override
    public void onGetDataFaild() {
        view.dismissLoading();
    }
}
