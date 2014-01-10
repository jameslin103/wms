<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
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
     		<div id="sub-header" class="clearfix">
					<h2>
						<s:property value="%{#request.session.enterprise.fullName}" />
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
					 <li>
						<a href="newSalaryBudgetTable">新建工资预算表</a>
					</li>
					<li>
						&nbsp;/&nbsp;
					</li>
					<li>
						<a href="viewSalaryTemplate">工资模板</a>
					</li>
					<li>
					&nbsp;/&nbsp;
					</li>
					<li>
						<a href="viewCustomBonus">定制奖金与各种补贴</a>
					</li>
				</ul>
          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th rowspan="2">工资预算表<br/>名称</th>
                <th rowspan="2" style="text-align:center">工资所属月份</th>
                <th rowspan="2" style="text-align:center">合并计税工资表</th>
                <th rowspan="2" style="text-align:center">性质</th>
                <th colspan="5" style="text-align:center">工资预算表汇总信息</th>
                <th colspan="4" style="text-align:center">发放明细</th>
                <th colspan="1" style="text-align:center">状态</th>
                <th rowspan="2">操作</th>
              </tr>
                <th>开票<br/>总额（元）</th>
                <th>工资<br/>总额（元）</th>
                <th>服务费<br/>总额（元）</th>
                <th style="color: red;">点数服务费总额（元）</th>
                <th>五险一金<br/>总额（元）</th>
                <th>发放<br/>人数（人）</th>
                <th>民生<br/>银行（人）</th>
                <th>他行<br/>（人）</th>
                <th>现金（人）</th>
                <th>（制作、实际发放）</th>
            </thead>
            <tbody>
             <s:iterator value="#request.createSalaryBudgetTables" id="createSalaryBudgetTable">
              <tr>
                <td>
                	<s:property value="%{#createSalaryBudgetTable.name}"/>
                	<a href="returnToModifyBudgetTable?budgetId=<s:property value="%{#createSalaryBudgetTable.budgetId}"/>"><br/>(重新上传工资)</a>
                </td>
                <td><s:date name="%{#createSalaryBudgetTable.salaryDate}" format="yyyy年MM月"/></td>
                <td><s:property value="%{#createSalaryBudgetTable.chooseTax}"/></td>
                <td><s:property value="%{#createSalaryBudgetTable.enterprise.insurancesTax.insurancesType==0?'市医保':'省医保'}"/></td>
                <td><s:property value="%{#createSalaryBudgetTable.makeTotal}"/></td>
                <td><s:property value="%{#createSalaryBudgetTable.wageTotal}"/></td>
                <td><s:property value="%{#createSalaryBudgetTable.serviceTotal}"/></td>
                  <td><s:property value="%{#createSalaryBudgetTable.serviceHeTotal}"/></td>
                <td><s:property value="%{#createSalaryBudgetTable.fiveInsurancesTotal}"/></td>
                <td>
	                <s:property value="%{#createSalaryBudgetTable.issueNumber}"/>
	                <br/>
	                <a href="viewSalaryWithBankPersonalNumber?budgetId=<s:property value="%{#request.createSalaryBudgetTable.budgetId}"/>">查看</a>
                </td>
                <td>
                	
		                <s:property value="%{#createSalaryBudgetTable.cmbc}"/><br/><span class="em">
		                  <s:if test="#request.createSalaryBudgetTable.cmbcDate!=null" >
		                	（<s:property value="%{#request.createSalaryBudgetTable.status}"/>）</span>
		                <br/><s:date name="%{#createSalaryBudgetTable.cmbcDate}" format="yyyy年MM月dd日HH时"/>
	                	</s:if>
                </td>
                <td>
	                <s:property value="%{#createSalaryBudgetTable.heLines}"/><br/><span class="em">
	                   <s:if test="#request.createSalaryBudgetTable.heLinesDate!=null" >
	               		（<s:property value="%{#createSalaryBudgetTable.status}"/>）</span>
	                	<br/><s:date name="%{#createSalaryBudgetTable.heLinesDate}" format="yyyy年MM月dd日HH时"/>
	                  </s:if>
                </td>
                <td>
	                <s:property value="%{#createSalaryBudgetTable.cashnumber}"/><br/><span class="em">
	                <s:if test="#createSalaryBudgetTable.cashnumberDate!=null" >
	                	（<s:property value="%{#createSalaryBudgetTable.status}"/>）
	                	</span>
		                <br/>
		                <s:date name="%{#createSalaryBudgetTable.cashnumberDate}" format="yyyy年MM月dd日HH时"/>
	                </s:if>
                </td>
                <td>
                  <ul>
                    <li>制作:<s:property value="%{#createSalaryBudgetTable.user.employee.name}"/>
                    </li>
                    <li>发放：<s:property value="%{#createSalaryBudgetTable.user_operator}"/></li>
                  </ul>
                </td>
                <td>
                  <s:set value="%{#createSalaryBudgetTable.budgetId}" var="budgetId"></s:set>
                  <a href="#info-for-check" onclick="findToIdSalayBudegTable('${budgetId}')" data-toggle="modal">修改</a>
                  <a href="deleteSalayBudgetTable?budgetId=<s:property value="%{#createSalaryBudgetTable.budgetId}" />&enterpriseId=<s:property value="%{#session.enterprise.enterpriseId}" />">删除</a><br>
                  <a href="viewAllEmployeesSalaryDetail?budgetId=<s:property value="%{#createSalaryBudgetTable.budgetId}"/>">查看</a>
                  <a href="downloadSalaryWithSumOfCategoriesReport?budgetId=<s:property value="%{#createSalaryBudgetTable.budgetId}" />">下载</a>
                </td>
              </tr>
               </s:iterator>
            </tbody>
          
          </table>
        </div>
  
      </div>
    </div>

    <div id="footer"></div>
  </div>
  <!-- ======================================================================== -->
  <div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">工资预算表修改</h3>
    </div>
    <div class="modal-body">
      <div class="row-fluid">
         <s:form action="updateSalayBudgetTable" method="post">
         	  <s:hidden name="enterpriseId" value="%{#request.session.enterprise.enterpriseId}"></s:hidden>
         	  <input name="createSalaryBudgetTable.makeTotal" type="hidden"/>
         	  <input name="createSalaryBudgetTable.serviceTotal" type="hidden"/>
         	  <s:hidden name="createSalaryBudgetTable.budgetId"></s:hidden>
         	  <s:hidden name="budgetId"></s:hidden>
              <div class="row-fluid">
                <div class="input-container">
                  <label>名称</label>
                  <s:textfield name="createSalaryBudgetTable.name" />
                </div>

                <div class="input-container">
                  <label>模板</label>
                  <span id="templateName"></span>
                </div>

                <div class="input-container" >
                  <label>生成哪月工资？</label>
                  <input type="text" id="d11"	onfocus="WdatePicker({skin:'whyGreen'})"  onblur="ajaxfindBeforeCurrentDateTemplate()" 
                         name="createSalaryBudgetTable.salaryDate"  class="Wdate"/>
                </div>
                <div class="input-container">
                  <label>选择与其他工资表合并计税</label>
                  <select id="salaryTable" name="createSalaryBudgetTable.chooseTax" >
                    <option value="0">--请选择--</option>
                  </select>
                </div>
                <div class="input-container">
                  <label>其它方式服务费</label>
                  <s:textfield name="createSalaryBudgetTable.serviceHeTotal" maxlength="30"/>
                </div>
                <div class="input-container">
                  <label>补充说明</label>
                  <textarea rows="3" name="createSalaryBudgetTable.note" cols="30" id="note">
                  			
                  </textarea>
                </div>

                <div class="input-container">
                  <s:submit   value="提交" cssClass="btn btn-primary" />
                </div>

              </div>
              <s:token></s:token>
            </s:form>   
      </div>
    </div>

    <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    </div>
  </div>
</body>

</html>