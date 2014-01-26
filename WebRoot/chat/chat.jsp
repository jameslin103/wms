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
<script type="text/javascript" src="js/wmsutil.js"></script>
<script type="text/javascript">
	var ws;
	
	//连接WebSocket服务器
	function connect(){
	
		ws=new WebSocket("ws://localhost:8080/wms/getOnlineUsers?user.id=${session.user.id}");
		ws.onopen=onopen;
		//ws.onclose=onclose;
		ws.onmessage=onmessage;
		//ws.onerror=onerror;
	} 
	
	function openDialog(){
		var id=$(this).attr("id");
		window.top.frames["mainFrame"].$.dialog({
			id:"chat-"+id,
		    content: 'url:chat/dialog.jsp',
		    max: false,
		    min: false,
		    title:"与"+$(this).find("span").html()+"聊天中",
		    width: '500px',
		    height:'400px',
		    resize: false,
		    cancelVal: '关闭',
		    cancel: true,
		    okVal:'发送',
		    ok:function(){
		    	var msg={
		    		"fromUserId":"${session.user.id}",
		    		"fromUserAccount":"${sessionScope.user.account}",
		    		"toUserId":id,
		    		"msg":window.top.frames["mainFrame"].$.dialog.list["chat-"+id].content.editor2.getContent(),
		    		"type":"normal"
		    	};
		    	ws.send(JSON.stringify(msg));
		    	
		    	var d=window.top.frames["mainFrame"].$.dialog.list["chat-"+id].content;
		    	d.editor2.setContent("");
		    	d.editor1.setContent(d.editor1.getContent()+" 自己 "+getNow()+"<br />"+msg.msg);
		    	return false;
		    },
		    data:messages.get(id),
		    init:function(){
		    	$("#"+id).find(".msgnum").html("");
		    }
		});
	}
	
	//在成功连上WebSocket服务器的时候回调该函数
	function onopen(){
		$.dialog.tips("成功连接服务器",1,null,function(){
			$.get("getOnlineUsers",function(users){
				var li="";
				for(var i=0;i<users.length;i++){
					li+="<li id='"+users[i].id+"'><span>"+users[i].account+"</span><span class='msgnum'></span></li>";
				}
				$("#users").append(li);
				
				$("li").dblclick(openDialog);
				
			},"json");
		});
	}
	function onclose(){
		$.dialog.tips("成功断开服务器",1);
	}
	
	//存放没有显示在聊天窗口中的消息
	var messages= new Map();
	
	function onmessage(e){
		var msg=JSON.parse(e.data);
		if(msg.type=="system"){
			//系统消息，上线与下线
			if(msg.msg=="on"){
				//上线消息
				var li="<li id='"+msg.fromUserId+"'><span>"+msg.fromUserAccount+"<span><span class='msgnum'></span></li>";
				$("#users").append(li);
				$("#"+msg.fromUserId).dblclick(openDialog);
			}else{
				//下线消息
				$("#"+msg.fromUserId).remove();
				var d=window.top.frames['mainFrame'].$.dialog.list["chat-"+msg.fromUserId];
				if(d){
					d.close();
				}
			}
		}else{
			//普通消息
			//判断当前发送消息用户对应的聊天窗口是否已经打开
			if(window.top.frames["mainFrame"].$.dialog.list["chat-"+msg.fromUserId]){
				var d=window.top.frames["mainFrame"].$.dialog.list["chat-"+msg.fromUserId].content;
				d.editor1.setContent(d.editor1.getContent()+msg.fromUserAccount+" "+ msg.sendTime+"<br />"+msg.msg);
			}else{
				//没有打开
				var m=messages.get(msg.fromUserId);
				if(!m){
					m=[];
				}
				m.push(msg);
				messages.put(msg.fromUserId,m);
				$("#"+msg.fromUserId).find(".msgnum").html("("+m.length+")");
			}
		}
		
	}
	function onerror(e){
		$.dialog.tips("出错",1);
	}
	
	$(function(){
		connect();
	});
	
</script>
<style type="text/css">
	.msgnum{
		color:red
	}
</style>
  </head>
  
  <body>
    <ul id="users">
    </ul>
  </body>
</html>
