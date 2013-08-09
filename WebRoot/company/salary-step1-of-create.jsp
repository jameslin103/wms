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
            <h3>1/3&nbsp;新建工资预算表信息</h3>
            <form action="salary-step2-of-create.html" method="post">
              <div class="row-fluid">
                <div class="input-container">
                  <label>名称</label>
                  <input type="text" name="">
                </div>

                <div class="input-container">
                  <label>选择模板</label>
                  <select>
                    <option value="">模板1</option>
                    <option value="" selected>模板2</option>
                    <option value="">模板3</option>
                  </select>
                </div>

                <div class="input-container">
                  <label>生成哪月工资？</label>
                  <select class="span3">
                    <option value="">2014年</option>
                    <option value="" selected>2013年</option>
                    <option value="">2012年</option>
                  </select>
                  <select class="span3">
                    <option value="">7月</option>
                    <option value="" selected>8月</option>
                    <option value="">9月</option>
                  </select> 
                </div>

                <div class="input-container">
                  <button type="submit" class="btn btn-primary">提交</button>
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
