package com.fhzz.springbootdemo.controller.demo.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;
import com.fhzz.springbootdemo.service.demo.jpa.JpaService;

@Controller
@RequestMapping("/demo/jpa")
public class JpaController {
	@Autowired
	private JpaService jpaService;

	@RequestMapping("/openMainContent")
	public String openMainContent() {
		return "demo/jpa/mainContent";
	}
	
	@RequestMapping("/openIntro")
	public String openIntro() {
		return "demo/jpa/intro";
	}
	
	
	
	@RequestMapping("/openList")
	public String list(Model model) {
		return "demo/jpa/list";
	}

	@RequestMapping("/toAdd")
	public String toAdd() {
		return "demo/jpa/add";
	}

	@RequestMapping("/toEdit")
	public String toEdit(Model model, String id) {
		OracleDemoTable oracleDemoTable = jpaService.findById(id);
		model.addAttribute("oracleDemoTable", oracleDemoTable);
		return "demo/jpa/edit";
	}

}
