package oa.qianfeng.com.oa.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.entity.BroadcastBean;
import oa.qianfeng.com.oa.entity.MsgBroadcastBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.impl.QueryService;
import oa.qianfeng.com.oa.utils.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/12/9.
 */
public class BroadcasdModel implements IBroadcaseModel {

    @Override
    public void loadData(MsgBroadcastBean bean, final OnGetDataListener<BroadcastBean> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(QFApp.getInstence().getHttpClient())
                .build();

        QueryService qs = retrofit.create(QueryService.class);
        Call<String> call = qs.getBroadcastData(bean.codeNum);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String value = response.body();

                Document doc = Jsoup.parse(value);
                Element content = doc.getElementsByClass("page-content").first();

                BroadcastBean bean = new BroadcastBean(content);
                if (listener != null) {
                    listener.onGetDataSuccess(bean);
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
