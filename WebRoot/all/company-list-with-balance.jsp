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
		<script>
			function topage(page)
			{
				var form = document.getElementById("myform1");
					form.page.value=page;
					form.submit();
			}
		</script>
		
</head>
<body>

	<div id="container">
			<div id="header">
				<fieldset>
						<legend>
							<img src="images/311.gif" />&nbsp;查询条件
						</legend>
						<s:form action="viewCompanyListWithSaraly" method="post">
							年份时间:
							<s:textfield id="d4324" cssClass="Wdate" type="text" cssStyle="width:150px;" onfocus="WdatePicker()" 
                   						name="createSalaryBudgetTable.salaryDate" value="%{#request.salaryDate}"/>
                   			企业名称:<s:textfield name="enterprise.fullName"></s:textfield>	
						 	<input type="submit" value=" 查  询 " class="oprbtn" style="width:70px;" />
						 </s:form>
					</fieldset>
			</div>
		<div id="main"> 
			<div class="row-fluid">
				<div id="center-pane">
         			 <ul class="nav nav-tabs">
					     <li>
							 <a href="viewCompanyListWithSaraly">工资</a>
						  </li>
						  <li  class="active">
							<a href="viewCompanyListWithBalance">资金往来</a>
						</li>
         			 </ul>
         
		<form action="viewCompanyListWithBalance" class="select-for-year" method="post" id="myform1">
			<input type="hidden" name="page"/>
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
	</div>
</body>

</html>
