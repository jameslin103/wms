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
		<%@ include file="../help/public_css_js.jsp"%>
		<script type="text/javascript">
			 function topage(page){
				var form = document.getElementById("salary_myform");
					form.page.value=page;
					form.submit();
			}
		
		
		</script>
	</head>
	<body>

		<div id="container">
			<div id="header">
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
								员工：<s:property value="%{#request.enterpriseEmployees.employeesName}"/>
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								查看各类明细：
							</li>
							<li>
								<a href="viewEmployeePersonalSalary?employeesId=<s:property value="%{#request.enterpriseEmployees.employeesId}"/>">工资</a>，
							</li>
							<li>
								<a
									href="viewEmployeeContract?employeesId=<s:property value="%{#request.enterpriseEmployees.employeesId}"/>">合同</a>，
							</li>
							<li>
								<a
									href="selectEnterpriseEmployeesWage?employeesId=<s:property value="%{#request.enterpriseEmployees.employeesId}"/>">基本信息</a>，
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								操作：
							</li>
						</ul>
						<s:if test="#request.pageView.records.size()==0 ">
							<table class="table table-striped table-bordered">
				            <thead>
			              	<tr>
			                <th rowspan="2">工资</th>
			                <th rowspan="2">奖金</th>
			                <th rowspan="2">补贴</th>
			                <th rowspan="2">应发工资</th>
			                <th rowspan="2">社会保险基数</th>
			                <th colspan="2">养老保险</th>
			                <th colspan="2">失业保险</th>
			                <th rowspan="2">生育保险基数</th>
			                <th rowspan="2">生育（企业）</th>
						    <th rowspan="2">工伤基数</th>
			                <th rowspan="2">工伤（企业）</th>
			                <th colspan="3">基本医疗保险</th>
			                <th colspan="3">住房公积金</th>
			                <th rowspan="2">疾病统计</th>
			                <th colspan="2">小计</th>
			                <th rowspan="2">税前工资</th>
			                <th colspan="2">个税</th>
			                <th rowspan="2">服务费</th>
			                <th rowspan="2">合计（企业应付）</th>
			                <th rowspan="2">到卡金额</th>
			              </tr>
			              <tr>
			                <td>企业</td>
			                <td>个人</td>
			                <td>企业</td>
			                <td>个人</td>
			                <td>缴费基数</td>
			                <td>企业</td>
			                <td>个人</td>
			                <td>缴费基数</td>
			                <td>企业</td>
			                <td>个人</td>
			                <td>企业</td>
			                <td>个人</td>
			                <td>企业</td>
			                <td>个人</td>
			              </tr>
			            </thead>  
			            	<thbody>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								</thbody>	
							</table>	
						</s:if>
						
						<s:else>
						<s:form action="viewEmployeePersonalSalary" method="post" id="salary_myform">
							<input type="hidden" name="page"/>
							<input  type="hidden" name="employeesId" value="${employeesId}"/>
						</s:form>
						<s:iterator value="#request.pageView.records"	id="employeesSalaryDetail">
							<h3>
								<s:date name="%{#employeesSalaryDetail.salaryDate}" format="yyyy年MM月"/>
							</h3>
							
							<table class="table table-striped table-bordered">
								<thead>
									<tr>
										<th rowspan="2">
											工资
										</th>
										<th rowspan="2">
											奖金
										</th>
										<th rowspan="2">
											补贴
										</th>
										<th rowspan="2">
											应发工资
										</th>
										<th rowspan="2">
											社会保险基数
										</th>
										<th colspan="2">
											养老保险
										</th>
										<th colspan="2">
											失业保险
										</th>
										<th rowspan="2">
											生育保险基数
										</th>
										<th rowspan="2">
											生育（企业）
										</th>
										<th rowspan="2">
											工伤基数
										</th>
										<th rowspan="2">
											工伤（企业）
										</th>
										<th colspan="3">
											基本医疗保险
										</th>
										<th colspan="3">
											住房公积金
										</th>
										<th rowspan="2">
											疾病统计
										</th>
										<th colspan="2">
											小计
										</th>
										<th rowspan="2">
											税前工资
										</th>
										<th colspan="2">
											个税
										</th>
										<th rowspan="2">
											服务费
										</th>
										<th rowspan="2">
											合计（企业应付）
										</th>
										<th rowspan="2">
											到卡金额
										</th>
									</tr>
									<tr>
										<td>
											企业
										</td>
										<td>
											个人
										</td>
										<td>
											企业
										</td>
										<td>
											个人
										</td>
										<td>
											缴费基数
										</td>
										<td>
											企业
										</td>
										<td>
											个人
										</td>
										<td>
											缴费基数
										</td>
										<td>
											企业
										</td>
										<td>个人</td>
										<td>企业</td>
										<td>个人	</td>
										<td>企业	</td>
										<td>个人	</td>
									</tr>
								</thead>
								<thbody>
								<tr>
									<td>
										<s:property value="%{#employeesSalaryDetail.wage}" />
									</td>
									<td>
										<s:property value="%{#employeesSalaryDetail.bonus}" />
									</td>
									<td>
										<s:property value="%{#employeesSalaryDetail.subsidies}" />
									</td>
									<td>
										<s:property value="%{#employeesSalaryDetail.shouldPay}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.socialInsuranceBase}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.enterprisePensionInsurance}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.personalPensionInsurance}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.enterpriseUnemploymentInsurance}" />
									</td>
									<td>
										<s:property	value="%{#employeesSalaryDetail.personalUnemploymentInsurance}" />
									</td>
									<td>
										<s:property value="%{#employeesSalaryDetail.birthInsuranceBase}" />
									</td>
									<td>
										<s:property value="%{#employeesSalaryDetail.enterpriseBirthInsurance}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.inductrialInjuryBase}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.enterpriseInductrialInjuryBase}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.medicalPaymentBase}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.enterpriseMedicalBase}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.personalMedicalBase}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.HousingReserveBase}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.enterpriseReserveBase}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.personalReserveBase}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.morbidityStatistics}" />
									</td>
									<td>
										<s:property
											value="%{#employeesSalaryDetail.enterpriseSubtotal}" />
									</td>
									<td>
										<s:property value="%{#employeesSalaryDetail.personalSubtotal}" />
									</td>
									<td>
										<s:property value="%{#employeesSalaryDetail.beforeSalary}" />
									</td>
									<td>
										<s:property value="%{#employeesSalaryDetail.enterpriseTax}" />
									</td>
									<td>
										<s:property value="%{#employeesSalaryDetail.personalTax}" />
									</td>
									<td>
										<s:property value="%{#employeesSalaryDetail.serviceCharge}" />
									</td>
									<td>
										<s:property value="%{#employeesSalaryDetail.aggregate}" />
									</td>
									<td>&nbsp;
										<s:property value="%{#employeesSalaryDetail.moneyToCards}" />
									</td>
								</tr>
							  </thbody>
							</table>
							</s:iterator>
					</s:else>
					<div class="pagination">
						<%@ include  file="../share/fenye.jsp"%>		
					</div>

					</div>
				</div>
			</div>
		</div>
	</body>

</html>
