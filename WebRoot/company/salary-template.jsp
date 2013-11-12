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
            <s:iterator value="%{#request.salaryTemplate}" id="salary" status="list">
            <tbody>
              <tr>
                <td><s:property value="%{#list.index+1}"/></td>
                <td><s:property value="%{#salary.templateName}"/></td>
                <td>
                  <ol>
                  	  <s:generator separator="," val="%{#salary.subsidyList}" var="sub" /> 
					   <s:iterator status="sl" value="#request.sub" id="sal">
					   		<s:iterator value="#request.customBonus" id="customBonus">
					   			<s:if test="#request.sal==#customBonus.id">
   	 								<li><s:property value="%{#customBonus.bonusName}"/></li><br/>
   	 							</s:if>
   	 							<s:else>

   	 							
   	 							</s:else>
                			</s:iterator>
						</s:iterator>            
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
                </td>
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
                <td>
                <s:set value="%{#salary.templateId}" var="templateId"></s:set>
               	 <a href="#info-for-check1" onclick="findToIdSalaryTemplate('${templateId}')" data-toggle="modal">修改</a>
                </td>
              </tr>
            </tbody>
            </s:iterator>
          </table>
        </div>
  
      </div>
    </div>

    <div id="footer"></div>

 </div>
  <!-- =============================addSalaryTemplate==================================== -->
    <div id="info-for-check" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">新建/修改工资模板</h3>
      </div>
      <div class="modal-body">
        <s:form action="addSalaryTemplate" method="post">
          <div class="row-fluid">
            <div class="input-container">
              <label>名称</label>
              	<s:textfield name="salaryTemplate.templateName"/>
            </div>
			
			<s:iterator value="#request.customBonus" id="customBonus">
            <div class="input-container">
              	<label class="checkbox">&nbsp;</label> 
             	 <input type="checkbox" value="<s:property value='%{#customBonus.id}'/>" name="salaryTemplate.subsidyList" />
             	 <s:property value="%{#customBonus.bonusName}"/>
            </div>
            </s:iterator>        

            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="salaryTemplate.fiveInsurances" value="1" checked="checked"/>包含五险一金，
              <input type="radio" name="salaryTemplate.fiveInsurances" value="0"/>不包含
            </div>
            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="salaryTemplate.tax" value="1" checked="checked"/>包含个税，
              <input type="radio" name="salaryTemplate.tax" value="0"/>不包含
            </div>
            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="salaryTemplate.status" value="1" checked="checked"/>启用，
              <input type="radio" name="salaryTemplate.status" value="0"/>停用
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
 <!-- =============================updateSalaryTemplate==================================== -->
    <div id="info-for-check1" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">修改工资模板</h3>
      </div>
      <div class="modal-body">
        <s:form action="updateSalaryTemplate" method="post">
        	<s:hidden name="salaryTemplate.templateId"></s:hidden>
          <div class="row-fluid">
            <div class="input-container">
              <label>名称</label>
              	<s:textfield name="salaryTemplate.templateName"/>
            </div>
			
			<s:iterator value="#request.customBonus" id="customBonus">
            <div class="input-container">
              <label class="checkbox">&nbsp;</label> 
              <input type="checkbox" value="<s:property value='%{#customBonus.id}'/>" name="salaryTemplate.subsidyList" />
              	<s:property value="%{#customBonus.bonusName}"/>
            </div>
            </s:iterator>        

            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="salaryTemplate.fiveInsurances" value="1" checked="checked"/>包含五险一金，
              <input type="radio" name="salaryTemplate.fiveInsurances" value="0"/>不包含
            </div>
            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="salaryTemplate.tax" value="1" checked="checked"/>包含个税，
              <input type="radio" name="salaryTemplate.tax" value="0"/>不包含
            </div>
            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="salaryTemplate.status" value="1" checked="checked"/>启用，
              <input type="radio" name="salaryTemplate.status" value="0"/>停用
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