<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
										<a tabindex="-1" href="company/company-list.jsp">企业相关</a>
									</li>
									<li>
										<a tabindex="-1" href="admin/tax-base.jsp">计税规则</a>
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
					计税规则
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
								<a href="admin/tax.jsp">五险一金（税率）</a>
							</li>
							<li class="active">
								<a href="admin/tax-base.jsp">五险一金（基数）</a>
							</li>
							<li>
								<a href="admin/tax-of-person.jsp">个税</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li>
								<a href="#info-for-check" data-toggle="modal">新建规则</a>
							</li>
						</ul>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th rowspan="2">
										序号
									</th>
									<th colspan="6" class="center">
										五险一金基数
									</th>
									<th rowspan="2">
										开始执行
										<br>
										月份
									</th>
									<th rowspan="2">
										操作
									</th>
								</tr>
								<tr>
									<th>
										社会保险（元）
									</th>
									<th>
										生育保险（元）
									</th>
									<th>
										工伤（元）
									</th>
									<th>
										基本医疗保险（元）
									</th>
									<th>
										住房公积金（元）
									</th>
									<th>
										大病统筹（元）
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										1
									</td>
									<td>
										1200
									</td>
									<td>
										2086.25
									</td>
									<td>
										2086.25
									</td>
									<td>
										2086.25
									</td>
									<td>
										2086.25
									</td>
									<td>
										60
									</td>
									<td>
										2013年7月
									</td>
									<td>
										<a href="#info-for-check" data-toggle="modal">修改</a>
									</td>
								</tr>
							</tbody>
						</table>

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
					五险一金基数设置
				</h3>
			</div>
			<div class="modal-body">
				<div class="row-fluid">
					<!-- ==================================================insurancesBaseSettings============================================== -->
					<s:form action="addInsurancesBaseSettings" method="post">
						<div class="row-fluid">
							<div class="input-container">
								<label>
									类型
								</label>
								<input type="radio" name="insurancesBaseSettings.insurancesType"
									value="1" checked="checked">
								市医保，
								<input type="radio" name="insurancesBaseSettings.insurancesType"
									value="0">
								省医保
							</div>

							<div class="input-container">
								<label>
									社会保险基数
								</label>
								<s:textfield name="insurancesBaseSettings.socialInsurance" />
								元
							</div>

							<div class="input-container">
								<label>
									生育保险基数
								</label>
								<s:textfield name="insurancesBaseSettings.birthInsurance" />
								元
							</div>

							<div class="input-container">
								<label>
									工伤基数
								</label>
								<s:textfield name="insurancesBaseSettings.inductrialInjury" />
								元
							</div>

							<div class="input-container">
								<label>
									住房公积金基数
								</label>
								<s:textfield name="insurancesBaseSettings.housingMPF" />
								元
							</div>

							<div class="input-container">
								<label>
									基本医疗基数
								</label>
								<s:textfield name="insurancesBaseSettings.basicMedical" />
								元
							</div>

							<div class="input-container">
								<label>
									大病统筹基数
								</label>
								<s:textfield name="insurancesBaseSettings.povertyStricken" />
								元
							</div>

							<div class="input-container">
								<label>
									开始执行年月份
								</label>
								<s:textfield id="d11" type="text" name="startDate" />
								<img onclick="WdatePicker({el:'d11'})"
									src="images/datePicker.gif" width="16" height="22" />
							</div>

							<div class="input-container">
								<s:submit cssClass="btn btn-primary" value="提交" />
							</div>
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
