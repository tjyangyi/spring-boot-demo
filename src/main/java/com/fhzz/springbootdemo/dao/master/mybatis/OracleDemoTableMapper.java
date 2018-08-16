package com.fhzz.springbootdemo.dao.master.mybatis;

import java.util.List;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;

public interface OracleDemoTableMapper {

	List<OracleDemoTable> getAll();

	OracleDemoTable getOne(Long id);

}
