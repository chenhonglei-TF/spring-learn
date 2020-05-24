package com.chenhl.thinking.in.denpency.injection.annotation;


import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;


@Target({ElementType.CONSTRUCTOR, ElementType.METHOD,  ElementType.FIELD,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutowired {

    boolean required() default true;
}
