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
				<jsp:include page="../layout/header.jsp"></jsp:include>

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
            <li><a href="index.html">综合</a></li>
            <li><a href="viewEnterpriseEmployees">员工档案</a></li>
            <li><a href="viewSalaryBudgetTable">工资预算表</a></li>
            <li class="active"><a href="viewInsuranceWithMonth">增减员与参保明细</a></li>
          </ul>

          <div class="span4">
            <h3>批量减员</h3>
              <div class="row-fluid">


                <div class="alert alert-info">
                  <ol>
                    <li>
                      <a href="downloadReductionTemplate">下载Excel表格</a>
                    </li>
                    <li>
                      	上传减员信息表
                      <form  action="uploadInsuranceReduction" class="form-search" method="post" enctype="multipart/form-data">
                        <s:file name="file"/><br>
                        <button type="submit" class="btn btn-primary">上传</button>
                      </form>
                    </li>
                  </ol>
                  <s:if test="#request.messageList.size()!=0">
                  <p class="red">备注：<br/>
                  	<s:iterator value="#request.messageList" id="mag">
              			<span style="color: red">${mag}</span><br/>
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
