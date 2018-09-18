package com.fhzz.springbootdemo.service.druid.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.springbootdemo.dao.master.jpa.OracleDemoTableJpa;
import com.fhzz.springbootdemo.dao.master.mybatis.OracleDemoTableMapper;
import com.fhzz.springbootdemo.dao.slave1.jpa.MysqlDemoTableJpa;
import com.fhzz.springbootdemo.dao.slave1.mybatis.MysqlDemoTableMapper;
import com.fhzz.springbootdemo.entity.master.OracleDemoTable;
import com.fhzz.springbootdemo.entity.slave1.MysqlDemoTable;
import com.fhzz.springbootdemo.service.druid.DruidService;

@Service
public class DruidServiceImpl implements DruidService {
	@Autowired
	private OracleDemoTableJpa oracleDemoTableJpa;

	@Autowired
	private OracleDemoTableMapper oracleDemoTableMapper;

	@Autowired
	private MysqlDemoTableJpa mysqlDemoTableJpa;

	@Autowired
	private MysqlDemoTableMapper mysqlDemoTableMapper;

	@Override
	public List<OracleDemoTable> getAllByMasterJpa() {
		return oracleDemoTableJpa.findAll();
	}

	@Override
	public List<OracleDemoTable> getAllByMasterMybatis() {
		return oracleDemoTableMapper.selectAll();
	}

	@Override
	public List<MysqlDemoTable> getAllBySlave1Jpa() {
		return mysqlDemoTableJpa.findAll();
	}

	@Override
	public List<MysqlDemoTable> getAllBySlave1Mybatis() {
		return mysqlDemoTableMapper.getAll();
	}

}
