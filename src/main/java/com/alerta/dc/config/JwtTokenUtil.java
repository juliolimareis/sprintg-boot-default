package com.alerta.dc.config;

import java.io.Serializable;


import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import com.alerta.dc.model.UserApi;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtTokenUtil implements Serializable {

	@Autowired
	HttpServletRequest request;

	private static final long serialVersionUID = -2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	// retorna o username do token jwt
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retorna expiration date do token jwt
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// para retornar qualquer informação do token nos iremos precisar da secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// gera token para user
	public String generateMasterToken() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userName", "admin");
		claims.put("idAgencia", 0);
		claims.put("permission", 0);
		return doGenerateToken(claims, 0L);
	}
	
	public String doGenerateTokenPerson(UserApi userApi) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userName", userApi.getUserName());
		claims.put("idAgencia", userApi.getIdAgencia());
		claims.put("permission", 1);
		return doGenerateToken(claims, userApi.getTokenExpirationMinutes());
	}

	// Cria o token e define tempo de expiração pra ele
	private String doGenerateToken(Map<String, Object> claims, Long tokenExpirationMinutes) {
		Long defaultValidity = JWT_TOKEN_VALIDITY;

		if(tokenExpirationMinutes > 0){
			defaultValidity = tokenExpirationMinutes;
		}

		return Jwts.builder()
			.setClaims(claims)
			.setSubject("api-alerta")
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + defaultValidity * 1000))
			.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public Map<String, Object> getClaimsToken(String token){
		final Claims claims = getAllClaimsFromToken(token);
		return (Map<String, Object>) claims;
	}

	public Long getIdAgencia(){
		return Long.parseLong(getClaimsToken(getToken()).get("idAgencia").toString());
	}

	public boolean isAdmin(){
		String token = getToken();
		if(token.isEmpty()){
			return false;
		} return true;
	}

	public String getToken(){
		String authHeader = request.getHeader("Authorization");
		if(authHeader != null && authHeader.startsWith("Bearer ")){
			return authHeader.substring(7);
		}
		return "";
	}

	// valida o token
	public Boolean validateToken(String token, UserDetails userDetails) {
		// final Claims claim = getAllClaimsFromToken(token);

		// System.out.println(claim.get("idAgencia"));
		// System.out.println(claim.get("permission"));

		return !isTokenExpired(token);
	}

}
