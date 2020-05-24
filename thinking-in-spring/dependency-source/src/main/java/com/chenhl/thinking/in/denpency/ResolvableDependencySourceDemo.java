package com.chenhl.thinking.in.denpency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(ResolvableDependencySourceDemo.class);

        ctx.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class, "hello world");
        });
        ctx.refresh();

        ResolvableDependencySourceDemo dependencySourceDemo = ctx.getBean(ResolvableDependencySourceDemo.class);

        ctx.close();
    }
}
