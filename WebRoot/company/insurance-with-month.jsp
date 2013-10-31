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
		<script>
			$(function (){
			   $("input[name='insuranceYear']").blur(function(){
			   	   var year=$("input[name='insuranceYear']").val();
			   	   if(year!="")
			   	   {
			   	   	   $(".select-for-year").submit();
			   	   }
			   });
			});
		
		
		</script>

	</head>
	<body>
		<div id="container">
			<div id="header">
				<jsp:include page="../layout/header.jsp"></jsp:include>

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
							<li>
								<a href="viewEnterpriseEmployees">员工档案</a>
							</li>
							<li>
								<a href="viewSalaryBudgetTable">工资预算表</a>
							</li>
							<li class="active">
								<a href="viewInsuranceWithMonth">增减员与参保明细</a>
							</li>
							<li>
								<a href="viewBalanceDetail">资金往来</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li class="right">
							
								<form action="viewInsuranceWithMonth" class="select-for-year" method="post">
									按年份查询:
									<!--<input id="d11" name="insuranceYear" onclick="WdatePicker()" class="Wdate" style="width: 110px;height: 25px;" />
										 -->
									<input type="text" name="insuranceYear" value="${insuranceYear}" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')"/>
									<input type="hidden" value="${insuranceYear}" name="insuranceYear"/>
								</form>
							</li>
							<li>
								<a href="increaseEmployeesUI">批量增员</a>（与续保）
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								<a href="insuranceReductionUI">批量减员</a>
							</li>
						</ul>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th align="center">
										月份
									</th>
									<th align="center">
										详细
									</th>
									<th align="center">
										状态
									</th>
									<th align="center">
										补充说明
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.insuranceSumTotal" id="sumTotal" status="list">
								<tr>
									<td align="center">
										<s:property value="%{#sumTotal[4]}"/>月
									</td>
									<td>
										<a href="insuranceWithEmployeeList?month=<s:property value="%{#sumTotal[4]}"/>
										&insuranceYear=<s:property value="%{#request.insuranceYear}"/>">
											新增<s:property value="%{#sumTotal[1]}"/>人，
											续保<s:property value="%{#sumTotal[2]}"/>人，
											减员<s:property value="%{#sumTotal[3]}"/>人
										</a>
									</td>
									<td>
										<s:if test="#sumTotal[1]==0">
											<span>未执行</span>
										</s:if>
										<s:elseif test="#sumTotal[1]==1">
											<span>执行中</span>
										</s:elseif>
										<s:elseif test="#sumTotal[1]==1">
											<span>已完成</span>
										</s:elseif>
									</td>
									<td>
										<!-- - -->
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
	</body>

</html>
