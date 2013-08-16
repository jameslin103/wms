function Ctrl($scope) {

  $scope.user = {name: 'guest', last: 'visitor'};
}
$(document).ready(function(){
	validate();
	
	
});

function ajaxUpdateEnterprise()
{
		var aj = $.ajax( {    
		    url:'updateEnterprise',// 跳转到 action    
		    data:{    
		           selRollBack : selRollBack,    
		           selOperatorsCode : selOperatorsCode,    
		           PROVINCECODE : PROVINCECODE,    
		           pass2 : pass2  
		    },    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {    
		        if(data.msg =="true" ){    
		            alert("修改成功！");    
		            window.location.reload();    
		        }else{    
		            view(data.msg);    
		        }    
		     },    
		     error : function() {  
		          alert("异常！");    
		     }    
	});  
}
























function mousc()
{
	
	$("#psd").mouseout(function(){
		  $("psd").css("background-color","#E9E9E4");
	});
}