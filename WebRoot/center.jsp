<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html>
<html>
<head>
<title>欢迎页面</title>
<base href="<%=basePath%>" />

<style type="text/css">
p { 
	text-align:center; 
	padding:44px; 
	margin:0; 
	font-family:Arial, Helvetica, sans-serif; 
	font-size:70px; 
	font-weight:bold; 
	color:#000;
	margin-top:50px;
}
</style>
</head>
<body>
	<div id="main">
	     <marquee direction="left"><p>欢迎使用富民派遣系统<br/></p></marquee> 
	</div>
</body>
</html>

