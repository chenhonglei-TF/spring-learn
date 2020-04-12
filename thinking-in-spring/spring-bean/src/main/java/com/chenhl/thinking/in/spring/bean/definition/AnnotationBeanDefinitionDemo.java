package com.chenhl.thinking.in.spring.bean.definition;

import com.chenhl.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        registerUserBeanDefinition(applicationContext, "chenhl2");
        registerUserBeanDefinition(applicationContext);



        applicationContext.refresh();

        System.out.println("Config 类型的所有 Beans: "+applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有 Beans: "+applicationContext.getBeansOfType(User.class));


        applicationContext.close();

    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName){
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id", 1L).addPropertyValue("name", "chenhl");

        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    @Component
    public static class Config{
        // 1. 通过 @Bean的方式来定义
        @Bean
        public User user(){
            User user = new User();
            user.setId(1L);
            user.setName("chenhl");
            return user;

        }
    }


}
