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
		<script type="text/javascript">
			 function topage(page){
				var form = document.getElementById("myformdate");
					form.page.value=page;
					form.submit();
			}
			function selected(){
				var budgetid=$("#bud").val();
				var select=$("#se").is(':checked');
				if(select==true){
					window.self.location="downloadEmployeesSalaryDetail?budgetId="+budgetid+"&selected="+select;  
				}else{
					window.self.location="downloadEmployeesSalaryDetail?budgetId="+budgetid+"&selected=false";
				
				}
			}
		</script>
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
						<a href="viewEnterpriseDetailed" >综合 </a>
					</li>
					<li>
						<a href="viewEnterpriseEmployees"  >员工档案</a>
					</li>
						<li class="active">
							<a href="viewSalaryBudgetTableSummary" >工资预算表</a>
						</li>
					</ul>
          
          <ul class="normal action-container clearfix">
            <li class="title"><s:date name="%{#request.pageView.records[1].salaryDate}" format="yyyy年MM月"/>工资明细</li>
            <li class="right">
            	<a class="btn btn-primary" onclick="selected()">下载Excel表格</a>
            	<s:hidden value="%{#request.budgetId}" name="budgetId" id="bud"/>
            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" value="true" name="selected" id="se"/>&nbsp;(<span style="color:red;">特殊补贴</span>)
            </li>
          </ul>
		  <s:form action="viewAllEmployeesSalaryDetail" method="post" id="myformdate">
		  		
            	<s:hidden name="budgetId"/>
            	<s:hidden name="page"/>
          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th rowspan="2">姓名</th>
                <th rowspan="2">操作</th>
                <th rowspan="2">工资</th>
                <th rowspan="2">奖金</th>
                <th rowspan="2">补贴</th>
                <th rowspan="2">应发工资</th>
                <th rowspan="2">社会保险基数</th>
                <th colspan="2">养老保险</th>
                <th colspan="2">失业保险</th>
                <th rowspan="2">生育工伤基数</th>
                <th rowspan="2">生育（企业）</th>
                <th rowspan="2">工伤（企业）</th>
                <th colspan="4" style="text-align:center;">基本医疗保险</th>
                <th colspan="3" style="text-align:center;">住房公积金</th>
                <th colspan="4" style="background-color:#8D45C4; text-align:center;">特殊补贴</th>
                <th colspan="2">小计</th>
                <th rowspan="2">税前工资</th>
                <th rowspan="2">意外险</th>
                <th colspan="2">个税</th>
                <th rowspan="2">服务费</th>
                <th rowspan="2" style="text-align:center; content:#8D45C4">点数服务费</th>
                <th rowspan="2">合计（企业应付）</th>
                <th rowspan="2">到卡金额</th>
              </tr>
              <tr>
                <td>企业</td>
                <td>个人</td>
                <td>企业</td>
                <td>个人</td>
                <td>缴费基数</td>
                <td>企业</td>
                <td>个人</td>
                <td>大病统筹</td>
                <td>缴费基数</td>
                <td>企业</td>
                <td>个人</td>
                <td style="background-color:#E5D82A;">养老失业补贴</td>
                <td style="background-color:#E5D82A;">工伤生育补贴</td>
                <td style="background-color:#E5D82A;">医保补贴</td>
                <td style="background-color:#E5D82A;">公积金补贴</td>
                <td>企业</td>
                <td>个人</td>
                <td>企业</td>
                <td>个人</td>
              </tr>
            </thead>  
            <thbody>
            <s:iterator value="#request.pageView.records" id="employeesSalaryDetail" status="list">
              <tr>
                <td><s:property value="%{#employeesSalaryDetail.employeesName}"/></td>
                <td>
                <s:set value="%{#employeesSalaryDetail.salaryId}" var="salaryId"></s:set>
                <a href="#info-for-check" onclick="findToIdSalaryDetail('${salaryId}')" data-toggle="modal">修改</a></td>
                <td><s:property value="%{#employeesSalaryDetail.wage}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.bonus}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.subsidies}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.shouldPay}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.socialInsuranceBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.enterprisePensionInsurance}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.personalPensionInsurance}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.enterpriseUnemploymentInsurance}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.personalUnemploymentInsurance}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.birthInsuranceBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.enterpriseBirthInsurance}"/></td>                
                <td><s:property value="%{#employeesSalaryDetail.enterpriseInductrialInjuryBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.medicalPaymentBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.enterpriseMedicalBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.personalMedicalBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.morbidityStatistics}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.HousingReserveBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.enterpriseReserveBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.personalReserveBase}"/></td>
                
                <td style="color:#4912FD;"><s:property value="%{#employeesSalaryDetail.specialOldSubsidies}"/></td>
                <td style="color:#4912FD;"><s:property value="%{#employeesSalaryDetail.specialUnemploymentSubsidies}"/></td>
                <td style="color:#4912FD;"><s:property value="%{#employeesSalaryDetail.specialHealthCareSubsidies}"/></td>
                <td style="color:#4912FD;"><s:property value="%{#employeesSalaryDetail.specialAccumulationFundSubsidies}"/></td>
                
                
                <td><s:property value="%{#employeesSalaryDetail.enterpriseSubtotal}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.personalSubtotal}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.beforeSalary}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.accident}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.enterpriseTax}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.personalTax}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.serviceCharge}"/></td>
                <td>&nbsp;&nbsp;&nbsp;</td>
                <td><s:property value="%{#employeesSalaryDetail.aggregate}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.moneyToCards}"/></td>
              </tr>
            </s:iterator>
            
            	<tr style="color:#EC222D;"> 
            	<td colspan="2" >合计:</td>
                <td><s:property value="employeesSalaryDetail.wage"/></td>
                <td><s:property value="employeesSalaryDetail.bonus"/></td>
                <td><s:property value="employeesSalaryDetail.subsidies"/></td>
                <td><s:property value="employeesSalaryDetail.shouldPay"/></td>
                <td style="text-align: center;">--</td>
                <td colspan="4" style="text-align: center;">
                		<s:property value="employeesSalaryDetail.enterprisePensionInsurance+
						                    employeesSalaryDetail.personalPensionInsurance+
						                    employeesSalaryDetail.enterpriseUnemploymentInsurance+
						                    employeesSalaryDetail.personalUnemploymentInsurance"/>
                </td>
                <td style="text-align: center;">--</td>
                <td  colspan="2" style="text-align: center;">
                	<s:property value="employeesSalaryDetail.enterpriseBirthInsurance
                					  +employeesSalaryDetail.enterpriseInductrialInjuryBase"/>
                </td>                
                <td style="text-align: center;">--</td>
                <td colspan="3" style="text-align: center;">
                	<s:property value="employeesSalaryDetail.enterpriseMedicalBase+
                					    employeesSalaryDetail.personalMedicalBase+
                					    employeesSalaryDetail.morbidityStatistics"/>
                </td>
                <td style="text-align: center;">--</td>
                
                <td colspan="2" style="text-align: center;">
                	<s:property value="employeesSalaryDetail.enterpriseReserveBase+employeesSalaryDetail.personalReserveBase"/>
                </td>
                
                <td><s:property value="employeesSalaryDetail.specialOldSubsidies"/></td>
                <td><s:property value="employeesSalaryDetail.specialUnemploymentSubsidies"/></td>
                <td><s:property value="employeesSalaryDetail.specialHealthCareSubsidies"/></td>
                <td><s:property value="employeesSalaryDetail.specialAccumulationFundSubsidies"/></td>
                
                <td colspan="2" style="text-align: center;">
                	<s:property value="employeesSalaryDetail.enterpriseSubtotal+employeesSalaryDetail.personalSubtotal"/>
                </td>
                <td><s:property value="employeesSalaryDetail.beforeSalary"/></td>
                 <td><s:property value="employeesSalaryDetail.accident"/></td>
                <td colspan="2" style="text-align: center;">
                	<s:property value="employeesSalaryDetail.enterpriseTax+employeesSalaryDetail.personalTax"/>
                </td>
                <td colspan="2" style="text-align: center;">
                	<s:property value="%{#employeesSalaryDetail.createSalaryBudgetTable.serviceTotal}"/>
                </td>
                <td><s:property value="employeesSalaryDetail.aggregate"/></td>
                <td><s:property value="employeesSalaryDetail.moneyToCards"/></td></tr>
             </thbody>
          </table>
          <div class="pagination">
            <%@include file="/share/fenye.jsp" %>
          </div>
           </s:form>
        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>

  <div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">工资明细</h3>
    </div>

    <div class="modal-body">
    	
      <h3><s:date name="%{#request.employeesSalaryDetail[1].salaryDate}" format="yyyy年MM月" />工资</h3>
      <form action="updateEmployeesSalaryDetail" method="post">
      	<s:hidden name="employeesSalaryDetail.salaryId"></s:hidden>
      	<s:hidden name="budgetId"></s:hidden>
        <div class="row-fluid">
          <div class="input-container">
            <p>姓名：<s:label id="name"></s:label></p>
          </div>

          <div class="input-container">
            <label>工资</label>
            <input type="text" name="employeesSalaryDetail.wage"/>
          </div>

          <div class="input-container">
            <label>奖金</label>
            <input type="text" name="employeesSalaryDetail.bonus"/>
          </div>

          <div class="input-container">
            <label>补贴</label>
            <input type="text" name="employeesSalaryDetail.subsidies"/>
          </div>
           <div class="input-container">
            <label>意外险</label>
            <input type="text" name="employeesSalaryDetail.accident"/>
          </div>
          <div class="input-container">
            <s:submit value="提交" cssClass="btn btn-primary"/>
          </div>
        </div>
      </form>
    </div>

    <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    </div>
  </div>
</body>
</html>
