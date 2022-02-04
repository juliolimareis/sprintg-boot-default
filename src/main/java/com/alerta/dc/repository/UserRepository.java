package com.alerta.dc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alerta.dc.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	@Query(value = "SELECT * FROM user WHERE email = ?1 AND password = ?2", nativeQuery = true)
	public User findByAuth(String email, String password);
}
