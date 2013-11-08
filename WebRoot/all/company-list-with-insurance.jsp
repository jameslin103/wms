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
			<div id="header">
				<jsp:include page="../layout/list_header.jsp" />
			</div>
		</div>
		<div id="main">
				<div class="row-fluid">
					<div id="center-pane">

						<ul class="nav nav-tabs">
						<s:iterator value="#session.menuList" id="menu">
			         		 <s:if test="#menu.url=='viewCompanyListWithSaraly'">
					            <li >
								  	<a href="viewCompanyListWithSaraly">工资</a>
								</li>
							</s:if>
							  <s:if test="#menu.url=='viewCompanyListWithInsurance'">
								<li  class="active"> 
								  <a href="viewCompanyListWithInsurance">增减员与参保</a>
								</li>
							</s:if>
							<s:if test="#menu.url=='viewCompanyListWithBalance'">
								<li>
									<a href="viewCompanyListWithBalance">资金往来</a>
								</li>
							</s:if>
					 </s:iterator>
						</ul>
						
						<ul class="normal action-container clearfix">
							<li class="right">
							<form action="viewCompanyListWithInsurance" class="select-for-year" method="post" id="myform1">
								按年份查询:
									<input type="text"  name="year" value="${year}" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')"/>
									
							</form>
							</li>
							    <li><a href="viewCompanyListWithInsurance?month=1&year=${year}">1月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=2&year=${year}">2月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=3&year=${year}">3月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=4&year=${year}">4月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=5&year=${year}">5月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=6&year=${year}">6月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=7&year=${year}">7月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=8&year=${year}">8月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=9&year=${year}">9月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=10&year=${year}">10月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=11&year=${year}">11月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=12&year=${year}">12月</a></li>
						</ul>
					
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>
										序
									</th>
									<th>
										企业
									</th>
									<th>
										所属月份
									</th>
									<th>
										详细
									</th>
									<th>
										状态
									</th>
									<th>
										补充说明
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
							 <s:iterator value="#request.map" id="enterpriseEmployees" status="list">
								<tr>
									<td>
										<s:property value="#list.index+1"/>
									</td>
									<td>
										<s:property value="%{#enterpriseEmployees.enterprise.fullName}"/>
									</td>
									<td>
										<s:property value="%{#enterpriseEmployees.cinsengDate.substring(0,4)}"/>年
										<s:property value="%{#enterpriseEmployees.cinsengDate.substring(5,7)}"/>月
									</td>
									<td>
										
										<a href="insuranceWithEmployeeList?enterpriseId=<s:property value="%{#enterpriseEmployees.enterprise.enterpriseId}"/>">
											新增员工<s:property value="%{#enterpriseEmployees.addIncrease}"/>人，
											减员<s:property value="%{#enterpriseEmployees.ginsengProtectNature}"/>人，
											参保<s:property value="%{#enterpriseEmployees.cinseng}"/>人
										</a>
									</td>
									<td>
										<s:if test="#enterpriseEmployees.reductionState==0">
										    <span>未执行</span>
										</s:if>
										<s:elseif test="#enterpriseEmployees.reductionState==1">
											<span>执行中</span>
										</s:elseif>
										<s:elseif test="#enterpriseEmployees.reductionState==2">
											<span>已完成</span>
										</s:elseif>
										<s:else>
										
										</s:else>
									</td>
									<td>
										<s:property value="%{#enterpriseEmployees.reductionNote}"/>
									</td>
									<td>
										<s:set value="%{#enterpriseEmployees.enterprise.enterpriseId}" name="enterpriseId"></s:set>
										<s:set value="%{#enterpriseEmployees.cinsengDate.substring(5,7)}" name="month"></s:set>
										<s:set value="%{#enterpriseEmployees.cinsengDate.substring(0,4)}" name="year"></s:set>
										<a href="#info-for-check" data-toggle="modal" class="recution" onclick="findEnterpriseEmployeesRecution(${enterpriseId},${month},${year})" data-id="<s:property value="%{#enterpriseEmployees.cinsengDate.substring(5,7)}"/> ">修改</a>
									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>

						<div class="pagination">
							<%@include file="../share/fenye.jsp" %>
						</div>
					</div>
				</div>
			</div>

			<div id="footer"></div>
		</div>

		<div id="info-for-check" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					增减员与参保
				</h3>
			</div>

			<div class="modal-body">
				<div class="row-fluid">
					<form action="updateRecutionState" method="post">
						<input type="hidden" name="enterpriseId"/>
						<input type="hidden" name="year"/>
						<input type="hidden" name="month"/>
						<span id="stateus"></span>
						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<input type="radio" name="reductionState" value="0" checked="checked" />
							未执行，
							<input type="radio" name="reductionState" value="1"/>
							执行中，
							<input type="radio" name="reductionState" value="2"/>
							已完成
						</div>

						<hr/>

						<div class="input-container">
							<label>
								补充说明
							</label>
							<input type="text" name="reductionNote" maxlength="30"/><span style="color: red;">(限30个字符)</span>
						</div>

						<hr/>

						<div class="input-container">
							<s:submit cssClass="btn btn-primary" value="提交"></s:submit>
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
