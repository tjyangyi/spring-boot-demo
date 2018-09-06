package com.fhzz.springbootdemo.service.shiro;


import com.fhzz.springbootdemo.entity.master.shiro.UserInfo;

public interface UserInfoService {
	/** 通过username查找用户信息; */
	public UserInfo findByUsername(String username);
}