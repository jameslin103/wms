<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'InputExcel.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   			<h1><s:property value="excelWorkSheet.sheetName" /> </h1>  
  <p>  
    <s:iterator value="excelWorkSheet.columns">  
        <s:property />  ||   
    </s:iterator>  
  </p>  
    
  <s:iterator var="user" value="excelWorkSheet.data">  
    <p>  
        <s:property value="#user.id"/>     
        <s:property value="#user.name"/>     
        <s:property value="#user.pass"/>     
        <s:property value="#user.lastname"/>     
        <s:property value="#user.addres"/>      
        <s:property value="#user.remark"/>      
    </p>  
  </s:iterator>
   <input type="button" onclick="javascript:window.location.href='<s:action name="userInfo" namespace="wms"/>'" value="返回">
   
   
   
   
   
   
   
   
   
   
   
  </body>
</html>
