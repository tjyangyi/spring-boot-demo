package com.fhzz.springbootdemo.controller.demo.shiro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo/shiro")
public class ShiroController {

	@RequestMapping("/openMainContent")
	public String openMainContent() {
		return "demo/shiro/mainContent";
	}

	@RequestMapping("/openIntro")
	public String openIntro() {
		return "demo/shiro/intro";
	}

	@RequestMapping("/openShiroThymeleaf")
	public String openShiroThymeleaf() {
		return "demo/shiro/shiro-thymeleaf";
	}

}
