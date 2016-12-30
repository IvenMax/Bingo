package com.iven.app.okhttp;

import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iven.app.okhttp.builder.GetBuilder;
import com.iven.app.okhttp.builder.HeadBuilder;
import com.iven.app.okhttp.builder.OtherRequestBuilder;
import com.iven.app.okhttp.builder.PostFileBuilder;
import com.iven.app.okhttp.builder.PostFormBuilder;
import com.iven.app.okhttp.builder.PostStringBuilder;
import com.iven.app.okhttp.callback.Callback;
import com.iven.app.okhttp.request.RequestCall;
import com.iven.app.okhttp.utils.Platform;
import com.iven.app.util.L;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static android.R.attr.id;

/**
 * Created by zhy on 15/8/17.
 */
public class OkHttpUtils {
    private static final String TAG = "zpy_OkHttpUtils";
    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;
    private Gson mGson;

    public OkHttpUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        } else {
            mOkHttpClient = okHttpClient;
        }
        final int sdk = Build.VERSION.SDK_INT;
        if (sdk >= 23) {
            GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
            mGson = gsonBuilder.create();
        } else {
            mGson = new Gson();
        }

        mPlatform = Platform.get();
    }


    public static OkHttpUtils initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtils getInstance() {
        return initClient(null);
    }


    public Executor getDelivery() {
        return mPlatform.defaultCallbackExecutor();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public static PostFileBuilder postFile() {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post() {
        return new PostFormBuilder();
    }

    public static OtherRequestBuilder put() {
        return new OtherRequestBuilder(METHOD.PUT);
    }

    public static HeadBuilder head() {
        return new HeadBuilder();
    }

    public static OtherRequestBuilder delete() {
        return new OtherRequestBuilder(METHOD.DELETE);
    }

    public static OtherRequestBuilder patch() {
        return new OtherRequestBuilder(METHOD.PATCH);
    }

    public void execute(final RequestCall requestCall, Callback callback) {
        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;
        final int id = requestCall.getOkHttpRequest().getId();

        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                String string = requestCall.getRequest().url().toString();
                sendFailResultCallback(call, e, finalCallback, id);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                if (response.code() > 400 && response.code() < 599) {
                    try {
                        sendFailResultCallback(call, new RuntimeException(response.body().string()), finalCallback, id);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //取消
                if (call.isCanceled()) {
                    sendFailResultCallback(call, new IOException("Canceled!"), finalCallback, id);
                }
                try {
                    String jsonString;
                    jsonString = response.body().string();
                    setData(requestCall, finalCallback, jsonString);

                    // sendSuccessResultCallback(jsonString, finalCallback, id);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (response.body() != null)
                        response.body().close();
                }
            }
        });
    }

    // TODO: 2016/12/30 反射解析数据 进行回传
    private void setData(RequestCall requestCall, Callback finalCallback, String jsonString) {
        L.e("json数据", "---------" + requestCall.getRequest().url().toString() + "  ------json数据---------------");
        L.e("json数据", jsonString);
        L.e("json数据", "---------" + requestCall.getRequest().url().toString() + "  ------json数据---------------");
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            Log.e(TAG, "setData: 166" + "行 = ");
        } catch (JSONException e) {
            sendFailResultCallback(requestCall.getCall(), new RuntimeException("数据解析异常"), null, id);
            e.printStackTrace();
            L.e("okhttp catch ", "setData . exception = " + e.getMessage());
        }

        sendSuccessResultCallback(jsonString, finalCallback, id);
    }

    public void sendFailResultCallback(final Call call, final Exception e, final Callback callback, final int id) {
        if (callback == null)
            return;

        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onError(call, e, id);
                callback.onAfter(id);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Callback callback, final int id) {
        if (callback == null)
            return;
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object, id);
                callback.onAfter(id);
            }
        });
    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public static class METHOD {
        public static final String HEAD = "HEAD";
        public static final String DELETE = "DELETE";
        public static final String PUT = "PUT";
        public static final String PATCH = "PATCH";
    }
}

