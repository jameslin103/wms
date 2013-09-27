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
							<li class="active">
								<a href="toBeResponsibleEnterprise">我负责的企业</a>
							</li>
							<li>
								<a href="#">所有企业</a>
							</li>
						</ul>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th rowspan="2">
										序号
									</th>
									<th rowspan="2">
										企业
									</th>
									<th colspan="2">
										资金往来（元）
									</th>
									<th rowspan="2">
										工资发放
									</th>
									<th rowspan="2">
										本月增减员
									</th>
									<th rowspan="2">
										负责人
									</th>
								</tr>
								<tr>
									<th>
										企业
									</th>
									<th>
										员工
									</th>
								</tr>
							</thead>

							<s:iterator value="#request.enterpriseList" var="enterprise">
								<tbody>
									<tr>
										<td>
											<s:property value="%{#enterprise.enterpriseId}" />
										</td>
										<td class="with-complement">
											<a href="viewEnterpriseDetailed?enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/>">
											<s:property  value="%{#enterprise.fullName}" /></a>
											<span class="complement"> <s:property
													value="%{#enterprise.contact}" /> 电话:<s:property
													value="%{#enterprise.phone}" /> QQ：<s:property
													value="%{#enterprise.qq}" /> </span>
											<s:set var="enterpriseId" value="%{#enterprise.enterpriseId}"></s:set>
											<a href="#info-of-company" onclick="modalEnterprise('${enterpriseId}')" data-toggle="modal">详细信息</a>，
											<a href="#info-for-check" data-toggle="modal" onclick="modalEnterprise('${enterpriseId}')" >修改联系人</a>
										</td>
										<td>
											<a href="viewBalanceDetail?enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/>"><s:property  value="%{#enterprise.balanceDetailTotal}"/></a>
										</td>
										<td>
											<a href="viewEnterpriseEmployees?enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/> "><s:property value="%{#enterprise.count}"/></a>
										</td>
										<td>
										<ol>
											<s:iterator value="%{#request.enterprises}" id="en">
												<s:iterator value="%{#en.createSalaryBugetTables}" id="cr">
														 <s:if test="#en.enterpriseId==#enterprise.enterpriseId">
														    <li>
															    <a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/>&budgetId=<s:property value="%{#cr.budgetId}"/>">
															    	<s:property value="%{#cr.name}"/>（<s:property value="%{#cr.note}"/>）
															    	</a>
														    </li>
														   </s:if>
														</s:iterator>
													</s:iterator>
											</ol>
										</td>
										<td>
											<s:iterator value="%{#request.enterprises}" id="en">
												 <s:if test="#en.enterpriseId==#enterprise.enterpriseId">
													<a href="viewWorkersIncreased?enterprise.enterpriseId=<s:property value="%{#en.enterpriseId}"/>">
														增员<s:property value="%{#en.addCount}"/>人，
														减员<s:property value="%{#en.renewalCount}"/>人，
														参保<s:property value="%{#en.whetherGinsengCount}"/>人
													</a>
											</s:if>
											</s:iterator>
										</td>
										<td>
											<s:property value="%{#username}"/>刘备
										</td>
									</tr>
									<tr>
								</tbody>
							</s:iterator>
						</table>

					</div>


				</div>
			</div>

			<div id="footer"></div>

		</div>

		<div id="info-of-company" class="modal hide fade" tabindex="-1"	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					企业信息
				</h3>
			</div>
			<div class="modal-body">
				<div class="row-fluid">
					<p>
						公司名称：<input type="text" name="enterprise.rferred" disabled="disabled"/>
					</p>
					<p>
						公司全称：<input type="text" name="enterprise.fullName" disabled="disabled"/>
					</p>
					<p>
						员工人数：<input type="text" name="" value="2000" disabled="disabled"/>
					</p>
					<p>
						公司地址：<input type="text" name="enterprise.address" disabled="disabled"/>
					</p>
					<p>
						法人代表：<input type="text" name="enterprise.legalRepresentative" disabled="disabled"/>
					</p>
					<p>
						开户银行：<input type="text" name="enterprise.accountLine" disabled="disabled"/>
					</p>
					<p>
						开户账号：<input type="text" name="enterprise.enterpriseBankAccount" disabled="disabled"/>
					</p>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>

		<div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					企业联系人信息
				</h3>
			</div>
			<div class="modal-body">
				<s:form action="updateEnterpriseContact" method="post">
				
				    <s:hidden name="enterprise.enterpriseId"></s:hidden>
					<div class="row-fluid">
						<p>
							公司名称：<input type="text" name="enterprise.rferred" disabled="disabled"/>
						</p>
						<p>
							公司全称：<input type="text" name="enterprise.fullName" disabled="disabled"/>
						</p>
						<p>
							公司地址：<input type="text" name="enterprise.address" disabled="disabled"/>
						</p>

						<div class="input-container">
							<label>
								联系人
							</label>
							<input type="text" name="enterprise.contact" maxlength="20">
						</div>

						<div class="input-container">
							<label>
								电话
							</label>
							<input type="text" name="enterprise.phone" maxlength="20">
						</div>
						<div class="input-container">
							<label>
								QQ
							</label>
							<input type="text" name="enterprise.qq" maxlength="20">
						</div>

						<div class="input-container">
							<label>
								传真
							</label>
							<input type="text" name="enterprise.fax" maxlength="20">
						</div>

						<div class="input-container">
							<label>
								电子邮件
							</label>
							<input type="text" name="enterprise.email" maxlength="20">
						</div>

						<div class="input-container">
							<s:submit cssClass="btn btn-primary"  value="提交" />
								
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
