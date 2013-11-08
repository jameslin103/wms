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
	          <s:iterator value="#session.menuList" id="menu">
			          <s:if test="#menu.url=='viewCompanyListWithSaraly'">
					        <li>
								<a href="viewCompanyListWithSaraly">工资</a>
							</li>
							</s:if>
							  <s:if test="#menu.url=='viewCompanyListWithInsurance'">
								<li> 
								  <a href="viewCompanyListWithInsurance">增减员与参保</a>
								</li>
							</s:if>
							<s:if test="#menu.url=='viewCompanyListWithBalance'">
								<li  class="active">
									<a href="viewCompanyListWithBalance">资金往来</a>
								</li>
							</s:if>
				 </s:iterator>
          </ul>
         
		<form action="viewCompanyListWithBalance" class="select-for-year" method="post" id="myform1">
          <ul class="normal action-container clearfix">
            <li class="right">
				按年份查询:
				<input type="text"  name="year" value="${year}" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'')"/>
            </li>
            <li><a href="viewCompanyListWithBalance?month=1&year=${year}">1月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=2&year=${year}">2月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=3&year=${year}">3月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=4&year=${year}">4月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=5&year=${year}">5月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=6&year=${year}">6月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=7&year=${year}">7月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=8&year=${year}">8月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=9&year=${year}">9月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=10&year=${year}">10月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=11&year=${year}">11月</a>，</li>
            <li><a href="viewCompanyListWithBalance?month=12&year=${year}">12月</a></li>
          </ul>
		</form>
          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th >序</th>
                <th>企业</th>
                <th>往来款（元）</th>
              </tr>
            </thead>
            <tbody>
             <s:iterator value="#request.balanceDetailList" id="balanceDetail" status="list">
	              <tr>
	                <td><s:property value="%{#list.index+1}"/></td>
	                <td><a href="viewBalanceDetail?enterpriseId=<s:property value="%{#balanceDetail.enterprise.enterpriseId}"/>"><s:property value="#balanceDetail.enterprise.fullName"/></a></td>
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
