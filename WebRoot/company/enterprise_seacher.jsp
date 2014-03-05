<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加员工信息</title>
	<%@ include file="/help/public_css_js.jsp"%>
	<script type="text/javascript" src="js/jquery.contextmenu.r2.js"></script>
	<style type="text/css">
		*{margin:0;padding:0;list-style-type:none;}
		a,img{border:0;}
		body{font:14px/180% Arial, Helvetica, sans-serif, "新宋体";}
		.content{margin:0 auto;width:360px;}
		.content p{margin:20px 0 0 0;border:solid 1px #C5D8FF;background:#EDF2FF;padding:10px;}
		
		
		
	</style>
	
  </head>
  	<script type="text/javascript">
  		$(function(){
  			$("#fullname").click(function(){
							$.dialog({
								id:'selEnterprise',
								content:'url:viewEnterpriseEmployees?enterpriseId='+$(this).prev().val(),
								width:'800px',
								height:'500px',
								title:'查看企业员工详细信息',
								lock:true,
								ok:false,
						cancel:true
						});
				});
				$(".demo1").mouseout( function() {  
			       $(this).parent().find("td").each(function(i){ $(this).css({color:"black",background: '#efefef' }) });  
			              
			       });  
				
				  $(".demo1").mouseover( function()
				   {  
				     $(this).parent().find("td").each(function(i){ $(this).css({color:"red",background: '#cccccc' }) });  
			 	 });  
				
				
				//=================================鼠标事件=====================================
				$('.demo1').contextMenu('myMenu1',{
					bindings:{
						'update': function(t){
							alert('Trigger was '+t.id+'\nAction was Open');
						},
						'save': function(t){
							alert('Trigger was '+t.id+'\nAction was Save');
						},
						'seldelete': function(t){
						
								 var $td = $tr.find("td");

       							 $td.eq(0).text();//ctld
					            var tdSeq = $(this).parent().find(".mytable td").index($(this)[0]);  
					            var trSeq = $(this).parent().parent().find(".mytable td tr").index($(this).parent()[0]);  
					            alert("第" + (trSeq + 1) + "行，第" + (tdSeq + 1) + "列");  
		      				 
						}
					}
				});
			
			
				$('.demo2').contextMenu('myMenu2',{
					menuStyle:{
						border: '2px solid #000'
					},
					itemStyle :{
						fontFamily: 'verdana',
						backgroundColor: '#666',
						color: 'white',
						border: 'none',
						padding: '1px'
					},
					itemHoverStyle: {
						color: '#fff',
						backgroundColor: '#0f0',
						border: 'none'
					}
				});
			
			
				$('.demo3').contextMenu('myMenu3',{
				
					onContextMenu: function(e){
						if ($(e.target).attr('id') == 'dontShow') return false;
						else return true;
					},
					onShowMenu: function(e, menu){
						if ($(e.target).attr('id') == 'showOne'){
							$('#item_2, #item_3', menu).remove();
						}
						return menu;
					}
				
				});
				
				
			var content; 
			$("#content tr:odd").css("background-color","#D2B48C"); 
			$("#content tr:even").css("background-color","#C0C0C0"); 
			$("#content td").click(function(){ 
			var clickObj = $(this); 
				content = clickObj.html(); 
				changeToEdit(clickObj); 
			}); 
			function changeToEdit(node)
			{ 
				node.html(""); 
				var inputObj = $("<input type='text'/>"); 
				inputObj.css("border","1").css("background-color",node.css("background-color")) 
				.css("font-size",node.css("font-size")).css("height","20px") 
				.css("width",node.css("width")).val(content).appendTo(node) 
				.get(0).select(); 
				inputObj.click(function(){ 
					return false; 
				}).keyup(function(event){ 
				var keyvalue = event.which; 
				if(keyvalue==13){ 
				node.html(node.children("input").val()); 
				} 
				if(keyvalue==27)
				{ 
					node.html(content); 
				} 
					}).blur(function(){ 
					if(node.children("input").val()!=content)
					{ 
						if(confirm("是否保存修改的内容？","Yes","No"))
						{ 
							node.html(node.children("input").val()); 
						}else{ 
							node.html(content); 
						} 
					}else{ 
						node.html(content); 
					} 
				}); 
		  } 
					
  		
	  			$("a[name=condel]").each(function(index,a){
						$(a).click(function(){
								$.ajax({
											url:'deleteEnterpriseContract?enterpriseContract.id='+$(a).prev().val(),
											type:'post',
											data:'enterpriseContract.id'+$(a).prev().val(),
											contentType:'application/json',
											dataType:'html',
											success:function(){
													$.dialog.alert('删除成功!',function(){
													   this.reload().time(2);
													   $(a).parent().parent().remove();
													});
											}
									});
							});
  					});
  					$("a[name=pro]").each(function(index,a){
						$(a).click(function(){
								$.ajax({
											url:'deleteEnterpriseProjects?enterpriseProjects.id='+$(a).prev().val(),
											type:'post',
											data:'enterpriseProjects.id'+$(a).prev().val(),
											contentType:'application/json',
											dataType:'html',
											success:function(){
													$.dialog.alert('删除成功!',function(){
													   this.reload().time(2);
													   $(a).parent().parent().remove();
													});
											}
									});
							});
  					});
	  		
  			$("a[name=conupda]").each(function(index,a){
						$(a).click(function(){
							// $(a).parent().prev().css('background-color', 'red');
							$.dialog({
								id:'conupda',
								content:'<table align="center" border="1px;" style="margin-left:0px;margin-top:0px;line-height:10px;">'+
										'<tr><td>合同开始日期:</td><td><input type="text" id="d16"onfocus="WdatePicker()" value="'+$(a).parent().prev().prev().prev().prev().prev().prev().text()+'" class="Wdate"/></td></tr>'+
										'<tr><td>合同结束日期:</td><td><input id="d12" class="Wdate" onfocus="WdatePicker()" value="'+$(a).parent().prev().prev().prev().prev().prev().text()+'" type="text"/></td></tr>'+
										'<tr><td>备&nbsp;&nbsp;注</td><td><input type="text" value="'+$(a).parent().prev().text()+'" id="no"/></td></tr>'+
										'</table>',
								width:'500px',
								height:'200px',
								title:'修改企业合同',
								lock:true,
								max: false,
    							min: false,
								ok:function(){
									var dept={
											"enterpriseContract.id":$(a).prev().prev().val(),
											"enterpriseContract.startContractDate":$("#d16").val(),
											"enterpriseContract.endContractDate":$("#d12").val()
										};
									
									$.ajax({
											url:'updateEnterpriseContract?enterpriseContract.id='+$(a).prev().prev().val()+
											'&enterpriseContract.startContractDate='+$("#d16").val()+'&enterpriseContract.endContractDate='+$("#d12").val()+
											'&enterpriseContract.note='+$("#no").val(),
											type:'get',
											data:JSON.stringify(dept),
											contentType:'application/json;charset=UTF-8"',
											dataType:'html',
											success:function(isOk)
											{
												$.dialog.alert('修改成功!',function(){
													 this.reload().time(2);
													   
												});
												
											}
										});
									},
						cancel:true
							});
						});
  					});
  			
  			$("a[name=proupda]").each(function(index,a){
						$(a).click(function(){
							$.dialog({
								id:'proupda',
								content:'url:toUpdateEnterpriseProjects?enterpriseProjects.id='+$(a).prev().prev().val(),
								width:'650px',
								height:'300px',
								title:'修改企业项目',
								lock:true,
								max: false,
    							min: false,
								ok:function(){
									var project=$.dialog.list['proupda'].content.$("#project").val();
									var fee1=$.dialog.list['proupda'].content.$("#fee1").is(':checked')==true?0:1;
									var rentou=$.dialog.list['proupda'].content.$("#rentou").val();
									var bili=$.dialog.list['proupda'].content.$("#bili").val();
									var selType=$.dialog.list['proupda'].content.$("#selType").val();
									var note=$.dialog.list['proupda'].content.$("#note").val();
									
									$.ajax({
											url:'updateEnterpriseProjects?enterpriseProjects.id='+$(a).prev().prev().val()+
											'&enterpriseProjects.projects='+project+'&enterpriseProjects.fee='+fee1+'&enterpriseProjects.serviceHead='+rentou+
											'&enterpriseProjects.serviceType='+selType+'&enterpriseProjects.proportion='+bili+
											'&enterpriseProjects.note='+note,
											type:'post',
											contentType:'application/json',
											dataType:'html',
											success:function(isOk)
											{
												$.dialog.alert('修改成功!',function(){
													 this.reload().time(2);
													   
												});
												
											}
										});
									},
						cancel:true
							});
						});
  					});
  			
			
			
			
			
			
  		
  		});
  		
  		
  	</script>
  	




  <body>
  	<div class="contextMenu" id="myMenu1">
		<ul>
			<li id="open"><img src="images/037.gif" />修&nbsp;&nbsp;改</li>
			<li id="email"><img src="images/menu/menu_addcust.gif" /> Email</li>
			<li id="save"><img src="images/menu/menu_addcust.gif" /> Save</li>
			<li id="seldelete"><img src="images/menu/menu_addcust.gif" />删&nbsp;&nbsp;除</li>
		</ul>
	</div>
  	<div class="datalist">
  		<table width="95%" align="center" border="1px;" style="margin-left:20px;margin-top:30px;line-height:30px;">
  			 <tr style="background-color:#C09853;">
  				 <td colspan="4" align="center"><h2>${enterprise.fullName}</h2></td>
  			 </tr>
  			  <tr>
  			  	  <td><b>合同编号:</b></td>
  			  	  <td colspan="3">
  			  	  	 ${enterprise.contatId}
  			  	  </td>
  			  	  
  			  </tr>
  			  
  			  <tr>
  			  	  <td><b>跟单员:</b></td>
  			  	  <td>
  			  	  	<s:iterator value="%{#request.enterprise.user}" id="user">
  			  	  		${user.employee.name}
  			  	  	</s:iterator>
  			  	  </td>
  			  	  <td>企业名称:</td>
				  <td>
				  	${enterprise.fullName}
				  </td>
  			  </tr>
  			  <tr>
  			  	<td>法人代表:</td>
				  <td>
				  	${enterprise.legalRepresentative}
				  </td>
  			  	<td>开户行:</td>
				  <td>
					${enterprise.accountLine}
				  </td>
				  
  			  </tr>
  			  <tr>
  			  	<td style="padding-top:10px;">企业银行账号 :</td>
  			  	<td style="padding-top:10px;">
					${enterprise.enterpriseBankAccount}
  			  	</td>
  			  	<td style="padding-top:10px;">地&nbsp;&nbsp;&nbsp;址 :</td>
  			  	<td style="padding-top:10px;">
					${enterprise.address}
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td>联系人 :</td>
  			  	<td>
  			  		${enterprise.contact}
  			  	</td>
  			  	<td>派遣联系人 :</td>
  			  	<td>
  			  		${enterprise.send}
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td>电 &nbsp;&nbsp;&nbsp;话</td>
  			  	<td>
  			  		${enterprise.phone}
  			  	</td>
  			  	<td>Q&nbsp;&nbsp;&nbsp;Q</td>
  			  	<td>
  			  		${enterprise.qq}
  			  	</td>
  			  </tr>
  			   <tr>
  			  	<td>传&nbsp;&nbsp;&nbsp;真:</td>
  			  	<td>
  			  		${enterprise.fax}
  			  	</td>
  			  	<td>电子邮件 :</td>
  			  	<td>
  			  		${enterprise.email}
  			  	</td>
  			  </tr>
  			   <tr>
  			  	<td>状&nbsp;&nbsp;&nbsp;态?</td>
  			  	<td>
  			  		<s:if test="#request.enterprise.status==0">
  			  			合约中
  			  		</s:if>
  			  		<s:elseif test="#request.enterprise.status==1">
  			  			暂停 
  			  		</s:elseif>
  			  	</td>
  			  	<td>备&nbsp;&nbsp;&nbsp;注:</td>
  			  	<td>
					${enterprise.note}
  			  	</td>
  			  </tr>
  			  <tr>
  			  	<td>创建时间:</td>
  			  	<td><s:date name="%{#request.enterprise.createDate}" format="yyyy-MM-dd HH:mm:dd"/></td>
  			  	<td>更新时间:</td>
  			  	<td><s:date name="%{#request.enterprise.updateDate}" format="yyyy-MM-dd HH:mm:dd"/></td>
  			  </tr>
  			  <tr>
  			  	  <td title="详细信息"> 目前有职工:</td>
  			  	  <td colspan="3">
  			  	  	 <input type="hidden" value="${enterprise.enterpriseId}"/>
  			  	  	 <a href="javascript:void(0)" id="fullname">[${enterprise.count}]</a>人
  			  	  </td>
  			  </tr>
  		</table>
  		<table width="95%" align="center" border="1px;" style="margin-left:20px;line-height:30px;">
  			<thead>
  				<tr style="background-color:#C09853;"><th colspan="9" align="center" style="font-size:25px;">合作项目详细表</th></tr>
  				<tr>
  					<th>序号</th>
  					<th>项目名称</th>
  					<th>服务性质</th>
  					<th>服务类型</th>
  					<th>服务费</th> 
  					<th>创建时间</th>
  					<th>修改时间</th>
  					<th>注意事项</th>
  					<th>操&nbsp;&nbsp;作</th>
  				</tr>
  			</thead>
  			<s:iterator value="#request.enterprise.enterpriseProjects" id="enterpriseProjects" status="list">
	  			<tbody class="content">
	  				<tr class="demo1" >
	  				<td align="center">${list.index+1}</td>
	  				<td>${enterpriseProjects.projects}</td>
	  				<td>
	  				 <s:if test="#enterpriseProjects.serviceType==3">
	  					 ${enterpriseProjects.customType}
	  				 </s:if>
	  				 <s:if test="#enterpriseProjects.serviceType==1">
	  				 	 完全派遣
	  				 </s:if>
	  				 <s:if test="#enterpriseProjects.serviceType==2">
	  				 	转移派遣
	  				 </s:if>
	  				</td>
	  				<s:if test="#enterpriseProjects.fee==0">
	  					<td>按人头</td>
	  					<td>${enterpriseProjects.serviceHead}</td>
	  				</s:if>
	  				<s:if test="#enterpriseProjects.fee==1">
	  					<td>按比例</td>
	  					<td>${enterpriseProjects.proportion}%</td>
	  				</s:if>
	  				<td><s:date name="%{#enterpriseProjects.createDate}" format="yyyy-MM-dd:HH:mm:dd"/></td>
	  				<td><s:date name="%{#enterpriseProjects.updateDate}" format="yyyy-MM-dd:HH:mm:dd"/></td>
	  				<td>${enterpriseProjects.note}</td>
	  				<td align="center">
	  					<input type="hidden" value="${enterpriseProjects.id}"/>
	  					<s:if test="#request.enterprise.auditStatus!=1">
		  					<a href="javascript:void(0)" name="pro">[删&nbsp;除]</a>
		  					<a href="javascript:void(0)" name="proupda">[修&nbsp;改]</a>
		  				</s:if>
	  				</td>
	  			</tr>
	  			</tbody>
  			</s:iterator>
  		</table>
  		<table width="95%" align="center" border="1px;" class="mytable" style="margin-left:20px;line-height:30px;">
  			<thead>
  				<tr style="background-color:#C09853;"><th colspan="8" align="center" style="font-size:25px;">企业合同资料</th></tr>
  				<tr>
  					<th>序号</th>
  					<th>合同开始日期</th>
  					<th>合同结束日期</th>
  					<th>签订合同时间</th>
  					<th>修改合同时间</th>
  					<th>状&nbsp;&nbsp;态</th>
  					<th>备&nbsp;&nbsp;注</th>
  					<th>操&nbsp;&nbsp;作</th>
  				</tr>
  			</thead>
  			<s:iterator value="%{#request.enterprise.enterpriseContract}" id="enterpriseContract" status="list">
	  			<tbody>
	  				<tr class="demo1">
	  				<td align="center">
	  					<s:property value="%{#list.index+1}"/>
	  					<input type="hidden" value="%{#enterpriseContract.id}" id="id">
	  				</td>
	  				<td><s:date name="%{#enterpriseContract.startContractDate}" format="yyyy-MM-dd"/></td>
	  				<td><s:date name="%{#enterpriseContract.endContractDate}" format="yyyy-MM-dd"/></td>
	  				<td><s:date name="%{#enterpriseContract.createDate}" format="yyyy-MM-dd HH:mm:dd"/></td>
	  				<td><s:date name="%{#enterpriseContract.updateDate}" format="yyyy-MM-dd HH:mm:dd"/></td>
	  				<td>${enterpriseContract.status}</td>
	  				<td>${enterpriseContract.note}</td>
	  				<td align="center">
		  				<s:if test="#request.enterprise.auditStatus!=1">
		  					<input type="hidden" value="${enterpriseContract.id}"/>
		  					<a href="javascript:void(0)" name="condel">[删&nbsp;除]</a>
		  					<a href="javascript:void(0)" name="conupda">[修&nbsp;改]</a>
		  				</s:if>
	  				</td>
	  			</tr>
	  			</tbody>
  			</s:iterator>
  		</table>
  		<div id="newdata" style="padding-top:30px;">
			<table width="95%" border="1" style="margin-left:20px;line-height:30px;">
				<tr style="background-color:#C09853;" >
					<td width="70">审核状态?</td>
					<td>
						
						<input type="radio" name="status" value="0"
					  	 <s:if test="%{(#request.enterprise.auditStatus!= null) && 
					  	 (\"0\" == #request.enterprise.auditStatus)}">checked</s:if> id="status"/>待审核
  			  	 	<input type="radio" name="status" value="1"
  			  	 		<s:if test="%{(#request.enterprise.auditStatus!= null) &&
  			  	 		(\"1\" == #request.enterprise.auditStatus)}">checked</s:if> id="status1"/>审核通过
  			  	 	<input type="radio" name="status" value="2"
  			  	 		<s:if test="%{(#request.enterprise.auditStatus!= null) &&
  			  	 		(\"2\" == #request.enterprise.auditStatus)}">checked</s:if> id="status2"/>审核不通过
					</td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input type="text" name="note" style="height:35px;" value="${enterprise.note}" maxlength="30" id="note">
						<span style="color:red">限制30个字符</span>
					</td>
				</tr>
			</table>
		</div>
  	</div>
  </body>
</html>
