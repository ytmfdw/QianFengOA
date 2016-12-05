package oa.qianfeng.com.oa.presenter;

import oa.qianfeng.com.oa.entity.MineBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.model.IMineModel;
import oa.qianfeng.com.oa.model.MineModel;
import oa.qianfeng.com.oa.utils.L;
import oa.qianfeng.com.oa.view.IMineView;

/**
 * Created by Administrator on 2016/12/1.
 */
public class MinePresenter {
    IMineView view;
    IMineModel model;

    public MinePresenter(IMineView view) {
        this.view = view;
        model = new MineModel();
    }

    public void initViews() {
        view.initViews();
    }

    public void setTitle() {
        view.setTitle();
    }

    public void getData() {
        model.getMineData(new OnGetDataListener<MineBean>() {
            @Override
            public void onGetDataSuccess(MineBean value) {
                L.d(value.toString());
                view.setMineData(value);
            }

            @Override
            public void onGetDataFaild() {

            }
        });
    }
}
