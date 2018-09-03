/**
 * 
 */
package com.fhzz.springbootdemo.service.demo.mybatis;

import java.util.List;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;

/**
 * @author Administrator
 *
 */
public interface MyBatisService {

	List<OracleDemoTable> findByContentOrNum(String content, Double num);

}
