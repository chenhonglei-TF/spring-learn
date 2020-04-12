package com.chenhl.thinking.in.spring.bean.instantiation;

import com.chenhl.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.chenhl.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:META-INF/special-bean-instantiation-context.xml");

        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        ServiceLoader<UserFactory>  serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayService(serviceLoader);
//        serviceloaderdemo();

        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.getUser());

    }

    public static void serviceloaderdemo(){
        ServiceLoader<UserFactory> serviceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        displayService(serviceLoader);

    }

    public static void displayService(ServiceLoader serviceLoader){
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.getUser());
        }
    }
}
