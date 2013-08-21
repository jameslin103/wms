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
				<ul class="user normal clearfix">
					<li>
						<a href="account/password.jsp">某某员工</a>
					</li>
					<li>
						<a href="#">退出</a>
					</li>
				</ul>

				<div class="navbar">
					<div class="navbar-inner">
						<div class="container">
							<a class="brand" href="#">富民</a>
							<ul class="nav">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										企业 <b class="caret"></b> </a>
									<ul class="dropdown-menu" role="menu"
										aria-labelledby="dropdownMenu">
										<li>
											<a tabindex="-1" href="company/list.jsp">我的企业</a>
										</li>
										<li>
											<a tabindex="-1" href="#">所有企业</a>
										</li>
									</ul>
								</li>

								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										管理 <b class="caret"></b> </a>
									<ul class="dropdown-menu" role="menu"
										aria-labelledby="dropdownMenu">
										<li>
											<a tabindex="-1" href="admin/company-list.jsp">企业相关</a>
										</li>
										<li>
											<a tabindex="-1" href="admin/tax.jsp">计税规则</a>
										</li>
										<li>
											<a tabindex="-1" href="admin/authorization.jsp">权限分配</a>
										</li>
									</ul>
								</li>

								<li>
									<a href="all/company-list-with-salary.jsp">汇总</a>
								</li>
								<li>
									<a href="help/index.jsp">帮助</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div id="sub-header" class="clearfix">
				<h2>
					福建电信
				</h2>
				<div class="date">
					2013年7月23日
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
								<a href="company/employee-list.jsp">员工档案</a>
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
								<a href="company/salary-list-of-customized-items.jsp">定制奖金与各种补贴</a>
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