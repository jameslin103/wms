<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

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
		<script>
		
		  $(function(){
			$("#submit").click(function()
			{
				
				var name=$("#employeesName").val();
				var carnumber=$("#carnumber").val();
				if(name=="" && carnumber=="")
				{
					$("#name").text("姓名必填项!");
					$("#errorcar").text("身份证必填项!");
					return false;
				}
				if(name=="" && carnumber!="")
				{
					$("#name").text("姓名必填项!")
					$("#errorcar").text(" ");
					$("#errorcar").css(" ");
					$("#name").css("color","red");
					return false;
				}
				if(name!="" && carnumber=="")
				{
					$("#name").text(" ")
					$("#errorcar").text("身份证必填项!");
					$("#name").css(" ")
					$("#errorcar").css("color","red")
					return false;
				}
			
			});
		
		});
		$(document).ready(function(){
			$("#add-employees-bnt").on("hidden",function(){
				clearForm(this);
			});
			
			$("#addEmployees").click(function(){
				$.dialog({
					id:"enterpriseEmployees",
					content:"url:toAddEmployees",
					title:"${session.enterprise.fullName}-(添加企业员工信息)",
					width:"900px",
					height:"500px",
					max: false,
					name:"提交",
					lock:true,
					ok:function(){
						var selCust=$.dialog.list["addEmployees"].content.enterpriseEmployees;
						alert(selCust);
						//$("#custid").val(selCust[0]);
						//$("#custname").val(selCust[1]);
					},
					cancelVal: '关闭',
					
				 });
			});
			
			
			$("a[name=name]").each(function(index,a){
					$(a).click(function(){
							$.dialog({
								id:'selEmployees',
								content:'url:selectEnterpriseEmployeesWage?employeesId='+$(a).prev().val(),
								width:'1200px',
								height:'550px',
								title:''+$(a).prev().prev().val()+'-详细信息',
								lock:true,
								ok:false,
						cancel:true
						});
						
					});
				});
			
			
		});
		
	
		</script>
	</head>
	<body>
		<div id="container">
			<div id="header">
				<div id="sub-header" class="clearfix">
					<h2>
						<s:property value="%{#session.enterprise.fullName}" />
					</h2>
				</div>
			</div>
			<div id="main">
				<div class="row-fluid">
					<div id="center-pane">
						<ul class="nav nav-tabs">
							<li >
								<a href="viewEnterpriseDetailed" >
									综合 
								</a>
							</li>
							<li class="active">
								<a href="viewEnterpriseEmployees"  >
									员工档案
								</a>
							</li>
							<li >
								<a href="viewSalaryBudgetTableSummary" >
									工资预算表
								</a>
						   </li>
						</ul>
						<ul class="normal action-container clearfix">
							<li>
								新员工档案：
							</li>
							<li>
								<a href="batchExcelDataEmployee">批量添加员工</a>，
							</li>
							<li>
								<a href="toAddEmployees" >单个添加员工</a>
							</li>
							<li>
								&nbsp;/&nbsp;
							</li>
							<li>
								查看：
							</li>
							<li>
								<a href="ginsengInsurance?insurance=1">参保</a>，
							</li>
							<li>
								<a href="uninsuredInsurance?insurance=0">未参保</a>，
							</li>
							<li>
								<a href="departureEmployees?departure=1">离职员工</a>，
							</li>
							<li>
								<a href="hideEmployees?pseudoDelete=1">隐藏信息</a>
							</li>
							<li class="right">
								<a href="exportEmployeesExcel" class="btn btn-primary">下载全体在职员工信息</a>
							</li>
							<li class="right">
								<s:form cssClass="navbar-form pull-left" action="fildAllEnterpriseEmployees" method="post">
									<input type="text" name="employessName" placeholder="输入姓名"/>
									<input type="checkbox" name="all" value="1"/>&nbsp;全站
                					<s:submit type="submit" cssClass="btn" value="搜索" />
								</s:form>
							</li>
						</ul>
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th rowspan="2" width="">
										序
									</th>
									<th rowspan="2" width="">
										&nbsp;&nbsp;全选<br/>
										<input type="button" id="delete" value="删除	"
										style="background-color:transparent; border:0px; color:#2E9AFE"/><br/>
										&nbsp;&nbsp;<input type="checkbox" id="all_box"/>
									</th>
									<th rowspan="2" width="">
										姓名
									</th>
									<th rowspan="2" width="">
										性别
									</th>
									<th rowspan="2" width="">
										户口
									</th>
									<th rowspan="2" width="">
										婚姻
									</th>
									<th rowspan="2" width="">
										照片
									</th>
									<th rowspan="2" width="">
										身份证
									</th>
									<th rowspan="2" width="">
										电话
									</th>
									<th rowspan="2" width="">
										服务费
										<br/>
										（元）
									</th>
									<th colspan="2" width="">
										参保
									</th>
									<th rowspan="2" width="">
										哪月起
										<br/>
										参保
									</th>
									<th rowspan="2" width="">
										五险一金基数设置
									</th>
									<th rowspan="2" width="">
										个税缴纳
									</th>
									<th rowspan="2" width="">
										操作
									</th>
								</tr>
								<tr>
									<th>
										是否
									</th>
									<th>
										性质
									</th>
								</tr>

							</thead>
							<s:iterator value="#request.pageView.records" id="emp" status="list">
							<tbody>
									<tr>
										<td>
											<s:property value="#list.index+1"/>
										</td>
										<td>
											&nbsp;&nbsp;<input type="checkbox" name="enterpriseEmployees.employeesId" value="<s:property value="%{#emp.employeesId}" />"/>
										</td>
										<td>
											<s:hidden value="%{#emp.employeesId}" name="employeesId" />
											<s:property value='<s:hidden value="{#emp.employeesId}"/>' />
												<s:hidden value="%{#emp.employeesName}" name="empname"/>
												<s:hidden value="%{#emp.employeesId}" name="employeesId"/>
												<a href="javascript:void(0)" name="name">
													<s:property value="%{#emp.employeesName}"/> 
												</a>
										</td>
										<td>
											<s:if test="#emp.employeesSex==1">
												<span>男</span>
											</s:if>
											<s:elseif test="#emp.employeesSex==0">
												<span>女</span>
											</s:elseif>
											<s:else>
												<s:property value="%{#emp.employeesSex}" />
											</s:else>
										</td>
										<td>
											<s:if test="#emp.householdRegister==1">
												<span>非农</span>
											</s:if>
											<s:elseif test="#emp.householdRegister==2">
												<span>农村</span>
											</s:elseif>
											<s:else>
											</s:else>
										</td>
										<td>
											<s:if test="#emp.maritalStatus==1">
												<span>未婚</span>
											</s:if>
											<s:elseif test="#emp.maritalStatus==2">
												<span>已婚</span>
											</s:elseif>
											<s:else>
												
											</s:else>
										</td>
										<td>
											<s:if test="#emp.photo==1">
												<span>有</span>
											</s:if>
											<s:elseif test="#emp.photo==0">
												<span>无</span>
											</s:elseif>
											<s:else>
												
											</s:else>
										</td>
										<td>
											<s:property value="%{#emp.cardNumber}" />
										</td>
										<td>
											<s:property value="%{#emp.phone}" />
										</td>
										<td>
											<s:property value="%{#emp.serviceCost}" />
										</td>

										<td>
											<s:if test="#emp.whetherGinseng==1">
												<span>是</span>
											</s:if>
											<s:elseif test="#emp.whetherGinseng==0">
												<span>否</span>
											</s:elseif>
											<s:else>
												
											</s:else>
										</td>
										<td>
											<s:if test="#emp.ginsengProtectNature==1">
													<span>新增</span>
											</s:if>
											<s:elseif test="#emp.ginsengProtectNature==2">
													<span>续保</span>
											</s:elseif>
											<s:elseif test="#emp.ginsengProtectNature==3">
													<span>无参保</span>
											</s:elseif>
											<s:else>
											
											</s:else>
										</td>
										<td>
											<s:date name="%{#emp.cinsengDate}" format="yyyy年MM月dd"/>
										</td>
										<td>
											<s:if test="#emp.base==0">
						                		默认
						                	</s:if>
											<s:elseif test="#emp.base==1">
						                		个性设置
						                	</s:elseif>
						                	<s:else>
						                	</s:else>
										</td>
										<td>
											<s:if test="#emp.paymentWay==0">
												<span>个人缴纳</span>
											</s:if>
											<s:elseif test="#emp.paymentWay==1">
												<span>企业缴纳</span>
											</s:elseif>
											<s:else>
												<s:property value="%{#emp.paymentWay}" />
											</s:else>
											
										</td>
										<td>
											<a href="toUpdateEnterpriseEmployees?employeesId=${emp.employeesId}&page=${page}">修改</a>
										</td>
									</tr>
								</tbody>
							</s:iterator>
						</table>
						<s:form action="viewEnterpriseEmployees" method="post" id="myform">
						<s:hidden name="page"></s:hidden>
						<div class="pagination">
							<%@ include file="/share/fenye.jsp" %>
						</div>
						</s:form>
					</div>
				</div>
			</div>
		</div>
		<!-- ================================== AddEnterpriseEmpoloyess ===============================-->
		<div id="add-employees-bnt" class="modal hide fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					员工信息
				</h3>
			</div>
			<div class="modal-body">
				<s:form action="addEnterpriseEmployees" method="post" id="loginForm">
					<div class="row-fluid">
						<div class="input-container">
							<label>
								合同编号
							</label>
							<s:textfield name="enterpriseEmployees.contractNo" cssClass="required email" id="input_e"/>

						</div>

						<div class="input-container">
							<label>
								姓名
							</label>
							<s:textfield name="enterpriseEmployees.employeesName" id="employeesName"/>
							<span style="color:red;">*</span><span style="color:red;" id="errorname"></span>
						</div>

						<div class="input-container">
							<label>
								身份证
							</label>
							<s:textfield name="enterpriseEmployees.cardNumber" id="carnumber"/>
							<span style="color:red;">*</span><span style="color:red;" id="errorcar"></span>
						</div>

						<div class="input-container">
							<label>
								性别
							</label>
							<input type="radio" name="enterpriseEmployees.employeesSex"
								value="1" checked="checked" />
							男
							<input type="radio" name="enterpriseEmployees.employeesSex"
								value="0" />
							女
						</div>

						<div class="input-container">
							<label>
								户口性质
							</label>
							<input type="radio" name="enterpriseEmployees.householdRegister"
								value="1" checked="checked" />
							非农
							<input type="radio" name="enterpriseEmployees.householdRegister"
								value="2" />
							农村
						</div>

						<div class="input-container">
							<label>
								是否有照片？
							</label>
							<input type="radio" name="enterpriseEmployees.photo" value="1"
								checked="checked" />
							有
							<input type="radio" name="enterpriseEmployees.photo" value="0" />
							无
						</div>

						<div class="input-container">
							<label>
								电话
							</label>
							<s:textfield name="enterpriseEmployees.phone" />
						</div>

						<div class="input-container">
							<label>
								家庭住址
							</label>
							<s:textfield name="enterpriseEmployees.homeAddress" />
						</div>

						<div class="input-container">
							<label>
								银行卡号
							</label>
							<s:textfield name="enterpriseEmployees.bankCardNumber" />
						</div>

						<div class="input-container">
							<label>
								开户银行
							</label>
							<s:textfield name="enterpriseEmployees.bank" />
						</div>

						<div class="input-container">
							<label>
								籍贯
							</label>
							<s:textfield name="enterpriseEmployees.nativePlace" />
						</div>

						<div class="input-container">
							<label>
								行业
							</label>
							<s:textfield name="enterpriseEmployees.industry" />
						</div>

						<div class="input-container">
							<label>
								岗位
							</label>
							<s:textfield name="enterpriseEmployees.jobs" />
						</div>

						<div class="input-container">
							<label>
								婚姻状况
							</label>
							<input type="radio" name="enterpriseEmployees.maritalStatus" value="1" checked="checked" />
							未婚，
							<input type="radio" name="enterpriseEmployees.maritalStatus" value="2" />
							已婚
						</div>

						<div class="input-container">
							<label>
								文化程度
							</label>
							<s:textfield name="enterpriseEmployees.levelEducation" />
						</div>

						<div class="input-container">
							<label>
								合同期限
							</label>
							起：
							<input type="text" id="d4311"
								name="enterpriseEmployees.startContractDeadline"
								 onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4311\')||\'2020-10-01\'}',skin:'whyGreen'})" class="Wdate" />
						</div>

						<div class="input-container">
							止：
							<input type="text" id="d4312" name="enterpriseEmployees.endContractDeadline"
								 class="Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01',skin:'whyGreen'})"/>
						</div>

						<div class="input-container">
							<label>
								是否参保?
							</label>
							<input type="radio" name="enterpriseEmployees.whetherGinseng" value="0" checked="checked"/>
							否，
							<input type="radio" name="enterpriseEmployees.whetherGinseng" value="1"/>
							是，
							<input type="radio" name="enterpriseEmployees.whetherGinseng" value="2"/>
							特殊参保(<span style="color:blue;">补贴</span>)
						</div>

						<div class="input-container">
							<label>
								参保类型
							</label>
							<input type="checkbox" name="enterpriseEmployees.sociaSecurity"	value="是" />
							医保
							<input type="checkbox" name="enterpriseEmployees.healthCare"  value="是" />
							社保
							<input type="checkbox" name="enterpriseEmployees.accumulationFund" value="是" />
							公积金
						</div>

						<div class="input-container">
							<label>
								参保性质
							</label>
							<input type="radio" 	name="enterpriseEmployees.ginsengProtectNature" value="1"  />
							新增，
							<input type="radio"	name="enterpriseEmployees.ginsengProtectNature" value="2" />
							续保
							<input type="radio"	name="enterpriseEmployees.ginsengProtectNature" value="3" checked="checked"/>
							无参保
						</div>

						<div class="input-container">
							<label>
								开始参保日期:
							</label>
							<s:textfield id="d11" name="enterpriseEmployees.cinsengDate" onclick="WdatePicker()" cssClass="Wdate" />
						</div>

						<div class="input-container">
							<label>
								参保基数
							</label>
							<input type="radio" name="enterpriseEmployees.base" value="0"
								checked="checked" />
							默认基数，
							<input type="radio" name="enterpriseEmployees.base" value="1" />
							个性设置
						</div>

						<div class="input-container">
							<label>
								社会保险基数
							</label>
							<s:textfield name="enterpriseEmployees.socialInsurance" />
						</div>
						<div class="input-container">
							<label>
								生育保险基数
							</label>
							<s:textfield name="enterpriseEmployees.fertilityInsurance" />
						</div>
						<div class="input-container">
							<label>
								工伤基数
							</label>
							<s:textfield name="enterpriseEmployees.inductrialBase" />
						</div>
						<div class="input-container">
							<label>
								基本医疗保险基数
							</label>
							<s:textfield name="enterpriseEmployees.basicMedical" />
						</div>
						<div class="input-container">
							<label>
								住房公积金基数
							</label>
							<s:textfield name="enterpriseEmployees.housingFund" />
						</div>
						<div class="input-container">
							<label>
								大病统筹
							</label>
							<s:textfield name="enterpriseEmployees.seriousDisease" />
						</div>
						<div class="input-container">
							<label>
								意外险
							</label>
							<input type="text" name="enterpriseEmployees.accident" maxlength="30"/>
						</div>
						<div class="input-container">
							<label>
								个税缴纳方式?
							</label>
							<input type="radio" name="enterpriseEmployees.paymentWay" value="0" checked="checked" />
							个人缴纳，
							<input type="radio" name="enterpriseEmployees.paymentWay" value="1" />
							企业缴纳
						</div>

						<div class="input-container">
							<label>
								状态?
							</label>
							<input type="radio" name="enterpriseEmployees.pseudoDelete" value="0" checked="checked"/>
							显示
							<input type="radio" name="enterpriseEmployees.pseudoDelete" value="1"/>
							隐藏
						</div>

						<div class="input-container">
							<label>
								服务费
							</label>
							<s:textfield name="enterpriseEmployees.serviceCost" />
						</div>
						<div class="input-container" >
							<s:submit cssClass="btn btn-primary" value="提交" id="submit"/>
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
	
	<!-- ================================== updateEnterpriseEmpoloyess ===============================-->
		<div id="edit-employees-bnt" class="modal hide fade" tabindex="-1"	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					员工信息
				</h3>
			</div>
			<div class="modal-body">
				<s:form action="updateEnterpriseEmployees" method="post">
					<input type="hidden" name="enterpriseEmployees.employeesId"/>
					<input type="hidden" name="page" value="${page}"/>
					<input type="hidden" name="employeesContract.contractid"/>
					<div class="row-fluid">
						<div class="input-container">
							<label>
								合同编号
							</label>
							<s:textfield name="enterpriseEmployees.contractNo" />

						</div>

						<div class="input-container">
							<label>
								姓名
							</label>
							<s:textfield name="enterpriseEmployees.employeesName" maxlength="19"/>
						</div>

						<div class="input-container">
							<label>
								身份证
							</label>
							<s:textfield name="enterpriseEmployees.cardNumber" maxlength="19"/>
						</div>

						<div class="input-container">
							<label>
								性别
							</label>
							<input type="radio" name="enterpriseEmployees.employeesSex"	value="1" checked="checked"/>
							男
							<input type="radio" name="enterpriseEmployees.employeesSex"	value="0" />
							女
						</div>

						<div class="input-container">
							<label>
								户口性质
							</label>
							<input type="radio" name="enterpriseEmployees.householdRegister" value="1" checked="checked" />
							非农
							<input type="radio" name="enterpriseEmployees.householdRegister" value="0" />
							农村
						</div>

						<div class="input-container">
							<label>
								是否有照片？
							</label>
							<input type="radio" name="enterpriseEmployees.photo" value="1" checked="checked" />
							无
							<input type="radio" name="enterpriseEmployees.photo" value="0" />
							有
						</div>

						<div class="input-container">
							<label>
								电话
							</label>
							<s:textfield name="enterpriseEmployees.phone" maxlength="13"/>
						</div>

						<div class="input-container">
							<label>
								家庭住址
							</label>
							<s:textfield name="enterpriseEmployees.homeAddress" />
						</div>

						<div class="input-container">
							<label>
								银行卡号
							</label>
							<s:textfield name="enterpriseEmployees.bankCardNumber" maxlength="20"/>
						</div>

						<div class="input-container">
							<label>
								开户银行
							</label>
							<s:textfield name="enterpriseEmployees.bank" maxlength="30"/>
						</div>

						<div class="input-container">
							<label>
								籍贯
							</label>
							<s:textfield name="enterpriseEmployees.nativePlace" maxlength="20"/>
						</div>

						<div class="input-container">
							<label>
								行业
							</label>
							<s:textfield name="enterpriseEmployees.industry" maxlength="19"/>
						</div>

						<div class="input-container">
							<label>
								岗位
							</label>
							<s:textfield name="enterpriseEmployees.jobs" maxlength="19"/>
						</div>

						<div class="input-container">
							<label>
								婚姻状况
							</label>
							<input type="radio" name="enterpriseEmployees.maritalStatus" value="1" checked="checked"/>
							未婚，
							<input type="radio" name="enterpriseEmployees.maritalStatus"value="2" />
							已婚
						</div>

						<div class="input-container">
							<label>
								文化程度
							</label>
							<s:textfield name="enterpriseEmployees.levelEducation" maxlength="19"/>
							
						</div>

						<div class="input-container">
							<label>
								合同期限
							</label>
							起：
							<input type="text" id="d11"
								name="employeesContract.contractStatrDate" onclick="WdatePicker()" class="Wdate" />
						</div>

						<div class="input-container">
							止：
							<input type="text" id="d11"
								name="employeesContract.contractEndDate" onclick="WdatePicker()" class="Wdate" />
						</div>

						<div class="input-container">
							<label>
								是否参保?
							</label>
							<input type="radio" name="enterpriseEmployees.whetherGinseng" value="0" checked="checked"/>
							否
							<input type="radio" name="enterpriseEmployees.whetherGinseng" value="1"  />
							是，
							<input type="radio" name="enterpriseEmployees.whetherGinseng" value="2" />
							 特殊参保(<span style="color:blue;">补贴</span>)
						</div>

						<div class="input-container">
							<label>	参保类型	</label>
							<input type="checkbox" name="enterpriseEmployees.sociaSecurity"	value="是" />
							医保
							<input type="checkbox" name="enterpriseEmployees.healthCare" value="是" />
							社保
							<input type="checkbox" name="enterpriseEmployees.accumulationFund" value="是" />
							公积金
						</div>

						<div class="input-container">
							<label>
								参保性质
							</label>
							<input type="radio"
								name="enterpriseEmployees.ginsengProtectNature" value="1" checked="checked"/>
							新增，
							<input type="radio"	name="enterpriseEmployees.ginsengProtectNature" value="2" />
							续保
							<input type="radio"	name="enterpriseEmployees.ginsengProtectNature" value="3" />
							无参保
						</div>

						<div class="input-container">
							<label>
								开始参保日期:
							</label>
							<input id="d11" type="text" name="enterpriseEmployees.cinsengDate" onclick="WdatePicker()" class="Wdate" />
						</div>

						<div class="input-container">
							<label>
								参保基数
							</label>
							<input type="radio" name="enterpriseEmployees.base" value="0" checked="checked" id="base"/>
							默认基数，
							<input type="radio" name="enterpriseEmployees.base" value="1" id="base"/>
							个性设置
						</div>

						<div class="input-container">
							<label>
								社会保险基数
							</label>
							<s:textfield name="enterpriseEmployees.socialInsurance" />
						</div>
						<div class="input-container">
							<label>
								生育保险基数
							</label>
							<s:textfield name="enterpriseEmployees.fertilityInsurance" />
						</div>
						<div class="input-container">
							<label>
								工伤基数
							</label>
							<s:textfield name="enterpriseEmployees.inductrialBase" />
						</div>
						<div class="input-container">
							<label>
								基本医疗保险基数
							</label>
							<s:textfield name="enterpriseEmployees.basicMedical" />
						</div>
						<div class="input-container">
							<label>
								住房公积金基数
							</label>
							<s:textfield name="enterpriseEmployees.housingFund" />
						</div>
						<div class="input-container">
							<label>
								大病统筹
							</label>
							<s:textfield name="enterpriseEmployees.seriousDisease" />
						</div>
						<div class="input-container">
							<label>
								意外险
							</label>
							<input type="text" name="enterpriseEmployees.accident" maxlength="30"/>
						</div>
						<div class="input-container">
							<label>
								个税缴纳方式?
							</label>
							<input type="radio" name="enterpriseEmployees.paymentWay" value="0" checked="checked"/>
							个人缴纳，
							<input type="radio" name="enterpriseEmployees.paymentWay"value="1" />
							企业缴纳
						</div>

						<div class="input-container">
							<label>
								状态?
							</label>
							<input type="radio" name="enterpriseEmployees.pseudoDelete" value="0" checked="checked"/>
							显示
							<input type="radio" name="enterpriseEmployees.pseudoDelete" value="1"/>
							隐藏
						</div>

						<div class="input-container">
							<label>
								服务费
							</label>
							<input type="text" name="enterpriseEmployees.serviceCost" />
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
	</body>

</html>
