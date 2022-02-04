package com.alerta.dc.model;

import com.alerta.dc.config.JwtTokenUtil;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Version {
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Value("${version.name}")
	private String name;
	@Value("${version.ambient}")
	private String ambient;
	@Value("${version.datetime}")
	private String datetime;
	@Value("${spring.datasource.url}")
	private String instance;
	
	public String getInstance() {
		if(jwtTokenUtil.isAdmin()){
			return instance.split("/")[3].split("\\?")[0];
		}
		return "****";
	}
			
	public void setInstance(String instance) {
		this.instance = instance;
	}
	
	public String getName() {
		return name;
	}
			
	public void setName(String name) {
		this.name = name;
	}

	public String getAmbient() {
		return ambient;
	}

	public void setAmbient(String ambient) {
		this.ambient = ambient;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
}
