function Ctrl($scope) {

  $scope.user = {name: 'guest', last: 'visitor'};
}




$(document).ready(function(){

	ajaxfindBeforeCurrentDateTemplate();
	valitate_xls();
	
	
	
	
});



function ajaxfindBeforeCurrentDateTemplate()
{
	$(".Wdate").blur(function (){
		var template=$("#d11").val();
		if(template!=""){
			$.ajax( {    
			    url:'findBeforeCurrentDateTemplate',// 跳转到 action  
			    data:{ salaryDate:template},    
			    type:'post',    
			    cache:false,
			    dataType:'json',    
			    success:function(data){
			        if(data.error=="true" ){
			        	  $("#salaryTable").html("");
			        	  $("#salaryTable").css('');
			        	  $(data.createSalaryBudgetTableList).each(function(i, value){
			        		  $("#salaryTable").append("<option value='" +value.budgetId+"'>"
			        				  + value.name + "</option>");  
			        	  });
			        }else{    
			        	 $("#salaryTable").html("");
			        	 $("#salaryTable").append("<option >无数据</option>");
			        	 //$("#salaryTable").css("background-color", "yellow");
			        }    
			     },    
			     error : function() {  
			          alert("异常！");    
			     }    
		});	
		}
	});
  
}

function valitate_xls()
{
	$("#uploadFile").click(function()
	{
		var file=$("#filevalue").val();
		if(file!='' && file!=undefined)
		{
			
			var file_format=file.lastindexof(".")+1;
			alert(file_format);
		    if (file_format!='xlsx'  && file_format!='xls')
		       {
		         alert('不支持该格式文件请重新选择！！');
		         file.val('');
		         return false;
		         
		        }
		    return false;
		}
		else
		{
			  alert('上传文件不能为空！！');
			  return false;
		}
  });
}













function mousc()
{
	
	$("#psd").mouseout(function(){
		  $("psd").css("background-color","#E9E9E4");
	});
}