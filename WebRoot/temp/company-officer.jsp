<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<title>富民人力银行派遣系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="../css/bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="../css/bootstrap-responsive.min.css"/>
  <link rel="stylesheet" type="text/css" href="../css/bootstrap-responsive.css"/>
  <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
  <link rel="stylesheet" type="text/css" href="../css/layout.css"/>

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
              <a href="company-list.html">所有企业</a>
            </li>         
          </ul>

          <ul class="normal action-container clearfix">
            <li><a href="#">添加新企业</a></li>
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th width="8%">序号</th>
                <th width="50%">企业</th>
                <th width="30%">负责人</th>
                <th width="12%">操作</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td class="with-complement">
                  福建电信
                  <span class="complement">林女士，电话：1391125864，QQ：96857563</span>                  
                </td>
                <td class="with-complement">
                  倪姐，陈姐
                  <a href="#info-for-check" class="complement" data-toggle="modal">增删负责人</a>
                </td>
                <td><a href="#info-for-check" data-toggle="modal">修改</a></td>
              </tr>
            </tbody>
          </table>

          <div class="pagination">
            <ul>
              <li><a href="#">&laquo;</a></li>
              <li><a href="#">1</a></li>
              <li><a href="#">2</a></li>
              <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
              <li><a href="#">6</a></li>
              <li><a href="#">7</a></li>
              <li><a href="#">8</a></li>
              <li><a href="#">&raquo;</a></li>
            </ul>
          </div>

        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>
  <div id="info-for-check" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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

  <script type="text/javascript" src="../js/jquery.js"></script>
	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
</body>

</html>
