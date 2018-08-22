package com.fhzz.springbootdemo.service.attendance;

import org.springframework.web.multipart.MultipartFile;

import com.fhzz.springbootdemo.entity.master.attendance.TAttendance;

public interface AttendanceService {

	String upload(MultipartFile fileInput, String year, String month);

}
