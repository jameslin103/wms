function Ctrl($scope) {

  $scope.user = {name: 'guest', last: 'visitor'};
}
$(document).ready( function() {  
//    $("table td").mouseout( function() {  
//
//        $(this).parent().find("td").each(function(i){ $(this).css({color:"black",background: '#efefef' }) });  
//              
//        });  
//
//    $("table td").mouseover( function() {  
//          
//    $(this).parent().find("td").each(function(i){ $(this).css({color:"red",background: '#cccccc' }) });  
//    });  
	
	
	
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
		      $("input[name='enterprise.contatId']").val(data.enterpriseJson.contatId);
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
//清除所有表单
function clearForm(objE){//objE为form表单  
    $(objE).find(':input').each(  
        function(){  
            switch(this.type){  
                case 'passsword':  
                case 'select-multiple':  
                case 'select-one':  
                case 'text':  
                case 'textarea':  
                    $(this).val('');  
                    break;  
                case 'checkbox':  
                case 'radio':  
                    this.checked = false;  
            }  
        }  
    );  
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
		      $("#user").val(data.enterpriseJson.user);
		      $("#fullName").html('');
		      $("#fullName").html(data.enterpriseJson.fullName);
		      $("input[name='enterpriseId']").val(data.enterpriseJson.enterpriseId);
		      $(".list-of-items-for-delete").html('');
			      $.each(data.users,function(i,value){
			    	  $(".list-of-items-for-delete").
			    	  append("<li>"+value.account+"<a href='removeToEnterpriseHeadUser?user.id="+value.id+"" +
			    	  		"&enterpriseId="+data.enterpriseJson.enterpriseId+"'style='width: 50px;'>(删除)</a></li>");
			      });
		     },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	
	}

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
		    	
		    	var sex=(data.enterpriseEmployeesJson.employeesSex=='男')?1:0;
		    	$("input[name='enterpriseEmployees.employeesId']").val(employeesId); 
		    	$("input[name='enterpriseEmployees.contractNo']").val(data.enterpriseEmployeesJson.contractNo);  
		    	$("input[name='enterpriseEmployees.employeesName']").val(data.enterpriseEmployeesJson.employeesName); 
		    	$("input[name='enterpriseEmployees.cardNumber']").val(data.enterpriseEmployeesJson.cardNumber); 
		    	$("input[name='enterpriseEmployees.employeesSex'][value="+sex+"]").attr("checked",true);
		    	$("input[name='enterpriseEmployees.householdRegister'][value="+data.enterpriseEmployeesJson.householdRegister+"]").attr("checked",true);
		    	$("input[name='enterpriseEmployees.photo'][value="+data.enterpriseEmployeesJson.photo+"]").attr("checked",true);
		    	$("input[name='enterpriseEmployees.phone']").val(data.enterpriseEmployeesJson.phone); 
		    	$("input[name='enterpriseEmployees.homeAddress']").val(data.enterpriseEmployeesJson.homeAddress); 
		    	$("input[name='enterpriseEmployees.bankCardNumber']").val(data.enterpriseEmployeesJson.bankCardNumber); 
		    	$("input[name='enterpriseEmployees.bank']").val(data.enterpriseEmployeesJson.bank); 
		    	$("input[name='enterpriseEmployees.nativePlace']").val(data.enterpriseEmployeesJson.nativePlace); 
		    	$("input[name='enterpriseEmployees.industry']").val(data.enterpriseEmployeesJson.industry); 
		    	$("input[name='enterpriseEmployees.jobs']").val(data.enterpriseEmployeesJson.jobs); 
		    	$("input[name='enterpriseEmployees.maritalStatus'][value="+data.enterpriseEmployeesJson.maritalStatus+"]").attr("checked",true);
		    	$("input[name='enterpriseEmployees.levelEducation']").val(data.enterpriseEmployeesJson.levelEducation); 
		    	$("input[name='enterpriseEmployees.whetherGinseng'][value="+data.enterpriseEmployeesJson.whetherGinseng+"]").attr("checked",true);
		    	$("input[name='enterpriseEmployees.accident']").val(data.enterpriseEmployeesJson.accident); 
		    	
				var sociaSecurity=data.enterpriseEmployeesJson.sociaSecurity;
				var healthCare=data.enterpriseEmployeesJson.healthCare;
				var accumulationFund=data.enterpriseEmployeesJson.accumulationFund;
				
				if(sociaSecurity=='是'){
					$("input[name='enterpriseEmployees.sociaSecurity']").attr("checked",'true');
				}
				if(healthCare=='是'){
					$("input[name='enterpriseEmployees.healthCare']").attr("checked",'true');
				}
				if(accumulationFund=='是'){
					$("input[name='enterpriseEmployees.accumulationFund']").attr("checked",'true');
				}
				$("input[name='enterpriseEmployees.ginsengProtectNature'][value="+data.enterpriseEmployeesJson.ginsengProtectNature+"]").attr("checked",true);
		    	if(data.enterpriseEmployeesJson.cinsengDate!=null){
		    		var   cinsengDate=new   Date(data.enterpriseEmployeesJson.cinsengDate).format("yyyy-MM-dd");
			    	$("input[name='enterpriseEmployees.cinsengDate']").val(data.enterpriseEmployeesJson.cinsengDate); 	
		    	}

		    	$("input[name='enterpriseEmployees.base'][value="+data.enterpriseEmployeesJson.base+"]").attr("checked",true);
		    	$("input[name='enterpriseEmployees.socialInsurance']").val(data.enterpriseEmployeesJson.socialInsurance); 
		    	$("input[name='enterpriseEmployees.fertilityInsurance']").val(data.enterpriseEmployeesJson.fertilityInsurance);
		    	$("input[name='enterpriseEmployees.inductrialBase']").val(data.enterpriseEmployeesJson.inductrialBase); 
		    	$("input[name='enterpriseEmployees.basicMedical']").val(data.enterpriseEmployeesJson.basicMedical); 
		    	$("input[name='enterpriseEmployees.housingFund']").val(data.enterpriseEmployeesJson.housingFund); 
		    	$("input[name='enterpriseEmployees.paymentWay'][value="+data.enterpriseEmployeesJson.paymentWay+"]").attr("checked",true) 
		    	$("input[name='enterpriseEmployees.pseudoDelete'][value="+data.enterpriseEmployeesJson.pseudoDelete+"]").attr("checked",true); 
		    	$("input[name='enterpriseEmployees.serviceCost']").val(data.enterpriseEmployeesJson.serviceCost); 
		    	$("input[name='enterpriseEmployees.seriousDisease']").val(data.enterpriseEmployeesJson.seriousDisease); 
		    	
		    	
			    	if(data.employeesContract.contractStatrDate!=null){
			    		var   contractStatrDate=new   Date(data.employeesContract.contractStatrDate).format("yyyy-MM-dd");  
				    	$("input[name='employeesContract.contractStatrDate']").val(data.employeesContract.contractStatrDate); 
			    	}
			    	if(data.employeesContract.contractEndDate!=null){
				    	var   contractEndDate=new   Date(data.employeesContract.contractEndDate).format("yyyy-MM-dd");  
				    	$("input[name='employeesContract.contractEndDate']").val(data.employeesContract.contractEndDate); 
			    	}
			    	if(data.employeesContract.contractid!=null){
			    		$("input[name='employeesContract.contractid']").val(data.employeesContract.contractid); 
			    	}
		    },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});
		
	}
}
function time(time){

     var data = new Date(time);  
     var year = data.getFullYear();  //获取年
     var month = data.getMonth() + 1;    //获取月
     var day = data.getDate(); //获取日
     var hours = data.getHours(); 
     var minutes = data.getMinutes();
     time = year + "年" + month + "月" + day + "日"
     alert(time);

}

