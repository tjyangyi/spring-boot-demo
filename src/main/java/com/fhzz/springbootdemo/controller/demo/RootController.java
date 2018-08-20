/**
 * 
 */
package com.fhzz.springbootdemo.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
public class RootController {
	@RequestMapping("/")
	public String index() {
		return "redirect:/demo/index/index";
	}
}
