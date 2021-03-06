package com.hehe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//@EnableAutoConfiguration
@SpringBootApplication

@MapperScan("com.hehe.dao")
public class ThymeleafApplication {

	public static void main(String[] args) {

	    SpringApplication.run(ThymeleafApplication.class, args);

	}
}
