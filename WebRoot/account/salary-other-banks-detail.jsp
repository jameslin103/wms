<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache"/> 
		<meta http-equiv="cache-control" content="no-cache"/> 
		<meta http-equiv="expires" content="0"/>  
	    <%@ include file="/help/public_css_js.jsp" %>
	<script type="text/javascript">
		function topage(page){
			var form = document.getElementById("viewOtherBank");
			form.page.value=page;
			form.submit();
		}
	</script>
		
</head>
<body>

	<div id="container">
		<div id="header">
			<jsp:include page="../layout/header.jsp"></jsp:include>
    	</div>
    	<div id="sub-header" class="clearfix">
			<h2>
				<s:property value="%{#session.enterprise.fullName}" />
			</h2>
		</div>
		<div id="main"> 
			<div class="row-fluid">
			<div id="center-pane">
         			<ul class="nav nav-tabs">
							<s:iterator value="#session.menuList" id="menu">
								<s:if test="#menu.url=='viewEnterpriseDetailed'">
									<li >
										<a href="viewEnterpriseDetailed"><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
								<s:if test="#menu.url=='viewEnterpriseEmployees'">
									<li >
										<a href="viewEnterpriseEmployees"  ><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
								<s:if test="#menu.url=='viewSalaryBudgetTable'">
									<li class="active">
										<a href="viewSalaryBudgetTable" >
											<s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
								<s:if test="#menu.url=='viewInsuranceWithMonth'">
									<li >
										<a href="viewInsuranceWithMonth" ><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
								<s:if test="#menu.url=='viewBalanceDetail'">
									<li >
										<a href="viewBalanceDetail" ><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
							</s:iterator>
					</ul>
 
          <ul class="normal action-container clearfix">
            <li>分类查看：</li>
            <li><a href="viewMinshengBank?budgetId=<s:property value="%{#request.budgetId}"/>">民生银行</a>，</li>
            <li><a href="viewOtherBank?budgetId=<s:property value="%{#request.budgetId}"/>">他行</a>，</li>
            <li><a href="viewCashIssue?budgetId=<s:property value="%{#request.budgetId}"/>">现金</a></li>
            <li class="right"><a href="downloadBankIssueSalary?budgetId=<s:property value="%{#request.budgetId}"/>" class="btn btn-primary">下载Excel表格</a></li>
          </ul>
		  <s:form action="viewOtherBank" method="post" id="viewOtherBank">
		  	 <input type="hidden" name="page"/>
		  	 <input type="hidden" name="budgetId" value="${budgetId}"/>
          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th style="text-align: center;">序</th>
                <th style="text-align: center;">姓名</th>
                <th style="text-align: center;">身份证</th>
                <th style="text-align: center;">银行名称</th>
                <th style="text-align: center;">卡号</th>
                <th style="text-align: center;">工资金额（元）</th>
              </tr>
            </thead>
            <tbody>
            <s:iterator value="#request.pageView.records" id="employeesSalaryDetail" status="list">
              <tr>
                <td style="text-align: center;"><s:property value="%{#list.index+1}"/></td>
                <td style="text-align: center;"><s:property value="%{#employeesSalaryDetail.employeesName}"/></td>
                <td style="text-align: center;"><s:property value="%{#employeesSalaryDetail.cardNumber}"/></td>
                <td style="text-align: center;"><s:property value="%{#employeesSalaryDetail.bank}"/></td>
                <td style="text-align: center;"><s:property value="%{#employeesSalaryDetail.bankCardNumber}"/></td>
                <td style="text-align: center;"><s:property value="%{#employeesSalaryDetail.wage}"/></td>
              </tr>
              </s:iterator>
            </tbody>
          </table>
          	<div class="pagination">
				<%@ include file="../share/fenye.jsp" %>
			</div>
		  </s:form>
		</div>
		</div>
				<!--<s:hidden name="budgetId" value="%{#request.budgetId}"></s:hidden>
		  		<table id="flexigrid" style="display: none;"></table>
				
		--></div>
		<div id="footer"></div>
	</div>
</body>

</html>
