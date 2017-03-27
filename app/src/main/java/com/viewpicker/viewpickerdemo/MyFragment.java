package com.viewpicker.viewpickerdemo;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpicker.viewpicker.annotation.ContentView;
import com.viewpicker.viewpicker.annotation.OnClick;
import com.viewpicker.viewpicker.annotation.ViewInject;
import com.viewpicker.viewpickerdemo.base.BaseFragment;

/**
 * Created by litengfei on 2017/3/27.
 */
@ContentView(R.layout.activity_main)
public class MyFragment extends BaseFragment {
    @ViewInject(R.id.tv)
    TextView tv;


    @Override
    protected void initData() {
        tv.setText("fragment");
    }

    @OnClick({R.id.tv, R.id.btn})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv:
                Toast.makeText(getActivity(), "tvclick", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn:
                Toast.makeText(getActivity(), "btnclick", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
