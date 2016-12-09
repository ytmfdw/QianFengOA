package oa.qianfeng.com.oa.presenter;

import java.util.List;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.entity.KaoQinAllBean;
import oa.qianfeng.com.oa.entity.KaoQinBean;
import oa.qianfeng.com.oa.entity.UserBean;
import oa.qianfeng.com.oa.impl.MineService;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.impl.OnLoadDataListener;
import oa.qianfeng.com.oa.impl.QueryService;
import oa.qianfeng.com.oa.model.AttendanceModel;
import oa.qianfeng.com.oa.model.IAttendanceModel;
import oa.qianfeng.com.oa.utils.API;
import oa.qianfeng.com.oa.utils.HtmlUtils;
import oa.qianfeng.com.oa.utils.L;
import oa.qianfeng.com.oa.view.IAttendanceView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Administrator on 2016/12/1.
 */
public class AttendancePresenter {
    IAttendanceView view;
    IAttendanceModel model;

    public AttendancePresenter(IAttendanceView view) {
        this.view = view;
        model = new AttendanceModel();
    }

    public void loadData(int page, final OnLoadDataListener listener) {
        view.showLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.URL_BASE)
                .client(QFApp.getInstence().getHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        QueryService qs = retrofit.create(QueryService.class);
        Call<String> queryCall = qs.query(page);

        queryCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                UserBean user = QFApp.user;
                String value = response.body().trim();
                L.d("ytmfdw", "查询结果：" + value);
                //考勤汇总
                if (model.getAllBean() == null) {
                    KaoQinAllBean allBean = HtmlUtils.getKaoQinAllBeanByHtml(value);
                    model.setAllBean(allBean);
                }
                //考勤数据
                List<KaoQinBean> data = HtmlUtils.getKaoQinBeanByHtml(value);
                view.setData(data);
                view.setDetail(model.getAllBean());
                view.setTitle();
                if (listener != null) {
                    listener.getDataSuccess(model.getAllBean(), data);
                }

                view.dismissLoading();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                view.dismissLoading();
                if (listener != null) {
                    listener.getDataFaild();
                }
            }
        });
    }

    public String getTitle() {
        return model.getTitle();
    }

    public void setTitle() {
        view.setTitle();
    }

    public String getDetail(KaoQinAllBean all) {
        return model.getDetail(all);
    }

    public void initViews() {
        view.initViews();
    }

    public void showDetail() {
        view.showDetail(model.getAllBean());
    }

}
