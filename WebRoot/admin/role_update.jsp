<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改角色</title>
<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>
<script type="text/javascript">
	var setting = {
		check: {
			enable: true,
		},
		data: {
			simpleData: {
				enable: true,
				pIdKey: "parentId"
			}
		},
		callback:{
            beforeClick:zTreeBeforeClick
        }
	};
	
	function zTreeBeforeClick(treeId, treeNode, clickFlag) {
				alert(treeId);
                var zTree = $.fn.zTree.getZTreeObj("tree");
                alert(zTree);
                if (treeNode.isParent) {
                    //zTree.expandNode(treeNode);
                    return false;
                } else {
                    treeNode.checked = true;
  
                    return true;
                }

   			 return (treeNode.id !== 1);
	};
	
	
	$(function(){
		
		$.fn.zTree.init($("#tree"), setting, ${privs}).expandAll(true);
		
		$("#new").click(function(){
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			//获得所有选择的复选框
			var nodes = treeObj.getCheckedNodes(true);
			for(var i=0;i<nodes.length;i++){
				var newPriv="<input type='text' name='prives' value='"+nodes[i].id+"'>";
				$("#selPrives").append(newPriv);
			}
			$("#role").submit();
		});
	});
</script>
</head>

<body>
 <div id="main">
  	<h1>修改角色</h1>
	<s:form  action="updateRole"   method="post" >
		<div id="newdata">
			<table width="500" border="1">
				<tr>
					<td width="60">角色名</td>
					<td><input type="text" name="role.name" value="${role.name}" size="30"/><s:fielderror name="name"></s:fielderror></td>
				</tr>
				<tr>
					<td>角色描述</td>
					<td><textarea name="role.description" id="description"  cols="27">${role.description}</textarea></td>
				</tr>
				<tr>
					<td>分配权限</td>
					<td><ul id="tree" class="ztree"></ul></td>
				</tr>
			</table>
		</div>
		<div id="opr">
			<input type="button" value=" 修  改 " class="oprbtn" id="new" /> 
			<input type="button" value=" 取  消 " class="oprbtn" id="cancel"/>
		</div>
		<div id="selPrives"></div>
		</s:form>
	</div>
</body>
</html>
