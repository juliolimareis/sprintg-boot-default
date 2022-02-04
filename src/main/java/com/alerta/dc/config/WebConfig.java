package com.alerta.dc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

// public class WebConfig implements WebMvcConfigurer {
	
	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
		// registry.addMapping("/**");// <- assim permite de qualquer origem, troque
		// "/**" pelo seu dominio por exemplo "http://meudominio.com"
		// }
		// }
		
@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig implements WebMvcConfigurer {
	// String[] domains = new String[] { "cepdec.ecops.com.br" };

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}

}
