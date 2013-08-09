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
   		<jsp:include page="../dashboard.jsp"></jsp:include>
    </div>

		<div id="main"> 
      <div class="row-fluid">
        <div id="center-pane">
          <ul class="nav nav-tabs">
            <li><a href="employee-list.html">员工</a></li>
            <li><a href="salary-with-month.html">工资</a></li>
          </ul>

          <div class="span4">
            <h3>核对增员信息</h3>
              <div class="row-fluid">

                <div class="alert">
                  <p>新增员工人数（人）：3人</p>
                  <p>新增员工姓名：张三、李四、王五</p>
                  <p>
                    <a href="employee-step1-of-create.html">信息错误，重新导入数据！</a>
                  </p>
                </div>
                <hr>

                <div>
                  <p>
                    <a href="employee-list.html" class="btn btn-primary">信息正确，确认！</a>
                  </p>
                </div>

              </div>
          </div>
        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>
</body>

</html>
