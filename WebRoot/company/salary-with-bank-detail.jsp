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
        <li><a href="account/password.jsp">某某员工</a></li>
				<li><a href="#">退出</a></li>
			</ul>

			<div class="navbar">
			  <div class="navbar-inner">
			    <div class="container">
			      <a class="brand" href="#">富民</a>
						<ul class="nav">
						  <li class="dropdown">
						    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
						      企业
						      <b class="caret"></b>
						    </a>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
								  <li><a tabindex="-1" href="company/list.jsp">我的企业</a></li>
								  <li><a tabindex="-1" href="#">所有企业</a></li>
								</ul>
						  </li>

						  <li class="dropdown">
						    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
						      管理
						      <b class="caret"></b>
						    </a>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
								  <li><a tabindex="-1" href="admin/company-list.jsp">企业相关</a></li>
                	<li><a tabindex="-1" href="admin/tax.jsp">计税规则</a></li>
                  <li>
                    <a tabindex="-1" href="admin/authorization.jsp">权限分配</a>
                  </li>
								</ul>
						  </li>

						  <li><a href="all/company-list-with-salary.jsp">汇总</a></li>
              <li><a href="help/index.jsp">帮助</a></li>
						</ul>
			    </div>
			  </div>
			</div>
  	</div>
		
    <div id="sub-header" class="clearfix">
      <h2>福建电信</h2>
      <div class="date">2013年7月23日</div>
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
            <li class="right"><a href="doc/工资发放表_民生他行现金.xls" class="btn btn-primary">下载Excel表格</a></li>
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
