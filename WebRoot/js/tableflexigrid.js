$(function() {  
	
        $("#flexigrid").flexigrid({  
        		
                url : 'viewSalaryWithBankPersonalNumberJson.action',  
                dataType:'json',  
                colModel:[{  
                        display : '序',  
                        name : 'salaryId',  
                        width : '50',// 得加上 要不IE报错  
                        sortable : true,  
                        align : 'center'  
                    }, {  
                        display : '姓名',  
                        name : 'employeesName',  
                        width : '150',  
                        sortable : true,  
                        align : 'center'  
                    }, {  
                        display : '身份证',  
                        name : 'cardNumber',  
                        width : 200,  
                        sortable : true,  
                        align : 'center'  
                    }, {  
                        display : '银行名称',  
                        name : 'note',  
                        width : 200,  
                        sortable : true,  
                        align : 'center'  
                    }, {  
                        display : '卡号',  
                        name : 'bankCardNumber',  
                        width : 200,  
                        sortable : true,  
                        align : 'center'  
                    }, {  
                        display : '工资金额（元）',  
                        name : 'wage',  
                        width : 200,  
                        sortable : true,  
                        align : 'center'  
                    }],  
                    
                buttons : [{  
                        name : '民生银行',  
                        bclass : 'viewMinshengBank',  
                        onpress : action  
                    }, {  
                        // 设置分割线  
                        separator : true  
                    }, {  
                        name : '他行',  
                        bclass : 'viewOtherBank',  
                        onpress : action  
                    }, {  
                        separator : true  
                    }, {  
                        name : '现金',  
                        bclass : 'viewCashIssue',  
                        onpress : action  
                    }, {  
                        separator : true  
                    }],  
                   
                sortname : "id",  
                sortorder : "asc",  
                usepager : true,  
                title : '发放工资人员列表',  
                useRp : true,  
                checkbox : true,// 是否要多选框  
                rowId : 'id',// 多选框绑定行的id  
            	pagestat: '显示记录从{from}到{to}，总数 {total} 条',
                rpOptions: [10,20],    //可选择设定的每页结果数
                errormsg:'连接失败,请重试',
				usepager: true,       //是否分页
				showcheckbox: true,  //是否显示第一列的checkbox（用于全选）	
				pagestat:'显示{from}至{to} 条  共{total}条',
				useRp: true,          //是否使用分面
				rp: 5,
				rpOptions: [5,10],    //可选择设定的每页结果数
				nomsg:'不存在记录!',
				minColToggle: 1, //允许显示的最小列数
				showTableToggleBtn: true,		
				autoload:true ,          //不允许自动加载					
				resizable: true,
				procmsg:'正在处理数据，请等待...',        
				checkbox: true,
				blockOpacity:0.1,
				method:'post',
                width : 1173,  
                height : 300 
            }); 
        
        var actions="";  
        function action(com, grid) {  
            switch (com) {  
                case '添加' :  
                    $("#savegoods input[type='text']").each(function() {  
                            $(this).val("");  
                        });  
                    // $('#savegoods input[name="id"]').removeAttr("<span style="font-family: 'Courier New', monospace;">disabled<span style="font-family: Verdana, Arial, Helvetica, sans-serif;">");</span></span>  
                     $('#savegoods').attr("action","add.action");  
                    actions="add.action";  
                    $("#goods").jqmShow();  
                    break;  
                case '他行' :  
                    selected_count = $('.trSelected', grid).length;  
                    if (selected_count == 0) {  
                        alert('请选择一条记录!');  
                        return;  
                    }  
                    if (selected_count > 1) {  
                        alert('抱歉只能同时修改一条记录!');  
                        return;  
                    }  
                    data = new Array();  
                    $('.trSelected td', grid).each(function(i) {  
                            data[i] = $(this).children('div').text();  
                        });  
                    $('#savegoods input[name="id"]').val(data[0]).
//                    attr("<span style="font-family: 'Courier New', monospace;">disabled<span style="'font-family: Verdana, Arial, Helvetica, sans-serif;'>,true);"</span>")  
                    $('#savegoods input[name="name"]').val(data[1]);  
                    $('#savegoods input[name="stand"]').val(data[2]);  
                    $('#savegoods input[name="money"]').val(data[3]);  
                    $('#savegoods input[name="leavings"]').val(data[4]);  
                    $('#savegoods input[name="orders"]').val(data[5]);  
                      
                   actions="modify.action";  
  
                    $("#goods").jqmShow();  
                    break;  
                case '删除' :  
                    selected_count = $('.trSelected', grid).length;  
                    if (selected_count == 0) {  
                        alert('请选择一条记录!');  
                        return;  
                    }  
                    names = '';  
                    $('.trSelected td:nth-child(3) div', grid).each(function(i) {  
                            if (i)  
                                names += ',';  
                            names += $(this).text();  
                        });  
                    ids = '';  
                    $('.trSelected td:nth-child(2) div', grid).each(function(i) {  
                            if (i)  
                                ids += ',';  
                            ids += $(this).text();  
                        })  
                    if (confirm("确定删除发放人员[" + names + "]?")) {  
                        delUser(ids);  
                    }  
                    break;  
            }  
        }  
        $("#goods").jqm({  
            // trigger : 'a.showDialog',// 触发  
            // ajax: '@href',//ajax读取方式  
            // ajaxText:'',//提示语言  
            modal : true,// 限制输入（鼠标点击，按键）的对话  
            overlay : 60 // 遮罩程度%  
              // target : t,// 提示  
              // onHide : function(h) {  
              // // // t.html('Please Wait...'); // Clear Content HTML on Hide.  
              // h.o.remove(); // remove overlay  
              // // h.w.fadeOut(888); // hide window  
              // }  
          }).jqmAddClose('.close')// 添加触发关闭的selector  
          .jqDrag('.drag');// 添加拖拽的selector  
          
        function delUser(ids) {  
            $.ajax({  
                    url : 'delete.action',  
                    data : {  
                        ids : ids  
                    },  
                    type : 'POST',  
                    dataType : 'json',  
                    success : function() {  
                        $('#flex').flexReload();//表格重载  
                    }  
                });  
        }  
        $("#submit").click(function(){  
             $.ajax({  
                    url : actions,  
                    data : $("#savegoods").serialize(),  
                    type : 'POST',  
                    dataType : 'json',  
                    success : function(data) {  
                        $("#goods").jqmHide();  
                        $('#flex').flexReload();  
                    }  
                });  
        })  
    });

	$(function(){
		$("#flex2").flexigrid({
			url: '',
			dataType: 'json',
			width: 'auto',
			height: '155',		
			colModel : [
				{display: '编号', name: 'id', width: 50, sortable: true,align: 'center', hide:false, toggle:false},
				{display: '工作名称', name : 'job_name', width : 250,align: 'center',sortable: true,hide:false},
				{display: '工作地址', name : 'work_address', width : 100, align: 'center',sortable: true},
				{display: '工资', name : 'salary', width : 60, align: 'right'},
				{display: '日期', name : 'date', width : 120, align: 'center'},
				{display: '语言', name : 'language', width : 80, align: 'center'}																
				],
			buttons:[
					{ displayname: '新增', name: '新增', bclass: 'add', onpress: toolbarItem },
					{ displayname: '修改', name: '修改',bclass: 'modify', onpress: toolbarItem },
	            	{ displayname: '删除', name: '删除', bclass: 'delete', onpress: toolbarItem },
					{separator: true}
					],	
			searchitems : [
				{display: '编号', name : 'id', isdefault: true},
				{display: '工作名称', name : 'job_name'},
				{display: '工作地址', name : 'work_address'},
				{display: '语言', name : 'language'}
				],		
				errormsg:'连接失败,请重试',
				sortname: "id",
				sortorder: "desc",
				usepager: true,       //是否分页
				showcheckbox: true,  //是否显示第一列的checkbox（用于全选）	
				title: '信息发布管理Flexigrid',
				pagestat:'显示{from}至{to} 条  共{total}条',
				useRp: true,          //是否使用分面
				rp: 5,
				rpOptions: [5,10],    //可选择设定的每页结果数
				nomsg:'不存在记录!',
				minColToggle: 1, //允许显示的最小列数
				showTableToggleBtn: true,		
				autoload:true ,          //不允许自动加载					
				resizable: true,
				procmsg:'正在处理数据，请等待...',        
				checkbox: true	
		});
		

//		function toolbarItem(com, grid){
//			var  ids ="";
//			if(com=='删除'){
//				${"hidden"}.value="delete";
//				if($('.trSelected').length == 0 ){
//						alert("请选择一个以上的记录进行删除");
//				}else if(confirm('是否删除这 ' + $('.trSelected',grid).length + ' 条记录吗?')){				
//				      $('.trSelected td:nth-child(2)',grid).each(function(){
//				      	ids+=","+$(this).text();
//				      });
//				     ids=ids.substring(1);
//					$(window.location.href('delAction.action?hidden='+${"hidden"}.value+'&id='+ids));
//				}
//			}else if(com=='新增'){
//				window.open('addJob.jsp','信息添加','height=400,width=300, top=100,left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
//			}else if(com == '修改'){
//				${"hidden"}.value="modify";
//				if($('.trSelected').length ==1){
//					window.open('JobAction.action?hidden='+${"hidden"}.value+'&id='+$('.trSelected',grid).find("td").eq(0).text(),'信息修改','height=400,width=300, top=100,left=400, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
//				}else if($('.trSelected').length == 0 ){
//						alert("请选择一个您要修该得信息");
//				}else if($('.trSelected').length >1){
//						alert("请选择一个修改，不能同时选择多个");
//				}			
//			}
//		}
				
		//刷新
		function refresh(){
			$("#flex1").flexReload({});
		}	
		
	});









