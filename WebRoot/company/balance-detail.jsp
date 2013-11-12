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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
							<s:iterator value="#session.menuList" id="menu">
								<s:if test="#menu.url=='viewEnterpriseEmployees'">
									<li >
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
									<li class="active">
										<a href="viewBalanceDetail" ><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
								<s:if test="#menu.url=='viewEnterpriseDetailed'">
									<li >
										<a href="viewEnterpriseDetailed" ><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
							</s:iterator>
					</ul>
					<s:form action="viewBalanceDetail" method="post" id="myform">
					<s:hidden name="page"/>
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th rowspan="2">
										序号
									</th>
									<th rowspan="2">
										月份
									</th>
									<th rowspan="2">
										期初余额（元）
									</th>
									<th colspan="4" class="center">
										应收款项（元）
									</th>
									<th rowspan="2">
										实收款项（元）
									</th>
									<th rowspan="2">
										期末余额（元）
									</th>
									<th colspan="3" class="center">
										余额分配（元）
									</th>
									<th rowspan="2">
										操作
									</th>
								</tr>
								<tr>
									<th>
										开票总额
									</th>
									<th>
										工资总额
									</th>
									<th>
										服务费总额
									</th>
									<th>
										五险一金
									</th>
									<th>
										工资
									</th>
									<th>
										服务费
									</th>
									<th>
										五险一金
									</th>
								</tr>
							</thead>
							<s:iterator value="%{#request.pageView.records}" id="balanceDetail" status="list">
							<tbody>
								<tr>
									<td rowspan="2">
										<s:property value="#list.index+1"/>
									</td>
									<td>
										<s:date name="%{#balanceDetail.yearMonth}" format="yyyy年MM月"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.balance}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.ballotsToal}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.wagesToal}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.serviceToal}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.receivableFiveFund}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.receivedFunds}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.endingBalance}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.wages}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.serviceWith}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.fiveFund}"/>
									</td>
									<td rowspan="2">
									<s:set value="%{#balanceDetail.detailId}" var="detailId"></s:set>
										<a href="#info-for-check" onclick="findToIdBalanceDetail('${detailId}')" data-toggle="modal">填写</a>
									</td>
								</tr>
								<tr>
									<td colspan="11" class="align-right">
										备注:（<s:property value="%{#request.session.user.username}"/>，
										<s:date name="%{#balanceDetail.createDate}" format="yyyy年MM月dd日"/>，
										<s:property value="%{#balanceDetail.note}"/>）
									</td>
								</tr>
							</tbody>
							</s:iterator>
						</table>
						
						<div class="pagination">
							<ul>
							<%@ include file="/share/fenye.jsp" %>
							</ul>
						</div>
						</s:form>
					</div>
				</div>
			</div>

			<div id="footer"></div>
		</div>
		<!--================================================= add  addBalanceDetail=================================================-->
		<div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					实收款项与余额分配
				</h3>
			</div>

			<div class="modal-body">
				<div class="row-fluid">
					<s:form action="addBalanceDetail" method="post">
					<s:hidden name="balanceDetail.detailId"></s:hidden>
						<div class="input-container">
							<label>
								实收款项 
							</label>
							<s:textfield name="balanceDetail.receivedFunds" />
						</div>
						<hr>
						<h3>
							余额分配
						</h3>
						<div class="input-container">
							<label>
								工资
							</label>
							<s:textfield name="balanceDetail.wages"/>
						</div>
						<div class="input-container">
							<label>
								服务费
							</label>
							<s:textfield name="balanceDetail.serviceWith"/>
						</div>
						<div class="input-container">
							<label>
								五险一金
							</label>
							<s:textfield name="balanceDetail.fiveFund" />
						</div>
						<hr>
						<div class="input-container">
							<label>
								备注
							</label>
							<s:textfield name="balanceDetail.note"/>
						</div>
						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<s:submit value="提交" cssClass="btn btn-primary" />
						</div>
					</s:form>
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