function findToIdBalanceDetail(detailId)
{
	if(detailId!=null && detailId!=undefined){
		$("input[name='balanceDetail.detailId']").val(detailId);
		$.ajax( {    
		    url:'findToIdBalanceDetail',// 跳转到 action  
		    data:{detailId:detailId},    
		    type:'post',    
		    cache:false,
		    dataType:'json',    
		    success:function(data){
		    	
		    	$("input[name='balanceDetail.detailId']").val(data.balanceDetail.detailId); 
		    	$("input[name='balanceDetail.receivedFunds']").val(data.balanceDetail.receivedFunds);
		    	$("input[name='balanceDetail.wages']").val(data.balanceDetail.receivedFunds); 
		    	$("input[name='balanceDetail.serviceWith']").val(data.balanceDetail.serviceWith); 
		    	$("input[name='balanceDetail.fiveFund']").val(data.balanceDetail.fiveFund); 
		    	$("input[name='balanceDetail.note']").val(data.balanceDetail.note); 
		    
		    },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	
	}
	$("#add-role,#edit-user").on("hidden",function(){
		$("form :checkbox",this).removeAttr("checked");
		$("form",this)[0].reset();
	});
}


function findToIdSalayBudegTable(budgetId)
{
	if(budgetId!=null && budgetId!=undefined){
		$.ajax( {    
		    url:'findToIdSalayBudegTable',// 跳转到 action  
		    data:{budgetId:budgetId},    
		    type:'post',    
		    cache:false,
		    dataType:'json',    
		    success:function(data){
		    	$("input[name='budgetId']").val(budgetId); 
		    	$("input[name='createSalaryBudgetTable.budgetId']").val(data.createSalaryBudgetTable.budgetId);
		    	$("input[name='createSalaryBudgetTable.makeTotal']").val(data.createSalaryBudgetTable.makeTotal);
		    	$("input[name='createSalaryBudgetTable.serviceHeTotal']").val(data.createSalaryBudgetTable.serviceHeTotal);
		    	$("input[name='createSalaryBudgetTable.serviceTotal']").val(data.createSalaryBudgetTable.serviceTotal); 
		    	$("#templateName").text(data.createSalaryBudgetTable.templateName)
		    	$("#budname").val(data.createSalaryBudgetTable.name==null?"":data.createSalaryBudgetTable.name); 
		    	$("#saldate").val(data.createSalaryBudgetTable.salaryDate);  
		    	$("#salaryTable").empty();
		    	$("#note").val(data.createSalaryBudgetTable.note);
		    
		    
		    	

		    },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	
	}
}

function findToIdCustomBonus(id)
{
	if(id!=null && id!=undefined){
		$.ajax( {    
		    url:'findToIdCustomBonus',// 跳转到 action  
		    data:{id:id},    
		    type:'post',    
		    cache:false,
		    dataType:'json',    
		    success:function(data){
		    	$("input[name='customBonus.id']").val(data.customBonus.id); 
		    	$("#bonusName").val(data.customBonus.bonusName); 
		    	$("input[name='customBonus.state'][value="+data.customBonus.state+"]").attr("checked",true);
		    
		    	

		    },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	
	}



}
function findToIdSalaryTemplate(templateId){
	
	if(templateId!=null && templateId!=undefined){
		$.ajax( {    
		    url:'findToIdSalaryTemplate',// 跳转到 action  
		    data:{templateId:templateId},    
		    type:'post',    
		    cache:false,
		    dataType:'json',    
		    success:function(data){
		    	
		    	$("input[name='salaryTemplate.templateId']").val(data.salaryTemplate.templateId); 
		    	$("input[name='salaryTemplate.templateName']").val(data.salaryTemplate.templateName); 
		    	
		    	var sublist=data.salaryTemplate.subsidyList;
		    	//alert(sublist.split(","));
		    	$("input[name='salaryTemplate.subsidyList'][value="+data.salaryTemplate.subsidyList+"]").attr("checked",true);
		    	
		    	
		    	$("input[name='salaryTemplate.fiveInsurances'][value="+data.salaryTemplate.fiveInsurances+"]").attr("checked",true);
		    	$("input[name='salaryTemplate.tax'][value="+data.salaryTemplate.tax+"]").attr("checked",true);
		    	$("input[name='salaryTemplate.status'][value="+data.salaryTemplate.status+"]").attr("checked",true); 
		    
		    	

		    },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	
	}

	
	
}

function findToIdSalaryDetail(salaryId)
{
	
	if(salaryId!=null && salaryId!=undefined){
		$.ajax( {    
		    url:'findToIdSalaryDetail',// 跳转到 action  
		    data:{salaryId:salaryId},    
		    type:'post',    
		    cache:false,
		    dataType:'json',    
		    success:function(data){
		    	
		    	$("input[name='employeesSalaryDetail.salaryId']").val(data.employeesSalaryDetail.salaryId); 
		    	$("input[name='budgetId']").val(data.employeesSalaryDetail.budgettableId); 
		    	$("#name").text(data.employeesSalaryDetail.employeesName);
		    	$("input[name='employeesSalaryDetail.wage']").val(data.employeesSalaryDetail.wage); 
		    	$("input[name='employeesSalaryDetail.bonus']").val(data.employeesSalaryDetail.bonus); 
		    	$("input[name='employeesSalaryDetail.subsidies']").val(data.employeesSalaryDetail.subsidies); 
		    
		    	

		    },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	

	}
		
}




function findEnterpriseEmployeesRecution(enterpriseId,month,year)
{
	if(enterpriseId!=null && enterpriseId!=undefined){
		$.ajax( {    
		    url:'findEnterpriseEmployeesRecution',// 跳转到 action  
		    data:{enterpriseId:enterpriseId,month:month,year:year},    
		    type:'post',    
		    cache:false,
		    dataType:'json',    
		    success:function(data){
		    	
	    		var reductionState=0;
	    		var enterpriseId=0;
	    		var reductionNote;
	    		$("input[name='reductionState'][value="+0+"]").attr("checked",true);
		    	$.each(data.employeesRecution,function(i,value){
		    		
		    		enterpriseId=value.enterprise.enterpriseId;
		    		reductionState=value.reductionState;
		    		reductionNote=value.reductionNote;
			    	
		    	});
		    	
		    	$("input[name='enterpriseId']").val(enterpriseId);
		    	$("input[name='reductionState'][value="+reductionState+"]").attr("checked",true);
		    	$("input[name='year']").val(year);
		    	$("input[name='month']").val(month);
		    	$("input[name='reductionNote']").val(reductionNote);
		    },    
		     error : function() {  
		    	 alert("系统异常，请稍后重试！");
		     }    
		});	

	}



}

//合同续签

function  findContractJson(contractid){
	if(contractid==null || contractid==undefined)return;
	var employeesId=$("#employeesId").val();
	$.ajax({    
	    url:'findContractJson',// 跳转到 action  
	    data:{contractid:contractid},    
	    type:'post',    
	    cache:false,
	    dataType:'json',    
	    success:function(data){
	    	
	    	var   contractStatrDate=new   Date(data.employeesContract.contractStatrDate).format("yyyy-MM-dd");   
	    	var   contractEndDate=new   Date(data.employeesContract.contractEndDate).format("yyyy-MM-dd");  
	    	
	    	$("input[name='employeesId']").val(employeesId);
	    	$("input[name='employeesContract.contractNo']").val(data.employeesContract.contractNo);
	    	$("input[name='employeesContract.contractStatrDate']").val(contractStatrDate);
	    	$("input[name='employeesContract.contractEndDate']").val(contractEndDate);
	    	
	    	var currentDate=new Date().format("yyyy-MM-dd");
	    	var data=$("#d4311").val();
	    	if(data<currentDate){
	    		//$("#d4311").attr("disabled",true);
	    	}else{
	    		//$("#d4311").removeAttr("disabled");
	    	}
	    },    
	     error : function() {  
	    	 alert("系统异常，请稍后重试！");
	     }    
	});	
		

}
function findSalaryTable(budgetId){
	if(budgetId==null || budgetId==undefined)return;
	$.ajax({    
	    url:'findSalaryTable',// 跳转到 action  
	    data:{budgetId:budgetId},    
	    type:'post',    
	    cache:false,
	    dataType:'json',    
	    success:function(data){
	    	
	    	$("input[name='budgetId']").val(budgetId);
	    	$("input[name='createSalaryBudgetTable.cmbcDate']").val('');
	    	$("input[name='createSalaryBudgetTable.cashnumberDate']").val('');
	    	$("input[name='createSalaryBudgetTable.heLinesDate']").val('');
	    	$("input[name='createSalaryBudgetTable.status']").val('');
	    	if(data.createSalaryBudgetTable.cmbcDate!=null && data.createSalaryBudgetTable.cmbcDate!=""){
	    		var   cmbcDate=new Date(data.createSalaryBudgetTable.cmbcDate).format("yyyy-MM-dd"); 
	    		$("input[name='createSalaryBudgetTable.cmbcDate']").val(data.createSalaryBudgetTable.cmbcDate);
	    	}
	    	if(data.createSalaryBudgetTable.cashnumberDate!=null){
	    		var   cashnumberDate=new Date(data.createSalaryBudgetTable.cashnumberDate).format("yyyy-MM-dd"); 
	    		$("input[name='createSalaryBudgetTable.cashnumberDate']").val(data.createSalaryBudgetTable.cashnumberDate);
	    	}
	    	if(data.createSalaryBudgetTable.heLinesDate!=null){
	    		var   heLinesDate=new  Date(data.createSalaryBudgetTable.heLinesDate).format("yyyy-MM-dd");
	    		$("input[name='createSalaryBudgetTable.heLinesDate']").val(data.createSalaryBudgetTable.heLinesDate);
	    	}
	    	$("input[name='createSalaryBudgetTable.status']").val(data.createSalaryBudgetTable.status);
	    },    
	     error : function() {  
	    	 alert("系统异常，请稍后重试！");
	     }    
	});	
	
}







/** 
 * 时间对象的格式化 
 */   
 Date.prototype.format = function(format) //author: meizz 
 { 
   var o = { 
     "M+" : this.getMonth()+1, //month 
     "d+" : this.getDate(),    //day 
     "h+" : this.getHours(),   //hour 
     "m+" : this.getMinutes(), //minute 
     "s+" : this.getSeconds(), //second 
     "q+" : Math.floor((this.getMonth()+3)/3),  //quarter 
     "S" : this.getMilliseconds() //millisecond 
   } 
   if(/(y+)/.test(format)) format=format.replace(RegExp.$1, 
     (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   for(var k in o)if(new RegExp("("+ k +")").test(format)) 
     format = format.replace(RegExp.$1, 
       RegExp.$1.length==1 ? o[k] : 
         ("00"+ o[k]).substr((""+ o[k]).length)); 
   return format; 
 } 







function valitate_xls()
{
	$("#uploadFile").click(function()
	{
		var file=$("#filevalue").val();
		if(file!='' && file!=undefined)
		{
			
			var file_format=file.lastindexof(".")+1;
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


$(function (){
	   
 	  $("input[name='year']").blur(function(){
 	  	   var year=$("input[name='year']").val();
 	  	    if(year<4){
 	  	   	  return;
 	  	    }
 	  	 	if(year!="" && year!=undefined)
 	  	 	{
 	  			$("#myform1").submit(); 
 	         }
 	  });
 	   
 });


function topage(page){
	
	var form = document.getElementById("myform");
		form.page.value=page;
		form.submit();
}
//权限角色管理
$(document).ready(function(){
	$("#add-role,#edit-role").on("hidden",function(){
		$("form :checkbox",this).removeAttr("checked");
		$("form",this)[0].reset();
	});
	$("#delete-role .btn-confirm").on("click",function(){
		$("#delete-role form").submit();
	});
	$(".delete-role-btn").on("click",function(){
		$("#delete-role form .roleId").val($(this).data("id"));
	});
	$(".edit-role-btn").on("click",function(){
		$.getJSON("loadRole",{"role.roleId":$(this).data("id")}).success(function(data){
			$("#edit-role .roleName").html(data.name);
			$("#edit-role .roleId").val(data.roleId);
			var menuIds = data.menuIds.split(",");
			for(var i=0;i<menuIds.length;i++){
				var menuId=menuIds[i];
				$("#edit-role .menu-id").each(function(){
					if($(this).val()==menuId)$(this).attr("checked",true);
				});
			}
		});
	});
});
//删除员工全选
$(function(){
	 	
    $("#all_box").click(function() {
         $('input[name="enterpriseEmployees.employeesId"]').attr("checked",this.checked);
     });
     var $subBox = $("input[name='enterpriseEmployees.employeesId']");
     $subBox.click(function(){
         $("#all_box").attr("checked",$subBox.length == $("input[name='enterpriseEmployees.employeesId']:checked").length ? true : false);
     });
     
	  $('#delete').click(function (){
	    	 var array = new Array();
	    	 var flag;
	    	 $("input[name='enterpriseEmployees.employeesId']:checkbox").each(function() {
	    		 if ($(this).attr("checked")) { //判断是否选中    
	    		        flag = true; //只要有一个被选择 设置为 true  
                 }  
             });
	    	  if (flag)
	    	  {  
	    		  if(confirm("确定要删除选定的员工；删除后将不可恢复?")){
	    			  $("input[name='enterpriseEmployees.employeesId']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox  
                          if ($(this).attr("checked")) { //判断是否选中    
                              //alert($(this).val());  
                              array.push($(this).val()); //将选中的值 添加到 array中  
                             // str+=$(this).val()+",";  
                          }  
                      }) ;
	    			  //将要集体删除的数据 传递给action处理   
	    			  
	    			  window.self.location="deleteEmpoyeesCheckbox?employees_id="+ array;  
	    		  }
	            
	          } else{  
	              alert("请至少选择一条数据?");  
	              return false;
	          }  
	    });
 });

$(function (){
	$("#sub").click(function(){
		$("#nameerror").css("");
		$("#temp").text("");
		$("#temp").css("");
		$("#nameerror").text("");
		$("#salaryDate").text("");
		$("#salaryDate").css("");
		var name=$("#budgetName").val();
		var tempid=$("#tempid").val();
		var salaryDate=$("input[name='salaryDate']").val();
		if(name==""){
			$("#nameerror").text("预算表名称必填项!");
			$("#nameerror").css("color","red");
			return false;
		}
		if(tempid==0)
		{
			$("#temp").text("模板必填项!");
			$("#temp").css("color","red");
			return false;
		}
		if(tempid=="")
		{
			$("#temp").text("模板必填项!");
			$("#temp").css("color","red");
			return false;
		}
		if(salaryDate==""){
			$("#salaryDate").text("生成那月工资必填项!");
			$("#salaryDate").css("color","red");
			return false;
		
		}
	});

});

/*
02
函数：把字符串转换为日期对象
03
参数：yyyy-mm-dd或dd/mm/yyyy形式的字符串
04
返回：Date对象
05
注：IE下不支持直接实例化日期对象，如new Date("2011-04-06")
06
*/
Date.prototype.convertDate = function (date)
{
	alert(date);
    var flag = true;
    var dateArray = date.split("-");
    if (dateArray.length != 3)
    {
        dateArray = date.split("/");
        if (dateArray.length != 3)
        {
            return null;
        }
        flag = false;
    }
    var newDate = new Date();
    if (flag)
    {
        // month从0开始
        newDate.setFullYear(dateArray[0], dateArray[1] - 1, dateArray[2]);
    }
    else
    {
        newDate.setFullYear(dateArray[2], dateArray[1] - 1, dateArray[0]);
    }
    newDate.setHours(0, 0, 0);
    return newDate;
};

//$(function (){
//	
//	var w= $("input[name='enterpriseEmployees.whetherGinseng']:checked").val();
//	if(w!=null && w!=undefined){
//		if(w==1){
//			$("#fundType").show();
//		}else{
//			$("#fundType").hide();
//		}	
//	}
//	$("input[name='enterpriseEmployees.whetherGinseng']").click(function(){
//		var w= $("input[name='enterpriseEmployees.whetherGinseng']:checked").val();
//		if(w!=null && w!=undefined){
//			if(w==1){
//				$("#fundType").show();
//			}else{
//				$("#fundType").hide();
//			}	
//		}
//	});
//});
function reset(){
	var form=document.getElementById("loginForm");
	 $(form).find(':input').each(  
		        function(){  
		            switch(this.type){  
		                case 'passsword':  
		                case 'select-multiple':  
		                case 'select-one':  
		                case 'text':  
		                case 'textarea':  
		                    $(this).val('');  
		                    break;  
//		                case 'checkbox':  
//		                case 'radio':  
//		                    this.checked = false;  
		            }  
		        }  
		    );  
	//clearForm(form);
}


