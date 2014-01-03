<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>" />
	<%@ include file="/help/public_css_js.jsp"%>
    <title>系统权限列表</title>
	<script type="text/javascript">
		$(function(){
			$("#menu3").addClass("current");
			$("#ok").click(function (){
				location.href="toAddSystemPrivilege";
			
			});
			
			
		});
	</script>
  </head>
  <body>
  	<br /><br />
  	<div id="main">
  	<h1>系统权限列表信息</h1>
		<div id="datalist">
			<table width="1000" border="1">
				<thead>
				<tr id="tableheader">
					<th>编号</th>
					<th>名称</th>
					<th>路径</th>
					<th>根节点序号</th>
					<th>图片</th>
					<th>排序方式</th>
					<th>操作</th>
				</tr>
				</thead>
				<s:iterator value="#request.privileges" id="privilege">
					<tbody>
						<tr>
							<td align="center">${privilege.id}</td>
							<td	>${privilege.name}</td>
							<td>${privilege.link}</td>
							<td align="center">${privilege.parentId}</td>
							<td>${privilege.icon}</td>
							<td align="center">${privilege.orderNum}</td>
							<td align="center">
								<a href="toUpdatePrivilege?privilege.id=${privilege.id}">[编  辑]</a>
							</td>
						</tr>
					</tbody>
				</s:iterator>
			</table>
		</div>
		<div id="opr">
			<input type="button" value=" 新  增 " class="oprbtn" id="ok" /> 
			<input type="button" value=" 取  消 " class="oprbtn" id="cancel"/>
		</div>
	</div>
  </body>
</html>
