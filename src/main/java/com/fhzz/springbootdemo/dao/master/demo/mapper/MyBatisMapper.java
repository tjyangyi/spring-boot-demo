/**
 * 
 */
package com.fhzz.springbootdemo.dao.master.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;

/**
 * @author Administrator
 *
 */
public interface MyBatisMapper {

	List<OracleDemoTable> findByContentOrNum(@Param("content") String content, @Param("num") Double num);

}
