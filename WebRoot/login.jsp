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

</head>
<body>

	<div id="container"> 	
  	<div class="main">
  		<div class="row-fluid">
  			<div class="span4"></div>
        
  			<div class="login span4">
          <div class="inner well">
            <form action="userLogin" method="post">
              <h2>富民派遣系统</h2>
              <s:textfield name="wmsUser.phone" value="13809505940" label="%{getText('username')}" maxlength="13" placeholder="手机号码" cssClass="input-block-level"/>
              <s:password name="wmsUser.password" value="123" label="%{getText('password')}" maxlength="15" placeholder="密码"  cssClass="input-block-level"/>
       
              <label class="checkbox">
                <input type="checkbox" value="remember-me"/>请记住我  
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
 