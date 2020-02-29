package com.my.blog.website;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@MapperScan("com.my.blog.website.dao")
@SpringBootApplication
@EnableTransactionManagement
public class CoreApplication {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        SpringApplication.run(CoreApplication.class, args);
    }
}
