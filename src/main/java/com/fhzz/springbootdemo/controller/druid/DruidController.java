package com.fhzz.springbootdemo.controller.druid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhzz.springbootdemo.entity.master.Users;
import com.fhzz.springbootdemo.entity.slave1.MysqlTableTest;
import com.fhzz.springbootdemo.service.druid.DruidService;

@RestController
public class DruidController {
	@Autowired 
	private  DruidService druidService;

	@RequestMapping("/getAllByMasterJpa")
	public List<Users> getAllByMasterJpa(){
		return druidService.getAllByMasterJpa();
	}
	
	@RequestMapping("/getAllByMasterMybatis")
	public List<Users> getAllByMasterMybatis(){
		return druidService.getAllByMasterMybatis();
	}
	
	@RequestMapping("/getAllBySlave1Jpa")
	public List<MysqlTableTest> getAllBySlave1Jpa(){
		return druidService.getAllBySlave1Jpa();
	}
	
	@RequestMapping("/getAllBySlave1Mybatis")
	public List<MysqlTableTest> getAllBySlave1Mybatis(){
		return druidService.getAllBySlave1Mybatis();
	}

	}
