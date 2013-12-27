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
		  
		<script>
			$(function (){
				$("input[name='year']").blur(function (){
					var year=$("input[name='year']").val();
					if(year!=""){
						$("#salary").submit();
					}
				});
			});
		</script>
	</head>
	<body>

		<div id="container">
			<div id="header">
				

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
									<li >
										<a href="viewEnterpriseEmployees"><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
								<s:if test="#menu.url=='viewSalaryBudgetTable'">
									<li class="active">
										<a href="viewSalaryBudgetTable" >
											<s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
								<s:if test="#menu.url=='viewInsuranceWithMonth'">
									<li>
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
									<li >
										<a href="viewEnterpriseDetailed" ><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
							</s:iterator>
						</ul>
						<ul class="normal action-container clearfix">
							<li class="right">
								<form action="viewSalaryBudgetTable" class="select-for-year" method="post" id="salary">
										按年份查询:<span style="color: red;">(如:2013)</span>
										<input type="text" name="year" value="${year}" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')"/>
								</form>
							</li>
							<li>
								<a href="newSalaryBudgetTable">新建工资预算表</a>
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								<a href="viewSalaryTemplate">工资模板</a>
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								<a href="viewCustomBonus">定制奖金与各种补贴</a>
							</li>
						</ul>	
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th style="font-weight:bold; text-align: center;">
										月份
									</th>
									<th style="font-weight:bold; text-align: center;">
										工资制作
									</th>
									<th style="font-weight:bold; text-align: center;">
										工资发放
									</th>
								</tr>
							</thead>  
							<tbody>
								<s:iterator begin="01" end="12" id="month">
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;"><s:property value="#month"/>月</div>
									</td>
									<td>
										<ol>
											<s:iterator value="#request.map" id="value">
												<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
													&budgetId=<s:property value="#value.createSalaryBudgetTable.budgetId"/>">
													<s:if test="#value.date.substring(5,7)==#month">
														<li>
															<s:if test="#value.createSalaryBudgetTable.name.length()>15">
																<s:property value="#value.createSalaryBudgetTable.name.substring(0,15)+'...'"/>
															</s:if>
															<s:else>
																<s:property value="#value.createSalaryBudgetTable.name"/>
															</s:else>
															<s:date name="#value.createSalaryBudgetTable.salaryDate" format="MM月"/>工资
														</li>
													</s:if>
												</a>
											</s:iterator>
										</ol>
										
									</td>
								
									<td>
										<ol>
											
											<s:iterator value="#request.map" id="value">
												<s:if test="#value.date.substring(5,7)==#month">
												<li>
													<s:if test="#value.createSalaryBudgetTable.name.length()>15">
														<s:property value="#value.createSalaryBudgetTable.name.substring(0,15)+'...'"/>
													</s:if>
													<s:else>
														<s:property value="#value.createSalaryBudgetTable.name"/>
													</s:else>
													<s:date name="#value.createSalaryBudgetTable.salaryDate" format="MM月"/>工资
													<span class="blue">
														<s:if test="#value.createSalaryBudgetTable.note!=null && #!value.createSalaryBudgetTable.note.empty()">
															（<s:property value="#value.createSalaryBudgetTable.note"/>）
														</s:if>
													</span>
													<a href="returnToModifyBudgetTable?budgetId=<s:property value="#value.createSalaryBudgetTable.budgetId"/>" style="color: red;">修改</a>
												</li>
												</s:if>
												
											</s:iterator>
											
										</ol>
									</td>
								</tr>
								</s:iterator>
							</tbody>
						</table>

					</div>
				</div>
			</div>

			<div id="footer"></div>
		</div>
	</body>

</html>