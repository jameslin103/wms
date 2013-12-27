<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>增加新员工</title>
<base href="<%=basePath%>" />

<%@ include file="/help/public_css_js.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/wms.css"/>


<script type="text/javascript">
var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true,
				pIdKey: "parentId",
			}
		},
		callback: {
			onClick: onClick
		}
	};
	
function onClick(e, treeId, treeNode) {
	$("#deptname").attr("value", treeNode.name);
	$("#deptid").attr("value", treeNode.id);
	hideMenu();
}

function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}

function showMenu() {
	var cityObj = $("#deptname");
	var cityOffset = $("#deptname").offset();
	$("#menuContent").css({left:cityOffset.left +"px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}

$(function(){
	$("#menuBtn").click(function(){
		showMenu(); 
		return false;
	});
		$.ajax({
			  type:"get",
			  url:"jsonDepartments",
			  async:false,
			  dataType:"json",
			  success:function(depts){
			 	 	$.fn.zTree.init($("#treeDemo"),setting,JSON.parse(depts)).expandAll(true);
				}
		   });
       		
		/**$.get("jsonDepartments",function(depms){ alert(depms);
	     	$.fn.zTree.init($("#treeDemo"), setting, JSON.parse(depms)).expandAll(true);
		});**/
		
	$("#cancel").click(function(){
		history.go(-1);
	});
	
	jQuery.validator.addMethod("isTelPhone",function(value,element){
		//var length=value.length;
		var telphone=/^1[3|4|5|8]\d{9}$/;
		return this.optional(element) || telphone.test(value);
	},"输入的手机号不合法");
	
	$("#employee").validate({
		rules:{
			name:{
				required:true,
				minlength:2
			},
			empNo:{
				remote:"emp/empnoexist"
			},
			email:{
				email:true
			},
			telPhone:{
				isTelPhone:true
			}
		},
		messages:{
			name:{
				required:"员工姓名不能为空",
				min:$.format("员工姓名长度至少{0}位")
			},
			empNo:{
				remote:"员工工号已经存在"
			},
			email:{
				email:"邮箱格式不合法"
			}
		}
	});
});
</script>
<style type="text/css">
#menuContent{
	background-color:white;
	border: 1px green dashed;
	z-index: 1000
}
</style>
</head>

<body>
 <div id="main">
  	<h1>增加新员工</h1>
	<form method="post" action="saveEmployee" enctype="multipart/form-data" >
		<div id="newdata">
			<table width="500" border="1">
				<tr>
					<td width="60">姓&nbsp;&nbsp;&nbsp;&nbsp;名</td>
					<td><input name="name" size="30"/><s:fielderror name="name"/></td>
				</tr>
				<tr>
					<td>性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
					<td><input type="radio" name="gender" value="男" checked="checked">男&nbsp;&nbsp;
					<input type="radio" name="gender" value="女" seelcted>女</td>
				</tr>
				<tr>
					<td>工&nbsp;&nbsp;&nbsp;&nbsp;号</td>
					<td><input type="text" name="empNo"  size="30"/></td>
				</tr>
				<tr>
					<td>部&nbsp;&nbsp;&nbsp;&nbsp;门</td>
					<td>
						<input id="deptname" type="text" readonly value="" style="width:120px;"/>
							&nbsp;<a id="menuBtn" href="#">选择</a>
							<input type="hidden" name="department.id" id="deptid">
					</td>
				</tr>
				<tr>
					<td>婚姻状况</td>
					<td>
					<input type="radio" name="marryState" value="已婚" />已婚&nbsp;&nbsp;
					<input type="radio" name="marryState" value="未婚"/>未婚
				</td>
				</tr>
				<tr>
					<td>出生年月</td>
					<td><input type="date" name="birthDate" size="30" /></td>
				</tr>
				<tr>
					<td>学&nbsp;&nbsp;&nbsp;&nbsp;历</td>
					<td>
					<select name="degree">
						<option value="本科"></option>
						<option value="专科"></option>
						<option value="研究生"></option>
						<option value="高中"></option>
						<option value="初中"></option>
						<option value="其他"></option>
					</select>
					</td>
				</tr>
				<tr>
					<td>身&nbsp;份&nbsp;证</td>
					<td><input type="text" name="idCard"  size="30"/></td>
				</tr>
				<tr>
					<td>电子邮箱</td>
					<td><input type="text" name="email"  size="30"/></td>
				</tr>
				<tr>
					<td>手机号码</td>
					<td><input type="text" name="telPhone"  size="30"/></td>
				</tr>
				<tr>
					<td>办公电话</td>
					<td><input type="text" name="phone"  size="30"/></td>
				</tr>
				<tr>
					<td>头&nbsp;&nbsp;&nbsp;&nbsp;像</td>
					<td><input type="file" name="file"  size="30"/></td>
				</tr>
			</table>
		</div>
		<div id="opr">
			<input type="submit" value=" 新  增 " class="oprbtn" id="new" /> <input
				type="button" value=" 取  消 " class="oprbtn" id="cancel" onclick="test()"/>
		</div>
		</form>
	</div>	
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
	</div>
</body>
</html>
