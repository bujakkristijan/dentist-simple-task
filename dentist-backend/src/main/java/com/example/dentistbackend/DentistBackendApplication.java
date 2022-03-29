package com.example.dentistbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.pattern.PathPattern;

@SpringBootApplication
@CrossOrigin(origins = "*")	
public class DentistBackendApplication {
	
	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowedMethods("*").allowCredentials(true);
			}
		};
	} */

	public static void main(String[] args) {
		SpringApplication.run(DentistBackendApplication.class, args);
	}

}
