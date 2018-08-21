package com.fhzz.springbootdemo.controller.demo.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhzz.springbootdemo.entity.master.OracleDemoTable;
import com.fhzz.springbootdemo.service.demo.jpa.JpaService;
import com.fhzz.springbootdemo.util.SystemStaticConst;
import com.fhzz.springbootdemo.util.json.JsonHelper;

@RestController
@RequestMapping("/demo/jpa")
public class JpaRestController {
	@Autowired
	private JpaService jpaService;

	@RequestMapping("/queryList")
	public Map<String, Object> list(OracleDemoTable oracleDemoTable) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<OracleDemoTable> list;
		if (!"".equals(oracleDemoTable.getContent()) || oracleDemoTable.getNum() != null) {
			list = jpaService.findByContentOrNum(oracleDemoTable.getContent(), oracleDemoTable.getNum());
		} else {
			list = jpaService.getAllList();
		}

		result.put("totalCount", list.size());
		result.put("result", list);
		return result;
	}

	@RequestMapping("/add")
	public Map<String, Object> save(OracleDemoTable oracleDemoTable) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			jpaService.save(oracleDemoTable);
			result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
			result.put(SystemStaticConst.MSG, "增加数据成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
			result.put(SystemStaticConst.MSG, "增加数据失败！");
		}
		return result;
	}

	@RequestMapping("/edit")
	public Map<String, Object> edit(OracleDemoTable oracleDemoTable) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			jpaService.edit(oracleDemoTable);
			result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
			result.put(SystemStaticConst.MSG, "更新数据成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
			result.put(SystemStaticConst.MSG, "更新数据失败！");
		}
		return result;
	}

	@RequestMapping("/delete")
	public Map<String, Object> delete(String json) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<OracleDemoTable> list = (List<OracleDemoTable>) JsonHelper.toList(json, OracleDemoTable.class);
			for (OracleDemoTable oracleDemoTable : list) {
				jpaService.delete(oracleDemoTable);
			}
			result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
			result.put(SystemStaticConst.MSG, "删除数据成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
			result.put(SystemStaticConst.MSG, "删除数据失败！");
		}
		return result;
	}

}
