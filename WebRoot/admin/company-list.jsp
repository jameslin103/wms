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
		<meta name="viewport" content="width=device-width, initial-scale=1.0" >
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
								<a href="#">所有企业</a>
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
										序号
									</th>
									<th width="50%">
										企业
									</th>
									<th width="30%">
										负责人
									</th>
									<th width="12%">
										操作
									</th>
								</tr>
							</thead>
							<s:iterator value="#request.enterpriseList" id="enterprise" status="en">
								<tbody>
									<tr>
										<td>
											<s:property value="%{#enterprise.enterpriseId}" />
										</td>
										<td class="with-complement">
											<s:property value="%{#enterprise.fullName}" />
											<span class="complement"> <s:property
													value="%{#enterprise.contact}" /> 电话： <s:property
													value="%{#enterprise.phone}" /> QQ： <s:property
													value="%{#enterprise.qq}" /> </span>
										</td>
										<td class="with-complement">
											<s:iterator value="#request.wmsUserList" id="user" status="us">
												<s:property value="%{#user.username}" />
												
											</s:iterator>
											<s:hidden value='%{#enterprise.id}' id="enterId"/>
											<a href="#info-for-check2" data-toggle="modal"   class="complement" >增删负责人 </a>
										</td>
										<td>
											<s:hidden value="%{#enterprise.id}"  id="update"/>
											<s:hidden value="%{#enterprise.contact}" />
											<s:hidden value="%{#enterprise.phone}" />
											<s:hidden value="%{#enterprise.qq}" />
											
																						
											<a href="#info-for-check1"  data-toggle="modal" id="updateto">修改</a>
										</td>
									</tr>
								</tbody>
							</s:iterator>
						</table>
						<!-- ================================================End According to  Enterprise=========================== -->
					</div>
				</div>
			</div>

			<div id="footer"></div>

		</div>
		<div id="info-for-check1" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
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
							<s:textfield name="enterprise.rferred"  value="%{#enterprise.rferred}"/>
						</div>

						<div class="input-container">
							<label>
								全称
							</label>
							<s:textfield name="enterprise.fullName" value="%{#enterprise.fullName}"/>
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
							<input type="radio" name="enterprise.status" value="0"
								checked="checked">
							合约中，
							<input type="radio" name="enterprise.status" value="1">
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
					<form action="" method="post">
						<div class="input-container">
							<label>
								企业名称
							</label>
							<s:property value="%{#enterprise.fullName}"/>
						</div>

						<div class="input-container">
							<label>
								当前负责人
							</label>
							<ul class="list-of-items-for-delete normal clearfix">
							<s:iterator value="#request.wmsUserList" id="user" status="us">		
								<li>
									<s:property value="%{#user.username}" />
									<a href="#">X</a>
								</li>
							</s:iterator>
							</ul>
						</div>
						<div class="input-container">
							<label>
								搜索并添加负责人
							</label>
							<s:select list="%{#request.wmsUsers}" label="0" listKey="username"
							 listValue="username"  headerKey="0" headerValue="-请选择-"/>
						</div>

						<div class="input-container">
							<button type="button" class="btn btn-primary">
								添加
							</button>
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
