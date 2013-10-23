<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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
				<jsp:include page="../layout/header.jsp" />
			</div>

			<div id="main">
				<div class="row-fluid">
					<div id="center-pane">
						<ul class="nav nav-tabs">
							<li class="active">
								<a href="employee-list.html">密码修改</a>
							</li>
						</ul>

						<div class="row-fluid">
							<div class="span3">
								<form action="updateBuyerPassword" method="post">
									<s:hidden value='%{#session.user.username}'
										name="buyer.username" id="username" />
									<s:hidden value="%{#session.user.password}" id="password"
										name="buyer.password" />
									<s:password cssClass="input-block-level" placeholder="旧密码"
										id="psd" name="oldPassword" />
									<s:label id="oldPassword_labe" />
									<s:password cssClass="input-block-level" placeholder="新密码"
										id="new_psd" name="newPassword" />
									<s:password cssClass="input-block-level" placeholder="新密码（确认）"
										id="rple_psd" />
									<button class="btn btn-primary" type="submit">
										提交
									</button>
								</form>
							</div>
						</div>

					</div>
				</div>
			</div>

			<div id="footer"></div>

		</div>


	</body>

</html>
