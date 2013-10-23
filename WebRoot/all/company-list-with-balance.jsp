<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@ include file="/help/public_css_js.jsp" %>
	<script type="text/javascript">
	   $(function (){
	   
	      var year=$("input[name='year']").val();
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
		<form action="viewCompanyListWithBalance" class="select-for-year" method="post" id="myform1">
          <ul class="normal action-container clearfix">
            <li class="right">
				 日期:<input id="d11" name="year" onclick="WdatePicker()" value="${year}" class="Wdate" style="width: 110px;height: 25px;" />
					<input type="hidden" name="year" value="${year}"/>
            </li>
            <li><a href="viewCompanyListWithBalance?month=1&year=<s:property value="%{#request.year}"/>">1月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=2&year=<s:property value="%{#request.year}"/>">2月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=3&year=<s:property value="%{#request.year}"/>">3月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=4&year=<s:property value="%{#request.year}"/>">4月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=5&year=<s:property value="%{#request.year}"/>">5月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=6&year=<s:property value="%{#request.year}"/>">6月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=7&year=<s:property value="%{#request.year}"/>">7月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=8&year=<s:property value="%{#request.year}"/>">8月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=9&year=<s:property value="%{#request.year}"/>">9月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=10&year=<s:property value="%{#request.year}"/>">10月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=11&year=<s:property value="%{#request.year}"/>">11月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=12&year=<s:property value="%{#request.year}"/>">12月</a></li>
          </ul>
		</form>
          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th>序</th>
                <th>企业</th>
                <th>往来款（元）</th>
              </tr>
            </thead>
            <tbody>
             <s:iterator value="#request.pageView.records" id="balanceDetail">
	              <tr>
	                <td><s:property value="%{#balanceDetail.detailId}"/></td>
	                <td><a href="viewBalanceDetail?enterpriseId=<s:property value="%{#balanceDetail.enterpriseId}"/>"><s:property value="%{#balanceDetail.enterpriseId}"/></a></td>
	                <td><s:property value="%{#balanceDetail.balance}"/></td>
	              </tr>
              </s:iterator>
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
