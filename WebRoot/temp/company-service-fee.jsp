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
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.min.css"/>
  <link rel="stylesheet" type="text/css" href="css/bootstrap-responsive.css"/>
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
  <link rel="stylesheet" type="text/css" href="css/layout.css"/>

</head>
<body>

	<div id="container">
    	<jsp:include page="../dashboard.jsp"></jsp:include>
    </div>

		<div id="main"> 
      <div class="row-fluid">

        <div id="side-nav" class="span2">
          <div class="inner well">
            <ul class="nav nav-list">
              <li class="nav-header">业务</li>
              <li><a href="../company/list.html">企业</a></li>
              <li class="nav-header">管理</li>
              <li><a href="../admin/company-list.html">企业</a></li>
            </ul>            
          </div>
        </div>

        <div id="center-pane" class="span7">
          <ul class="nav nav-tabs">
            <li>
              <a href="company-list.html">所有企业</a>
            </li>         
            <li class="active">
              <a href="company-service-fee.html">企业与服务费</a>
            </li>            
          </ul>
          
          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th width="10%">序</th>
                <th width="40%">企业名称</th>
                <th width="15%">服务费</th>
                <th width="20%">开始执行月份</th>
                <th width="15">操作</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td class="with-complement">
                  福建电信
                  <span class="complement">中国福建省电信总公司</span>                  
                </td>
                <td>30元</td>
                <td>2013年7月</td>
                <td><a href="#">修改</a></td>
              </tr>
              <tr>
                <td>2</td>
                <td>--</td>
                <td>--</td>
                <td>--</td>
                <td>--</td>
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
          
        <div id="right-pane" class="span3">
         <div class="inner well">
            <h3>企业服务费</h3>
            <form action="" method="post">
              <div class="row-fluid">
                <div class="input-container">
                  <label>企业名称</label>
                  福建电信
                </div>

                <div class="input-container">
                  <label>服务费</label>
                  <input type="text" name="service-fee">
                </div>

                <div class="input-container">
                  <label>开始执行年月份</label>
                  <select class="span5">
                    <option value="">2014年</option>
                    <option value="" selected>2013年</option>
                    <option value="">2012年</option>
                  </select>
                  <select class="span5">
                    <option value="">7月</option>
                    <option value="" selected>8月</option>
                    <option value="">9月</option>
                  </select>  
                </div>              

                <div class="input-container">
                  <button type="button" class="btn btn-primary">提交</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>
	<!-- Modal -->
  <div id="info-for-check" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">Modal header</h3>
    </div>
    <div class="modal-body">
      <p>One fine body…</p>
    </div>
    <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    </div>
  </div>

  <script type="text/javascript" src="../js/jquery.js"></script>
  <script type="text/javascript" src="../js/bootstrap.min.js"></script>
</body>

</html>
