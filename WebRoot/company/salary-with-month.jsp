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
								<form action="" class="select-for-year" method="post">
									 日期:<input id="d11" name="salaryDate" onclick="WdatePicker()" class="Wdate" style="width: 110px;height: 25px;" />
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
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">一月</div>
									</td>
									<td>
										<ol>
										<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
										<s:property value="%{#sal.salaryDate}"/>
											<li>
												<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
													&budgetId=<s:property value="%{#sal.budgetId}"/>">
													<s:if test="#sal.enterprise.enterpriseId">
													
														<s:property value="%{#sal.name}"/>	
														
													</s:if>		
												</a>
											</li>
											</s:iterator>
										</ol>
									</td>
								
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								
								</tr>
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">二月</div>
									</td>
									<td>
									
										<ol>
										<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
											<li>
												<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
													&budgetId=<s:property value="%{#sal.budgetId}"/>">
														<s:property value="%{#sal.name}"/>			
												</a>
											</li>
										</s:iterator>
										</ol>
										
									</td>
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								</tr>
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">三月</div>
									</td>
									<td>
									
										<ol>
											<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
												<li>
													<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
														&budgetId=<s:property value="%{#sal.budgetId}"/>">
															<s:property value="%{#sal.name}"/>			
													</a>
												</li>
											</s:iterator>
										</ol>
										
									</td>
								
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								
								</tr>
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">四月</div>
									</td>
									<td>
									
										<ol>
											<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
												<li>
													<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
														&budgetId=<s:property value="%{#sal.budgetId}"/>">
															<s:property value="%{#sal.name}"/>			
													</a>
												</li>
											</s:iterator>
										</ol>
									</td>
								
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								
								</tr>
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">五月</div>
									</td>
									<td>
										<ol>
											<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
												<li>
													<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
														&budgetId=<s:property value="%{#sal.budgetId}"/>">
															<s:property value="%{#sal.name}"/>			
													</a>
												</li>
											</s:iterator>
										</ol>
									</td>
								
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								
								</tr>
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">六月</div>
									</td>
									<td>
										<ol>
											<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
												<li>
													<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
														&budgetId=<s:property value="%{#sal.budgetId}"/>">
															<s:property value="%{#sal.name}"/>			
													</a>
												</li>
											</s:iterator>
										</ol>
									</td>
								
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								
								</tr>
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">七月</div>
									</td>
									<td>
										<ol>
											<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
												<li>
													<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
														&budgetId=<s:property value="%{#sal.budgetId}"/>">
															<s:property value="%{#sal.name}"/>			
													</a>
												</li>
											</s:iterator>
										</ol>
									</td>
								
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								
								</tr>
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">八月</div>
									</td>
									<td>
										<ol>
											<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
												<li>
													<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
														&budgetId=<s:property value="%{#sal.budgetId}"/>">
															<s:property value="%{#sal.name}"/>			
													</a>
												</li>
											</s:iterator>
										</ol>
									</td>
								
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								
								</tr>
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">九月</div>
									</td>
									<td>
										<ol>
											<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
												<li>
													<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
														&budgetId=<s:property value="%{#sal.budgetId}"/>">
															<s:property value="%{#sal.name}"/>			
													</a>
												</li>
											</s:iterator>
										</ol>
									</td>
								
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								
								</tr>
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">十月</div>
									</td>
									<td>
										<ol>
											<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
												<li>
													<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
														&budgetId=<s:property value="%{#sal.budgetId}"/>">
															<s:property value="%{#sal.name}"/>			
													</a>
												</li>
											</s:iterator>
										</ol>
									</td>
								
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								
								</tr>
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">十一月</div>
									</td>
									<td>
										<ol>
											<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
												<li>
													<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
														&budgetId=<s:property value="%{#sal.budgetId}"/>">
															<s:property value="%{#sal.name}"/>			
													</a>
												</li>
											</s:iterator>
										</ol>
									</td>
								
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								
								</tr>
								<tr>
									<td>
										<div style="font-weight:bold; text-align: center;">十二月</div>
									</td>
									<td>
										<ol>
											<s:iterator value="#request.createSalaryBudgetTable" id="sal" >
												<li>
													<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#request.session.enterprise.enterpriseId}"/>
														&budgetId=<s:property value="%{#sal.budgetId}"/>">
															<s:property value="%{#sal.name}"/>			
													</a>
												</li>
											</s:iterator>
										</ol>
									</td>
								
									<td>
										<ol>
											<li>
												xxx项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												judd项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
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