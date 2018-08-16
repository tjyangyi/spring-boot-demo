package com.fhzz.springbootdemo.dao.slave1.mybatis;

import java.util.List;

import com.fhzz.springbootdemo.entity.slave1.MysqlDemoTable;

public interface MysqlDemoTableMapper {

	List<MysqlDemoTable> getAll();

	MysqlDemoTable getOne(Long id);

}
