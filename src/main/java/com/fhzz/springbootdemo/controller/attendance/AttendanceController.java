/**
 * 
 */
package com.fhzz.springbootdemo.controller.attendance;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fhzz.springbootdemo.entity.master.attendance.TAttendance;
import com.fhzz.springbootdemo.entity.master.attendance.TAttendanceDetail;
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

	@RequestMapping("/openAttendanceDetails")
	public String openAttendanceDetails(Model model, String attendanceId) {
		System.out.println(attendanceId);
		model.addAttribute("attendanceId", attendanceId);
		return "attendance/attendance-details";
	}

	/**
	 * 实现文件上传
	 */
	@RequestMapping("/fileUpload")
	@ResponseBody
	public Map<String, Object> fileUpload(MultipartFile fileInput, String year, String month) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = attendanceService.upload(fileInput, year, month);
		if ("true".equals(result)) {
			map.put("success", true);
		} else {
			map.put("success", false);
			map.put("reason", result);
		}
		return map;
	}

	@RequestMapping("/queryAttendanceList")
	@ResponseBody
	public Map<String, Object> queryAttendanceList() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<TAttendance> list = attendanceService.getAllList();
		result.put("totalCount", list.size());
		result.put("result", list);
		return result;
	}

	@RequestMapping("/queryAttendanceDetails")
	@ResponseBody
	public Map<String, Object> queryAttendanceDetails(String attendanceId) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<TAttendanceDetail> list = attendanceService.queryAttendanceDetails(attendanceId);
		result.put("totalCount", list.size());
		result.put("result", list);
		return result;
	}

	@RequestMapping("/download")
	public void Download(String attendanceId, HttpServletResponse response) throws UnsupportedEncodingException {
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition",
				"attachment;fileName=" + new String("产品产出线.xls".getBytes("GB2312"), "ISO-8859-1"));
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		File file = attendanceService.generateNewFile(attendanceId);

		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(file));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}