$(function() {  
	
        $("#flexigrid").flexigrid({  
        		
                url : 'viewSalaryWithBankPersonalNumberJson.action',  
                dataType:'json',  
                colModel : [
                			{display: '序', name : 'salaryId', width : 50, sortable : true, align: 'center',hide: true,toggle : false},
                			{display: '姓名', name : 'employeesName', width : 150, sortable : false, align: 'center'},
                			{display: '身份证', name : 'cardNumber', width : 200, sortable : true, align: 'center'},
                			{display: '银行名称', name : 'note', width : 200, sortable : true, align: 'center'},
                			{display: '卡 号', name : 'bankCardNumber', width : 200, sortable : true, align: 'center'},
                			{display: '工资金额', name : 'wage', width : 200, sortable : true, align: 'center',process:formatMoney}																
                			],
                 buttons : [
                            {displayname: '分类查看：' },
                   			{ name: '民生银行', displayname: '民生银行',bclass: 'bbit-grid',onpress: toolbarItem },
                            { name: '他行', displayname: '他行', bclass: 'bbit-grid', onpress: toolbarItem },
                            { name: '现金', displayname: '现金', bclass: 'bbit-grid', onpress: toolbarItem },
                   			{separator: true}
                   			],
                   
                sortname : "salaryId",  
                sortorder : "asc",  
                usepager : true,  
                title : '发放工资人员列表',  
                useRp : true,  
                checkbox : true,	   // 是否要多选框  
                rowId : 'salaryId',		// 多选框绑定行的id  
                errormsg:'连接失败,请重试',
                procmsg:'正在处理数据，请等待...', 
				usepager: true,       //是否分页
				showcheckbox: true,   //是否显示第一列的checkbox（用于全选）	
				useRp: true,          //是否使用分面
				rp: 10,
				rpOptions: [10, 15, 20, 30, 40, 100],    //可选择设定的每页结果数
				pagestat: '显示记录从{from}到{to}，总数 {total} 条',
				nomsg:'不存在记录!',
				minColToggle: 1,	 //允许显示的最小列数
				showTableToggleBtn:true,		
				autoload:true ,          //不允许自动加载					
				resizable: true,
				
				hideOnSubmit: true, //是否在回调时显示遮盖
				blockOpacity: 0.5,//透明度设置
				showcheckbox: true,//是否显示第一列的checkbox（用于全选）
				//	gridClass: "bbit-grid",//样式
		        rowhandler: contextmenu,//是否启用行的扩展事情功能,在生成行时绑定事件，如双击，右键菜单等
				rowbinddata: true,//配合上一个操作，如在双击事件中获取该行的数据
				onrowchecked: callme,//在每一行的的checkbox选中状态发生变化时触发某个事件
				checkbox: true,
				method:'post',
                width : 1173,  
                height : 400 
            }); 
        function callme(){
    		alert("aaaaaaaaa");
    	}
    	function contextmenu(row) {
            var menu = { width: 150, items: [
    			             { text: "查看", icon: "images/icons/search.png", alias: "contextmenu-view", action: contextMenuItem },
    			             { text: "编辑", icon: "images/icons/modify.png", alias: "contextmenu-modify", action: contextMenuItem },
    			             { text: "删除", icon: "images/icons/close.png", alias: "contextmenu-delete", action: contextMenuItem }
    			        	]
        				};
    		function contextMenuItem(target) {
                var id = $(target).attr("id").substr(3);
                var cmd = this.data.alias;//别称
                var ch = $.browser.msie ? target.ch : target.getAttribute("ch");
                var cell = ch.split("_FG$SP_");
                if (cmd == "contextmenu-view") {
                    //alert("查看，产品编号=" + id);
                    log.info( "查看，产品编号=" + id )
                }
                else if (cmd == "contextmenu-modify") {
                    //alert("编辑，产品编号=" + id);
                    log.info( "编辑，产品编号=" + id )
                    //openDialogModify();
                }
                else if (cmd == "contextmenu-delete") {
                    var name = cell[1];
                    if (confirm("你确认要删除商品 [" + name + "] 吗？")) {
                        //alert("删除，产品编号=" + id);
                        $.ajax({
    						type: "POST",
    						//url: "flexGridServlet.do?action=delete",
    						data: "id="+id,
    						dataType:"text",
    						success: function(msg){
    							if(msg=="success"){
    								$("#flexTable").flexReload();
    							}else{
    								alert("有错误发生,msg="+msg);
    							}
    						},
    						error: function(msg){
    							alert("msg="+msg);
    						}
    					});
                    }
                }
                else {
                    $("#flexTable").flexReload();
                }
            }
            $(row).contextmenu(menu);
        }
    	
    	function toolbarItem(com, grid) {
           if (com=='delete'){
    			deleteMe();
    		}else if (com=='add'){
    			openDialogAdd();
    		}else if (com=='modify'){
    			openDialogModify();
    		}
        }
        //操作函数
    	function formatMoney(value, pid) {
             return "￥" + parseFloat(value).toFixed(2);
        }
        function openDialogAdd(){
    		$('#dialogAdd').dialog({
    			closed:false
    		});
    	}
        function openDialogModify(){
        	if($(".trSelected",grid).length==1){
    			$('#dialogModify').dialog({
    				closed:false
    			});
    		}else if($(".trSelected",grid).length>1){
    			alert("请选择一个修改,不能同时修改多个记录!");
    		}else if($(".trSelected",grid).length==0){
    			alert("请选择一个您要修改的记录!")
    		}
    	}
        
        function deleteMe(){
    		if($('.trSelected',grid).length==0){
    			alert("请选择要删除的数据!");
    		}else{
    			if(confirm('是否删除这 ' + $('.trSelected',grid).length + ' 条记录吗?')){
    				 var  ids ="";
    			      $('.trSelected td:nth-child(2)',grid).each(function(){
    			      	ids+=","+$(this).text();
    			      });
    			      ids=ids.substring(1);
    			      //alert("id="+ids);
    			      $.ajax({
    						type: "POST",
    						//url: "flexGridServlet.do?action=delete",
    						data: "id="+ids,
    						dataType:"text",
    						success: function(msg){
    							if(msg=="success"){
    								$("#flexTable").flexReload();
    							}else{
    								alert("有错误发生,msg="+msg);
    							}
    						},
    						error: function(msg){
    							alert("msg="+msg);
    						}
    					});
    		      	}
    		   }
    	}
        //dialog start （add）
        $('#dialogAdd').dialog({
        		href:'addmodify.jsp',
        		onClose:function(){
        			$('#dialogAdd #form1')[0].reset();
        			//document.forms[0].reset();
    				//$('#dialogAdd #form1 :input').val("");//清空表单值
    				//$('#dialogAdd #form1 #language')[0].selectedIndex = 0;//恢复select的默认值
        		},
       			closed:true,
       			width:320,
       			height:400,
       			showType:'fade',//'slide','fade','show'
       			showSpeed:400,
    			iconCls: 'icon-save',
    			buttons:{
    				'\u65B0\u589E':function(){//新增
    					var params=$("#dialogAdd #form1").serialize();
    					$.ajax({
    							type: "POST",
    							//url: "flexGridServlet.do?action=add",
    							data: encodeURI(params),
    							dataType:"text",
    							success: function(msg){
    								if(msg=="success"){
    									$('#flexTable').flexReload();
    									$('#dialogAdd').dialog({closed:true});
    								}else{
    									$('#dialogAdd').dialog({closed:true});
    									alert("有错误发生,msg="+msg);
    								}
    							},
    							error: function(msg){
    								alert("msg="+msg);
    							}
    					});
    				},
    				'\u53D6\u6D88':function(){//取消
    					$('#dialogAdd').dialog({closed:true});
    				}
    			}
    	})
    	
    	 //dialog start （Modify）
        $('#dialogModify').dialog({
        		href:'addmodify.jsp',
        		onOpen:function(){
        			var id=$('.trSelected td:nth-child(2)',grid).text();
        			log.info('id='+id);
        			//ps：这样获取是不妥的，为了简单先不改。
        			$('#dialogModify #form1 #name').val($('.trSelected td:nth-child(3)',grid).text());
        			$('#dialogModify #form1 #address').val($('.trSelected td:nth-child(4)',grid).text());
        			$('#dialogModify #form1 #salary').val($('.trSelected td:nth-child(5)',grid).text().substring(1));
        			$('#dialogModify #form1 #date').val($('.trSelected td:nth-child(6)',grid).text());
        			$('#dialogModify #form1 #language').val($('.trSelected td:nth-child(7)',grid).text());
        		},
        		onClose:function(){
        			$('#dialogModify #form1')[0].reset();
    				//$('#dialogModify #form1 :input').val("");//清空表单值
    				//$('#dialogModify #form1 #language')[0].selectedIndex = 0;//恢复select的默认值
        		},
       			closed:true,
       			width:320,
       			height:400,
       			showType:'fade',//'slide','fade','show'
       			showSpeed:400,
    			iconCls: 'icon-modify',
    			buttons:{
    				'\u4FEE\u6539':function(){//修改
    					var params=$("#dialogModify #form1").serialize();//此处必须加上#dialogModify
    					var id=$('.trSelected td:nth-child(2)',grid).text();
    					$.ajax({
    							type: "POST",
    							//url: "flexGridServlet.do?action=modify&id="+id,
    							data: encodeURI(params),
    							dataType:"text",
    							success: function(msg){
    								if(msg=="success"){
    									$("#flexTable").flexReload();
    									$('#dialogModify').dialog({closed:true});
    								}else{
    									$('#dialogModify').dialog({closed:true});
    									alert("有错误发生,msg="+msg);
    								}
    							},
    							error: function(msg){
    								alert("msg="+msg);
    							}
    					});
    				},
    				'\u53D6\u6D88':function(){//取消
    					$('#dialogModify').dialog({closed:true});
    				}
    			}
    	});
    	
    	//context menu start
    	 $('span.context').contextMenu('contextMenu', {
    	      bindings: {
    				        'add': function(t) {
    				          openDialogAdd();
    				        },
    				        'modify': function(t) {
    				        	openDialogModify();
    				          //alert('Trigger was '+t.id+'\nAction was Save');
    				        },
    				        'delete': function(t) {
    				          deleteMe();
    				        }
    				    },
    			/*
    	      menuStyle:{
    				      border : "2px solid green"
    				    },
    	      itemStyle:{
    				        fontFamily : 'verdana',
    				        backgroundColor : '#666',
    				        color: 'white',
    				        border: 'none',
    				        padding: '1px'
    				    },
    	
    	      itemHoverStyle:{
    					        color: '#fff',
    					        backgroundColor: '#0f0',
    					        border: 'none'
    					      },
    					      */
    	      shadow:true,
    	      eventPosX:0,
    	      eventPosY:0
    	      //onContextMenu:function(e){if ($(e.target).attr('id') == 'dontShow') return false;else return true; },
    	      //onShowMenu:function(e, menu) {if ($(e.target).attr('id') == 'showOne') { $('#item_2, #item_3', menu).remove();} return menu;}
        });
        
        //联动select option start
         $("#province").FillOptions(
         		//"flexGridServlet.do?action=getProvince",
         		{
          		datatype:"json",
          		textfield:"province",
          		valuefiled:"provinceID",
          		//selectedindex:0,//填充并选中第1项
          		keepold:true//填充并且保留原有项
         		}
         );
    	 $("#province").AddOption("请选择省份：","-1",true,0);//最上端插入一个文本为“请选择省份“，值为”-1“的列表项，并且是选中状态
         $("#city").AddOption("请选择城市：","-1",true,0);
          $("#province").CascadingSelect(
               $("#city"),//需要联动的下拉列表框，必须
               //"flexGridServlet.do?action=getCity",
               {datatype:"json",textfield:"city",valuefiled:"cityID",parameter:"p"},//通过设置parameter:”p”这个参数会生成一个"handler1.ashx?p=xxx”这样的地址来做ajax请求
               function(){//完成联动后执行
               		//log.info("测试");
               }
          );
    	
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









