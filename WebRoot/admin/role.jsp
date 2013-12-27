<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/help/public_css_js.jsp"%>

</head>
<body>
	<div id="container">
		<div id="header">
		</div>
		<div id="main">
			<div class="row-fluid">
				<div id="center-pane">
					<ul class="nav nav-tabs">
						<li><a href="toAuthorizationUser">权限分配</a></li>
						<li class="active"><a href="toRoleManage">角色管理</a></li>
					</ul>
					<ul class="normal action-container clearfix">
						<li><a href="#add-role" data-toggle="modal">新增角色</a>
						</li>
					</ul>
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th align="center">序</th><th align="center">角色名</th><th align="center">权限</th><th align="center">操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="roleList" var="role" status="list">
								<tr>
									<td><s:property value="#list.index+1"/></td>
									<td><s:property value="name"/></td>
									<td><s:iterator value="menus" var="menu">
										<s:if test="parentMenu.menuId==1">
											<s:property value="name"/>(&nbsp;
											<s:iterator value="menus" var="submenu">
												<s:if test="#menu.menuId==#submenu.parentMenu.menuId">
													<s:property value="#submenu.name"/>&nbsp;
												</s:if>
											</s:iterator>)<br/>
										</s:if>
									</s:iterator></td>
									<td><a href="#edit-role" data-toggle="modal" class="edit-role-btn" data-id="<s:property value="roleId"/>">修改</a>&nbsp;<a href="#delete-role" class="delete-role-btn" data-toggle="modal" data-id="<s:property value="roleId"/>">删除</a></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

				</div>
			</div>
		</div>
		<div id="footer"></div>
	</div>
	<div id="add-role" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">新增角色</h3>
		</div>
		<div class="modal-body">
			<form action="addRole" class="navbar-form pull-left" method="post">
				<div class="row-fluid">
					<div class="input-container">
						<label>角色名:</label><input type="text" name="role.name"></input>
					</div>
					<hr/>
					<s:iterator value="menuList" var="menu">
						<s:if test="parentMenu.menuId==1">
							<div class="input-container">
								<input type="checkbox" name="menuIds" value="<s:property value="menuId"/>"/><s:property value="name"/>
							</div>
							<s:iterator value="menuList" var="submenu">
								<s:if test="#menu.menuId==#submenu.parentMenu.menuId">
									<div class="input-container">
										&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="menuIds" value="<s:property value="#submenu.menuId"/>"/><s:property value="#submenu.name"/>
									</div>
								</s:if>
							</s:iterator>
						</s:if>
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

	<div id="edit-role" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">修改角色</h3>
		</div>
		<div class="modal-body">
			<div class="row-fluid">
				<form action="updateRole" class="navbar-form pull-left" method="post">
					<div class="row-fluid">
						<div class="input-container">
							<h3 class="roleName"></h3>
							<s:hidden cssClass="roleId" name="role.roleId"/>
						</div>
						<s:iterator value="menuList" var="menu">
							<s:if test="parentMenu.menuId==1">
								<div class="input-container">
									<input type="checkbox" name="menuIds" class="menu-id" value="<s:property value="menuId"/>"/><s:property value="name"/>
								</div>
								<s:iterator value="menuList" var="submenu">
									<s:if test="#menu.menuId==#submenu.parentMenu.menuId">
										<div class="input-container">
											&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="menuIds" class="menu-id" value="<s:property value="#submenu.menuId"/>" /><s:property value="#submenu.name"/>
										</div>
									</s:if>
								</s:iterator>
							</s:if>
						</s:iterator>
						<div class="input-container">
							<button type="submit" class="btn btn-primary">提交</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		</div>
	</div>
	<div id="delete-role" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">删除角色</h3>
		</div>
		<div class="modal-body">
			确定删除该角色?
			<form action="deleteRole" method="post">
				<input type="hidden" name="role.roleId" class="roleId"/>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary btn-confirm">确定</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		</div>
	</div>
</body>

</html>
