package com.fhzz.springbootdemo.dao.master.demo.mapper;

import java.util.List;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;

public interface OracleDemoTableMapper {

	List<OracleDemoTable> getAll();

	OracleDemoTable getOne(Long id);

}
