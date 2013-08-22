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
							<li class="active">
								<a href="company/employee-list.jsp">员工档案</a>
							</li>
							<li>
								<a href="company/salary-with-month.jsp">工资预算表</a>
							</li>
							<li>
								<a href="company/insurance-with-month.jsp">增减员与参保明细</a>
							</li>
						</ul>
						<div class="span4">
							<h3>
								批量上传新员工信息
							</h3>
							<div class="row-fluid">
								<div class="alert alert-info">
									<ol>
										<li>
											<a href="../doc/增员表.xls">下载增员Excel表格</a>
										</li>
										<li>
											上传新增员工表
											<s:form action="addImportExcelEmployees"
												cssClass="form-search" method="post"
												enctype="multipart/form-data">
												<s:file name="file" />
												<br>
												<s:submit cssClass="btn btn-primary" value="上传" />
											</s:form>
										</li>
									</ol>
									<p class="red">
										备注：上传时，检查身份证号，如果重复载入，给予提醒！
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
