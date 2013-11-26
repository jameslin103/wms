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
							<s:iterator value="#session.menuList" id="menu">
								<s:if test="#menu.url=='viewEnterpriseEmployees'">
									<li class="active">
										<a href="viewEnterpriseEmployees"  ><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
								<s:if test="#menu.url=='viewSalaryBudgetTable'">
									<li >
										<a href="viewSalaryBudgetTable" >
											<s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
								<s:if test="#menu.url=='viewInsuranceWithMonth'">
									<li >
										<a href="viewInsuranceWithMonth" ><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
								<s:if test="#menu.url=='viewBalanceDetail'">
									<li >
										<a href="viewBalanceDetail" ><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
								<s:if test="#menu.url=='viewEnterpriseDetailed'">
									<li>
										<a href="viewEnterpriseDetailed" ><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
							</s:iterator>
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
							<s:iterator value="#request.enterpriseEmployeesList" id="enterpriseEmployees" status="list">
							<tbody>
								<tr>
									<td>
										<s:property value="%{#list.index+1}"/>
									</td>
									<td>
										<a href="selectEnterpriseEmployeesWage?employeesId=<s:property value='%{#enterpriseEmployees.employeesId}'/>">
											<s:property value="%{#enterpriseEmployees.employeesName}"/>
										</a>
									</td>
									<td>
										<s:property value="%{#enterpriseEmployees.enterprise.fullName}"/>
									</td>
									<td>
										<s:property value="%{#enterpriseEmployees.employeesSex}"/>
									</td>
									<td>
										
										<s:if test="#enterpriseEmployees.householdRegister==1 ">
											非农
										</s:if>
										<s:elseif test="#enterpriseEmployees.householdRegister==02 ">
											农村
										</s:elseif>
										<s:else>
											
										</s:else>
									</td>
									<td>
										
										<s:if test="#enterpriseEmployees.maritalStatus==1">
											已婚
										</s:if>
										<s:elseif test="#enterpriseEmployees.maritalStatus==0">
											未婚
										</s:elseif>
										<s:else>
											
										</s:else>
									</td>
									<td>
										<s:if test="#enterpriseEmployees.photo==1">
											有
										</s:if>
										<s:elseif test="#enterpriseEmployees.photo==0">
											无
										</s:elseif>
										<s:else>
											
										</s:else>
									</td>
									<td>
										<s:property value="%{#enterpriseEmployees.cardNumber}"/>
									</td>
									<td>
										<s:property value="%{#enterpriseEmployees.phone}"/>
									</td>
								</tr>
							</tbody>
							</s:iterator>
						</table>

					</div>

				</div>
			</div>
			<div id="footer"></div>
		</div>
	</body>

</html>
