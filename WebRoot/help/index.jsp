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
<%@ include file="/help/public_css_js.jsp"%>
</head>
<body>

		
	<div id="container">
		<div id="header">
    		<jsp:include page="../layout/header.jsp" />
    	</div>
	<div id="main"> 
      <div class="row-fluid">
        <div id="center-pane">

          <div class="tabbable">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#tab1" data-toggle="tab">同事</a></li>
              <li><a href="#tab2" data-toggle="tab">开发</a></li>
            </ul>
            <div class="tab-content">
              <!-- 同事 -->
              <div class="tab-pane active" id="tab1">
                <h3>权限分配</h3>
                <table class="normal">
                  <thead>
                    <tr>
                      <th width="20%">角色</th>
                      <th>权限</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>派单专员</td>
                      <td>
                        <ol>
                          <li>补充企业基本信息（具体联系人等）</li>
                          <li>员工档案</li>
                          <li>工资预算表制作</li>
                          <li>工资预算表审核</li>
                        </ol>
                      </td>
                    </tr>
                    <tr>
                      <td>五险一金办理专员</td>
                      <td>
                        <ol>
                          <li>增员减员五险一金办理</li>
                        </ol>
                      </td>
                    </tr>
                    <tr>
                      <td>财务专员</td>
                      <td>
                        <ol>
                          <li>工资发放</li>
                        </ol>
                      </td>
                    </tr>
                    <tr>
                      <td>高级管理员</td>
                      <td>
                        <ol>
                          <li>具有以上所有角色功能</li>
                          <li>添加企业基本信息</li>
                          <li>添加各种规则（个税、五险一金基数调整等）</li>
                          <li>权限分配</li>
                        </ol>
                      </td>
                    </tr>
                  </tbody>
                </table>

                <hr>

                <h3>任务流程</h3>

                <table class="normal">
                  <thead>
                    <tr>
                      <th width="10%">序</th>
                      <th>说明</th>
                      <th width="20%">角色</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>1</td>
                      <td>企业信息建立</td>
                      <td>高级管理员</td>
                    </tr>
                    <tr>
                      <td>2</td>
                      <td>企业员工信息批量导入</td>
                      <td>派单专员</td>
                    </tr>
                    <tr>
                      <td>3</td>
                      <td>企业新工资预算表制作</td>
                      <td>派单专员</td>
                    </tr>
                    <tr>
                      <td>4</td>
                      <td>企业新工资预算表审核</td>
                      <td>派单专员</td>
                    </tr>
                    <tr>
                      <td>5</td>
                      <td>企业员工工资发放</td>
                      <td>财务专员</td>
                    </tr>
                    <tr>
                      <td>6</td>
                      <td>增员减员五险一金办理</td>
                      <td>五险一金办理专员</td>
                    </tr>
                  </tbody>
                </table>

              </div>


              <!-- 开发 -->
              <div class="tab-pane" id="tab2">
                <ol>
                  <li>富民人力银行企业基本信息建立；</li>
                  <li>富民人力银行企业员工信息导入；</li>
                  <li>后台指定一位高级管理员；</li>
                  <li>高级管理员，为富民员工分配权限；</li>
                  <li>富民员工使用手机号登陆，开始使用派遣系统；</li>
                  <li>根据反馈，迭代开发，持续推进！</li>
                </ol>
              </div>
            </div>
          </div>



        </div>
      </div>
    </div>
		<div id="footer"></div>
</div>
</body>

</html>
