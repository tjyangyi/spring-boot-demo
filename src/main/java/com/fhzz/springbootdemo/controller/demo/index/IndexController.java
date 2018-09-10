/**
 * 
 */
package com.fhzz.springbootdemo.controller.demo.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/demo/index")
public class IndexController {
		
	@RequestMapping("/index")
	public String index(Model model) {
		return "demo/index/index";
	}
	
}
