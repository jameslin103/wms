<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>增加新角色</title>
<base href="${base}" />
<%@ include file="/help/public_css_js.jsp"%>
<link rel="stylesheet" href="styles/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="js/jquery.ztree.all-3.4.js"></script>
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
		
		$.fn.zTree.init($("#tree"), setting, ${privileges}).expandAll(true);
		
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
  	<h1>增加新角色</h1>
	<s:form  action="addWmsRole"   method="post" >
		<div id="newdata">
			<table width="500" border="1">
				<tr>
					<td width="60">角色名</td>
					<td><input type="text" name="name"  size="30"/><s:fielderror name="name"></s:fielderror></td>
				</tr>
				<tr>
					<td>角色描述</td>
					<td><textarea name="description" id="description" cols="27"></textarea></td>
				</tr>
				<tr>
					<td>分配权限</td>
					<td><ul id="tree" class="ztree"></ul></td>
				</tr>
			</table>
		</div>
		<div id="opr">
			<input type="button" value=" 新  增 " class="oprbtn" id="new" /> 
			<input type="button" value=" 取  消 " class="oprbtn" id="cancel"/>
		</div>
		<div id="selPrives"></div>
		</s:form>
	</div>
</body>
</html>
