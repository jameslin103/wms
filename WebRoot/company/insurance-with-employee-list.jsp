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
						</ul>

						<ul class="normal action-container clearfix">
							<li class="right">
								<s:a href="downloadInsuranceWithEmployeeList" cssClass="btn btn-primary" >下载社医保办理与减员表</s:a>
							</li>
							<li>
								2013年1月
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								查看：
							</li>
							<li>
								<a href="newStaffEmployees?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>">新增</a>，
							</li>
							<li>
								<a href="renewalEmployees?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>">续保</a>，
							</li>
							<li>
								<a href="#">减员</a>
							</li>
						</ul>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th rowspan="2">
										序
									</th>
									<th rowspan="2">
										参保人
									</th>
									<th rowspan="2">
										性别
									</th>
									<th rowspan="2">
										身份证
									</th>
									<th rowspan="2">
										联系电话
									</th>
									<th rowspan="2">
										籍贯
									</th>
									<th rowspan="2">
										户口
									</th>
									<th rowspan="2">
										婚姻
									</th>
									<th rowspan="2">
										文化程度
									</th>
									<th rowspan="2">
										合同期限
									</th>
									<th rowspan="2">
										照片
									</th>
									<th colspan="3" style="text-align: center;">
										参保
									</th>
									<th rowspan="2">
										状态
									</th>
									<th rowspan="2">
										操作
									</th>
								</tr>
								<tr>
									<th>
										性质
									</th>
									<th>
										类型
									</th>
									<th>
										费用
										<br>
										（元）
									</th>
								</tr>
							</thead>
							
							<tbody>
							<s:iterator value="%{#request.employees}" id="emp">
								<tr>
									<td>
										<s:property value="%{#emp.employeesId}"/>
									</td>
									<td>
										<s:property value="%{#emp.employeesName}"/>
									</td>
									<td>
										<s:property value="%{#emp.employeesSex}"/>
									</td>
									<td>
										<s:property value="%{#emp.phone}"/>
									</td>
									<td>
										<s:property value="%{#emp.cardNumber}"/>
									</td>
									<td>
										<s:property value="%{#emp.homeAddress}"/>
									</td>
									<td>
										<s:if test="%{#emp.householdRegister==0}">
												<span>非农</span>
										</s:if>
										<s:elseif test="%{#emp.householdRegister==1}">
												<span>农村</span>
										</s:elseif>
										</td>
										<td>
											<s:if test="%{#emp.maritalStatus==0}">
												<span>已婚</span>
											</s:if>
											<s:else>
												<span>未婚</span>
											</s:else>
										</td>
									<td>
										<s:property value="%{#emp.levelEducation}"/>
									</td>
									<td>
										<s:date name="%{#emp.startContractDeadline}" format="yyyy.MM.dd"/>
										——
										<s:date name="%{#emp.endContractDeadline}" format="yyyy.MM.dd"/>
									</td>
									<td>
										<s:if test="%{#emp.photo}==0">
											没有
										</s:if>
										<s:elseif test="%{#emp.photo}==1">
											有
										</s:elseif>
									</td>
									<td>
										<s:property value="%{#emp.ginsengProtectNature}"/>
									</td>
									<td>
										<ol>
											<li>
												<s:property value="%{#emp.sociaSecurity}"/>
											</li>
											<li>
												<s:property value="%{#emp.healthCare}"/>
											</li>
											<li>
												<s:property value="%{#emp.accumulationFund}"/>
											</li>
											<li>
												<s:property value="%{#emp.seriousDisease}"/>
											</li>
										</ol>
									</td>
									<td>
										560
									</td>
									<td>
										执行中
									</td>
									<td>
										<a href="deleteEmployees?enterpriseEmployees.employeesId=<s:property value="%{#emp.employeesId}"/>">删除</a>
									</td>
								</tr>
								</s:iterator>
							</tbody>
							
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
					增减员与参保
				</h3>
			</div>

			<div class="modal-body">
				<div class="row-fluid">
					<form action="" method="post">

						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<input type="radio" name="" value="1" checked="checked">
							执行中，
							<input type="radio" name="" value="0">
							已完成
						</div>

						<div class="input-container">
							<label>
								补充说明
							</label>
							<input type="text" name="">
						</div>

						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<button type="button" class="btn btn-primary">
								提交
							</button>
						</div>
					</form>
				</div>
			</div>

			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>

		<div id="info-for-check1"
			class="modal hide fade modal-of-info-for-check" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					参保人员信息
				</h3>
			</div>

			<div class="modal-body">

			</div>

			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>
	</body>

</html>
