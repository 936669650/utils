package com.hzy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域访问配置
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域的请求的域名/源
                .allowedOrigins("*")
                //设置允许请求的方法
                .allowedMethods("GET","HEAD","POST","DELETE","OPTIONS")
                //是否允许证书，不在默认开启/是否发送cookie
                .allowCredentials(true)
                //跨域允许时间/预检间隔时间
                .maxAge(3600)
                //允许头部设置
                .allowedHeaders("*");
    }
}
