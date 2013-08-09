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
            <li>
              <a href="employee-list.html">员工</a>
            </li>
            <li class="active">
              <a href="salary-with-month.html">工资</a>
            </li>
          </ul>
 
          <ul class="normal action-container clearfix">
            <li class="right">
              <form action="" class="select-for-year" method="post">
                <select>
                  <option value="">2014年</option>
                  <option value="" selected>2013年</option>
                  <option value="">2012年</option>
                </select>
              </form>
            </li>
            <li><a href="salary-step1-of-create.html">新建工资预算表</a></li>
            <li>&nbsp;/&nbsp;</li>
            <li><a href="salary-template.html">工资模板</a></li>
            <li>&nbsp;/&nbsp;</li>
            <li><a href="salary-list-of-customized-items.html">定制奖金与各种补贴</a></li>
          </ul>

          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th rowspan="2">序</th>
                <th rowspan="2">企业</th>
                <th rowspan="2">工资预算表<br>名称</th>
                <th rowspan="2" style="text-align:center">工资所属月份</th>
                <th colspan="4" style="text-align:center">工资预算表汇总信息</th>
                <th colspan="4" style="text-align:center">发放明细</th>
                <th colspan="1" style="text-align:center">状态</th>
                <th rowspan="2">操作</th>
              </tr>
                <th>开票<br>总额（元）</th>
                <th>工资<br>总额（元）</th>
                <th>服务费<br>总额（元）</th>
                <th>五险一金<br>总额（元）</th>
                <th>发放<br>人数（人）</th>
                <th>民生<br>银行（人）</th>
                <th>他行<br>（人）</th>
                <th>现金（人）</th>
                <th>（制作、审核、实际发放）</th>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>福建电信</td>
                <td>
                  某某工程工资
                </td>
                <td>2013年1月</td>
                <td>10435.50</td>
                <td>9480.00</td>
                <td>60</td>
                <td>600</td>
                <td>15<br><a href="../task/employee-list-info1.html">查看</a></td>
                <td>8<br><span class="em">（已发放）</span><br>2013年7月15日9时</td>
                <td>2<br><span class="em">（已发放）</span><br>2013年7月15日9：30时</td>
                <td>5<br><span class="em">（已发放）</span><br>2013年7月16日11时</td>
                <td>
                  <ul>
                    <li>制作：倪姐，2013-07-18，9:00</li>
                    <li>审核：倪姐，2013-07-19，9:30</li>
                    <li>发放：小甘</li>
                  </ul>
                </td>
                <td>
                  <a href="salary-list.html">修改</a>
                  <a href="">删除</a><br>
                  <a href="salary-list.html">查看</a>
                  <a href="">下载</a>
                </td>
              </tr>
              <tr>
                <td>2</td>
                <td>福建电信</td>
                <td>
                  某某工程工资
                </td>
                <td>2013年1月</td>
                <td>10435.50</td>
                <td>9480.00</td>
                <td>60</td>
                <td>600</td>
                <td>15<br><a href="../task/employee-list-info1.html">查看</a></td>
                <td>8<br><span class="em">（已发放）</span><br>2013年7月15日9时</td>
                <td>2<br></td>
                <td>5</td>
                <td>
                  <ul>
                    <li>制作：倪姐，2013-07-18，9:00</li>
                    <li>审核：倪姐，2013-07-19，9:30</li>
                    <li>发放：小甘</li>
                  </ul>
                </td>
                <td>
                  <a href="salary-list.html">修改</a>
                  <a href="">删除</a><br>
                  <a href="salary-list.html">查看</a>
                  <a href="">下载</a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
  
      </div>
    </div>

    <div id="footer"></div>
  </div>
</body>

</html>