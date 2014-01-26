<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>手机号码归属地查询</title>
<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>

<script type="text/javascript">
$(function(){
	  $("#phone").keyup(function(){
		  if($(this).val().length==7){
			  $.get("util/getAddress/"+$(this).val(),function(address){
				  $("#address").html(address);
			  });
		  }
	  });
});

</script>
</head>

<body>

 <div id="main">
  	<h1>手机号码归属地查询</h1>
		<div id="newdata">
			<table width="500" border="1">
				<tr>
					<td width="60">手机号码</td>
					<td><input type="text" name="phone" id="phone" size="30"/></td>
				</tr>
				<tr>
					<td>归属地</td>
					<td><br><span id="address"></span><br></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
