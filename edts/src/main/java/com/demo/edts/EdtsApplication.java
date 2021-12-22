package com.demo.edts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.demo.edts")
public class EdtsApplication extends SpringBootServletInitializer {

	private static final Logger log = LoggerFactory.getLogger(EdtsApplication.class);
	
	public static void main(String[] args) {
    	log.info("Starting Edts Demo Application..");

		final ApplicationContext ctx = SpringApplication.run(EdtsApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EdtsApplication.class);
    }
}
