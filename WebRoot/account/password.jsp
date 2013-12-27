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
		<script type="text/javascript">
		 function passwordVailte(){
		 	var oldPassword=$("#oldPassword").val();
		 	var password=$("#password").val();
		 	var comfitPassword=$("#comfitPassword").val();
		 	var newpassword=$("#newpassword").val();
		 	
		 		if(oldPassword==""){
		 			alert("旧密码不能为空!")
		 			return false;
		 		}
		 		if(newpassword==""){
		 			$("#span_newpassword").html("新密码不能为空!");
		 			return false;
		 		}
		 		if(comfitPassword==""){
		 			$("#span_comfitPassword").html("确认密码不能为空!");
		 			return false;
		 		}
		 		if(comfitPassword!=newpassword){
		 			alert("新密码两次输入不一样!")
		 			return false;
		 		}
		 
		 }
		
		
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
							<li class="active">
								<a href="employee-list.html">密码修改</a>
							</li>
						</ul>
						<div class="row-fluid">
							<div class="span3">
								<form action="updateWmsUserPassword" method="post">
									<input type="password" class="input-block-level" placeholder="旧密码" id="oldPassword" value="${user.password}" name="oldPassword" maxlength="20"/>
									<s:password cssClass="input-block-level" placeholder="新密码" id="newpassword" name="newPassword" maxlength="20"/>
									<s:password cssClass="input-block-level" placeholder="新密码（确认）" id="comfitPassword"  name="loginUser.password" maxlength="20"/>
									<s:submit cssClass="btn btn-primary"  value="提交" onclick="passwordVailte()"/>
								</form>
							</div>
						</div>

					</div>
				</div>
			</div>
			<div id="footer"></div>
		</div>


	</body>

</html>
