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
								<a href="index.jsp">综合</a>
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

						<ul class="normal action-container clearfix">
							<li>
								员工：张三
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								查看各类明细：
							</li>
							<li>
								<a href="company/employee-personal-salary.jsp">工资</a>，
							</li>
							<li>
								<a href="viewEmployeeContract">合同</a>，
							</li>
							<li>
								<a href="selectEnterpriseEmployeesWage">基本信息</a>，
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								操作：
							</li>
							<li>
								<a href="#info-for-check" data-toggle="modal">修改</a>
							</li>
						</ul>

						<h3>
							2013年2月
						</h3>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
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

						<h3>
							2013年1月
						</h3>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
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
					员工信息
				</h3>
			</div>
			<div class="modal-body">
				<form action="" method="post">
					<div class="row-fluid">
						<div class="input-container">
							<label>
								合同编号
							</label>
							<input type="text" name="contract-number">
						</div>

						<div class="input-container">
							<label>
								姓名
							</label>
							<input type="text" name="name">
						</div>

						<div class="input-container">
							<label>
								身份证
							</label>
							<input type="text" name="shenfenzheng">
						</div>

						<div class="input-container">
							<label>
								性别
							</label>
							<input type="radio" name="sex" value="1" checked="checked">
							男，
							<input type="radio" name="sex" value="0">
							女
						</div>

						<div class="input-container">
							<label>
								户口性质
							</label>
							<input type="radio" name="type-of-residence" value="1"
								checked="checked">
							非农，
							<input type="radio" name="type-of-residence" value="0">
							农村
						</div>

						<div class="input-container">
							<label>
								是否有照片？
							</label>
							<input type="radio" name="photo" value="1" checked="checked">
							无，
							<input type="radio" name="photo" value="0">
							有
						</div>

						<div class="input-container">
							<label>
								电话
							</label>
							<input type="text" name="tel">
						</div>

						<div class="input-container">
							<label>
								家庭住址
							</label>
							<input type="text" name="address">
						</div>

						<div class="input-container">
							<label>
								银行卡号
							</label>
							<input type="text" name="cardnumber">
						</div>

						<div class="input-container">
							<label>
								开户银行
							</label>
							<select>
								<option>
									工商
								</option>
								<option>
									农行
								</option>
								<option>
									兴业
								</option>
							</select>
						</div>

						<div class="input-container">
							<label>
								籍贯
							</label>
							<input type="text" name="">
						</div>

						<div class="input-container">
							<label>
								行业
							</label>
							<select>
								<option value="">
									计算机与信息
								</option>
								<option value="">
									制造业
								</option>
								<option value="">
									财政金融
								</option>
							</select>
						</div>

						<div class="input-container">
							<label>
								岗位
							</label>
							<select>
								<option value="">
									销售
								</option>
								<option value="">
									生产
								</option>
							</select>
						</div>

						<div class="input-container">
							<label>
								婚姻状况
							</label>
							<input type="radio" name="ismarried" value="1" checked="checked">
							未婚，
							<input type="radio" name="ismarried" value="0">
							已婚
						</div>

						<div class="input-container">
							<label>
								文化程度
							</label>
							<select>
								<option value="">
									博士
								</option>
								<option value="">
									研究生
								</option>
								<option value="">
									本科
								</option>
								<option value="">
									大专
								</option>
								<option value="">
									中专
								</option>
								<option value="">
									高中
								</option>
								<option value="">
									初中
								</option>
								<option value="">
									小学
								</option>
							</select>
						</div>

						<div class="input-container">
							<label>
								合同期限
							</label>
							起：
							<select class="span2">
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
							<select class="span2">
								<option value="">
									7月
								</option>
								<option value="" selected>
									8月
								</option>
								<option value="">
									9月
								</option>
							</select>
							<select class="span2">
								<option value="">
									15日
								</option>
								<option value="" selected>
									19日
								</option>
								<option value="">
									22日
								</option>
							</select>
						</div>

						<div class="input-container">
							止：
							<select class="span2">
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
							<select class="span2">
								<option value="">
									7月
								</option>
								<option value="" selected>
									8月
								</option>
								<option value="">
									9月
								</option>
							</select>
							<select class="span2">
								<option value="">
									15日
								</option>
								<option value="" selected>
									19日
								</option>
								<option value="">
									22日
								</option>
							</select>
						</div>

						<div class="input-container">
							<label>
								是否参保?
							</label>
							<input type="radio" name="has-insurance" value="1"
								checked="checked">
							是，
							<input type="radio" name="has-insurance" value="0">
							否
						</div>

						<div class="input-container">
							<label>
								参保类型
							</label>
							<input type="checkbox" name="">
							医保，
							<input type="checkbox" name="">
							社保，
							<input type="checkbox" name="">
							公积金
						</div>

						<div class="input-container">
							<label>
								参保性质
							</label>
							<input type="radio" name="type-of-insurance" value="1"
								checked="checked">
							新增，
							<input type="radio" name="type-of-insurance" value="0">
							续保
						</div>

						<div class="input-container">
							<label>
								从哪一月开始参保？
							</label>
							<select class="span2">
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

							<select class="span2">
								<option value="">
									7月
								</option>
								<option value="" selected>
									8月
								</option>
								<option value="">
									9月
								</option>
							</select>
						</div>

						<div class="input-container">
							<label>
								参保基数
							</label>
							<input type="radio" name="base-of-insurance" value="1"
								checked="checked">
							默认基数，
							<input type="radio" name="base-of-insurance" value="0">
							个性设置
						</div>

						<div class="input-container">
							<label>
								社会保险基数
							</label>
							<input type="text" name="">
						</div>
						<div class="input-container">
							<label>
								生育保险基数
							</label>
							<input type="text" name="">
						</div>
						<div class="input-container">
							<label>
								工伤基数
							</label>
							<input type="text" name="">
						</div>
						<div class="input-container">
							<label>
								基本医疗保险基数
							</label>
							<input type="text" name="">
						</div>
						<div class="input-container">
							<label>
								住房公积金基数
							</label>
							<input type="text" name="">
						</div>

						<div class="input-container">
							<label>
								个税缴纳方式?
							</label>
							<input type="radio" name="status-of-tax" value="1"
								checked="checked">
							个人缴纳，
							<input type="radio" name="status-of-tax" value="0">
							企业缴纳
						</div>

						<div class="input-container">
							<label>
								状态?
							</label>
							<input type="checkbox" name="status-of-job" value="0">
							隐藏
						</div>

						<div class="input-container">
							<label>
								服务费
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
