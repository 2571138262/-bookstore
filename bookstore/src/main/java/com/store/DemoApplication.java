package com.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描mybatis mapper 包路径
@MapperScan(basePackages = "com.store.mapper")
////// 扫描所有需要的包，包含一些自用的工具包 所在的路径
//@ComponentScan(basePackages = {"com.store", "org.n3r.idworker"})

// 开启定时认为
@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
