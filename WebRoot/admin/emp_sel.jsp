<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>选择员工</title>
 	<base href="<%=basePath%>" />
<%@ include file="/help/public_css_js.jsp"%>
	<link rel="stylesheet" href="<%=basePath%>styles/zTreeStyle.css" type="text/css"/>
  	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/wms.css"/>
	<script type="text/javascript" src="<%=basePath%>js/jquery.ztree.all-3.4.js"></script>
    
    <style type="text/css">
    	#department{
    		border:1px green dashed;
    		width:180px;
    		float:left
    	}
    	#employees{
    		border:1px green dashed;
    		width:370px;
    		height:400px;
    		left:200px;
    		top:0px;
    		position: fixed;
    	}
    	#emplist{
    		text-align: center;
    	}
    	#nav{
    		text-align: center;
    		margin-top: 10px;
    	}
    </style>
	
    <script type="text/javascript">
    var selEmp=[];
    var setting = {
    		data : {
    			key: {
    				children: "children",
    				name: "name",
    				title: "",
    				url: "link"
    			},
    			simpleData: {
    				enable: true,
    				idKey: "id",
    				pIdKey: "parentId",
    				rootPId: null
    			},
    			keep: {
    				parent: false,
    				leaf: false
    			}
    		},
    		callback : {
    			onClick : function(event, treeId, treeNode) {
    				$("#emplist").empty();
    				 	$(function(){
				       		$.ajax({
								  type:"get",
								  url:"getEmpByDeptId?department.id="+treeNode.id,
								  async:false,
								  dataType:"json",
								  success:function(emps){
									 var empContent="";
				    				 $.each(emps.employees.data,function(i,value){ 
							            var emp=value;
										var account=emp.usern==null?"":emp.usern.account;
										empContent+="<tr><td><input type='radio' value='"+emp.id+"' name='empId'></td>";
										empContent+="<td>"+emp.name+"</td><td>"+emp.empNo+"</td><td>"+account+"</td></tr>";
										$("#emplist").append(empContent);
					    			 });
									  $(":radio").click(function(){
											selEmp[0]=$(this).val();
											selEmp[1]=$(this).parent().next().html();
									  });
							    					
								}
							});
					     });

    				/**$.get("getEmpByDeptId?department.id="+treeNode.id,function(emps){
    					var empContent="";
    					for(var i=0;i<emps.employees.length;i++){
    						var emp=employees[i];
    						alert(employees[i]);
    						var account=emp.user==null?"":emp.user.account;
    						empContent+="<tr><td><input type='radio' value='"+emp.id+"' name='empId'></td>";
    						empContent+="<td>"+emp.name+"</td><td>"+emp.empNo+"</td><td>"+account+"</td></tr>";
    						//alert(emp.user);
    						//alert(emp.id+","+emp.empNo+".");
    					}
    					$("#emplist").append(empContent);
    					$(":radio").click(function(){
        					selEmp[0]=$(this).val();
        					selEmp[1]=$(this).parent().next().html();
        				});
    				});**/
    				
    				
    			}
    		}
    	};
       	$(function(){
       		$.ajax({
				  type:"get",
				  url:"jsonDepartments",
				  async:false,
				  dataType:"json",
				  success:function(depts){
					$.fn.zTree.init($("#dept"),setting,JSON.parse(depts)).expandAll(true);
				}
		   });
       	
       		/**$.get("jsonDepartments",function(depts){
       			alert(depts);
       			$.fn.zTree.init($("#dept"),setting,JSON.parse(depts)).expandAll(true);
       		});**/
       		
       	});
     </script>
  </head>
  <body>
  
  <div id="department">
  	<ul id="dept" class="ztree"></ul>
  </div>
    
    <div id="employees">
     <div id="datalist">
		<table width="95%" border="0" align="center">
			<thead>
				<tr id="tableheader">
					<th width="15%">选择</th>
					<th>员工</th>
					<th>工号</th>
					<th>绑定帐号</th>
				</tr>
			</thead>
			<tbody id="emplist">
			</tbody>
		</table>
		<div id="nav">
			<img src="images/first.gif" id="first"/>
			<img src="images/back.gif"  id="back">
			<img src="images/next.gif"  id="next" /> 
			<img src="images/last.gif"  id="last">
		</div>
		</div>
	</div>
		
  </body>
</html>
