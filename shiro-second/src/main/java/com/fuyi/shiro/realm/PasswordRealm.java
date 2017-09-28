package com.fuyi.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class PasswordRealm extends AuthorizingRealm {

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//String p = "$shiro1$SHA-256$500000$5r/CLU/TOaQ+3rlSeI84Zg==$4BbN1eXF8YMmpnMluKinThOLLsehd9Fj7zhrT6AX7dM=";
		String p = "6ad14ba9986e3615423dfca256d04e3f";
		String salt = "user";
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo("fuyi@qq.com", p, getName());
		info.setCredentialsSalt(ByteSource.Util.bytes(salt));
		return info;
	}

}
