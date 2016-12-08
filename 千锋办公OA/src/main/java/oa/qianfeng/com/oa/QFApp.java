package oa.qianfeng.com.oa;

import android.app.Application;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import oa.qianfeng.com.oa.entity.UserBean;
import oa.qianfeng.com.oa.utils.CookieManger;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/30.
 */
public class QFApp extends Application {

    static QFApp app;

    OkHttpClient httpClient;

    public static UserBean user;

    EventBus msgBus = new EventBus();

    private Map<String, String> leaderId = new HashMap<>();
    private Map<String, String> reasonId = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public Map<String, String> getLeaderMap() {
        return leaderId;
    }

    public Map<String, String> getReasonMap() {
        return reasonId;
    }

    public EventBus getMsgBus() {
        return msgBus;
    }

    public static QFApp getInstence() {
        return app;
    }

    public synchronized OkHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request()
                                    .newBuilder()
                                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                    .addHeader("Connection", "keep-alive")
                                    .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                                    .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0")
                                    .build();
                            return chain.proceed(request);
                        }
                    }).cookieJar(new CookieManger(this)).build();
        }
        return httpClient;
    }


}
