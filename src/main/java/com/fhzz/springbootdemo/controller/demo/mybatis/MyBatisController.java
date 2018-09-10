package com.fhzz.springbootdemo.controller.demo.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;
import com.fhzz.springbootdemo.service.demo.mybatis.MyBatisService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/demo/mybatis")
public class MyBatisController {

	@Autowired
	private MyBatisService myBatisService;

	@RequestMapping("/openMainContent")
	public String openMainContent() {
		return "demo/mybatis/main-content";
	}

	@RequestMapping("/openIntro")
	public String openIntro() {
		return "demo/mybatis/intro";
	}

	@RequestMapping("/openListExample")
	public String openListExample(Model model) {
		return "demo/mybatis/list-example";
	}
	
	@RequestMapping("/openPagehelperExample")
	public String openPagehelperExample(Model model) {
		return "demo/mybatis/pagehelper-example";
	}

	@RequestMapping("/queryList")
	@ResponseBody
	public Map<String, Object> queryList(OracleDemoTable oracleDemoTable) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<OracleDemoTable> list = myBatisService.findByContentOrNum(oracleDemoTable.getContent(),
				oracleDemoTable.getNum());
		result.put("total", list.size());
		result.put("list", list);
		return result;
	}
	
	@RequestMapping("/queryPagedList")
	@ResponseBody
	public PageInfo<OracleDemoTable> queryPagedList(OracleDemoTable oracleDemoTable, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum + 1, pageSize);
		List<OracleDemoTable> list = myBatisService.findByContentOrNum(oracleDemoTable.getContent(),
				oracleDemoTable.getNum());
		PageInfo<OracleDemoTable> pageInfo = new PageInfo<OracleDemoTable>(list);
		return pageInfo;
	}
}
