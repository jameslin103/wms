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
            <h3>批量上传新员工信息</h3>
              <div class="row-fluid">


                <div class="alert alert-info">
                  <ol>
                    <li>
                      <a href="../doc/增员表.xls">下载增员Excel表格</a>
                    </li>
                    <li>
                      上传新增员工表
                      <form  action="employee-step2-of-create.jsp" class="form-search">
                        <input type="file"><br>
                        <button type="submit" class="btn btn-primary">上传</button>
                      </form>
                    </li>
                  </ol>
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
