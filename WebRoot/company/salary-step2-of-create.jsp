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
            <h3>2/3&nbsp;新建工资预算表信息</h3>
              <div class="row-fluid">

                <div class="alert">
                  <p>名称：某某工程项目结算工资补发</p>
                  <p>模板：某某模板</p>
                  <p>哪月：2013年7月</p>
                  <p><a href="salary-step1-of-create.html">返回修改</a></p>
                </div>
                <hr>

                <h4>方式一</h4>
                <div class="alert alert-info">
                  <p>
                    快速导入2013年3月工资数据，生成本次工资！
                  </p>
                  <p>
                    <a href="salary-step3-of-create.html" class="btn btn-primary">
                      导入数据
                    </a>
                  </p>
                </div>

                <h4>方式二</h4>
                <div class="alert alert-info">
                  <ol>
                    <li>
                      <a href="../doc/工资预算表.xls">下载工资预算表</a>
                    </li>
                    <li>
                      上传新工资预算表
                      <form action="salary-step3-of-create.html" class="form-search">
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
