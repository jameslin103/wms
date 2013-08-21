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
							<li class="active">
								<a href="viewEnterpriseEmployees">员工档案</a>
							</li>
							<li>
								<a href="company/salary-with-month.jsp">工资预算表</a>
							</li>
							<li>
								<a href="company/insurance-with-month.jsp">增减员与参保明细</a>
							</li>
						</ul>


						<h3>
							搜索结果
						</h3>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>
										序
									</th>
									<th>
										姓名
									</th>
									<th>
										企业
									</th>
									<th>
										性别
									</th>
									<th>
										户口
									</th>
									<th>
										婚姻
									</th>
									<th>
										照片
									</th>
									<th>
										身份证
									</th>
									<th>
										电话
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										1
									</td>
									<td>
										<a href="company/employee-personal-info.jsp">某某人</a>
									</td>
									<td>
										福建电信
									</td>
									<td>
										男
									</td>
									<td>
										非农
									</td>
									<td>
										已婚
									</td>
									<td>
										有
									</td>
									<td>
										132562345698756231
									</td>
									<td>
										13356899685
									</td>
								</tr>
							</tbody>
						</table>

					</div>

				</div>
			</div>
			<div id="footer"></div>
		</div>
	</body>

</html>
