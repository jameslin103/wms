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
  	<script type="text/javascript">
  		$(function(){
  			$("#sumit1").click(function(){
  			
  				alert("xxx");
  				if(confirm("您确定要关闭本页吗？")){
					//window.opener=null;
					window.open('addEnterpriseEmployees','');
					window.close();
					this.close();
					var DG = frameElement.lhgDG;
					DG.cancel();  //关闭窗口
				}
  				//var list = $.dialog.list;
  				//alert(list);
				//for( var i in list ){
				 //   list[i].close();
				//}
 
  			});
  		
  		});
  	
  	</script>
  <body>
  	<div class="modal-body">
  	  <s:form action="addEnterpriseEmployees" method="post" id="addempForm">
  		<table width="90%" align="center" style="text-align:left;">
  			  <tr>
  			  	  <td>合同编号:</td>
  			  	  <td>
  			  	  	<s:textfield name="enterpriseEmployees.contractNo" cssClass="required email" id="input_e"/>
  			  	  </td>
  			  </tr>
  			  <tr>
  			  	  <td>姓&nbsp;&nbsp;&nbsp;名:</td>
  			  	  <td>
  			  	  	<s:textfield name="enterpriseEmployees.employeesName" id="employeesName"/>
  			  	  </td>
  			  	  <td>身 份 证:</td>
				  <td>
				  	<s:textfield name="enterpriseEmployees.cardNumber" id="carnumber"/>
				  </td>
  			  </tr>
  			  <tr>
  			  	<td> 籍&nbsp;&nbsp;&nbsp;贯:</td>
				  <td>
				  	<s:textfield name="enterpriseEmployees.nativePlace" />
				  </td>
  			  	<td>家庭住址:</td>
				  <td>
					<s:textfield name="enterpriseEmployees.homeAddress" />
				  </td>
				  
  			  </tr>
  			  <tr >
  			  	 <td style="padding-top:10px;">性&nbsp;&nbsp;&nbsp;别:</td>
  			  	  <td style="padding-top:10px;">
					 <input type="radio" name="enterpriseEmployees.employeesSex"value="1" checked="checked" />男
					 <input type="radio" name="enterpriseEmployees.employeesSex" value="0" />女
  			  	  </td>
  			  	  <td style="padding-top:10px;">户口性质:</td>
  			  	  <td style="padding-top:10px;">
  			  	  	<input type="radio" name="enterpriseEmployees.householdRegister"value="1" checked="checked" />非农
					<input type="radio" name="enterpriseEmployees.householdRegister"value="2" />农村
  			  	  </td>
  			  </tr>
  			  <tr>
  			  	<td style="padding-top:10px;">是否有照片?</td>
  			  	<td style="padding-top:10px;">
  			  		<input type="radio" name="enterpriseEmployees.photo" value="1"checked="checked" />有
				    <input type="radio" name="enterpriseEmployees.photo" value="0" />无
  			  	 </td>
  			  	<td style="padding-top:10px;">婚姻状况:</td>
  			  	<td style="padding-top:10px;">
  			  		<input type="radio" name="enterpriseEmployees.maritalStatus" value="1" checked="checked" />未婚，
					<input type="radio" name="enterpriseEmployees.maritalStatus" value="2" />已婚
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td style="padding-top:10px;">电&nbsp;&nbsp;&nbsp;话:</td>
  			  	<td style="padding-top:10px;">
					<s:textfield name="enterpriseEmployees.phone" />
  			  	</td>
  			  	<td style="padding-top:10px;">银行卡号:</td>
  			  	<td style="padding-top:10px;">
					<s:textfield name="enterpriseEmployees.bankCardNumber" />
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td>开户银行</td>
  			  	<td><s:textfield name="enterpriseEmployees.bank" /></td>
  			  	<td>行&nbsp;&nbsp;&nbsp;业</td>
  			  	<td><s:textfield name="enterpriseEmployees.industry" /></td>
  			  </tr>
  			  <tr>
  			  	<td>岗&nbsp;&nbsp;&nbsp;位</td>
  			  	<td><s:textfield name="enterpriseEmployees.jobs" /></td>
  			  	<td>文化程度</td>
  			  	<td><s:textfield name="enterpriseEmployees.levelEducation" /></td>
  			  </tr>
  			  <tr>
  			  	<td>合同期限-(起):</td>
  			  	<td>
				<input type="text" id="d4311"name="enterpriseEmployees.startContractDeadline"
					onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4311\')||\'2020-10-01\'}',skin:'whyGreen'})" class="Wdate" />
  			  	</td>
  			  	<td>止：</td>
  			  	<td>
					<input type="text" id="d4312" name="enterpriseEmployees.endContractDeadline"
					class="Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01',skin:'whyGreen'})"/>
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td>是否参保?</td>
  			  	<td>
  			  	 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="1" checked="checked" />是，
				 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="0" />否，
				 <input type="radio" name="enterpriseEmployees.whetherGinseng" value="2" />特殊参保(<span style="color:blue;">补贴</span>)
  			  	</td>
  			  	<td>参保类型:</td>
  			  	<td>
  			   		<input type="checkbox" name="enterpriseEmployees.sociaSecurity"	value="是" />&nbsp;医保
					<input type="checkbox" name="enterpriseEmployees.healthCare"  value="是" />&nbsp;社保
					<input type="checkbox" name="enterpriseEmployees.accumulationFund" value="是" />&nbsp;公积金
  			  	</td>
  			  </tr>
  			   <tr>
  			  	<td>参保性质</td>
  			  	<td>
					<input type="radio" name="enterpriseEmployees.ginsengProtectNature" value="1" checked="checked" />新增，
					<input type="radio"	name="enterpriseEmployees.ginsengProtectNature" value="2" />续保
					<input type="radio"	name="enterpriseEmployees.ginsengProtectNature" value="3" />无参保
  			  	</td>
  			  	<td>开始参保日期:</td>
  			  	<td style="padding-top:10px;">
					<s:textfield id="d11" name="enterpriseEmployees.cinsengDate" onclick="WdatePicker()" cssClass="Wdate" />
  			  	</td>
  			  </tr>
  			   <tr>
  			  	<td>参保基数</td>
  			  	<td>
				 	<input type="radio" name="enterpriseEmployees.base" value="0" checked="checked" />默认基数，
					<input type="radio" name="enterpriseEmployees.base" value="1" />个性设置
  			  	</td>
  			  	<td>住房公积金基数</td>
  			  	<td><s:textfield name="enterpriseEmployees.housingFund" /></td>
  			  </tr>
  			   <tr>
  			  	<td>社会保险基数</td>
  			  	<td>
  			  		<s:textfield name="enterpriseEmployees.socialInsurance" />
  			  	</td>
  			  	<td>生育保险基数</td>
  			  	<td><s:textfield name="enterpriseEmployees.fertilityInsurance" /></td>
  			  </tr>
  			  <tr>
  			  	<td>工伤基数</td>
  			  	<td>
  			  		<s:textfield name="enterpriseEmployees.inductrialBase" />
  			  	</td>
  			  	<td>基本医疗保险基数</td>
  			  	<td><s:textfield name="enterpriseEmployees.basicMedical" /></td>
  			  </tr>
  			  
  			   <tr>
  			  	<td>个税缴纳方式?</td>
  			  	<td>
					<input type="radio" name="enterpriseEmployees.paymentWay" value="0" checked="checked" />个人缴纳，
					<input type="radio" name="enterpriseEmployees.paymentWay" value="1" />企业缴纳
  			  	</td>
  			  	<td>状&nbsp;&nbsp;&nbsp;态?</td>
  			  	<td>
					<input type="radio" name="enterpriseEmployees.pseudoDelete" value="0" checked="checked"/>显示
					<input type="radio" name="enterpriseEmployees.pseudoDelete" value="1"/>隐藏
  			  	</td>
  			  
  			  </tr>
  			  <tr>
  			  	<td>服务费</td>
  			  	<td style="padding-top:10px;">
  			  		<input type="text" name="enterpriseEmployees.serviceCost" />
  			  	</td>
  			  	<td>意外险</td>
  			  	<td style="padding-top:10px;"><input type="text" name="enterpriseEmployees.accident " maxlength="30"/></td>
  			  </tr>
  			  <tr>
  			  	<td colspan="4" align="center">
  			  		<s:submit cssClass="btn btn-primary"  value="提交" id="submit" cssStyle="width:80px;"/>
  			  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			  		<input type="button" class="btn btn-primary" style="width:80px;" value="重置" id="sumit1">
  			  	</td>
  			  </tr>
  		</table>
  	</s:form>
  	</div>
  </body>
</html>
