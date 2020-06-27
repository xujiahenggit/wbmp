package com.bank.build;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@ComponentScan("com.bank")
@EntityScan("com.bank")
@MapperScan("com.bank.*.dao")
public class WbmpStartApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WbmpStartApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WbmpStartApplication.class);
    }
}
