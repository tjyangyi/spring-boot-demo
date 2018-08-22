/**
 * 
 */
package com.fhzz.springbootdemo.controller.attendance;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

	@RequestMapping("/index")
	public String index(Model model) {
		return "attendance/index";
	}
}