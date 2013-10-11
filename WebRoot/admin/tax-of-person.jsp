<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>富民人力银行派遣系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="/help/public_css_js.jsp"%>
		<script type="text/javascript">
			$(function (){
				$("#submit").click(function (){
				  var taxThreshold=$("input[name='taxOfPerson.taxThreshold']").val();
				  var statrDate=$("input[name='taxOfPerson.statrDate']").val();

				
				   if(taxThreshold=="")
				   {
				   		$("#errortax").css("color","red");
				   		$("#errortax").text("起征点必填项!");
				   		return false;
				   }else if(statrDate==""){
				   		$("#errorStatrDate").css("color","red");
				   		$("#errorStatrDate").text("执行日期必填项!");
				   		return false;
				   }else{
				   		clearErrorMessage();
				   		return true;
				   }
				});
			});
		$(function (){
			$(".close").click(function (){
				clearErrorMessage();
			});
			$("#close").click(function (){
				clearErrorMessage();
			});
		
		});
			
			
			
		function clearErrorMessage()
		{
			$("#errortax").text(" ");
			$("#errorStatrDate").text(" ");
		}
		
		</script>
	</head>
	<body>

		<div id="container">
			<div id="header">
				<jsp:include page="../layout/header.jsp" />
			</div>

			<div id="main">
				<div class="row-fluid">

					<div id="center-pane" class="span9">
						<ul class="nav nav-tabs">
							<li>
								<a href="toViewTaxRules">五险一金（比率）</a>
							</li>
							<li>
								<a href="viewInsurancesBaseSettings">五险一金（基数）</a>
							</li>
							<li class="active">
								<a href="viewTaxOfPerson">个税</a>
							</li>
						</ul>
						<div>
						<table class="table table-striped table-bordered">
				            <thead>
				              <tr>
				                <th class="center">序号</th>
				                <th class="center">个人所得税计起征点</th>
				                <th class="center">开始执行月份</th>
				                <th class="center">操作</th>
				              </tr> 
				              
				             <tr>
				             	<th class="center"><s:property value="%{#request.taxOfPerson.taxid}"/></th>
				                <th class="center"><s:property value="%{#request.taxOfPerson.taxThreshold}"/></th>
				                <th class="center"><s:date name="%{#request.taxOfPerson.statrDate}" format="yyyy年MM月dd日"/></th>
				                <th class="center"><a href="#info-for-check" data-toggle="modal">修改</a></th>
				              </tr> 
				          </table>
				          	<br/>
				         	<p style="color: red;">计算公式是：
							       工资、薪金所得个人所得税应纳税额=应纳税所得额×适用税率-速算扣除数
							</p>
							<div>
							<table class="table table-striped table-bordered">
					            <thead>
					              <tr>
					                <th class="center">级数</th>
					                <th class="center">含税级距</th>
					                <th class="center">不含税级距</th>
					                <th class="center">税率（％）</th>
					                <th class="center">速算扣除数</th>
					              </tr> 
					              
					             <tr>
					             	<td class="center">1</td>
					                <td>不超过1500元的</td>
					                <td>不超过1455元的</td>
					                <td class="center">3</td>
					                <td class="center">0</td>
					             </tr>
					             <tr>
					             	<td class="center">2</td>
					                <td>超过1500元至4500元的部分</td>
					                <td>超过1455元至4155元的部分</td>
					                <td class="center">10</td>
					                <td class="center">105</td>
					              </tr> 
					              <tr>
					             	<td class="center">3</td>
					                <td>超过4500元至9000元的部分</td>
					                <td>超过4155元至7755元的部分</td>
					                <td class="center">20</td>
					                <td class="center">555</td>
					              </tr> 
					              <tr>
					             	<td class="center">4</td>
					                <td>超过9000元至35000元的部分</td>
					                <td>超过7755元至27255元的部分</td>
					                <td class="center">25</td>
					                <td class="center">1005</td>
					              </tr> 
					              <tr>
					             	<td class="center">5</td>
					                <td >超过35000元至55000元的部分</td>
					                <td>超过27255元至41255元的部分</td>
					                <td class="center">30</td>
					                <td class="center">2755</td>
					              </tr> 
					              <tr>
					             	<td class="center">6</td>
					                <td>超过55000元至80000元的部分</td>
					                <td>超过41255元至57505元的部分</td>
					                <td class="center">35</td>
					                <td class="center">5505</td>
					              </tr> 
					              <tr>
					             	<td class="center">7</td>
					                <td>超过80000元的部分</td>
					                <td>超过57505元的部分</td>
					                <td class="center">45</td>
					                <td class="center">13505</td>
					              </tr> 
				           </table>
				           <p style="font:normal;"><a style="TEXT-DECORATION:none">
				           		注：①表中所列含税级距、不含税级距，均为按照税法规定减除有关费用后的所得额。
				           		          ②含税级距适用于由纳税人负担税款的工资、薪金所得；
				           			不含税级距适用于由他人（单位）代付税款的工资、薪金所得。<br/>
									<span style="color: red">例：王某当月取得工资收入9400元，当月个人承担住房公积金、基本养老保险金、医疗保险金、
									失业保险金共计1000元，费用扣除额为3500元，
									则 王某当月应纳税所得额=9400-1000-3500=4900元。
									应纳个人所得税税额=4900×20%-555=425元。</span></a>
				           </p>
							</div> 
						</div>
					</div>
					
					<div id="right-pane" class="span3">

					</div>

				</div>
			</div>
			
			<div id="footer"></div>

		</div>
		<div id="info-for-check" class="modal hide fade" tabindex="-1"	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					×
				</button>
		<h3 id="myModalLabel">个人所得税起征点</h3>
   		 </div>
	    <div class="modal-body">
	      <form action="updateTaxOfPerson" method="post" id="myForm">
	      	<input type="hidden" value="${taxOfPerson.taxid}" name="taxOfPerson.taxid"/>
	        <div class="row-fluid">
	          <div class="input-container">
	            <label>起征点:</label>
	            <input type="text" name="taxOfPerson.taxThreshold" value="${taxOfPerson.taxThreshold}" style="height:30px;"><span style="color: red"> *</span>(元)
	            <span id="errortax"></span>
	          </div>
	          <div class="input-container">
	            <label>&nbsp;</label>
	             <label>开始执行日期:</label>
	              <input type="text" id="d11" name="taxOfPerson.statrDate" value="${taxOfPerson.statrDate}" onclick="WdatePicker()" class="Wdate" style="height: 30px;"/>
	          	   <span style="color: red"> *</span>
	          	   <span id="errorStatrDate"></span>
	          </div>                    
	          <div class="input-container">
	              <s:submit value="提交" cssClass="btn btn-primary" id="submit"/>
	          </div>
	        </div>
	      </form>              
	    </div>
	    <div class="modal-footer">
	      <button class="btn" data-dismiss="modal" aria-hidden="true" id="close">Close</button>
	    </div>
	
			</div>
	</body>

</html>
