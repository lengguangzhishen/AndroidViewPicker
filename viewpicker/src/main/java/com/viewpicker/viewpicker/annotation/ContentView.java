package com.viewpicker.viewpicker.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by litengfei on 2017/3/24.
 */
@Retention(RetentionPolicy.RUNTIME)//SOURCE级别(编译期)
@Target(ElementType.TYPE)//
public @interface ContentView {

    int value();
}
