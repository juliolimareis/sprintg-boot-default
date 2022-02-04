package com.alerta.dc.model;

public class UserApi {
	private String userName;
	private Long idAgencia;
	private Long tokenExpirationMinutes;

	public Long getIdAgencia() {
		return idAgencia;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setIdAgencia(Long idAgencia) {
		this.idAgencia = idAgencia;
	}

	public Long getTokenExpirationMinutes() {
		return tokenExpirationMinutes;
	}

	public void setTokenExpirationMinutes(Long tokenExpirationMinutes) {
		this.tokenExpirationMinutes = tokenExpirationMinutes;
	}
}