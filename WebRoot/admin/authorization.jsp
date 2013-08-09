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
    	<jsp:include page="../dashboard.jsp"></jsp:include>
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
