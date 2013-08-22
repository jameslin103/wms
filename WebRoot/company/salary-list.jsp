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
						<form action="salary-with-month.html">

							<ul class="normal action-container clearfix">
								<li class="title">
									2013年1月工资明细
								</li>
								<li class="right">
									<a href="#" class="btn btn-primary">下载Excel表格</a>
								</li>
							</ul>

							<table class="table table-striped table-bordered">
								<thead>
									<tr>
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
											大病统筹
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
										30
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


						</form>

					</div>
				</div>
			</div>

			<div id="footer"></div>

		</div>

		<div id="info-for-check"
			class="modal hide fade modal-of-info-for-check" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					工资明细
				</h3>
			</div>

			<div class="modal-body">
				<h3>
					2013年5月工资
				</h3>
				<form action="" method="post">
					<div class="row-fluid">
						<div class="input-container">
							<p>
								姓名：张三
							</p>
						</div>

						<div class="input-container">
							<label>
								工资
							</label>
							<input type="text" name="">
						</div>

						<div class="input-container">
							<label>
								奖金
							</label>
							<input type="text" name="">
						</div>

						<div class="input-container">
							<label>
								补贴
							</label>
							<input type="text" name="">
						</div>

						<div class="input-container">
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
