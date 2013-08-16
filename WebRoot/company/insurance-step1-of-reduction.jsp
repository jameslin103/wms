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
    		<jsp:include page="../dashboard.jsp"></jsp:include>
    	</div>
	<div id="main"> 
    		 <div class="row-fluid">
        <div id="center-pane">
          <ul class="nav nav-tabs">
            <li><a href="index.html">综合</a></li>
            <li><a href="employee-list.html">员工档案</a></li>
            <li><a href="salary-with-month.html">工资预算表</a></li>
            <li class="active"><a href="insurance-with-month.html">增减员与参保明细</a></li>
          </ul>

          <div class="span4">
            <h3>批量减员</h3>
              <div class="row-fluid">


                <div class="alert alert-info">
                  <ol>
                    <li>
                      <a href="">下载Excel表格</a>
                    </li>
                    <li>
                      上传减员信息表
                      <form  action="insurance-step2-of-reduction.html" class="form-search" method="post">
                        <input type="file"><br>
                        <button type="submit" class="btn btn-primary">上传</button>
                      </form>
                    </li>
                  </ol>
                  <p class="red">备注：上传时，所有姓名，必须与数据库中已有的员工档案一致，重名用身份证号识别，如果无法识别，给予提醒！</p>
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
