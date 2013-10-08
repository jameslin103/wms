<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page isErrorPage="true"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<%

    response.setHeader("Cache-Control","no-cache");

    response.setHeader("Cache-Control","no-store");

    response.setDateHeader("Expires",0);

    response.setHeader("Pragma","no-cache");

%>

<html>

<head>

<script language="javascript">

function showContent(){

    if(document.getElementById("errorMessage").style.display == 'block'){

       document.getElementById("errorMessage").style.display = 'none';

    }else{

       document.getElementById("errorMessage").style.display = 'block';

    }

}

</script>

</head>

<body scroll="auto">

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">

<tr>

    <td align="center" class="bg" valign="top">

       <table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;word-break:break-all;">

           <tr>

              <td align="center" width="100%" height="80">
                  <!--<img src="${pageContext.request.contextPath}/images/fullname.jpg" border="0" align="absmiddle">--> 
                  <s:property value="exception.message" />  
              </td>

           </tr>

           <tr>

              <td height="30" align="center">

                  <a href="#" onclick="javascript:history.go(-1);"><s:text name="global.return"/></a>&nbsp; &nbsp;

                  <a href="#" onclick="javascript:showContent();">查看详细信息</a>

              </td>

           </tr>

           <tr>

              <td align="left" valign="top">

                  <!-- 异常堆栈信息(开发人员用) -->

                  <div style="display:none;" id="errorMessage">

                  <pre><s:property value="exceptionStack" /></pre>

                  </div>

              </td>

           </tr>

       </table>

    </td>

</tr>
</table>
</body>
</html>