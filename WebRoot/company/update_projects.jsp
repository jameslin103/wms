<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"/>
    <title>添加企业信息</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<%@ include file="/help/public_css_js.jsp"%>
	<link rel="stylesheet" type="text/css" href="styles/wms.css"/>

	<style>
  		legend{ text-align:center; width:40%\9} 
   </style>
   <script>
   	$(function(){
   	
			$("#bili").focus(function(){
					$("#b").attr("checked",true); 
				
			});
			$("#rentou").focus(function(){
					$("#r").attr("checked",true); 
			});
			
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




		$("#selType").val("${enterpriseProjects.serviceType}");
			
   	
   	})
   
   </script>
   
   
   
  </head>
  
  <body>
  	  <center>
  	  		<br/><br/>
            <table>
              <tbody>
                <tr>
                  <td class="bgc">合作项目:</td>
                  <td colspan="3">
                  	<input type="text"  value="${enterpriseProjects.projects}" maxlength="75" id="project"/>
                  </td>
                </tr>
                <tr>
                  <td class="bgc">服务性质:</td>
                  <td>
                    <select name="enterpriseProjects.serviceType" id="selType">
                      <option value="0">-请选择-</option>
                      <option value="1">完全派遣</option>
                      <option value="2">转移派遣</option>
                      <option value="3">其它</option>
                    </select>
                  </td>
                  <td>服务备注:</td>
                  <td >
                  <input type="text" value="${enterpriseProjects.customType}" id="type"/>
                  </td>
                </tr>
              </tbody>
              <tbody>
                <tr>
                  <td>服务费:</td>
                  <td colspan="3">
                    	<input type="radio"  value="0" id="fee1" name="fee"
					  	<s:if test="%{(#request.enterpriseProjects.fee!= null) && (\"0\" == #request.enterpriseProjects.fee)}">checked</s:if> />按人头，
					  	 <input class="span1" type="text" name="enterpriseProjects.serviceHead" value="${enterpriseProjects.serviceHead}" maxlength="10" id="rentou"/>元/人
  			  	 		<input type="radio" value="1" name="fee"
  			  	 		<s:if test="%{(#request.enterpriseProjects.fee!=null) && (\"1\" == #request.enterpriseProjects.fee)}">checked</s:if> />按比例，
  			  	 		<input class="span1" type="text" name="enterpriseProjects.proportion" value="${enterpriseProjects.proportion}" maxlength="10" id="bili"/>%
                    
                  </td>
                </tr>
                <tr>
                  <td>注意事项:</td>
                  <td colspan="3">
                  		<input type="text"   value="${enterpriseProjects.note}" style="height:50px;width:300px;" id="note"/>
                  </td>
                </tr>
              </tbody>
            </table>
	  </center>
  </body>
</html>
