package com.chenhl.thinking.in.denpency;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

public class DependencySourceDemo {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void initByInject(){
        System.out.println("beanFactory == applicationContext  " + (beanFactory==applicationContext));
        System.out.println("beanFactory == applicationContext.getAutowireCapableBeanFactory  " + (beanFactory==applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext  " + (resourceLoader==applicationContext));
        System.out.println("applicationEventPublisher == applicationContext  " + (applicationEventPublisher==applicationContext));
    }

    @PostConstruct
    public void initByLookup(){
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ApplicationEventPublisher.class);
        getBean(ApplicationEventPublisher.class);
    }


    private  <T>  T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型 " + beanType.getName() + " 无法在BeanFactory中查找！");
        }

        return null;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(DependencySourceDemo.class);

        ctx.refresh();
        DependencySourceDemo dependencySourceDemo = ctx.getBean(DependencySourceDemo.class);

        ctx.close();
    }
}
