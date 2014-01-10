<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>部门管理</title>
<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/wms.css"/>

<script type="text/javascript">
var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit: {
			renameTitle:'修改部门名',
			removeTitle:'删除部门',
			enable: true,
			editNameSelectAll: true
		},
		data: {
			key: {
				children: "children",
				name: "name",
				title: "",
				url: "link"
			},
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentId",
				rootPId: null
			},
			keep: {
				parent: false,
				leaf: false
			}
		},
		callback: {
			/*beforeDrag: beforeDrag,
			beforeEditName: beforeEditName,
			*/
			onRemove: onRemove,
			beforeRename: beforeRename,
			beforeRemove: beforeRemove,
			onRename: onRename
		}
	};
	
	function beforeRename(treeId, treeNode, newName){
		if(newName==treeNode.name){
			var zTree = $.fn.zTree.getZTreeObj("tree");
			zTree.refresh();
			return false;
		}
	}
	function beforeRemove(treeId, treeNode, newName)
	{
			var zTree = $.fn.zTree.getZTreeObj("tree");
			zTree.refresh();
			return false;
	}
	function onRemove(event, treeId, treeNode)
	{
		$.dialog.confirm("您确认删除吗？",function(){
			var dept={
				"id":treeNode.id,
				"name":treeNode.name
			};
			$.ajax({
				url:'deleteDepartment?department.id='+treeNode.id,
				type:'post',
				contentType:'application/json',
				dataType:'html',
				success:function(id){
					//var zTree = $.fn.zTree.getZTreeObj("tree");
					//zTree.addNodes(treeNode, {id:id, pId:treeNode.id, name:dept.name});
					$.dialog.tips("删除成功",2);
				}
			});
		});
	
	}
	function onRename(event, treeId, treeNode){
		$.dialog.confirm("您确认修改吗？",function(){
			var dept={
				"id":treeNode.id,
				"name":treeNode.name
			};
			$.ajax({
				url:'updateDepartment?department.id='+treeNode.id+'&name='+treeNode.name,
				type:'post',
				//data:JSON.stringify(dept),
				contentType:'application/json',
				dataType:'html',
				success:function(id){
					//var zTree = $.fn.zTree.getZTreeObj("tree");
					//zTree.addNodes(treeNode, {id:id, pId:treeNode.id, name:dept.name});
					$.dialog.tips("修改成功",2);
				}
			});
		});
	}
	

function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.id).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.id
		+ "' title='新增部门' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.id);
	if (btn) btn.bind("click", function(){
		$.dialog({
			id:"adddept",
			content:"url:admin/dept_add.jsp",
			title:'新增'+treeNode.name+'子部门',
			width:'500px',
			height:'50px',
			resizable:false,
			ok:function(){
				var name=$.dialog.list['adddept'].content.$("#name").val();
				var parentId=treeNode.id;
				var dept={
					"department.parentId":treeNode.id,
					"name":$.dialog.list['adddept'].content.$("#name").val()
				};
				$.ajax({
					url:'addDepartment?department.parentId='+parentId+'&name='+name,
					type:'post',
					data:JSON.stringify(dept),
					contentType:'application/json',
					dataType:'html',
					success:function(id){
						var zTree = $.fn.zTree.getZTreeObj("tree");
						zTree.addNodes(treeNode, {id:id, pId:treeNode.id, name:dept.name});
						$.dialog.tips("增加成功",2);
					}
				});
			},
			cancelValue:'关闭',
			cancel:true
			
		});
		return false;
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.id).unbind().remove();
};


	$(document).ready(function(){
		$.ajax({
				  type:"get",
				  url:"jsonDepartments",
				  async:false,
				  dataType:"json",
				  success:function(depts){
					$.fn.zTree.init($("#tree"),setting,JSON.parse(depts)).expandAll(true);
				}
		   });
	});

</script>
</head>
<body>
<div id="main">
  	<h1>部门管理</h1>
		<div id="newdata">
			<table width="700" border="1">
				<tr>
					<td><ul id="tree" class="ztree"></ul></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
