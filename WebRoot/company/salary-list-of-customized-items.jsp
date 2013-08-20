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
				<ul class="user  normal clearfix">
					<li>
						<a href="account/password.jsp">某某员工</a>
					</li>
					<li>
						<a href="#">退出</a>
					</li>
				</ul>
				<div class="navbar">
					<div class="navbar-inner">
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
								<tr>
									<td>
										1
									</td>
									<td>
										奖金
									</td>
									<td>
										启用
									</td>
									<td>
										<a href="#info-for-check" data-toggle="modal">修改</a>
									</td>
								</tr>
								<tr>
									<td>
										2
									</td>
									<td>
										--
									</td>
									<td>
										--
									</td>
									<td>
										--
									</td>
								</tr>
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
				<form action="" method="post">
					<div class="row-fluid">
						<div class="input-container">
							<label>
								名称
							</label>
							<input type="text" name="">
						</div>
						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<input type="radio" name="start" value="1" checked="checked">
							启用，
							<input type="radio" name="start" value="0">
							停用
						</div>

						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<button type="button" class="btn btn-primary">
								提交
							</button>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>
	</body>

</html>
