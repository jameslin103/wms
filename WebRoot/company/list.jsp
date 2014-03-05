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
		<meta http-equiv="pragma" content="no-cache"/> 
		<meta http-equiv="cache-control" content="no-cache"/> 
		<meta http-equiv="expires" content="0"/>  
		<%@ include file="/help/public_css_js.jsp"%>
		<link rel="stylesheet" type="text/css" href="styles/wms.css"/>
		
		<script>
			function topage(page){
				var form = document.getElementById("to_enter_form");
				form.page.value=page;
				form.submit();
		  }
		$(function(){
			$("a[name=fullname]").each(function(index,a){
					$(a).click(function(){
							$.dialog({
								id:'selEnterprise',
								content:'url:seacherEnterprise?enterprise.enterpriseId='+$(a).prev().val(),
								width:'800px',
								height:'500px',
								title:'查看企业详细信息',
								lock:true,
								ok:false,
						cancel:true
						});
						
					});
				});
				
			$("#pagesel").val("${page}");
			$("#pagesel").change(function(){
				topage($(this).val());
			});
		});
		</script>

	</head>
	<body>
	<div id="container">
			<div id="main" >
				<div id="search">
					<fieldset>
						<legend>
							<img src="images/311.gif" />&nbsp;查询条件
						</legend>
						<s:form action="toBeResponsibleEnterprise" method="post">
							<input type="hidden" name="page" id="page" value="1"/>
							企业名称：<s:textfield name="enterprise.fullName" maxlength="50"/>
						 	<input type="submit" value=" 查  询 " class="oprbtn" style="width:70px;" />
						 </s:form>
						 <a href="seacher?enterpriseId=1">jqgrid</a>
					</fieldset>
				 </div>
				 <div>
						<table class="table table-striped table-bordered">
							<thead>
								<tr bgcolor="#CEAE71">
									<th style="text-align: center;">序号</th>
									<th style="text-align: center;">企业</th>
									<th style="text-align: center;">在职员工</th>
									<th style="text-align: center;">资金往来（元）</th>
									<th style="text-align: center;">工资发放</th>
									<th style="text-align: center;">本月增减员</th>
									<th style="text-align: center;">负责人</th>
								</tr>
							</thead>
							<s:iterator value="#request.enterprises" id="enterprise" status="list">
								<tbody>
									<tr>
										<td style="text-align: center;">
											<s:property value="%{#list.index+1}" />
										</td>
										<td class="with-complement" >
											<a href="viewEnterpriseEmployees?enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/>">
											<s:property  value="%{#enterprise.fullName}" /></a>
											<span class="complement">
												  联系人:<s:property value="%{#enterprise.contact}" />
												  电&nbsp;&nbsp;话:<s:property value="%{#enterprise.phone}" />
												 <!--
												 <s:hidden value="%{#enterprise.enterpriseId}"></s:hidden>
												 <a href="javascript:void(0)" name="fullname" title="详细信息">(详细信息)</a>
												 -->
											</span>
											
										</td>
										<td style="text-align: center;">
											<a href="viewEnterpriseEmployees?enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/> "><s:property value="%{#enterprise.count}"/></a>
										</td>
										<td style="text-align: center;">
											<s:property  value="%{#enterprise.balanceDetailTotal}"/>
										</td>
										
										<td>
										<ol>
											<li>
												预算表总共:
												<a href="viewSalaryBudgetTableSummary?enterpriseId=<s:property value="%{#enterprise.enterpriseId}"/>">
													(<s:property  value="%{#enterprise.createSalaryBugetTables.size()}"/>)
												</a>张
											</li>
										
										</ol>
										</td>
										<td>
											增员<s:property value="%{#enterprise.addCount}"/>人，
											减员<s:property value="%{#enterprise.reduction}"/>人，
											参保<s:property value="%{#enterprise.whetherGinsengCount}"/>人
										</td>
										<td style="text-align: center;">
											<s:iterator value="#enterprise.user" id="user">
												<s:property value="%{#user.employee.name}"/>
											</s:iterator>
										</td>
									</tr>
								</tbody>
							</s:iterator>
						</table>
					</div>
						<s:form action="toBeResponsibleEnterprise" method="post" id="to_enter_form">
							<input name="page" type="hidden"/>
							<div class="pagination">
								<%@ include file="/share/fenye.jsp" %>
								<div style="text-align: right; color:#2E9AFE">
								  显示第：
									<select id="pagesel" style="width:60px;height:25px;">
										<s:iterator begin="1" end="#request.pageView.totalpage" var="p">
											<option value="${p}">${p}</option>
										</s:iterator>
									</select>
									页
								</div>
							</div>
					</s:form>
					</div>
				
				</div>
			<div>
	</div>

	</body>

</html>
