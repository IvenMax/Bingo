package com.iven.app.util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iven.app.R;

public class LoadingUtil {

    private Dialog pd;
    private AnimationDrawable loadingAnimation;
    private View loading;
    private boolean showLoading = true;
    private Activity activity;
    private LinearLayout layout;
    private String dialogTitle;

    /**
     * 构造方法
     *
     * @param activity 这个context 其实应该是Activity , 否则要报错
     */
    public LoadingUtil(Activity activity) {
        this(activity, activity.getResources().getString(R.string.loading));

    }

    /**
     * 构造方法
     *
     * @param activity
     * @param dialogTitle
     */
    public LoadingUtil(Activity activity, String dialogTitle) {
        super();
        this.activity = activity;
        this.dialogTitle = dialogTitle;
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            loadingAnimation.start();
            //            if (!loadingAnimation.isRunning()) {
            //                loading.setBackgroundResource(R.drawable.loading_08);
            //            }
        }
    };
    private TextView tv_loading;

    public void startShowLoading() {
        if (showLoading && activity != null && pd == null) {
            if (pd != null) {
                pd.cancel();
                pd = null;
            }
            this.pd = createDialog();
            if (!this.pd.isShowing()) {
                if (!activity.isFinishing()) {
                    this.pd.show();
                }
            }
            handler.sendEmptyMessage(0);
        } else {
            this.pd = null;
        }
    }


    public void stopShowLoading() {
        // 回调
        if (this.pd != null && this.pd.isShowing() && activity != null && !activity.isFinishing()) {

            try {
                this.pd.dismiss();
            } catch (Exception ex) {
                ex.printStackTrace();
                pd.dismiss();
            }
        }
    }

    private Dialog createDialog() {
        View view = View.inflate(activity, R.layout.layout_loading, null);
        loading = view.findViewById(R.id.loading);
        layout = (LinearLayout) view.findViewById(R.id.dialog_view);
        layout.setGravity(Gravity.CENTER);
        tv_loading = (TextView) view.findViewById(R.id.tv_loading);
        tv_loading.setText(dialogTitle);

        loading.setVisibility(View.VISIBLE);
        loading.setBackgroundResource(R.drawable.loading);
        loadingAnimation = (AnimationDrawable) loading.getBackground();

        Dialog dialog = new Dialog(activity, R.style.loading_dialog);
        dialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        // 设置透明度为0.3
        lp.alpha = 0.6f;
        lp.width = ScreenUtils.getInstance(activity).dip2px(120);
        lp.height = ScreenUtils.getInstance(activity).dip2px(80);
        window.setAttributes(lp);

        return dialog;
    }

}
