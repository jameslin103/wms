<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>角色列表</title>
<base href="${base}"></base>
<%@ include file="/help/public_css_js.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#new").click(function(){
			location.href="toAddRole";
		});
	});
	
	function del(id){
		if(confirm("您确认删除吗?")){
			location.href="deleteRole?role.id="+id;
		}
	}
</script>
</head>

<body>
	<div id="main">
		<fieldset>
			<legend>
				<img src="images/311.gif" />&nbsp;角色列表
			</legend>
			
			<div id="datalist">
				<table>
					<thead>
						<tr id="tableheader">
							<th width="60"><input type="checkbox" />选择</th>
							<th width="60">序号</th>
							<th width="80">角色名称</th>
							<th width="300">角色描述</th>
							<th width="60">编辑</th>
							<th width="60">删除</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="#request.roles" var="role" status="s">
							<tr>
								<td class="center"><input name="ids" type="checkbox" value=${role.id} /></td>
								<td class="center">${s.count}</td>
								<td class="center">${role.name}</td>
								<td class="center">${role.description}</td>
								<td class="center"><img src="images/037.gif" width="9"
									height="9" />[<a href="updateRole?role.id=${role.id}">编辑</a>]</td>
								<td class="center"><img src="images/010.gif" width="9"
									height="9" /> [<a href="javascript:void(0)" onclick="del('${role.id}')">删除</a>]</td>
							</tr>
					</s:iterator>
					</tbody>
				</table>
			</div>
			<div id="opr">
				<input type="button" value=" 新  增 " class="oprbtn" id="new" /> 
				<input type="button" value=" 删  除 " class="oprbtn" />
			</div>
		</fieldset>
	</div>
</body>
</html>

