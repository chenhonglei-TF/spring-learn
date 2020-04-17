package com.chenhl.thinking.in.denpency.injection;

import com.chenhl.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class AnnotationDependencyFieldInjectionDemo {

    @Autowired
    private UserHolder userHolder;
//    private static UserHolder userHolder;// 如果使用static,得到的userHolder为null,说明@Autowired会忽略掉静态字段

    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        AnnotationDependencyFieldInjectionDemo dependencyFieldInjectionDemo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

        UserHolder bean = dependencyFieldInjectionDemo.userHolder;
        System.out.println(bean);

        UserHolder bean2 = dependencyFieldInjectionDemo.userHolder2;
        System.out.println(bean2);

        System.out.println(bean==bean2);

        applicationContext.close();
    }

    @Bean
    public UserHolder userHolder(User user){
        UserHolder userHolder = new UserHolder(user);
        return userHolder;
    }
}
