<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="${base}">
    <title>权限分配</title>
	<script type="text/javascript">
		$(function(){
			$("#menu3").addClass("current");
		});
	</script>
  </head>
  
  <body>
  	<jsp:include page="priv_assign_nav.jsp"></jsp:include>
  	<br /><br />
  	<div id="main">
  	<h1>为权限指定用户</h1>
	<form method="post" action="">
		<div id="newdata">
			<table width="500" border="1">
				<tr>
					<td width="60">权限</td>
					<td><input type="text" name="name"  size="30"/>
					<input type="button" class="oprbtn" value="选择权限" /></td>
				</tr>
				<tr>
					<td width="60">已有该权限的用户</td>
					<td></td>
				</tr>
				<tr>
					<td>用户列表</td>
					<td>
						<table width="100%" border="1">
							<thead>
							<tr>
								<th width="120"><input type="checkbox">全选/全不选</th>
								<th width="40">序号</th>
								<th>帐号</th>
								<th>分配给</th>
								</tr>
							</thead>
							<tbody>
							   
							</tbody>
						</table>
					</td>
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
