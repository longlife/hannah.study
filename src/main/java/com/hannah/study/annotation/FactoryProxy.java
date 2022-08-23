package com.hannah.study.annotation;

import java.lang.reflect.Method;

/**
 * Factory创建器
 */
public class FactoryProxy {

    public static <T> T create(Class<T> clazz, String id) {
        try {
            Class<?> factoryClass = Class.forName(clazz.getCanonicalName() + FactoryGroupedClasses.SUFFIX);
            Object instance = factoryClass.newInstance();
            Method method = factoryClass.getDeclaredMethod("create");
            return (T) method.invoke(instance, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
