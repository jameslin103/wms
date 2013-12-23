<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>无标题文档</title>
<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>
<link rel="stylesheet" href="<%=basePath%>styles/zTreeStyle.css" type="text/css"/>
  <link rel="stylesheet" type="text/css" href="<%=basePath%>styles/wms.css"/>
<script type="text/javascript" src="<%=basePath%>js/jquery.ztree.all-3.4.js"></script>
<script type="text/JavaScript">
var setting = {
		data : {
			key: {
				children: "children",
				name: "name",
				title: "",
				url: "url"
			},
			simpleData: {
				enable: true,
				idKey: "menuId",
				pIdKey: "type",
				rootPId: null
			},
			keep: {
				parent: false,
				leaf: false
			}
		},
		callback : {
			onClick:function(event, treeId, treeNode) {
				if (!treeNode.isParent) {
					top.mainFrame.addTab(treeNode.name,treeNode.url);
				}
			}
		}
	};

	$(document).ready(function() {
		$.fn.zTree.init($("#tree"), setting,${menu});
	});
</script>
</head>

<body>
	<table width="177" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="26" background="images/main_21.gif">&nbsp;</td>
		</tr>
		<tr>
			<td>
				<ul id="tree" class="ztree"></ul>
			</td>
		</tr>
	</table>
</body>
</html>
