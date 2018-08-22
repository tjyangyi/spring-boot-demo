/**
 * 
 */
package com.fhzz.springbootdemo.controller.demo.bootstratp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/demo/bootstrap")
public class BootstrapController {
		
	@RequestMapping("/bootstrapDemo")
	public String index(Model model) {
		return "demo/bootstrap/bootstrapDemo";
	}

}
