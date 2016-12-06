package oa.qianfeng.com.oa.impl;

import oa.qianfeng.com.oa.utils.API;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2016/12/5.
 */
public interface MineService {
    @GET(API.URL_MINE)
    Call<String> getMineData();

    @GET(API.URL_LOGINOUT)
    Call<String> loginOut();
}
