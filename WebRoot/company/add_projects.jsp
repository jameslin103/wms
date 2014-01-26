<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加企业项目</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/help/public_css_js.jsp"%>
  </head>
  <script>
  	$(function(){
	  		$("#selType").change(function()
	  		{
	  			if($(this).val()==3){
	  				if($("#type").is(":hidden")){
						$("#type").show();
					}else{
						$("#type").hide();
					}
	  			}
	  			if($(this).val()!=3){
	  				if($("#type").is(":visible")){
						$("#type").hide();
					}
	  			
	  			}
	  		
	  		});
	  	function enterpriseProjects(projects,serviceType,customType,fee,serviceHead,proportion,note)
	  	{
	  		this.projects=projects;
	  		this.serviceType=serviceType;
	  		this.customType=customType;
	  		this.fee=fee;
	  		this.serviceHead=serviceHead;
	  		this.proportion=proportion;
	  		this.note=note;
	  	
	  	}
	  	var project={}
	  	$(".btn-primary").click(function(){
	  		
	  				  project={
							 projects:$("input[name='enterpriseProjects.projects']").val(),
							 serviceType:$("input[name='enterpriseProjects.serviceType']").val(),
							 customType:$("input[name='enterpriseProjects.customType']").val(),
							 fee:$("input[name='enterpriseProjects.fee']").val(),
							 serviceHead:$("input[name='enterpriseProjects.serviceHead']").val(),
							 proportion:$("input[name='enterpriseProjects.proportion']").val(),
							 note:$("input[name='enterpriseProjects.note']").val()
						 };
				alert(project.note);
	  		});
	  	
	  	
	  	
	  
  	});
  	
  
  </script>
  
  <body>
  		<div style="padding-top:30px;">
           <table class="table table-bordered" >
              <tbody>
                <tr>
                  <td class="bgc">合作项目:</td>
                  <td colspan="3">
                  	<input type="text" name="enterpriseProjects.projects" style="height:30px;width:200px;"/>
                  </td>
                </tr>
                <tr>
                  <td class="bgc">服务性质:</td>
                  <td>
                    <select name="enterpriseProjects.serviceType" id="selType" style="height:30px;width:200px;">
                      <option value="0">-请选择-</option>
                      <option value="1">完全派遣</option>
                      <option value="2">转移派遣</option>
                      <option value="3">其它</option>
                    </select>
                  </td>
                  <td>服务备注:</td>
                  <td >
                  <input type="text" name="enterpriseProjects.customType" style="height:30px;width:200px;display:none;" id="type"/>
                  </td>
                </tr>
              </tbody>
              <tbody>
                <tr>
                  <td>服务费:</td>
                  <td colspan="3">
                    <input type="radio" name="enterpriseProjects.fee" value="0" checked="checked"/>按人头，
                    <input class="span1" type="text" name="enterpriseProjects.serviceHead" maxlength="10"/>元/人
                    <input type="radio" name="enterpriseProjects.fee" value="1"/>按比例，
                    <input class="span1" type="text" name="enterpriseProjects.proportion" maxlength="10"/>%
                  </td>
                </tr>
                <tr>
                  <td>注意事项:</td>
                  <td colspan="3">
                  		<input type="text" name="enterpriseProjects.note" style="height:50px;width:200px;"/>
                  </td>
                </tr>
                 <tr>
                  <td colspan="4" style="text-align: center;">
                		<s:submit cssClass="btn btn-primary" value="新增" />
                		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                		<input type="button" class="btn btn-primary" value="新增" />
                	</td>
                </tr>
              </tbody>
            </table>
           </div>
  </body>
</html>
