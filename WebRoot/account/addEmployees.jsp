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
  	legend{ text-align:center; width:40%\9} 
  </style>
  	<script type="text/javascript">
  		$(function(){
				$("#cancel").click(function(){
					history.go(-1);
				});
				
		/**$("#employeesName").blur(validateEmployeesName);
		$("#carnumber").blur(validateCarnumber);
		$("#addentemp").submit(function(){
			var t=validateCarnumber();
			var b=validateEmployeesName();
			return (b && t);
			
  		});
  	  function validateEmployeesName(){
		var b=true;
		if($("#employeesName").val()==""){
			$("#namemsg").html("用户名不能为空");
			$("#namemsg").addClass("input_error");
			return false;
		}else{
			$.ajax({
				  type: "get",
				  url: "isExistEmployees?enterpriseEmployees.cardNumber"+$("#carnumber").val(),
				  async:false,
				  success:function(r){
						if(r==true){
							$("#namemsg").html("身份证号码已经存在");
							$("#namemsg").addClass("input_error");
							b= false;
						}else{
							$("#namemsg").html("&nbsp;");
							$("#namemsg").addClass("input_ok");
						}
				}
		   });
		   
		}
		return b;
	}
	
	function validateCarnumber(){
		if($("#carnumber").val()==""){
			$("#carnumber").html("身份证号码不能为空");
			$("#carnumbermsg").addClass("input_error");
			return false;
		}
		return true;
	}**/
	
	jQuery.validator.addMethod("isTelPhone",function(value,element){
		//var length=value.length;
		var telphone=/^1[3|4|5|8]\d{9}$/;
		return this.optional(element) || telphone.test(value);
	},"输入的手机号不合法");
	
	$("#addentemp").validate({
		/**rules:{
			employeesName:{
				required:true,
				maxlength:15
			},
			cardNumber:{
				//remote:"emp/empnoexist"
				required:true,
				maxlength:18
			},
			phone:{
				isTelPhone:true
			}
		},**/
		messages:{
			employeesName:{
				required:"员工姓名不能为空",
				max:$.format("员工姓名长度大于{0}位")
			},
			cardNumber:{
				required:"身份证号不能为空",
				max:$.format("身份证号码至少{0}位")
			}
		}
	});
	
  });
  	</script>
  	
  <body style="background-color:#F1EEEE;">
  	<br/>
  	<center>
		<fieldset style="border:2px groove #F0F0F0; width:1100px;">
			<legend style="text-align:center;">
				<span style="font-size:30px;" ><s:property value="%{#session.enterprise.fullName}" /></span>
			</legend>
			 <h3>添加企业员工信息</h3>
	  	  <s:form action="addEnterpriseEmployees" method="post" id="addentemp">
	  		<table width="900" style="text-align:left;">
  			  <tr>
  			  	  <td>合同编号:</td>
  			  	  <td>
  			  	  	<s:textfield name="enterpriseEmployees.contractNo"  maxlength="25" id="input_e"/>
  			  	  </td>
  			  </tr>
  			  <tr>
  			  	  <td>姓&nbsp;&nbsp;&nbsp;名:</td>
  			  	  <td>
  			  	  	<s:textfield name="enterpriseEmployees.employeesName" cssClass="required employeesName" id="employeesName" maxlength="20"/>
  			  	  	<s:fielderror fieldName="employeesName"></s:fielderror>
  			  	  </td>
  			  	  <td>身 份 证:</td>
				  <td>
				  	<s:textfield name="enterpriseEmployees.cardNumber" id="carnumber" cssClass="required cardNumber" maxlength="19" />
				  	
				  </td>
  			  </tr>
  			  <tr>
  			  	<td> 籍&nbsp;&nbsp;&nbsp;贯:</td>
				  <td>
				  	<s:textfield name="enterpriseEmployees.nativePlace" maxlength="10"/>
				  </td>
  			  	<td>家庭住址:</td>
				  <td>
					<s:textfield name="enterpriseEmployees.homeAddress" maxlength="50"/>
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
					<s:textfield name="enterpriseEmployees.phone" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"/>
  			  	</td>
  			  	<td style="padding-top:10px;">银行卡号:</td>
  			  	<td style="padding-top:10px;">
					<s:textfield name="enterpriseEmployees.bankCardNumber" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"/>
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td>开户银行</td>
  			  	<td><s:textfield name="enterpriseEmployees.bank" maxlength="30"/></td>
  			  	<td>行&nbsp;&nbsp;&nbsp;业</td>
  			  	<td><s:textfield name="enterpriseEmployees.industry" maxlength="20"/></td>
  			  </tr>
  			  <tr>
  			  	<td>岗&nbsp;&nbsp;&nbsp;位</td>
  			  	<td><s:textfield name="enterpriseEmployees.jobs" maxlength="20"/></td>
  			  	<td>文化程度</td>
  			  	<td><s:textfield name="enterpriseEmployees.levelEducation" maxlength="15"/></td>
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
					<input type="radio"	name="enterpriseEmployees.ginsengProtectNature" value="0" />无参保
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
  			  	<td><s:textfield name="enterpriseEmployees.housingFund" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"/></td>
  			  </tr>
  			   <tr>
  			  	<td>社会保险基数</td>
  			  	<td>
  			  		<s:textfield name="enterpriseEmployees.socialInsurance" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"/>
  			  	</td>
  			  	<td>生育保险基数</td>
  			  	<td><s:textfield name="enterpriseEmployees.fertilityInsurance" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')" /></td>
  			  </tr>
  			  <tr>
  			  	<td>工伤基数</td>
  			  	<td>
  			  		<s:textfield name="enterpriseEmployees.inductrialBase" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')" />
  			  	</td>
  			  	<td>基本医疗保险基数</td>
  			  	<td><s:textfield name="enterpriseEmployees.basicMedical" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')" /></td>
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
  			  	<td>意外险</td>
  			  	<td style="padding-top:10px;" colspan="3">
  			  		<input type="text" name="enterpriseEmployees.accident " maxlength="30" onKeyUp="value=value.replace(/[^\d]/g,'') "/>
  			  	</td>
  			  </tr>
  		</table>
  			 <div id="opr">
					<input type="submit" value=" 新   增" class="oprbtn" id="ok" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value=" 取  消 " class="oprbtn" id="cancel"/>
			</div>
  	</s:form>
  	</fieldset>
  	</center>
  </body>
</html>
