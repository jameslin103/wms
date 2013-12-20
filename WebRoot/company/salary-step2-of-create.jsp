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
	    function commit(){
	        var myform=document.getElementById('myForm');
	       	myform.submit();
	        return false;
    }
    </script>
</head>
<body>

	<div id="container">
  	<div id="header">
   		 <jsp:include page="../layout/header.jsp"/>
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
					<s:iterator value="#session.menuList" id="menu">
						<s:if test="#menu.url=='viewEnterpriseEmployees'">
							<li >
								<a href="viewEnterpriseEmployees"  ><s:property value="#menu.name" />
								</a>
							</li>
						</s:if>
						<s:if test="#menu.url=='viewSalaryBudgetTable'">
							<li class="active">
								<a href="viewSalaryBudgetTable" >
									<s:property value="#menu.name" />
								</a>
							</li>
						</s:if>
						<s:if test="#menu.url=='viewInsuranceWithMonth'">
							<li >
								<a href="viewInsuranceWithMonth" ><s:property value="#menu.name" />
								</a>
							</li>
						</s:if>
						<s:if test="#menu.url=='viewBalanceDetail'">
							<li >
								<a href="viewBalanceDetail" ><s:property value="#menu.name" />
								</a>
							</li>
						</s:if>
						<s:if test="#menu.url=='viewEnterpriseDetailed'">
							<li >
								<a href="viewEnterpriseDetailed" ><s:property value="#menu.name" />
								</a>
							</li>
						</s:if>
					</s:iterator>
				</ul>
          <div class="span4">
            <h3>2/3&nbsp;新建工资预算表信息</h3>
              <div class="row-fluid">
                <div class="alert">
                  <p>名称：<s:property value="%{#request.createSalaryBudgetTable.name}"/></p>
                  <p>模板：<s:property value="%{#request.createSalaryBudgetTable.salaryTemplate.templateName}"/></p>
                  <p>哪月：<s:date name="%{#request.createSalaryBudgetTable.salaryDate}" format="yyyy年MM月" /></p>
                  <p>合并计税工资表：<s:property value="%{#request.createSalaryBudgetTable.chooseTax}"/></p>
                  <p><a href="updateSalaryBudgetTable?budgetId=<s:property value="%{#request.createSalaryBudgetTable.budgetId}"/>">返回修改</a></p>
                </div>
                <hr/>
                
                <div class="alert alert-info">
                  <ol>
                    <li>
                      <a href="downloadSalaryBudgetTable?templateId=<s:property value="%{#request.createSalaryBudgetTable.salaryTemplate.templateId}"/>">下载工资预算表</a>
                    </li>
                    <li>
                      	上传新工资预算表
                      <s:form action="uploadEmployeesSalaryDetail" cssClass="form-search" method="post" enctype="multipart/form-data">
                      	 <s:hidden value="%{#request.createSalaryBudgetTable.salaryTemplate.templateId}" name="templateId"></s:hidden>
                      	 <s:hidden value="%{#request.createSalaryBudgetTable.budgetId}" name="budgetId"/>
                      	  <s:hidden value="%{#request.createSalaryBudgetTable.salaryDate}" name="salaryDate"/>
                      	 <s:hidden name="file" value="file"></s:hidden>
                         <input type="file" name="file" id="filevalue"/><br/>
                       <s:submit  cssClass="btn btn-primary" id="uploadFile" value="上传"/>
                      </s:form>
                    </li>
                  </ol> 
                </div>
					<div>
						<s:iterator value="#request.employeesNames" id="names">
							<span style="color: red">${names}</span>
						</s:iterator>
					
					</div>
              </div>
          </div>
        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>
</body>

</html>
