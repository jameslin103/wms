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
		   		<jsp:include page="../dashboard.jsp"></jsp:include>
	    </div>
	
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
                <th rowspan="2">序号</th>
                <th rowspan="2">企业</th>
                <th colspan="2">资金往来（元）</th>
                <th rowspan="2">工资发放</th>
                <th rowspan="2">本月增减员</th>
                <th rowspan="2">负责人</th>
              </tr>
              <tr>
                <th>企业</th>
                <th>员工</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td class="with-complement">
                  <a href="company/index.jsp">福建电信</a>
                  <span class="complement">林女士，电话：1391125864，QQ：96857563</span>
                  <a href="#info-of-company" data-toggle="modal">详细信息</a>，
                  <a href="#info-for-check" data-toggle="modal">修改联系人</a>
                </td>
                <td><a href="company/balance-detail.jsp">-200</a></td>
                <td><a href="company/employee-list.jsp">100</a></td>
                <td>
                  <ol>
                    <li><a href="company/salary-with-sum-of-categories.jsp">工资预算表1</a>（待发放）</li>
                    <li><a href="company/salary-with-sum-of-categories.jsp">工资预算表1</a>（已发放）</li>
                    <li><a href="company/salary-with-sum-of-categories.jsp">工资预算表1</a>（待发放）</li>
                  </ol>
                </td>
                <td><a href="company/insurance-with-employee-list.jsp">增员1人，减员2人，参保3人</a></td>
                <td>倪姐</td>
              </tr>
              <tr>
                <td>2</td>
                <td class="with-complement">
                  <a href="index.html">中国银行</a>
                  <span class="complement">林女士，电话：1391125864，QQ：96857563</span>
                  <a href="#info-of-company" data-toggle="modal">详细信息</a>，
                  <a href="#info-for-check" data-toggle="modal">修改联系人</a>
                </td>
                <td>-200</td>
                <td>100</td>
                <td>
                  <ol>
                    <li><a href="company/salary-with-sum-of-categories.jsp">工资预算表1</a>（待发放）</li>
                    <li><a href="company/salary-with-sum-of-categories.jsp">工资预算表1</a>（已发放）</li>
                    <li><a href="company/salary-with-sum-of-categories.jsp">工资预算表1</a>（待发放）</li>
                  </ol>
                </td>
                <td><a href="company/insurance-with-employee-list.jsp">增员1人，减员2人，参保3人</a></td>
                <td>晓彬</td>
              </tr>
            </tbody>
          </table>
          
        </div>         


      </div>
    </div>

		<div id="footer"></div>

</div>

  <div id="info-of-company" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">企业信息</h3>
    </div>
    <div class="modal-body">
      <div class="row-fluid"> 
        <p>公司名称：福建电信</p>
        <p>公司全称：福建电信有限公司</p>
        <p>员工人数：200人</p>
        <p>公司地址：福建省鼓楼区某某路某某大楼230号</p>
        <p>法人代表：某某人</p>
        <p>开户银行：某某银行</p>
        <p>开户账号：xxxx-xxxx-xxxx-xxxx</p>
      </div>
    </div>
    <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    </div>
  </div>
  
  <div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">企业联系人信息</h3>
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
	