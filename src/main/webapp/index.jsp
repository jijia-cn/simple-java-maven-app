<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Spring boot web demo</title>
</head>
<body>
<h1>Hello spring boot web demo</h1><br>
跳转页面：
<a href="hello">Jump to hello jsp</a><br>
获取一个对象数据:
<a href="user">fetch user</a><br>
获取list对象集合数据：
<a href="users">fetch users</a><br>
获取map对象数据：
<a href="usermap">fetch user map</a>
<hr>
使用myBatis对数据库操作<br>
<a href="addUser?userName=zhangsan&age=33">add User</a><br>
<a href="updateUser?id=2&userName=zhangsanUpdate&age=53">update User</a><br>
<a href="removeUser?id=55555">remove User</a><br>
<a href="listUser">list User</a>
</body>
</html>