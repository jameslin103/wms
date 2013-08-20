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
                  <li><a tabindex="-1" href="tax.html">计税规则</a></li>
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
              <a href="#info-for-check1" data-toggle="modal">权限分配</a>
            </li>         
          </ul>

          <ul class="normal action-container clearfix">
            <li><a href="#info-for-check1" data-toggle="modal">为员工分配权限</a></li>
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th>序</th>
                <th>姓名</th>
                <th>权限</th>
                <th>修改</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>倪姐</td>
                <td>
                  <ol>
                    <li>派单员</li>
                  </ol>
                </td>
                <td><a href="#info-for-check2" data-toggle="modal">修改</a></td>
              </tr>
              <tr>
                <td>2</td>
                <td>陈姐</td>
                <td>
                  <ol>
                    <li>五险一金办理专员</li>
                  </ol>
                </td>
                <td><a href="#info-for-check2" data-toggle="modal">修改</a></td>
              </tr>
            </tbody>
          </table>

        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>
  <div id="info-for-check1" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">权限分配</h3>
    </div>
    <div class="modal-body">
      <form action="" class="navbar-form pull-left" method="post">
        <div class="row-fluid"> 
          <div class="input-container">
            <input type="text">
            <button type="submit" class="btn">搜索</button>
          </div>

          <div class="input-container">
            <ul class="list-of-items-for-delete normal clearfix">
              <li>倪姐<a href="#">X</a></li>
              <li>陈姐<a href="#">X</a></li>
              <li>某某人<a href="#">X</a></li>
            </ul>
          </div>

          <hr>

          <div class="input-container">
            <input type="checkbox" name="">派单专员，
          </div>

          <div class="input-container">
            <input type="checkbox" name="">五险一金办理专员，
          </div>

          <div class="input-container">
            <input type="checkbox" name="">财务专员，
          </div>

          <div class="input-container">
            <input type="checkbox" name="">高级管理员
          </div>

          <div class="input-container">
            <button type="button" class="btn btn-primary">提交</button>
          </div>
        </div>
      </form>
    </div>
    <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    </div>
  </div>

  <div id="info-for-check2" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">权限分配</h3>
    </div>
    <div class="modal-body">
      <div class="row-fluid">
        <form action="" class="navbar-form pull-left" method="post">
          <div class="row-fluid"> 

            <div class="input-container">
              <p>倪姐</p>
            </div>

            <div class="input-container">
              <input type="checkbox" name="">派单专员，
            </div>

            <div class="input-container">
              <input type="checkbox" name="">五险一金办理专员，
            </div>

            <div class="input-container">
              <input type="checkbox" name="">财务专员，
            </div>

            <div class="input-container">
              <input type="checkbox" name="">高级管理员
            </div>

            <div class="input-container">
              <button type="button" class="btn btn-primary">提交</button>
            </div>
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
