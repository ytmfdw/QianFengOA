package oa.qianfeng.com.oa.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.entity.LeaveBean;
import oa.qianfeng.com.oa.impl.AskService;
import oa.qianfeng.com.oa.impl.OnGetDataListener;
import oa.qianfeng.com.oa.utils.API;
import oa.qianfeng.com.oa.utils.Constant;
import oa.qianfeng.com.oa.utils.L;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2016/12/7.
 */
public class AskModel implements IAskModel {
    @Override
    public void loadData(final int type, final OnGetDataListener<Map<String, Object>> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(QFApp.getInstence().getHttpClient())
                .build();
        AskService as = retrofit.create(AskService.class);
        Call<String> call = as.getData(getType(type));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String value = response.body();
                L.d(value);
                Document doc = Jsoup.parse(value);

                Map<String, Object> map = new HashMap<>();
                //解析领导
                List<String> bosses = new ArrayList<>();
                Element leader = doc.getElementById("leader_id");

                Elements option = leader.select("option");
                for (Element e : option) {
                    String attr = e.attr("value");
                    if (!attr.equals("0")) {
                        bosses.add(e.text());
                        QFApp.getInstence().getLeaderMap().put(e.text(), attr);
                    }
                }
                map.put(Constant.MAP_KEY_LIST_BOSS, bosses);
                //解析请假事由
                List<String> reason = new ArrayList<>();
                Element myreason = doc.getElementById("myreason");

                Elements reason_option = myreason.select("option");
                for (Element e : reason_option) {
                    String attr = e.attr("value");
                    if (!attr.equals("0")) {
                        reason.add(e.text());
                        QFApp.getInstence().getReasonMap().put(e.text(), attr);
                    }
                }
                map.put(Constant.MAP_KEY_LIST_REASON, reason);

                //解析实体
                LeaveBean bean = new LeaveBean();


                Elements content = doc.getElementsByClass("form-group");
                bean.leaveType = type;
                bean.strType = getStrType(type);
                //姓名
                bean.name = content.get(0).getElementsByClass("middle").first().text();
                //部门
                bean.department = content.get(1).getElementsByClass("middle").first().text();
                //group_id
                Elements input = content.get(1).select("input");
                for (Element e : input) {
                    if (e.hasAttr("name") && e.attr("name").equals("group_id")) {
                        bean.group_id = e.attr("value");
                    }
                }
                //审批人
                //请假理由
                //时间
                //共多少小时
                //备注

                map.put(Constant.MAP_KEY_BEAN, bean);
                if (listener != null) {
                    listener.onGetDataSuccess(map);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private String getType(int type) {
        switch (type) {
            case Constant.TYPE_LEFAVE: {
                return "leave";
            }
            case Constant.TYPE_OVERTIME: {
                return "work";
            }
            case Constant.TYPE_SIGN: {
                return "check";
            }
        }
        return "work";
    }

    public String getStrType(int type) {
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
}
