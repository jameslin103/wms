<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
  <head>
	  <base href="<%=basePath%>"/>
	  <title>富民人力银行派遣系统</title>
	  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	  <%@ include file="/help/public_css_js.jsp" %>
</head>
<body>

<div id="container">
	<div id="header">	
    	<jsp:include page="../dashboard.jsp"></jsp:include>
    </div>

	<div id="main"> 
      <div class="row-fluid">
        <div id="center-pane">
          <ul class="nav nav-tabs">
            <li><a href="index.html">综合</a></li>
            <li class="active"><a href="employee-list.html">员工档案</a></li>
            <li><a href="salary-with-month.html">工资预算表</a></li>
            <li><a href="insurance-with-month.html">增减员与参保明细</a></li>
            <li><a href="balance-detail.html">资金往来</a></li>            
          </ul>
          
          <ul class="normal action-container clearfix">
            <li>新员工档案：</li>
            <li><a href="company/employee-step1-of-create.jsp">批量录入</a>，</li>
            <li><a href="#info-for-check" data-toggle="modal">单个录入</a></li>
            <li>&nbsp;/&nbsp;</li>
            <li>查看：</li>
            <li><a href="#">参保</a>，</li>
            <li><a href="#">未参保</a>，</li>
            <li><a href="#">离职员工</a>，</li>
            <li><a href="#">隐藏信息</a></li>
            <li class="right"><a href="../doc/全部员工信息表.xls" class="btn btn-primary">下载全体在职员工信息</a></li>
            <li class="right">
              <form class="navbar-form pull-left" action="company/employee-search-result.jsp">
                <input type="text" placeholder="输入姓名"/>
                <input type="checkbox"/>&nbsp;全站
                <button type="submit" class="btn">搜索</button>
              </form>
            </li>
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th rowspan="2" width="">序</th>
                <th rowspan="2" width="">姓名</th>
                <th rowspan="2" width="">性别</th>
                <th rowspan="2" width="">户口</th>
                <th rowspan="2" width="">婚姻</th>
                <th rowspan="2" width="">照片</th>
                <th rowspan="2" width="">身份证</th>
                <th rowspan="2" width="">电话</th>
                <th rowspan="2" width="">服务费<br>（元）</th>
                <th colspan="2" width="">参保</th>
                <th rowspan="2" width="">哪月起<br>参保</th>
                <th rowspan="2" width="">五险一金基数设置</th>
                <th rowspan="2" width="">个税缴纳</th>
                <th rowspan="2" width="">操作</th>
              </tr>
              <tr>
                <th>是否</th>
                <th>性质</th>
              </tr>

            </thead>
            <s:iterator value="#request.employees" var="employees">
            <tbody>
              <tr>
                <td><s:property value="%{#employees.employeesId}"/></td>
                <td><a href="company/employee-personal-info.jsp">
               			 <s:property value="%{#employees.employeesName}"/>
               		 </a>
                </td>
                <td>
                	<s:if test="%{#employees.employeesSex}==0" >
                		<s:property value="男"/>
                	</s:if>
                	<s:if test="%{#employees.employeesSex}==1" >
                		<s:property value="女"/>
                	</s:if>
                </td>
                <td>
                	<s:if test="employees.householdRegister=='0'" >
                		<s:property value="非农"/>
                	</s:if>
                	<s:if test="%{#employees.householdRegister}=='1'" >
                			<s:property value="农村"/>
                		<s:property value="女"/>
                	</s:if>
                </td>
                <td> 
                	<s:property value="%{#employees.maritalStatus}"/>
                	<s:set name="marital" value="%{#employees.maritalStatus}" />   
                	<s:if test="#marital=='0'">
                		<s:property value="已婚"/>
                	</s:if>
                	<s:else>
                		<s:property value="未婚"/>
                	</s:else>
                </td>
                <td>
                	<s:if test="%{#employees.photo}==1">
                		<s:property value="有"/>
                	</s:if>
                	<s:else>
                		<s:property value="否"/>
                	</s:else>
                </td>
                <td>
               		 <s:property value="%{#employees.cardNumber}"/>
                </td>
                <td>
                   <s:property value="%{#employees.phone}"/>
                 </td>
                <td><s:property value="%{#employees.serviceCost}"/></td>
                
                <td><s:property value="%{#employees.whetherGinseng}"/></td>
                
                <td>
                	<s:if test="%{#employees.ginsengProtectNature}==1">
                		<s:property value="新增"/>
                	</s:if>
                	<s:else>
                		<s:property value="续保"/>
                	</s:else>
                </td>
                <td><s:property value="%{#employees.cinsengDate}"/></td>
                <td>默认</td>
                <td><s:property value="%{#employees.paymentWay}"/></td>
                <td>
                  <a href="#info-for-check" data-toggle="modal">修改</a>
                </td>
              </tr>
            </tbody>
            </s:iterator>
          </table>

          <div class="pagination">
            <ul>
              <li><a href="#">&laquo;</a></li>
              <li><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li><a href="#">6</a></li>
              <li><a href="#">7</a></li>
              <li><a href="#">8</a></li>
              <li><a href="#">&raquo;</a></li>
            </ul>
          </div>

        </div>

      </div>
    </div>

	<div id="footer"></div>

