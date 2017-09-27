<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String path = request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Hello <shiro:principal/></h1>
<shiro:guest>
	<a href="${path }login">用户登录</a>
</shiro:guest>
<shiro:user>
	<a href="${path }user/list.jsp">用户列表</a>
	
	<shiro:hasPermission name="user:add">
		<a href="${path }user/add.jsp">用户添加</a>
	</shiro:hasPermission>
	
	<shiro:hasRole name="admin">
		<a href="${path }admin/">管理员界面</a>
	</shiro:hasRole>
	
	<a href="${path }logout">退出系统</a>
</shiro:user>


</body>
</html>