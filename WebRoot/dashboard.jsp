<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="/help/public_css_js.jsp"%>
		 
	</head>
<body>
<div id="container">
	<div id="header">
		<ul class="user normal clearfix">
			<li>
				<a href="toPassword">用户:&nbsp;&nbsp;<s:property value="%{#session.user.username}" />
				</a>
			</li>
			<li>
				<s:a href="loginOut">退出</s:a>
			</li>
		</ul>
	<!-- begin -->
		<div class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<a class="brand">富民派遣系统</a>
					<ul class="nav">
					<s:iterator value="#session.menuList" var="menu">
						<s:if test="parentMenu.menuId==1">
							<s:if test="type==0">
								<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><s:property value="name"/><b class="caret"></b></a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
										<s:iterator value="#session.menuList" var="submenu">
											<s:if test="#menu.menuId==#submenu.parentMenu.menuId">
												<li><a tabindex="-1" href="<s:property value="#submenu.url"/>"><s:property value="#submenu.name"/></a></li>
											</s:if>
										</s:iterator>
									</ul>
								</li>
							</s:if>
							<s:if test="type==1">
								<li><a href="<s:property value="url"/>"><s:property value="name"/></a></li>
							</s:if>
						</s:if>
					</s:iterator>
					</ul>
				</div>
			</div>
		</div>
	</div>
   <!-- end -->
	<div id="sub-header" class="clearfix">
		<div class="date">
			<%
				java.util.Date now = new java.util.Date();
				Date currentTime = new Date();
				java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
						"yyyy年MM月dd日 ");
				String dateString = formatter.format(currentTime);
				out.println(dateString);
			%>
		</div>
	</div>

	<div id="main">
		<div class="row-fluid">
			<div id="center-pane"></div>
		</div>
	</div>

	<div id="footer">
	</div>
</div>
</body>
</html>