</div>
	<!-- Modal AddEnterpriseEmpoloyess-->
  <div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">员工信息</h3>
    </div>
    <div class="modal-body">  
      <s:form action="addEnterpriseEmployees" method="post">
        <div class="row-fluid">
          <div class="input-container">
            <label>合同编号</label>
            <s:textfield name="enterpriseEmployees.contractNo" />
            
          </div>

          <div class="input-container">
            <label>姓名</label>
            <s:textfield name="enterpriseEmployees.employeesName" />
          </div>

          <div class="input-container">
            <label>身份证</label>
             <s:textfield name="enterpriseEmployees.cardNumber" />
          </div>

          <div class="input-container">
            <label>性别</label>
            <input type="radio" name="enterpriseEmployees.employeesSex" value="1" checked="checked"/>男
            <input type="radio" name="enterpriseEmployees.employeesSex" value="0"/>女
          </div>

          <div class="input-container">
            <label>户口性质</label>
            <input type="radio" name="enterpriseEmployees.householdRegister" value="1" checked="checked"/>非农
            <input type="radio" name="enterpriseEmployees.householdRegister" value="0"/>农村
          </div>

          <div class="input-container">
            <label>是否有照片？</label>
            <input type="radio" name="enterpriseEmployees.photo" value="1"  checked="checked"/>无
            <input type="radio" name="enterpriseEmployees.photo" value="0"/>有
          </div>

          <div class="input-container">
            <label>电话</label>
            <s:textfield name="enterpriseEmployees.phone" />
          </div>
          
          <div class="input-container">
            <label>家庭住址</label>
            <s:textfield name="enterpriseEmployees.homeAddress" />
          </div>

          <div class="input-container">
            <label>银行卡号</label>
             <s:textfield name="enterpriseEmployees.bankCardNumber" />
          </div>

          <div class="input-container">
            <label>开户银行</label>
            <s:select list="#{1:'工商',2:'农行',3:'兴业'}" name="enterpriseEmployees.bank" label="abc" listKey="key"
             listValue="value"  headerKey="0" headerValue="-请选择-"/> 
          </div>

          <div class="input-container">
            <label>籍贯</label>
            <s:textfield name="enterpriseEmployees.nativePlace" />
          </div>

          <div class="input-container">
            <label>行业</label>
            <s:select list="#{1:'计算机与信息',2:'制造业',3:'财政金融'}" name="enterpriseEmployees.industry" label="abc" listKey="key"
             listValue="value"  headerKey="0" headerValue="-请选择-"/> 
          </div>

          <div class="input-container">
            <label>岗位</label>
             <s:select list="#{1:'销售',2:'生产'}" name="enterpriseEmployees.jobs" label="abc" listKey="key"
             listValue="value"  headerKey="0" headerValue="-请选择-"/> 

          </div>

          <div class="input-container">
            <label>婚姻状况</label>
            <input type="radio" name="enterpriseEmployees.maritalStatus" value="1"  checked="checked"/>未婚，
            <input type="radio" name="enterpriseEmployees.maritalStatus" value="0"/>已婚
          </div>

          <div class="input-container">
            <label>文化程度</label>
            <s:select list="#{1:'博士',2:'研究生',3:'本科',4:'大专',5:'中专',6:'高中',7:'初中',8:'小学'}" name="enterpriseEmployees.jobs" label="abc" listKey="key"
             listValue="value"  headerKey="0" headerValue="-=请选择=-"/> 
          </div>

          <div class="input-container">
            <label>合同期限</label>
            起：
           <s:textfield id="d11" name="enterpriseEmployees.startContractDeadline" onclick="WdatePicker()" cssClass="Wdate"/>
          </div>

          <div class="input-container">
            止：
           <s:textfield id="d11" name="enterpriseEmployees.endContractDeadline"  onclick="WdatePicker()" cssClass="Wdate"/>
          </div>

          <div class="input-container">
            <label>是否参保?</label>
            <input type="radio" name="enterpriseEmployees.whetherGinseng" value="1"  checked="checked"/>是，
            <input type="radio" name="enterpriseEmployees.whetherGinseng" value="0"/>否
          </div>

          <div class="input-container">
            <label>参保类型</label>
            <s:checkboxlist list="#{'1':'医保', '2':'社保', '3':'公积金'}" name="enterpriseEmployees.ginsengProtectType"  labelposition="left" key="0"/>
          </div>

          <div class="input-container">
            <label>参保性质</label>
            <input type="radio" name="enterpriseEmployees.ginsengProtectNature" value="1"  checked="checked"/>新增，
            <input type="radio" name="enterpriseEmployees.ginsengProtectNature" value="0"/>续保
          </div>

          <div class="input-container">
            <label>开始参保日期:</label>
           	<s:textfield id="d11"  name="enterpriseEmployees.cinsengDate" onclick="WdatePicker()" cssClass="Wdate" />
          </div>

          <div class="input-container">
            <label>参保基数</label>
            <input type="radio" name="enterpriseEmployees.base" value="1"  checked="checked"/>默认基数，
            <input type="radio" name="enterpriseEmployees.base" value="0"/>个性设置
          </div>

          <div class="input-container">
            <label>社会保险基数</label>
            <s:textfield name="enterpriseEmployees.socialInsurance" />
          </div>
          <div class="input-container">
            <label>生育保险基数</label>
           <s:textfield name="enterpriseEmployees.fertilityInsurance" />
          </div>
          <div class="input-container">
            <label>工伤基数</label>
            <s:textfield name="enterpriseEmployees.inductrialBase" />
          </div>
          <div class="input-container">
            <label>基本医疗保险基数</label>
            <s:textfield name="enterpriseEmployees.basicMedical" />
          </div>
          <div class="input-container">
            <label>住房公积金基数</label>
            <s:textfield name="enterpriseEmployees.housingFund" />
          </div>

          <div class="input-container">
            <label>个税缴纳方式?</label>
            <input type="radio" name="enterpriseEmployees.paymentWay" value="1" checked="checked"/>个人缴纳，
            <input type="radio" name="enterpriseEmployees.paymentWay" value="0"/>企业缴纳
          </div>

          <div class="input-container">
            <label>状态?</label>
            <input type="checkbox" name="enterpriseEmployees.pseudoDelete" value="0"/>隐藏
          </div>
          
          <div class="input-container">
            <label>服务费</label>
            <s:textfield name="enterpriseEmployees.serviceCost" />
          </div>
          
          <div class="input-container">
            <s:submit cssClass="btn btn-primary" value="提交"/>
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
