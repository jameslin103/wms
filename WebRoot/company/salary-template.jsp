<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 		<%@ include file="/help/public_css_js.jsp" %>
 		<script type="text/javascript">
		$(function(){
			$("#submit").click(function(){
				var pro=$("#proj").val();
					if(pro==0){
						$("#error").text("必选项!");
						$("#error").css("color","red")
						return false;
					}else{
						$("#error").text(" ");
						$("#error").css("");
						return true;
					}
			});
		
		});

</script>
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
							<li>
								<a href="viewEnterpriseEmployees"  >
									员工档案
								</a>
							</li>
							<li class="active">
								<a href="viewSalaryBudgetTableSummary" >
									工资预算表
								</a>
						  </li>
					</ul>
          <ul class="normal action-container clearfix">
            <li><a href="#info-for-check" data-toggle="modal"> 新建工资模板</a></li>
          </ul>
          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th>序</th>
                <th>项目名称</th>
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
        <s:form action="addSalaryTemplate" method="post" onclick="return valite()">
          <div class="row-fluid">
            <div class="input-container">
              <label>请选择项目:</label>
              	  <s:select list="%{#request.enterprisePO.enterpriseProjects}" name="enterpriseProjects.id" label="0" listKey="id"  theme="simple"
								  listValue="projects"  headerKey="0" headerValue="-请选择-" id="proj"/>
				<span style="color: red" id="error">*</span>
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
              <s:submit value="提交" cssClass="btn btn-primary" id="submit"/>
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