<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
							<li class="active">
								<a href="toViewTaxRules">五险一金（税率）</a>
							</li>
							<li>
								<a href="viewInsurancesBaseSettings">五险一金（基数）</a>
							</li>
							<li>
								<a href="viewTaxOfPerson">个税</a>
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
									<th colspan="2">
										养老保险
									</th>
									<th colspan="2">
										失业保险
									</th>
									<th rowspan="2">
										生育（企业）
									</th>
									<th rowspan="2">
										工伤（企业）
									</th>
									<th colspan="2">
										基本医疗保险
									</th>
									<th colspan="2">
										住房公积金
									</th>
									<th rowspan="2">
										开始执行月份
									</th>
									<th rowspan="2">
										类型
									</th>
									<th rowspan="2">
										操作
									</th>
								</tr>
								<tr>
									<th>
										企业
									</th>
									<th>
										个人
									</th>
									<th>
										企业
									</th>
									<th>
										个人
									</th>
									<th>
										企业
									</th>
									<th>
										个人
									</th>
									<th>
										企业
									</th>
									<th>
										个人
									</th>
								</tr>
							</thead>
							<s:iterator value="#request.insurancesTax" var="insurancesTax">
								<tbody>
									<tr>
										<td>
											<s:property value="%{#insurancesTax.id}" />
										</td>
										<td>
											<s:property value="%{#insurancesTax.endowmentInsurance}" />
											%
										</td>
										<td>
											<s:property
												value="%{#insurancesTax.personalEndowmentInsurance}" />
											%
										</td>
										<td>
											<s:property value="%{#insurancesTax.unemploymentInsurance}" />
											%
										</td>
										<td>
											<s:property
												value="%{#insurancesTax.personalUnemploymentInsurance}" />
											%
										</td>
										<td>
											<s:property value="%{#insurancesTax.birthEnterprise}" />
											%
										</td>
										<td>
											<s:property value="%{#insurancesTax.injuriesEnterprise}" />
											%
										</td>
										<td>
											<s:property value="%{#insurancesTax.medicalEnterprise}" />
											%
										</td>
										<td>
											<s:property value="%{#insurancesTax.personalEnterprise}" />
											%

										</td>
										<td>
											<s:property value="%{#insurancesTax.housingFundEnterprise}" />
											%
										</td>
										<td>
											<s:property value="%{#insurancesTax.personalHousingFund}" />
											%
										</td>
										<td>
											<s:date name="%{#insurancesTax.startDate}" format="yyyy年MM月dd日"/>
										</td>
										<td>
											<s:if test="#insurancesTax.InsurancesType==0 ">
												<span>市医保</span>
											</s:if>
											<s:elseif test="#insurancesTax.InsurancesType==1 ">
												<span>省医保</span>
											</s:elseif>
										</td>
										<td>
											<s:set value="%{#insurancesTax.id}" var="id"></s:set>
											<a href="#info-for-check1" onclick="findInsurancesTax('${id}')" data-toggle="modal">修改</a>
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
		<!-- ====================================================addinsurancesTax============================================================= -->
		<div id="info-for-check" class="modal hide fade" tabindex="-1"	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					五险一金（税率）
				</h3>
			</div>
			<div class="modal-body">
				<div class="row-fluid">
					<s:form action="addInsurancesTax" method="post">
						<div class="row-fluid">

							<div class="input-container">
								<label>
									类型
								</label>
								<input type="radio" name="insurancesTax.insurancesType"	value="0" checked="checked">
								市医保，
								<input type="radio" name="insurancesTax.insurancesType"	value="1">
								省医保
							</div>

							<div class="input-container">
								<label>
									养老保险（公司）
								</label>
								<s:textfield name="insurancesTax.endowmentInsurance" />	%
							</div>

							<div class="input-container">
								<label>
									养老保险（个人）
								</label>
								<s:textfield name="insurancesTax.personalEndowmentInsurance" />	%
							</div>

							<div class="input-container">
								<label>
									失业保险（公司）
								</label>
								<s:textfield name="insurancesTax.unemploymentInsurance" />%
							</div>

							<div class="input-container">
								<label>
									失业保险（个人）
								</label>
								<s:textfield name="insurancesTax.personalUnemploymentInsurance" />%
							</div>

							<div class="input-container">
								<label>
									生育（企业）
								</label>
								<s:textfield name="insurancesTax.birthEnterprise" />%
							</div>
							<div class="input-container">
								<label>	工伤（企业）</label>
								<s:textfield name="insurancesTax.injuriesEnterprise" />	%
							</div>
							<div class="input-container">
								<label>
									基本医疗保险（企业）
								</label>
								<s:textfield name="insurancesTax.medicalEnterprise" />
								%
							</div>
							<div class="input-container">
								<label>
									基本医疗保险（个人）
								</label>
								<s:textfield name="insurancesTax.personalEnterprise" />
								%
							</div>
							<div class="input-container">
								<label>
									住房公积金（个人）
								</label>
								<s:textfield name="insurancesTax.personalHousingFund" />
								%
							</div>
							<div class="input-container">
								<label>
									住房公积金（公司）
								</label>
								<s:textfield name="insurancesTax.housingFundEnterprise" />
								%
							</div>
							<div class="input-container">
								<label>
									开始执行年月份
								</label>
								<s:textfield id="d11" name="startDate" cssClass="Wdate"
									onclick="WdatePicker()" />
							</div>

							<div class="input-container">
								<s:submit type="button" cssClass="btn btn-primary" value="提交" />
							</div>
						</div>
					</s:form>
				</div>
			</div>
	</div>
		<!-- =======================================update INsurancesTax================================================= -->
			<div id="info-for-check1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					五险一金（税率）
				</h3>
			</div>
			<div class="modal-body">
				<div class="row-fluid">
					<s:form action="/updateInsurancesTax.action" method="post">
						<div class="row-fluid">
							<s:hidden value="" name="insurancesTax.id"></s:hidden>
							<div class="input-container">
								<label>
									类型
								</label>
								<input type="radio" name="insurancesTax.insurancesType"	value="0">
								市医保，
								<input type="radio" name="insurancesTax.insurancesType"	value="1">
								省医保
							</div>

							<div class="input-container">
								<label>
									养老保险（公司）
								</label>
								<s:textfield name="insurancesTax.endowmentInsurance" />
								%
							</div>

							<div class="input-container">
								<label>
									养老保险（个人）
								</label>
								<s:textfield name="insurancesTax.personalEndowmentInsurance" />
								%
							</div>

							<div class="input-container">
								<label>
									失业保险（公司）
								</label>
								<s:textfield name="insurancesTax.unemploymentInsurance" />
								%
							</div>

							<div class="input-container">
								<label>
									失业保险（个人）
								</label>
								<s:textfield name="insurancesTax.personalUnemploymentInsurance" />
								%
							</div>

							<div class="input-container">
								<label>
									生育（企业）
								</label>
								<s:textfield name="insurancesTax.birthEnterprise" />
								%
							</div>
							<div class="input-container">
								<label>
									工伤（企业）
								</label>
								<s:textfield name="insurancesTax.injuriesEnterprise" />
								%
							</div>
							<div class="input-container">
								<label>
									基本医疗保险（企业）
								</label>
								<s:textfield name="insurancesTax.medicalEnterprise" />
								%
							</div>
							<div class="input-container">
								<label>
									基本医疗保险（个人）
								</label>
								<s:textfield name="insurancesTax.personalEnterprise" />
								%
							</div>
							<div class="input-container">
								<label>
									住房公积金（个人）
								</label>
								<s:textfield name="insurancesTax.personalHousingFund" />
								%
							</div>
							<div class="input-container">
								<label>
									住房公积金（公司）
								</label>
								<s:textfield name="insurancesTax.housingFundEnterprise" />
								%
							</div>
							<div class="input-container">
								<label>
									开始执行年月份
								</label>
								<s:textfield id="d11" name="insurancesTax.startDate" cssClass="Wdate" onclick="WdatePicker()" />
							</div>

							<div class="input-container">
								<s:submit type="button" cssClass="btn btn-primary" value="提交" />
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
