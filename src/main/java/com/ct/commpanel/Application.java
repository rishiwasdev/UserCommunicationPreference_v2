package com.ct.commpanel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
@EntityScan( basePackages = "com.ct.commpanel.model" )
@ComponentScan( "com.ct.commpanel" )
public class Application {
	public static void main( String[] args ) {
		SpringApplication.run(Application.class, args);
	}
}