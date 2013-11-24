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
		<meta http-equiv="pragma" content="no-cache"/> 
		<meta http-equiv="cache-control" content="no-cache"/> 
		<meta http-equiv="expires" content="0"/> 
		
		
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
								<s:property value="#request.employees.employeesName" />
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
								<a href="selectEnterpriseEmployeesWage?employeesId=<s:property value="%{#request.employees.employeesId}"/>">基本信息</a>
							</li>
							<li>/</li>
							
						</ul>

						<h3>
							合同记录
						</h3>
							
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th style="text-align: center;">
										序
									</th>
									<th style="text-align: center;">
										合同编号
									</th>
									<th style="text-align: center;">
										起
									</th>
									<th style="text-align: center;">
										止
									</th>
									<th style="text-align: center;">
										实际终止
									</th>
									<th style="text-align: center;">
										性质
									</th>
									<th style="text-align: center;">
										操作
									</th>
								</tr>
							</thead>
							<s:iterator value="#request.employees.employeesContract" id="employeesContract" status="list">
							
							<tbody>
								<tr>
									<td style="text-align: center;">
										<s:property value="#list.index+1"/>
									</td>
									<td style="text-align: center;">
										<s:property value="%{#employeesContract.contractNo}"/>
									</td>
									<td style="text-align: center;">
										<s:date name="%{#employeesContract.contractStatrDate}" format="yyyy年MM月dd日"/>
									</td>
									<td style="text-align: center;">
										<s:date name="%{#employeesContract.contractEndDate}" format="yyyy年MM月dd日"/>
									</td>
									<td style="text-align: center;">
										<s:date name="%{#employeesContract.actualTerminationDate}" format="yyyy年MM月dd日"/>
									</td>
									<td style="text-align: center;"><s:property value="%{#employeesContract.status}"/></td>
									<td style="text-align: center;">
									  <s:if test="#employeesContract.actualTerminationDate==null">
									  		<s:set value="%{#employeesContract.contractid}" var="contractid"></s:set>
									  		<input type="hidden" value="${employeesId}" id="employeesId"/>
										<a href="#info-for-check" onclick="findContractJson('${contractid}')" data-toggle="modal">续签/修改</a>
									  </s:if>
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
		<!-- Modal -->
		<div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					合同信息
				</h3>
			</div>
			<div class="modal-body">
				<s:form action="updateEmployeesContract" method="post" id="con_form">
					
					<s:hidden name="employeesId"></s:hidden>
					<s:hidden name="enterpriseEmployees.contractid"></s:hidden>
					<div class="row-fluid">
						<div class="input-container">
							<label>
								合同编号
							</label>
							<s:textfield name="employeesContract.contractNo" cssClass="required email" id="input_e"/>
						</div>
						<div class="input-container">
							<label>
								合同期限
							</label>
							起：
							<input type="text" id="d4311"
								name="employeesContract.contractStatrDate"
								 onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4311\')}',skin:'whyGreen'})" class="Wdate" />
						</div>
						<div class="input-container">
							止：
							<input type="text" id="d4312" name="employeesContract.contractEndDate"
								 class="Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01',skin:'whyGreen'})"/>
						</div>
						<div class="input-container">
							续签
							<input type="checkbox" name="employeesContract.status" value="续签"/>
						</div>
						<div class="input-container">
							<s:submit cssClass="btn btn-primary" value="提交" />
						</div>

					</div>
				</s:form>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>
	</body>

</html>
