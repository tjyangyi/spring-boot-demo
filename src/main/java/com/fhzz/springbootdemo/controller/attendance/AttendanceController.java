/**
 * 
 */
package com.fhzz.springbootdemo.controller.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fhzz.springbootdemo.service.attendance.AttendanceService;

/**
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;

	@RequestMapping("/index")
	public String index(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		if ("admin".equals(username)) {
			return "attendance/admin";
		} else {
			return "attendance/index";
		}
	}

	/**
	 * 实现文件上传
	 */
	@RequestMapping("/fileUpload")
	@ResponseBody
	public String fileUpload(MultipartFile fileInput, String year, String month) {
		return attendanceService.upload(fileInput,year,month);
	}

}