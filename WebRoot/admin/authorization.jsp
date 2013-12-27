<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
		$("#add-System").on("hidden",function(){
			$("form",this)[0].reset();
			$("#username").text("*");
			$("#password").text("*");
			$(".is_Phone").text(" ");
		});
		
			$("input[name='wmsUser.phone']").blur(function()
			{
				var phone=$("input[name='wmsUser.phone']").val();
				if(phone!="")
				{
					$.getJSON("isExitPhone",{"wmsUser.phone":phone}).success(function(data){
						if(data=="此号码已存在!"){
							$(".is_Phone").text('');
							$(".is_Phone").text(data);
							$(".is_Phone").css("color","red");
						}else{
							$(".is_Phone").text('');
							$(".is_Phone").text(data);
							$(".is_Phone").css("color","blue");
						}
					});
				}
			});
			$("#sub").click(function (){
				var username=$("input[name='wmsUser.username']").val();
				var password=$("input[name='wmsUser.password']").val();
				var phone=$("input[name='wmsUser.phone']").val();
				if(username=="" && password=="" && phone==""){
					$("#username").text("*");
					$("#password").text("*");
					$("#username").text("用户名必填项!");
					$("#password").text("密码必填项!");
					$(".is_Phone").text("手机号码必填项!");
					$(".is_Phone").css("color","red");
					$("#password").css("color","red");
					$("#username").css("color","red");
					return false;
				}
				if(username!="" && password==""){
					$("#username").text(" ");
					$("#password").text(" ");
					$("#password").text("密码必填项!");
					$("#password").css("color","red");
					return false;
				}
				if(username=="" && password!=""){
					$("#username").text(" ");
					$("#password").text(" ");
					$("#username").text("用户名必填项!");
					$("#username").css("color","red");
					return false;
				}
				if(username=="" && password==""){
					$("#username").text("*");
					$("#password").text("*");
					$("#username").text("用户名必填项!");
					$("#password").text("密码必填项!");
					$("#password").css("color","red");
					$("#username").css("color","red");
					return false;
				}
			
			});
	});
</script>
</head>
<body>
	<div id="container">
		<div id="header">
		</div>
		<div id="main">
			<div class="row-fluid">
				<div id="center-pane">
					<ul class="nav nav-tabs">
						<li class="active"><a href="toAuthorizationUser">权限分配</a></li>
						<li><a href="toRoleManage">角色管理</a></li>
					</ul>
					<ul class="normal action-container clearfix">
						<li><a href="#add-permission" data-toggle="modal">为员工分配权限</a></li>
						<li><a href="#add-System" data-toggle="modal">增加操作系统员工</a></li>
					</ul>
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th align="center">序</th>
								<th align="center">姓名</th>
								<th align="center">权限</th>
								<th align="center">修改</th>
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
	<!-- add permission -->
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
						<s:select id="user-select-list" list="wmsUserList" name="userIds" listKey="userId" listValue="username" headerKey="" headerValue="请选择"></s:select>
					</div>
					<div class="input-container">
						<ul class="list-of-items-for-delete normal clearfix">
						</ul>
					</div>
					<hr />
					<s:iterator value="roleList" var="role">
						<div class="input-container">
							<input type="checkbox" name="roleIds" class="role-id" value="<s:property value="roleId"/>" /><s:property value="name"/>
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
	<!-- add WmsUser -->
		<div id="add-System"
		class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">添加员工</h3>
		</div>
		<div class="modal-body">
			<form action="addWmsUser" class="navbar-form pull-left" method="post">
				<div class="row-fluid">
					<label>用户名:</label>
					<div class="input-container">
						<input type="text" name="wmsUser.username"/><span id="username" style="color:red">*</span>
					</div>
					<label>密    码:</label>
					<div class="input-container">
						<input type="text" name="wmsUser.password"/><span id="password" style="color:red">*</span>
					</div>
					<label>手机号码:</label>
					<div class="input-container">
						<input type="text" name="wmsUser.phone" 
						onkeyup="value=value.replace(/[^\d]/g,'')" id="text" 
						onbeforepaste="clipboardData.setData('wmsUser.phone',clipboardData.getData('text').replace(/[^\d]/g,''))"/><span style="color:red">*</span>
						<span class="is_Phone"></span>
					</div>
					<label>邮     箱:</label>
					<div class="input-container">
						<input type="text" name="wmsUser.email"/>
					</div>
					<hr />
					<div class="input-container">
						<button type="submit" id="sub" class="btn btn-primary">提交</button>
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		</div>
	</div>
	
	
	
	<!-- endit User -->
	<div id="edit-user" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3 id="myModalLabel">修改权限分配</h3>
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
								<input type="radio" name="roleIds" class="role-id" value="<s:property value="roleId"/>" /><s:property value="name"/>
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
