function Ctrl($scope) {

  $scope.user = {name: 'guest', last: 'visitor'};
}




$(document).ready(function(){

	ajaxfindBeforeCurrentDateTemplate();
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
























function mousc()
{
	
	$("#psd").mouseout(function(){
		  $("psd").css("background-color","#E9E9E4");
	});
}