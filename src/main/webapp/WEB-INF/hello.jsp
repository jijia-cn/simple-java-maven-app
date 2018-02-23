<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hello Jsp file</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="<%=basePath %>js/test2.js"></script>
</head>
<body>
<h2>Hello jsp file</h2>
<%=basePath %>
<hr>
<img alt="" src="<%=basePath%>images/background.jpg" style="height: 200px;width: 300px;">
</body>
</html>