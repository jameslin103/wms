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
              <a href="#">所有企业</a>
            </li>         
          </ul>

          <ul class="normal action-container clearfix">
            <li><a href="#info-for-check1" data-toggle="modal">添加新企业</a></li>
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
                  <a href="#info-for-check2" data-toggle="modal" class="complement">增删负责人
                  </a>
                </td>
                <td><a href="#info-for-check1" data-toggle="modal">修改</a></td>
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
      <h3 id="myModalLabel">企业信息</h3>
    </div>
    <div class="modal-body">
  
      <form action="" method="post">
        <div class="row-fluid"> 
          <div class="input-container">
            <label>简称</label>
            <input type="text" name="short-name">
          </div>

          <div class="input-container">
            <label>全称</label>
            <input type="text" name="name">
          </div>

          <div class="input-container">
            <label>法人代表</label>
            <input type="text" name="person-in-charge">
          </div>

          <div class="input-container">
            <label>开户行</label>
            <input type="text" name="">
          </div>

          <div class="input-container">
            <label>企业银行账号</label>
            <input type="text" name="">
          </div>

          <div class="input-container">
            <label>地址</label>
            <input type="text" name="adress">
          </div>

          <div class="input-container">
            <label>联系人</label>
            <input type="text" name="contact">
          </div>

          <div class="input-container">
            <label>电话</label>
            <input type="text" name="tel">
          </div>
          <div class="input-container">
            <label>QQ</label>
            <input type="text" name="qq">
          </div>

          <div class="input-container">
            <label>传真</label>
            <input type="text" name="fax">
          </div>

          <div class="input-container">
            <label>电子邮件</label>
            <input type="text" name="email">
          </div>

          <div class="input-container">
            <label>类型</label>
            <input type="radio" name="type-of-insurance" value="1" checked="checked">市医保，
            <input type="radio" name="type-of-insurance" value="0">省医保
          </div>
          
          <div class="input-container">
            <label>状态?</label>
            <input type="radio" name="status" value="1" checked="checked">合约中，
            <input type="radio" name="status" value="0">暂停
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
