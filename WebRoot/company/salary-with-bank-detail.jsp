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
	<%@ include file="/help/public_css_js.jsp" %>
	<script type="text/javascript">
		function topage(page){
			var form = document.getElementById("myform");
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

		<div id="main"> 
			<div class="row-fluid">
				<div id="center-pane">
         <ul class="nav nav-tabs">
            <li><a href="company/index.jsp">综合</a></li>
            <li><a href="viewEnterpriseEmployees">员工档案</a></li>
            <li class="active"><a href="viewSalaryBudgetTable">工资预算表</a></li>
            <li><a href="viewInsuranceWithMonth">增减员与参保明细</a></li>
            <li><a href="viewBalanceDetail">资金往来</a></li>            
          </ul>
 
          <ul class="normal action-container clearfix">
            <li>分类查看：</li>
            <li><a href="viewMinshengBank?budgetId=<s:property value="%{#request.budgetId}"/>">民生银行</a>，</li>
            <li><a href="viewOtherBank?budgetId=<s:property value="%{#request.budgetId}"/>">他行</a>，</li>
            <li><a href="viewCashIssue?budgetId=<s:property value="%{#request.budgetId}"/>">现金</a></li>
            <li class="right"><a href="downloadBankIssueSalary" class="btn btn-primary">下载Excel表格</a></li>
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th>序</th>
                <th>姓名</th>
                <th>身份证</th>
                <th>银行名称</th>
                <th>卡号</th>
                <th>工资金额（元）</th>
              </tr>
            </thead>
            <tbody>
            <s:iterator value="#request.pageView.records" id="employeesSalaryDetail">
              <tr>
                <td><s:property value="%{#employeesSalaryDetail.salaryId}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.employeesName}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.cardNumber}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.note}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.bankCardNumber}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.wage}"/></td>
              </tr>
              </s:iterator>
            </tbody>
          </table>

          <div class="pagination">
            <%@include file="../share/fenye.jsp" %>
          </div>
				</div>
			</div>
		</div>

		<div id="footer"></div>
	</div>
</body>

</html>
