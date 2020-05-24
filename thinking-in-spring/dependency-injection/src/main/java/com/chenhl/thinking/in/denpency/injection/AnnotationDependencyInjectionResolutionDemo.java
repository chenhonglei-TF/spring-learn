package com.chenhl.thinking.in.denpency.injection;

import com.chenhl.thinking.in.denpency.injection.annotation.InjectedUser;
import com.chenhl.thinking.in.denpency.injection.annotation.MyAutowired;
import com.chenhl.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.*;

import static java.util.Arrays.asList;
import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 注解驱动 依赖注入处理过程
 */
public class AnnotationDependencyInjectionResolutionDemo {


    @Autowired // DependencyDescriptor ---->   实时注入 + 通过类型查找 + 字段名称（"user"）
    private User user;

    @Autowired
    @Lazy
    private User lazyUser;

    @Autowired
    private Map<String, User> users;

    @MyAutowired
    private Optional<User> userOptional;

@InjectedUser
private User myInjectedUser;

//@Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
//    AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//    Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(asList(Autowired.class, Inject.class, InjectedUser.class));
//    beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//    return beanPostProcessor;
//}


    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE -3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(asList(Autowired.class, Inject.class, InjectedUser.class));
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }

//    @Inject
    private User injectedUser;

//    @Autowired // 延时注入
//    private ObjectProvider<User> userObjectProvider;

//    @Autowired
//    private ObjectProvider<Set<User>> setObjectProvider;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlRoursePath = "classpath:/META-INF/dependency-lookup-context.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlRoursePath);

        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo bean = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println("bean.user = " + bean.user );
        System.out.println("bean.injectedUser = " + bean.injectedUser );
        System.out.println("bean.users = " + bean.users );
//        System.out.println("bean.userObjectProvider = " + bean.userObjectProvider.getObject() );
//        System.out.println("bean.setObjectProvider = " + bean.setObjectProvider.getObject() );

//        bean.userObjectProvider.stream().forEach(System.out::println);


        applicationContext.close();

    }

}
