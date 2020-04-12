package com.chenhl.thinking.in.spring.ioc.overview.dependency.injection;

import com.chenhl.thinking.in.spring.ioc.overview.annotation.Super;
import com.chenhl.thinking.in.spring.ioc.overview.domain.User;
import com.chenhl.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * 1.根据bean的名称注入
 * 2.根据Bean类型注入
 *   2.1 单个bean对象
 *   2.2 集合bean对象
 * 3.根据容器内建bean对象
 * 4.注入非bean对象
 * 5.注入类型
 *   5.1 实时注入
 *   5.2 延迟注入
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");

        // 依赖来源一：自定义 Bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);

//        System.out.println(userRepository.getUsers());
//        System.out.println(userRepository.getBeanFactory());
//        System.out.println(userRepository.getBeanFactory()==beanFactory);

        // todo ---> BeanFactory 是ApplicationContext里组合的一个对象，这里使用了一个组合模式
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源二： 内建依赖
//        ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();
//        System.out.println(userObjectFactory.getObject());
        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject() == beanFactory);

        // 依赖来源三：容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的Bean "+ environment);
    }
}
