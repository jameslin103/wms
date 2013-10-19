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
				<jsp:include page="../layout/list_header.jsp" />
			</div>
		<div id="main"> 
			<div class="row-fluid">
				<div id="center-pane">

          <ul class="nav nav-tabs">
            <li>
			  <a href="viewCompanyListWithSaraly">工资</a>
			</li>
			<li > 
			  <a href="viewCompanyListWithInsurance">增减员与参保</a>
			</li>
			<li class="active">
				<a href="viewCompanyListWithBalance">资金往来</a>
			</li>
          </ul>

          <ul class="normal action-container clearfix">
            <li class="right">
			<form action="" class="select-for-year" method="post">
				 日期:<input id="d11" name="year" onclick="WdatePicker()"  class="Wdate" style="width: 110px;height: 25px;" />
			</form>

            </li>
            <li><a href="#">1月</a>，</li>
            <li><a href="#">2月</a>，</li>
            <li><a href="#">3月</a>，</li>
            <li><a href="#">4月</a>，</li>
            <li><a href="#">5月</a>，</li>
            <li><a href="#">6月</a>，</li>
            <li><a href="#">7月</a>，</li>
            <li><a href="#">8月</a>，</li>
            <li><a href="#">9月</a>，</li>
            <li><a href="#">10月</a>，</li>
            <li><a href="#">11月</a>，</li>
            <li><a href="#">12月</a></li>
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th>序</th>
                <th>企业</th>
                <th>往来款（元）</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td><a href="company/balance-detail.jsp">福建电信</a></td>
                <td>360</td>
              </tr>
              <tr>
                <td>2</td>
                <td><a href="company/balance-detail.jsp">中国英航</a></td>
                <td>-250</td>
              </tr>
            </tbody>
          </table>

          <div class="pagination">
           <%@include file="../share/fenye.jsp" %>
          </div>
				</div>
			</div>
		</div>

		<div id="footer"></div>
	</div>

<!--   <div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">增减员与参保</h3>
    </div>

    <div class="modal-body">
      <div class="row-fluid">

      </div>
    </div>

    <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    </div>
  </div> -->
</body>

</html>
