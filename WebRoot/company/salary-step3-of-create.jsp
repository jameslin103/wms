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

</head>
<body>

	<div id="container">
   		<div id="header">
    		<jsp:include page="../layout/header.jsp"/>
  		</div>

	<div id="main"> 
      <div class="row-fluid">
        <div id="center-pane">
          <ul class="nav nav-tabs">
            <li><a href="index.html">综合</a></li>
            <li><a href="viewEnterpriseEmployees">员工档案</a></li>
            <li class="active"><a href="viewSalaryBudgetTable">工资预算表</a></li>
            <li><a href="viewInsuranceWithMonth">增减员与参保明细</a></li>
          </ul>
 
          <div class="span4">
            <h3>3/3&nbsp;新建工资预算表信息</h3>
            <form action="viewSalaryBudgetTable" method="post">
              <div class="row-fluid">

                <div class="alert">
                  <p>名称：<s:property value="%{#request.createSalaryBudgetTable.name}"/></p>
                  <p>哪月：<s:date name="%{#request.createSalaryBudgetTable.salaryDate}" format="yyyy年MM月"/></p>
                  <p>相关数据：</p>
                  <ul>
                    <li>开票总额：<s:property value="%{#request.createSalaryBudgetTable.makeTotal}"/>（元）；</li>
                    <li>工资总额：<s:property value="%{#request.createSalaryBudgetTable.wageTotal}"/>（元）；</li>
                    <li>五险一金总额：<s:property value="%{#request.createSalaryBudgetTable.fiveInsurancesTotal}"/>（元）；</li>
                    <li>服务费总额：<s:property value="%{#request.createSalaryBudgetTable.serviceTotal}"/>（元）；</li>
                    <li>发放人数：<s:property value="%{#request.createSalaryBudgetTable.issueNumber}"/>（人）；</li>                 
                    <li>本工资表中，3人没有工资！分别是：张三、王五</li>
                  </ul>
                  <p><a href="toImportSalaryData">重新导入数据</a></p>
                </div>
                <hr>

                <div>
                	<s:token></s:token>
                  <p>
                  	<s:submit value="信息正确，确认！" cssClass="btn btn-primary"></s:submit>
                  </p>
                </div>

              </div>
            </form>   
          </div>
        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>
</body>

</html>
