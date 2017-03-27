package com.viewpicker.viewpickerdemo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.viewpicker.viewpicker.ViewPicker;

/**
 * Created by litengfei on 2017/3/24.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewPicker.bindActivity(this);

        initData();
    }

    public abstract void initData();
}
