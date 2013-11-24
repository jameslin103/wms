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
		
		<script>
			function topage(page){
				var form = document.getElementById("to_enter_form");
				form.page.value=page;
				form.submit();
		  }
		
		</script>

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
							<!--<li>
								<a href="toBeResponsibleEnterprise">所有企业</a>
							</li>
						--></ul>
						<s:form action="toBeResponsibleEnterprise" method="post" id="to_enter_form">
						<input name="page" type="hidden"/>
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th rowspan="2" style="text-align: center;">
										序号
									</th>
									<!--<th rowspan="2">
										&nbsp;&nbsp;全选<br/>
										<input type="button" id="delete" value="删除	" 
										style="background-color:transparent; border:0px; color:#2E9AFE"/><br/>
										&nbsp;&nbsp;<input type="checkbox" id="all_box"/>
									</th>
									--><th rowspan="2" style="text-align: center;">
										企业
									</th>
									<th colspan="2" style="text-align: center;">
										资金往来（元）
									</th>
									<th rowspan="2" style="text-align: center;">
										工资发放
									</th>
									<th rowspan="2" style="text-align: center;">
										本月增减员
									</th>
									<th rowspan="2" style="text-align: center;">
										负责人
									</th>
								</tr>
								<tr>
									<th style="text-align: center;">
										企业
									</th>
									<th style="text-align: center;">
										员工
									</th>
								</tr>
							</thead>
							<s:iterator value="#request.enterprises" id="enterprise" status="list">
								<tbody>
									<tr>
										<td style="text-align: center;">
											<s:property value="%{#list.index+1}" />
										</td>
										<!--<td>
											<input type="checkbox" value="<s:property  value="%{#enterprise.enterpriseId}" />"/>
										</td>-->
										<td class="with-complement" >
											<a href="viewEnterpriseDetailed?enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/>">
											<s:property  value="%{#enterprise.fullName}" /></a>
											<span class="complement">
												  <s:property value="%{#enterprise.contact}" />
												  电话:<s:property value="%{#enterprise.phone}" />
												 QQ：<s:property value="%{#enterprise.qq}" /> 
											</span>
											<s:set var="enterpriseId" value="%{#enterprise.enterpriseId}"></s:set>
											<a href="#info-of-company" onclick="modalEnterprise('${enterpriseId}')" data-toggle="modal">详细信息</a>，
											<a href="#info-for-check" data-toggle="modal" onclick="modalEnterprise('${enterpriseId}')" >修改联系人</a>
										</td>
										<td style="text-align: center;">
											<a href="viewBalanceDetail?enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/>" id="viewdetail">
												<s:property  value="%{#enterprise.balanceDetailTotal}"/></a>
										</td>
										<td style="text-align: center;">
											<a href="viewEnterpriseEmployees?enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/> "><s:property value="%{#enterprise.count}"/></a>
										</td>
										<td>
										<ol>
											<s:iterator value="#enterprise.createSalaryBugetTables" id="cr">
														    <li>
															    <a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/>&budgetId=<s:property value="%{#cr.budgetId}"/>">
																    	<s:if test="#cr.name.length()>15">
																			<s:property value="#cr.name.substring(0,15)+'...'"/>
																		</s:if>
																		<s:else>
																			<s:property value="%{#cr.name}"/>
																		</s:else>
																		<s:if test="#cr.note!=null && !#cr.note.isEmpty()">
																			（<s:property value="%{#cr.note}"/>）
																		</s:if>		    	
															    	
															    </a>
														    </li>
												</s:iterator>
										
										</ol>
										</td>
										<td>
											<a href="viewWorkersIncreased?enterprise.enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/>">
													增员<s:property value="%{#enterprise.addCount}"/>人，
													减员<s:property value="%{#enterprise.reduction}"/>人，
													参保<s:property value="%{#enterprise.whetherGinsengCount}"/>人
											</a>
										</td>
										<td style="text-align: center;">
											<s:property value="%{#session.user.username}"/>
										</td>
									</tr>
								</tbody>
							</s:iterator>
						</table>
						<div class="pagination">
							<%@ include file="/share/fenye.jsp" %>
						</div>
					</s:form>
	
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
							<input type="text" name="enterprise.contact" maxlength="20"/>
						</div>

						<div class="input-container">
							<label>
								电话
							</label>
							<input type="text" name="enterprise.phone" maxlength="20"/>
						</div>
						<div class="input-container">
							<label>
								QQ
							</label>
							<input type="text" name="enterprise.qq" maxlength="20"/>
						</div>

						<div class="input-container">
							<label>
								传真
							</label>
							<input type="text" name="enterprise.fax" maxlength="20"/>
						</div>

						<div class="input-container">
							<label>
								电子邮件
							</label>
							<input type="text" name="enterprise.email" maxlength="20"/>
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
