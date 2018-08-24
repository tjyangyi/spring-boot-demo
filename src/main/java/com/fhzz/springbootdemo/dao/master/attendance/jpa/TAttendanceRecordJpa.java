/**
 * 
 */
package com.fhzz.springbootdemo.dao.master.attendance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhzz.springbootdemo.entity.master.attendance.TAttendanceRecord;

/**
 * @author Administrator
 *
 */
public interface TAttendanceRecordJpa extends JpaRepository<TAttendanceRecord, String> {

}
