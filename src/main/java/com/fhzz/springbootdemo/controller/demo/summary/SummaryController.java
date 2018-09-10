/**
 * 
 */
package com.fhzz.springbootdemo.controller.demo.summary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/demo/summary")
public class SummaryController {
		
	@RequestMapping("/openMainContent")
	public String index(Model model) {
		return "demo/summary/main-content";
	}
	
	@RequestMapping("/openSpringBootIntro")
	public String openSpringBootIntro(Model model) {
		return "demo/summary/spring-boot-intro";
	}
	
	@RequestMapping("/openMenuStructureIntro")
	public String openMenuStructureIntro(Model model) {
		return "demo/summary/menu-structure-intro";
	}
	
}
