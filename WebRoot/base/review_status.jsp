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
<title>审核企业状态</title>
<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>
</head>

<body>
	<div id="main">
		<div id="newdata" style="padding-top:30px;">
			<table width="95%" border="1">
				<tr>
					<td width="70">审核状态?</td>
					<td>
						<input type="radio" name="status" checked="checked" value="1" id="status"/>审核通过
						<input type="radio" name="status" value="2" id="status2"/>审核不通过
					</td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input type="text" name="note" style="height:35px;" maxlength="30" id="note">
						<span style="color:red">限制30个字符</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>