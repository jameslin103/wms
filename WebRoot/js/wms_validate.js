function Ctrl($scope) {

  $scope.user = {name: 'guest', last: 'visitor'};
}
$(document).ready(function(){
	$("#psd").mouseout(function(){
		var oldPassword=$("#password").val();
		 $("[name='txt']").val("111");
		alert(oldPassword);
		var newPassword=$("#psd").val();
		
		if(oldPassword==""){
			$("#oldPassword_labe").val("请输入旧密码!").css("color","red");
		}
		if($("#psd").text()==""){
			//alert($("#psd").val());
			$("#psd").css("background-color","#E9E9E4");
		}
		 	
	});
	
	
});
function mousc()
{
	
	$("#psd").mouseout(function(){
		  $("psd").css("background-color","#E9E9E4");
	});
}