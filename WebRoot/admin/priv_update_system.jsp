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
		});
	</script>
  </head>
  <body>
  	<br /><br />
  	<div id="main">
  	<h1>修改系统权限配置</h1>
	<form method="post" action="updatePrivilege">
		<div id="newdata">
			<table width="800">
				<tr>
					<td>编号</td>
					<td><input type="text" name="privilege.id" value="${privilege.id}"/></td>
					<td>名称</td>
					<td><input type="text" name="privilege.name" value="${privilege.name}"/></td>
				</tr>
				<tr>
					<td>路径</td>
					<td><input type="text" name="privilege.link" value="${privilege.link}"/></td>
					<td>根节点序号</td>
					<td><input type="text" name="privilege.parentId" value="${privilege.parentId}"/></td>
				</tr>
				<tr>
					<td>图片路径</td>
					<td><input type="text" name="privilege.icon" value="${privilege.icon}"/></td>
					<td>排序方式</td>
					<td><input type="text" name="privilege.odernum" value="${privilege.orderNum}"/></td>
				</tr>
				
				
			</table>
		</div>
		<div id="opr">
			<input type="submit" value=" 修  改 " class="oprbtn" id="ok" /> 
			<input type="button" value=" 取  消 " class="oprbtn" id="cancel"/>
		</div>
		<div id="selPrives"></div>
		</form>
	</div>
  </body>
</html>
