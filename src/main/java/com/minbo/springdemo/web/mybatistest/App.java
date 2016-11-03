package com.minbo.springdemo.web.mybatistest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication  
//启注解事务管理  
@EnableTransactionManagement  
@EnableAutoConfiguration
public class App {
	public static void main(String[] args) {  
        System.out.println("Hello World!");  
    }  
}
