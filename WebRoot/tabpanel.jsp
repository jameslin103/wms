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
<base href="<%=basePath%>" />
<title>选项卡</title>
<%@ include file="/help/public_css_js.jsp"%>
<link type="text/css" href="<%=basePath%>styles/tabpanel.css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>js/Math.uuid.js"></script>
<script type="text/javascript" src="<%=basePath%>js/tabpanel.js"></script>

<script type="text/javascript">
	var tabpanel;
	$(document).ready(function() {
						tabpanel = new TabPanel(
								{
									widthResizable : true,
									heightResizable : true,
									autoResizable : true,
									width : '100%',
									height : document.documentElement.clientHeight,
									active : 0,
									items : [ {
										id : 'toolbarPlugin',
										title : '我的桌面',
										html : '<iframe name="ifrmMain" src="center.jsp" width="100%" frameborder="0" height="99%"></iframe>',
										closable : false
									} ]
								});
					});
	function addTab(tabTitle, url) {
		var tabs = tabpanel.tabs;
		var isAdded=false;
		var tabId;
		for (var i=0; i<tabs.length; i++) {
			if (tabs[i].title.text()==tabTitle) {
				isAdded = true;
				tabId = tabs[i].id;
				break;
			}
		}
		if (tabs.length >= 8) {
			$.dialog.alert("选项卡不能超出8个，请关闭多余的选项卡！");
			return;
		}

		if (!tabId) {
			tabpanel.addTab({
						title : tabTitle,
						html : '<iframe name="ifrmMain" src='
								+ url
								+ ' width="100%" height="99%" frameborder="0"></iframe>'
					});
		} else {// 存在相同ID，则激活
			tabpanel.addTab({
						id : tabId,
						title : tabTitle,
						html : '<iframe name="ifrmMain" src='+ url+ ' width="100%" height="100%" frameborder="0"></iframe>'
					});
			var position = tabpanel.getTabPosision(tabId);
			var iframes = tabpanel.tabs[position].content.find('iframe');
			iframes[0].src = iframes[0].src;
			alert(iframes[0].src);
		}
	}
</script>
</head>
</html>
