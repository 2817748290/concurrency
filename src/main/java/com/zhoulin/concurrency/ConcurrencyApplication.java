package com.zhoulin.concurrency;

import com.zhoulin.concurrency.filter.HttpFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/*
Spring Boot 2.0 WebMvcConfigurerAdapter 过时
推荐使用 WebMvcConfigurationSupport
 */
@SpringBootApplication
public class ConcurrencyApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean httpFilter() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new HttpFilter());

        registrationBean.addUrlPatterns("/threadLocal/*","/thread/*");

        return registrationBean;

    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
    }
}
