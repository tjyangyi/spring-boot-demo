package com.fhzz.springbootdemo.dao.master.attendance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhzz.springbootdemo.entity.master.attendance.TAttendance;

public interface AttendanceJpa extends JpaRepository<TAttendance, String> {
	
	TAttendance findByYearAndMonth(String year, String month);
}
