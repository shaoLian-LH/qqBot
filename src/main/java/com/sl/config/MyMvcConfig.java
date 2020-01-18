package com.sl.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author：邵莲
 * @Date：2019/11/2 9:53
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    //添加静态资源目录
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/webjars/**") .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
    }

    //注册拦截器
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //静态资源有SpringBoot自动增加了映射(1.x)
        super.addInterceptors(registry);
    }

    //为支持PUT和DELETE方法设置的过滤器，注册成为组件
    //其最终实现了ServletInitial，在容器初始加载时启动
    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> putFilter() {
        FilterRegistrationBean<HiddenHttpMethodFilter> registration = new FilterRegistrationBean<HiddenHttpMethodFilter>();
        registration.setFilter(new HiddenHttpMethodFilter());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.setName("HiddenHttpMethodFilter");//设置优先级
        registration.setOrder(2);//设置优先级
        return registration;
    }


    @Override
    protected void addFormatters(FormatterRegistry registry) {

    }
}
