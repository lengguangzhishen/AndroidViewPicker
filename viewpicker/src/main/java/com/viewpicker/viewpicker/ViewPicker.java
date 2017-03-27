package com.viewpicker.viewpicker;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpicker.viewpicker.annotation.ContentView;
import com.viewpicker.viewpicker.annotation.EventBase;
import com.viewpicker.viewpicker.annotation.ViewInject;
import com.viewpicker.viewpicker.proxy.EventInvokeHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by litengfei on 2017/3/24.
 */
public class ViewPicker {

    public static View bindFragment(Fragment fragment, LayoutInflater inflater, ViewGroup container) {

        if (fragment == null) {
            return null;
        }

        Class<? extends Fragment> fragmentClass = fragment.getClass();
        ContentView contentView = fragmentClass.getAnnotation(ContentView.class);
        if (contentView == null) {
            return null;
        }

        int layoutId = contentView.value();
        return inflater.inflate(layoutId, container);

    }

    public static void injectFragment(Fragment fragment, View view) {
        if (fragment == null || view == null) {
            return;
        }

        injectView(fragment, view);
        injectEvent(fragment, view);
    }

    private static void injectEvent(Fragment fragment, View view) {
        Class<? extends Fragment> clazz = fragment.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                if (eventBase == null) {
                    continue;
                }

                String listenerName = eventBase.listenerName();
                Class<?> listenerType = eventBase.listenerType();
                String callbackMethod = eventBase.callbackMethod();

                Map<String, Method> methodMap = new HashMap<>();
                methodMap.put(callbackMethod, method);

                try {
                    Method annotationMethod = annotationType.getDeclaredMethod("value");
                    int[] value = (int[]) annotationMethod.invoke(annotation);

                    for (int id : value) {
                        View v = view.findViewById(id);
                        if (v == null) {
                            continue;
                        }
                        Method onClickMethod = v.getClass().getMethod(listenerName, listenerType);

                        EventInvokeHandler eventInvokeHandler = new EventInvokeHandler(fragment, methodMap);
                        Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, eventInvokeHandler);
                        onClickMethod.invoke(v, proxy);
                    }

                } catch (Exception e) {
                }
            }
        }
    }

    private static void injectView(Fragment fragment, View view) {
        Class<? extends Fragment> fragmentClass = fragment.getClass();
        Field[] fields = fragmentClass.getDeclaredFields();
        for (Field field : fields) {
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if (viewInject == null) {
                return;
            }
            int id = viewInject.value();
            View v = view.findViewById(id);
            field.setAccessible(true);
            try {
                field.set(fragment, v);
            } catch (IllegalAccessException e) {
            }

        }
    }

    public static void bindActivity(Activity activity){
        if (activity == null) {
            return;
        }
        injectLayout(activity);
        injectView(activity);
        injectEvent(activity);
    }

    private static void injectEvent(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                if (eventBase == null) {
                    continue;
                }

                String listenerName = eventBase.listenerName();
                Class<?> listenerType = eventBase.listenerType();
                String callbackMethod = eventBase.callbackMethod();

                Map<String, Method> methodMap = new HashMap<>();
                methodMap.put(callbackMethod, method);

                try {
                    Method annotationMethod = annotationType.getDeclaredMethod("value");
                    int[] value = (int[]) annotationMethod.invoke(annotation);

                    for (int id : value) {
                        View view = activity.findViewById(id);
                        if (view == null) {
                            continue;
                        }
                        Method onClickMethod = view.getClass().getMethod(listenerName, listenerType);

                        EventInvokeHandler eventInvokeHandler = new EventInvokeHandler(activity, methodMap);
                        Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, eventInvokeHandler);
                        onClickMethod.invoke(view, proxy);
                    }

                } catch (Exception e) {
                }
            }
        }

    }

    private static void injectLayout(Activity activity) {

        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView == null) {
            return;
        }
        activity.setContentView(contentView.value());
    }

    private static void injectView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if (viewInject == null) {
                continue;
            }
            int id = viewInject.value();
            View view = activity.findViewById(id);
            field.setAccessible(true);
            try {
                field.set(activity, view);
            } catch (IllegalAccessException e) {

            }
        }
    }

}
