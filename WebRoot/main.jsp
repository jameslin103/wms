<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>富民人力银行</title>
<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>
<link rel="shortcut icon" href="images/favicon.ico" />
<script type="text/javascript">
	function go(url)
	{
		$("#main").attr("src",url);
	}
</script>
</head>
<frameset rows="60,*,24" framespacing="0" frameborder="no" border="0">
  <frame src="top.jsp" scrolling="no" noresize="noresize" />
  <frameset cols="180,*" frameborder="yes" border="1" bordercolor="green" noresize="noresize">
  	<frame src="toViewPrivis" scrolling="yes"  scrolling="auto"/>
  	<frame src="tabpanel.jsp" name="mainFrame" id="main"/>
  </frameset>
  	<frame src="bottom.jsp" scrolling="no" noresize="noresize" />
</frameset>
</html>
