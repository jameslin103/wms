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
				<jsp:include page="../layout/header.jsp"></jsp:include>
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
							<li>
								<a href="company/balance-detail.jsp">资金往来</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li class="right">
								<form action="" class="select-for-year" method="post">
									<select>
										<option value="">
											2014年
										</option>
										<option value="" selected>
											2013年
										</option>
										<option value="">
											2012年
										</option>
									</select>
								</form>
							</li>
							<li>
								<a href="company/salary-step1-of-create.jsp">新建工资预算表</a>
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								<a href="company/salary-template.jsp">工资模板</a>
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								<a href="viewCustomBonus">定制奖金与各种补贴</a>
							</li>
						</ul>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>
										月份
									</th>
									<th>
										工资制作
									</th>
									<th>
										工资发放
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										一月
									</td>
									<td>
										<ol>
											<li>
												<a href="company/salary-with-sum-of-categories.jsp">某某项目12月工资</a>
											</li>
											<li>
												某某项目11月工资
											</li>
										</ol>
									</td>
									<td>
										<ol>
											<li>
												某某项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												某某项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								</tr>
								<tr>
									<td>
										二月
									</td>
									<td>
										<ol>
											<li>
												某某项目12月工资
											</li>
											<li>
												某某项目11月工资
											</li>
										</ol>
									</td>
									<td>
										<ol>
											<li>
												某某项目1月工资
												<span class="blue">（已发放）</span>
											</li>
											<li>
												某某项目1月工资第二批
												<span class="red">（资金已到位，待发放）</span>
											</li>
										</ol>
									</td>
								</tr>

							</tbody>

						</table>

					</div>

				</div>
			</div>

			<div id="footer"></div>
		</div>
	</body>

</html>