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
              <a href="tax.jsp">五险一金（税率）</a>
            </li> 
            <li><a href="admin/tax-base.jsp">五险一金（基数）</a></li>        
            <li><a href="tax-of-person.jsp">个税</a></li>        
          </ul>

          <ul class="normal action-container clearfix">
            <li><a href="#info-for-check" data-toggle="modal">新建规则</a></li>
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th rowspan="2">序号</th>
                <th colspan="2">养老保险</th>
                <th colspan="2">失业保险</th>
                <th rowspan="2">生育（企业）</th>
                <th rowspan="2">工伤（企业）</th>
                <th colspan="2">基本医疗保险</th>
                <th colspan="2">住房公积金</th>
                <th rowspan="2">开始执行月份</th>
                <th rowspan="2">类型</th>
                <th rowspan="2">操作</th>
              </tr>
              <tr>
                <th>企业</th>
                <th>个人</th>
                <th>企业</th>
                <th>个人</th>
                <th>企业</th>
                <th>个人</th>
                <th>个人</th>
                <th>个人</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>8%</td>
                <td>8%</td>
                <td>1%</td>
                <td>1%</td>
                <td>0.7%</td>
                <td>0.50%</td>
                <td>8%</td>
                <td>2%</td>
                <td>12%</td>
                <td>12%</td>
                <td>2013年3月</td>
                <td>市医保</td>
                <td><a href="#info-for-check" data-toggle="modal">修改</a></td>
              </tr>
            </tbody>
          </table>

        </div>
      </div>
    </div>

		<div id="footer"></div>

</div>
<!-- ====================================================insurancesTax============================================================= -->
  <div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">五险一金（税率）</h3>
    </div>
    <div class="modal-body">
      <div class="row-fluid">
        <s:form action="addInsurancesTax" method="post">
          <div class="row-fluid">         

            <div class="input-container">
              <label>类型</label>
              <input type="radio" name="insurancesTax.insurancesType" value="1" checked="checked">市医保，
              <input type="radio" name="insurancesTax.insurancesType" value="0">省医保
            </div>

            <div class="input-container">
              <label>养老保险（公司）</label>
              <s:textfield name="insurancesTax.endowmentInsurance"/>%
            </div>

            <div class="input-container">
              <label>养老保险（个人）</label>
              <s:textfield name="insurancesTax.personalEndowmentInsurance"/>%
            </div>

            <div class="input-container">
              <label>失业保险（公司）</label>
             <s:textfield name="insurancesTax.unemploymentInsurance"/>%
            </div>

            <div class="input-container">
              <label>失业保险（个人）</label>
              <s:textfield name="insurancesTax.personalUnemploymentInsurance"/>%
            </div>

            <div class="input-container">
              <label>----------</label>
              <input type="text" name="">%
            </div>

            <div class="input-container">
              <label>开始执行年月份</label>
                <s:textfield id="d11" type="text" name="startDate"/>
				<img onclick="WdatePicker({el:'d11'})" src="images/datePicker.gif" width="16" height="22" />
            </div>

            <div class="input-container">
              <s:submit type="button" cssClass="btn btn-primary" value="提交"/>
            </div>
          </div>
        </s:form>
      </div>
    </div>
    <div class="modal-footer">
      <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    </div>
  </div>
</body>

</html>
