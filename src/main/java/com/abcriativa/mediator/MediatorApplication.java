package com.abcriativa.mediator;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * App thet intercept leads call to treats anything to improve de sells results...
 * Running in Java 8
 */
@SpringBootApplication
@RestController
public class MediatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediatorApplication.class, args);
	}

	@RequestMapping(value = "/apptest")
	public String firstApp(){
		return "My Google service is running fine with https...";
	}

}
