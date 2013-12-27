<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>增加新用户</title>
 <base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp" %>
<script type="text/javascript">

	function validateAccount(){
		var isExitUser=true;
		if($("#account").val()==""){
			$("#accountmsg").html("用户帐号不能为空");
			$("#accountmsg").addClass("input_error");
			return false;
		}else if($("#account").val().length<3 || $("#account").val().length>15){
			$("#accountmsg").html("用户帐号长度不能为小于3或者大于15");
			$("#accountmsg").addClass("input_error");
			return false;
		}else{
			/*
			$.get("user/isexist/"+$("#account").val(),function(r){
				if(r==true){
					$("#accountmsg").html("用户帐号已经存在");
					$("#accountmsg").addClass("input_error");
					b= false;
				}else{
					$("#accountmsg").html("&nbsp;");
					$("#accountmsg").addClass("input_ok");
				}
			});*/
			$.ajax({
				  type:"get",
				  url: "isExistUser?user.account="+$("#account").val(),
				  async:false,
				  success:function(date){
						if(date==true){
							$("#accountmsg").html("用户帐号已经存在");
							$("#accountmsg").addClass("input_error");
							isExitUser= false;
						}else{
							$("#accountmsg").html("&nbsp;");
							$("#accountmsg").addClass("input_ok");
						}
				}
		   });
		}
		return isExitUser;
	}
	
	function validatePassword(){
		if($("#password").val()==""){
			$("#passwordmsg").html("用户密码不能为空");
			$("#passwordmsg").addClass("input_error");
			return false;
		}
		return true;
	}
	
	$(function(){
		$("#account").blur(validateAccount);
		$("#password").blur(validatePassword);
		$("#userform").submit(function(){
			var t=validatePassword();
			var b=validateAccount();
			return (b && t);
		});
		
		$("#selEmp").click(function(){
			$.dialog({
				id:'selEmp',
				content:'url:admin/emp_sel.jsp',
				width:'600px',
				height:'400px',
				title:'选择员工',
				lock:true,
				ok:function(){
					var selEmp=$.dialog.list['selEmp'].content.selEmp;
					$("#empid").val(selEmp[0]);
					$("#empName").val(selEmp[1]);
				},
				cancel:true
			});
		});
	});
</script>
</head>

<body>
 <div id="main">
  	<h1>增加新用户</h1>
	<form method="post" action="addWmsUser" id="userform">
		<div id="newdata">
			<table width="500" border="1">
				<tr>
					<td width="80">用户账号:</td>
					<td><input type="text" name="user.account"  id="account" size="30"/><span id="accountmsg"></span></td>
				</tr>
				<tr>
					<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
					<td><input type="password" name="user.password" id="password" size="30"><span id="passwordmsg"></span></input></td>
				</tr>
				<tr>
					<td>确认密码:</td>
					<td><input type="password" name="cfmpassword" id="cfmpassword" size="30"><span id="cfmpwdmsg"></span></input></td>
				</tr>
				<tr>
					<td>分配给员工:</td>
					<td><input type="hidden" name="employee.id" id="empid">
					<input type="text" id="empName" readonly="readonly">
					<input type="button" value=" 选 择 " class="oprbtn" id="selEmp"></td>
				</tr>
			</table>
		</div>
		<div id="opr">
			<input type="submit" value="新  增 " class="oprbtn" id="new" /> <input
				type="button" value=" 取  消 " class="oprbtn" id="cancel"/>
		</div>
		</form>
	</div>
</body>
</html>
