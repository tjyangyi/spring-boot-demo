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

import javax.servlet.http.HttpServletRequest;
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
import com.fhzz.springbootdemo.util.SystemStaticConst;
import com.fhzz.springbootdemo.util.login.IpUtil;

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
			return "attendance/normal";
		}
	}

	@RequestMapping("/getTitleTip")
	@ResponseBody
	public String getTitleTip(String year, String month) {
		return attendanceService.getTitleTip(year,month);
	}

	@RequestMapping("/openAttendanceDetails")
	public String openAttendanceDetails(Model model, String attendanceId) {
		model.addAttribute("attendanceId", attendanceId);
		return "attendance/attendance-details";
	}

	@RequestMapping("/openModifyAttendanceDetails")
	public String openModifyAttendanceDetails(Model model, String attendanceDetailId) {
		model.addAttribute("attendanceDetailId", attendanceDetailId);
		TAttendanceDetail attendanceDetail = attendanceService.queryTAttendanceDetailById(attendanceDetailId);
		model.addAttribute("attendanceDetail", attendanceDetail);
		return "attendance/modify-attendanced-details";
	}

	@RequestMapping("/modifyAttendanceDetails")
	@ResponseBody
	public Map<String, Object> modifyAttendanceDetails(TAttendanceDetail attendanceDetail,HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			attendanceService.modifyAttendanceDetails(attendanceDetail,IpUtil.getIpAddr(request));
			result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
			result.put(SystemStaticConst.MSG, "更新数据成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
			result.put(SystemStaticConst.MSG, "更新数据失败！");
		}
		return result;
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

	@RequestMapping("/queryAttendanceDetailsByYearMonth")
	@ResponseBody
	public Map<String, Object> queryAttendanceDetailsByYearMonth(String year, String month, String username) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<TAttendanceDetail> list;
		if ("".equals(username)) {
			list = attendanceService.queryAttendanceDetailsByYearMonth(year, month);
		} else {
			list = attendanceService.queryAttendanceDetailsByYearMonthUsername(year, month, username);
		}
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