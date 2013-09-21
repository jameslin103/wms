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
								<a href="toViewTaxRules">五险一金（税率）</a>
							</li>
							<li class="active">
								<a href="viewInsurancesBaseSettings">五险一金（基数）</a>
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
							<s:iterator value="#request.insurancesBase" var="base">
								<tbody>
									<tr>
										<td>
											<s:property value="%{#base.id}" />
										</td>
										<td>
											<s:property value="%{#base.socialInsurance}" />
										</td>
										<td>
											<s:property value="%{#base.birthInsurance}" />
										</td>
										<td>
											<s:property value="%{#base.inductrialInjury}" />
										</td>
										<td>
											<s:property value="%{#base.basicMedical}" />
										</td>
										<td>
											<s:property value="%{#base.housingMPF}" />
										</td>
										<td>
											<s:property value="%{#base.povertyStricken}" />
										</td>
										<td>
											<s:date name="%{#base.startDate}" format="yyyy年MM月dd日"/>
										</td>
										<td>
											<s:set value="%{#base.id}" var="baseId"></s:set>
											<a href="#info-for-check1" onclick="findIdToBaseTax('${baseId}')" data-toggle="modal">修改</a>
										</td>
									</tr>
								</tbody>
							</s:iterator>
						</table>

					</div>
				</div>
			</div>

			<div id="footer"></div>

		</div>
			<!-- ==================================================add insurancesBaseSettings============================================== -->
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
								<s:textfield name="insurancesBaseSettings.birthInsurance"/>
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
								<s:textfield id="d11" type="text" name="startDate"	onclick="WdatePicker()" cssClass="Wdate" />
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
		<!-- ==================================================update insurancesBaseSettings============================================== -->
		<div id="info-for-check1" class="modal hide fade" tabindex="-1"
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
					<s:form action="updateInsurancesBaseSettings" method="post">
						<s:hidden name="insurancesBaseSettings.id" value=""></s:hidden>
						<div class="row-fluid">
							<div class="input-container">
								<label>
									类型
								</label>
								<input type="radio" name="insurancesBaseSettings.insurancesType" value="1">
								市医保，
								<input type="radio" name="insurancesBaseSettings.insurancesType" value="0">
								省医保
							</div>

							<div class="input-container">
								<label>
									社会保险基数
								</label>
								<s:textfield name="insurancesBaseSettings.socialInsurance" value=""/>
								元
							</div>

							<div class="input-container">
								<label>
									生育保险基数
								</label>
								<s:textfield name="insurancesBaseSettings.birthInsurance" value=""/>
								元
							</div>

							<div class="input-container">
								<label>
									工伤基数
								</label>
								<s:textfield name="insurancesBaseSettings.inductrialInjury" value=""/>
								元
							</div>

							<div class="input-container">
								<label>
									住房公积金基数
								</label>
								<s:textfield name="insurancesBaseSettings.housingMPF" value=""/>
								元
							</div>

							<div class="input-container">
								<label>
									基本医疗基数
								</label>
								<s:textfield name="insurancesBaseSettings.basicMedical" value=""/>
								元
							</div>

							<div class="input-container">
								<label>
									大病统筹基数
								</label>
								<s:textfield name="insurancesBaseSettings.povertyStricken" value=""/>
								元
							</div>

							<div class="input-container">
								<label>
									开始执行年月份
								</label>
								<s:textfield id="d11" type="text" name="insurancesBaseSettings.startDate"	onclick="WdatePicker()" cssClass="Wdate" />
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
