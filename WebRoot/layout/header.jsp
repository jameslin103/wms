<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="/help/public_css_js.jsp" %>
 <%@ taglib uri="/struts-tags" prefix="s" %>
<div id="header">
			<ul class="user normal clearfix">
				<li><a href="account/password.jsp"><s:property value="%{#session.user.username}"/></a></li>
				<li><s:a href="loginOut">退出</s:a></li>
			</ul>
			<div class="navbar">
			  <div class="navbar-inner">
			    <div class="container">
			      <a class="brand" href="#">富民</a>
						<ul class="nav">
						  <li class="dropdown">
						    <a href="" class="dropdown-toggle" data-toggle="dropdown">
						      企业
						      <b class="caret"></b>
						    </a>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
								  <li><a tabindex="-1" href="company/list.jsp">我的企业</a></li>
								  <li><a tabindex="-1" href="#">所有企业</a></li>
								</ul>
						  </li>
						  <li class="dropdown">
						    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
						      管理
						      <b class="caret"></b>
						    </a>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
								  <li><a tabindex="-1" href="admin/company-list.jsp">企业相关</a></li>
                	<li><a tabindex="-1" href="../admin/tax.jsp">计税规则</a></li>
									<li><a tabindex="-1" href="admin/authorization.jsp">权限分配</a></li>
								</ul>
						  </li>
		 					<li><a href="task/salary.jsp">任务</a></li>
		 					<li><a href="help/index.jsp">帮助</a></li>
						</ul>
			    </div>
			    <div id="sub-header" class="clearfix">
			<div class="date">
				2013年7月23日
			</div>
		</div>
			  </div>
			</div>

  	</div>
		