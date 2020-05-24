package com.chenhl.thinking.in.denpency;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource(value = "META-INF/default.properties", encoding = "UTF-8")
public class ExternalConfigrableDependencySourceDemo {


    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String name;

    @Value("${user.resource:classpath://default.properties}")
    private Resource resource;


    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ExternalConfigrableDependencySourceDemo.class);

        ctx.refresh();
        ExternalConfigrableDependencySourceDemo demo = ctx.getBean(ExternalConfigrableDependencySourceDemo.class);

        System.out.println("demo.id " + demo.id);
        System.out.println("demo.name " + demo.name);
        System.out.println("demo.resource " + demo.resource);

        ctx.close();
    }
}
