package com.chenhl.thinking.in.denpency.injection;

import com.chenhl.thinking.in.denpency.injection.annotation.UserGroup;
import com.chenhl.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User nameUser;

    @Autowired
    private Collection<User> allUser;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUser;

    @Autowired
    @UserGroup
    private Collection<User> userGroupUser;

    @Bean
    @Qualifier // 进行分组
    private User user1(){
        User user1 = new User();
        user1.setId(7L);
        return user1;
    }

    @Bean
    @Qualifier // 进行分组
    private User user2(){
        User user2 =  new User();
        user2.setId(8L);
        return user2;
    }

    @Bean
    @UserGroup
    private User user3(){
        User user3 =  new User();
        user3.setId(9L);
        return user3;
    }

    @Bean
    @UserGroup // 进行分组
    private User user4(){
        User user4 =  new User();
        user4.setId(10L);
        return user4;
    }



    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlRoursePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlRoursePath);

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo bean = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        System.out.println("bean.user = " + bean.user );
        System.out.println("bean.nameduser = " + bean.nameUser );
        System.out.println("bean.allUser = " + bean.allUser );
        System.out.println("bean.qualifierUser = " + bean.qualifierUser );
        System.out.println("bean.userGroupUser = " + bean.userGroupUser );

        applicationContext.close();

    }

}
