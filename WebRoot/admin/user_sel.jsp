<%@ page language="java" pageEncoding="UTF-8"%>
  <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>选择用户</title>
<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>
<script type="text/javascript">
	var selUser=[];
	$(function()
	{
		$(":radio").click(function()
		{
			selUser[0]=$(this).val();
			selUser[1]=$(this).parent().next().html();
		});
		
	});
		
		
		
		
	function goPage(p)
	{
		
	}
	
	
	
</script>
  </head>
  
  <body>
     <div id="datalist">
		<table width="90%" border="1" align="center">
			<thead>
				<tr id="tableheader">
					<th width="10%">选择</th>
					<th>用户</th>
					<th>绑定员工</th>
				</tr>
			</thead>
			<tbody id="emplist">
				<s:iterator value="#request.users" id="user">
					<tr>
						<td align="center"><input type="radio" value="<s:property value="%{#user.id}"/>" name="ids"></td>
						<td align="center">${user.account}</td>
						<td align="center">${user.employee.name}</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div id="nav" style="padding-left:27px;">	
			<img src="images/first.gif" id="first"/>
			<img src="images/back.gif" id="prev"/>
			<img src="images/next.gif" id="next" /> 
			<img src="images/last.gif" id="last">
		</div>
	 </div>
  </body>
</html>