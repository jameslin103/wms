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
					<li>
						<a href="account/password.jsp">某某员工</a>
					</li>
					<li>
						<a href="#">退出</a>
					</li>
				</ul>

				<div class="navbar">
					<div class="navbar-inner">
						<div class="container">
							<a class="brand" href="#">富民</a>
							<ul class="nav">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										企业 <b class="caret"></b> </a>
									<ul class="dropdown-menu" role="menu"
										aria-labelledby="dropdownMenu">
										<li>
											<a tabindex="-1" href="company/list.jsp">我的企业</a>
										</li>
										<li>
											<a tabindex="-1" href="#">所有企业</a>
										</li>
									</ul>
								</li>

								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										管理 <b class="caret"></b> </a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
										<li>
											<a tabindex="-1" href="admin/company-list.jsp">企业相关</a>
										</li>
										<li>
											<a tabindex="-1" href="admin/tax.jsp">计税规则</a>
										</li>
										<li>
											<a tabindex="-1" href="admin/authorization.jsp">权限分配</a>
										</li>
									</ul>
								</li>
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										汇总 <b class="caret"></b> </a>
									<ul class="dropdown-menu" role="menu"
										aria-labelledby="dropdownMenu">
										<li>
											<a tabindex="-1" href="all/company-list-with-salary.jsp">工资预算表</a>
										</li>
										<li>
											<a tabindex="-1"
												href="all/company-list-with-insurance.jsp">增减员与参保</a>
										</li>
										<li>
											<a tabindex="-1" href="all/company-list-with-balance.jsp">资金往来</a>
										</li>
									</ul>
								</li>
								<li>
									<a href="help/index.jsp">帮助</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div id="sub-header" class="clearfix">
				<h2>
					2013年8月
				</h2>
				<div class="date">
					2013年7月23日
				</div>
			</div>
		<div id="main"> 
			<div class="row-fluid">
				<div id="center-pane">

          <ul class="nav nav-tabs">
            <li>
              <a href="all/company-list-with-salary.jsp">工资</a>
            </li>            
            <li>
              <a href="all/company-list-with-insurance.jsp">增减员与参保</a>
            </li>
            <li  class="active"><a href="all/company-list-with-balance.jsp">资金往来</a></li>
          </ul>

          <ul class="normal action-container clearfix">
            <li class="right">
              <form action="" class="select-for-year" method="post">
                <select>
                  <option value="">2014年</option>
                  <option value="" selected>2013年</option>
                  <option value="">2012年</option>
                </select>
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