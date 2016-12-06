package oa.qianfeng.com.oa.model;

import oa.qianfeng.com.oa.QFApp;
import oa.qianfeng.com.oa.impl.MessageService;
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
public class MessageModel implements IMessageModel {
    @Override
    public String getTitle() {
        return "公告消息";
    }

    @Override
    public void loadData(final OnGetDataListener<String> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(QFApp.getInstence().getHttpClient())
                .build();
        MessageService ms = retrofit.create(MessageService.class);
        Call<String> call = ms.getData();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (listener != null) {
                    listener.onGetDataSuccess(response.body());
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
