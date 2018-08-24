package com.fhzz.springbootdemo.service.sercurity;

import com.fhzz.springbootdemo.entity.master.security.TUser;

public interface TUserService {
	void saveNewUser(String username, String realname);

	void updateUser(TUser user);

	TUser findByUsername(String username);
}
