package oa.qianfeng.com.oa.impl;

import oa.qianfeng.com.oa.utils.API;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/12/7.
 */
public interface AskService {
    @GET(API.URL_ASK)
    Call<String> getData(@Query("type") String type);
}
