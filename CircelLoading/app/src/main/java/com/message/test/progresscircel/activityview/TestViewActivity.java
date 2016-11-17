package com.message.test.progresscircel.activityview;

import android.app.Activity;
import android.os.Bundle;

import com.message.test.progresscircel.R;
import com.message.test.progresscircel.activityview.view.ProgressCircelView;

/**
 * Created by shengjunhao on 16/11/14.
 */
public class TestViewActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view);

        ProgressCircelView progressCircelView = (ProgressCircelView) findViewById(R.id.loading);
        progressCircelView.beginRoute();

        ProgressCircelView progressCircelView2= (ProgressCircelView) findViewById(R.id.loading2);
        progressCircelView2.setCount(2);
        progressCircelView2.beginRoute();
    }
}
