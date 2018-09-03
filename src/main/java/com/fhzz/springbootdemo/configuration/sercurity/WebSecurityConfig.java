package com.fhzz.springbootdemo.configuration.sercurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fhzz.springbootdemo.service.sercurity.CustomUserService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	UserDetailsService customUserService() {
		return new CustomUserService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	/**
	 * 描述：csrf().disable()为了关闭跨域访问的限制，若不关闭则websocket无法与后台进行连接
	 * 
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/demo/index/index").failureUrl("/login?error=true").permitAll().and().logout()
				.logoutSuccessUrl("/login").permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 解决静态资源被拦截的问题
		web.ignoring().antMatchers("/jquery/**").antMatchers("/bootstrap/**").antMatchers("/js/**")
				.antMatchers("/css/**").antMatchers("/download/**");

	}

}
