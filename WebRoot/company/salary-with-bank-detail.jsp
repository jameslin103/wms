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
			<jsp:include page="../layout/header.jsp"></jsp:include>
    	</div>

		<div id="main"> 
			<div class="row-fluid">
				<div id="center-pane">
         <ul class="nav nav-tabs">
            <li><a href="company/index.jsp">综合</a></li>
            <li><a href="company/employee-list.jsp">员工档案</a></li>
            <li class="active"><a href="company/salary-with-month.jsp">工资预算表</a></li>
            <li><a href="company/insurance-with-month.jsp">增减员与参保明细</a></li>
            <li><a href="company/balance-detail.jsp">资金往来</a></li>            
          </ul>
 
          <ul class="normal action-container clearfix">
            <li>分类查看：</li>
            <li><a href="#">民生银行</a>，</li>
            <li><a href="#">他行</a>，</li>
            <li><a href="#">现金</a></li>
            <li class="right"><a href="downloadBankIssueSalary" class="btn btn-primary">下载Excel表格</a></li>
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th>序</th>
                <th>姓名</th>
                <th>身份证</th>
                <th>银行名称</th>
                <th>卡号</th>
                <th>工资金额（元）</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>张三</td>
                <td>012345678912345678</td>
                <td>民生</td>
                <td>1234567890123456789012</td>
                <td>360</td>
              </tr>
              <tr>
                <td>2</td>
                <td>李四</td>
                <td>012345678912345678</td>
                <td>工商</td>
                <td>1234567890123456755555</td>
                <td>360</td>
              </tr>
              <tr>
                <td>3</td>
                <td>王五</td>
                <td>012345678912345678</td>
                <td></td>
                <td></td>
                <td>360</td>
              </tr>
            </tbody>
          </table>

          <div class="pagination">
            <ul>
              <li><a href="#">&laquo;</a></li>
              <li><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li><a href="#">6</a></li>
              <li><a href="#">7</a></li>
              <li><a href="#">8</a></li>
              <li><a href="#">&raquo;</a></li>
            </ul>
          </div>
				</div>
			</div>
		</div>

		<div id="footer"></div>
	</div>
</body>

</html>
