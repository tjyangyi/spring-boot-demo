/**
 * 
 */
package com.fhzz.springbootdemo.service.demo.jpa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.springbootdemo.dao.master.jpa.OracleDemoTableJpa;
import com.fhzz.springbootdemo.entity.master.OracleDemoTable;
import com.fhzz.springbootdemo.service.demo.jpa.JpaService;

/**
 * @author Administrator
 *
 */
@Service
public class JpaServiceImpl implements JpaService {
	@Autowired
	private OracleDemoTableJpa oracleDemoTableJpa;

	@Override
	public List<OracleDemoTable> getAllList() {
		return oracleDemoTableJpa.findAll();
	}

	@Override
	public void save(OracleDemoTable oracleDemoTable) {
		oracleDemoTableJpa.save(oracleDemoTable);
	}

	@Override
	public OracleDemoTable findById(String id) {
		return oracleDemoTableJpa.findById(id);
	}

	@Override
	public void edit(OracleDemoTable oracleDemoTable) {
		oracleDemoTableJpa.save(oracleDemoTable);

	}

	@Override
	public void delete(OracleDemoTable oracleDemoTable) {
		oracleDemoTableJpa.delete(oracleDemoTable);
	}

}
