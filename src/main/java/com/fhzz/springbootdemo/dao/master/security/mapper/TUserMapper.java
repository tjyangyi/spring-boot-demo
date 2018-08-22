/**
 * 
 */
package com.fhzz.springbootdemo.dao.master.security.mapper;

import com.fhzz.springbootdemo.entity.master.security.TUser;

/**
 * @author Administrator
 *
 */
public interface TUserMapper {
	TUser findByUsername(String username);

}
