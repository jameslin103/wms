<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>富民人力银行派遣系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
