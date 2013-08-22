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
							<li>
								<a href="viewEnterpriseEmployees">员工档案</a>
							</li>
							<li class="active">
								<a href="company/salary-with-month.jsp">工资预算表</a>
							</li>
							<li>
								<a href="company/insurance-with-month.jsp">增减员与参保明细</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li>
								<a href="#info-for-check" data-toggle="modal">定制奖金与各种补贴</a>
							</li>
						</ul>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th width="10">
										序
									</th>
									<th width="50">
										名称
									</th>
									<th width="20">
										状态
									</th>
									<th width="20">
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.customBonus" id="customBonus">
									<tr>
										<td width="10">
											<s:property value="%{#customBonus.id}" />
										</td>
										<td width="50">
											<s:property value="%{#customBonus.bonusName}" />
										</td>
										<td width="20">
											<s:if test="%{#customBonus.state==0}">
												<span>禁用</span>
											</s:if>
											<s:elseif test="%{#customBonus.state==1}">
												<span>启用</span>
											</s:elseif>
											<s:else>
												<span>&nbsp;&nbsp;</span>
											</s:else>
										</td>
										<td width="20">
											<a href="#info-for-check" data-toggle="modal">修改</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>

						</table>

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
					定制奖金与各种补贴
				</h3>
			</div>
			<div class="modal-body">
				<s:form action="addCustomBonus" method="post">
					<div class="row-fluid">
						<div class="input-container">
							<label>
								名称
							</label>
							<s:textfield name="customBonus.bonusName" />
						</div>
						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<input type="radio" name="customBonus.state" value="1"
								checked="checked">
							启用，
							<input type="radio" name="customBonus.state" value="0">
							停用
						</div>

						<div class="input-container">
							<label>
								&nbsp;
							</label>
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
