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
            <li  class="active"><a href="salary-summary.html">往来款明细</a></li>
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th rowspan="2">序</th>
                <th rowspan="2">月份</th>
                <th rowspan="2">期初余额（元）</th>
                <th colspan="4" class="center">应收款项（元）</th>
                <th rowspan="2">实收款项（元）</th>
                <th rowspan="2">期末余额（元）</th>
                <th colspan="3" class="center">余额分配（元）</th>
                <th rowspan="2">备注</th>
                <th rowspan="2">操作</th>
              </tr>
              <tr>
                <th>开票总额</th>
                <th>工资总额</th>
                <th>服务费总额</th>
                <th>五险一金</th>
                <th>工资</th>
                <th>服务费</th>
                <th>五险一金</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>2013年3月</td>
                <td>0</td>
                <td>21000</td>
                <td></td>
                <td></td>
                <td></td>
                <td>20000</td>
                <td>-1000</td>
                <td>-500</td>
                <td>-300</td>
                <td>-200</td>
                <td></td>
                <td><a href="#info-for-check" data-toggle="modal">填写</a></td>
              </tr>
              <tr>
                <td>2</td>
                <td>2013年4月</td>
                <td>-1000</td>
                <td>21000</td>
                <td></td>
                <td></td>
                <td></td>
                <td>22000</td>
                <td>0</td>
                <td>500</td>
                <td>300</td>
                <td>200</td>
                <td>-------------</td>
                <td><a href="#info-for-check" data-toggle="modal">填写</a></td>
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

  <div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">实收款项与余额分配</h3>
    </div>

    <div class="modal-body">
      <div class="row-fluid">
        <form action="" method="post">
            <div class="input-container">
              <label>实收款项</label>
              <input type="text" name="">
            </div> 
            <hr>
            <h3>余额分配</h3>
            <div class="input-container">
              <label>工资</label>
              <input type="text" name="">
            </div> 
            <div class="input-container">
              <label>服务费</label>
              <input type="text" name="">
            </div> 
            <div class="input-container">
              <label>五险一金</label>
              <input type="text" name="">
            </div> 
            <div class="input-container">
              <label>&nbsp;</label>
              <button type="button" class="btn btn-primary">提交</button>
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
