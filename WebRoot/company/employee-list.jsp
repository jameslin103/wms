<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="/help/public_css_js.jsp"%>
		<script type="text/javascript">
		function topage(page){
		
			var form = document.getElementById("myform");
			form.page.value=page;
			form.submit();
		}
		</script>
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
							<li class="active">
								<a href="viewEnterpriseEmployees">员工档案</a>
							</li>
							<li>
								<a href="viewSalaryBudgetTable">工资预算表</a>
							</li>
							<li>
								<a href="company/insurance-with-month.jsp">增减员与参保明细</a>
							</li>
							<li>
								<a href="viewBalanceDetail">资金往来</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li>
								新员工档案：
							</li>
							<li>
								<a href="batchExcelDataEmployee">批量录入</a>，
							</li>
							<li>
								<a href="#info-for-check" data-toggle="modal">单个录入</a>
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								查看：
							</li>
							<li>
								<a href="fildInsuranceEnterpriseEmployees?insurance=1">参保</a>，
							</li>
							<li>
								<a href="fildInsuranceEnterpriseEmployees?insurance=0">未参保</a>，
							</li>
							<li>
								<a href="#">离职员工</a>，
							</li>
							<li>
								<a href="#">隐藏信息</a>
							</li>
							<li class="right">
								<a href="exportExcel" class="btn btn-primary">下载全体在职员工信息</a>
							</li>
							<li class="right">
								<s:form cssClass="navbar-form pull-left" action="fildAllEnterpriseEmployees" method="post">
									
									<input type="text" name="employessName" placeholder="输入姓名"/>
									<input type="checkbox" name="all" value="1"/>&nbsp;全站
                					<s:submit type="submit" cssClass="btn" value="搜索" />
								</s:form>
							</li>
						</ul>
					<s:form action="viewEnterpriseEmployees" method="post" id="myform">
						<s:hidden name="page"/>
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th rowspan="2" width="">
										序
									</th>
									<th rowspan="2" width="">
										姓名
									</th>
									<th rowspan="2" width="">
										性别
									</th>
									<th rowspan="2" width="">
										户口
									</th>
									<th rowspan="2" width="">
										婚姻
									</th>
									<th rowspan="2" width="">
										照片
									</th>
									<th rowspan="2" width="">
										身份证
									</th>
									<th rowspan="2" width="">
										电话
									</th>
									<th rowspan="2" width="">
										服务费
										<br>
										（元）
									</th>
									<th colspan="2" width="">
										参保
									</th>
									<th rowspan="2" width="">
										哪月起
										<br>
										参保
									</th>
									<th rowspan="2" width="">
										五险一金基数设置
									</th>
									<th rowspan="2" width="">
										个税缴纳
									</th>
									<th rowspan="2" width="">
										操作
									</th>
								</tr>
								<tr>
									<th>
										是否
									</th>
									<th>
										性质
									</th>
								</tr>

							</thead>
							<s:iterator value="#request.employees" id="emp">
								<tbody>
									<tr>
										<td>
											<s:property value="%{#emp.employeesId}" />
										</td>
										<td>
											<s:hidden value="%{#emp.employeesId}" name="employeesId" />
											<s:property value='<s:hidden value="{#emp.employeesId}"/>' />
											<a
												href="selectEnterpriseEmployeesWage?employeesId=<s:property value="%{#emp.employeesId}"/> ">
												<s:property value="%{#emp.employeesName}" /> </a>
										</td>
										<td>
											<s:property value="%{#emp.employeesSex}" />
										</td>
										<td>
											<s:if test="%{#emp.householdRegister==0}">
												<span>非农</span>
											</s:if>
											<s:if test="%{#emp.householdRegister==1}">
												<span>农村</span>
											</s:if>
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
											<s:if test="%{#emp.photo==1}">
												<span>有</span>
											</s:if>
											<s:else>
												<span>否</span>
											</s:else>
										</td>
										<td>
											<s:property value="%{#emp.cardNumber}" />
										</td>
										<td>
											<s:property value="%{#emp.phone}" />
										</td>
										<td>
											<s:property value="%{#emp.serviceCost}" />
										</td>

										<td>
											<s:if test="%{#emp.whetherGinseng==1}">
												<span>是</span>
											</s:if>
											<s:elseif test="%{#emp.whetherGinseng==0}">
												<span>否</span>
											</s:elseif>
											<s:else>
												<span>&nbsp;&nbsp;</span>
											</s:else>
										</td>
										<td>
											<s:property value="%{#emp.ginsengProtectNature}" />
										</td>
										<td>
											<s:date name="%{#emp.cinsengDate}" format="yyyy年MM月dd"/>
										</td>
										<td>
											<s:if test="%{#emp.base==0}">
						                		默认
						                	</s:if>
											<s:if test="%{#emp.base==1}">
						                		个性设置
						                	</s:if>
										</td>
										<td>
											<s:property value="%{#emp.paymentWay}" />
										</td>
										<td>
											<a href="#info-for-check" data-toggle="modal">修改</a>
										</td>
									</tr>
								</tbody>
							</s:iterator>
						</table>

						<div class="pagination">
						<font color="blue">  当前页:第${pageView.currentpage}页 | 总记录数:${pageView.totalrecord}条 | 每页显示:${pageView.maxresult}条 | 总页数:${pageView.totalpage}页</font>　
						<s:iterator begin="%{#request.pageView.pageindex.startindex}" end="%{#request.pageView.pageindex.endindex}" var="wp">
    					<s:if test="%{#request.pageView.currentpage==wp}">
	    					<b>
	    						<font color="blue">第${wp}页</font>
	    					</b>
    					</s:if>
   						 <s:if test="%{#request.pageView.currentpage!=wp}">
   						 	<a href="javascript:topage('${wp}')">第${wp}页</a>
   						 </s:if>
						</s:iterator>
						</div>
						</s:form>
					</div>
				</div>
			</div>

			<div id="footer"></div>

		</div>
		<!-- Modal AddEnterpriseEmpoloyess-->
		<div id="info-for-check" class="modal hide fade" tabindex="-1"
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
				<s:form action="addEnterpriseEmployees" method="post">
					<div class="row-fluid">
						<div class="input-container">
							<label>
								合同编号
							</label>
							<s:textfield name="enterpriseEmployees.contractNo" />

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
							<s:select list="#{1:'工商',2:'农行',3:'兴业'}"
								name="enterpriseEmployees.bank" label="abc" listKey="key"
								listValue="value" headerKey="0" headerValue="-请选择-" />
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
							<s:select list="#{1:'计算机与信息',2:'制造业',3:'财政金融'}"
								name="enterpriseEmployees.industry" label="abc" listKey="key"
								listValue="value" headerKey="0" headerValue="-请选择-" />
						</div>

						<div class="input-container">
							<label>
								岗位
							</label>
							<s:select list="#{1:'销售',2:'生产'}" name="enterpriseEmployees.jobs"
								label="abc" listKey="key" listValue="value" headerKey="0"
								headerValue="-请选择-" />

						</div>

						<div class="input-container">
							<label>
								婚姻状况
							</label>
							<input type="radio" name="enterpriseEmployees.maritalStatus"
								value="1" checked="checked" />
							未婚，
							<input type="radio" name="enterpriseEmployees.maritalStatus"
								value="0" />
							已婚
						</div>

						<div class="input-container">
							<label>
								文化程度
							</label>
							<s:select
								list="#{1:'博士',2:'研究生',3:'本科',4:'大专',5:'中专',6:'高中',7:'初中',8:'小学'}"
								name="enterpriseEmployees.jobs" label="abc" listKey="key"
								listValue="value" headerKey="0" headerValue="-=请选择=-" />
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
							<s:textfield id="d11"
								name="enterpriseEmployees.endContractDeadline"
								onclick="WdatePicker()" cssClass="Wdate" />
						</div>

						<div class="input-container">
							<label>
								是否参保?
							</label>
							<input type="radio" name="enterpriseEmployees.whetherGinseng"
								value="1" checked="checked" />
							是，
							<input type="radio" name="enterpriseEmployees.whetherGinseng"
								value="0" />
							否
						</div>

						<div class="input-container">
							<label>
								参保类型
							</label>
							<input type="checkbox" name="enterpriseEmployees.sociaSecurity"
								value="0" />
							医保
							<input type="checkbox" name="enterpriseEmployees.healthCare"
								value="0" />
							社保
							<input type="checkbox"
								name="enterpriseEmployees.accumulationFund" value="0" />
							公积金
						</div>

						<div class="input-container">
							<label>
								参保性质
							</label>
							<input type="radio"
								name="enterpriseEmployees.ginsengProtectNature" value="1"
								checked="checked" />
							新增，
							<input type="radio"
								name="enterpriseEmployees.ginsengProtectNature" value="0" />
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
							<input type="radio" name="enterpriseEmployees.paymentWay"
								value="1" checked="checked" />
							个人缴纳，
							<input type="radio" name="enterpriseEmployees.paymentWay"
								value="0" />
							企业缴纳
						</div>

						<div class="input-container">
							<label>
								状态?
							</label>
							<input type="checkbox" name="enterpriseEmployees.pseudoDelete"
								value="0" />
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
