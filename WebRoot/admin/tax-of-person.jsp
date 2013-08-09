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

        <div id="center-pane" class="span9">
          <ul class="nav nav-tabs">
            <li>
              <a href="tax.html">五险一金</a>
            </li> 
            <li class="active"><a href="tax-of-person.html">个税</a></li>        
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
