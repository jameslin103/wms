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
   		<jsp:include page="../layout/header.jsp"/>
    </div>

    <div id="main">
      <div class="row-fluid">

        <div id="center-pane">
          <ul class="nav nav-tabs">
            <li>
              <a href="viewEnterpriseEmployees">员工</a>
            </li>
            <li class="active">
              <a href="company/salary-with-month.jsp">工资</a>
            </li>
          </ul>
 
          <ul class="normal action-container clearfix">
            <li><a href="#info-for-check" data-toggle="modal"> 新建工资模板</a></li>
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th>序</th>
                <th>名称</th>
                <th>包含奖金与补贴项</th>
                <th>包含五险一金</th>
                <th>包含个税</th>
                <th>状态</th>
                <th>操作</th>
              </tr>
            </thead>
            <s:iterator value="#request.salaryTemplate" id="salary">
            <tbody>
              <tr>
                <td><s:property value="%{#salary.id}"/></td>
                <td><s:property value="%{#salary.templateName}"/></td>
                <td>
                  <ol>
                  	  <s:generator separator="," val="%{#salary.subsidyList}" var="sub">  
					     <s:iterator>

					     		  <s:property/>
									
					     			
					         
					      </s:iterator>  
					     </s:generator>  
					     
					     
                		<s:iterator value="#request.customBonus" id="customBonus">
                			<s:if test="%{#customBonus.id}"></s:if>
                		
                		
                		</s:iterator>
                  		<li><s:property value="%{#salary.subsidyList}"/></li>
               
                  </ol>
                </td>
                <td>
                <s:if test="%{#salary.fiveInsurances==1}">
                	<span>是</span>
                </s:if>
               	<s:elseif test="%{#salary.fiveInsurances==0}">
               		<span>否</span>
               	</s:elseif>
                <s:else>
                </s:else>
                </td>
                <td>
                <s:if test="%{#salary.tax==1}">
                	<span>是</span>
                </s:if>
               	<s:elseif test="%{#salary.tax==0}">
               		<span>否</span>
               	</s:elseif>
                <s:else>
                </s:else>
                <td>
                	<s:if test="%{#salary.status==1}">
                	<span>启用</span>
                </s:if>
               	<s:elseif test="%{#salary.status==0}">
               		<span>禁用</span>
               	</s:elseif>
                <s:else>
                </s:else>
                
                </td>
                <td><a href="#info-for-check" data-toggle="modal">修改</a></td>
              </tr>
            </tbody>
            </s:iterator>
          </table>
        </div>
  
      </div>
    </div>

    <div id="footer"></div>

 </div>

    <div id="info-for-check" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">新建/修改工资模板</h3>
      </div>
      <div class="modal-body">
      <!-- =============================addSalaryTemplate==================================== -->
        <s:form action="addSalaryTemplate" method="post">
          <div class="row-fluid">
            <div class="input-container">
              <label>名称</label>
              	<s:textfield name="salaryTemplate.templateName"/>
            </div>
			
			<s:iterator value="#request.customBonus" id="customBonus">
            <div class="input-container">
              <label class="checkbox">&nbsp;</label> 
              <input type="checkbox" value="<s:property value='%{#customBonus.id}'/>" name="salaryTemplate.subsidyList" >
              <s:property value="%{#customBonus.bonusName}"/>
            </div>
            </s:iterator>        

            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="salaryTemplate.fiveInsurances" value="1" checked="checked">包含五险一金，
              <input type="radio" name="salaryTemplate.fiveInsurances" value="0">不包含
            </div>
            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="salaryTemplate.tax" value="1" checked="checked">包含个税，
              <input type="radio" name="salaryTemplate.tax" value="0">不包含
            </div>
            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="salaryTemplate.status" value="1" checked="checked">启用，
              <input type="radio" name="salaryTemplate.status" value="0">停用
            </div>                    

            <div class="input-container">
              <label>&nbsp;</label>
              <s:submit value="提交" cssClass="btn btn-primary"/>
            </div>
          </div>
        </s:form>              
      </div>
      <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
      </div>
    </div> 
</body>

</html>