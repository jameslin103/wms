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
<title>无标题文档</title>
<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>
<style type="text/css">
	#back{
		width:47px;
		height: 23px;
		background-image: url('images/main_12.gif');
		background-position:-51px 0;
		border-width: 0px
	}
	#go{
		width:49px;
		height: 23px;
		background-image: url('images/main_12.gif');
		background-position:-98px 0;
		border-width: 0px
	}
	#info{
		width:104px;
		height: 23px;
		background-image: url('images/main_12.gif');
		background-position:-206px 0;
		border-width: 0px
	}
	#exit{
		width:58px;
		height: 23px;
		background-image: url('images/main_12.gif');
		background-position:-309px 0;
		border-width: 0px	
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#back").click(function(){
			history.go(-1);
		});
		$("#go").click(function(){
			history.go(1);
		});

		
		$("#info").click(function(){
			 window.top.frames['mainFrame'].$.dialog({
				id:'updatePwd',
			    content: 'url:account/password.jsp',
			    lock:true,
			    max: false,
			    min: false,
			    title:'修改个人密码',
			    width: '400px',
			    height:'200px',
			    //drag: false,
			    resize: false,
			    cancelVal: '关闭',
			    cancel: true,
			    ok:function(){
			    	window.top.frames['mainFrame'].$.dialog.list['updatePwd'].content.updatePwd();
			    	return false;
			    }
			});
		});
		$("#exit").click(function(){
			window.top.frames['mainFrame'].$.dialog.confirm("您确定退出吗？",function(){
				top.location.href="loginOut";
			});
		});
		
		$("#chat").click(function(){
			 window.top.frames['mainFrame'].$.dialog({
					id:'chat',
				    content: 'url:chat/chat.jsp',
				    max: false,
				    min: false,
				    title:'在线用户',
				    width: '250px',
				    height:'420px',
				    left:'95%',
				    resize: false,
				    cancelVal: '关闭',
				    cancel: function(){
				    	//获取所有打开的弹出窗口
				    		var dialogs=window.top.frames['mainFrame'].$.dialog.list;
				    	for(var id in dialogs){
				    		if(id.substring(0,5)=="chat-"){
				    			dialogs[id].close();
				    		}
				    	}
				    	window.top.frames['mainFrame'].$.dialog.list["chat"].content.ws.close();
				    }
				});
		});
		
		$.get("",function(provinces){
			for(var i=0;i<provinces.length;i++){
				$("#provinces").append("<option value="+provinces[i].code+">"+provinces[i].name+"</option>");
			}
		},'json');
		
		$("#provinces").change(function(){
			$("#cities").empty();
			$.get("util/getcites/"+$(this).val(),function(cities){
				for(var i=0;i<cities.length;i++){
					$("#cities").append("<option value="+cities[i].code+">"+cities[i].name+"</option>");
				}
			},'json');
		});
		
		$("#cities").change(function(){
			$.get("util/getweather/"+$(this).val(),function(weather){
				$("#weather").html(weather[4]);
			},'json');
		});
	}); 
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
  <tr>
    <td height="9" style="line-height:9px; background-image:url(images/main_04.gif)"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="97" height="9" background="images/main_01.gif">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="47" background="images/main_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="38" height="47" background="images/main_06.gif">&nbsp;</td>
        <td width="59"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="29" background="images/main_07.gif">&nbsp;</td>
          </tr>
          <tr>
            <td height="18" background="images/main_14.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;">
              <tr>
                <td  style="width:1px;">&nbsp;</td>
                <td ><span class="STYLE1">${user.username}</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="155" background="images/main_08.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><input type="button" id="back" /><input type="button" id="go" /><input type="button" id="info" /><input type="button" id="exit" /></a></td>
          </tr>
        </table></td>
        <td width="420" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="11%" height="23"><img src="images/chat.gif" alt="聊天" id="chat"></td>
            <!--
            <td width="60%" valign="bottom">
            	<select id="provinces">
            	</select>
            	<select id="cities">
            	</select>
            </td>
            --><td><span id="weather"></span></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" style="line-height:5px; background-image:url(images/main_18.gif)"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="180" background="images/main_16.gif"  style="line-height:5px;">&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
</html>
