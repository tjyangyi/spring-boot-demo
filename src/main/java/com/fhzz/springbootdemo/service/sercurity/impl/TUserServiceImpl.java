/**
 * 
 */
package com.fhzz.springbootdemo.service.sercurity.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fhzz.springbootdemo.dao.master.security.jpa.TUserJpa;
import com.fhzz.springbootdemo.entity.master.security.TUser;
import com.fhzz.springbootdemo.service.sercurity.TUserService;

/**
 * @author Administrator
 *
 */
@Service
public class TUserServiceImpl implements TUserService {
	@Autowired
	private TUserJpa userJpa;

	@Override
	public void saveNewUser(String username, String realname) {
		TUser user = userJpa.findByUsername(username);
		if (user != null)
			return;
		user = new TUser();
		user.setUsername(username);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode("1234"));
		user.setRealname(realname);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		userJpa.save(user);
	}

	@Override
	public void updateUser(TUser user) {
		userJpa.save(user);
	}

	@Override
	public TUser findByUsername(String username) {
		return userJpa.findByUsername(username);
	}

}
