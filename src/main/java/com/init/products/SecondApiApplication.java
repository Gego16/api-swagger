package com.init.products;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@SpringBootApplication
@Configurable
@EnableSwagger2
public class SecondApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondApiApplication.class, args);
	}

}
