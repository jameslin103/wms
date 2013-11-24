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
						<ul class="normal action-container clearfix">
							<li>
								员工：
								<s:property value="%{#request.employees.employeesName}" />
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								查看各类明细：
							</li>
							<li>
								<a href="viewEmployeePersonalSalary?employeesId=<s:property value="%{#request.employees.employeesId}"/>">工资</a>，
							</li>
							<li>
								<a
									href="viewEmployeeContract?employeesId=<s:property value="%{#request.employees.employeesId}"/>">合同</a>，
							</li>
							<li>
								<a
									href="selectEnterpriseEmployeesWage?employeesId=<s:property value="%{#request.employees.employeesId}"/>">基本信息</a>
							</li>
						</ul>

						<h3>
							基本信息
						</h3>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>
										合同编号
									</th>
									<th>
										姓名
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
									<th>
										行业
									</th>
									<th>
										岗位
									</th>
									<th>
										服务费
										<br/>
										（元）
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<s:property value="#request.employees.contractNo" />
									</td>
									<td>
										<s:property value="#request.employees.employeesName" />
									</td>
									<td>
										<s:property value="#request.employees.employeesSex" />
									</td>

									<td>
										<s:if test="#request.employees.householdRegister==2">
											<span>农村</span>
										</s:if>
										<s:elseif test="#request.employees.householdRegister==1">
											<span>非农</span>
										</s:elseif>
										<s:else>
											
										</s:else>
									</td>
									<td>
										<s:if test="#request.employees.maritalStatus=='0'">
											<span>已婚</span>
										</s:if>
										<s:else>
											<span>未婚</span>
										</s:else>
									</td>
									<td>
										<s:if test="#request.employees.photo==1">
											<span>有</span>
										</s:if>
										<s:elseif test="#request.employees.photo==0">
											<span>无</span>
										</s:elseif>
										<s:else>
											
										</s:else>
									</td>
									<td>
										<s:property value="%{#request.employees.cardNumber}" />
									</td>
									<td>
										<s:property value="#request.employees.phone" />
									</td>
									<td>
										<s:property value="#request.employees.industry" />
									</td>
									<td>
										<s:property value="%{#request.employees.jobs}" />
									</td>
									<td>
										<s:property value="#request.employees.serviceCost" />
									</td>
								</tr>
							</tbody>
						</table>

						<h3>
							五险一金
						</h3>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th colspan="2" width="">
										参保
									</th>
									<th rowspan="2" width="">
										哪月起
										<br/>
										参保
									</th>
									<th rowspan="2" width="">
										基数设置
									</th>
									<th colspan="6" class="center">
										基数（元）（未填写表示没有参保）
									</th>
									<th rowspan="2" width="">
										个税缴纳
									</th>
								</tr>
								<tr>
									<th>
										是否
									</th>
									<th>
										性质
									</th>
									<th>
										社会
										<br/>
										保险
									</th>
									<th>
										生育
										<br/>
										保险
									</th>
									<th>
										工伤
									</th>
									<th>
										基本
										<br/>
										医疗保险
									</th>
									<th>
										住房
										<br/>
										公积金
									</th>
									<th>
										大病
										<br/>
										统筹
									</th>
								</tr>

							</thead>
							<tbody>
								<tr>
									<td>
										<s:if test="#request.employees.whetherGinseng==1">
											<span>是</span>
										</s:if>
										<s:elseif test="#request.employees.whetherGinseng==0">
											<span>否</span>
										</s:elseif>
										<s:else>
											
										</s:else>
									</td>
									<td>
										<s:if test="#request.employees.ginsengProtectNature==1">
											<span>增员</span>
										</s:if>
										<s:elseif test="#request.employees.ginsengProtectNature==2">
											<span>续保</span>
										</s:elseif>
										<s:elseif test="#request.employees.ginsengProtectNature==3">
											<span>减员</span>
										</s:elseif>
										<s:else>
											
										</s:else>
									</td>
									<td>
										<s:date name="%{#request.employees.cinsengDate}" format="yyyy年MM月dd"/>
									</td>
									<td>
										<s:if test="#request.employees.base==0">
											<span>默认</span>
										</s:if>
										<s:elseif test="#request.employees.base==1">
											<span>个性设置</span>
										</s:elseif>
										<s:else>
										
										</s:else>
									</td>
									<td>
										<s:property value="%{#request.employees.socialInsurance}" />
									</td>
									<td>
										<s:property value="%{#request.employees.fertilityInsurance}" />
									</td>
									<td>
										<s:property value="%{#request.employees.inductrialBase}" />
									</td>
									<td>
										<s:property value="%{#request.employees.basicMedical}" />
									</td>
									<td>
										<s:property value="%{#request.employees.housingFund}" />
									</td>
									<td>
										<s:property value="%{#request.employees.seriousDiseaseBase}" />
									</td>
									<td>
									<s:if test="#request.employees.paymentWay==0">
											<span>个人缴税</span>
										</s:if>
										<s:elseif test="#request.employees.paymentWay==1">
											<span>企业缴税</span>
										</s:elseif>
										<s:else>
										
										</s:else>
									</td>
								</tr>
							</tbody>
						</table>

						<h3>
							银行卡
						</h3>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>
										开户行
									</th>
									<th>
										卡号
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<s:property value="%{#request.employees.bank}" />
									</td>
									<td>
										<s:property value="%{#request.employees.bankCardNumber}" />
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
