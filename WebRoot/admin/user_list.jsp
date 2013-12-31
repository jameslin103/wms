<%@ page language="java" pageEncoding="UTF-8"%>
  <%
	String path = request.getContextPath();String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>用户列表</title>
<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache"/> 
		<meta http-equiv="cache-control" content="no-cache"/> 
		<meta http-equiv="expires" content="0"/>  
<%@ include file="/help/public_css_js.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/wms.css"/>
<script type="text/javascript">
	$(function(){
		$("#new").click(function(){
			location.href="toAddUser";
		});
		$("#selall").click(function(){
			$(":checkbox[name=ids]").prop("checked",$(this).prop("checked"));
		});
		$(":checkbox[name=ids]").click(function(){
			var b=true;
			$(":checkbox[name=ids]").each(function(){
				if(!$(this).prop("checked")){
					b=false;
					return;
				}
			});
			$("#selall").prop("checked",b);
		});
		
	//	alert($("a[name=dels]").length);
		$("a[name=dels]").each(function(index,a){
			$(a).click(function(){
				alert($(a).prev().val());
				$.get("delUser?user.id="+$(a).prev().val(),function(){
					$(a).parent().parent().remove();
				});
			});
		});
	});
</script>
</head>

<body>
	<div id="main">
		<fieldset>
			<legend>
				<img src="images/311.gif" />&nbsp;用户列表
			</legend>
			
			<div id="datalist">
				<table border="1px;">
					<thead>
						<tr id="tableheader">
							<th width="60"><input type="checkbox" id="selall"/>选择</th>
							<th width="60">序号</th>
							<th width="80">用户账号</th>
							<th width="80">员工姓名</th>
							<th width="80">用户状态</th>
							<th width="160">最后登陆时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.users" id="user" status="s">
							<tr>
								<td class="center"><input name="ids" type="checkbox" value="<s:property value="%{#user.userId}"/>" /></td>
								<td class="center"><s:property value="#s.index+1"/></td>
								<td class="center"><s:property value="%{#user.account}"/> </td>
								<td class="center">
									<a href="" title="详细信息">
									<s:property value="#user.employee.name"/></a>
								</td>
								<td class="center"><s:property value="%{#user.status}"/></td>
								<td class="center"><s:date name="%{#user.lastLoginTime}" format="yyyy-MM-dd HH:mm:ss"/></td>
								
								<td class="center">
									<img src="images/037.gif" width="9" height="9" />[<a href="toUpdateUser?user.id=<s:property value="%{#user.id}"/>">编辑</a>]&nbsp;
									<img src="images/010.gif" width="9" height="9" />[<input type="hidden" value="<s:property value="%{#user.id}"/>"><a href="javascript:void(0)" name="dels">删除</a>]
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div id="opr">
				<input type="button" value=" 新  增 " class="oprbtn" id="new" /> <input
					type="button" value=" 删  除 " class="oprbtn" />
			</div>
		</fieldset>
	</div>
</body>
</html>

