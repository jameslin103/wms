<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>员工列表</title>
<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#new").click(function(){
			location.href="toAddEmployee";
		});
		$("#first").click(function(){
			goPage(1);
		});
		$("#prev").click(function(){
			goPage("${pageBean.page-1}");
		});
		$("#next").click(function(){
			goPage("${pageBean.page+1}");
		});
		$("#last").click(function(){
			goPage("${pageBean.totalPage}");
		});
		$("#pagesel").val("${pageBean.page}");
		$("#pagesel").change(function(){
			goPage($(this).val());
		});
		if("${pageBean.page}" == "1"){
			$("#first").off("click");
			$("#prev").off("click");
		}
		if("${pageBean.page}" == "${pageBean.totalPage}"){
			$("#next").off("click");
			$("#last").off("click");
		}
	});
	
	function del(id){
		if(confirm("您确认删除吗?")){
			location.href="deleteEmployee?employee.id="+id;
		}
	}
	
	
	function bind(a,empId){
		$.dialog({
			id:'selUser',
			content:'url:toSearchUser',
			height:'400px',
			width:'300px',
			title:'选择用户',
			ok:function(){
				var selUser=$.dialog.list['selUser'].content.selUser;
				$.get("bind?employee.id="+empId+""+selUser[0],function(){
					$.dialog.tips("绑定用户成功！",1);
					$(a).parent().prev().html(selUser[1]);
					$(a).html("解除绑定");
					$(a).removeAttr("onclick");
					$(a).off("click");
					$(a).click(function(){
						unbind(a,empId);
					});
				});
			},
			cancel:true
		});
	}
	
	function unbind(a,empId){
		$.dialog.confirm("确认解除绑定吗？",function(){
			$.get("unbind??employee.id="+empId,function(){
				$.dialog.tips("解除绑定成功！",1);
				$(a).parent().prev().html("");
				$(a).html("绑定用户");
				$(a).removeAttr("onclick");
				$(a).off("click");
				$(a).click(function(){
					bind(a,empId);
				});
			});
		});
	}
	
	
	function goPage(p){
		$("#page").val(p);
		$("#employee").submit();
	}
</script>
</head>

<body>
	<div id="main">
		<div id="search">
			<fieldset>
				<legend>
					<img src="images/311.gif" />&nbsp;查询条件
				</legend>
				<s:form action="toEmployeeManage" method="post">
					<input type="hidden" name="page" id="page" value="1">
					员工姓名：<s:textfield name="employee.name" size="10" cssStyle="width:150px;"/>
					工号：<s:textfield name="employee.empNo" size="10" cssStyle="width:150px;"/>
					性别：
						<s:select  list="{'不限','男','女'}" theme="simple"  headerValue=""  
							name="employee.gender" cssStyle="width:80px;" />
							
					学历：<s:select  list="{'不限','本科','专科','研究生','高中','初中','其他'}" 
									theme="simple"  headerValue=""  name="employee.degree" cssStyle="width:80px;" />
							
				 	<input type="submit" value=" 查  询 " class="oprbtn" style="width:70px;">
				 </s:form>
			</fieldset>
		</div>
		<fieldset>
			<legend>
				<img src="images/311.gif" />&nbsp;员工列表
			</legend>
			<div id="datalist">
				<table border="1px;">
					<thead>
						<tr id="tableheader">
							<th width="60"><input type="checkbox" />选择</th>
							<th width="40">序号</th>
							<th width="70">员工姓名</th>
							<th width="40">性别</th>
							<th width="60">部门</th>
							<th width="40">工号</th>
							<th width="80">出生年月</th>
							<th width="60">学历</th>
							<th width="70">绑定用户</th>
							<th width="190">操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.pageBean.data" var="emp" status="s">
							<tr>
								<td class="center"><input name="ids" type="checkbox" value=${role.id} /></td>
								<td class="center">${s.count}</td>
								<td class="center">${emp.name}</td>
								<td class="center">${emp.gender}</td>
								<td class="center">${emp.department.name}</td>
								<td class="center">${emp.empNo}</td>
								<td class="center"><s:date name="%{#emp.birthDate}" format="yyyy-MM-dd"/></td>
								<td class="center">${emp.degree}</td>
								<td class="center">${emp.user.account}</td>
								<td class="center">
								
									<s:if test="#emp.user.account==null">
										<img src="images/001.gif" width="9" height="9" />
										[<a href="javascript:void(0)" onclick="bind(this,'${emp.id}')">绑定用户</a>]&nbsp;
									</s:if>
									<s:if test="#emp.user.account!=null">
										<img src="images/005.gif" width="9" height="9" />
										[<a href="javascript:void(0)" onclick="unbind(this,'${emp.id}')">解除绑定</a>]&nbsp;
									</s:if>
									
									<img src="images/037.gif" width="9" height="9" />[<a href="exportEmployees?employee.id=${emp.id}">导出</a>]&nbsp;
									<img src="images/037.gif" width="9" height="9" />[<a href="toUpdateEmployees?employee.id=${emp.id}">编辑</a>]&nbsp;
									<img src="images/010.gif" width="9" height="9" />[<a href="javascript:void(0)" onclick="del('${emp.id}')">删除</a>]
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			 <div id="nav" >
				<table width="80%" align="center">
					<tr>
						<td align="left" style="padding-left:263px;">
							本页${pageBean.actualPageSize}条纪录，共${pageBean.totalNums}条记录，
							当前第${pageBean.page}/${pageBean.totalPage}页
						</td>
						<td align="right">
							<img src="images/first.gif" id="first"/> 
							<img src="images/back.gif" id="prev"/> 
							<img src="images/next.gif" id="next"/> 
							<img src="images/last.gif" id="last"/>
						<select id="pagesel" style="width:50px;height:30px;">
							<s:iterator begin="1" end="#request.pageBean.totalPage" var="p">
								<option value="${p}">${p}</option>
							</s:iterator>
						</select>
						</td>
					</tr>
				</table>
			</div>
			<div id="opr">
				<input type="button" value=" 新  增 " class="oprbtn" id="new" style="width:70px;"/>
				<input type="button" value=" 删  除 " class="oprbtn" style="width:70px;"/>
			</div>
		</fieldset>
	</div>
</body>
</html>

