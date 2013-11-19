<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
   <title>JavaScript提示框</title>

   <script type="text/javascript"> 
    
   /** 
   * 类名：CLASS_MSN_MESSAGE 
   * 功能：提供类似MSN消息框 
   * 示例： 
   * var MSG = new CLASS_MSN_MESSAGE("aa",200,120,"短消息提示：","您有1封消息","今天请我吃饭哈"); 
   * MSG.show(); 
    * 消息构造 
   */ 
   function CLASS_MSN_MESSAGE(id,width,height,caption,title,message,target,action){ 
      this.id       = id; 
      this.title    = title; 
      this.caption = caption; 
      this.message = message; 
      this.target   = target; 
      this.action   = action; 
      this.width    = width?width:200; 
      this.height   = height?height:120; 
      this.timeout = 150; 
      this.speed    = 20; 
      this.step     = 1; 
      this.right    = screen.width -1; 
      this.bottom   = screen.height; 
      this.left     = this.right - this.width; 
      this.top      = this.bottom - this.height; 
      this.timer    = 0; 
      this.pause    = false;
      this.close    = false;
      this.autoHide = true;
   } 
  
   /**
   * 隐藏消息方法 
   */ 
   CLASS_MSN_MESSAGE.prototype.hide = function(){ 
      if(this.onunload()){ 
          var offset = this.height>this.bottom-this.top?this.height:this.bottom-this.top; 
          var me = this; 
          if(this.timer>0){   
              window.clearInterval(me.timer); 
          } 
  
          var fun = function(){ 
              if(me.pause==false||me.close){
                  var x = me.left; 
                  var y = 0; 
                  var width = me.width; 
                  var height = 0; 
                  if(me.offset>0){ 
                      height = me.offset; 
                  } 
       
                  y = me.bottom - height; 
                  if(y>=me.bottom){ 
                      window.clearInterval(me.timer); 
                      me.Pop.hide(); 
                  } else { 
                      me.offset = me.offset - me.step; 
                  } 
                  me.Pop.show(x,y,width,height);    
              }             
          } 
          this.timer = window.setInterval(fun,this.speed)      
      } 
   } 
  
   /** 
   * 消息卸载事件，可以重写 
   */ 
   CLASS_MSN_MESSAGE.prototype.onunload = function() { 
      return true; 
   } 
   /**
   * 消息命令事件，要实现自己的连接，请重写它 
   */ 
   CLASS_MSN_MESSAGE.prototype.oncommand = function(){ 
      this.close = true;
      this.hide(); 
    window.open("http://www.lost63.com");
   } 
   /** 
   * 消息显示方法 
   */ 
   CLASS_MSN_MESSAGE.prototype.show = function(){ 
      var oPopup = window.createPopup(); //IE5.5+ 
      this.Pop = oPopup; 
  
      var w = this.width; 
      var h = this.height; 
  
    // "×"关闭按钮
 
          str += "<tr>" 
          str += "<td style='font-size: 12px;color: #0f2c8c' width=30 height=24></td>" 
          str += "<td style='padding-left: 4px; font-weight: normal; font-size: 12px; color: #1f336b; padding-top: 4px' valign=center width='100%'>" + this.caption + "</td>" 
          str += "<td style='padding-right: 2px; padding-top: 2px' valign=center align=right width=19>" 
          str += "<span title=关闭 style='font-weight: bold; font-size: 12px; cursor: hand; color: red; margin-right: 4px' id='btsysclose' >×</span></td>" 
          str += "</tr>"

    // 按钮style
    // style='width:60px; height:20px; border-right: #002D96 1px solid; padding-right: 2px; border-top: #002D96 1px solid; padding-left: 2px; FONT-SIZE: 12px; filter: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); border-left: #002D96 1px solid; CURSOR: hand; color: black; padding-top: 2px; border-bottom: #002D96 1px solid;'
   
      var str = "<div style='border-right: #455690 1px solid; border-top: #a6b4cf 1px solid; z-index: 99999; left: 0px; border-left: #a6b4cf 1px solid; width: " + w + "px; border-bottom: #455690 1px solid; position: absolute; top: 0px; height: " + h + "px; background-color: #c9d3f3'>" 
          str += "<table style='border-top: #ffffff 1px solid; border-left: #ffffff 1px solid' cellspacing=0 cellpadding=0 width='100%' bgcolor=#cfdef4 border=0>"
           
          str += "<tr>" 
          str += "<td style='font-size: 12px;color: #0f2c8c' width=30 height=24></td>" 
          str += "<td style='padding-left: 4px; font-weight: normal; font-size: 12px; color: #1f336b; padding-top: 4px' valign=center width='100%'>" + this.caption + "</td>" 
          str += "</tr>"
          str += "<tr>" 
          /*height：设置里面内容区域的高度 */
          str += "<td style='padding-right: 1px;padding-bottom: 1px' colspan=3 height=" + (h-50) + ">" 
          str += "<div style='border-right: #b9c9ef 1px solid; padding-right: 8px; border-top: #728eb8 1px solid; padding-left: 8px; font-size: 12px; padding-bottom: 8px; border-left: #728eb8 1px solid; width: 100%; color: #1f336b; padding-top: 8px; border-bottom: #b9c9ef 1px solid; height: 100%'>" + this.title + "<br><br>" 
          str += "<div style='word-break: break-all' align=left><a href='javascript:void(0)' hidefocus=false id='btcommand'><font color=#ff0000>" + this.message + "</font></a> - <a href='tencent://message/?uin=35501547&site=http://hi.baidu.com/lupeng0527&menu=yes' hidefocus=false id='ommand'><font color=#ff0000>有事您q我!</font></a></div>" 
          str += "</div>" 
          str += "</td>" 
          str += "</tr>" 
          str += "<tr align=center>"
          str += "<td colspan=3>"
          str += "<div style='padding: 2 0 2 0;'>"
          str += "<span id='buttonClose'><input type='button' title=关闭 value=确认 style='width:60px; height:20px; border-right: #002D96 1px solid; padding-right: 2px; border-top: #002D96 1px solid; padding-left: 2px; FONT-SIZE: 12px; filter: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#FFFFFF, EndColorStr=#9DBCEA); border-left: #002D96 1px solid; CURSOR: hand; color: black; padding-top: 2px; border-bottom: #002D96 1px solid;'></span>" 
          str += "</div>"
          str += "</td>"
          str += "</tr>"
          str += "</table>" 
          str += "</div>" 

      oPopup.document.body.innerHTML = str; 
      this.offset = 0; 
      var me = this; 
  
      oPopup.document.body.onmouseover = function(){me.pause=true;}
      oPopup.document.body.onmouseout = function(){me.pause=true;}
  
      var fun = function(){ 
          var x = me.left; 
          var y = 0; 
          var width      = me.width; 
          var height     = me.height;
  
              if(me.offset>me.height){ 
                  height = me.height; 
              } else { 
                  height = me.offset; 
              }
  
          y = me.bottom - me.offset; 
          if(y<=me.top){ 
              //me.timeout--; 
              if(me.timeout==0){ 
                  window.clearInterval(me.timer); 
                  if(me.autoHide){
                      me.hide(); 
                  }
              } 
          } else { 
              me.offset = me.offset + me.step; 
          } 
          me.Pop.show(x,y,width,height);   
      } 
  
      this.timer = window.setInterval(fun,this.speed)      
   
    //"确认"关闭事件
   
      var buttonClose = oPopup.document.getElementById("buttonClose"); 
      buttonClose.onclick = function(){ 
          me.close = true;
          me.hide(); 
      }
      
      /* 
       * "×"关闭事件
       * var btClose = oPopup.document.getElementById("btSysClose"); 
    *
       * btClose.onclick = function(){ 
       *   me.close = true;
       *   me.hide(); 
       * } 
       */
  
      var btCommand = oPopup.document.getElementById("btCommand"); 
      btCommand.onclick = function(){ 
          me.oncommand(); 
      }    
    var ommand = oPopup.document.getElementById("ommand"); 
      ommand.onclick = function(){ 
      this.close = true;
      me.hide(); 
   window.open(ommand.href);
      }   
   } 
   /* 
   * 设置速度方法 
   */ 
   CLASS_MSN_MESSAGE.prototype.speed = function(s){ 
      var t = 20; 
      try { 
          t = praseInt(s); 
      } catch(e){} 
      this.speed = t; 
   } 
   /** 
   * 设置步长方法 
   */ 
   CLASS_MSN_MESSAGE.prototype.step = function(s){ 
      var t = 1; 
      try { 
          t = praseInt(s); 
      } catch(e){} 
      this.step = t; 
   } 
  
   CLASS_MSN_MESSAGE.prototype.rect = function(left,right,top,bottom){ 
      try { 
          this.left        = left    !=null?left:this.right-this.width; 
          this.right        = right    !=null?right:this.left +this.width; 
          this.bottom        = bottom!=null?(bottom>screen.height?screen.height:bottom):screen.height; 
          this.top        = top    !=null?top:this.bottom - this.height; 
      } catch(e){} 
   }
   var MSG1 = new CLASS_MSN_MESSAGE("aa",240,150,"管理员：","思路决定出路","迷失路上"); 
      MSG1.rect(null,null,null,screen.height-50); 
      MSG1.speed    = 10; 
      MSG1.step    = 5; 
      alert(MSG1.top); 
      MSG1.show(); 
   //同时两个有闪烁，只能用层代替了，不过层不跨框架
   /* 
     var MSG2 = new CLASS_MSN_MESSAGE("aa",200,120,"短消息提示：","您有2封消息","好的啊"); 
     MSG2.rect(100,null,null,screen.height); 
     MSG2.show();
   */
    
   /**
   * 显示提示框
   */ 
   function showMSG(){
    MSG1.show();
   }
   
   </script>
   </head>

<body>
<center>
   <bgsound   src="" id="bs" loop="no">   
   <input type="button" value="按钮" onclick="showMSG(); ">
   <br>
   <hr>
</center>
</body>
</html>