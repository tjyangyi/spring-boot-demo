package com.fhzz.springbootdemo.controller.demo.druid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;
import com.fhzz.springbootdemo.entity.slave1.MysqlDemoTable;
import com.fhzz.springbootdemo.service.druid.DruidService;

@RestController
@RequestMapping("/demo/druid")
public class DruidRestController {
	@Autowired
	private DruidService druidService;

	@RequestMapping("/getAllByMasterJpa")
	public List<OracleDemoTable> getAllByMasterJpa() {
		return druidService.getAllByMasterJpa();
	}

	@RequestMapping("/getAllByMasterMybatis")
	public List<OracleDemoTable> getAllByMasterMybatis() {
		return druidService.getAllByMasterMybatis();
	}

	@RequestMapping("/getAllBySlave1Jpa")
	public List<MysqlDemoTable> getAllBySlave1Jpa() {
		return druidService.getAllBySlave1Jpa();
	}

	@RequestMapping("/getAllBySlave1Mybatis")
	public List<MysqlDemoTable> getAllBySlave1Mybatis() {
		return druidService.getAllBySlave1Mybatis();
	}

}
