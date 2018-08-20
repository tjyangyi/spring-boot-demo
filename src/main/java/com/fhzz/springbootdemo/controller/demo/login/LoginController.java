/**
 * 
 */
package com.fhzz.springbootdemo.controller.demo.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/demo/login")
public class LoginController {

	@RequestMapping("/login")
	public String index(Model model) {
		return "demo/login/login";
	}
}
