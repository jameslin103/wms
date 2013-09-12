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
								<a href="viewEnterpriseEmployees">员工档案</a>
							</li>
							<li>
								<a href="viewSalaryBudgetTable">工资预算表</a>
							</li>
							<li>
								<a href="viewInsuranceWithMonth">增减员与参保明细</a>
							</li>
						</ul>

						<ul class="normal action-container clearfix">
							<li>
								员工：
								<s:property value="%{#request.employees.employeesName}" />
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								查看各类明细：
							</li>
							<li>
								<a href="viewEmployeePersonalSalary?employeesId=<s:property value="%{#request.employees.employeesId}"/>">工资</a>，
							</li>
							<li>
								<a
									href="viewEmployeeContract?employeesId=<s:property value="%{#request.employees.employeesId}"/>">合同</a>，
							</li>
							<li>
								<a
									href="selectEnterpriseEmployeesWage?employeesId=<s:property value="%{#request.employees.employeesId}"/>">基本信息</a>，
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
							基本信息
						</h3>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>
										合同编号
									</th>
									<th>
										姓名
									</th>
									<th>
										性别
									</th>
									<th>
										户口
									</th>
									<th>
										婚姻
									</th>
									<th>
										照片
									</th>
									<th>
										身份证
									</th>
									<th>
										电话
									</th>
									<th>
										行业
									</th>
									<th>
										岗位
									</th>
									<th>
										服务费
										<br>
										（元）
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<s:property value="#request.employees.contractNo" />
									</td>
									<td>
										<s:property value="#request.employees.employeesName" />
									</td>
									<td>
										<s:property value="#request.employees.employeesSex" />
									</td>

									<td>
										<s:if test="#request.employees.householdRegister=='0'">
											<span>非农</span>
										</s:if>
										<s:else>
											<span>农村</span>
										</s:else>
									</td>
									<td>
										<s:if test="#request.employees.maritalStatus=='0'">
											<span>已婚</span>
										</s:if>
										<s:else>
											<span>未婚</span>
										</s:else>
									</td>
									<td>
										<s:if test="#request.employees.photo=='1'">
											<span>有</span>
										</s:if>
										<s:else>
											<span>否</span>
										</s:else>
									</td>
									<td>
										<s:property value="%{#request.employees.cardNumber}" />
									</td>
									<td>
										<s:property value="#request.employees.phone" />
									</td>
									<td>
										<s:property value="#request.employees.industry" />
									</td>
									<td>
										<s:property value="%{#request.employees.jobs}" />
									</td>
									<td>
										<s:property value="#request.employees.serviceCost" />
									</td>
								</tr>
							</tbody>
						</table>

						<h3>
							五险一金
						</h3>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th colspan="2" width="">
										参保
									</th>
									<th rowspan="2" width="">
										哪月起
										<br>
										参保
									</th>
									<th rowspan="2" width="">
										基数设置
									</th>
									<th colspan="6" class="center">
										基数（元）（未填写表示没有参保）
									</th>
									<th rowspan="2" width="">
										个税缴纳
									</th>
								</tr>
								<tr>
									<th>
										是否
									</th>
									<th>
										性质
									</th>
									<th>
										社会
										<br>
										保险
									</th>
									<th>
										生育
										<br>
										保险
									</th>
									<th>
										工伤
									</th>
									<th>
										基本
										<br>
										医疗保险
									</th>
									<th>
										住房
										<br>
										公积金
									</th>
									<th>
										大病
										<br>
										统筹
									</th>
								</tr>

							</thead>
							<tbody>
								<tr>
									<td>
										<s:if test="%{#request.employees.whetherGinseng}=='1'">
											<span>是</span>
										</s:if>
										<s:else>
											<span>否</span>
										</s:else>
									</td>
									<td>
										<s:property value="%{#request.employees.ginsengProtectNature}" />
									</td>
									<td>
										<s:date name="%{#request.employees.cinsengDate}" format="yyyy年MM月dd"/>
									</td>
									<td>
										<s:if test="%{#request.employees.base}=='0'">
											<span>默认</span>
										</s:if>
										<s:else>
											<span>个性设置</span>
										</s:else>
									</td>
									<td>
										<s:property value="%{#request.employees.socialInsurance}" />
									</td>
									<td>
										<s:property value="%{#request.employees.fertilityInsurance}" />
									</td>
									<td>
										<s:property value="%{#request.employees.inductrialBase}" />
									</td>
									<td>
										<s:property value="%{#request.employees.basicMedical}" />
									</td>
									<td>
										<s:property value="%{#request.employees.housingFund}" />
									</td>
									<td>
										<s:property value="%{#request.employees.seriousDiseaseBase}" />
									</td>
									<td>
										<s:property value="%{#request.employees.paymentWay}" />
									</td>
								</tr>
							</tbody>
						</table>

						<h3>
							银行卡
						</h3>

						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>
										开户行
									</th>
									<th>
										卡号
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<s:property value="%{#request.employees.bank}" />
									</td>
									<td>
										<s:property value="%{#request.employees.bankCardNumber}" />
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
		<div id="info-for-check" class="modal hide fade" tabindex="-1"
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

		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
	</body>

</html>
