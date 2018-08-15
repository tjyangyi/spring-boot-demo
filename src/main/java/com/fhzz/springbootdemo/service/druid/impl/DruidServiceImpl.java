package com.fhzz.springbootdemo.service.druid.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.springbootdemo.dao.master.jpa.UserRepository;
import com.fhzz.springbootdemo.dao.master.mybatis.UserMapper;
import com.fhzz.springbootdemo.dao.slave1.jpa.MysqlTabelTestRepository;
import com.fhzz.springbootdemo.dao.slave1.mybatis.MysqlTableTestMapper;
import com.fhzz.springbootdemo.entity.master.Users;
import com.fhzz.springbootdemo.entity.slave1.MysqlTableTest;
import com.fhzz.springbootdemo.service.druid.DruidService;

@Service
public class DruidServiceImpl implements DruidService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private MysqlTabelTestRepository mysqlTabelTestRepository;
	
	@Autowired
	private MysqlTableTestMapper mysqlTableTestMapper;
	
	
	@Override
	public List<Users> getAllByMasterJpa() {
		return userRepository.findAll();
	}

	@Override
	public List<Users> getAllByMasterMybatis() {
		return userMapper.getAll();
	}

	@Override
	public List<MysqlTableTest> getAllBySlave1Jpa() {
		return mysqlTabelTestRepository.findAll();
	}

	@Override
	public List<MysqlTableTest> getAllBySlave1Mybatis() {
		return mysqlTableTestMapper.getAll();
	}

}
