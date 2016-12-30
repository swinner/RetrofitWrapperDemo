package com.example.gaotengfei.retrofitwrapperdemo.httpUtil.converter;

import android.util.Log;

import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.ServiceFactoty;
import com.example.gaotengfei.retrofitwrapperdemo.httpUtil.exception.CustomException;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import java.lang.reflect.Type;
import static okhttp3.internal.Util.UTF_8;

/**
 * @anthor: gaotengfei
 * @time: 2016/12/2
 * @tel: 18511913443
 * @desc: 模仿ResponseBodyConverter进行重写，改动点为adapterType，首先进行判断adapterType类型，
 * 为了进行区分传入的是String还是数据bean ，同时在convert进行返回数据的拦截，如果返回的数据是加密的可以在此处进行解密
 * 还可以在此处统一处理返回的code，根据code值进行统一处理逻辑。
 */

public class CustomResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private String TAG = CustomResponseBodyConverter.class.getSimpleName();
    private final Gson gson;
    private final Type adapterType;

    CustomResponseBodyConverter(Gson gson, Type adapterType) {
        this.gson = gson;
        this.adapterType = adapterType;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Log.e(ServiceFactoty.Tag, "responseConvert=====>" + response);
        try {
            JSONObject result = new JSONObject(response);
            JSONObject statusObj = result.getJSONObject("status");
            int code = statusObj.getInt("code");
            String msg = statusObj.getString("msg");
            if (code != 0) {
                //返回的code不是RESULT_OK时Toast显示msg
                if(code == -1){
                    throw new CustomException(code, "token过期，请重新登录", response);
                }else{
                    throw new CustomException(code, msg, response);
                }
            }
        } catch (JSONException e) {
            value.close();
            //服务端返回的不是JSON，服务端出问题
            throw new CustomException(-1, "数据格式错误", response);
        }
        if (adapterType instanceof Class) {//要返回的数据类型为String的情况
            if (adapterType == String.class) {
                Log.e(ServiceFactoty.Tag, "String.class=====>");
                return (T) response;
            }
        }
        //要返回的数据类型为数据bean的情况
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(adapterType));
        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);
        try {
            return (T) adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}

