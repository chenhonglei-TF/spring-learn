package com.chenhl.thinking.in.denpency.injection;

import com.chenhl.thinking.in.denpency.injection.annotation.UserGroup;
import com.chenhl.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Set;

public class LazyAnnotationDependencyInjectionDemo {


    @Autowired // 实时注入
    private User user;

    @Autowired // 延时注入
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectProvider<Set<User>> setObjectProvider;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlRoursePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlRoursePath);

        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo bean = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        System.out.println("bean.user = " + bean.user );
        System.out.println("bean.userObjectProvider = " + bean.userObjectProvider.getObject() );
        System.out.println("bean.setObjectProvider = " + bean.setObjectProvider.getObject() );

        bean.userObjectProvider.stream().forEach(System.out::println);


        applicationContext.close();

    }

}
