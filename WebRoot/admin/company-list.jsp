<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  <title>富民人力银行派遣系统</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
 	<%@ include file="/help/public_css_js.jsp" %>

</head>
<body>

	<div id="container">
		<div id="header">
			<ul class="user normal clearfix">
	        <li><a href="account/password.jsp"><s:property value="%{#session.user.username}"/></a></li>
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
                  <li><a tabindex="-1" href="../company/list.html">我的企业</a></li>
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
                  <li><a tabindex="-1" href="admin/tax.jsp">计税规则</a></li>
                  <li><a tabindex="-1" href="authorization.html">权限分配</a></li>
                </ul>
              </li>
              
              <li><a href="../all/company-list-with-salary.html">汇总</a></li>
              <li><a href="../help/index.html">帮助</a></li>
            </ul>
        </div>        
      </div>
    </div>
    <div id="sub-header" class="clearfix">
      <div class="date">
      		 <% 
				java.util.Date now=new java.util.Date(); 
				Date currentTime = new Date(); 
				java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy年MM月dd日 "); 
				String dateString = formatter.format(currentTime);
				out.println(dateString);
			 %> 
      </div>
     </div>		
	
	<div id="main"> 
      <div class="row-fluid">
        <div id="center-pane">
          <ul class="nav nav-tabs">
            <li class="active">
              <a href="#">所有企业</a>
            </li>         
          </ul>

          <ul class="normal action-container clearfix">
            <li><a href="#info-for-check1" data-toggle="modal">添加新企业</a></li>
          </ul>
		<!-- ======================================According to  Enterprise==================================== -->
		
          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th width="8%">序号</th>
                <th width="50%">企业</th>
                <th width="30%">负责人</th>
                <th width="12%">操作</th>
              </tr>
            </thead>
             <s:iterator value="#request.enterpriseList" var="enterprise">
            <tbody>
              <tr>
                <td><s:property value="%{#enterprise.id}"/></td>
                <td class="with-complement">	 
                  <s:property value="%{#enterprise.fullName}"/>
                  <span class="complement">
	                    <s:property value="%{#enterprise.contact}"/>
	                  	电话： <s:property value="%{#enterprise.phone}"/>
	                  	QQ：   <s:property value="%{#enterprise.qq}"/>
                  </span>                  
                </td>
                <td class="with-complement">
                  	倪姐，陈姐
                  <a href="#info-for-check2" data-toggle="modal" class="complement">增删负责人
                  </a>
                </td>
                <td><a href="#info-for-check1" data-toggle="modal">修改</a></td>
              </tr>
            </tbody>
            </s:iterator>
          </table>
		<!-- ================================================End According to  Enterprise=========================== -->
        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>
  <div id="info-for-check1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">企业信息</h3>
    </div>
     <!-- ==================================addEnterprise====================================== -->
    <div class="modal-body">
      <s:form action="addEnterprise" method="post">
        <div class="row-fluid"> 
          <div class="input-container">
            <label>简称</label>
            <s:textfield name="enterprise.rferred"/>
          </div>

          <div class="input-container">
            <label>全称</label>
             <s:textfield name="enterprise.fullName"/>
          </div>

          <div class="input-container">
            <label>法人代表</label>
            <s:textfield name="enterprise.legalRepresentative"/>
          </div>

          <div class="input-container">
            <label>开户行</label>
            <s:textfield name="enterprise.accountLine"/>
          </div>

          <div class="input-container">
            <label>企业银行账号</label>
           <s:textfield name="enterprise.enterpriseBankAccount"/>
          </div>

          <div class="input-container">
            <label>地址</label>
            <s:textfield name="enterprise.address"/>
          </div>

          <div class="input-container">
            <label>联系人</label>
            <s:textfield name="enterprise.contact"/>
          </div>

          <div class="input-container">
            <label>电话</label>
           <s:textfield name="enterprise.phone"/>
          </div>
          <div class="input-container">
            <label>QQ</label>
            <s:textfield name="enterprise.qq"/>
          </div>

          <div class="input-container">
            <label>传真</label>
           <s:textfield name="enterprise.fax"/>
          </div>

          <div class="input-container">
            <label>电子邮件</label>
            <s:textfield name="enterprise.email"/>
          </div>
          
          <div class="input-container">
            <label>状态?</label>
            <input type="radio" name="enterprise.status" value="1" checked="checked">合约中，
            <input type="radio" name="enterprise.status" value="0">暂停
          </div>

          <div class="input-container">
            <s:submit cssClass="btn btn-primary" value="提交"/>
          </div>
        </div>
      </s:form>
    </div>
   
   
   
   
   
    <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    </div>
  </div>

  <div id="info-for-check2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">增删负责人</h3>
    </div>
    <div class="modal-body">
      <div class="row-fluid">
        <form action="" method="post">
          <div class="input-container">
            <label>企业名称</label>
            福建电信
          </div>

          <div class="input-container">
            <label>当前负责人</label>
            <ul class="list-of-items-for-delete normal clearfix">
              <li>倪姐<a href="#">X</a></li>
              <li>陈姐<a href="#">X</a></li>
              <li>某某人<a href="#">X</a></li>
            </ul>
          </div>

          <div class="input-container">
            <label>搜索并添加负责人</label>
            <input type="text" name="">
          </div>
          
          <div class="input-container">
            <button type="button" class="btn btn-primary">添加</button>
          </div>
        </form>
      </div>
    </div>
    <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    </div>
  </div>
</body>

</html>
