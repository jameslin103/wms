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
        <li><a href="account/password.jsp"><s:property value="%{#session.user.username}" /></a></li>
        <li><a href="loginOut">退出</a></li>
      </ul>
      <div class="navbar">
        <div class="navbar-inner">
          <a class="brand" href="#">富民</a>
          <ul class="nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                企业
                <b class="caret"></b>
              </a>
              <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                <li><a tabindex="-1" href="toBeResponsibleEnterprise">我的企业</a></li>
                <li><a tabindex="-1" href="#">所有企业</a></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                管理
                <b class="caret"></b>
              </a>
              <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                <li><a tabindex="-1" href="viewEnterprise">企业相关</a></li>
                <li><a tabindex="-1" href="toViewTaxRules">计税规则</a></li>
                <li><a tabindex="-1" href="company/authorization.jsp">权限分配</a></li>
              </ul>
            </li>

            <li><a href="all/company-list-with-salary.jsp">汇总</a></li>
            <li><a href="help/index.jsp">帮助</a></li>
          </ul>
        </div>        
      </div>
    </div>
    
    <div id="sub-header" class="clearfix">
      <h2>计税规则</h2>
      <div class="date">
       		<%
				java.util.Date now = new java.util.Date();
				Date currentTime = new Date();
				java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
						"yyyy年MM月dd日 ");
				String dateString = formatter.format(currentTime);
				out.println(dateString);
			%>
      </div>
    </div>

	<div id="main"> 
      <div class="row-fluid">

        <div id="center-pane" class="span9">
          <ul class="nav nav-tabs">
            <li>
              <a href="toViewTaxRules">五险一金（比率）</a>
            </li> 
            <li><a href="viewInsurancesBaseSettings">五险一金（基数）</a></li>        
            <li class="active"><a href="admin/tax-of-person.jsp">个税</a></li>        
          </ul>

          <p>由程序员协助制定，提醒，每个规则需要指定开始执行的年月份！</p>
        </div>
          
        <div id="right-pane" class="span3">
          
        </div>

      </div>
    </div>

		<div id="footer"></div>

</div>
  <div id="info-for-check" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">Modal header</h3>
    </div>
    <div class="modal-body">
      <p>One fine body…</p>
    </div>
    <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    </div>
  </div>
</body>

</html>
