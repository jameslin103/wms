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
    		<jsp:include page="../layout/header.jsp"></jsp:include>
    	</div>
   
	<div id="main"> 
      <div class="row-fluid">
        <div id="center-pane">
          <ul class="nav nav-tabs">
            <li><a href="index.jsp">综合</a></li>
            <li><a href="viewEnterpriseEmployees">员工档案</a></li>
            <li><a href="viewSalaryBudgetTable">工资预算表</a></li>
            <li class="active"><a href="viewInsuranceWithMonth">增减员与参保明细</a></li>
          </ul>

          <div class="span4">
            <h3>批量增员（与续保）</h3>
              <div class="row-fluid">


                <div class="alert alert-info">
                  <ol>
                    <li>
                      <a href="downloadBatchIncreaseEmployeesExcel">下载Excel表格</a>
                    </li>
                    <li>
                      	上传增员（与续保）信息表
                      <s:form  action="batchIncreaseEmployees" cssClass="form-search" method="post"   enctype="multipart/form-data" >
                        <input type="file" name="file"><br>
                        <s:token/>
                        <s:submit cssClass="btn btn-primary" value="上传"/>
                      </s:form>
                    </li>
                  </ol>
                 	<s:if test="#request.message.size()!=0">
	                  <p class="red">备注： 
		                  <s:iterator value="#request.message" var="mes">
		                      ${mes}
		                  </s:iterator>
	                  </p>
                  </s:if>
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
