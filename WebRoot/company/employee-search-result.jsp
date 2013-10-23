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
								<a href="index.jsp">综合</a>
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


						<h3>
							搜索结果
						</h3>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>
										序
									</th>
									<th>
										姓名
									</th>
									<th>
										企业
									</th>
									<th>
										性别
									</th>
									<th>
										户口
									</th>
									<th>
										婚姻
									</th>
									<th>
										照片
									</th>
									<th>
										身份证
									</th>
									<th>
										电话
									</th>
								</tr>
							</thead>
							<s:iterator value="#request.enterpriseEmployeesList" id="enterpriseEmployees">
							<tbody>
								<tr>
									<td>
										<s:property value="%{#enterpriseEmployees.employeesId}"/>
									</td>
									<td>
										<a href="selectEnterpriseEmployeesWage?employeesId=<s:property value='%{#enterpriseEmployees.employeesId}'/>">
											<s:property value="%{#enterpriseEmployees.employeesName}"/>
										</a>
									</td>
									<td>
										<s:property value="%{#enterpriseEmployees.enterprise.fullName}"/>
									</td>
									<td>
										<s:property value="%{#enterpriseEmployees.employeesSex}"/>
									</td>
									<td>
										
										<s:if test="#enterpriseEmployees.householdRegister==1 ">
											非农
										</s:if>
										<s:elseif test="#enterpriseEmployees.householdRegister==0 ">
											农村
										</s:elseif>
										<s:else>
											&npsp;&npsp;&npsp;&npsp;
										</s:else>
									</td>
									<td>
										
										<s:if test="#enterpriseEmployees.maritalStatus==1">
											已婚
										</s:if>
										<s:elseif test="#enterpriseEmployees.maritalStatus==0">
											未婚
										</s:elseif>
										<s:else>
											&npsp;&npsp;&npsp;&npsp;
										</s:else>
									</td>
									<td>
										<s:if test="#enterpriseEmployees.photo==1">
											有
										</s:if>
										<s:elseif test="#enterpriseEmployees.photo==0">
											没有
										</s:elseif>
										<s:else>
											&npsp;&npsp;&npsp;&npsp;
										</s:else>
									</td>
									<td>
										<s:property value="%{#enterpriseEmployees.cardNumber}"/>
									</td>
									<td>
										<s:property value="%{#enterpriseEmployees.phone}"/>
									</td>
								</tr>
							</tbody>
							</s:iterator>
						</table>

					</div>

				</div>
			</div>
			<div id="footer"></div>
		</div>
	</body>

</html>
