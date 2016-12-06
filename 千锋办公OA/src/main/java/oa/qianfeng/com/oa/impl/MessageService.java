package oa.qianfeng.com.oa.impl;

import oa.qianfeng.com.oa.utils.API;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/12/6.
 */
public interface MessageService {
    @GET(API.URL_MESSAGE)
    Call<String> getData();

    @GET(API.URL_BROADCAST_DETAIL)
    Call<String> getBroadcastDetail(@Query("mid") String mid);
}
