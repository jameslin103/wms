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
			</div>
			<div id="main">
				<div class="row-fluid">
					<div id="center-pane">
						<ul class="nav nav-tabs">
							<li>
								<a href="index.html">综合</a>
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
						</ul>

						<div class="span4">
							<h3>
								批量减员
							</h3>
							<div class="row-fluid">

								<div class="alert">
									<p>
										减员<s:property value="%{#request.count}"/>人
									</p>
									<p>
										<a href="uploadInsuranceReduction">信息错误，重新导入数据！</a>
									</p>
								</div>
								<hr>
								
								<div>
									<p>
										<a href="viewInsuranceWithMonth" class="btn btn-primary">信息正确，确认！</a>
									</p>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="footer"></div>

		</div>
	</body>

</html>
