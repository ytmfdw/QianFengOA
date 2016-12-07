package oa.qianfeng.com.oa.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.entity.LeaveBean;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.impl.QueryService;
import oa.qianfeng.com.oa.utils.API;
import oa.qianfeng.com.oa.utils.Constant;
import oa.qianfeng.com.oa.utils.L;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/12/6.
 */
public class LeaveModel implements ILeaveModel {
    @Override
    public String getTitle(int type) {
        String value = "加班";
        switch (type) {
            case Constant.TYPE_LEFAVE: {
                value = "请假";
            }
            break;
            case Constant.TYPE_OVERTIME: {
                value = "加班";
            }
            break;
            case Constant.TYPE_SIGN: {
                value = "补签";
            }
            break;
        }
        return value;
    }

    @Override
    public void loadData(final OnGetDataListener<List<LeaveBean>> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(QFApp.getInstence().getHttpClient())
                .build();
        QueryService qs = retrofit.create(QueryService.class);
        Call<String> call = qs.getLeaveData();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (listener != null) {
                    String value = response.body();
                    L.d(value);
                    Document doc = Jsoup.parse(value);
                    Element table = doc.select("table").first();
                    Element tbody = table.select("tbody").first();
                    Elements trs = tbody.select("tr");
                    List<LeaveBean> data = new ArrayList<>();
                    for (Element e : trs) {
                        if (e.select("td").size() == 10) {
                            LeaveBean bean = new LeaveBean(e);
                            data.add(bean);
                        }
                    }
                    listener.onGetDataSuccess(data);
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
