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
	<%@ include file="/help/public_css_js.jsp"%>
  </head>
  	<script type="text/javascript">
  		$(function(){
  			$("#fullname").click(function(){
							$.dialog({
								id:'selEnterprise',
								content:'url:viewEnterpriseEmployees?enterpriseId='+$(this).prev().val(),
								width:'800px',
								height:'500px',
								title:'查看企业员工详细信息',
								lock:true,
								ok:false,
						cancel:true
						});
				});
  		
  		
  		});
  		
  		
  		
  	
  	</script>
  <body>
  	<div class="datalist">
  		<table width="95%" align="center" border="1px;" style="margin-left:20px;margin-top:30px;line-height:30px;">
  			 <tr><td colspan="4" align="center"><h2>${enterprise.fullName}</h2></td></tr>
  			  <tr>
  			  	  <td>合同编号:</td>
  			  	  <td colspan="3">
  			  	  	 ${enterprise.contatId}
  			  	  </td>
  			  </tr>
  			  <tr>
  			  	  <td>企业简称:</td>
  			  	  <td>
  			  	  	${enterprise.rferred}
  			  	  </td>
  			  	  <td>企业全称:</td>
				  <td>
				  	${enterprise.fullName}
				  </td>
  			  </tr>
  			  <tr>
  			  	<td>法人代表:</td>
				  <td>
				  	${enterprise.legalRepresentative}
				  </td>
  			  	<td>开户行:</td>
				  <td>
					${enterprise.accountLine}
				  </td>
				  
  			  </tr>
  			  <tr>
  			  	<td style="padding-top:10px;">企业银行账号 :</td>
  			  	<td style="padding-top:10px;">
					${enterprise.enterpriseBankAccount}
  			  	</td>
  			  	<td style="padding-top:10px;">地&nbsp;&nbsp;&nbsp;址 :</td>
  			  	<td style="padding-top:10px;">
					${enterprise.address}
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td>联系人 :</td>
  			  	<td>
  			  		${enterprise.contact}
  			  	</td>
  			  	<td>派遣联系人 :</td>
  			  	<td>
  			  		${enterprise.send}
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td>电 &nbsp;&nbsp;&nbsp;话</td>
  			  	<td>
  			  		${enterprise.phone}
  			  	</td>
  			  	<td>Q&nbsp;&nbsp;&nbsp;Q</td>
  			  	<td>
  			  		${enterprise.qq}
  			  	</td>
  			  </tr>
  			   <tr>
  			  	<td>传&nbsp;&nbsp;&nbsp;真:</td>
  			  	<td>
  			  		${enterprise.fax}
  			  	</td>
  			  	<td>电子邮件 :</td>
  			  	<td>
  			  		${enterprise.email}
  			  	</td>
  			  </tr>
  			   <tr>
  			  	<td>状&nbsp;&nbsp;&nbsp;态?</td>
  			  	<td>
  			  		<s:if test="#request.enterprise.status==0">
  			  			合约中
  			  		</s:if>
  			  		<s:elseif test="#request.enterprise.status==1">
  			  			暂停 
  			  		</s:elseif>
  			  	</td>
  			  	<td>备&nbsp;&nbsp;&nbsp;注:</td>
  			  	<td>
					${enterprise.note}
  			  	</td>
  			  </tr>
  			  <tr>
  			  	  <td title="详细信息"> 目前有职工:</td>
  			  	  <td colspan="3">
  			  	  	 <input type="hidden" value="${enterprise.enterpriseId}"/>
  			  	  	 <a href="javascript:void(0)" id="fullname">[${enterprise.count}]</a>人
  			  	  </td>
  			  	  
  			  </tr>
  		</table>
  	</div>
  </body>
</html>
