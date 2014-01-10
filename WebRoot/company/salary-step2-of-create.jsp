<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@ include file="/help/public_css_js.jsp" %>
	 <script type="text/javascript">
	  function commit(){
	        var myform=document.getElementById('myForm');
	       	myform.submit();
	        return false;
    }
    var enterprise_Employees= new Array();
    $(function (){
			$("#ent_emp").click(function(){
				 $("#emp").html('');
				 $.dialog({
						id:'enterpriseEmployees',
						content:'url:getEmployeesJson?enterpriseId='+${session.enterprise.enterpriseId},
						height:'500px',
						width:'1000px',
						title:'${session.enterprise.fullName}-在职员工',
						ok:function(){
							var employees=$.dialog.list['enterpriseEmployees'].content.enterpriseEmployees;
							 $(employees).each(function(i,employees){
							 	var emp="&nbsp;&nbsp;姓名: <font style='color:red;'>"+employees.name+"</font>&nbsp;&nbsp;身份证:&nbsp;&nbsp;<font style='color:red;'>"+employees.card+"</font>&nbsp;&nbsp;&nbsp;";
							 	var names="<input type='hidden' name='empIds' value='"+employees.id+"' /><br/>"+
							 			  "<input type='hidden' name='empNames' value='"+employees.name+"' /><br/>"+
							 			  "<input type='hidden' name='empCards' value='"+employees.card+"' />";
							 	$("#emps").append(emp);
							 	$("#selempNames").append(names);
							 	
							 	
							 	 var enterpriseEmployees = {
								       employeesId:employees.id,
								       employeesName:employees.name,
								       cardNumber:employees.card
								  };
								 enterprise_Employees.push(enterpriseEmployees);
							 	
							});
							//$("#entEmps").val(enterprise_Employees);
							
							
				},
			cancel:true
		});
				 
				 
				/**$.get("getEmployeesJson?enterpriseId="+${session.enterprise.enterpriseId},function(employees){
					if($("#emp").is(":hidden")){
						 $("#emp").append(employees);
						 $("#emp").show();
					}else{
						$("#emp").hide();
					}
				});**/
			});
			$("#close").click(function(){
				if($("#emp").is(":visible")){
					$("#emp").hide();
				}
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
						<a href="viewEnterpriseDetailed" >综合 </a>
					</li>
					<li>
						<a href="viewEnterpriseEmployees"  >员工档案</a>
					</li>
						<li class="active">
							<a href="viewSalaryBudgetTableSummary" >工资预算表</a>
						</li>
					</ul>
          <div>
          	<table width="1000px;" align="center" border="1" style="line-height: 30px;">
          		<tr>
          			<td colspan="4" align="center"><h2>2/3&nbsp;新建工资预算表信息</h2></td>
          		</tr>
          		<tr bgcolor="#C09853">
          			<th>预算表名称:</th>
          			<td><s:property value="%{#request.createSalaryBudgetTable.name}"/></td>
          			<th>项目模板:</th>
          			<td><s:property value="%{#request.createSalaryBudgetTable.salaryTemplate.templateName}"/></td>
          		</tr>
          		<tr bgcolor="#C09853">
          			<th>所属月份:</th>
          			<td><s:date name="%{#request.createSalaryBudgetTable.salaryDate}" format="yyyy年MM月" /></td>
          			<th>合并计税工资表:</th>
          			<td><s:property value="%{#request.createSalaryBudgetTable.chooseTax}"/></td>
          		</tr>
          		<tr>
          			<td colspan="4"><a href="updateSalaryBudgetTable?budgetId=<s:property value="%{#request.createSalaryBudgetTable.budgetId}"/>">返回修改</a></td>
          		</tr>
          		<tr>
          			<td colspan="4">
          				<input type="button" value="选择员工" id="ent_emp"/>
          				<form action="downloadSalaryBudgetTable" id="my_downFile" method="post">
          					<s:hidden name="templateId" value="%{#request.createSalaryBudgetTable.salaryTemplate.templateId}"></s:hidden>
          					<s:hidden name="createSalaryBudgetTable.budgetId" value="%{#request.createSalaryBudgetTable.budgetId}"></s:hidden>
		          			<div style="display: none;" id="selempNames"></div>
		          			<s:hidden name="entEmps" id="entEmps"></s:hidden>
		          			<s:submit value="下载工资预算表" cssStyle="border:0px;color:#2E9AFE;font-size:20px;background-color: white;"/>
	          			</form>
          			</td>
          		</tr>
          		<tr>
          			<td colspan="4">上传新工资预算表</td>
          		</tr>
          		<tr>
          			<td colspan="4">
          				<s:form action="uploadEmployeesSalaryDetail" cssClass="form-search" method="post" enctype="multipart/form-data">
                      	 	<s:hidden value="%{#request.createSalaryBudgetTable.salaryTemplate.templateId}" name="templateId"></s:hidden>
                      	 	<s:hidden value="%{#request.createSalaryBudgetTable.budgetId}" name="budgetId"/>
                      	  	<s:hidden value="%{#request.createSalaryBudgetTable.salaryDate}" name="salaryDate"/>
                      	 	<s:hidden name="file" value="file"></s:hidden>
                         <input type="file" name="file" id="filevalue"/><br/>
                      </s:form>
          			</td>
          		</tr>
          		<tr>
          			<td colspan="4" align="center">
          			 <s:submit  cssClass="btn btn-primary" id="uploadFile" value="上传"/>
          			</td>
          		</tr>
          		<s:iterator value="#request.employeesNames" id="names">
          		 <tr>
          		 	<td colspan="4"><span style="color: red">${names}</span></td>
          		 </tr>
				 </s:iterator>
          	</table>
          		  <div>
          		  	  <table width='1000px;' style="background-color:#CEAE71;" align='center' border='1' id="emps">
          		  	 	
          		  	  </table>
          		  </div>
              </div>
          </div>
        </div>
      </div>
    </div>
    
    
    
    <!-- ===================================在线编辑工资============================================= -->
    <div id="add-enterprise-bnt" style="display: none;" class="white_content ui_title_bar">
		<div class="modal-header" style="background-color:#CEAE71">
				<button type="button" style="color:#2E9AFE;" class="close" id="close" data-dismiss="modal" aria-hidden="true">
					关闭
				</button>
				<h2 id="myModalLabel" align="center">
					<span style="color:white">添加企业信息</span>
				</h2>
			</div>
          <form action="addEnterprise" method="post" id="add_form">
            <table class="table table-bordered">
              <tbody>
                <tr>
                  <td class="bgc">企业名称</td>
                  <td><input type="text" name="enterprise.fullName"/></td>
                  <td class="bgc">合同编号</td>
                  <td><input type="text" name="enterprise.contatId"/></td>
                </tr>
                <tr>
                  <td class="bgc">行业分类</td>
                  <td>
                    <select name="enterprise.industryType">
                      <option value="0">-请选择-</option>
                      <option value="1">IT|通信|电子|互联网</option>
                      <option value="2">金融业</option>
                      <option value="3">房地产|建筑业</option>
                      <option value="4">商业服务</option>
                      <option value="5">贸易|批发|零售|租赁业</option>
                      <option value="6">文体教育|工艺美术</option>
                      <option value="7">生产|加工|制造</option>
                      <option value="8">交通|运输|物流|仓储</option>
                      <option value="9">服务业</option>
                      <option value="10">文化|传媒|娱乐|体育</option>
                      <option value="11">能源|矿产|环保</option>
                      <option value="12">政府|非盈利机构</option>
                      <option value="13">农|林|牧|渔|其他</option>
                    </select>
                  </td>
                  <td class="bgc">合同起止时间</td>
                  <td>
                   <input id="d4311" class="Wdate" type="text" style="width:150px;"
                   			onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4311\')||\'2020-10-01\'}',skin:'whyGreen'})" 
                   				name="enterprise.startContractDate"></input>
						~
                    <input id="d4312" class="Wdate" type="text" style="width:150px;"
                    	onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01',skin:'whyGreen'})" 
                    		name="enterpriseEmployees.endContractDate"></input>
                  </td>
                </tr>
                <tr>
                  <td class="bgc">企业地址</td>
                  <td><input type="text" name="enterprise.adress"/></td>
                  <td class="bgc">法人代表</td>
                  <td><input type="text" name="enterprise.legalRepresentative"/></td>
                </tr>
                <tr>
                  <td class="bgc">企业联系人</td>
                  <td><input type="text" name="enterprise.contact"/></td>
                  <td class="bgc">开户行</td>
                  <td><input type="text" name="enterprise.accountLine"/></td>
                </tr>
                <tr>
                  <td class="bgc">联系电话</td>
                  <td><input type="text" name="enterprise.phone"/></td>
                  <td class="bgc">企业银行账号</td>
                  <td><input type="text" name="enterprise.enterpriseBankAccount"/></td>
                </tr>
                <tr>
                  <td class="bgc">QQ</td>
                  <td><input type="text" name="enterprise.qq"/></td>
                  <td class="bgc">合作项目</td>
                  <td><input type="text" name="enterprise.projects"/></td>
                </tr>
                <tr>
                  <td class="bgc">邮箱</td>
                  <td><input type="text" name="enterprise.email"/></td>
                  <td class="bgc">服务性质</td>
                  <td>
                    <select name="enterprise.serviceType">
                      <option value="0">-请选择-</option>
                      <option value="1">完全派遣</option>
                      <option value="2">转移派遣</option>
                      <option value="3">其它</option>
                    </select>
                    <input type="text" name="enterprise.customType" style="display:none;"/>
                  </td>
                </tr>
              </tbody>
              <tbody>
                <tr>
                  <td>服务费</td>
                  <td>
                    <input type="radio" name="fee" value="1" checked="checked"/>按人头，
                    <input class="span1" type="text" name="enterprise.serviceHead" maxlength="10"/>元/人
                    <input type="radio" name="fee" value="0"/>按比例，
                    <input class="span1" type="text" name="enterprise.proportion" maxlength="10"/>%
                  </td>
                  <td>企业状态</td>
                   <td> 
                   	  <input type="radio" name="enterprise.status" value="0"/>合约
                   	  <input type="radio" name="enterprise.status" value="1"/>暂停
                   	</td>
                </tr>
                <tr>
                  <td>注意事项</td>
                  <td><input type="text" name="enterprise.note"/></td>
                </tr>
                <tr>
                	<td colspan="4" style="text-align: center;">
                		<s:submit cssClass="btn btn-primary" value="新增" />
                		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                		<input type="button" class="btn btn-primary" value="重置" />
                	</td>
                </tr>
              </tbody>
            </table>
          </form>
       	 </div>
    	<div id="emp" style="display:none;" class="white_content">
    			<div class="modal-header" style="background-color:#CEAE71">
				<button type="button" style="color:#2E9AFE;" class="close" id="close" data-dismiss="modal" aria-hidden="true">
					关闭
				</button>
				<h2 id="myModalLabel" align="center">
					<span style="color:white">员工列表</span>
				</h2>
			</div>
    	</div>
    
</body>

</html>
