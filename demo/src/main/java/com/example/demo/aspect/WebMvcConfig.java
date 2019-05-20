package com.example.demo.aspect;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auther: shieldofzues
 * @Date: 2018/10/12 11:40
 * @Description:
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /*
     * 静态资源本地映射
     *
     * */
    @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 前端跨域
     */

    @Override public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//设置允许跨域的路径
            .allowedOrigins("*")//设置允许跨域请求的域名
            .allowCredentials(true)//是否允许证书 不再默认开启
            .allowedMethods("GET", "POST", "PUT", "DELETE")//设置允许的方法
            .maxAge(3600);//跨域允许时间
    }
}
