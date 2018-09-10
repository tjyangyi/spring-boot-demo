/**
 * 
 */
package com.fhzz.springbootdemo.controller.demo.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/demo/logback")
public class LogbackController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/openMainContent")
	public String openMainContent() {
		return "demo/logback/mainContent";
	}

	@RequestMapping("/openIntro")
	public String openIntro() {
		return "demo/logback/intro";
	}

	@RequestMapping("/openExample")
	public String openExample() {
		return "demo/logback/example";
	}

	@RequestMapping("/pringAllLogs")
	@ResponseBody
	public String pringAllLogs() {
		// 日志级别从低到高分为TRACE < DEBUG < INFO < WARN < ERROR <
		// FATAL，如果设置为WARN，则低于WARN的信息都不会输出。
		logger.trace("日志输出 trace");
		logger.debug("日志输出 debug");
		logger.info("日志输出 info");
		logger.warn("日志输出 warn");
		logger.error("日志输出 error");
		return null;
	}
}
