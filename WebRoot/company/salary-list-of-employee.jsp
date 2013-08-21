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
								<a href="employee-list.html">员工</a>
							</li>
							<li class="active">
								<a href="salary-with-month.html">工资</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li class="title">
								员工工资明细
							</li>
							<li class="right">
								<a href="#" class="btn btn-primary">下载Excel表格</a>
							</li>
						</ul>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th rowspan="2">
										月份
									</th>
									<th rowspan="2">
										姓名
									</th>
									<th rowspan="2">
										操作
									</th>
									<th rowspan="2">
										工资
									</th>
									<th rowspan="2">
										奖金
									</th>
									<th rowspan="2">
										补贴
									</th>
									<th rowspan="2">
										应发工资
									</th>
									<th rowspan="2">
										社会保险基数
									</th>
									<th colspan="2">
										养老保险
									</th>
									<th colspan="2">
										失业保险
									</th>
									<th rowspan="2">
										生育保险基数
									</th>
									<th rowspan="2">
										生育（企业）
									</th>
									<th rowspan="2">
										工伤基数
									</th>
									<th rowspan="2">
										工伤（企业）
									</th>
									<th colspan="3">
										基本医疗保险
									</th>
									<th colspan="3">
										住房公积金
									</th>
									<th rowspan="2">
										疾病统计
									</th>
									<th colspan="2">
										小计
									</th>
									<th rowspan="2">
										税前工资
									</th>
									<th colspan="2">
										个税
									</th>
									<th rowspan="2">
										服务费
									</th>
									<th rowspan="2">
										合计（企业应付）
									</th>
									<th rowspan="2">
										到卡金额
									</th>
								</tr>
								<tr>
									<td>
										企业
									</td>
									<td>
										个人
									</td>
									<td>
										企业
									</td>
									<td>
										个人
									</td>
									<td>
										缴费基数
									</td>
									<td>
										企业
									</td>
									<td>
										个人
									</td>
									<td>
										缴费基数
									</td>
									<td>
										企业
									</td>
									<td>
										个人
									</td>
									<td>
										企业
									</td>
									<td>
										个人
									</td>
									<td>
										企业
									</td>
									<td>
										个人
									</td>
								</tr>
							</thead>
							<thbody>
							<tr>
								<td>
									一月
								</td>
								<td>
									张三
								</td>
								<td>
									<a href="#info-for-check" data-toggle="modal">修改</a>
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
							</tr>
							<tr>
								<td>
									二月
								</td>
								<td>
									张三
								</td>
								<td>
									<a href="#info-for-check" data-toggle="modal">修改</a>
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
								<td>
									2000
								</td>
							</tr>
							</thbody>
						</table>
					</div>

				</div>
			</div>

			<div id="footer"></div>

		</div>
	</body>

</html>
