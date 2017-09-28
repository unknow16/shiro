package com.fuyi.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestEncode {

	@Test
	public void testEncode() {
		/**
		 * md5 sha 加密
		 * toHash() 编码
		 * toBase64() 编码
		 * 
		 */
		System.out.println(new Md5Hash("123"));
		
		System.out.println(new Md5Hash("123", "user"));
		System.out.println(new Sha1Hash("123", "user").toBase64());
	}
	
	@Test
	public void testPasswordService() {
		
		DefaultPasswordService service = new DefaultPasswordService();
		String mm = service.encryptPassword("123");
		String m1 = service.encryptPassword("123");
		System.out.println(mm.equals(m1));
		System.out.println(service.passwordsMatch("123", mm));
		
	}
	@Test
	public void testPasswordServiceRealm() {
		Subject subject = login("kh", "123");
		
		System.out.println(subject.getPrincipal());
		//System.out.println(subject.getPrincipals().getRealmNames());
		
	}
	

	
	public Subject login(String username, String password) {
		SecurityManager manager = new IniSecurityManagerFactory("classpath:shiro-password.ini").getInstance();
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
