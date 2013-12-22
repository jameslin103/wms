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
				<jsp:include page="../layout/header.jsp" />
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
								<s:if test="#menu.url=='viewEnterpriseDetailed'">
									<li >
										<a href="viewEnterpriseDetailed"  ><s:property value="#menu.name" />
										</a>
									</li>
								</s:if>
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
							</s:iterator>
					</ul>
			<div class="span4">
            <h3>1/3&nbsp;新建工资预算表信息</h3>
            <s:form action="addSalaryBudgetTable" method="post">
            	<s:hidden name="enterpriseId" value="%{#request.session.enterprise.enterpriseId}"></s:hidden>
            	<s:hidden value="%{#request.createSalaryBudgetTable.budgetId}" name="createSalaryBudgetTable.budgetId"/>
              <div class="row-fluid">
                <div class="input-container">
                  <label>名称</label>
                  <s:textfield name="createSalaryBudgetTable.name" id="budgetName" maxlength="30" value="%{#request.createSalaryBudgetTable.name}"/>
                  <span style="color:red;">*</span>
                  <span id="nameerror"></span>
                  <s:fielderror fieldName="message" theme="simple"></s:fielderror>
                </div>

                <div class="input-container">
                  <label>选择模板</label>
                   <s:select  headerValue="--请选择--" headerKey="0" 
            			 list="%{#request.salaryTemplates}" name="templateId" 
            			 listKey="templateId" listValue="templateName" id="tempid">
             	   </s:select><span style="color:red;">*</span><span id="temp"></span>
                </div>

                <div class="input-container" >
                  <label>生成哪月工资？</label>
                  <input type="text" id="d11"	name="salaryDate" onblur="ajaxfindBeforeCurrentDateTemplate()" 
                  onclick="WdatePicker()" value="${createSalaryBudgetTable.salaryDate}" class="Wdate"  />
                  <span style="color:red;">*</span><span id="salaryDate"></span>
                </div>


                <div class="input-container">
                  <label>选择与其他工资表合并计税</label>
                  <select id="salaryTable" name="budgetId"  disabled="disabled">
                    <option value="0">--请选择--</option>
                  </select>
                </div>

                <div class="input-container">
                  <label>补充说明</label>
                  <textarea rows="3" name="createSalaryBudgetTable.note" cols="60" >
                  		<s:property value="%{#createSalaryBudgetTable.note}"/>
                  </textarea>
                </div>
                <div class="input-container">
                  <s:submit   value="提交" cssClass="btn btn-primary" id="sub" onclick="submin()"/>
                </div>
				<s:actionerror />
              </div>
              <s:token></s:token>
            </s:form>   
          </div>
        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>
</body>

</html>
