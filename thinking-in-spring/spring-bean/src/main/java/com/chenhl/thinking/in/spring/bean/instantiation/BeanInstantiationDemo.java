package com.chenhl.thinking.in.spring.bean.instantiation;

import com.chenhl.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-instantiation-context.xml");
        User user = beanFactory.getBean("user-by-static", User.class);
        System.out.println(user);
        User user2 = beanFactory.getBean("user-by-instance", User.class);
        System.out.println(user2);
        User user3 = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(user3);

        System.out.println(user==user2);
        System.out.println(user==user3);
    }
}
