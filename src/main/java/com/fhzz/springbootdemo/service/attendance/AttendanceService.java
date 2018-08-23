package com.fhzz.springbootdemo.service.attendance;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fhzz.springbootdemo.entity.master.attendance.TAttendance;
import com.fhzz.springbootdemo.entity.master.attendance.TAttendanceDetail;

public interface AttendanceService {

	String upload(MultipartFile fileInput, String year, String month);

	List<TAttendance> getAllList();

	List<TAttendanceDetail> queryAttendanceDetails(String attendanceId);

	File generateNewFile(String attendanceId);

	List<TAttendanceDetail> queryAttendanceDetailsByYearMonth(String year, String month);

	List<TAttendanceDetail> queryAttendanceDetailsByYearMonthUsername(String year, String month, String username);

}
