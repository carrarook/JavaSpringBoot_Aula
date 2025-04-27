package com.javaweb.app03_ControllerMVCMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "service", "model", "com.javaweb.app03_ControllerMVCMVC"})
public class App03ControllerMvcmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(App03ControllerMvcmvcApplication.class, args);
	}

}
