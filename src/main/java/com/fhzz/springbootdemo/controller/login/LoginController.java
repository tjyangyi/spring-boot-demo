/**
 * 
 */
package com.fhzz.springbootdemo.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
public class LoginController {
	@RequestMapping("/login")
	public String index(Model model) {
		return "login/login";
	}
}
