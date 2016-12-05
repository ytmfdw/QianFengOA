package oa.qianfeng.com.oa.model;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.entity.MineBean;
import oa.qianfeng.com.oa.impl.MineService;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.utils.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/12/1.
 */
public class MineModel implements IMineModel {
    @Override
    public String getTitle() {
        return "我的";
    }

    @Override
    public void getMineData(final OnGetDataListener<MineBean> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(QFApp.getInstence().getHttpClient())
                .build();

        MineService ms = retrofit.create(MineService.class);
        Call<String> call = ms.getMineData();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (listener != null) {
                    Document doc = Jsoup.parse(response.body());
                    Element e = doc.getElementsByClass("page-content").first();
                    MineBean mine = new MineBean(e);
                    listener.onGetDataSuccess(mine);
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
