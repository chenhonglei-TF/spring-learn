package com.chenhl.thinking.in.denpency.injection.annotation;


import java.lang.annotation.*;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD,  ElementType.FIELD,})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {

}
