<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<%@ include file="/help/public_css_js.jsp"%>
		<script type="text/javascript">
			function topage(page){
				var form = document.getElementById("myform");
				form.page.value=page;
				form.submit();
			}
			$(function (){
					$("a[name=ba]").each(function(index,a){
						$(a).click(function(){
							//alert($(a).parent().prev().prev().prev().prev().text());
							//var cc=$(a).parent().prev().prev().prev().prev().text();
							$.dialog({
								id:'ba',
								content:'<table align="center" border="1px;" width="350px;">'+
										'<tr><td>实收款项:</td><td><input type="text" id="shishou" /></td></tr>'+
										'<tr><td>工&nbsp;&nbsp;资</td><td><input type="text"  id="wage"/></td></tr>'+
										'<tr><td>服&nbsp;务&nbsp;费</td><td><input type="text"  id="service"/></td></tr>'+
										'<tr><td>五险一金</td><td><input type="text"  id="five"/></td></tr>'+
										'</table>',
								width:'500px',
								height:'300px',
								title:'分配余额',
								lock:true,
								max: false,
    							min: false,
								ok:function(){
									var shishou=$("#shishou").val();
									var wage=$("#wage").val();
									var service=$("#service").val();
									var five=$("#five").val();
									
									$.ajax({
											url:'addBalanceDetail?balanceDetail.detailId='+$(a).prev().val()+'&balanceDetail.receivedFunds='+shishou+'&balanceDetail.wages='+wage+
												'&balanceDetail.serviceWith='+service+'&balanceDetail.fiveFund='+five,
											type:'post',
											//data:JSON.stringify(dept),
											contentType:'application/json;charset=UTF-8"',
											dataType:'html',
											success:function(isOk)
											{
												$.dialog.alert('修改成功!',function(){
													 window.self.location="viewBalanceDetail?page="+${page};  
													 //this.reload().time(2);
													   
												});
												
											}
										});
									},
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
						<ul class="nav nav-tabs" >
							<li >
								<a href="viewEnterpriseDetailed" >
									综合 
								</a>
							</li>
							<li>
								<a href="viewCompanyListWithSaraly"  >
									工资
								</a>
							</li>
							<li class="active">
							
								<a href="viewBalanceDetail" >
									资金往来明细
								</a>
						  </li>
						</ul>
					<s:form action="viewBalanceDetail" method="post" id="myform">
					<s:hidden name="page"/>
						<table class="table table-striped table-bordered" >
							<thead>
								<tr>
									<th rowspan="2">
										序号
									</th>
									<th rowspan="2">
										月份
									</th>
									<th rowspan="2">
										期初余额（元）
									</th>
									<th colspan="4" class="center">
										应收款项（元）
									</th>
									<th rowspan="2">
										实收款项（元）
									</th>
									<th rowspan="2">
										期末余额（元）
									</th>
									<th colspan="3" class="center">
										余额分配（元）
									</th>
									<th rowspan="2">
										操作
									</th>
								</tr>
								<tr>
									<th>
										开票总额
									</th>
									<th>
										工资总额
									</th>
									<th>
										服务费总额
									</th>
									<th>
										五险一金
									</th>
									<th>
										工资
									</th>
									<th>
										服务费
									</th>
									<th>
										五险一金
									</th>
								</tr>
							</thead>
							<s:iterator value="%{#request.pageView.records}" id="balanceDetail" status="list">
							<tbody>
								<tr>
									<td rowspan="2">
										<s:property value="#list.index+1"/>
									</td>
									<td>
										<s:date name="%{#balanceDetail.yearMonth}" format="yyyy年MM月"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.balance}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.ballotsToal}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.wagesToal}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.serviceToal}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.receivableFiveFund}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.receivedFunds}"/>
									</td>
									<td>
										<s:property value="%{#balanceDetail.endingBalance}"/>
									</td>
									<td>
										${balanceDetail.wages}
									</td>
									<td>
										<s:property value="%{#balanceDetail.serviceWith}"/>
									</td>
									<td>
										${balanceDetail.fiveFund}
									</td>
									<td rowspan="2">
										<input type="hidden" value="${balanceDetail.detailId}"/>
										<a href="javascript:void(0)" name="ba">填写</a>
									</td>
								</tr>
								<tr>
									<td colspan="7">
										<span style="color:red;">预算表名称:
											(
												<span style="color:blue;">
													<s:if test="#balanceDetail.createSalaryBudgetTable.name.length()>10">
														<s:property value="#balanceDetail.createSalaryBudgetTable.name.substring(0,10)+'...'"/>
													</s:if>
													<s:else>
														<s:property value="#balanceDetail.createSalaryBudgetTable.name"/>
													</s:else>
													
												</span>
												)
										 制作人：(<s:property value="#balanceDetail.createSalaryBudgetTable.user.username"/>
										 制作时间:<s:date name="#balanceDetail.createSalaryBudgetTable.createDate" format="yyyy年MM月dd日"/>
										)</span>
									</td>
									<td colspan="11" class="align-right">
										备注:（<s:property value="#balanceDetail.userIusse"/>，
										<s:date name="%{#balanceDetail.createDate}" format="yyyy年MM月dd日"/>，
										<s:property value="%{#balanceDetail.note}"/>）
									</td>
								</tr>	
							</tbody>
							</s:iterator>
						</table>
						
						<div class="pagination">
							<ul>
								<%@ include file="/share/fenye.jsp" %>
							</ul>
						</div>
						</s:form>
					</div>
				</div>
			</div>

			<div id="footer"></div>
		</div>
		<!--================================================= add  addBalanceDetail=================================================-->
		<div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
				<h3 id="myModalLabel">
					实收款项与余额分配
				</h3>
			</div>

			<div class="modal-body">
				<div class="row-fluid">
					<s:form action="addBalanceDetail" method="post">
					<s:hidden name="balanceDetail.detailId"></s:hidden>
						<div class="input-container">
							<label>
								实收款项 
							</label>
							<s:textfield name="balanceDetail.receivedFunds" />
						</div>
						<hr/>
						<h3>
							余额分配
						</h3>
						<div class="input-container">
							<label>
								工资
							</label>
							<s:textfield name="balanceDetail.wages"/>
						</div>
						<div class="input-container">
							<label>
								服务费
							</label>
							<s:textfield name="balanceDetail.serviceWith"/>
						</div>
						<div class="input-container">
							<label>
								五险一金
							</label>
							<s:textfield name="balanceDetail.fiveFund" />
						</div>
						<hr/>
						<div class="input-container">
							<label>
								备注
							</label>
							<s:textfield name="balanceDetail.note"/>
						</div>
						<div class="input-container">
							<label>
								&nbsp;
							</label>
							<s:submit value="提交" cssClass="btn btn-primary" />
						</div>
					</s:form>
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
