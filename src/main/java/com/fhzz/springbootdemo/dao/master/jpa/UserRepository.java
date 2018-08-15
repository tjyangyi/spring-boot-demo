package com.fhzz.springbootdemo.dao.master.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhzz.springbootdemo.entity.master.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	Users findById(String id);

	String deleteById(String id);
}
