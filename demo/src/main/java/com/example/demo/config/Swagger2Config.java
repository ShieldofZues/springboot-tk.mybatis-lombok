package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author shieldofzues
 * @Description Swagger2Config
 * @Date 15:52 2016/5/23
 *
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {
	/**
	 * 摘要信息
	 */
	@Bean
	public Docket controllerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder()
						.title("标题：xx公司管理系统_接口文档")
						.description("描述：用于管理集团旗下公司的人员信息,具体包括XXX,XXX模块...")
						.contact(new Contact("啥也不是", null, null))
						.version("版本号:1.0")
						.build())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
				.paths(PathSelectors.any())
				.build();
	}

}