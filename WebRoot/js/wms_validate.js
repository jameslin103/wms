function Ctrl($scope) {

  $scope.user = {name: 'guest', last: 'visitor'};
}




$(document).ready(function(){

	ajaxfindBeforeCurrentDateTemplate();
	valitate_xls();

	
	
	
});

/**
 * json 请求查询企业信息
 * @param enterpriseId
 * @return
 */
function modalEnterprise(enterpriseId)
{
	if(enterpriseId!=null && enterpriseId!=undefined){
		$.ajax( {    
		    url:'findToIdEnterprise',// 跳转到 action  
		    data:{enterpriseId:enterpriseId},    
		    type:'post',    
		    cache:false,
		    dataType:'json',    
		    success:function(data){
		     
		    	
		      $("input[name='enterprise.enterpriseId']").val(data.enterpriseJson.enterpriseId);
		      $("input[name='enterprise.rferred']").val(data.enterpriseJson.rferred);
		      $("input[name='enterprise.fullName']").val(data.enterpriseJson.fullName);
		      $("input[name='enterprise.legalRepresentative']").val(data.enterpriseJson.legalRepresentative);
		      $("input[name='enterprise.accountLine']").val(data.enterpriseJson.accountLine);
		      $("input[name='enterprise.enterpriseBankAccount']").val(data.enterpriseJson.enterpriseBankAccount);
		      $("input[name='enterprise.address']").val(data.enterpriseJson.address);
		      $("input[name='enterprise.contact']").val(data.enterpriseJson.contact);
		      $("input[name='enterprise.phone']").val(data.enterpriseJson.phone);
		      $("input[name='enterprise.qq']").val(data.enterpriseJson.qq);
		      $("input[name='enterprise.fax']").val(data.enterpriseJson.fax);
		      $("input[name='enterprise.email']").val(data.enterpriseJson.email);
		      $("input[name='enterprise.status'][value="+data.enterpriseJson.status+"]").attr("checked",true); 
		    
		       
		       
		       
		       
		        
		     },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	
	}
}

function findEnterpriseToUser(enterpriseId)
{
	if(enterpriseId!=null && enterpriseId!=undefined){
		$.ajax( {    
		    url:'findToIdEnterprise',// 跳转到 action  
		    data:{enterpriseId:enterpriseId},    
		    type:'post',    
		    cache:false,
		    dataType:'json',    
		    success:function(data){
		      $("#fullName").html('');
		      $("#fullName").html(data.enterpriseJson.fullName);
		      
		     },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	
	}

}



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
/**
 * 查找修改的基数
 * @param taxId
 * @return
 */
function findInsurancesTax(taxId)
{
	
	if(taxId!=null && taxId!=undefined){
		$.ajax( {    
		    url:'findInsurancesTax',// 跳转到 action  
		    data:{taxId:taxId},    
		    type:'post',    
		    cache:false,
		    dataType:'json',    
		    success:function(data){
		    	$("input[name='insurancesTax.id']").val(data.insuranceTaxJson.id); 
		    	$("input[name='insurancesTax.insurancesType'][value="+data.insuranceTaxJson.insurancesType+"]").attr("checked",true); 
		    	$("input[name='insurancesTax.endowmentInsurance']").val(data.insuranceTaxJson.endowmentInsurance); 
		    	$("input[name='insurancesTax.personalEndowmentInsurance']").val(data.insuranceTaxJson.personalEndowmentInsurance); 
		    	$("input[name='insurancesTax.unemploymentInsurance']").val(data.insuranceTaxJson.unemploymentInsurance); 
		    	$("input[name='insurancesTax.personalUnemploymentInsurance']").val(data.insuranceTaxJson.personalUnemploymentInsurance); 
		    	$("input[name='insurancesTax.birthEnterprise']").val(data.insuranceTaxJson.birthEnterprise); 
		    	$("input[name='insurancesTax.injuriesEnterprise']").val(data.insuranceTaxJson.injuriesEnterprise); 
		    	$("input[name='insurancesTax.medicalEnterprise']").val(data.insuranceTaxJson.medicalEnterprise); 
		    	$("input[name='insurancesTax.personalEnterprise']").val(data.insuranceTaxJson.personalEnterprise); 
		    	$("input[name='insurancesTax.housingFundEnterprise']").val(data.insuranceTaxJson.housingFundEnterprise); 
		    	$("input[name='insurancesTax.personalHousingFund']").val(data.insuranceTaxJson.personalHousingFund); 
		    	$("input[name='insurancesTax.startDate']").val(data.insuranceTaxJson.startDate); 
		    
		    },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	
	}

}

function findIdToBaseTax(baseId)
{
	if(baseId!=null && baseId!=undefined){
		$.ajax( {    
		    url:'findIdToBaseTax',// 跳转到 action  
		    data:{baseId:baseId},    
		    type:'post',    
		    cache:false,
		    dataType:'json',    
		    success:function(data){
		    	
		    	$("input[name='insurancesBaseSettings.id']").val(data.insurancesBaseSettingsJosn.id); 
		    	$("input[name='insurancesBaseSettings.insurancesType'][value="+data.insurancesBaseSettingsJosn.insurancesType+"]").attr("checked",true); 
		    	$("input[name='insurancesBaseSettings.socialInsurance']").val(data.insurancesBaseSettingsJosn.socialInsurance); 
		    	$("input[name='insurancesBaseSettings.birthInsurance']").val(data.insurancesBaseSettingsJosn.birthInsurance); 
		    	$("input[name='insurancesBaseSettings.inductrialInjury']").val(data.insurancesBaseSettingsJosn.inductrialInjury); 
		    	$("input[name='insurancesBaseSettings.housingMPF']").val(data.insurancesBaseSettingsJosn.housingMPF); 
		    	$("input[name='insurancesBaseSettings.basicMedical']").val(data.insurancesBaseSettingsJosn.basicMedical); 
		    	$("input[name='insurancesBaseSettings.povertyStricken']").val(data.insurancesBaseSettingsJosn.povertyStricken); 
		    	$("input[name='insurancesBaseSettings.startDate']").val(data.insurancesBaseSettingsJosn.startDate); 
		    
		    },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	
	}
}



function findIdToEmployees(employeesId)
{
	if(employeesId!=null && employeesId!=undefined){
		$.ajax( {    
		    url:'findIdToEmployees',// 跳转到 action  
		    data:{employeesId:employeesId},    
		    type:'post',    
		    cache:false,
		    dataType:'json',    
		    success:function(data){
		    	var sex=(data.enterpriseEmployees.employeesSex=='男')?1:0;
		    	var way=(data.enterpriseEmployees.paymentWay='个人')?1:0;
		    	
		    	$("input[name='enterpriseEmployees.employeesId']").val(data.enterpriseEmployees.employeesId); 
		    	$("input[name='enterpriseEmployees.contractNo']").val(data.enterpriseEmployees.contractNo);  
		    	$("input[name='enterpriseEmployees.employeesName']").val(data.enterpriseEmployees.employeesName); 
		    	$("input[name='enterpriseEmployees.cardNumber']").val(data.enterpriseEmployees.cardNumber); 
		    	$("input[name='enterpriseEmployees.employeesSex'][value="+sex+"]").attr("checked",true);
		    	$("input[name='enterpriseEmployees.householdRegister'][value="+data.enterpriseEmployees.householdRegister+"]").attr("checked",true);
		    	$("input[name='enterpriseEmployees.photo'][value="+data.enterpriseEmployees.photo+"]").attr("checked",true);
		    	$("input[name='enterpriseEmployees.phone']").val(data.enterpriseEmployees.phone); 
		    	$("input[name='enterpriseEmployees.homeAddress']").val(data.enterpriseEmployees.homeAddress); 
		    	$("input[name='enterpriseEmployees.bankCardNumber']").val(data.enterpriseEmployees.bankCardNumber); 
		    	$("input[name='enterpriseEmployees.bank']").val(data.enterpriseEmployees.bank); 
		    	$("input[name='enterpriseEmployees.nativePlace']").val(data.enterpriseEmployees.nativePlace); 
		    	$("input[name='enterpriseEmployees.industry']").val(data.enterpriseEmployees.industry); 
		    	$("input[name='enterpriseEmployees.jobs']").val(data.enterpriseEmployees.jobs); 
		    	$("input[name='enterpriseEmployees.maritalStatus'][value="+data.enterpriseEmployees.maritalStatus+"]").attr("checked",true);
		    	$("input[name='enterpriseEmployees.levelEducation']").val(data.enterpriseEmployees.levelEducation); 
		    	$("input[name='enterpriseEmployees.startContractDeadline']").val(data.enterpriseEmployees.startContractDeadline); 
		    	$("input[name='enterpriseEmployees.endContractDeadline']").val(data.enterpriseEmployees.endContractDeadline); 
		    	$("input[name='enterpriseEmployees.whetherGinseng']").val(data.enterpriseEmployees.whetherGinseng); 
		    	$("input[name='enterpriseEmployees.sociaSecurity']").val(data.enterpriseEmployees.sociaSecurity); 
		    	$("input[name='enterpriseEmployees.ginsengProtectNature']").val(data.enterpriseEmployees.ginsengProtectNature); 
		    	$("input[name='enterpriseEmployees.cinsengDate']").val(data.enterpriseEmployees.cinsengDate); 
		    	$("input[name='enterpriseEmployees.base']'][value="+data.enterpriseEmployees.base+"]").attr("checked",true);
		    	$("input[name='enterpriseEmployees.socialInsurance']").val(data.enterpriseEmployees.socialInsurance); 
		    	$("input[name='enterpriseEmployees.fertilityInsurance']").val(data.enterpriseEmployees.fertilityInsurance); 
		    	$("input[name='enterpriseEmployees.inductrialBase']").val(data.enterpriseEmployees.inductrialBase); 
		    	$("input[name='enterpriseEmployees.basicMedical']").val(data.enterpriseEmployees.basicMedical); 
		    	$("input[name='enterpriseEmployees.housingFund']").val(data.enterpriseEmployees.housingFund); 
		    	$("input[name='enterpriseEmployees.paymentWay']'][value="+way+"]").attr("checked",true) 
		    	$("input[name='enterpriseEmployees.pseudoDelete']").val(data.enterpriseEmployees.pseudoDelete); 
		    	
		    	$("input[name='enterpriseEmployees.serviceCost']").val(data.enterpriseEmployees.serviceCost); 
		    	
		    },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	
	}
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












