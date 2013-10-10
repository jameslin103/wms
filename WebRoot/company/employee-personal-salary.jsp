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
								员工：<s:property value="%{#request.employees.employeesName}"/>
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
									href="selectEnterpriseEmployeesWage?employeesId=<s:property value="%{#request.employees.employeesId}"/>">基本信息</a>，
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								操作：
							</li>
							<li>
								<a href="#info-for-check" data-toggle="modal">修改</a>
							</li>

						</ul>
						<s:if test="#request.employeesSalaryDetails.size()==0 ">
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
						<s:iterator value="#request.employeesSalaryDetails"	id="employeesSalaryDetail">
					
							<h3>
								<s:date name="%{#employeesSalaryDetail.createDate}" format="yyyy年MM月dd"/>
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

			<div id="footer"></div>

		</div>
		<!-- Modal -->
		<div id="info-for-check"
			class="modal hide fade modal-of-info-for-check" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					员工信息
				</h3>
			</div>
			<div class="modal-body">
				<s:form action="updateSalaryEnterpriseEmployees" method="post">
					<s:hidden value="%{#request.employees.employeesId}" name="enterpriseEmployees.employeesId"/>
					<div class="row-fluid">
						<div class="input-container">
							<label>
								合同编号
							</label>
							<s:textfield name="enterpriseEmployees.contractNo"  value="%{#request.employees.contractNo}"/>
						</div>

						<div class="input-container">
							<label>
								姓名
							</label>
							<s:textfield name="enterpriseEmployees.employeesName"  value="%{#request.employees.employeesName}"/>
						</div>

						<div class="input-container">
							<label>
								身份证
							</label>
							<s:textfield name="enterpriseEmployees.cardNumber"  value="%{#request.employees.cardNumber}"/>
						</div>

						<div class="input-container">
							<label>
								性别
							</label>
							<input type="radio" name="sex" value="1" checked="checked">
							男，
							<input type="radio" name="sex" value="0">
							女
						</div>

						<div class="input-container">
							<label>
								户口性质
							</label>
							<input type="radio"  name="enterpriseEmployees.householdRegister" value="1"
								checked="checked">
							非农，
							<input type="radio"  name="enterpriseEmployees.householdRegister" value="0">
							农村
						</div>

						<div class="input-container">
							<label>
								是否有照片？
							</label>
							<input type="radio"  name="enterpriseEmployees.photo" value="1" checked="checked">
							无，
							<input type="radio"  name="enterpriseEmployees.photo" value="0">
							有
						</div>

						<div class="input-container">
							<label>
								电话
							</label>
							<s:textfield name="enterpriseEmployees.phone"  value="%{#request.employees.phone}"/>
						</div>

						<div class="input-container">
							<label>
								家庭住址
							</label>
							<s:textfield name="enterpriseEmployees.homeAddress"  value="%{#request.employees.homeAddress}"/>
						</div>

						<div class="input-container">
							<label>
								银行卡号
							</label>
							<s:textfield name="enterpriseEmployees.bankCardNumber"  value="%{#request.employees.bankCardNumber}"/>
						</div>

						<div class="input-container">
							<label>
								开户银行
							</label>
							<select>
								<option>
									工商
								</option>
								<option>
									农行
								</option>
								<option>
									兴业
								</option>
							</select>
						</div>

						<div class="input-container">
							<label>
								籍贯
							</label>
							<s:textfield name="enterpriseEmployees.nativePlace"  value="%{#request.employees.nativePlace}"/>
						</div>

						<div class="input-container">
							<label>
								行业
							</label>
							<select>
								<option value="">
									计算机与信息
								</option>
								<option value="">
									制造业
								</option>
								<option value="">
									财政金融
								</option>
							</select>
						</div>

						<div class="input-container">
							<label>
								岗位
							</label>
							<select>
								<option value="">
									销售
								</option>
								<option value="">
									生产
								</option>
							</select>
						</div>

						<div class="input-container">
							<label>
								婚姻状况
							</label>
							<input type="radio" name="enterpriseEmployees.maritalStatus" value="1" checked="checked">
							未婚，
							<input type="radio" name="enterpriseEmployees.maritalStatus" value="0">
							已婚
						</div>

						<div class="input-container">
							<label>
								文化程度
							</label>
							<select>
								<option value="">
									博士
								</option>
								<option value="">
									研究生
								</option>
								<option value="">
									本科
								</option>
								<option value="">
									大专
								</option>
								<option value="">
									中专
								</option>
								<option value="">
									高中
								</option>
								<option value="">
									初中
								</option>
								<option value="">
									小学
								</option>
							</select>
						</div>

						<div class="input-container">
							<label>
								合同期限
							</label>
							起：
							<input type="text" name="enterpriseEmployees.startContractDeadline" value="<s:date name='%{#request.employees.startContractDeadline}' format="yyyy-MM-dd"/>" 
							id="d11" class="Wdate" onclick="WdatePicker()"/>
						</div>
						
						<div class="input-container">
							止：
							<input type="text" name="enterpriseEmployees.endContractDeadline" value="<s:date name='%{#request.employees.endContractDeadline}' format="yyyy-MM-dd"/>" 
							id="d11" class="Wdate" onclick="WdatePicker()"/>
							
						</div>

						<div class="input-container">
							<label>
								是否参保?
							</label>
							<input type="radio" name="has-insurance" value="1"
								checked="checked">
							是，
							<input type="radio" name="has-insurance" value="0">
							否
						</div>

						<div class="input-container">
							<label>
								参保类型
							</label>
							<input type="checkbox" name="">
							医保，
							<input type="checkbox" name="">
							社保，
							<input type="checkbox" name="">
							公积金
						</div>

						<div class="input-container">
							<label>
								参保性质
							</label>
							<input type="radio" name="enterpriseEmployees.sociaSecurity" value="1" checked="checked">
							新增，
							<input type="radio" name="enterpriseEmployees.sociaSecurity" value="0">
							续保
						</div>

						<div class="input-container">
							<label>
								从哪一月开始参保？
							</label>
							<input type="text" name="enterpriseEmployees.cinsengDate" value="<s:date name='%{#request.employees.cinsengDate}' format="yyyy-MM-dd"/>" 
							 id="d11" class="Wdate" onclick="WdatePicker()"/>
						</div>

						<div class="input-container">
							<label>
								参保基数
							</label>
							<input type="radio" name="enterpriseEmployees.base" value="1" checked="checked">
							默认基数，
							<input type="radio" name="enterpriseEmployees.base" value="0">
							个性设置
						</div>

						<div class="input-container">
							<label>
								社会保险基数
							</label>
							<s:textfield name="enterpriseEmployees.socialInsurance"  value="%{#request.employees.socialInsurance}"/>
						</div>
						<div class="input-container">
							<label>
								生育保险基数
							</label>
							<s:textfield name="enterpriseEmployees.fertilityInsurance"  value="%{#request.employees.fertilityInsurance}"/>
						</div>
						<div class="input-container">
							<label>
								工伤基数
							</label>
							<s:textfield name="enterpriseEmployees.inductrialBase"  value="%{#request.employees.inductrialBase}"/>
						</div>
						<div class="input-container">
							<label>
								基本医疗保险基数
							</label>
							<s:textfield name="enterpriseEmployees.basicMedical"  value="%{#request.employees.basicMedical}"/>
						</div>
						<div class="input-container">
							<label>
								住房公积金基数
							</label>
							<s:textfield name="enterpriseEmployees.housingFund"  value="%{#request.employees.housingFund}"/>
						</div>

						<div class="input-container">
							<label>
								个税缴纳方式?
							</label>
							<input type="radio"  name="enterpriseEmployees.paymentWay" value="1" checked="checked">
							个人缴纳，
							<input type="radio"  name="enterpriseEmployees.paymentWay"  value="0">
							企业缴纳
						</div>

						<div class="input-container">
							<label>
								状态?
							</label>
							<input type="checkbox" name="enterpriseEmployees.pseudoDelete" value="0">
							隐藏
						</div>

						<div class="input-container">
							<label>
								服务费
							</label>
							<s:textfield name="enterpriseEmployees.serviceCost"  value="%{#request.employees.serviceCost}"/>
						</div>

						<div class="input-container">
							<s:submit value="提交" cssClass="btn btn-primary" />
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
