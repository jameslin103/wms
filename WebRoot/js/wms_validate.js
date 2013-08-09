function Ctrl($scope) {

  $scope.user = {name: 'guest', last: 'visitor'};
}
$(document).ready(function(){
	$("#psd").mouseout(function(){
		$("#psd").css("background-color","#E9E9E4");
		$("#psd").val("");
		if($("#psd").text()==""){
			alert($("#psd").value());
		}
		 
	});
	
	
});
function mousc()
{
	
	$("#psd").mouseout(function(){
		  $("psd").css("background-color","#E9E9E4");
	});
}