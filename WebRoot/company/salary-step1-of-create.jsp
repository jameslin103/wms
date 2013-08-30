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
            <li><a href="company/index.jsp">综合</a></li>
            <li><a href="viewEnterpriseEmployees">员工档案</a></li>
            <li class="active"><a href="viewSalaryBudgetTable">工资预算表</a></li>
            <li><a href="company/insurance-with-month.jsp">增减员与参保明细</a></li>
          </ul>
			<div class="span4">
            <h3>1/3&nbsp;新建工资预算表信息</h3>
            <s:form action="addSalaryBudgetTable" method="post">
              <div class="row-fluid">
                <div class="input-container">
                  <label>名称</label>
                  <s:textfield name="createSalaryBudgetTable.name" cssStyle="width: 220px;height:30px;"/>
                </div>

                <div class="input-container">
                  <label>选择模板</label>
                   <s:select  headerValue="--请选择--" headerKey="0" 
            			 list="%{#request.salaryTemplates}" name="createSalaryBudgetTable.temple" 
            			 listKey="templateName" listValue="templateName">
             	   </s:select> 
                </div>

                <div class="input-container" >
                  <label>生成哪月工资？</label>
                  <s:textfield id="d11"	name="salaryDate" onclick="WdatePicker()" value="%{#createSalaryBudgetTable.salaryDate}" cssClass="Wdate" cssStyle="width: 220px;height:30px;"  />
                </div>


                <div class="input-container">
                  <label>选择与其他工资表合并计税</label>
                  <select id="salaryTable" name="createSalaryBudgetTable.chooseTax" >
                    <option value="%{#createSalaryBudgetTable.temple}">--请选择--</option>
                  </select>
                </div>

                <div class="input-container">
                  <label>补充说明</label>
                  <textarea rows="3" name="createSalaryBudgetTable.note"   style="width: 220px;">
                  		<s:property value="%{#createSalaryBudgetTable.note}"/>
                  </textarea>
                </div>

                <div class="input-container">
                  <s:submit   value="提交" cssClass="btn btn-primary" />
                </div>

              </div>
            </s:form>   
          </div>
        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>
</body>

</html>
