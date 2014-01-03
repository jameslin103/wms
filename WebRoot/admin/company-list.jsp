
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="/help/public_css_js.jsp"%>
	<script >
		function topage(page){
			var form = document.getElementById("my_enterprise");
				form.page.value=page;
				form.submit();
		}
		function reset_enterprise(){
			var form=$('#add_form');
			clearForm(form);
		}
		function reset(){
			$("#add-enterprise-bnt,#edit-enterprise-bnt").on("hidden",function(){
				$("form :checkbox",this).removeAttr("checked");
				$("form",this)[0].reset();
				clearForm(this);
			});
		
		}
		
		$(function(){
		
			$("#fullname").blur(function (){
			
			var fullname=$("#fullname").val();
			if(fullname=="")
			{
				$("#errorFullName").text("企业名称必填项");
				return;
			}else{
				$("#errorFullName").text("");
				$("#errorFullName").text("*");
				
				$.getJSON("isExitFullname",{"enterprise.fullName":encodeURI(fullname,"utf-8")}).success(function(data)
				{
					if(data==1){
						$("#errorFullName").text("此企业已存在!");
					}
			   
			    });
				
			 }
				
			});	
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
		<div id="main">
				<div id="search">
					<fieldset>
						<legend>
							<img src="images/311.gif" />&nbsp;查询条件
						</legend>
						<s:form action="viewEnterprise" method="post">
							<input type="hidden" name="page" id="page" value="1"/>
							企业名称：<s:textfield name="enterprise.fullName" size="10" cssStyle="width:150px;"/>
							合同编号：<s:textfield name="enterprise.contatId" size="10" cssStyle="width:150px;"/>
							负  责  人：  <s:select list="%{#request.users}" name="user.id" label="0" listKey="id"  theme="simple"
									   listValue="employee.name"  headerKey="0" headerValue="-请选择-" cssStyle="width:90px;"/>
						 	<input type="submit" value=" 查  询 " class="oprbtn" style="width:70px;" />
						 </s:form>
					</fieldset>
				</div>
				<div id="datalist">
					<fieldset>
						<legend>
							<img src="images/311.gif" />
							<a href="#add-enterprise-bnt" data-toggle="modal" onclick="reset_enterprise()">添加新企业</a>
							--总<span style="color:red;">&nbsp;(${pageView.totalrecord})</span>家
						</legend>
					</fieldset>
						<!-- ======================================According to  Enterprise==================================== -->
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th width="5%" style="text-align: center;">
										序号
									</th>
									<th width="8%" style="text-align: center;">
										合同编号
									</th>
									<th width="30%" style="text-align: center;">
										企业
									</th>
									<th width="15%" style="text-align: center;">
										负责人
									</th>
									<th width="10%" style="text-align: center;">
										合同性质
									</th>
									<th width="20%" style="text-align: center;">
										备注
									</th>
									<th width="12%" style="text-align: center;">
										操作
									</th>
								</tr>
							</thead>
							<s:iterator value="#request.pageView.records" id="enterprise" status="list">
								<tbody>
									<tr>
										<td style="text-align: center;">
											<s:property value="%{#enterprise.enterpriseId}"/>
										</td>
										<td style="text-align: center;">
											<s:property value="%{#enterprise.contatId}"/>
										</td>
										<td class="with-complement" title="详细信息">
											<input type="hidden" value="<s:property value="%{#enterprise.enterpriseId}"/>"/>
											<a href="javascript:void(0)" name="fullname"><s:property value="%{#enterprise.fullName}" /></a>
											<span class="complement"> 
													联系人:<s:property value="%{#enterprise.contact}" />  
													电话： <s:property value="%{#enterprise.phone}" /> 
											</span>
										</td>
										<td class="with-complement" style="text-align: center;">
											<s:iterator value="%{#enterprise.user}" id="user">
													<s:property value="%{#user.employee.name}" />
											</s:iterator>
											<s:set value="%{#enterprise.enterpriseId}" var="enterpriseId"></s:set>
											<s:hidden value='%{#enterprise.id}' id="enterId"/>
											<a href="#info-for-check2" data-toggle="modal"  onclick="findEnterpriseToUser('${enterpriseId}')" class="complement" >
												<span>[增删负责人]</span>
											 </a>
										</td>
										<td style="text-align: center;">
											<s:if test="%{#enterprise.status==0}">
												合约中
											</s:if>
											<s:elseif test="%{#enterprise.status==1}">
												暂停中
											</s:elseif>
										
										</td>
										<td>${enterprise.note}</td>
										<td style="text-align: center;">
											<img src="images/037.gif" width="9" height="9" />
											<a href="#edit-enterprise-bnt"  data-toggle="modal" id="updateto" onclick="modalEnterprise('${enterpriseId}')" >[编 辑]</a>
										</td>
									</tr>
								</tbody>
							</s:iterator>
						</table>
						<s:form action="viewEnterprise" method="post" id="my_enterprise">
							<input type="hidden" name="page"/>
							<div class="pagination" style="color:#2E9AFE">
								<%@ include file="/share/fenye.jsp" %>
								<div style="text-align: right;">
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
	</div>
		<div id="add-enterprise-bnt" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					企业信息
				</h3>
			</div>
			<!-- ==================================addEnterprise====================================== -->
			
			<div class="modal-body">
				<s:form action="addEnterprise" method="post" id="add_form">
					<div class="row-fluid">
						<div class="input-container">
							<label>
								简称
							</label>
							<s:textfield name="enterprise.rferred" />
						</div>

						<div class="input-container">
							<label>
								全称
							</label>
							<s:textfield name="enterprise.fullName"  id="fullname"/>
							<span id="errorFullName" style="color: red;">*</span>
						</div>

						<div class="input-container">
							<label>
								法人代表
							</label>
							<s:textfield name="enterprise.legalRepresentative"/>
						</div>

						<div class="input-container">
							<label>
								开户行
							</label>
							<s:textfield name="enterprise.accountLine" maxlength="50"/>
						</div>

						<div class="input-container">
							<label>
								企业银行账号
							</label>
							<s:textfield name="enterprise.enterpriseBankAccount" maxlength="20"/>
						</div>

						<div class="input-container">
							<label>
								地址
							</label>
							<s:textfield name="enterprise.address" />
						</div>

						<div class="input-container">
							<label>
								联系人
							</label>
							<s:textfield name="enterprise.contact" />
						</div>
						<div class="input-container">
							<label>
								派遣联系人
							</label>
							<s:textfield name="enterprise.send" />
						</div>
						<div class="input-container">
							<label>
								电话
							</label>
							<s:textfield name="enterprise.phone" />
						</div>
						<div class="input-container">
							<label>
								QQ
							</label>
							<s:textfield name="enterprise.qq" />
						</div>
						<div class="input-container">
							<label>
								传真
							</label>
							<s:textfield name="enterprise.fax" />
						</div>

						<div class="input-container">
							<label>
								电子邮件
							</label>
							<s:textfield name="enterprise.email" />
						</div>
						<div class="input-container">
							<label>
								状态?
							</label>
							<input type="radio" name="enterprise.status" value="0"checked="checked"/>
							合约中，
							<input type="radio" name="enterprise.status" value="1"/>
							暂停
						</div>

						<div class="input-container">
							<s:submit cssClass="btn btn-primary" value="提交" />
						</div>
					</div>
				</s:form>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>
		<!-- =================================updateEnterprise====================================== -->
		<div id="edit-enterprise-bnt" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					企业信息
				</h3>
			</div>
			<div class="modal-body" id="updateEnterprise">
				<s:form action="updateEnterprise" method="post">
				<s:hidden name="enterprise.enterpriseId" value=""></s:hidden>
					<div class="row-fluid">
						<div class="input-container">
							<label>
								简称
							</label>
							<s:textfield name="enterprise.rferred"  value=""/>
						</div>

						<div class="input-container">
							<label>
								全称
							</label>
							<s:textfield name="enterprise.fullName" value=""/>
						</div>

						<div class="input-container">
							<label>
								法人代表
							</label>
							<s:textfield name="enterprise.legalRepresentative"  value="%{#enterprise.legalRepresentative}"/>
						</div>

						<div class="input-container">
							<label>
								开户行
							</label>
							<s:textfield name="enterprise.accountLine"  value="%{#enterprise.accountLine}"/>
						</div>

						<div class="input-container">
							<label>
								企业银行账号
							</label>
							<s:textfield name="enterprise.enterpriseBankAccount"  value="%{#enterprise.enterpriseBankAccount}"/>
						</div>

						<div class="input-container">
							<label>
								地址
							</label>
							<s:textfield name="enterprise.address"  value="%{#enterprise.address}"/>
						</div>

						<div class="input-container">
							<label>
								联系人
							</label>
							<s:textfield name="enterprise.contact"  value="%{#enterprise.contact}"/>
						</div>
						<div class="input-container">
							<label>
								派遣联系人
							</label>
							<s:textfield name="enterprise.send" />
						</div>
						<div class="input-container">
							<label>
								电话
							</label>
							<s:textfield name="enterprise.phone"  value="%{#enterprise.phone}"/>
						</div>
						<div class="input-container">
							<label>
								QQ
							</label>
							<s:textfield name="enterprise.qq"  value="%{#enterprise.qq}"/>
						</div>

						<div class="input-container">
							<label>
								传真
							</label>
							<s:textfield name="enterprise.fax"  value="%{#enterprise.fax}"/>
						</div>

						<div class="input-container">
							<label>
								电子邮件
							</label>
							<s:textfield name="enterprise.email"  value="%{#enterprise.email}"/>
						</div>

						<div class="input-container">
							<label>
								状态?
							</label>
							<input type="radio" name="enterprise.status" value="0" checked="checked"/>
							合约中，
							<input type="radio" name="enterprise.status" value="1"/>
							暂停
						</div>

						<div class="input-container">
							<s:submit cssClass="btn btn-primary" value="提交" />
						</div>
					</div>
				</s:form>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>
		
		<div id="info-for-check2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					增删负责人
				</h3>
			</div>
			<div class="modal-body">
				<div class="row-fluid">
					<form action="addEnterpriseToUser" method="post">
						<s:hidden name="page" value="%{#request.page}"></s:hidden>
						<s:hidden name="enterpriseId" value=""></s:hidden>
						<div class="input-container">
							<label>
								企业名称
							</label>
							<s:label value="" id="fullName"></s:label>
						</div>

						<div class="input-container">
							 <label>当前负责人</label>
				            <ul class="list-of-items-for-delete normal clearfix">
				              	
				            </ul>
						</div>
						<div class="input-container">
							<label>
								搜索并添加负责人
							</label>
							<s:select list="%{#request.users}" name="user.id" label="0" listKey="id"
							 listValue="employee.name"  headerKey="0" headerValue="-请选择-"/>
						</div>

						<div class="input-container">
							<s:submit cssClass="btn btn-primary" value="添加" />
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">
					Close
				</button>
			</div>
		</div>
	</body>

</html>
