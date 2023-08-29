package com.yunshucloud.shopping_common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ShoppingCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCommonApplication.class, args);
    }

}
