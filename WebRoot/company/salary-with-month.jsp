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
							<li class="active">
								<a href="viewSalaryBudgetTable">工资预算表</a>
							</li>
							<li>
								<a href="viewInsuranceWithMonth">增减员与参保明细</a>
							</li>
							<li>
								<a href="viewWageBudgetSummary?enterpriseId=<s:property value='%{#request.session.enterprise.enterpriseId}'/>">资金往来</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li class="right">
								<form action="viewSalaryBudgetTable" class="select-for-year" method="post" id="salary">
									<!--<input id="d11" name="salaryDate" onclick="WdatePicker()" class="Wdate" style="width: 110px;height: 25px;" />
										-->
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
											<s:iterator value="#request.map">
												<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
													&budgetId=<s:property value="value.budgetId"/>">
													<s:if test="key.substring(5,7)==#month">
														<li>
															<s:if test="value.name.length()>15">
																<s:property value="value.name.substring(0,15)+'...'"/>
															</s:if>
															<s:else>
																<s:property value="value.name"/>
															</s:else>
															<s:date name="value.salaryDate" format="MM月"/>工资
														</li>
													</s:if>
												</a>
											</s:iterator>
										</ol>
										
									</td>
								
									<td>
										<ol>
											
											<s:iterator value="#request.map">
												<s:if test="key.substring(5,7)==#month">
												<li>
													<s:if test="value.name.length()>15">
														<s:property value="value.name.substring(0,15)+'...'"/>
													</s:if>
													<s:else>
														<s:property value="value.name"/>
													</s:else>
													<s:date name="value.salaryDate" format="MM月"/>工资
													<span class="blue">（<s:property value="value.note"/>）</span>
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