package com.fhzz.springbootdemo.controller.demo.quartz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo/quartz")
public class QuartzDemoController {

	@RequestMapping("/openMainContent")
	public String openMainContent() {
		return "demo/quartz/main-content";
	}

	@RequestMapping("/openIntro")
	public String openIntro() {
		return "demo/quartz/intro";
	}

	@RequestMapping("/openList")
	public String list(Model model) {
		return "quartz/list-job";
	}

}
