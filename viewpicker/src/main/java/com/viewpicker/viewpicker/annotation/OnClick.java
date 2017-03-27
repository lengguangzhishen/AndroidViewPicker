package com.viewpicker.viewpicker.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by litengfei on 2017/3/24.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventBase(listenerName = "setOnClickListener", listenerType = View.OnClickListener.class, callbackMethod = "onClick")
public @interface OnClick {
    int[] value();
}
