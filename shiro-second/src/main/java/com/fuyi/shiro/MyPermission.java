package com.fuyi.shiro;

import org.apache.shiro.authz.Permission;

public class MyPermission implements Permission {
	
	private String resourceId;
	private String operator;
	private String instanceId;
	
	public MyPermission() {}
	
	public MyPermission(String permissionStr) {
		if(permissionStr.trim() != null) {
			String[] perms = permissionStr.split("\\+");
			if(perms.length > 1) {
				this.resourceId = perms[1];
			}
			if(perms.length > 2) {
				this.operator = perms[2];
			}
			if(perms.length > 3) {
				this.instanceId = perms[3];
			}
		}
	}

	public boolean implies(Permission p) {
		
		if(!(p instanceof MyPermission)) return false;
		
		MyPermission myPerm = (MyPermission) p;
		if(this.getResourceId() != null && !this.getResourceId().equals("")
				&& !this.getResourceId().equals(myPerm.getResourceId())) {
			return false;
		}
		
		if(this.getOperator() != null && !this.getOperator().equals("")
				&& !this.getOperator().equals(myPerm.getOperator())){
			return false;
		}
		
		if(this.getInstanceId() != null && !this.getInstanceId().equals("")
				&& !this.getInstanceId().equals(myPerm.getInstanceId())){
			return false;
		}
		return true;
	}

	
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
}
