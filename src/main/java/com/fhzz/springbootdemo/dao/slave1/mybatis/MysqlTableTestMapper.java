package com.fhzz.springbootdemo.dao.slave1.mybatis;

import java.util.List;

import com.fhzz.springbootdemo.entity.master.Users;
import com.fhzz.springbootdemo.entity.slave1.MysqlTableTest;

public interface MysqlTableTestMapper {

	List<MysqlTableTest> getAll();

	Users getOne(Long id);

}
