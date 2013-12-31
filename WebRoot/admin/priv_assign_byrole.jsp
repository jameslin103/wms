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
    <title>权限分配</title>
	<script type="text/javascript">
		$(function(){
			$("#menu2").addClass("current");
		});
	</script>
  </head>
  
  <body>
  	<jsp:include page="priv_assign_nav.jsp"></jsp:include>
  	<br /><br />
  	<div id="main">
  	<h1>为角色指定用户</h1>
	<form method="post" action="">
		<div id="newdata">
			<table width="500" border="1">
				<tr>
					<td width="60">角色</td>
					<td><input type="text" name="role.name"  size="30"/>
					<input type="button" class="oprbtn" value="选择角色" /></td>
				</tr>
				<tr>
					<td>用户列表</td>
					<td></td>
				</tr>
			</table>
		</div>
		<div id="opr">
			<input type="button" value=" 确  定 " class="oprbtn" id="ok" /> <input
				type="button" value=" 取  消 " class="oprbtn" id="cancel"/>
		</div>
		<div id="selPrives"></div>
		</form>
	</div>
  </body>
</html>
