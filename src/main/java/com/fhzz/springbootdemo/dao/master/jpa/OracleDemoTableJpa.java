package com.fhzz.springbootdemo.dao.master.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;

public interface OracleDemoTableJpa extends JpaRepository<OracleDemoTable, Integer> {

	OracleDemoTable findById(String id);

}
