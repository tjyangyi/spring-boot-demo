/**
 * 
 */
package com.fhzz.springbootdemo.controller.demo.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/demo/thymeleaf")
public class ThymeleafController {

	@RequestMapping("/openMainContent")
	public String openMainContent() {
		return "demo/thymeleaf/mainContent";
	}

	@RequestMapping("/openIntro")
	public String openIntro() {
		return "demo/thymeleaf/intro";
	}

	@RequestMapping("/openExpress")
	public String openExpress() {
		return "demo/thymeleaf/express";
	}

	@RequestMapping("/openThLabel")
	public String openThLabel() {
		return "demo/thymeleaf/th-label";
	}

	@RequestMapping("/openUsage")
	public String openUsage() {
		return "demo/thymeleaf/usage";
	}

	@RequestMapping("/openLayout")
	public String openLayout() {
		return "demo/thymeleaf/layout";
	}

}
