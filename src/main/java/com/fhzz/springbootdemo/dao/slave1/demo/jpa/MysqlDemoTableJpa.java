package com.fhzz.springbootdemo.dao.slave1.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhzz.springbootdemo.entity.slave1.MysqlDemoTable;

public interface MysqlDemoTableJpa extends JpaRepository<MysqlDemoTable, Integer> {

	MysqlDemoTable findById(String id);

	String deleteById(String id);
}
