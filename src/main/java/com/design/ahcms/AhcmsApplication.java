package com.design.ahcms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = {"com.design.ahcms.mapper"})
@EnableTransactionManagement
@EnableCaching
public class AhcmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AhcmsApplication.class, args);
    }

}
