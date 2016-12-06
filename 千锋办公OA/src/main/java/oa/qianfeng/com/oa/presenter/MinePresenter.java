package oa.qianfeng.com.oa.presenter;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.entity.MineBean;
import oa.qianfeng.com.oa.impl.MineService;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.model.IMineModel;
import oa.qianfeng.com.oa.model.MineModel;
import oa.qianfeng.com.oa.utils.API;
import oa.qianfeng.com.oa.utils.L;
import oa.qianfeng.com.oa.view.IMineView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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

    public void loginOut(final OnGetDataListener<String> listener) {
        //退出登录
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(QFApp.getInstence().getHttpClient())
                .build();
        MineService ms = retrofit.create(MineService.class);
        Call<String> call = ms.loginOut();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String value = response.body();
                L.d(value);
//                String reg = "[^\u4e00-\u9fa5]";
//                value = value.replaceAll(reg, "");
                if (listener != null) {

                    if (value.contains("登录")) {
                        listener.onGetDataSuccess("退出成功");
                    } else {
                        listener.onGetDataFaild();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (listener != null) {
                    listener.onGetDataFaild();
                }
            }
        });
    }
}
