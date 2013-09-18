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
            <li><a href="company/index.jsp">综合</a></li>
            <li><a href="viewEnterpriseEmployees">员工档案</a></li>
            <li class="active"><a href="viewSalaryBudgetTable">工资预算表</a></li>
            <li><a href="company/insurance-with-month.jsp">增减员与参保明细</a></li>
          </ul>

          <div class="span4">
            <h3>2/3&nbsp;新建工资预算表信息</h3>
              <div class="row-fluid">
              	<s:form action="returnToModifySalaryBudgetTable" method="post" id="myForm">
                <div class="alert">
                   <s:hidden value="%{#request.createSalaryBudgetTable.budgetId}" name="createSalaryBudgetTable.budgetId"/>
                  <p>名称：<s:property value="%{#request.createSalaryBudgetTable.name}"/></p>
                  <p>模板：<s:property value="%{#request.createSalaryBudgetTable.salaryTemplate.templateName}"/></p>
                  <p>哪月：<s:date name="%{#request.createSalaryBudgetTable.salaryDate}" format="yyyy年MM月" /></p>
                  <p>合并计税工资表：<s:property value="%{#request.createSalaryBudgetTable.chooseTax}"/></p>
                  <p><a href="javascript:commit()">返回修改</a></p>
                </div>
                 <s:token/>
                </s:form>
                <hr>
                
                <div class="alert alert-info">
                  <ol>
                    <li>
                      <a href="downloadSalaryBudgetTable">下载工资预算表</a>
                    </li>
                    <li>
                      	上传新工资预算表
                      <s:form action="uploadEmployeesSalaryDetail" cssClass="form-search" method="post" enctype="multipart/form-data">
                      	 <s:hidden value="%{#request.createSalaryBudgetTable.budgetId}" name="budgetId"/>
                      	<s:hidden name="file" value="file"></s:hidden>
                        <input type="file" name="file" id="filevalue"><br>
                        <s:submit  cssClass="btn btn-primary" id="uploadFile" value="上传"/>
                      </s:form>
                    </li>
                  </ol> 
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
