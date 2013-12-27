<%@ page language="java" pageEncoding="UTF-8"%>
  <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>选择用户</title>
<base href="<%=basePath%>" />
<script type="text/javascript">
	var selUser=[];
	$(function(){
		$(":radio").click(function(){
			selUser[0]=$(this).val();
			selUser[1]=$(this).parent().next().html();
		});
	
	});
	
	function goPage(p){
		
	}
	
</script>
  </head>
  
  <body>
     <div id="datalist">
		<table width="95%" border="0" align="center">
			<thead>
				<tr id="tableheader">
					<th width="15%">选择</th>
					<th>用户</th>
					<th>绑定员工</th>
				</tr>
			</thead>
			<tbody id="emplist">
				<c:forEach items="${users}" var="user">
					<tr>
						<td><input type="radio" value="${user.id}" name="ids"></td>
						<td>${user.account}</td>
						<td>${user.employee.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="nav">
			<img src="images/first.gif" id="first"/>
			<img src="images/back.gif" id="prev">
			<img src="images/next.gif" id="next" /> 
			<img src="images/last.gif" id="last">
		</div>
	 </div>
  </body>
</html>