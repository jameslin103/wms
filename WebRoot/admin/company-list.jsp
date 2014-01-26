
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
		<link rel="stylesheet" type="text/css" href="styles/wms.css"/>
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
								width:'900px',
								height:'700px',
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
			
			$("#add_en").click(function(){
				if($("#add-enterprise-bnt").is(":hidden")){
					$("#add-enterprise-bnt").show();
				}else{
					$("#add-enterprise-bnt").hide();
				}
				
			});
			$("#close,#close_1").click(function(){
				if($("#add-enterprise-bnt").is(":visible")){
					$("#add-enterprise-bnt").hide();
				}
				
			});
			
			$("a[name=project]").each(function(index,a){
				$(a).click(function(){
					if($("#add-project-bnt").is(":hidden")){
						$("#add-project-bnt").show();
						$("#en_id").val($(a).prev().val());
					}else{
						$("#add-project-bnt").hide();
					}
				
				});
			});
			$("#closeprocet").click(function(){
					if($("#add-project-bnt").is(":visible")){
						$("#add-project-bnt").hide();
					}
					
			});
			
			$("#selType").change(function()
	  		{
	  			if($(this).val()==3){
	  				if($("#type").is(":hidden")){
						$("#type").show();
					}else{
						$("#type").hide();
					}
	  			}
	  			if($(this).val()!=3){
	  				if($("#type").is(":visible")){
						$("#type").hide();
					}
	  			
	  			}
	  		
	  		});
			
			$("a[name=review]").each(function(index,a){
					$(a).click(function(){
							$.dialog({
								id:'selreview',
								content:'url:base/review_status.jsp',
								width:'500px',
								height:'200px',
								title:''+$(a).prev().val()+'-审核',
								lock:true,
								ok:function(){
									var selStat=$.dialog.list['selreview'].content.$("#status").is(':checked');
									var status;
									if(selStat==true){
										status=1;
									}else{
										status=2;
									}
									var selNote=$.dialog.list['selreview'].content.$("#note").val();
									var enterpriseId=$(a).prev().prev().val();
									var dept={
											"enterprise.enterpriseId":$(a).prev().prev().val(),
											"enterprise.note":selNote,
											"enterprise.auditStatus":selStat
										};
									
									$.ajax({
											url:'updateEnterStatus?enterprise.enterpriseId='+enterpriseId+'&enterprise.auditStatus='+status+'&enterprise.note='+selNote,
											type:'post',
											data:JSON.stringify(dept),
											contentType:'application/json',
											dataType:'html',
											success:function(isOk){
												if(isOk=="true"){
													$.dialog.alert('审核成功!',function(){
													   this.reload().time(2);
													});
												}else{
													$.dialog.alert('审核失败!',function(){
													   this.reload().time(2);
													});
												}
												
												
											}
										});
									},
						cancelValue:'关闭',
						cancel:true
						});
						
					});
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
							--总<span style="color:red;">&nbsp;(${pageView.totalrecord})</span>家
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
				<div>
						<!-- ======================================According to  Enterprise==================================== -->
					<s:form action="viewEnterprise" method="post" id="my_enterprise">
						<input type="hidden" name="page"/>
						<table class="table table-bordered">
							<thead>
								<tr  style="background-color:#C09853;">
									<th width="5%" style="text-align: center;">
										序号
									</th>
									<th width="8%" style="text-align: center;">
										合同编号
									</th>
									<th width="20%" style="text-align: center;">
										企业名称
									</th>
									<th width="10%" style="text-align: center;">
										合同性质
									</th>
									<th width="13%" style="text-align: center;">
										跟单员
									</th>
									<th width="20%" style="text-align: center;">
										审核状态
									</th>
									<th width="15%" style="text-align: center;">
										备注
									</th>
									<th width="10%" style="text-align: center;">
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
											<br/>
											<s:hidden name="" value="%{#enterprise.enterpriseId}"></s:hidden>
											<a href="javascript:void(0)" name="project">[添加项目]</a>
										</td>
										<td class="with-complement" title="详细信息">
											<input type="hidden" value="<s:property value="%{#enterprise.enterpriseId}"/>"/>
											<a href="javascript:void(0)" name="fullname"><s:property value="%{#enterprise.fullName}" /></a>
											<br/><span>创建时间:<s:date name="%{#enterprise.createDate}" format="yyyy-MM-dd:HH:mm:ss"/></span>
										</td>
										<td style="text-align: center;">
											<s:if test="%{#enterprise.status==0}">
												合约中
											</s:if>
											<s:elseif test="%{#enterprise.status==1}">
												暂停中
											</s:elseif>
										
										</td>
										<td class="with-complement" style="text-align: center;">
											<s:iterator value="%{#enterprise.user}" id="user">
													<s:property value="%{#user.employee.name}" />
											</s:iterator>
											<s:set value="%{#enterprise.enterpriseId}" var="enterpriseId"></s:set>
											<s:hidden value='%{#enterprise.id}' id="enterId"/>
											<a href="#info-for-check2" data-toggle="modal"  onclick="findEnterpriseToUser('${enterpriseId}')" class="complement" >
												[增删负责人]
											 </a>
										</td>
										<td>
											<s:if test="#enterprise.auditStatus==0">
												<s:hidden name="" value="%{#enterprise.enterpriseId}"></s:hidden>
												<input type="hidden" value="<s:property value="%{#enterprise.fullName}"/>"/>
												<a href="javascript:void(0)" name="review">待审核</a>
											</s:if>
											<s:elseif test="#enterprise.auditStatus==1">
												<span style="color:red">
													审核通过
												</span>
											</s:elseif>
											<s:elseif test="#enterprise.auditStatus==2">
												<s:hidden name="" value="%{#enterprise.enterpriseId}"></s:hidden>
												<input type="hidden" value="<s:property value="%{#enterprise.fullName}"/>"/>
												<a href="javascript:void(0)" name="review">审核不通过</a>
											</s:elseif>
											<s:else>
												
											</s:else>
											<br/><b>时间:</b><s:date name="%{#enterprise.auditDate}" format="yyyy-MM-dd HH:mm:ss"/>
											<br/><b>审核人:</b>${enterprise.audituser}
										</td>
										<td>${enterprise.note}</td>
										<td style="text-align: center;">
											<img src="images/037.gif" width="9" height="9" />
											<a href="toUpdateEnterprise?enterpriseId=${enterprise.enterpriseId}">[编 &nbsp;&nbsp;辑]</a>
										</td>
									</tr>
								</tbody>
							</s:iterator>
						</table>
					
							<!--
							<s:hidden name="enterprise.fullName" value="%{#request.enterprise.fullName}"></s:hidden>
							<s:hidden name="enterprise.contatId" value="%{#request.enterprise.contatId}"></s:hidden>
							-->
							<s:hidden name="user.id" ></s:hidden>
							
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
     <!-- =================================add-project-bnt====================================== -->
       	<div id="add-project-bnt" style="display: none;" class="white_content_min ui_title_bar">
			<div class="modal-header" style="background-color:#C09853;">
				<button type="button" style="color:#2E9AFE;" class="close" id="closeprocet" data-dismiss="modal" aria-hidden="true">
					关闭
				</button>
				<h2 id="myModalLabel" align="center">
					<span style="color:white">添加企业合作项目</span>
				</h2>
			</div>
          <form action="addEnterpriseProjects" method="post" id="add_form">
          	<s:hidden name="enterpriseId" id="en_id"></s:hidden>
            <table class="table table-bordered" >
              <tbody>
                <tr>
                  <td class="bgc">合作项目:</td>
                  <td colspan="3">
                  	<input type="text" name="enterpriseProjects.projects"/>
                  </td>
                </tr>
                <tr>
                  <td class="bgc">服务性质:</td>
                  <td>
                    <select name="enterpriseProjects.serviceType" id="selType">
                      <option value="0">-请选择-</option>
                      <option value="1">完全派遣</option>
                      <option value="2">转移派遣</option>
                      <option value="3">其它</option>
                    </select>
                  </td>
                  <td>服务备注:</td>
                  <td >
                  <input type="text" name="enterpriseProjects.customType" id="type"/>
                  </td>
                </tr>
              </tbody>
              <tbody>
                <tr>
                  <td>服务费:</td>
                  <td colspan="3">
                    <input type="radio" name="enterpriseProjects.fee" value="0" checked="checked"/>按人头，
                    <input class="span1" type="text" name="enterpriseProjects.serviceHead" maxlength="10"/>元/人
                    <input type="radio" name="enterpriseProjects.fee" value="1"/>按比例，
                    <input class="span1" type="text" name="enterpriseProjects.proportion" maxlength="10"/>%
                  </td>
                </tr>
                <tr>
                  <td>注意事项:</td>
                  <td colspan="3">
                  		<input type="text" name="enterpriseProjects.note" style="height:50px;width:200px;"/>
                  </td>
                </tr>
                 <tr>
                  <td colspan="4" style="text-align: center;">
                		<s:submit cssClass="btn btn-primary" value="新增" />
                		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                		<input type="button" class="btn btn-primary" value="取消" />
                	</td>
                </tr>
              </tbody>
            </table>
          </form>
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
