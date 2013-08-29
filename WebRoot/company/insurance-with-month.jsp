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
				<jsp:include page="../layout/header.jsp"></jsp:include>

				<div id="sub-header" class="clearfix">
					<h2>
						<s:property value="%{#session.enterprise.fullName}" />
					</h2>
				</div>
			</div>
			<div id="main">
				<div class="row-fluid">
					<div id="center-pane">
						<ul class="nav nav-tabs">
							<li>
								<a href="company/index.jsp">综合</a>
							</li>
							<li>
								<a href="viewEnterpriseEmployees">员工档案</a>
							</li>
							<li>
								<a href="viewSalaryBudgetTable">工资预算表</a>
							</li>
							<li class="active">
								<a href="company/insurance-with-month.jsp">增减员与参保明细</a>
							</li>
							<li>
								<a href="viewBalanceDetail">资金往来</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li class="right">
								<form action="" class="select-for-year" method="post">
									<select>
										<option value="">
											2014年
										</option>
										<option value="" selected>
											2013年
										</option>
										<option value="">
											2012年
										</option>
									</select>
								</form>
							</li>
							<li>
								<a href="company/insurance-step1-of-create.jsp">批量增员</a>（与续保）
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								<a href="company/insurance-step1-of-reduction.jsp">批量减员</a>
							</li>
						</ul>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>
										月份
									</th>
									<th>
										详细
									</th>
									<th>
										状态
									</th>
									<th>
										补充说明
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										一月
									</td>
									<td>
										<a href="company/insurance-with-employee-list.jsp">新增3人，续保4人，减员2人</a>
									</td>
									<td>
										已完成
									</td>
									<td>
										---
									</td>
								</tr>
								<tr>
									<td>
										二月
									</td>
									<td>
										新增3人
									</td>
									<td>
										已完成
									</td>
									<td>
										-----
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
