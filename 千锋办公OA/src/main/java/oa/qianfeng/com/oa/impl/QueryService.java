package oa.qianfeng.com.oa.impl;

import oa.qianfeng.com.oa.utils.API;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/10/14 0014.
 */
public interface QueryService {
    @GET(API.URL_CLOCK)
    Call<String> query(@Path("page") int page);
}
