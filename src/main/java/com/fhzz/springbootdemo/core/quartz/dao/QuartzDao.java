package com.fhzz.springbootdemo.core.quartz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhzz.springbootdemo.core.quartz.entity.QrtzJobDetails;
import com.fhzz.springbootdemo.core.quartz.entity.QrtzJobDetailsId;

/**
 * @author YangYi
 * 
 */
public interface QuartzDao extends JpaRepository<QrtzJobDetails, QrtzJobDetailsId> {
}
