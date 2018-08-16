/**
 * 
 */
package com.fhzz.springbootdemo.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "redirect:/index";
	}
		
		
	@RequestMapping("/index")
	public String index(Model model) {
		return "index/index";
	}
	
	@RequestMapping("/index2")
	public String index2(Model model) {
		return "index/index2";
	}
}
