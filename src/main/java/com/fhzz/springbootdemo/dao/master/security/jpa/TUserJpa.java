package com.fhzz.springbootdemo.dao.master.security.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhzz.springbootdemo.entity.master.security.TUser;

public interface TUserJpa extends JpaRepository<TUser, String> {
	TUser findByUsername(String username);
}
