/**
 * 
 */
package com.fhzz.springbootdemo.service.demo.jpa;

import java.util.List;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;

/**
 * @author Administrator
 *
 */
public interface JpaService {

	List<OracleDemoTable> getAllList();

	void save(OracleDemoTable oracleDemoTable);

	OracleDemoTable findById(String id);

	void edit(OracleDemoTable oracleDemoTable);

	void delete(OracleDemoTable oracleDemoTable);

}
