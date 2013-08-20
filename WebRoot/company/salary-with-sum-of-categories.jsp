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
      <ul class="user normal clearfix">
        <li><a href="account/password.jsp">某某员工</a></li>
        <li>
          <a href="#">退出</a>
        </li>
      </ul>
      <div class="navbar">
        <div class="navbar-inner">
          <a class="brand" href="#">富民</a>
          <ul class="nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                企业
                <b class="caret"></b>
              </a>
              <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                <li><a tabindex="-1" href="list.jsp">我的企业</a></li>
                <li><a tabindex="-1" href="#">所有企业</a></li>
              </ul>
            </li>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                管理
                <b class="caret"></b>
              </a>
              <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
                <li><a tabindex="-1" href="admin/company-list.jsp">企业相关</a></li>
                <li><a tabindex="-1" href="admin/tax.jsp">计税规则</a></li>
                <li><a tabindex="-1" href="admin/authorization.jsp">权限分配</a></li>                
              </ul>
            </li>

            <li><a href="all/company-list-with-salary.jsp">汇总</a></li>
            <li><a href="help/index.jsp">帮助</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div id="sub-header" class="clearfix">
      <h2>福建电信</h2>
      <div class="date">2013年7月23日</div>
    </div>

    <div id="main">
      <div class="row-fluid">

        <div id="center-pane">
          <ul class="nav nav-tabs">
            <li><a href="company/index.jsp">综合</a></li>
            <li><a href="company/employee-list.jsp">员工档案</a></li>
            <li class="active"><a href="company/salary-with-month.jsp">工资预算表</a></li>
            <li><a href="insurance-with-month.jsp">增减员与参保明细</a></li>
          </ul>
 
          <table class="table table-striped table-bordered">
            <thead>
              <tr>
                <th rowspan="2">工资预算表<br>名称</th>
                <th rowspan="2" style="text-align:center">工资所属月份</th>
                <th rowspan="2" style="text-align:center">合并计税工资表</th>
                <th rowspan="2" style="text-align:center">性质</th>
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
                <th>（制作、实际发放）</th>
            </thead>
            <tbody>
              <tr>
                <td>
                  某某工程工资
                </td>
                <td>2013年1月</td>
                <td>某某工资预算表</td>
                <td>市医保</td>
                <td>10435.50</td>
                <td>9480.00</td>
                <td>60</td>
                <td>600</td>
                <td>15<br><a href="company/salary-with-bank-detail.jsp">查看</a></td>
                <td>8<br><span class="em">（已发放）</span><br>2013年7月15日9时</td>
                <td>2<br><span class="em">（已发放）</span><br>2013年7月15日9：30时</td>
                <td>5<br><span class="em">（已发放）</span><br>2013年7月16日11时</td>
                <td>
                  <ul>
                    <li>制作：倪姐，2013-07-18，9:00</li>
                    <li>发放：小柴</li>
                  </ul>
                </td>
                <td>
                  <a href="#info-for-check" data-toggle="modal">修改</a>
                  <a href="">删除</a><br>
                  <a href="salary-list.jsp">查看</a>
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
  
  <div id="info-for-check" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
      <h3 id="myModalLabel">工资预算表修改</h3>
    </div>

    <div class="modal-body">
      <div class="row-fluid">
        <form action="" method="post">
          <div class="row-fluid">
            <div class="input-container">
              <label>名称</label>
              <input type="text" name="">
            </div>

            <div class="input-container">
              <label>模板</label>
              <p>某某模板名称</p>
            </div>

            <div class="input-container">
              <label>生成哪月工资？</label>
              <select class="span3">
                <option value="">2014年</option>
                <option value="" selected>2013年</option>
                <option value="">2012年</option>
              </select>
              <select class="span3">
                <option value="">7月</option>
                <option value="" selected>8月</option>
                <option value="">9月</option>
              </select> 
            </div>


            <div class="input-container">
              <label>选择与其他工资表合并计税</label>
              <select>
                <option value="">无</option>
                <option value="">某某工资表1</option>
                <option value="">某某工资表2</option>
                <option value="">某某工资表3</option>
                <option value="">某某工资表4</option>
              </select>
            </div>

            <div class="input-container">
              <label>补充说明</label>
              <textarea rows="3"></textarea>
            </div>

            <div class="input-container">
              <button type="submit" class="btn btn-primary">提交</button>
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