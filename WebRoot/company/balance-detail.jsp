<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@ include file="/help/public_css_js.jsp"%>
	</head>
	<body>

		<div id="container">
			<div id="header">
				<jsp:include page="../layout/header.jsp" />
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
							<li>
								<a href="company/insurance-with-month.jsp">增减员与参保明细</a>
							</li>
							<li class="active">
								<a href="viewBalanceDetail">资金往来</a>
							</li>
						</ul>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th rowspan="2">
										<s:property value=""/>
									</th>
									<th rowspan="2">
										月份
									</th>
									<th rowspan="2">
										期初余额（元）
									</th>
									<th colspan="4" class="center">
										应收款项（元）
									</th>
									<th rowspan="2">
										实收款项（元）
									</th>
									<th rowspan="2">
										期末余额（元）
									</th>
									<th colspan="3" class="center">
										余额分配（元）
									</th>
									<th rowspan="2">
										操作
									</th>
								</tr>
								<tr>
									<th>
										开票总额
									</th>
									<th>
										工资总额
									</th>
									<th>
										服务费总额
									</th>
									<th>
										五险一金
									</th>
									<th>
										工资
									</th>
									<th>
										服务费
									</th>
									<th>
										五险一金
									</th>
								</tr>
							</thead>
							<s:iterator value="%{#request.balanceDetails}" id="balanceDetail">
							<tbody>
								<tr>
									<td rowspan="2">
										<s:property value="%{#balanceDetail.detailId}"/>
									</td>
									<td>
										<s:date name="%{#balanceDetail.yearMonth}" format="yyyy年MM月dd日"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.balance}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.ballotsToal}"/>
									</td>
									<td></td>
									<td></td>
									<td></td>
									<td>
										<s:property value="%{#balanceDetail.receivedFunds}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.endingBalance}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.wages}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.serviceWith}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.fiveFund}"/>
									</td>
									<td rowspan="2">
										<a href="#info-for-check" data-toggle="modal">填写</a>
									</td>
								</tr>
								<tr>
									<td colspan="11" class="align-right">
										备注:（<s:property value="%{#balanceDetail.note}"/>）
									</td>
								</tr>
							</tbody>
							</s:iterator>
						</table>

						<div class="pagination">
							<ul>
								<li>
									<a href="#">&laquo;</a>
								</li>
								<li>
									<a href="#">1</a>
								</li>
								<li>
									<a href="#">2</a>
								</li>
								<li>
									<a href="#">3</a>
								</li>
								<li>
									<a href="#">4</a>
								</li>
								<li>
									<a href="#">5</a>
								</li>
								<li>
									<a href="#">6</a>
								</li>
								<li>
									<a href="#">7</a>
								</li>
								<li>
									<a href="#">8</a>
								</li>
								<li>
									<a href="#">&raquo;</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div id="footer"></div>
		</div>

		<div id="info-for-check" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					实收款项与余额分配
				</h3>
			</div>

			<div class="modal-body">
				<div class="row-fluid">
					<s:form action="addBalanceDetail" method="post">
					<s:hidden value="%{#balanceDetail.detailId}" name="balanceDetail.detailId"></s:hidden>
						<div class="input-container">
							<label>
								实收款项
							</label>
							<s:textfield name="balanceDetail.receivedFunds"  value="%{#balanceDetail.receivedFunds}"/>
						</div>
						<hr>
						<h3>
							余额分配
						</h3>
						<div class="input-container">
							<label>
								工资
							</label>
							<s:textfield name="balanceDetail.wages" value="%{#balanceDetail.wages}"/>
						</div>
						<div class="input-container">
							<label>
								服务费
							</label>
							<s:textfield name="balanceDetail.serviceWith" value="%{#balanceDetail.serviceWith}"/>
						</div>
						<div class="input-container">
							<label>
								五险一金
							</label>
							<s:textfield name="balanceDetail.fiveFund" value="%{#balanceDetail.fiveFund}"/>
						</div>
						<hr>
						<div class="input-container">
							<label>
								备注
							</label>
							<s:textfield name="balanceDetail.note" value="%{#balanceDetail.note}"/>
						</div>
						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<s:submit value="提交" cssClass="btn btn-primary" />
						</div>
					</s:form>
				</div>
			</div>

			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>
	</body>

</html>
