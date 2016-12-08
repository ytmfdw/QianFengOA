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

    @Override
    public void postData(LeaveBean bean, final OnGetDataListener<String> listener) {
        //提交请求
        String startData = "";
        String startHour = "0";
        String startMin = "0";
        String endData = "";
        String endHour = "0";
        String endMin = "0";
        //补签
        String starData_ = "";
        String hour_ = "0";
        String min_ = "0";
        if (bean.strType.contains("补签")) {
            try {
                String[] tmp = bean.duration.split(" ");
                starData_ = tmp[0];
                String[] HM = tmp[1].split(":");
                hour_ = HM[0];
                min_ = HM[1];
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                String[] tmp = bean.duration.split("至");
                //开始
                String[] start = tmp[0].split(" ");
                startData = start[0];
                String[] startHM = start[1].split(":");
                startHour = startHM[0];
                startMin = startHM[1];
                //结束
                String[] end = tmp[1].split(" ");
                endData = end[0];
                String[] emdHM = end[1].split(":");
                endHour = emdHM[0];
                endMin = emdHM[1];

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(QFApp.getInstence().getHttpClient())
                .build();
        AskService as = retrofit.create(AskService.class);
        Call<String> call = as.postAsk(
                bean.id,
                bean.group_id,
                QFApp.getInstence().getLeaderMap().get(bean.boss),
                "",
                QFApp.getInstence().getReasonMap().get(bean.strType),
                startData,
                startHour,
                startMin,
                endData,
                endHour,
                endMin,
                starData_,
                hour_,
                min_,
                bean.hours,
                bean.mark
        );

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                if (listener != null) {
                    if (result.contains("成功")) {
                        listener.onGetDataSuccess("提交成功");
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
