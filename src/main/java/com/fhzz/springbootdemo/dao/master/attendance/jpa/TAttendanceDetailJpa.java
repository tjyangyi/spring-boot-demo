package com.fhzz.springbootdemo.dao.master.attendance.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhzz.springbootdemo.entity.master.attendance.TAttendanceDetail;

public interface TAttendanceDetailJpa extends JpaRepository<TAttendanceDetail, Integer> {
	List<TAttendanceDetail> findByAttendaceIdOrderByIndexNumberAsc (String attendaceId);

	TAttendanceDetail findByAttendaceIdAndUsername(String attendanceId, String username);

	List<TAttendanceDetail> findByAttendaceIdAndUsernameOrderByIndexNumberAsc(String id, String username);

}
