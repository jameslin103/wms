<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	  <%@taglib prefix="s" uri="/struts-tags" %>
      <ul class="user  normal clearfix">
        <li><a href="account/password.jsp"><s:property value="%{#session.user.username}" /></a></li>
        <li><a href="loginOut">退出</a></li>
      </ul>
      <div class="navbar">
        <div class="navbar-inner">
          <a class="brand" href="#">富民</a>
          <ul class="nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
             	   企业
                <b class="caret"></b>
              </a>
              <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                <li><a tabindex="-1" href="toBeResponsibleEnterprise">我的企业</a></li>
                <li><a tabindex="-1" href="#">所有企业</a></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                管理
                <b class="caret"></b>
              </a>
              <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                 <li><a tabindex="-1" href="viewEnterprise">企业相关</a></li>
                  <li><a tabindex="-1" href="toViewTaxRules">计税规则</a></li>
                  <li><a tabindex="-1" href="company/authorization.jsp">权限分配</a></li>            
              </ul>
            </li>
            
            <li><a href="all/company-list-with-salary.jsp">汇总</a></li>
            <li><a href="help/index.jsp">帮助</a></li>
          </ul>
        </div>        
      </div>
     <div id="sub-header" class="clearfix">
      <div class="date">
        	<%
				java.util.Date now = new java.util.Date();
				Date currentTime = new Date();
				java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
						"yyyy年MM月dd日 ");
				String dateString = formatter.format(currentTime);
				out.println(dateString);
			%>
      </div>
    </div>

		