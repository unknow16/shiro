package com.fuyi.shiro.test;

import java.util.Arrays;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestShiro {

	@Test
	public void testBase() {
		Subject subject = login("kh", "123");
		
		PrincipalCollection principals = subject.getPrincipals();
		System.out.println(principals.getRealmNames());
		System.out.println(principals.asList());
	}
	
	@Test
	public void testRole() {
		Subject subject = login("kh", "123");
		System.out.println(subject.hasRole("r1"));
		System.out.println(subject.hasAllRoles(Arrays.asList("r1", "r2", "r3"))); //是否都有
		System.out.println(subject.hasRoles(Arrays.asList("r1", "r2", "r3"))[2]); //返回数组对象，每个角色是否有的boolean
		
		subject.checkRole("r3");  //不具有该角色，抛错， hasRole*返回boolean
	}
	
	@Test
	public void testPermission() {
		Subject subject = login("ls", "123");
		System.out.println(subject.isPermitted("user:query"));
		System.out.println(subject.isPermitted("topic:*"));
		
		System.out.println(subject.isPermitted("classroom:test:delete:aa"));
		
		System.out.println(subject.isPermitted("admin:user:add:1"));
		
		System.out.println(subject.isPermitted("admin:user:view"));
	}
	
	@Test
	public void testPermissionResolver() {
		Subject subject = login("kh", "123");
		
		PrincipalCollection principals = subject.getPrincipals();
		System.out.println(principals.getRealmNames());
		System.out.println(principals.asList());
		
		//System.out.println(subject.hasRole("r1"));
		//System.out.println(subject.isPermitted("upms:create"));
		//System.out.println(subject.isPermitted("+user+view"));
		
		System.out.println(subject.isPermitted("cms:create"));
	}
	
	public Subject login(String username, String password) {
		SecurityManager manager = new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
		SecurityUtils.setSecurityManager(manager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("用户名不存在");
		} catch (IncorrectCredentialsException e) {
			System.out.println("密码错误");
		}
		return subject;
	}
}
