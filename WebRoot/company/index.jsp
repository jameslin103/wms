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
    		<jsp:include page="../layout/header.jsp"></jsp:include>
    	</div>
		<div id="main"> 
      <div class="row-fluid">
        <div id="center-pane">
          <ul class="nav nav-tabs">
            <li class="active"><a href="company/index.jsp">综合</a></li>
            <li><a href="viewEnterpriseEmployees">员工档案</a></li>
            <li><a href="company/salary-with-month.jsp">工资预算表</a></li>
            <li><a href="company/insurance-with-month.jsp">增减员与参保明细</a></li>
            <li><a href="company/balance-detail.jsp">资金往来</a></li>            
          </ul>   
          
        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>
</body>
</html>
