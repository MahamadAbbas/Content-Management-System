package com.example.cms.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDoc {
	
	Contact contact() {
		return new Contact().name("Mahamad Abbas")
				.url("https://bejewelled-zabaione-fdd732.netlify.app")
				.email("mahamadabbas07@gmail.com");
	}
	
	@Bean
	Info info() {
		return new Info().title("Content Management System")
				.description("RESTful API with basic CRUD Operation")
				.version("v1").contact(contact());
	}
	
	@Bean		
	OpenAPI openAPI() {
		return new OpenAPI().info(info());
	}

}
