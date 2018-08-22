package com.fhzz.springbootdemo.service.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fhzz.springbootdemo.dao.master.security.mapper.TUserMapper;
import com.fhzz.springbootdemo.entity.master.security.TUser;

public class CustomUserService implements UserDetailsService {
	@Autowired
	private TUserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TUser user = userMapper.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		return user;
	}

}
