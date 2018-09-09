package com.skat.smev.fns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SmevFnsTransformerApplication extends SpringBootServletInitializer{

//	public static void main(String[] args) {
//		SpringApplication.run(SmevFnsTransformerApplication.class, args);
//	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SmevFnsTransformerApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SmevFnsTransformerApplication.class, args);
	}
}
