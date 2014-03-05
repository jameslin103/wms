<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加员工信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/help/public_css_js.jsp"%>
  </head>
  	 <style>
  		fieldset legend{
		    text-align:center;
		    width:40%\9;    /* 兼容IE浏览器 */
		    padding:0 5px;
		    margin:0 auto;  /* 兼容Firefox , Opera */
  	</style>
  	<script type="text/javascript">
  		$(function(){
				$("#cancel").click(function(){
					history.go(-1);
				});
			
  		});
  	
  	</script>
  <body style="background-color:#F1EEEE;">
	 <center>
		 <br/>
		<fieldset style="border:2px groove #F0F0F0; width:1100px;">
			<legend>
				<span style="font-size:30px;">${session.enterprise.fullName}</span>
			</legend>
			 <h3>修改员工信息</h3>
	  	  <form action="updateEnterEmployees" method="post">
	  	  	 <s:hidden name="page" value="%{#request.page}"></s:hidden>
	  	 	 <s:hidden  name="enterpriseEmployees.employeesId" value="%{#request.enterpriseEmployees.employeesId}"/>
	  		<table width="900px;" style="text-align: left;">
  			  <tr>
  			  	  <td>合同编号:</td>
  			  	  <td>
  			  	  	<input type="text" name="enterpriseEmployees.contractNo" value="${enterpriseEmployees.contractNo}" class="required email" id="input_e"/>
  			  	  </td>
  			  </tr>
  			  <tr>
  			  	  <td>姓&nbsp;&nbsp;&nbsp;名:</td>
  			  	  <td>
  			  	  	<input type="text" name="enterpriseEmployees.employeesName" value="${enterpriseEmployees.employeesName}" id="employeesName"/>
  			  	  </td>
  			  	  <td>身 份 证:</td>
				  <td>
				  	<input type="text" name="enterpriseEmployees.cardNumber" value="${enterpriseEmployees.cardNumber}" id="carnumber"/>
				  </td>
  			  </tr>
  			  <tr>
  			  	<td> 籍&nbsp;&nbsp;&nbsp;贯:</td>
				  <td>
				  	<input type="text" name="enterpriseEmployees.nativePlace" value="${enterpriseEmployees.nativePlace}" />
				  </td>
  			  	  <td>家庭住址:</td>
				  <td>
					<input type="text" name="enterpriseEmployees.homeAddress"  value="${enterpriseEmployees.homeAddress}"/>
				  </td>
  			  </tr>
  			  <tr >
  			  	 <td style="padding-top:10px;">性&nbsp;&nbsp;&nbsp;别:</td>
  			  	  <td style="padding-top:10px;">
					<input type="radio" name="enterpriseEmployees.employeesSex" value="女"
					  	 <s:if test="%{(#request.enterpriseEmployees.employeesSex!= null) && (\"女\" == #request.enterpriseEmployees.employeesSex)}">checked</s:if> />女
  			  	 	<input type="radio" name="enterpriseEmployees.employeesSex" value="男"
  			  	 		<s:if test="%{(#request.enterpriseEmployees.employeesSex!=null) && (\"男\" == #request.enterpriseEmployees.employeesSex)}">checked</s:if> />男 
  			  	  </td>
  			  	  <td style="padding-top:10px;">户口性质:</td>
  			  	  <td style="padding-top:10px;">
  			  	  
					<input type="radio" name="enterpriseEmployees.householdRegister" value="1"
					  	 <s:if test="%{(#request.enterpriseEmployees.householdRegister!= null) && 
					  	 (\"1\" == #request.enterpriseEmployees.householdRegister)}">checked</s:if> />非农，
  			  	 	<input type="radio" name="enterpriseEmployees.householdRegister" value="2"
  			  	 		<s:if test="%{(#request.enterpriseEmployees.householdRegister!= null) &&
  			  	 		(\"2\" == #request.enterpriseEmployees.householdRegister)}">checked</s:if> />农村
  			  	  </td>
  			  </tr>
  			  <tr>
  			  	<td style="padding-top:10px;">是否有照片?</td>
  			  	<td style="padding-top:10px;">
				    <input type="radio" name="enterpriseEmployees.photo" value="0"
					  	 <s:if test="%{(#request.enterpriseEmployees.photo!= null) && (\"0\" == #request.enterpriseEmployees.photo)}">checked</s:if> />有
  			  	 	<input type="radio" name="enterpriseEmployees.photo" value="1"
  			  	 		<s:if test="%{(#request.enterpriseEmployees.photo!= null) && (\"1\" == #request.enterpriseEmployees.photo)}">checked</s:if> />无
				    
  			  	 </td>
  			  	<td style="padding-top:10px;">婚姻状况:</td>
  			  	<td style="padding-top:10px;">
  			  	
					<input type="radio" name="enterpriseEmployees.maritalStatus" value="1"
					  	 <s:if test="%{(#request.enterpriseEmployees.maritalStatus!= null) && 
					  	 (\"1\" == #request.enterpriseEmployees.maritalStatus)}">checked</s:if> />未婚，
  			  	 	<input type="radio" name="enterpriseEmployees.maritalStatus" value="2"
  			  	 		<s:if test="%{(#request.enterpriseEmployees.maritalStatus!= null) && 
  			  	 					(\"2\" == #request.enterpriseEmployees.maritalStatus)}">checked</s:if> />已婚
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td style="padding-top:10px;">电&nbsp;&nbsp;&nbsp;话:</td>
  			  	<td style="padding-top:10px;">
					<input type="text" name="enterpriseEmployees.phone" value="${enterpriseEmployees.phone}"/>
  			  	</td>
  			  	<td style="padding-top:10px;">银行卡号:</td>
  			  	<td style="padding-top:10px;">
					<input type="text" name="enterpriseEmployees.bankCardNumber" value="${enterpriseEmployees.bankCardNumber}"/>
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td>开户银行</td>
  			  	<td><input type="text" name="enterpriseEmployees.bank" value="${enterpriseEmployees.bank}"/></td>
  			  	<td>行&nbsp;&nbsp;&nbsp;业</td>
  			  	<td><input type="text" name="enterpriseEmployees.industry" value="${enterpriseEmployees.industry}"/></td>
  			  </tr>
  			  <tr>
  			  	<td>岗&nbsp;&nbsp;&nbsp;位</td>
  			  	<td><input type="text" name="enterpriseEmployees.jobs" value="${enterpriseEmployees.jobs}"/></td>
  			  	<td>文化程度</td>
  			  	<td><input type="text" name="enterpriseEmployees.levelEducation" value="${enterpriseEmployees.levelEducation}"/></td>
  			  </tr>
  			  <tr>
  			  	<td>合同期限-(起):</td>
  			  	<td>
				<input type="text" id="d4311"name="enterpriseEmployees.startContractDeadline" value="${enterpriseEmployees.startContractDeadline}"
					onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4311\')||\'2020-10-01\'}',skin:'whyGreen'})" class="Wdate" />
  			  	</td>
  			  	<td>止：</td>
  			  	<td>
					<input type="text" id="d4312" name="enterpriseEmployees.endContractDeadline" value="${enterpriseEmployees.endContractDeadline}"
					class="Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01',skin:'whyGreen'})"/>
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td>是否参保?</td>
  			  	<td>
  			  	 <s:if test="#request.enterpriseEmployees.whetherGinseng==1">
  			  	 	 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="1" checked="checked" />是，
					 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="0" />否，
					 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="2" />特殊参保(<span style="color:blue;">补贴</span>)
  			  	 </s:if>
  			  	 <s:elseif test="#request.enterpriseEmployees.whetherGinseng==0">
  			  	 	 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="1" />是，
					 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="0"  checked="checked"/>否，
					 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="2" />特殊参保(<span style="color:blue;">补贴</span>)
  			  	 </s:elseif>
  			  	  <s:elseif test="#request.enterpriseEmployees.whetherGinseng==2">
  			  	 	 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="1" />是，
					 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="0" />否，
					 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="2"  checked="checked"/>特殊参保(<span style="color:blue;">补贴</span>)
  			  	 </s:elseif>
  			  	 
  			  	
  			  	</td>
  			  	<td>参保类型:</td>
  			  	<td>
					<input type="checkbox" name="enterpriseEmployees.sociaSecurity" value="是"
					  	 <s:if test="%{(#request.enterpriseEmployees.sociaSecurity!= null)&& 
					  	 (\"是\" == #request.enterpriseEmployees.sociaSecurity)}">checked</s:if> />&nbsp;医保
					  	 
					<input type="checkbox" name="enterpriseEmployees.healthCare" value="是"
					  	 <s:if test="%{(#request.enterpriseEmployees.healthCare!=null) && 
					  	 (\"是\" == #request.enterpriseEmployees.healthCare)}">checked</s:if> />&nbsp;社保
					  	 
					 <input type="checkbox" name="enterpriseEmployees.accumulationFund" value="是"
					  	 <s:if test="%{(#request.enterpriseEmployees.accumulationFund!=null) && 
					  	 (\"是\" == #request.enterpriseEmployees.accumulationFund)}">checked</s:if> />&nbsp;公积金
  			  	</td>
  			  </tr>
  			   <tr>
  			  	<td>参保性质</td>
  			  	<td>
					
					<input type="radio" name="enterpriseEmployees.ginsengProtectNature" value="1"
					  	 <s:if test="%{(#request.enterpriseEmployees.ginsengProtectNature!= null)&& 
					  	 (\"1\" == #request.enterpriseEmployees.ginsengProtectNature)}">checked</s:if> />&nbsp;新增，
					  	 
					<input type="radio" name="enterpriseEmployees.ginsengProtectNature" value="2"
					  	 <s:if test="%{(#request.enterpriseEmployees.ginsengProtectNature!=null) && 
					  	 (\"2\" == #request.enterpriseEmployees.ginsengProtectNature)}">checked</s:if> />&nbsp;续保
					  	 
					 <input type="radio" name="enterpriseEmployees.ginsengProtectNature" value="0"
					  	 <s:if test="%{(#request.enterpriseEmployees.ginsengProtectNature!=null) && 
					  	 (\"0\" == #request.enterpriseEmployees.ginsengProtectNature)}">checked</s:if> />&nbsp;无参保
					
					
  			  	</td>
  			  	<td>开始参保日期:</td>
  			  	<td style="padding-top:10px;">
					<input type="text" id="d11" name="enterpriseEmployees.cinsengDate" onclick="WdatePicker()" value="${enterpriseEmployees.cinsengDate}" class="Wdate" />
  			  	</td>
  			  </tr>
  			   <tr>
  			  	<td>参保基数</td>
  			  	<td>
					<input type="radio" name="enterpriseEmployees.base" value="0"
					  	 <s:if test="%{(#request.enterpriseEmployees.base!= null)&& 
					  	 (\"0\" == #request.enterpriseEmployees.base)}">checked</s:if> />&nbsp;默认基数
					  	 
					<input type="radio" name="enterpriseEmployees.base" value="1"
					  	 <s:if test="%{(#request.enterpriseEmployees.base!=null) && 
					  	 (\"1\" == #request.enterpriseEmployees.base)}">checked</s:if> />&nbsp;个性设置
					  	 
  			  	</td>
  			  	<td>住房公积金基数</td>
  			  	<td><s:textfield name="enterpriseEmployees.housingFund" value="%{#request.enterpriseEmployees.housingFund}"/></td>
  			  </tr>
  			   <tr>
  			  	<td>社会保险基数</td>
  			  	<td>
  			  		<s:textfield name="enterpriseEmployees.socialInsurance" value="%{#request.enterpriseEmployees.socialInsurance}"/>
  			  	</td>
  			  	<td>生育保险基数</td>
  			  	<td><s:textfield name="enterpriseEmployees.fertilityInsurance" value="%{#request.enterpriseEmployees.fertilityInsurance}"/></td>
  			  </tr>
  			  <tr>
  			  	<td>工伤基数</td>
  			  	<td>
  			  		<s:textfield name="enterpriseEmployees.inductrialBase" value="%{#request.enterpriseEmployees.inductrialBase}"/>
  			  	</td>
  			  	<td>基本医疗保险基数</td>
  			  	<td><s:textfield name="enterpriseEmployees.basicMedical" value="%{#request.enterpriseEmployees.basicMedical}"/></td>
  			  </tr>
  			  
  			   <tr>
  			  	<td>个税缴纳方式?</td>
  			  	<td>
  			  		<s:if test="#request.enterpriseEmployees.paymentWay==0">
  			  			<input type="radio" name="enterpriseEmployees.paymentWay" value="0" checked="checked" />个人缴纳，
						<input type="radio" name="enterpriseEmployees.paymentWay" value="1" />企业缴纳
  			  		</s:if>
  			  		<s:elseif test="#request.enterpriseEmployees.paymentWay==1">
  			  			<input type="radio" name="enterpriseEmployees.paymentWay" value="0"/>个人缴纳，
						<input type="radio" name="enterpriseEmployees.paymentWay" value="1" checked="checked"  />企业缴纳
  			  		</s:elseif>
					
  			  	</td>
  			  	<td>状&nbsp;&nbsp;&nbsp;态?</td>
  			  	<td>
  			  		<s:if test="#request.enterpriseEmployees.pseudoDelete==0">
  			  			<input type="radio" name="enterpriseEmployees.pseudoDelete" value="0" checked="checked"/>显示
						<input type="radio" name="enterpriseEmployees.pseudoDelete" value="1"/>隐藏
  			  		</s:if>
  			  		<s:elseif test="#request.enterpriseEmployees.pseudoDelete==1">
  			  			<input type="radio" name="enterpriseEmployees.pseudoDelete" value="0"/>显示
						<input type="radio" name="enterpriseEmployees.pseudoDelete" value="1" checked="checked"/>隐藏
  			  		</s:elseif>
					
  			  	</td>
  			  
  			  </tr>
  			  <tr>
  			  	<td>意外险</td>
  			  	<td style="padding-top:10px;" colspan="3"><input type="text" name="enterpriseEmployees.accident" value="${enterpriseEmployees.accident}" maxlength="30"/></td>
  			  </tr>
  		   </table>
  				 <div id="opr">
					<s:submit value=" 修   改" cssClass="oprbtn" id="ok" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value=" 取  消 " class="oprbtn" id="cancel"/>
				</div>
	  	</form>
	</fieldset>
  	</center>
  </body>
</html>
