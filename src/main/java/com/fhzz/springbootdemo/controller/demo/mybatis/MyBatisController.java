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
		return "demo/mybatis/mainContent";
	}

	@RequestMapping("/openIntro")
	public String openIntro() {
		return "demo/mybatis/intro";
	}

	@RequestMapping("/openListExample")
	public String list(Model model) {
		return "demo/mybatis/list-example";
	}

	@RequestMapping("/queryList")
	@ResponseBody
	public Map<String, Object> list(OracleDemoTable oracleDemoTable, int pageNum, int pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		PageHelper.startPage(pageNum, pageSize);
		List<OracleDemoTable> list = myBatisService.findByContentOrNum(oracleDemoTable.getContent(),
				oracleDemoTable.getNum());
		PageInfo<OracleDemoTable> pageList = new PageInfo<OracleDemoTable>(list);
		result.put("totalCount", pageList.getTotal());
		result.put("result", pageList.getList());
		return result;
	}
}
