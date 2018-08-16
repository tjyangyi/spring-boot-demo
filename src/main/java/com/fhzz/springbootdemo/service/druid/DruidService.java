/**
 * 
 */
package com.fhzz.springbootdemo.service.druid;

import java.util.List;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;
import com.fhzz.springbootdemo.entity.slave1.MysqlDemoTable;

/**
 * @author Administrator
 *
 */
public interface DruidService {

	List<OracleDemoTable> getAllByMasterJpa();

	List<OracleDemoTable> getAllByMasterMybatis();

	List<MysqlDemoTable> getAllBySlave1Jpa();

	List<MysqlDemoTable> getAllBySlave1Mybatis();

}
