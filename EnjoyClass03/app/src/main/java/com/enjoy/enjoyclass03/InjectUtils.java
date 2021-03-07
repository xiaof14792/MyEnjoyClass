package com.enjoy.enjoyclass03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Field;

public class InjectUtils {

    public static void injectView(Activity activity) throws IllegalAccessException {
        Class<? extends Activity> cls = activity.getClass();
        Field[] fields = cls.getDeclaredFields();

        for (Field field: fields){
            if (field.isAnnotationPresent(InjectView.class)){
                InjectView injectView = field.getAnnotation(InjectView.class);

                int id = injectView.value();
                View view = activity.findViewById(id);
                field.setAccessible(true);
                field.set(activity, view);
            }
        }
    }

    public static void autoWired(Activity activity) {
        Class<? extends Activity> cls = activity.getClass();
        Field[] fields = cls.getDeclaredFields();
        Bundle extras = activity.getIntent().getExtras();

        for (Field field: fields){
            if (field.isAnnotationPresent(AutoWired.class)){ //当前属性是否存在"AutoWired"注解
                AutoWired autoWired = field.getAnnotation(AutoWired.class);

                String key = autoWired.value().isEmpty() ? field.getName() : autoWired.value();
                if (extras.containsKey(key)){
                    Object value = extras.get(key);

                    field.setAccessible(true);
                    try {
                        field.set(activity, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
