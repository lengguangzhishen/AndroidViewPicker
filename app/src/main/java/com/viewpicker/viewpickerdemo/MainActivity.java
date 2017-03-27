package com.viewpicker.viewpickerdemo;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpicker.viewpicker.annotation.ContentView;
import com.viewpicker.viewpicker.annotation.OnClick;
import com.viewpicker.viewpicker.annotation.ViewInject;
import com.viewpicker.viewpickerdemo.base.BaseActivity;

@ContentView(R.layout.activity_fragment)
public class MainActivity extends BaseActivity {

//    @ViewInject(R.id.tv)
//    TextView tv;
//    @ViewInject(R.id.btn)
//    Button btn;


    @Override
    public void initData() {
//        tv.setText("initData");
    }

//    @OnClick({R.id.tv, R.id.btn})
//    public void click(View view) {
//        switch (view.getId()) {
//            case R.id.tv:
//                Toast.makeText(MainActivity.this, tv.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.btn:
//                Toast.makeText(MainActivity.this, btn.getText().toString(), Toast.LENGTH_SHORT).show();
//                break;
//        }

//    }
}
