package com.message.test.myapplication.activityview;

import android.app.Activity;
import android.os.Bundle;

import com.message.test.myapplication.R;

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
    }
}
