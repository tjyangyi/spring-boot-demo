/**
 * 
 */
package com.fhzz.springbootdemo.service.druid;

import java.util.List;

import com.fhzz.springbootdemo.entity.master.Users;
import com.fhzz.springbootdemo.entity.slave1.MysqlTableTest;

/**
 * @author Administrator
 *
 */
public interface DruidService {

	List<Users> getAllByMasterJpa();

	List<Users> getAllByMasterMybatis();

	List<MysqlTableTest> getAllBySlave1Jpa();

	List<MysqlTableTest> getAllBySlave1Mybatis();

}
