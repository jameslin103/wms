<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'OutExcel.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/styles.css">
	

  </head>
  
  <body>
    	<div align="center">
			<s:form name="form1" action="outPut" method="post">  
  			<span>用户资料信息</span>
  			<table >
  				<tr>
  					<td>用户名</td>
  					<td>邮箱</td>
  					<td>性别</td>
  					<td>密码</td>
  					<td>真实名</td>
  					<td>时间</td>
  					<td>状态</td>
  					<td>编号</td>
  				</tr>
  				
  				
  				
  				
  			</table>
        	<input type="hidden" name="format" value="xls" />  
    			<s:submit name="sub" value="导出数据"></s:submit>  
  			</s:form>   
  </div> 
  </body>
</html>
