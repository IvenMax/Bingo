package com.iven.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iven.app.base.BaseActivity;
import com.iven.app.bean.TestBean;
import com.iven.app.okhttp.OkHttpUtils;
import com.iven.app.okhttp.callback.StringCallback;
import com.iven.app.util.ConstantValue;

import java.util.List;

import okhttp3.Call;

/**
 * learn ~~~~
 */
public class MainActivity extends BaseActivity {
    private static final String TAG = "zpy_MainActivity";
    private TextView tv_show_text;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setTitle() {
        title_left.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.title_back), null, null, null);
        title_title.setText("首页");
    }

    @Override
    public void initWidget() {
        tv_show_text = (TextView) findViewById(R.id.tv_show_text);


    }

    @Override
    public void widgetClick(View view) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logdData();
    }

    private void logdData() {
        OkHttpUtils.get().url(ConstantValue.TEST).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this, "失败" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError: 50" + "行 =e.getMessage().toString()= " + e.getMessage().toString());
            }

            @Override
            public void onResponse(String response, int id) {
                if (null != response) {
                    Gson gson = new Gson();
                    TestBean testBean = gson.fromJson(ConstantValue.TEST_STRING, TestBean.class);
                    Log.e(TAG, "onResponse: 64" + "行 = " +testBean.getDate());
                    List<TestBean.StoriesBean> stories = testBean.getStories();
                    for (int i = 0; i < stories.size(); i++) {
                        Log.e(TAG, "onResponse: 69" + "行 = " +stories.get(i).getTitle());
                    }

                    //                    tv_show_text.setText(response);
                }
            }
        });

    }

}
