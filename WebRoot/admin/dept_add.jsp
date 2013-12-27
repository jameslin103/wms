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
<meta charset="UTF-8">
<title>增加子部门</title>
<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>
</head>
<body>
	<div id="main">
		<div id="newdata">
			<table width="95%" border="1">
				<tr>
					<td width="70">部门名称</td>
					<td><input type="text" name="department.name" id="name"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>