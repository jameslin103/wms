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
							<li>
								<a href="company/employee-list.jsp">员工档案</a>
							</li>
							<li>
								<a href="company/salary-with-month.jsp">工资预算表</a>
							</li>
							<li class="active">
								<a href="company/insurance-with-month.jsp">增减员与参保明细</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li class="right">
								<a href="doc/社医保办理与减员表.xls" class="btn btn-primary">下载社医保办理与减员表</a>
							</li>
							<li>
								2013年1月
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								查看：
							</li>
							<li>
								<a href="#">新增</a>，
							</li>
							<li>
								<a href="#">续保</a>，
							</li>
							<li>
								<a href="#">减员</a>
							</li>
						</ul>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th rowspan="2">
										序
									</th>
									<th rowspan="2">
										参保人
									</th>
									<th rowspan="2">
										性别
									</th>
									<th rowspan="2">
										身份证
									</th>
									<th rowspan="2">
										联系电话
									</th>
									<th rowspan="2">
										籍贯
									</th>
									<th rowspan="2">
										户口
									</th>
									<th rowspan="2">
										婚姻
									</th>
									<th rowspan="2">
										文化程度
									</th>
									<th rowspan="2">
										合同期限
									</th>
									<th rowspan="2">
										照片
									</th>
									<th colspan="3" style="text-align: center;">
										参保
									</th>
									<th rowspan="2">
										状态
									</th>
									<th rowspan="2">
										操作
									</th>
								</tr>
								<tr>
									<th>
										性质
									</th>
									<th>
										类型
									</th>
									<th>
										费用
										<br>
										（元）
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										1
									</td>
									<td>
										刘备
									</td>
									<td>
										男
									</td>
									<td>
										13958458965
									</td>
									<td>
										12856547156
									</td>
									<td>
										福建福州
									</td>
									<td>
										非农
									</td>
									<td>
										未婚
									</td>
									<td>
										研究生
									</td>
									<td>
										2013.4.18——2016.4.17
									</td>
									<td>
										有
									</td>
									<td>
										新增
									</td>
									<td>
										<ol>
											<li>
												医保
											</li>
											<li>
												社保
											</li>
											<li>
												公积金
											</li>
											<li>
												大病统筹
											</li>
										</ol>
									</td>
									<td>
										560
									</td>
									<td>
										执行中
									</td>
									<td>
										<a href="#">删除</a>
									</td>
								</tr>
							</tbody>
						</table>

						<div class="pagination">
							<ul>
								<li>
									<a href="#">&laquo;</a>
								</li>
								<li>
									<a href="#">1</a>
								</li>
								<li>
									<a href="#">2</a>
								</li>
								<li>
									<a href="#">3</a>
								</li>
								<li>
									<a href="#">4</a>
								</li>
								<li>
									<a href="#">5</a>
								</li>
								<li>
									<a href="#">6</a>
								</li>
								<li>
									<a href="#">7</a>
								</li>
								<li>
									<a href="#">8</a>
								</li>
								<li>
									<a href="#">&raquo;</a>
								</li>
							</ul>
						</div>

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
					增减员与参保
				</h3>
			</div>

			<div class="modal-body">
				<div class="row-fluid">
					<form action="" method="post">

						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<input type="radio" name="" value="1" checked="checked">
							执行中，
							<input type="radio" name="" value="0">
							已完成
						</div>

						<div class="input-container">
							<label>
								补充说明
							</label>
							<input type="text" name="">
						</div>

						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<button type="button" class="btn btn-primary">
								提交
							</button>
						</div>
					</form>
				</div>
			</div>

			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>

		<div id="info-for-check1"
			class="modal hide fade modal-of-info-for-check" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					参保人员信息
				</h3>
			</div>

			<div class="modal-body">

			</div>

			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>
	</body>

</html>
