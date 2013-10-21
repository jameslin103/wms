<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>富民人力银行派遣系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="/help/public_css_js.jsp"%>
		<script type="text/javascript">
	   $(function (){
	      var year=$("input[name='year']").val();
	      alert(year);
	   	  $("input[name='year']").blur(function(){
	   	 	  
	   	  	 	if(year!="" && year!=undefined)
	   	  	 	{
	   	  	 			
	   	  			$("#myform1").submit(); 
					
	   	         }
	   	  });
	   	   
	   });
	
	
	</script>
	</head>
	<body>
		<div id="container">
		<div id="header">
			<div id="header">
				<jsp:include page="../layout/list_header.jsp" />
			</div>
		</div>
		<div id="main">
				<div class="row-fluid">
					<div id="center-pane">

						<ul class="nav nav-tabs">
							<li >
								<a href="viewCompanyListWithSaraly">工资</a>
							</li>
							<li class="active">
								<a href="viewCompanyListWithInsurance">增减员与参保</a>
							</li>
							<li>
								<a href="viewCompanyListWithBalance">资金往来</a>
							</li>
						</ul>
						<form action="viewCompanyListWithInsurance" class="select-for-year" method="post" id="myform1">
						<ul class="normal action-container clearfix">
							<li class="right">
								
									 日期:<input id="d11" name="year" onclick="WdatePicker()" value="${year}" class="Wdate" style="width: 110px;height: 25px;" />
									<input type="hidden" name="year" value="${year}"/>
							
							</li>
							    <li><a href="viewCompanyListWithInsurance?month=1&year=<s:property value="%{#request.year}"/>">1月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=2&year=<s:property value="%{#request.year}"/>">2月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=3&year=<s:property value="%{#request.year}"/>">3月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=4&year=<s:property value="%{#request.year}"/>">4月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=5&year=<s:property value="%{#request.year}"/>">5月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=6&year=<s:property value="%{#request.year}"/>">6月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=7&year=<s:property value="%{#request.year}"/>">7月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=8&year=<s:property value="%{#request.year}"/>">8月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=9&year=<s:property value="%{#request.year}"/>">9月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=10&year=<s:property value="%{#request.year}"/>">10月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=11&year=<s:property value="%{#request.year}"/>">11月</a>，</li>
					            <li><a href="viewCompanyListWithInsurance?month=12&year=<s:property value="%{#request.year}"/>">12月</a></li>
						</ul>
						</form>
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>
										序
									</th>
									<th>
										企业
									</th>
									<th>
										所属月份
									</th>
									<th>
										详细
									</th>
									<th>
										状态
									</th>
									<th>
										补充说明
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										1
									</td>
									<td>
										枫叶科技公司
									</td>
									<td>
										2013年9月
									</td>
									<td>
										<a href="insuranceWithEmployeeList">新增员工3人，减员2人，参保3人
										</a>
									</td>
									<td>
										已完成
									</td>
									<td>
										---
									</td>
									<td>
										<a href="#info-for-check" data-toggle="modal">修改</a>
									</td>
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

		<div id="info-for-check" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					增减员与参保
				</h3>
			</div>

			<div class="modal-body">
				<div class="row-fluid">
					<form action="" method="post">

						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<input type="radio" name="iscompleted" value="0"
								checked="checked">
							未执行，
							<input type="radio" name="iscompleted" value="1">
							执行中，
							<input type="radio" name="iscompleted" value="2">
							已完成
						</div>

						<hr>

						<div class="input-container">
							<label>
								补充说明
							</label>
							<input type="text" name="">
						</div>

						<hr>

						<div class="input-container">
							<button type="button" class="btn btn-primary">
								提交
							</button>
						</div>
					</form>
				</div>
			</div>

			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>
	</body>

</html>
