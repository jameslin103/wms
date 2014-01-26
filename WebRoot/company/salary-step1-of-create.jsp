<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="/help/public_css_js.jsp"%>

	</head>
	<body>
		<div id="container">
			<div id="header">
				<div id="sub-header" class="clearfix">
					<h2>
						<s:property value="%{#session.enterprise.fullName}" />
					</h2>
				</div>
			</div>
	
			<div id="main"> 
		      <div class="row-fluid">
		        <div id="center-pane">
		         		<ul class="nav nav-tabs">
									<li >
										<a href="viewEnterpriseDetailed" >
											综合 
										</a>
									</li>
									<li >
										<a href="viewEnterpriseEmployees">
											员工档案
										</a>
									</li>
									<li class="active">
										<a href="viewSalaryBudgetTableSummary" >
											工资预算表
										</a>
									</li>
									<li>
										<a href="viewInsuranceWithMonth" ></a>
									</li>
									<li >
										<a href="viewBalanceDetail" >
											资金往来
										</a>
									</li>
									
						</ul>
             <s:form action="addSalaryBudgetTable" method="post">
            	<s:hidden name="enterpriseId" value="%{#request.session.enterprise.enterpriseId}"></s:hidden>
            	<s:hidden value="%{#request.createSalaryBudgetTable.budgetId}" name="createSalaryBudgetTable.budgetId"/>
            	<table width="700px;" align="center">
            		<tr><td colspan="4" align="center"><h2>1/3&nbsp;新建工资预算表信息</h2></td></tr>
            		<tr>
            			<td>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</td>
            			<td> 
            				<s:textfield name="createSalaryBudgetTable.name" id="budgetName" maxlength="30" 
            					value="%{#request.createSalaryBudgetTable.name}"/>
            					 <span style="color:red;">*</span>
                  				<span id="nameerror"></span>
            			</td>
            			<td>选择模板</td>
            			<td>
            				<s:select  headerValue="--请选择--" headerKey="0" 
		            			 list="%{#request.salaryTemplates}" name="templateId" 
		            			 listKey="templateId" listValue="templateName" id="tempid">
             	  			 </s:select>
             	  			 <span style="color:red;">*</span><span id="temp"></span>
            			</td>
            		</tr> 
            		<tr>
            			<td>生成哪月工资？</td>
            			<td colspan="3">
            				<input type="text" id="d11"	name="salaryDate"  
			                 onclick="WdatePicker()" value="${createSalaryBudgetTable.salaryDate}" class="Wdate"  />
			                 <span style="color:red;">*</span><span id="salaryDate"></span>
            			</td>
            			
            		</tr>
            		<tr>
            			<td>补充说明</td>
            			<td colspan="3">
            				<textarea rows="3" name="createSalaryBudgetTable.note" cols="60" >
                  				<s:property value="%{#createSalaryBudgetTable.note}"/>
                  			</textarea>
                  		</td>
            		</tr>
            		<tr>
            			<td colspan="4" align="center" height="50px;">
            				<s:submit value="提交" cssClass="btn btn-primary"  id="sub" onclick="submin()"/>
            			</td>
            		</tr>
            	</table>
               
              <s:token></s:token>
            </s:form>   
          </div>
        </div>
      </div>
    </div>
</body>

</html>
