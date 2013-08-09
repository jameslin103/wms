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

	<div id="main"> 
      <div class="row-fluid">

        <div id="center-pane">
          <ul class="nav nav-tabs">
            <li class="active">
              <a href="#">我负责的企业</a>
            </li>   
            <li>
              <a href="#">所有企业</a>
            </li>          
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th>序号</th>
                <th>企业</th>
                <th>性质</th>
                <th>员工总数（人）</th>
                <th>资金往来（元）</th>
                <th>工作提醒</th>
                <th>负责人</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td class="with-complement">
                  <a href="index.jsp">福建电信</a>
                  <span class="complement">林女士，电话：1391125864，QQ：96857563</span>                  
                </td>
                <td>市医保</td>
                <td><a href="company/employee-list.jsp">200</a></td>
                <td><a href="../task/salary-summary-detail.jsp">-320</a></td>
                <td>
                  <ol class="reminder">
                    <li><a href="../task/salary.jsp">某某工资预算表（待审核）</a></li>
                    <li><a href="../task/employee-list-with-insurance.jsp">2013年7月增减员与参保（已完成）</a></li>
                  </ol>
                </td>
                <td>倪姐</td>
                <td><a href="#info-for-check" data-toggle="modal">修改</a></td>
              </tr>
              <tr>
                <td>2</td>
                <td class="with-complement">
                  <a href="index.jsp">中国银行</a>
                  <span class="complement">林女士，电话：1391125864，QQ：96857563</span>                  
                </td>
                <td>市医保</td>
                <td><a href="employee-list.jsp">150</a></td>
                <td><a href="../task/salary-summary-detail.jsp">0</a></td>
                <td></td>
                <td>倪姐</td>
                <td><a href="#">修改</a></td>
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
      <h3 id="myModalLabel">企业信息</h3>
    </div>
    <div class="modal-body">
      <form action="" method="post">
        <div class="row-fluid"> 
          <p>公司名称：福建电信</p>
          <p>公司全称：福建电信有限公司</p>
          <p>公司地址：福建省鼓楼区某某路某某大楼230号</p>

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
