package oa.qianfeng.com.oa.impl;

import oa.qianfeng.com.oa.utils.API;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface AskService {
    @GET(API.URL_ASK)
    Call<String> getData(@Query("type") String type);

    @GET
    Call<String> editAdk(@Url String url);

    @FormUrlEncoded
    @POST
    Call<String> postEditAsk(@Url String url, @Field("id") String id,
                             @Field("group_id") String group_id,
                             @Field("leader_id[]") String leader_id,
                             @Field("daoxiu") String daoxiu,
                             @Field("reason") String reason,
                             @Field("starDate") String starDate,
                             @Field("starTime[hour]") String starTimeHour,
                             @Field("starTime[min]") String starTimeMin,
                             @Field("endDate") String endDate,
                             @Field("endTime[hour]") String endTimeHour,
                             @Field("endTime[min]") String endTimeMin,
                             @Field("starDate_") String starDate_,
                             @Field("hour_") String hour_,
                             @Field("min_") String min_,
                             @Field("counttime") String counttime,
                             @Field("mark") String mark
    );

    @FormUrlEncoded
    @POST(API.URL_ASK)
    Call<String> postAsk(@Field("id") String id,
                         @Field("group_id") String group_id,
                         @Field("leader_id[]") String leader_id,
                         @Field("daoxiu") String daoxiu,
                         @Field("reason") String reason,
                         @Field("starDate") String starDate,
                         @Field("starTime[hour]") String starTimeHour,
                         @Field("starTime[min]") String starTimeMin,
                         @Field("endDate") String endDate,
                         @Field("endTime[hour]") String endTimeHour,
                         @Field("endTime[min]") String endTimeMin,
                         @Field("starDate_") String starDate_,
                         @Field("hour_") String hour_,
                         @Field("min_") String min_,
                         @Field("counttime") String counttime,
                         @Field("mark") String mark
    );

    @GET(API.URL_DEL_LEAVE)
    Call<String> delLeave(@Query("ID") String id, @Query("user_id") String user_id);
}
