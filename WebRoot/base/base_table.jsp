<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>选择用户</title>
<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>
</head>
<script type="text/javascript">
	function topage(page){
			var form = document.getElementById("my_employee");
				form.page.value=page;
				form.submit();
	}
	
	var enterpriseEmployees= new Array();
	$(function(){
			$("#pagesel").val("${page}");
				$("#pagesel").change(function(){
					topage($(this).val());
			});
			
		$("#selall").click(function(){
			$(":checkbox[name=ids]").prop("checked",$(this).prop("checked"));
		});
		$(":checkbox[name=ids]").click(function(){
			var b=true;
			$(":checkbox[name=ids]").each(function(){
				if(!$(this).prop("checked")){
					b=false;
					return;
				}
			});
			$("#selall").prop("checked",b);
		});
		$("#addEmp").click(function(){
		
			var flag=false;
			$(":checkbox[name=ids]").each(function(){
				 if ($(this).attr("checked")) {
	    		        flag = true; 
                 }  
			});
			if(flag==false)
			{
				$.dialog.tips("请选择员工",2);
			}else{
				 $(":checkbox[name=ids]").each(function(){
				 
						 if ($(this).attr("checked")) 
						 {
						 	  var car=$(this).parent().next().next().html();
						 	  var name=$(this).parent().next().html();
						 	  var repeat=false;
						 	  if(enterpriseEmployees.length!=0 && enterpriseEmployees!=undefined)
						 	  {
						 	  	$(enterpriseEmployees).each(function(i,value){
						 	  	   if(value==car){
						 	  	   	$.dialog.tips("选择了重复员工: ("+name+","+")",2);
						 	  	   	repeat=true;
						 	  	   }
						 	  	});
						 	  	
						 	  }
						 	 if(repeat ==false )
						 	 {
								  var employee = {
								       id:$(this).val(),
								       name:$(this).parent().next().html(),
								       card:$(this).parent().next().next().html()
								  };
								 enterpriseEmployees.push(employee);
							}
		                 }
					});
			 }
		 });
	
	})
</script>
 <body>
 		<div id="datalist">
	 	 <table width="90%" border="1" align="center">
		 	  <thead>
		 	  		<tr id="tableheader">
						<th width="60">
							<input type="checkbox" id="selall"/>选择
							<input type="button" id="addEmp" value="添  加" style="color:white;background-color: #2E9AFE; border:0px;"/>
						</th>
						<th width="80">姓&nbsp;&nbsp;名</th>
						<th width="80">身份证</th>
					</tr>
		 	  	  <tr>
		 	  	  	
		 	  	  </tr>
		 	  </thead>
		 	  		
              <tbody>
              
              	<s:iterator value="#request.pageView.records" id="employees">
	                <tr>
	                  <td class="center">
	                  	<input name="ids" type="checkbox" value="<s:property value="%{#employees.employeesId}"/>" />
	                  </td>
	                  <td class="bgc">${employees.employeesName}</td>
	                  <td>${employees.cardNumber}</td>
	                </tr>
	                <tr>
                </s:iterator>
              </tbody>
            </table>
            </div>
			<s:form action="getEmployeesJson" method="post" id="my_employee">
				<s:hidden name="page"/>
				<s:hidden name="enterpriseId" value="%{#request.enterpriseId}"/>
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
		
</body>		
</html>