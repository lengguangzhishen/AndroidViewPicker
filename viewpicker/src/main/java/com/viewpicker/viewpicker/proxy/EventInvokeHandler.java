package com.viewpicker.viewpicker.proxy;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by litengfei on 2017/3/24.
 */
public class EventInvokeHandler implements InvocationHandler {

    private Map<String, Method> map;
    private Activity activity;
    private Fragment fragment;

    public EventInvokeHandler(Activity activity, Map<String, Method> map) {

        this.activity = activity;
        this.map = map;
    }

    public EventInvokeHandler(Fragment fragment, Map<String, Method> methodMap) {
        this.fragment = fragment;
        this.map = methodMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method m = map.get(method.getName());
        if (m == null) {
            return method.invoke(proxy, args);
        }
//        if (fragment == null) {
//            return m.invoke(activity, args);
//        }


        return fragment == null ? m.invoke(activity, args) : m.invoke(fragment, args);
    }
}
