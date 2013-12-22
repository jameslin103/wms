<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />

		<title>富民人力银行派遣系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@ include file="/help/public_css_js.jsp"%>
		<script>
			function topage(page)
			{
				var form = document.getElementById("myformlist");
					form.page.value=page;
					form.submit();
			}
		
		</script>
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
		          <s:iterator value="#session.menuList" id="menu">
		         		 <s:if test="#menu.url=='viewCompanyListWithSaraly'">
				            <li  class="active">
							  	<a href="viewCompanyListWithSaraly">工资</a>
							</li>
						</s:if>
						  <s:if test="#menu.url=='viewCompanyListWithInsurance'">
							<li> 
							  <a href="viewCompanyListWithInsurance">增减员与参保</a>
							</li>
						</s:if>
						<s:if test="#menu.url=='viewCompanyListWithBalance'">
							<li>
								<a href="viewCompanyListWithBalance">资金往来</a>	
							</li>
						</s:if>
					 </s:iterator>
          </ul>
						
						
						<ul class="normal action-container clearfix">
							<li class="right">
							<form action="viewCompanyListWithSaraly" class="select-for-year" method="post" id="myform1">
									按年份查询:<input type="text" name="year"  value="${year}"  maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')"/>
									
							</form>	
							</li>
								<li><a href="viewCompanyListWithSaraly?month=1&year=${year}">1月</a>，</li>
					            <li><a href="viewCompanyListWithSaraly?month=2&year=${year}">2月</a>，</li>
					            <li><a href="viewCompanyListWithSaraly?month=3&year=${year}">3月</a>，</li>
					            <li><a href="viewCompanyListWithSaraly?month=4&year=${year}">4月</a>，</li>
					            <li><a href="viewCompanyListWithSaraly?month=5&year=${year}">5月</a>，</li>
					            <li><a href="viewCompanyListWithSaraly?month=6&year=${year}">6月</a>，</li>
					            <li><a href="viewCompanyListWithSaraly?month=7&year=${year}">7月</a>，</li>
					            <li><a href="viewCompanyListWithSaraly?month=8&year=${year}">8月</a>，</li>
					            <li><a href="viewCompanyListWithSaraly?month=9&year=${year}">9月</a>，</li>
					            <li><a href="viewCompanyListWithSaraly?month=10&year=${year}">10月</a>，</li>
					            <li><a href="viewCompanyListWithSaraly?month=11&year=${year}">11月</a>，</li>
					            <li><a href="viewCompanyListWithSaraly?month=12&year=${year}">12月</a></li>
						</ul>
						<s:form action="viewCompanyListWithSaraly"  method="post" id="myformlist">
							<s:hidden name="page"></s:hidden>
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th rowspan="2">
										序
									</th>
									<th rowspan="2">
										企业
									</th>
									<th rowspan="2">
										工资预算表
										<br/>
										名称
									</th>
									<th rowspan="2" style="text-align: center">
										工资所属月份
									</th>
									<th colspan="4" style="text-align: center">
										工资预算表汇总信息
									</th>
									<th colspan="4" style="text-align: center">
										发放明细
									</th>
									<th colspan="1" style="text-align: center">
										状态
									</th>
								</tr>
								<th>
									开票
									<br/>
									总额（元）
								</th>
								<th>
									工资
									<br/>
									总额（元）
								</th>
								<th>
									服务费
									<br/>
									总额（元）
								</th>
								<th>
									五险一金
									<br/>
									总额（元）
								</th>
								<th>
									发放
									<br/>
									人数（人）
								</th>
								<th>
									民生
									<br/>
									银行（人）
								</th>
								<th>
									他行
									<br/>
									（人）
								</th>
								<th>
									现金（人）
								</th>
								<th>
									（制作、审核、实际发放）
								</th>
							</thead>
						<s:iterator value="#request.pageView.records" id="createSalaryBudgetTable" status="list">
							<tbody>
								<tr>
									<td>
										<s:property value="%{#list.index+1}"/>
									</td>
									<td>
										<s:if test="#createSalaryBudgetTable.enterprise.fullName.length()>10">
											<s:property value="#createSalaryBudgetTable.enterprise.fullName.substring(0,10)+'...'"/>
										</s:if>
										<s:else>
											<s:property value="#createSalaryBudgetTable.enterprise.fullName"/>
										</s:else>
									</td>
									<td>
										<a href="viewAllEmployeesSalaryDetail?enterpriseId=<s:property value="%{#createSalaryBudgetTable.enterprise.enterpriseId}"/>
													&budgetId=<s:property value="%{#createSalaryBudgetTable.budgetId}"/>">
										<s:if test="#createSalaryBudgetTable.name.length()>10">
											<s:property value="#createSalaryBudgetTable.name.substring(0,10)+'...'"/>
										</s:if>
										<s:else>
											<s:property value="%{#createSalaryBudgetTable.name}"/>
										</s:else>
										
										</a>
									</td>
									<td>
										<s:date name="%{#createSalaryBudgetTable.salaryDate}" format="yyyy年MM月"/>
									</td>
									<td>
										<s:property value="%{#createSalaryBudgetTable.makeTotal}"/>
									</td>
									<td>
										<s:property value="%{#createSalaryBudgetTable.wageTotal}"/>
									</td>
									<td>
										<s:property value="%{#createSalaryBudgetTable.serviceTotal}"/>
									</td>
									<td>
										<s:property value="%{#createSalaryBudgetTable.fiveInsurancesTotal}"/>
									</td>
									<td>
										<s:property value="%{#createSalaryBudgetTable.issueNumber}"/>
										<br/>
										<a href="viewSalaryWithBankPersonalNumber?enterpriseId=<s:property value="%{#createSalaryBudgetTable.enterprise.enterpriseId}"/>&budgetId=<s:property value="%{#createSalaryBudgetTable.budgetId}"/>">查看</a>
									</td>
									<td>
										<s:property value="%{#request.createSalaryBudgetTable.cmbc}"/>
										<br/>
										<s:if test="#createSalaryBudgetTable.cmbcDate!=null">
											<span class="em">（<s:property value="%{#request.createSalaryBudgetTable.status}"/>）</span>
											<br/>
											<s:date name="%{#createSalaryBudgetTable.cmbcDate}" format="yyyy年MM月dd日HH时"/>
										</s:if>
									</td>
									<td>
										<s:property value="%{#request.createSalaryBudgetTable.heLines}"/>
										<br/>
										<s:if test="#createSalaryBudgetTable.heLinesDate!=null">
											<span class="em">（<s:property value="%{#request.createSalaryBudgetTable.status}"/>）</span>
											<br/>
											<s:date name="%{#createSalaryBudgetTable.heLinesDate}" format="yyyy年MM月dd日HH时"/>
										</s:if>
										
									</td>
									<td>
										<s:property value="%{#request.createSalaryBudgetTable.cashnumber}"/>
										<br/>
										<s:if test="#createSalaryBudgetTable.cashnumberDate!=null">
											<span class="em">（<s:property value="%{#request.createSalaryBudgetTable.status}"/>）</span>
											<br/>
											<s:date name="%{#createSalaryBudgetTable.cashnumberDate}" format="yyyy年MM月dd日HH时"/>
										</s:if>
									</td>
									<td>
										<ul>
											<li>
												制作：<s:property value="#createSalaryBudgetTable.user.username"/>，
												<s:date name="%{#createSalaryBudgetTable.createDate}" format="yyyy-MM-dd,HH:ss"/>
											</li>
											<li>
												发放：<s:property value="%{#createSalaryBudgetTable.user_operator}"/>
												<s:set value="%{#createSalaryBudgetTable.budgetId}" id="budgetId"/>
												<a href="#info-for-check2" data-toggle="modal" onclick="findSalaryTable('${budgetId}')">操作</a>
											</li>
										</ul>
									</td>
								</tr>
								
							</tbody>
							</s:iterator>
							
						</table>

						<div class="pagination">
							<%@include file="../share/fenye.jsp"  %>
						</div>
					</s:form>
					</div>
				</div>
			</div>

			<div id="footer"></div>
		</div>

		<div id="info-for-check1" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					审核
				</h3>
			</div>

			<div class="modal-body">
				<div class="row-fluid">
					<form action="" method="post">
						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<input type="radio" name="start" value="1" checked="checked"/>
							通过，
							<input type="radio" name="start" value="0"/>
							不通过
						</div>

						<div class="input-container">
							<label>
								补充说明
							</label>
							<input type="text" name=""/>
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

		<div id="info-for-check2" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					发放情况
				</h3>
			</div>

			<div class="modal-body">
				<div class="row-fluid">
					<form action="updateSaralyStatus" method="post">
					   <input type="hidden" name="budgetId"/>
					   <input type="hidden" name="page" value="${page}"/>
						<div class="input-container">
							<label>
								民生银行
							</label>
							<input type="text" id="d4311"
								name="createSalaryBudgetTable.cmbcDate"
								 onfocus="WdatePicker(d4311)" class="Wdate" />
						</div>
						<div class="input-container">
							<label>
								他行
							</label>
							<input type="text" id="d4312"
								name="createSalaryBudgetTable.heLinesDate"
								 onfocus="WdatePicker(d4312)" class="Wdate" />
						</div>
						
						<div class="input-container">
							<label>
								现金
							</label>
							<input type="text" id="d4313"
								name="createSalaryBudgetTable.cashnumberDate"
								 onfocus="WdatePicker(d4313)" class="Wdate" />
						</div>
						<div class="input-container">
							<label>
								备注:
							</label>
							<input name="createSalaryBudgetTable.status" type="text"/>
						</div>
						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<s:submit value="提交" cssClass="btn btn-primary"/>
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
	</body>

</html>
