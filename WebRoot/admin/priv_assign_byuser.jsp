<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>" />
    <title>权限分配</title>
    <%@ include file="/help/public_css_js.jsp"%>
	<script type="text/javascript">
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true,
					pIdKey: "parentId"
				}
			}
		};
$(function(){

		$.fn.zTree.init($("#tree"), setting, ${privs}).expandAll(true);
		
		$("#selUser").click(function(){
			$.dialog({
				id:'selUser',
				content:'url:toSearchUser',
				width:'600px',
				height:'400px',
				title:'选择用户',
				lock:true,
				ok:function(){
					var selUser=$.dialog.list['selUser'].content.selUser;
					$("#userId").val(selUser[0]);
					$("#username").val(selUser[1]);
				},
				cancel:true
			});
		});
		
		
		$("#ok").click(function(){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			//获得所有选择的复选框
			var nodes = treeObj.getCheckedNodes(true);
			for(var i=0;i<nodes.length;i++){
				var newPriv="<input type='text' name='prives' value='"+nodes[i].id+"'>";
				$("#selPrives").append(newPriv);
			}
			$("#priv").submit();
		});
		
		<s:if test=" not empty #request.msg">
			$.dialog.tips("${msg}",2);
		</s:if>
	});
	</script>
  </head>
  
  <body>
  	<jsp:include page="priv_assign_nav.jsp"></jsp:include>
  	<br /><br />
  	<div id="main">
  	<h1>为用户指定角色或权限</h1>
	 <s:form method="post" action="addUserByRoleAndByPrivilege" id="priv">
		<div id="newdata">
			<table width="500" border="1">
				<tr>
					<td width="60">用&nbsp;&nbsp;&nbsp;户</td>
					<td><input type="hidden" name="userId" id="userId" size="30"/>
					<input type="text" name="name"  id="username" size="30" readonly="readonly"/>
					<input type="button" class="oprbtn" id="selUser"  value="选择用户" /></td>
				</tr>
				<tr>
					<td>角色列表</td>
					<td>
						<ul type="none">
							<s:iterator value="#request.roles" var="role">
								<li><input type="checkbox" name="roleIds" value="${role.id}" />${role.name}(${role.description})</li>	
							</s:iterator>
						</ul>
					</td>
				</tr>
				<tr>
					<td>权限列表</td>
					<td><ul id="tree" class="ztree"></ul></td>
				</tr>
			</table>
		</div>
		<div id="opr">
			<input type="button" value=" 确  定 " class="oprbtn" id="ok" /> <input
				type="button" value=" 取  消 " class="oprbtn" id="cancel"/>
		</div>
		<div id="selPrives"></div>
		</s:form>
		
	</div>
  </body>
</html>
