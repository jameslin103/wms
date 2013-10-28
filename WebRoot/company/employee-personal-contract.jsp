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
							<li>
								<a href="company/index.jsp">综合</a>
							</li>
							<li class="active">
								<a href="viewEnterpriseEmployees">员工档案</a>
							</li>
							<li>
								<a href="viewSalaryBudgetTable">工资预算表</a>
							</li>
							<li>
								<a href="viewInsuranceWithMonth">增减员与参保明细</a>
							</li>
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
								<a href="selectEnterpriseEmployeesWage?employeesId=<s:property value="%{#request.employees.employeesId}"/>">基本信息</a>，
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								操作：
							</li>
							<!--<li>
								<s:set value="%{#request.employees.employeesId}" var="employeesId"></s:set>
								<a href="#info-for-check" onclick="findIdToEmployees('${employeesId}')" data-toggle="modal">修改</a>
							</li>
						--></ul>

						<h3>
							合同记录
						</h3>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>
										序
									</th>
									<th>
										起
									</th>
									<th>
										止
									</th>
									<th>
										实际终止
									</th>
									<th>
										性质
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<s:property value="#request.employees.employeesId" />
									</td>
									<td>
										<s:date name="#request.employees.startContractDeadline" format="yyyy年MM月dd日"/>
									</td>
									<td>
										<s:date name="#request.employees.endContractDeadline" format="yyyy年MM月dd日"/>
									</td>
									<td>
										<s:date name="#request.employees.reductionDate" format="yyyy年MM月dd日"/>
									</td>
									<td>续签</td>
								</tr>
							</tbody>
						</table>

					</div>

				</div>
			</div>

			<div id="footer"></div>

		</div>
		<!-- Modal -->
		<div id="#info-for-check" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					员工信息
				</h3>
			</div>
			<div class="modal-body">
				<s:form action="updateEnterpriseEmployees" method="post" id="loginForm">
					<s:hidden name="enterpriseEmployees.employeesId"></s:hidden>
					<div class="row-fluid">
						<div class="input-container">
							<label>
								合同编号
							</label>
							<s:textfield name="enterpriseEmployees.contractNo" cssClass="required email" id="input_e"/>

						</div>

						<div class="input-container">
							<label>
								姓名
							</label>
							<s:textfield name="enterpriseEmployees.employeesName" />
						</div>

						<div class="input-container">
							<label>
								身份证
							</label>
							<s:textfield name="enterpriseEmployees.cardNumber" />
						</div>

						<div class="input-container">
							<label>
								性别
							</label>
							<input type="radio" name="enterpriseEmployees.employeesSex"
								value="1" checked="checked" />
							男
							<input type="radio" name="enterpriseEmployees.employeesSex"
								value="0" />
							女
						</div>

						<div class="input-container">
							<label>
								户口性质
							</label>
							<input type="radio" name="enterpriseEmployees.householdRegister"
								value="1" checked="checked" />
							非农
							<input type="radio" name="enterpriseEmployees.householdRegister"
								value="0" />
							农村
						</div>

						<div class="input-container">
							<label>
								是否有照片？
							</label>
							<input type="radio" name="enterpriseEmployees.photo" value="1"
								checked="checked" />
							无
							<input type="radio" name="enterpriseEmployees.photo" value="0" />
							有
						</div>

						<div class="input-container">
							<label>
								电话
							</label>
							<s:textfield name="enterpriseEmployees.phone" />
						</div>

						<div class="input-container">
							<label>
								家庭住址
							</label>
							<s:textfield name="enterpriseEmployees.homeAddress" />
						</div>

						<div class="input-container">
							<label>
								银行卡号
							</label>
							<s:textfield name="enterpriseEmployees.bankCardNumber" />
						</div>

						<div class="input-container">
							<label>
								开户银行
							</label>
							<s:textfield name="enterpriseEmployees.bank" />
						</div>

						<div class="input-container">
							<label>
								籍贯
							</label>
							<s:textfield name="enterpriseEmployees.nativePlace" />
						</div>

						<div class="input-container">
							<label>
								行业
							</label>
							<s:textfield name="enterpriseEmployees.industry" />
						</div>

						<div class="input-container">
							<label>
								岗位
							</label>
							<s:textfield name="enterpriseEmployees.jobs" />
						</div>

						<div class="input-container">
							<label>
								婚姻状况
							</label>
							<input type="radio" name="enterpriseEmployees.maritalStatus" value="1" checked="checked" />
							未婚，
							<input type="radio" name="enterpriseEmployees.maritalStatus" value="0" />
							已婚
						</div>

						<div class="input-container">
							<label>
								文化程度
							</label>
							<s:textfield name="enterpriseEmployees.jobs" />
						</div>

						<div class="input-container">
							<label>
								合同期限
							</label>
							起：
							<s:textfield id="d11"
								name="enterpriseEmployees.startContractDeadline"
								onclick="WdatePicker()" cssClass="Wdate" />
						</div>

						<div class="input-container">
							止：
							<s:textfield id="d11" name="enterpriseEmployees.endContractDeadline"
								onclick="WdatePicker()" cssClass="Wdate" />
						</div>

						<div class="input-container">
							<label>
								是否参保?
							</label>
							<input type="radio" name="enterpriseEmployees.whetherGinseng" value="1" checked="checked" />
							是，
							<input type="radio" name="enterpriseEmployees.whetherGinseng"
								value="0" />
							否
						</div>

						<div class="input-container">
							<label>
								参保类型
							</label>
							<input type="checkbox" name="enterpriseEmployees.sociaSecurity"	value="医保" />
							医保
							<input type="checkbox" name="enterpriseEmployees.healthCare"  value="社保" />
							社保
							<input type="checkbox" name="enterpriseEmployees.accumulationFund" value="公积金" />
							公积金
						</div>

						<div class="input-container">
							<label>
								参保性质
							</label>
							<input type="radio" 	name="enterpriseEmployees.ginsengProtectNature" value="1" checked="checked" />
							新增，
							<input type="radio"	name="enterpriseEmployees.ginsengProtectNature" value="0" />
							续保
						</div>

						<div class="input-container">
							<label>
								开始参保日期:
							</label>
							<s:textfield id="d11" name="enterpriseEmployees.cinsengDate"
								onclick="WdatePicker()" cssClass="Wdate" />
						</div>

						<div class="input-container">
							<label>
								参保基数
							</label>
							<input type="radio" name="enterpriseEmployees.base" value="1"
								checked="checked" />
							默认基数，
							<input type="radio" name="enterpriseEmployees.base" value="0" />
							个性设置
						</div>

						<div class="input-container">
							<label>
								社会保险基数
							</label>
							<s:textfield name="enterpriseEmployees.socialInsurance" />
						</div>
						<div class="input-container">
							<label>
								生育保险基数
							</label>
							<s:textfield name="enterpriseEmployees.fertilityInsurance" />
						</div>
						<div class="input-container">
							<label>
								工伤基数
							</label>
							<s:textfield name="enterpriseEmployees.inductrialBase" />
						</div>
						<div class="input-container">
							<label>
								基本医疗保险基数
							</label>
							<s:textfield name="enterpriseEmployees.basicMedical" />
						</div>
						<div class="input-container">
							<label>
								住房公积金基数
							</label>
							<s:textfield name="enterpriseEmployees.housingFund" />
						</div>

						<div class="input-container">
							<label>
								个税缴纳方式?
							</label>
							<input type="radio" name="enterpriseEmployees.paymentWay" value="1" checked="checked" />
							个人缴纳，
							<input type="radio" name="enterpriseEmployees.paymentWay" value="0" />
							企业缴纳
						</div>

						<div class="input-container">
							<label>
								状态?
							</label>
							<input type="checkbox" name="enterpriseEmployees.pseudoDelete" value="1" />
							隐藏
						</div>

						<div class="input-container">
							<label>
								服务费
							</label>
							<s:textfield name="enterpriseEmployees.serviceCost" />
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
