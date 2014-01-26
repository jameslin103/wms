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
  			 <tr style="background-color:#C09853;">
  				 <td colspan="4" align="center"><h2>${enterprise.fullName}</h2></td>
  			 </tr>
  			  <tr>
  			  	  <td><b>合同编号:</b></td>
  			  	  <td colspan="3">
  			  	  	 ${enterprise.contatId}
  			  	  </td>
  			  	  
  			  </tr>
  			  
  			  <tr>
  			  	  <td><b>跟单员:</b></td>
  			  	  <td>
  			  	  	<s:iterator value="%{#request.enterprise.user}" id="user">
  			  	  		${user.employee.name}
  			  	  	</s:iterator>
  			  	  </td>
  			  	  <td>企业名称:</td>
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
  			  	<td>创建时间:</td>
  			  	<td><s:date name="%{#request.enterprise.createDate}" format="yyyy-MM-dd HH:mm:dd"/></td>
  			  	<td>更新时间:</td>
  			  	<td><s:date name="%{#request.enterprise.updateDate}" format="yyyy-MM-dd HH:mm:dd"/></td>
  			  </tr>
  			  <tr>
  			  	  <td title="详细信息"> 目前有职工:</td>
  			  	  <td colspan="3">
  			  	  	 <input type="hidden" value="${enterprise.enterpriseId}"/>
  			  	  	 <a href="javascript:void(0)" id="fullname">[${enterprise.count}]</a>人
  			  	  </td>
  			  </tr>
  		</table>
  		<table width="95%" align="center" border="1px;" style="margin-left:20px;line-height:30px;">
  			<thead>
  				<tr style="background-color:#C09853;"><th colspan="8" align="center" style="font-size:25px;">合作项目详细表</th></tr>
  				<tr>
  					<th>序号</th>
  					<th>项目名称</th>
  					<th>服务性质</th>
  					<th>服务类型</th>
  					<th>服务费</th> 
  					<th>创建时间</th>
  					<th>修改时间</th>
  					<th>注意事项</th>
  				</tr>
  			</thead>
  			<s:iterator value="#request.enterprise.enterpriseProjects" id="enterpriseProjects" status="list">
	  			<tbody>
	  				<tr>
	  				<td align="center">${list.index+1}</td>
	  				<td>${enterpriseProjects.projects}</td>
	  				<td>
	  				 <s:if test="#enterpriseProjects.serviceType==3">
	  					 ${enterpriseProjects.customType}
	  				 </s:if>
	  				 <s:if test="#enterpriseProjects.serviceType==1">
	  				 	 完全派遣
	  				 </s:if>
	  				 <s:if test="#enterpriseProjects.serviceType==2">
	  				 	转移派遣
	  				 </s:if>
	  				</td>
	  				<s:if test="#enterpriseProjects.fee==0">
	  					<td>按人头</td>
	  					<td>${enterpriseProjects.serviceHead}</td>
	  				</s:if>
	  				<s:if test="#enterpriseProjects.fee==1">
	  					<td>按比例</td>
	  					<td>${enterpriseProjects.proportion}</td>
	  				</s:if>
	  				<td><s:date name="%{#enterpriseProjects.createDate}" format="yyyy-MM-dd:HH:mm:dd"/></td>
	  				<td><s:date name="%{#enterpriseProjects.updateDate}" format="yyyy-MM-dd:HH:mm:dd"/></td>
	  				<td>${enterpriseProjects.note}</td>
	  			</tr>
	  			</tbody>
  			</s:iterator>
  		</table>
  		<table width="95%" align="center" border="1px;" style="margin-left:20px;line-height:30px;">
  			<thead>
  				<tr style="background-color:#C09853;"><th colspan="7" align="center" style="font-size:25px;">企业合同资料</th></tr>
  				<tr>
  					<th>序号</th>
  					<th>合同开始日期</th>
  					<th>合同结束日期</th>
  					<th>签订合同时间</th>
  					<th>修改合同时间</th>
  					<th>状&nbsp;&nbsp;态</th>
  					<th>备&nbsp;&nbsp;注</th>
  				</tr>
  			</thead>
  			<s:iterator value="%{#request.enterprise.enterpriseContract}" id="enterpriseContract" status="list">
	  			<tbody>
	  				<tr>
	  				<td align="center">${#list.index+1}</td>
	  				<td><s:date name="%{#enterpriseContract.startContractDate}" format="yyyy-MM-dd"/></td>
	  				<td><s:date name="%{#enterpriseContract.endContractDate}" format="yyyy-MM-dd"/></td>
	  				<td><s:date name="%{#enterpriseContract.createDate}" format="yyyy-MM-dd HH:mm:dd"/></td>
	  				<td><s:date name="%{#enterpriseContract.updateDate}" format="yyyy-MM-dd HH:mm:dd"/></td>
	  				<td>${enterpriseContract.status}</td>
	  				<td>${enterpriseContract.note}</td>
	  				
	  			</tr>
	  			</tbody>
  			</s:iterator>
  		</table>
  	</div>
  </body>
</html>
