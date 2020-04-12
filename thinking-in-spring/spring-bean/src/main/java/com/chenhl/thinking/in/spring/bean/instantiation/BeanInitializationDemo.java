package com.chenhl.thinking.in.spring.bean.instantiation;


import com.chenhl.thinking.in.spring.bean.factory.DefaultUserFactory;
import com.chenhl.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);

        // 启动
        applicationContext.refresh();

        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        // 关闭
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestoryUserFactory")
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }
}
