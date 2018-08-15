package com.fhzz.springbootdemo.dao.master.mybatis;

import java.util.List;

import com.fhzz.springbootdemo.entity.master.Users;

public interface UserMapper {

	List<Users> getAll();

	Users getOne(Long id);

}
