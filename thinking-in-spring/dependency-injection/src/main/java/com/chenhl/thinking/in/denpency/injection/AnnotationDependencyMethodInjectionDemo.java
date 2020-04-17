package com.chenhl.thinking.in.denpency.injection;

import com.chenhl.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;

    private UserHolder userHolder2;

    @Autowired
    public void initUserHolder(UserHolder userHolder){
        this.userHolder=userHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder2){
        this.userHolder2=userHolder2;
    }

    @Bean
    public UserHolder userHolder(User user){
        UserHolder userHolder = new UserHolder(user);
        return userHolder;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        applicationContext.refresh();

        AnnotationDependencyMethodInjectionDemo dependencyFieldInjectionDemo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        UserHolder bean = dependencyFieldInjectionDemo.userHolder;
        System.out.println(bean);

        UserHolder bean2 = dependencyFieldInjectionDemo.userHolder2;
        System.out.println(bean2);

        System.out.println(bean==bean2);

        applicationContext.close();
    }

}
