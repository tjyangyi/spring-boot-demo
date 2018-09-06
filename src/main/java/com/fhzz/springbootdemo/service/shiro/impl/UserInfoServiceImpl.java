package com.fhzz.springbootdemo.service.shiro.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fhzz.springbootdemo.dao.master.shiro.jpa.UserInfoDao;
import com.fhzz.springbootdemo.entity.master.shiro.UserInfo;
import com.fhzz.springbootdemo.service.shiro.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}