package com.iven.app.okhttp.callback;


import android.app.Activity;
import android.content.Context;

import com.iven.app.base.BaseActivity;
import com.iven.app.util.LoadingUtil;

import okhttp3.Request;

/**
 * @author Iven
 * @date 2016/12/30 13:42
 * @Description
 */

public abstract class MyDefaultCallback<T> extends Callback<T> {
    private Context mContext;
    private LoadingUtil loadingUtil;
    private boolean isShowLoading;//是否显示加载动画

    public MyDefaultCallback(Context context, boolean showLoading) {

        this.mContext = context;
        this.isShowLoading = showLoading;

        if (mContext instanceof Activity && showLoading) {
            this.loadingUtil = new LoadingUtil((Activity) mContext);
        }
    }

    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
        if (this.isShowLoading) {
            try {
                this.loadingUtil.startShowLoading();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAfter(int id) {
        super.onAfter(id);
        if (isShowLoading) {
            try {

                this.loadingUtil.stopShowLoading();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onStopLoading(Request request) {
        if (isShowLoading) {
            this.loadingUtil.stopShowLoading();
        }
    }
    public BaseActivity getContext() {
        return (BaseActivity) this.mContext;
    }
}
