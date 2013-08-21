<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">>
  <title>富民人力银行派遣系统</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <%@ include file="/help/public_css_js.jsp" %>
</head>
<body>

  <div id="container">
  	 <div id="header">
   		<jsp:include page="../layout/header.jsp"/>
    </div>

    <div id="main">
      <div class="row-fluid">

        <div id="center-pane">
          <ul class="nav nav-tabs">
            <li>
              <a href="employee-list.html">员工</a>
            </li>
            <li class="active">
              <a href="salary-with-month.html">工资</a>
            </li>
          </ul>
 
          <ul class="normal action-container clearfix">
            <li><a href="#info-for-check" data-toggle="modal"> 新建工资模板</a></li>
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th>序</th>
                <th>名称</th>
                <th>包含奖金与补贴项</th>
                <th>包含五险一金</th>
                <th>包含个税</th>
                <th>状态</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>某某模板</td>
                <td>
                  <ol>
                    <li>满勤奖</li>
                    <li>高温补贴</li>
                    <li>伙食补贴</li>
                  </ol>
                </td>
                <td></td>
                <td></td>
                <td>停用</td>
                <td><a href="#info-for-check" data-toggle="modal">修改</a></td>
              </tr>
              <tr>
                <td>2</td>
                <td>某某模板</td>
                <td>
                  <ol>
                    <li>季度奖</li>
                    <li>话费补贴</li>
                    <li>伙食补贴</li>
                  </ol>
                </td>
                <td></td>
                <td></td>
                <td>停用</td>
                <td><a href="#info-for-check" data-toggle="modal">修改</a></td>
              </tr>
              <tr>
                <td>3</td>
                <td>某某模板</td>
                <td>
                  <ol>
                    <li>伙食补贴</li>
                  </ol>
                </td>
                <td></td>
                <td></td>
                <td>停用</td>
                <td><a href="#info-for-check" data-toggle="modal">修改</a></td>
              </tr>                            
            </tbody>
          </table>
        </div>
  
      </div>
    </div>

    <div id="footer"></div>

 </div>

    <div id="info-for-check" class="modal hide fade modal-of-info-for-check" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">新建/修改工资模板</h3>
      </div>
      <div class="modal-body">
        <form action="" method="post">
          <div class="row-fluid">
            <div class="input-container">
              <label>名称</label>
              <input type="text" name="">
            </div>


            <div class="input-container">
              <label class="checkbox">&nbsp;</label> 
              <input type="checkbox" value="remember-me">满勤奖
            </div>
            <div class="input-container">
              <label class="checkbox">&nbsp;</label> 
              <input type="checkbox" value="remember-me">季度奖
            </div>
            <div class="input-container">
              <label class="checkbox">&nbsp;</label> 
              <input type="checkbox" value="remember-me">伙食补贴
            </div>                    
            <div class="input-container">
              <label class="checkbox">&nbsp;</label> 
              <input type="checkbox" value="remember-me">高温补贴
            </div>  
            <div class="input-container">
              <label class="checkbox">&nbsp;</label> 
              <input type="checkbox" value="remember-me">住宿补贴
            </div>                  

            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="insurance" value="1" checked="checked">包含五险一金，
              <input type="radio" name="insurance" value="0">不包含
            </div>
            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="personl" value="1" checked="checked">包含个税，
              <input type="radio" name="personl" value="0">不包含
            </div>
            <div class="input-container">
              <label>&nbsp;</label>
              <input type="radio" name="start" value="1" checked="checked">启用，
              <input type="radio" name="start" value="0">停用
            </div>                    

            <div class="input-container">
              <label>&nbsp;</label>
              <button type="button" class="btn btn-primary">提交</button>
            </div>
          </div>
        </form>              
      </div>
      <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
      </div>
    </div> 
</body>

</html>