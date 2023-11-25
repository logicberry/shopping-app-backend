package com.codegenius.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.codegenius.shop")
public class ShoppingAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShoppingAppApplication.class, args);
	}

}
