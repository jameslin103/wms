
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
							<li class="active">
								<a href="viewEnterprise">所有企业</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li>
								<a href="#info-for-check1" data-toggle="modal">添加新企业</a>
							</li>
						</ul>
						<!-- ======================================According to  Enterprise==================================== -->
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th width="8%">
										&nbsp;&nbsp;&nbsp;&nbsp;序号
									</th>
									<th width="8%">
										&nbsp;&nbsp;&nbsp;&nbsp;全选<br/>
										&nbsp;&nbsp;<input type="button" id="delete" value="删除	" 
										style="background-color:transparent; border:0px; color:#2E9AFE"/><br/>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="all_box"/>
									</th>
									<th width="50%" style="text-align: center;">
										企业
									</th>
									<th width="30%" style="text-align: center;">
										负责人
									</th>
									<th width="12%" style="text-align: center;">
										操作
									</th>
								</tr>
							</thead>
							<s:iterator value="#request.enterpsie" id="enterprise" status="list">
								<tbody>
									<tr>
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="%{#list.index+1}" />
										</td>
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="checkbox" value="<s:property  value="%{#enterprise.enterpriseId}" />"/>
										</td>
										<td class="with-complement">
											<s:property value="%{#enterprise.fullName}" />
											<span class="complement"> 
													<s:property value="%{#enterprise.contact}" />  
													电话： <s:property value="%{#enterprise.phone}" /> 
													QQ： <s:property value="%{#enterprise.qq}" /> 
											</span>
										</td>
										<td class="with-complement">
											<s:iterator value="#request.enterprise.user" id="user">
												<s:property value="%{#user.username}" />
											</s:iterator>
											<s:set value="%{#enterprise.enterpriseId}" var="enterpriseId"></s:set>
											<s:hidden value='%{#enterprise.id}' id="enterId"/>
											<a href="#info-for-check2" data-toggle="modal"  onclick="findEnterpriseToUser('${enterpriseId}')" class="complement" >
												<s:iterator value="#enterprise.user" id="user"> 
													<s:iterator value="#user.role" id="role">
														<s:property value="%{#role}"/>
													</s:iterator>
												</s:iterator>
												<s:if test="#request.isSystemAdmin==true">
														<span>增删负责人</span>
												</s:if>
													
											 </a>
										</td>
										<td>
											<a href="#info-for-check3"  data-toggle="modal" id="updateto" onclick="modalEnterprise('${enterpriseId}')" >修改</a>
										</td>
									</tr>
								</tbody>
							</s:iterator>
						</table>
						<!-- ================================================End According to  Enterprise=========================== -->
						<!--<table id="enterpriseflexigrid" style="display:none;">
						</table>
					
					
					
					--></div>
				</div>
			</div>

			<div id="footer"></div>

		</div>
		<div id="info-for-check1" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					企业信息
				</h3>
			</div>
			<!-- ==================================addEnterprise====================================== -->
			
			<div class="modal-body">
				<s:form action="addEnterprise" method="post">
					<div class="row-fluid">
						<div class="input-container">
							<label>
								简称
							</label>
							<s:textfield name="enterprise.rferred" />
						</div>

						<div class="input-container">
							<label>
								全称
							</label>
							<s:textfield name="enterprise.fullName" />
						</div>

						<div class="input-container">
							<label>
								法人代表
							</label>
							<s:textfield name="enterprise.legalRepresentative"/>
						</div>

						<div class="input-container">
							<label>
								开户行
							</label>
							<s:textfield name="enterprise.accountLine" />
						</div>

						<div class="input-container">
							<label>
								企业银行账号
							</label>
							<s:textfield name="enterprise.enterpriseBankAccount" />
						</div>

						<div class="input-container">
							<label>
								地址
							</label>
							<s:textfield name="enterprise.address" />
						</div>

						<div class="input-container">
							<label>
								联系人
							</label>
							<s:textfield name="enterprise.contact" />
						</div>

						<div class="input-container">
							<label>
								电话
							</label>
							<s:textfield name="enterprise.phone" />
						</div>
						<div class="input-container">
							<label>
								QQ
							</label>
							<s:textfield name="enterprise.qq" />
						</div>
						<div class="input-container">
							<label>
								传真
							</label>
							<s:textfield name="enterprise.fax" />
						</div>

						<div class="input-container">
							<label>
								电子邮件
							</label>
							<s:textfield name="enterprise.email" />
						</div>
						<div class="input-container">
							<label>
								状态?
							</label>
							<input type="radio" name="enterprise.status" value="0"checked="checked"/>
							合约中，
							<input type="radio" name="enterprise.status" value="1"/>
							暂停
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
		<!-- =================================updateEnterprise====================================== -->
		<div id="info-for-check3" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					企业信息
				</h3>
			</div>
			<div class="modal-body" id="updateEnterprise">
				<s:form action="/updateEnterprise" method="post">
				<s:hidden name="enterprise.enterpriseId" value=""></s:hidden>
					<div class="row-fluid">
						<div class="input-container">
							<label>
								简称
							</label>
							<s:textfield name="enterprise.rferred"  value=""/>
						</div>

						<div class="input-container">
							<label>
								全称
							</label>
							<s:textfield name="enterprise.fullName" value=""/>
						</div>

						<div class="input-container">
							<label>
								法人代表
							</label>
							<s:textfield name="enterprise.legalRepresentative"  value="%{#enterprise.legalRepresentative}"/>
						</div>

						<div class="input-container">
							<label>
								开户行
							</label>
							<s:textfield name="enterprise.accountLine"  value="%{#enterprise.accountLine}"/>
						</div>

						<div class="input-container">
							<label>
								企业银行账号
							</label>
							<s:textfield name="enterprise.enterpriseBankAccount"  value="%{#enterprise.enterpriseBankAccount}"/>
						</div>

						<div class="input-container">
							<label>
								地址
							</label>
							<s:textfield name="enterprise.address"  value="%{#enterprise.address}"/>
						</div>

						<div class="input-container">
							<label>
								联系人
							</label>
							<s:textfield name="enterprise.contact"  value="%{#enterprise.contact}"/>
						</div>

						<div class="input-container">
							<label>
								电话
							</label>
							<s:textfield name="enterprise.phone"  value="%{#enterprise.phone}"/>
						</div>
						<div class="input-container">
							<label>
								QQ
							</label>
							<s:textfield name="enterprise.qq"  value="%{#enterprise.qq}"/>
						</div>

						<div class="input-container">
							<label>
								传真
							</label>
							<s:textfield name="enterprise.fax"  value="%{#enterprise.fax}"/>
						</div>

						<div class="input-container">
							<label>
								电子邮件
							</label>
							<s:textfield name="enterprise.email"  value="%{#enterprise.email}"/>
						</div>

						<div class="input-container">
							<label>
								状态?
							</label>
							<input type="radio" name="enterprise.status" value="0"/>
							合约中，
							<input type="radio" name="enterprise.status" value="1"/>
							暂停
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
		
		
		<div id="info-for-check2" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					增删负责人
				</h3>
			</div>
			<div class="modal-body">
				<div class="row-fluid">
					<form action="addEnterpriseToUser" method="post">
						<s:hidden name="enterpriseId" value=""></s:hidden>
						<div class="input-container">
							<label>
								企业名称
							</label>
							<s:label value="" id="fullName"></s:label>
						</div>

						<div class="input-container">
							 <label>当前负责人</label>
				            <ul class="list-of-items-for-delete normal clearfix">
				              	
				            </ul>
						</div>
						<div class="input-container">
							<label>
								搜索并添加负责人
							</label>
							<s:select list="%{#request.wmsUsers}" name="userId" label="0" listKey="userId"
							 listValue="username"  headerKey="0" headerValue="-请选择-"/>
						</div>

						<div class="input-container">
							<s:submit cssClass="btn btn-primary" value="添加" />
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>
	</body>

</html>
