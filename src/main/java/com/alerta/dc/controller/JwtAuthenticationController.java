package com.alerta.dc.controller;

import com.alerta.dc.model.UserApi;
import com.alerta.dc.model.JwtRequest;
import com.alerta.dc.model.JwtResponse;
import com.alerta.dc.config.JwtTokenUtil;
import com.alerta.dc.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		Long idUser = authenticate(authenticationRequest);
		
		if(idUser != null){
			final String token = jwtTokenUtil.generateMasterToken();
			return ResponseEntity.ok(new JwtResponse(token));
		}

		return new ResponseEntity<>("{\"error\":\"invalid user\"}", HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping(value = "/generateToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> generateTokenPerson(@RequestBody UserApi createToken) throws Exception {
		final String token = jwtTokenUtil.doGenerateTokenPerson(createToken);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private Long authenticate(JwtRequest jwtRequest) throws Exception {
		try {
			return userService.getAuthUser(jwtRequest).getIdUser();
		} catch (Exception e) {
			return null;
		}
	}
}