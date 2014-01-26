<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'switch.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%@ include file="/help/public_css_js.jsp"%>
  </head>
  
  <script type="text/javascript" language="JavaScript">
	function Submit_onclick(){
	    var win = window.parent.document.getElementById("myFrame");
	    if(win.cols == "183,8,*") {
	        win.cols="0,8,*";
	        document.getElementById("ImgArrow").src="resourse/switch_right.gif";
	        document.getElementById("ImgArrow").alt="打开左侧导航栏";
	    } else {
	        win.cols="183,8,*";
	        document.getElementById("ImgArrow").src="resourse/switch_left.gif";
	        document.getElementById("ImgArrow").alt="隐藏左侧导航栏";
	    }
	}
	function MyLoad() {
	    if(window.parent.location.href.indexOf("MainUrl")>0) {
	        window.top.midFrame.document.getElementById("ImgArrow").src="resourse/switch_right.gif";
	    }
	}
</script>
<body onload="MyLoad()">
<div id="switchpic"><a href="javascript:Submit_onclick()"><img src="images/001.gif" alt="隐藏左侧导航栏" id="ImgArrow" /></a></div>
</body>
</html>