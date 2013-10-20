<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>富民人力银行派遣系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/help/public_css_js.jsp"%>
<script>
	$(document).ready(function(){
		$("#add-role,#edit-user").on("hidden",function(){
			$("form :checkbox",this).removeAttr("checked");
			$("form",this)[0].reset();
		});
		$(".edit-user-btn").on("click",function(){
			$.getJSON("loadWmsUser",{"wmsUser.userId":$(this).data("id")}).success(function(data){
				$("#edit-user .userName").html(data.username);
				$("#edit-user .userId").val(data.userId);
				var roleIds = data.roleIds?data.roleIds.split(","):null;
				if(roleIds){
					for(var i=0;i<roleIds.length;i++){
						var roleId=roleIds[i];
						$("#edit-user .role-id").each(function(){
							if($(this).val()==roleId)$(this).attr("checked",true);
						});
					}
				}
			});
		});
		$("#user-select-list option").on("click",function(){
			if($(this).val()){
				$(".list-of-items-for-delete").append("<li>"+$(this).html()+"<input type='hidden' name='userIds' value='"+$(this).val()+"'/><button type='button' class='close' data-dismiss='alert'>&times;</button></li>");
			}
		});
	});
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<jsp:include page="../layout/header.jsp" />
		</div>

		<div class="navbar">
			<div class="navbar-inner">
				<a class="brand" href="#">富民</a>
				<ul class="nav">
					<s:iterator value="#session.menu" var="menu">
						<s:if test="parentMenu.menuId==1">
							<s:if test="type==0">
								<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><s:property value="name"/><b class="caret"></b></a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
										<s:iterator value="#session.menu" var="submenu">
											<s:if test="#menu.menuId==#submenu.parentMenu.menuId">
												<li><a tabindex="-1" href="<s:property value="#submenu.url"/>"><s:property value="#submenu.name"/></a></li>
											</s:if>
										</s:iterator>
									</ul>
								</li>
							</s:if>
							<s:if test="type==1">
								<li><a href="<s:property value="url"/>"><s:property value="name"/></a></li>
							</s:if>
						</s:if>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div id="main">
			<div class="row-fluid">
				<div id="center-pane">
					<ul class="nav nav-tabs">
						<li class="active"><a href="toAuthorizationUser">权限分配</a></li>
						<li><a href="toRoleManage">角色管理</a></li>
					</ul>
					<ul class="normal action-container clearfix">
						<li><a href="#add-permission" data-toggle="modal">为员工分配权限</a>
						</li>
					</ul>
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>序</th>
								<th>姓名</th>
								<th>权限</th>
								<th>修改</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="wmsUserList" var="user" status="list">
								<tr>
									<td><s:property value="#list.index+1"/></td>
									<td><s:property value="username"/></td>
									<td><s:iterator value="roles" var="role" status="list2">
										<s:property value="#list2.index+1"/>.<s:property value="name"/>
										<s:if test="#list2.index+1<roles.size"><br></s:if>
									</s:iterator>&nbsp;</td>
									<td><a href="#edit-user" data-toggle="modal" class="edit-user-btn" data-id="<s:property value="userId"/>">修改</a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="footer"></div>
	</div>
	<div id="add-permission"
		class="modal hide fade modal-of-info-for-check" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">权限分配</h3>
		</div>
		<div class="modal-body">
			<form action="addAuthorization" class="navbar-form pull-left" method="post">
				<div class="row-fluid">
					<div class="input-container">
						<!-- <input type="text">
						<button type="submit" class="btn">搜索</button> -->
						<s:select id="user-select-list" list="wmsUserList" listKey="userId" listValue="username" headerKey="" headerValue="请选择"></s:select>
					</div>
					<div class="input-container">
						<ul class="list-of-items-for-delete normal clearfix">
						</ul>
					</div>
					<hr>
					<s:iterator value="roleList" var="role">
						<div class="input-container">
							<input type="checkbox" name="roleIds" class="role-id" value="<s:property value="roleId"/>"><s:property value="name"/>
						</div>
					</s:iterator>
					<div class="input-container">
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		</div>
	</div>

	<div id="edit-user" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">权限分配</h3>
		</div>
		<div class="modal-body">
			<div class="row-fluid">
				<form action="updateAuthorization" class="navbar-form pull-left" method="post">
					<div class="row-fluid">
						<div class="input-container">
							<h3 class="userName"></h3>
							<s:hidden cssClass="userId" name="wmsUser.userId"/>
						</div>
						<s:iterator value="roleList" var="role">
							<div class="input-container">
								<input type="checkbox" name="roleIds" class="role-id" value="<s:property value="roleId"/>"><s:property value="name"/>
							</div>
						</s:iterator>
						<div class="input-container">
							<button type="submit" class="btn btn-primary">提交</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		</div>
	</div>
</body>

</html>
