<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<title>在线用户</title>
<%@ include file="/help/public_css_js.jsp"%>
<script type="text/javascript" src="ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<link rel="stylesheet" href="ueditor/themes/default/css/ueditor.css" type="text/css"></link>

<script type="text/javascript">
var UEDITOR_HOME_URL=<%=basePath%>"ueditor";
var editor1,editor2;

var api=frameElement.api;

$(function(){
	
	var option1={
			 		toolbars:[]
			        ,initialFrameWidth:490 
			        ,initialFrameHeight:150
			        ,readonly:true
			        ,wordCount:false
			        ,elmentPathEnabled:false
			        ,autoHeightEnabled:true
	};
	
	editor1=new baidu.editor.ui.Editor(option1);
	editor1.render('render1');
	
	var option2={
	 		toolbars:[ ['fullscreen', 'source', '|', 'undo', 'redo', '|',
		                'bold', 'italic', 'underline', 'fontborder', 'strikethrough',
		                'directionalityrtl', 'indent', '|',
		                'searchreplace', 'help']]
	        ,initialFrameWidth:490 
	        ,initialFrameHeight:100
	        ,wordCount:false
	         ,elmentPathEnabled:false
	         ,autoHeightEnabled:true
	};
	
	editor2=new baidu.editor.ui.Editor(option2);
	editor2.render('render2');
	
	setTimeout(function(){
		for(var i=0;i<api.data.length;i++){
			var msg=api.data[i];
			editor1.setContent(editor1.getContent()+msg.fromUserAccount+" "+msg.sendTime+"<br />"+msg.msg);
		}
	},1000);
});

</script>
  </head>
  
  <body>
   <script id="render1"></script>
   <script id="render2"></script>
  </body>
</html>
