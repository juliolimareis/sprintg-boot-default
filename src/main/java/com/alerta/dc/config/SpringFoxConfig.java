package com.alerta.dc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {                                    
	@Bean
	public Docket swagger() { 
		return new Docket(DocumentationType.SWAGGER_2)  
			.select()                                  
			.apis(RequestHandlerSelectors.any())              
			.paths(PathSelectors.any())                          
			.build();                                           
	}
}

