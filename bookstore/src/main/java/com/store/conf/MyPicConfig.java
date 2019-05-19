package com.store.conf;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//新增加一个类用来添加虚拟路径映射
@Configuration
public class MyPicConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        addResourceHandler()里配置需要映射的文件夹，此处代表映射文件夹user下的所有资源。
//        addResourceLocations()配置文件夹在系统中的路径，使用绝对路径，格式为“file:你的路径”
        registry.addResourceHandler("/img/**").addResourceLocations("file:E:\\我的毕业设计\\bookstore\\src\\main\\resources\\static\\img\\");
    }
}
