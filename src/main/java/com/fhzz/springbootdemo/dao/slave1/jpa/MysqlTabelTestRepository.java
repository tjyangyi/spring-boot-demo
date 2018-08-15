package com.fhzz.springbootdemo.dao.slave1.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhzz.springbootdemo.entity.slave1.MysqlTableTest;

public interface MysqlTabelTestRepository extends JpaRepository<MysqlTableTest, Integer> {

	MysqlTableTest findById(String id);

	String deleteById(String id);
}
