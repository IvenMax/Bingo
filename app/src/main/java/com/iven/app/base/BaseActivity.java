package com.iven.app.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.iven.app.R;
import com.iven.app.util.L;


/**
 * @auth Iven
 * 2016/12/3 23:05
 * @desc
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener {
    // TODO: 2016/12/4 状态栏沉浸的方法
    //http://jaeger.itscoder.com/android/2016/02/15/status-bar-demo.html
    public static String TAG = "";//全局TAG
    public static Context context;//全局Context
    protected int windowWidth;//屏幕宽
    protected int windowHeight;//屏幕高
    protected int titleHeight;//标题栏高度
    public int noTitleHeight;
    private int[] location;
    protected View title;//Title的View显示
    protected TextView title_left, title_title, title_right;
    protected boolean allowFullScreen = false;//是否全屏显示APP(默认false)

    /**
     * 设置Layout布局
     *
     * @return int
     */
    public abstract int setLayout();

    /**
     * 标题设置
     */
    public abstract void setTitle();

    /**
     * 初始化控件
     */
    public abstract void initWidget();

    /**
     * 控件点击事件处理
     *
     * @param view View
     */
    public abstract void widgetClick(View view);

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消默认标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        context = this;
        //打印生命周期日志
        TAG = "IVEN_" + context.getClass().getSimpleName();
        L.e(TAG, "onCreate.activityName = " + getClass().getSimpleName());
        //获取布局ID
        int resId = setLayout();
        if (resId == 0)
            return;
        View view = View.inflate(context, resId, null);
        //布局显示
        setContentView(view);
        //初始化标题栏布局
        initTitle();
        //
        setTitle();
        initWidget();

    }

    /**
     * 初始化标题栏 Title
     */
    private void initTitle() {
        location = new int[2];//获取View的屏幕位置
        title = findViewById(R.id.title);
        title_left = (TextView) title.findViewById(R.id.title_left);
        title_title = (TextView) title.findViewById(R.id.title_title);
        title_right = (TextView) title.findViewById(R.id.title_right);
        //分辨率相关
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = getWindowManager();
        manager.getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        windowHeight = metrics.heightPixels;
        windowWidth = metrics.widthPixels;
        //将标题栏的高度，设置为屏幕高度的 1/8
        ViewGroup.LayoutParams params = title.getLayoutParams();
        params.height = (int) (width / 8);
        titleHeight = params.height;
        title.setLayoutParams(params);
        noTitleHeight = windowHeight - titleHeight;
        //设置是否全屏显示
        setAllowFullScreen(allowFullScreen);
    }


    @Override
    protected void onResume() {
        super.onResume();
        context = this;

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.e(TAG, "onDestroy : ");
    }


    /**
     * 通过Class跳转页面
     *
     * @param clazz Class
     */
    public void startActivity(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
        // TODO: 2016/12/4  添加跳转动画
    }

    /**
     * 带有Bundle 的 通过Class跳转页面
     *
     * @param clazz  Class
     * @param bundle Bundle
     */
    public void startActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        // TODO: 2016/12/4  添加跳转动画
    }

    public void startActivityAndFinish(Class<?> clz) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        intent.setClass(this, clz);
        startActivity(intent);
        this.finish();
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityAndFinish(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null || bundle.equals(null)) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        this.finish();
    }

    /**
     * 不让本APP的字体大小随着系统字体大小变化而变化
     *
     * @return
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 是否全屏
     *
     * @param allowFullScreen true -是
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        if (allowFullScreen) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }
}
