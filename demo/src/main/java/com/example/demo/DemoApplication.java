package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.example.demo.mapper")
@SpringBootApplication
/**
 * 开启异步支持
 */
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}