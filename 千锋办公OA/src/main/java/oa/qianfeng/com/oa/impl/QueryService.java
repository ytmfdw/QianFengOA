package oa.qianfeng.com.oa.impl;

import oa.qianfeng.com.oa.utils.API;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public interface QueryService {
    @GET(API.URL_CLOCK)
    Call<String> query(@Path("page") int page);

    @GET(API.URL_LEAVE)
    Call<String> getLeaveData();

    @GET(API.URL_BROADCAST_DETAIL)
    Call<String> getBroadcastData(@Query("mid") String mid);
}
