package com.fhzz.springbootdemo.controller.login;

import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
	//
	// @RequestMapping("/login")
	// public String login(Model model) {
	// return "login/login";
	// }
	//
	// @RequestMapping("/openModifyPassword")
	// public String openModifyPassword(Model model) {
	// return "login/modify-password";
	// }

	// @RequestMapping("/modifyPassword")
	// @ResponseBody
	// public Map<String, Object> modifyPassword(String oldPassword, String
	// newPassword) {
	// Map<String, Object> result = new HashMap<String, Object>();
	// UserDetails userDetails = (UserDetails)
	// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// TUser user = userSerivce.findByUsername(userDetails.getUsername());
	// String password = user.getPassword();
	// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	// boolean matched = encoder.matches(oldPassword, password);
	// if (!matched) {
	// result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
	// result.put(SystemStaticConst.MSG, "旧密码错误");
	// return result;
	// }
	// user.setPassword(encoder.encode(newPassword));
	// try {
	// userSerivce.updateUser(user);
	// result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
	// result.put(SystemStaticConst.MSG, "修改密码成功！");
	// } catch (Exception e) {
	// e.printStackTrace();
	// result.put(SystemStaticConst.RESULT, SystemStaticConst.FAIL);
	// result.put(SystemStaticConst.MSG, "修改密码失败！");
	// }
	// return result;
	// }
}
