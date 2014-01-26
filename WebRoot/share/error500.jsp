<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统繁忙</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/help/public_css_js.jsp"%>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<body>
	<div id="container"> 
		<div class="row-fluid">
			<div class="span3"></div>
	      	<div class="span6" style="min-height:400px; min-width:200px; background-color: #EBF4E1; border: 1px solid #d0ac6b; margin-top: 90px; border-radius: 25px;">
	       	 	<h1 style=" text-align: center;">系统提示!</h1><br/><br/>
	       	 	<div style=" text-align: center; margin-top:30px">
	       	 		<h2>未知的系统错误；请联系技术人员!<br/><br/><br/><br/>
	       	 		<a href="javascript:history.go(-1)" style="margin-top:30px">返回</a></h2>
	      		</div>
	      	</div>
  		</div>  	
	</div>
   				
   				
   				
   				
  </body>
</html>
