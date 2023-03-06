package com.zhou.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan("com.zhou.demo.persist.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)//授权开启。
public class demoapplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(demoapplication.class, args);
//        String[] beanNames =  ctx.getBeanDefinitionNames();
//        System.out.println("所以beanNames个数："+beanNames.length);
//        for(String bn:beanNames){
//            System.out.println(bn);
//        }
    }
}
