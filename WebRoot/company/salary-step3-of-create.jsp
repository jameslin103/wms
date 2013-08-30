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
            <li><a href="company/insurance-with-month.jsp">增减员与参保明细</a></li>
          </ul>
 
          <div class="span4">
            <h3>3/3&nbsp;新建工资预算表信息</h3>
            <form action="company/salary-list.jsp" method="post">
              <div class="row-fluid">

                <div class="alert">
                  <p>名称：某某工程项目结算工资补发</p>
                  <p>哪月：2013年7月</p>
                  <p>相关数据：</p>
                  <ul>
                    <li>开票总额：10000（元）；</li>
                    <li>工资总额：6500（元）；</li>
                    <li>五险一金总额：2000（元）；</li>
                    <li>服务费总额：1500（元）；</li>
                    <li>发放人数：15（人）；</li>                 
                    <li>本工资表中，3人没有工资！分别是：张三、王五</li>
                  </ul>
                  <p><a href="company/salary-step2-of-create.jsp">重新导入数据</a></p>
                </div>
                <hr>

                <div>
                  <p>
                    <a href="company/salary-with-month.jsp" class="btn btn-primary">信息正确，确认！</a>

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
