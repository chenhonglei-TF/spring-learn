package com.chenhl.thinking.in.spring.ioc.overview.dependency.lookup;

import com.chenhl.thinking.in.spring.ioc.overview.annotation.Super;
import com.chenhl.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 1.根据bean的名称查找
 *   1.1 实时查找
 *   1.2 延迟查找
 * 2.根据Bean类型查找
 *   2.1 单个bean对象
 *   2.2 集合bean对象
 * 3.根据bean名称与类型查找
 * 4.根据java注解查找
 *   4.1 单个bean对象
 *   4.2 集合bean对象
 */
public class DependencyLookupDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        //实时查找
//        lookupInRealTime(beanFactory);
        //延迟查找
//        lookupInLazy(beanFactory);
        // 根据类型查询单个对象
//        lookupByType(beanFactory);
        // 根据类型查询多个对象
        lookupCollectionByType(beanFactory);
        // 通过注解查找
        lookupByAnnotationType(beanFactory);

    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);

            System.out.println("查找到的标注了super注解的user对象： " + users);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有user对象： " + users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user =  beanFactory.getBean(User.class);
        System.out.println("实时查找: " + user);
    }

    public static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找: " + user);
    }

    public static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找: " + user);
    }
}
