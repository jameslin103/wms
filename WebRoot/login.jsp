<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>" />
    <%@ taglib uri="/struts-tags" prefix="s" %>
  <title>富民人力银行派遣系统</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
 	<%@ include file="/help/public_css_js.jsp" %>
	<script type="text/javascript">
		$(function(){
		    var	phone=$("input[name='wmsUser.phone']").val();
		    var password=$("input[name='wmsUser.password']").val();
			if(phone==""){
				$("phone").text("手机号码必填项!");
			}
			if(password==""){
				$("phone").text("密码必填项!");
			}

		});
	
	
	</script>
</head>
<body style="background-color: #0570ae;">

	<div id="container"> 	
  	<div class="main">
  		<div class="row-fluid">
  			<div class="span4">
  			</div>
        
  			<div class="login span4">
          <div class="inner well">
          	 
            <form action="userLogin" method="post">
            	
              <h2>富民派遣系统</h2>
              <input type="text"  name="wmsUser.phone"  maxlength="13" placeholder="手机号码" cssClass="input-block-level"/>
              <input type="password"  name="wmsUser.password"   maxlength="15" placeholder="密码"  cssClass="input-block-level"/>
       		   <span style="color:red;"><s:fielderror name="username"/></span>
              <label class="checkbox">
                 <s:checkbox label="自动登录" name="userCookie" value="true"></s:checkbox>请记住我  
              </label> 
              <button class="btn btn-large btn-primary" type="submit">登陆</button>
            </form>            
          </div>
  			</div>

			 <div class="span4"></div>
	   </div>
  	</div>  	
	</div>
</body>
</html>
 