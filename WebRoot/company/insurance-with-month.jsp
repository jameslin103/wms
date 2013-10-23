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
								<a href="viewInsuranceWithMonth">增减员与参保明细</a>
							</li>
							<li>
								<a href="viewBalanceDetail">资金往来</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li class="right">
							
								<form action="" class="select-for-year" method="post">
									 日期:<input id="d11" name="year" onclick="WdatePicker()"  class="Wdate" style="width: 110px;height: 25px;" />
								</form>
							</li>
							<li>
								<a href="increaseEmployeesUI">批量增员</a>（与续保）
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								<a href="insuranceReductionUI">批量减员</a>
							</li>
						</ul>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th align="center">
										月份
									</th>
									<th align="center">
										详细
									</th>
									<th align="center">
										状态
									</th>
									<th align="center">
										补充说明
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td align="center">
										一月
									</td>
									<td>
										<a href="insuranceWithEmployeeList?month=8">新增3人，续保4人，减员2人</a>
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
								<tr>
									<td>
										三月
									</td>
									<td>
										<a href="insuranceWithEmployeeList?month=8">新增3人，续保4人，减员2人</a>
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
										四月
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
								<tr>
									<td>
										五月
									</td>
									<td>
										<a href="insuranceWithEmployeeList?month=8">新增3人，续保4人，减员2人</a>
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
										六月
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
								<tr>
									<td>
										七月
									</td>
									<td>
										<a href="insuranceWithEmployeeList?month=8">新增3人，续保4人，减员2人</a>
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
										八月
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
								<tr>
									<td>
										九月
									</td>
									<td>
										<a href="insuranceWithEmployeeList?month=8">新增3人，续保4人，减员2人</a>
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
										十月
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
								<tr>
									<td>
										十一月
									</td>
									<td>
										<a href="insuranceWithEmployeeList?month=8">新增3人，续保4人，减员2人</a>
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
										十二月
									</td>
									<td>
										新增0人
									</td>
									<td>
										未完成
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
