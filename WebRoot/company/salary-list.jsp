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
		<script type="text/javascript">
			 
		
		
		</script>
	</head>
	<body>

		<div id="container">
			<div id="header">
				<jsp:include page="../layout/header.jsp" />
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
            <li><a href="company/index.jsp">综合</a></li>
            <li><a href="viewEnterpriseEmployees">员工档案</a></li>
            <li class="active"><a href="viewSalaryBudgetTable">工资预算表</a></li>
            <li><a href="viewInsuranceWithMonth">增减员与参保明细</a></li>
          </ul>
          
          <ul class="normal action-container clearfix">
            <li class="title"><s:date name="%{#request.employeesSalaryDetail[1].salaryDate}" format="yyyy年MM月"/>工资明细</li>
            <li class="right"><a href="downloadEmployeesSalaryDetail?budgetId=<s:property value="%{#request.budgetId}"/>" class="btn btn-primary">下载Excel表格</a></li>
          </ul>

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
                <th rowspan="2">生育保险基数</th>
                <th rowspan="2">生育（企业）</th>
                <th rowspan="2">工伤基数</th>
                <th rowspan="2">工伤（企业）</th>
                <th colspan="3">基本医疗保险</th>
                <th colspan="3">住房公积金</th>
                <th rowspan="2">大病统筹</th>
                <th colspan="2">小计</th>
                <th rowspan="2">税前工资</th>
                <th colspan="2">个税</th>
                <th rowspan="2">服务费</th>
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
                <td>缴费基数</td>
                <td>企业</td>
                <td>个人</td>
                <td>企业</td>
                <td>个人</td>
                <td>企业</td>
                <td>个人</td>
              </tr>
            </thead>  
            <s:iterator value="#request.employeesSalaryDetail" id="employeesSalaryDetail">
            <thbody >
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
                <td><s:property value="%{#employeesSalaryDetail.inductrialInjuryBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.enterpriseInductrialInjuryBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.medicalPaymentBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.enterpriseMedicalBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.personalMedicalBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.HousingReserveBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.enterpriseReserveBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.personalReserveBase}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.morbidityStatistics}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.enterpriseSubtotal}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.personalSubtotal}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.beforeSalary}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.enterpriseTax}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.personalTax}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.serviceCharge}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.aggregate}"/></td>
                <td><s:property value="%{#employeesSalaryDetail.moneyToCards}"/></td>
              </tr>
            </thbody>
            </s:iterator>
          </table>

          <div class="pagination">
            <%@include file="/share/fenye.jsp" %>
          </div>
          
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
            <input type="text" name="employeesSalaryDetail.wage">
          </div>

          <div class="input-container">
            <label>奖金</label>
            <input type="text" name="employeesSalaryDetail.bonus">
          </div>

          <div class="input-container">
            <label>补贴</label>
            <input type="text" name="employeesSalaryDetail.subsidies">
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
