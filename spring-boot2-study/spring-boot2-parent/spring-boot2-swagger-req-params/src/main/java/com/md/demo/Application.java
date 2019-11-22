package com.md.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;

/**
 * 程序主入口
 * 
 * @author Minbo
 *
 */
@SpringBootApplication
@EnableSwaggerBootstrapUI
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}