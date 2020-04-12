package com.chenhl.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("errorBean",
                builder.getBeanDefinition());

        applicationContext.refresh();

        applicationContext.close();
    }

    static class POJO implements InitializingBean {
        @PostConstruct
        public void init() throws Throwable {
            throw new Exception("PostConstruct init() FOR PROPURSE");
        }
        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet  FOR PROPURSE");
        }
    }
}
