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
		<!--<script type="text/javascript" src="http://keleyi.com/keleyi/pmedia/jquery/jquery-1.10.2.min.js"></script>
			<style>
			/* 柯乐义 
			* http://keleyi.com
			*/
			*
			{
			margin:0;
			padding:0;
			}
			body
			{
			background:#f9f9f9;
			color:#000;
			font:15px Calibri, Arial, sans-serif;
			text-shadow:1px 2px 1px #FFFFFF;
			}
			a,
			a:visited
			{
			text-decoration:none;
			outline:none;
			color:#0000ff;
			}
			a:hover
			{
			text-decoration:underline;
			color:#009900;
			}
			/*the footer*/
			footer
			{
			background:#444 url("http://keleyi.com/image/a/im66vsri.png") repeat;
			position:fixed;
			width:100%;
			height:70px;
			bottom:0;
			left:0;
			color:#fff;
			text-shadow:2px 2px #000;
			-moz-box-shadow:5px 1px 10px #000;
			-webkit-box-shadow:5px 1px 10px #000;
			box-shadow:5px 1px 10px #000;
			}
			footer h1
			{
			font:25px/26px Acens;
			font-weight:normal;
			left:50%;
			margin:0px 0 0 150px;
			padding:25px 0;
			position:relative;
			width:400px;
			}
			footer a.orig,
			a.orig:visited
			{
			background:url("http://keleyi.com/image/a/xihqij1u.png") no-repeat right top;
			border:none;
			text-decoration:none;
			color:#FCFCFC;
			font-size:14px;
			height:70px;
			left:50%;
			line-height:50px;
			margin:12px 0 0 -400px;
			position:absolute;
			top:0;
			width:250px;
			}
			/*styling for the clock*/
			#clock-keleyi-com
			{
			position: relative;
			width: 600px;
			height: 600px;
			list-style: none;
			margin: 20px auto;
			background: url('http://keleyi.com/image/a/nejs3bnm.png') no-repeat center;
			
			}
			#seconds,
			#minutes,
			#hours
			{
			position: absolute;
			width: 30px;
			height: 580px;
			left: 270px;
			}
			#date
			{
			text-shadow:1px 2px 1px #000;
			position: absolute;
			top: 365px;
			color:#fff;
			right: 140px;
			font:30px/36px Acens;
			font-weight:bold;
			letter-spacing:3px;
			}
			#hours
			{
			background: url('http://keleyi.com/image/a/exs0erm9.png') no-repeat left;
			z-index: 1000;
			}
			#minutes
			{
			background: url('http://keleyi.com/image/a/exs0erm9.png') no-repeat center;
			width:25px;
			z-index: 2000;
			}
			
			#seconds
			{
			background: url('http://keleyi.com/image/a/exs0erm9.png') no-repeat right;
			z-index: 3000;
			}
			</style>
			<script>
			$(document).ready(function () {
			
			//markup for the clock	
			var clock = [
			'<ul id="clock-ke'+'leyi-com">',
			'<li id="date"></li>',
			'<li id="seconds"></li>',
			'<li id="hours"></li>',
			'<li id="minutes"></li>',
			'</ul>'].join('');
			
			//fadein the clock and append it to the body keleyi.com	
			$(clock).fadeIn().appendTo('body');
			
			//an autoexecuting function that updates the clovk view every second
			//you can also use setInterval (function Clock (){})();
			(function Clock() {
			
			//get the date and time
			var date = new Date().getDate(), //get the current date
			hours = new Date().getHours(), //get the current hour
			minutes = new Date().getMinutes(); //get the current minute
			seconds = new Date().getSeconds(), //get the current second
			
			$("#date").html(date); //show the current date on the clock
			
			var hrotate = hours * 30 + (minutes / 2);
			//i.e 12 hours * 30 = 360 degrees
			
			$("#hours").css({
			'transform': 'rotate(' + hrotate + 'deg)',
			'-moz-transform': 'rotate(' + hrotate + 'deg)',
			'-webkit-transform': 'rotate(' + hrotate + 'deg)'
			});
			
			var mrotate = minutes * 6; //degrees to rotate the minute hand
			
			$("#minutes").css({
			'transform': 'rotate(' + mrotate + 'deg)',
			'-moz-transform': 'rotate(' + mrotate + 'deg)',
			'-webkit-transform': 'rotate(' + mrotate + 'deg)'
			});
			
			var srotate = seconds * 6; //for the rotation to reach 360 degrees
			//i.e 60 seconds * 6 = 360 degrees.
			
			$("#seconds").css({
			'transform': 'rotate(' + srotate + 'deg)',
			'-moz-transform': 'rotate(' + srotate + 'deg)',
			'-webkit-transform': 'rotate(' + srotate + 'deg)'
			});
			
			//a call to the clock function after every 1000 miliseconds
			setTimeout(Clock, 1000);
			})();
			}); </script>
	--></head>
<body>
<div id="container">
	<div id="header">
		<ul class="user normal clearfix">
			<li>
				<a href="toPassword">欢迎用户:&nbsp;&nbsp;<s:property value="%{#session.user.username}" />
				</a>
			</li>
			<li>
				<s:a href="loginOut">退出系统</s:a>
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
	<div id="main"><!--
					欢迎---<s:property value="#session.user.role.name"/>--<s:property value="#session.user.username"/>--进入系统
			
			--><div class="row-fluid">
				
				<div id="center-pane">
				</div>
		</div>
	</div>
</div>
</body>
</html>
