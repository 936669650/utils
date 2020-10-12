package com.lenovo.manufacture.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.lenovo.manufacture.four.entity.FourInfo;
import com.lenovo.manufacture.myinterface.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtil {

    private static String RESULT = null;

    /**
     * @param url 请求路径
     * @param map    需要传递的参数
     * @param mClass 需要解析的javaBean(此参数不能为空)
     * @param result okhttp中callback回调中调用此接口的实现
     * @param <T>    类型
     */
    public static <T> void doPost(Map<String, Object> map, String url, Class<T> mClass,Result<? super T> result){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if(map != null){
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                builder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .post(formBody)
                .url(url)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("请求失败","失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(mClass != null){
                    T t = new Gson().fromJson(RESULT = response.body().string(), mClass);
                    result.getData(t);
                }
            }
        });
    }
}
