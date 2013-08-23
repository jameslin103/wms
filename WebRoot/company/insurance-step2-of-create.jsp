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
							<li>
								<a href="index.jsp">综合</a>
							</li>
							<li>
								<a href="employee-list.jsp">员工档案</a>
							</li>
							<li>
								<a href="salary-with-month.jsp">工资预算表</a>
							</li>
							<li class="active">
								<a href="insurance-with-month.jsp">增减员与参保明细</a>
							</li>
						</ul>

						<div class="span4">
							<h3>
								批量增员（与续保）
							</h3>
							<div class="row-fluid">

								<div class="alert">
									<p>
										增员3人，续保4人
									</p>
									<p>
										<a href="insurance-step1-of-create.jsp">信息错误，重新导入数据！</a>
									</p>
								</div>
								<hr>

								<div>
									<p>
										<a href="insurance-with-month.jsp" class="btn btn-primary">信息正确，确认！</a>
									</p>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="footer"></div>

		</div>
	</body>

</html>