/**
 * 
 */
package com.fhzz.springbootdemo.service.demo.mybatis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fhzz.springbootdemo.dao.master.demo.mapper.MyBatisMapper;
import com.fhzz.springbootdemo.entity.master.OracleDemoTable;
import com.fhzz.springbootdemo.service.demo.mybatis.MyBatisService;

/**
 * @author Administrator
 *
 */
@Service
public class MyBatisServiceImpl implements MyBatisService {

	@Autowired
	private MyBatisMapper myBatisMapper;

	@Override
	public List<OracleDemoTable> findByContentOrNum(String content, Double num) {
		return myBatisMapper.findByContentOrNum(content, num);
	}

}
