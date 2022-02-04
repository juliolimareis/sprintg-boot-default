package com.alerta.dc.controller;

import com.alerta.dc.model.Version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/version")
public class VersionController {

	@Autowired
	Version version;

	@GetMapping
	public Version getVersion() {
		return version;
	}
}

