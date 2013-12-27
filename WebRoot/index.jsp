<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>登录富民派遣系统</title>
 <base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp" %>
<link rel="shortcut icon" href="images/favicon.ico" />
<script type="text/javascript">
	$(function() {
		alert("xxx");
		$("#phone").keydown(function() {
			if (event.keyCode == 13) {
				$("#loginform").submit();
			}
		});
		$("#password").keydown(function() {
			if (event.keyCode == 13) {
				$("#loginform").submit();
			}
		});
		$("#login").click(function() {
			$("#loginform").submit();
		});
		$("#reset").click(function() {
			$("#phone").val("");
			$("#password").val("");
		});
		<c:if test="${not empty msg}">
		$.dialog.alert("${msg}");
		</c:if>
	});
</script>
<style type="text/css">
#login {
	width: 40px;
	height: 22px;
	background-image: url('images/dl.gif');
	background-position: 0 0px;
	border-width: 0px
}

#reset {
	width: 40px;
	height: 22px;
	background-image: url('images/dl.gif');
	background-position: -40px 0px;
	border-width: 0px
}
</style>
</head>
<body>
	<form action="userLogin" method="post" id="loginform">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td bgcolor="#e5f6cf">&nbsp;</td>
			</tr>
			<tr>
				<td height="608" background="images/login_03.gif"><table
						width="862" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="266" background="images/login_04.gif">&nbsp;</td>
						</tr>
						<tr>
							<td height="95">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="424" height="95" background="images/login_06.gif">&nbsp;</td>
										<td width="183" background="images/login_07.gif"><table
												width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="21%" height="30"><div align="center">
															<span class="STYLE3">手机号码</span>
														</div>
													</td>
													<td width="79%" height="30">
													 <input type="text" id="phone" name="wmsUser.phone"  maxlength="13" placeholder="手机号码" class="input-block-level"
														style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;">
													</td>
												</tr>
												<tr>
													<td height="30"><div align="center">
															<span class="STYLE3">密码</span>
														</div>
													</td>
													<td height="30">
													<input type="password" id="password" name="wmsUser.password"   maxlength="15" placeholder="密码"  class="input-block-level"
														style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;">
													</td>
												</tr>
												<tr>
													<td height="30">&nbsp;</td>
													<td height="30">
													<input type="button" id="login">
													<input type="button" id="reset"></td>
												</tr>
											</table>
										</td>
										<td width="255" background="images/login_08.gif">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="247" valign="top" background="images/login_09.gif"><table
									width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="22%" height="30">&nbsp;</td>
										<td width="56%">&nbsp;</td>
										<td width="22%">&nbsp;</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td height="30"><table width="100%" border="0"
												cellspacing="0" cellpadding="0">
												<tr>
													<td width="44%" height="20">&nbsp;</td>
													<td width="56%" class="STYLE4"></td>
												</tr>
											</table>
										</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td bgcolor="#a2d962">&nbsp;</td>
			</tr>
		</table>
	</form>
</html>