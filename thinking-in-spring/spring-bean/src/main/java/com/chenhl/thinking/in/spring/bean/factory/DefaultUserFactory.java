package com.chenhl.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct : UserFactory 初始化中……");
    }

    public void initUserFactory(){
        System.out.println("自定义初始化方法 初始化中……");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet : UserFactory 初始化中……");
    }

    @PreDestroy
    public void predestory(){
        System.out.println("@PreDestroy : UserFactory 销毁中……");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean : UserFactory 销毁中……");
    }

    public void doDestoryUserFactory(){
        System.out.println("自定义销毁方法 销毁中……");
    }


}
