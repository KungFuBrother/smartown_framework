package com.smartown.library.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Tiger on 2016-03-24.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonDescription {

    //变量在JSON中对应的键名
    String key();

    //变量是否为自定义对象
    boolean isObject() default false;

    //变量是否为集合
    boolean isList() default false;

    //集合变量类型
    Class listContentClass() default Object.class;

}
