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
      <ul class="user normal clearfix">
        <li><a href="account/password.jsp">某某员工</a></li>
        <li><a href="#">退出</a></li>
      </ul>

     <div class="navbar">
        <div class="navbar-inner">
          <div class="container">
            <a class="brand" href="#">富民</a>
            <ul class="nav">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  企业
                  <b class="caret"></b>
                </a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                  <li><a tabindex="-1" href="list.jsp">我的企业</a></li>
                  <li><a tabindex="-1" href="#">所有企业</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  管理
                  <b class="caret"></b>
                </a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                  <li><a tabindex="-1" href="admin/company-list.jsp">企业相关</a></li>
                  <li><a tabindex="-1" href="admin/tax.jsp">计税规则</a></li>
                  <li><a tabindex="-1" href="admin/authorization.jsp">权限分配</a></li>                  
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  汇总
                  <b class="caret"></b>
                </a>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                  <li><a tabindex="-1" href="all/company-list-with-salary.jsp">工资预算表</a></li>
                  <li><a tabindex="-1" href="all/company-list-with-insurance.jsp">增减员与参保</a></li>
                  <li><a tabindex="-1" href="all/company-list-with-balance.jsp">资金往来</a></li>                  
                </ul>
              </li>
              <li><a href="help/index.jsp">帮助</a></li>
            </ul>
          </div>
        </div>
     </div>

    </div>
    
    <div id="sub-header" class="clearfix">
      <h2>福建电信</h2>
      <div class="date">
        2013年7月23日
      </div>
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
