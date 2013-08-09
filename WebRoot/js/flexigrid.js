

/*
 * Flexigrid for jQuery - New Wave Grid
 *
 * Copyright (c) 2008 Paulo P. Marinas (webplicity.net/flexigrid)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *                                                                                                               pu
 * $Date: 2008-04-01 00:09:43 +0800 (Tue, 01 Apr 2008) $
 */
 
var myArray = new Array();
(function($) {

    $.addFlex = function(t, p)
    {
        if (t.grid) return false; //return if already exist
         
        // apply default properties
        p = $.extend({
        	 height: 'auto', //default height
			 width: 'auto', //auto width
			 striped: true, //apply odd even stripes
			 novstripe: false,
			 minwidth: 30, //min width of columns 
			 minheight: 80, //min height of columns
			 resizable: false, //resizable table
			 url: false, //ajax url
			 method: 'POST', // data sending method
			 dataType: 'xml', // type of data loaded
			 errormsg: errormsg,
			 validateError:validateError,
			 usepager: true, //
			 nowrap: true, //
			 page: 1, //current page
			 total: 1, //total pages
			 useRp: true, //use the results per page select box
			 rp: 15, // results per page
			 alis:'1',
			 rpOptions: [5,10],
			 title: false,
			 pagestat: pagestat,
			 procmsg: procmsg,
			 query: '',
			 qtype: '',
			 nomsg: nomsg,
             no_record_current_page:no_record_current_page,
			 minColToggle: 1, //minimum allowed column to be hidden
			 showToggleBtn: false, //show or hide column toggle popup
			 hideOnSubmit: true,
			 autoload: true,
			 blockOpacity: 0.5,
			 onToggleCol: false,
			 onChangeSort: false,
			 onSuccess: false,
			 onSubmit: false,
            editCell:true,
            backList:'annualList',
            moduleName:"",
            saveAction:null,
            saveType:'json',
            oclick:false,
            gridList:'gridList',
            dateFormat :'yy-mm-dd',
            search:true,
            searchList:'searchList',
            searchitems:false,
            buttons :  false,
            showTableToggleBtn: true,
            oclick:false,
            dbclick:false,
            search:false,
            onSelect: true,
            selectType:'checkbox',
            checkField:'id',
            colsable:false,
            myArray:new Array()
        }, p);


        $(t)
                .show()//show if hidden
                .attr({cellPadding: 0, cellSpacing: 0, border: 0})//remove padding and spacing
                .removeAttr('width') //remove width properties
                ;
        var edit = false,statcId;
        var chk = "";
        var isCheckAll = false;
        var btnTop = 0;
     
        myArray = p.myArray; 
        //create grid class
        var g = {
        		hset : {},
    			rePosDrag: function () {
        		right = g.vDiv.offsetLeft+$(g.vDiv).width();
    			var cdleft = 0 - this.hDiv.scrollLeft;
    			if (this.hDiv.scrollLeft>0) cdleft -= Math.floor(p.cgwidth/2);
    			$(g.cDrag).css({top:g.hDiv.offsetTop+1});
    			var cdpad = this.cdpad;
    			
    			//$('div',g.cDrag).hide();
    			var i = 1;
    			$('thead tr:first th:visible',this.hDiv).each
    				(
    			 	function ()
    					{
    					var n = $('thead tr:first th:visible',g.hDiv).index(this);

    					var cdpos = parseInt($('div',this).width());
    					var ppos = cdpos;
    					if (cdleft==0) 
    							cdleft -= Math.floor(p.cgwidth/2); 

    					cdpos = cdpos + cdleft + cdpad;        				
         				if(cdpos<right && cdpos>0){
         					$('div:eq('+n+')',g.cDrag).css({'left':cdpos+'px'}).show();
         				}
    					cdleft = cdpos;
    					i++;
    					}
    				);
    				
    			},

            fixHeight: function (newH) {


                if (!newH) newH = $(g.bDiv).height();
                var hdHeight = $(this.hDiv).height();
                $('div', this.cDrag).each(
                        function ()
                        {
 //                           $(this).height(newH + hdHeight);
                            $(this).height(newH + 10);
                        }
                        );
                $(g.block).css({height:newH,marginBottom:(newH * -1)});

                var hrH =  newH;
                if (p.height != 'auto' && p.resizable) //hrH = g.vDiv.offsetTop;
                $(g.rDiv).css({height: hrH});
             

            },
            dragStart: function (dragtype, e, obj) { //default drag function start
            	$(g.cDrag).css('top',btnTop);    
                if (dragtype == 'colresize') //column resize
                {
                    $(g.nDiv).hide();
                    $(g.nBtn).hide();
                    
                    var n = $('div', this.cDrag).index(obj);
                    var ow = $('th:visible div:eq(' + n + ')', this.hDiv).width();
                    $(obj).addClass('dragging').siblings().hide();
                    $(obj).prev().addClass('dragging').show();

                    this.colresize = {startX: e.pageX, ol: parseInt(obj.style.left), ow: ow, n : n };
                    $('body').css('cursor', 'col-resize');
                }
                else if (dragtype == 'vresize') //table resize
                {
                    var hgo = false;
                    $('body').css('cursor', 'row-resize');
                    if (obj)
                    {
                        hgo = true;
                        $('body').css('cursor', 'col-resize');
                    }
                    this.vresize = {h: p.height, sy: e.pageY, w: p.width, sx: e.pageX, hgo: hgo};

                }
                else if (dragtype == 'colMove') //column header drag
                    {
                        $(g.nDiv).hide();
                        $(g.nBtn).hide();
                        this.hset = $(this.hDiv).offset();
                        this.hset.right = this.hset.left + $('table', this.hDiv).width();
                        this.hset.bottom = this.hset.top + $('table', this.hDiv).height();
                        this.dcol = obj;
                        this.dcoln = $('th', this.hDiv).index(obj);

                        this.colCopy = document.createElement("div");
                        this.colCopy.className = "colCopy";
                        this.colCopy.innerHTML = obj.innerHTML;
                        if ($.browser.msie)
                        {
                            this.colCopy.className = "colCopy ie";
                        }


                        $(this.colCopy).css({position:'absolute',float:'left',display:'none', textAlign: obj.align});
                        $('body').append(this.colCopy);
                        $(this.cDrag).hide();

                    }

                $('body').noSelect();

            },
            dragMove: function (e) {
              	$(g.cDrag).css('top',btnTop);    
                if (this.colresize) //column resize
                {
                    var n = this.colresize.n;
                    var diff = e.pageX - this.colresize.startX;
                    var nleft = this.colresize.ol + diff;
                    var nw = this.colresize.ow + diff;
                    if (nw > p.minwidth)
                    {
                        $('div:eq(' + n + ')', this.cDrag).css('left', nleft);
                        this.colresize.nw = nw;
                    }
                }
                else if (this.vresize) //table resize
                {
                    var v = this.vresize;
                    var y = e.pageY;
                    var diff = y - v.sy;

                    if (!p.defwidth) p.defwidth = p.width;

                    if (p.width != 'auto' && !p.nohresize && v.hgo)
                    {
                        var x = e.pageX;
                        var xdiff = x - v.sx;
                        var newW = v.w + xdiff;
                        if (newW > p.defwidth)
                        {
                            this.gDiv.style.width = newW + 'px';
                            p.width = newW;
                        }
                    }

                    var newH = v.h + diff;
                    if ((newH > p.minheight || p.height < p.minheight) && !v.hgo)
                    {
                        this.bDiv.style.height = newH + 'px';
                        p.height = newH;
                        this.fixHeight(newH);
                    }
                    v = null;
                }
                else if (this.colCopy) {
                        $(this.dcol).addClass('thMove').removeClass('thOver');
                        if (e.pageX > this.hset.right || e.pageX < this.hset.left || e.pageY > this.hset.bottom || e.pageY < this.hset.top)
                        {
                            //this.dragEnd();
                            $('body').css('cursor', 'move');
                        }
                        else
                            $('body').css('cursor', 'pointer');
                        $(this.colCopy).css({top:e.pageY + 10,left:e.pageX + 20, display: 'block'});
                    }

            },
            dragEnd: function () {
               
                if (this.colresize)
                {
                    var n = this.colresize.n;
                    var nw = this.colresize.nw;

                    $('th:visible div:eq(' + n + ')', this.hDiv).css('width', nw);
                    $('tr', this.bDiv).each(
                            function ()
                            {
                                $('td:visible div:eq(' + n + ')', this).css('width', nw);
                            }
                            );
                    this.hDiv.scrollLeft = this.bDiv.scrollLeft;


                    $('div:eq(' + n + ')', this.cDrag).siblings().show();
                    $('.dragging', this.cDrag).removeClass('dragging');
                    this.rePosDrag();
                    this.colresize = false;
                	$(g.cDrag).css('top',btnTop); 
                }
                else if (this.vresize)
                {
                    this.vresize = false;
                }
                else if (this.colCopy)
                    {
                        $(this.colCopy).remove();
                        if (this.dcolt != null)
                        {


                            if (this.dcoln > this.dcolt)

                                $('th:eq(' + this.dcolt + ')', this.hDiv).before(this.dcol);
                            else
                                $('th:eq(' + this.dcolt + ')', this.hDiv).after(this.dcol);


                            this.switchCol(this.dcoln, this.dcolt);
                            $(this.cdropleft).remove();
                            $(this.cdropright).remove();
                            this.rePosDrag();


                        }

                        this.dcol = null;
                        this.hset = null;
                        this.dcoln = null;
                        this.dcolt = null;
                        this.colCopy = null;

                        $('.thMove', this.hDiv).removeClass('thMove');
                        $(this.cDrag).show();
                    }
                $('body').css('cursor', 'default');
                $('body').noSelect(false);
            },
            toggleCol: function(cid, visible) {
                var ncol = $("th[axis='col" + cid + "']", this.hDiv)[0];
                var n = $('thead th', g.hDiv).index(ncol);
                var cb = $('input[value=' + cid + ']', g.nDiv)[0];


                if (visible == null)
                {
                    visible = ncol.hide;
                }


                if ($('input:checked', g.nDiv).length < p.minColToggle && !visible) return false;

                if (visible)
                {
                    ncol.hide = false;
                    $(ncol).show();
                    cb.checked = true;
                }
                else
                {
                    ncol.hide = true;
                    $(ncol).hide();
                    cb.checked = false;
                }

                $('tbody tr', t).each
                        (
                                function ()
                                {
                                    if (visible)
                                        $('td:eq(' + n + ')', this).show();
                                    else
                                        $('td:eq(' + n + ')', this).hide();
                                }
                                );

                this.rePosDrag();

                if (p.onToggleCol) p.onToggleCol(cid, visible);

                return visible;
            },
            switchCol: function(cdrag, cdrop) { //switch columns

                $('tbody tr', t).each
                        (
                                function ()
                                {
                                    if (cdrag > cdrop)
                                        $('td:eq(' + cdrop + ')', this).before($('td:eq(' + cdrag + ')', this));
                                    else
                                        $('td:eq(' + cdrop + ')', this).after($('td:eq(' + cdrag + ')', this));
                                }
                                );

                //switch order in nDiv
                if (cdrag > cdrop)
                    $('tr:eq(' + cdrop + ')', this.nDiv).before($('tr:eq(' + cdrag + ')', this.nDiv));
                else
                    $('tr:eq(' + cdrop + ')', this.nDiv).after($('tr:eq(' + cdrag + ')', this.nDiv));

                if ($.browser.msie && $.browser.version < 7.0) $('tr:eq(' + cdrop + ') input', this.nDiv)[0].checked = true;

                this.hDiv.scrollLeft = this.bDiv.scrollLeft;
            },
            scroll: function() {
                this.hDiv.scrollLeft = this.bDiv.scrollLeft;
                this.rePosDrag();
            },
            isCheck:function(os) {
                var rs = false;
                if (chk == "") {
                    return false;
                }
                var col = chk.split(',');
                for (var i in col) {

                    if ($.trim(col[i]) == $.trim(os)) {
                        rs = true;
                    }
                }                             
                return rs;
            },
            isCheckY:function(os,idx){
            	var rs = false;
            	
             	for(var i = 0;i<p.myArray.length;i++){
             		var j = 0;
             		var url = p.myArray[i][p.myArray[i].length-1];
        			if(p.myArray[i][j] == idx&&p.myArray[i][j+1]== os && p.url.indexOf(url)!=-1){
        				rs = true;
        			}                   		
             	}
             	 return rs;
            },
            lookAhead:function(match,format) {
            	var iFormat = 0;
            	var n = 0;
            	for(;iFormat<format.lenght;){
            		if(format.charAt(iFormat)==match){
            			n++;
            		}
            	}
            	var matches = (iFormat + 1 < format.length && format.charAt(iFormat + 1) == match);
            	if (matches)
            		iFormat++;
            	return matches;
            },
            formatNumber : function(match, value, len,format) {
            	var num = '' + value;
            	if (true)
            		while (num.length < len)
            			num = '0' + num;
            	return num;
            },
            dateFormat:function(format,date){
            	var formatDate = date.substring(0,date.indexOf('T')).split('-');
            	var time = date.substring(date.indexOf('T')+1);
            	date = new Date(parseInt(formatDate[0],10),parseInt(formatDate[1],10)-1,parseInt(formatDate[2],10));
            	var output = '';
            	if (date){
            		for (var iFormat = 0; iFormat < 11;) {    
            				switch (format.charAt(iFormat)) {
            					case 'd':
            						output += g.formatNumber('d', formatDate[2], 2,format);
            						iFormat=iFormat+2;
            						break;
            					case 'D':
            						output += g.formatNumber('d', formatDate[2], 2,format);
            						iFormat=iFormat+2;
            						break;
             					case 'm':
            						output += g.formatNumber('m', date.getMonth()+1, 2,format);
            						iFormat=iFormat+2;
            						break;
            					case 'M':
            						output += g.formatNumber('m', date.getMonth()+1, 2,format);
            						iFormat=iFormat+2;           						
            						break;
            					case 'y':
            						output += date.getFullYear().toString();
            						iFormat=iFormat+4;
            						break;
            					case '@':
            						output += date.getTime();
            						break;
            					case "'":
            						if (g.lookAhead("'"))
            							output += "'";
            						else
            							literal = true;
            						break;
            					default:
            						output += format.charAt(iFormat);
            						iFormat=iFormat+1;
            				}
            		}
            	}
            	output = output+" "+time;
            	return output.substring(0, format.length+1);
            }, 
            validateField:function(data){
            	var rs = true;
            	var fieldMessage="";
            	if(data!=undefined&&data!=null){
            	 $.each(data,function(i,result){
            		 var fieldName = result.split('|')[0];
            		 fieldMessage += result.split('|')[1]+"\n";
            		 var field = $('input[name="'+fieldName+'"]');   
            		 var obj =  $('input[id="'+fieldName+'"]');  
            		 if( $(field).length == 0){
            			 $(obj).addClass('searchErrorLabelCss');
            		 }else
            		 if($(field).length > 1){
            			 $('input[name="'+fieldName+'"]:eq(0)').addClass('searchErrorLabelCss'); 
            			 $('input[name="'+fieldName+'"]:eq(1)').addClass('searchErrorLabelCss');
            		 }else{
            			 $(field).addClass('searchErrorLabelCss');
            		 }	
            		 
            	 }); 
            	 if(fieldMessage!=""){
            		 rs = false;
            		 alert(fieldMessage);
            	 }
            	}
            	return rs;
            },
            validateJsonUserNotLogin:function(data){
                if(data!=undefined && data!=null && data.isJsonUserLogin !=undefined
                        && !data.isJsonUserLogin){
                    toUrl($("#bbpmContext").val()+"/user/userLogout.action");
                }
            },
            validateJsonSessionTimeOut:function(data){
                if(data!=undefined && data!=null && data.isJsonSessionExpired !=undefined
                        &&  data.isJsonSessionExpired){
                    toUrl($("#bbpmContext").val()+"/user/userLogout.action");
                }
            },
            addEmptyData:function(){
            	var tbody = document.createElement('tbody'); 
            	var tr = document.createElement('tr');
            	 $('thead tr:first th', g.hDiv).each(function(){
            		 var td = document.createElement('td');
            		  $(tr).append($(td).css("visibility","hidden"));
     
            	 });
            	 $(tr).attr("id","emptyData");
            	$(tbody).append(tr);
       		  	tr =  null;
       		  	$(t).append(tbody);
       		  	this.addCellProp();
       		  	$(t).css("border-bottom","none");
       		  	
       		  	
            },
            addData: function (data) { //parse data
                g.validateJsonUserNotLogin(data);
                g.validateJsonSessionTimeOut(data);
                $('.pReload', this.pDiv).removeClass('loading');
                this.loading = false;
                this.rePosDrag();

                if(g.validateField(data['fieldErrorList'])==false){
                	data = null;
                }
                if (!data)
                {
                    $('.pPageStat', this.pDiv).html(p.validateError);
                    return false;
                }
                
                if (p.dataType == 'xml')
                    p.total = +$('rows total', data).text();
                else
                //p.total = data.total;
                    p.total = data['baseGrid']['total'];
                if(p.total == null){
                    toUrl($("#bbpmContext").val()+"/user/userLogout.action");
                }
                if (p.total == 0)
                {
                    $('tr', t).unbind();
                    $(t).empty();
                    p.pages = 1;
                    p.page = 1;
                    this.buildpager();
                    $('.pPageStat', this.pDiv).html(p.nomsg);
                    g.addEmptyData(); 
                    return false;
                }

                p.pages = Math.ceil(p.total / p.rp);

                if (p.dataType == 'xml')
                    p.page = +$('rows page', data).text();
                else
                //p.page = data.page;
                    p.page = data['baseGrid']['page'];
                this.buildpager();
                $('#all').attr('checked', false);     
                //build new body
                var tbody = document.createElement('tbody'); 
                if (p.dataType == 'json')
                {
                    if (p.search) {
                        p.searchList = data[p.searchList];
                        g.addSearch();
                    }

                    $.each
                            (
                                    data['baseGrid'][p.gridList],
                                    function(i, row)
                                    {
                                    	var newRow;
                                    	var oldRow;
                                    	if(row.length!=undefined &&row.length>1){
                                    		newRow =row[p.index];
                                    	}else{
                                    		newRow = row;
                                    	}
                                    	oldRow = row;
                                        var tr = document.createElement('tr');
                                        if (i % 2 && p.striped) tr.className = 'erow';

                                       // if (row.id) tr.id = 'row' + row.id; |modify|
                                        tr.id = 'row' + newRow[p.checkField];
                                        var inx = 0;
                                        
                                        //add cell
                                        $('thead tr:first th', g.hDiv).each
                                                (
                                                        function ()
                                                        {                                                       
                                                        	if(oldRow.length!=undefined && oldRow.length>1){                                          
                                                        		var html = $('th:eq('+inx+')',$(this).parent()); 
                                                        		var ix = parseInt($('th:eq('+inx+')>div',$(this).parent()).attr("ix"),10);
                                                        		if(isNaN(ix)){
                                                        			ix = 0;
                                                        		}
                                                        		row =oldRow[ix];
                                                        	}
                                                            var td = document.createElement('td');
                                                            var idx = $(this).attr('abbr'); 
                                                            var htm = $(this).innerHTML;
                                                            $(td).attr('abbr',idx);
                                                            var check = "";
                                                            
                                                            td.align = this.align;
                                                            if (p.onSelect && idx == p.checkField) {                                  	
                                                                if (g.isCheckY(row.id,idx)) {
                                                                	isCheckAll = true; 
                                                                	var dis = "";
//                                                                    $('#all').attr('checked', true); 
                                                    
                                                                    if(p.disableSelect){dis="disabled";$(tr).unbind('click');}
                                                                    check = "<input type='checkbox' "+dis+" checked id='" + g.splitField(idx,row) + "' name='data' />";
                                                                    $(tr).addClass('trSelected');
                                                                    
                                                                    
                                                                } else{
                                                                    check = "<input type='checkbox' id='" + g.splitField(idx,row) + "' name='data' />";
                                                                }

                                                                td.innerHTML = check;
                                                            } else {
                                                                var field = "";
                                                                var fr;
                                                                reg=/^\d*-\d{0,2}-\d{0,2}T\d{0,2}:\d{0,2}:\d{0,2}$/;     
                                                                if (idx.indexOf('.') != -1) { 
                                                                    field = idx.split('.');
                                                                    var value = "";
                                                                    if(field.length > 2){
                                                                    	if (row[field[0]] == undefined 
                                                                    			|| row[field[0]][field[1]] == undefined
                                                                    			||row[field[0]][field[1]][field[2]] == undefined) {
	                                                                    	value =  "";
	                                                                    } else {
	                                                                    	 if(reg.test(row[field[0]][field[1]][field[2]])){
	                                                                    		fr = g.getModel(idx).format;
	                                                                     		if(fr == undefined||fr ==''||fr=='dateTime' ){
	                                                                     			fr = $('#dateTimeFormat').val();
	                                                                     		}else if(fr=='date'){
	                                                                      			fr = $('#dateFormat').val();
	                                                                      		}else{
	                                                                      			fr = $('#dateHourMinuteFormat').val();
	                                                                      		}       
	                                                                    		 value = g.dateFormat(fr,row[field[0]][field[1]][field[2]]);
	                                                                    		
	                                                                    	 }else{
	                                                                    		 if(row[field[0]][field[1]][field[2]].toString() == "true"){
	                                                                    			 value = IS_TRUE;
	                                                                    		 }else
	                                                                    		 if(row[field[0]][field[1]][field[2]].toString() == "false"){
	                                                                    			 value = IS_FALSE;
	                                                                    		 }else
	                                                                    			 value = row[field[0]][field[1]][field[2]];
	                                                                    	 }
	                                                                    }
                                                                    }else{
                                                                    	
	                                                                    if (row[field[0]] == undefined || row[field[0]][field[1]] == undefined) {
	                                                                    	value =  "";
	                                                                    } else {
	                                                                    	 if(reg.test(row[field[0]][field[1]])){
	                                                                    		fr = g.getModel(idx).format;
	                                                                     		if(fr == undefined||fr ==''||fr=='dateTime' ){
	                                                                     			fr = $('#dateTimeFormat').val();
	                                                                     		}else if(fr=='date'){
	                                                                      			fr = $('#dateFormat').val();
	                                                                      		}else{
	                                                                      			fr = $('#dateHourMinuteFormat').val();
	                                                                      		}       
	                                                                    		 value = g.dateFormat(fr,row[field[0]][field[1]]);
	                                                                    		
	                                                                    	 }else{
	                                                                    		 if(row[field[0]][field[1]].toString() == "true"){
	                                                                    			 value = IS_TRUE;
	                                                                    		 }else
	                                                                    		 if(row[field[0]][field[1]].toString() == "false"){
	                                                                    			 value = IS_FALSE;
	                                                                    		 }else
	                                                                    			 value = row[field[0]][field[1]];
	                                                                    	 }
	                                                                    }
	                                                                   
                                                                    }
                                                                    td.innerHTML = check + value;
                                                                } else {
                                                                    if (row[idx] == undefined) {
                                                                        td.innerHTML = check + "";
                                                                    } else {
                                                                    	if(reg.test(row[idx])){
                                                                    		fr = g.getModel(idx).format;
                                                                    		if(fr == undefined||fr ==''||fr=='dateTime' ){
                                                                    			fr = $('#dateTimeFormat').val();
                                                                    		}else if(fr=='date'){
                                                                     			fr = $('#dateFormat').val();
                                                                     		}else{
                                                                     			fr = $('#dateHourMinuteFormat').val();
                                                                     		}                                                                 		
                                                                   		 	value = g.dateFormat(fr,row[idx]);
                                                                   		 	td.innerHTML = check + value;
                                                                 		 
                                                                    	}else{
                                                                    		 if(row[idx].toString()=="true"){
                                                                    			 td.innerHTML = check + IS_TRUE;
                                                                    		 }else
                                                                    		 if(row[idx].toString() == "false"){
                                                                    			 td.innerHTML = check + IS_FALSE;
                                                                    		 }else
                                                                    			 td.innerHTML = check + row[idx];
                                                                   	 	}
                                                                    }
                                                                }


                                                            }
                                                            //alert(idx);
                                                            $(tr).append(td);
                                                            td = null;
                                                            inx++;
                                                        }
                                                        );

                                        if ($('thead', this.gDiv).length < 1) //handle if grid has no headers
                                        {

                                            for (idx = 0; idx < cell.length; idx++)
                                            {
                                                var td = document.createElement('td');
                                                td.innerHTML = row.cell[idx];
                                                $(tr).append(td);
                                                td = null;
                                            }
                                        }

                                        $(tbody).append(tr);
                                        tr = null;
                                    }
                                    );

                } else if (p.dataType == 'xml') {

                    i = 1;

                    $("rows row", data).each
                            (

                                    function ()
                                    {

                                        i++;

                                        var tr = document.createElement('tr');
                                        if (i % 2 && p.striped) tr.className = 'erow';

                                        var nid = $(this).attr('id');
                                        if (nid) tr.id = 'row' + nid;

                                        nid = null;

                                        var robj = this;


                                        $('thead tr:first th', g.hDiv).each
                                                (
                                                        function ()
                                                        {

                                                            var td = document.createElement('td');
                                                            var idx = $(this).attr('axis').substr(3);
                                                            td.align = this.align;
                                                            td.innerHTML = $("cell:eq(" + idx + ")", robj).text();
                                                            $(tr).append(td);
                                                            td = null;
                                                        }
                                                        );


                                        if ($('thead', this.gDiv).length < 1) //handle if grid has no headers
                                        {
                                            $('cell', this).each
                                                    (
                                                            function ()
                                                            {
                                                                var td = document.createElement('td');
                                                                td.innerHTML = $(this).text();
                                                                $(tr).append(td);
                                                                td = null;
                                                            }
                                                            );
                                        }

                                        $(tbody).append(tr);
                                        tr = null;
                                        robj = null;
                                    }
                                    );

                }

                $('tr', t).unbind();
                $(t).empty();
                $(t).append(tbody);
         
                this.addCellProp();
      
                this.addRowProp();
             
                this.fixHeight($(this.bDiv).height());

                this.rePosDrag();

                tbody = null;
                data = null;
                i = null;
            	g.setAllCheck(this);
                if (p.onSuccess) p.onSuccess();
                if (p.hideOnSubmit) $(g.block).remove();//$(t).show();

                this.hDiv.scrollLeft = this.bDiv.scrollLeft;
                if ($.browser.opera) $(t).css('visibility', 'visible');                                       
                return true;
            },            
            splitField :function(idx,row){
            	 field = idx.split('.');
                 var value = "";
                 if(field.length == 1){
                	 value = row[field[0]];
                 }else if(field.length == 2){
                	 value = row[field[0]][field[1]];
                 }else{
                	 value = row[field[0]][field[1]][field[2]];
                 }
                 return value;
            },
            changeSort: function(th) { //change sortorder
                chk = "";
                $('input[name="data"]:checked').each(function() {
                    chk += this.id + ",";
                });
                chk = chk.substring(0, chk.length - 1);
                if (this.loading) return true;
                $(g.nDiv).hide();
                $(g.nBtn).hide();
                var defineSort = g.getModel($(th).attr('abbr')).sortName;
                p.alis = g.getModel($(th).attr('abbr')).alis;
                if (p.sortname == $(th).attr('abbr') || p.sortname == defineSort)
                {
                    if (p.sortorder == 'asc') p.sortorder = 'desc';
                    else p.sortorder = 'asc';
                } 
               
                $(th).addClass('sorted').siblings().removeClass('sorted');
                $('.sdesc', this.hDiv).removeClass('sdesc');
                $('.sasc', this.hDiv).removeClass('sasc');
                $('div', th).addClass('s' + p.sortorder);
                p.sortname = $(th).attr('abbr');
                p.qtype = g.getModel($(th).attr('abbr')).sortType;
              
                if(defineSort != undefined && defineSort != ''){
                	p.sortname = defineSort;
                }
                 if (p.onChangeSort)
                    p.onChangeSort(p.sortname, p.sortorder);
                else
                    this.populate();
                return true;
            },
            buildpager: function() { //rebuild pager based on new properties
                $('.pcontrol input').val(p.page);
                $('.pcontrol span').html(p.pages);
                var stat = p.pagestat;
                if(p.page > p.pages){
                    stat = p.no_record_current_page;
                }else{
                    var r1 = (p.page - 1) * p.rp + 1;
                    var r2 = r1 + p.rp - 1;
                    if (p.total < r2) r2 = p.total;
                    stat = stat.replace(/{from}/, r1);
                    stat = stat.replace(/{to}/, r2);
                    stat = stat.replace(/{total}/, p.total);
                }
                $('.pPageStat', this.pDiv).html(stat);
            },
            getSelectedIds: function() {
                var selectedIds = new Array();
                for (var i = 0; i < myArray.length; i++) {
                    for (var j = 0; j < myArray[i].length; j++) {
                        if ("id" == myArray[i][j]) {
                            selectedIds.push(myArray[i][j + 1]);
                        }
                    }
                }
                return selectedIds.join(',');
            },
            populate: function () { //get latest data
                if (this.loading) return true;

                if (p.onSubmit)
                {
                    var gh = p.onSubmit();
                    if (!gh) return false;
                }

                this.loading = true;
                if (!p.url) return false;

                $('.pPageStat', this.pDiv).html(p.procmsg);

                $('.pReload', this.pDiv).addClass('loading');

                $(g.block).css({top:g.bDiv.offsetTop});

                if (p.hideOnSubmit) $(this.gDiv).prepend(g.block); //$(t).hide();

                if ($.browser.opera) $(t).css('visibility', 'hidden');

                if (!p.newp) p.newp = 1;

                if (p.page > p.pages) p.page = p.pages;
                var param = {"baseGrid.page":p.newp,"baseGrid.alis":p.alis,
                    "baseGrid.rp": p.rp, "baseGrid.sortname": p.sortname,
                    "baseGrid.sortorder": p.sortorder,
                    "baseGrid.query": p.query,
                    "baseGrid.qtype": g.clearUndefined(p.qtype),
                    "baseGrid.selectedIds": g.getSelectedIds()};
                if (p.para) {
                    var para = p.para;
                    $.extend(param, para);
                }
                if (p.params)
                {
                    var nparam = {};
                    $.each(p.params, function() {
                        nparam[this.name] = this.value;
                    });
                    $.extend(param, nparam);
                }

                $.ajax({
                    type: p.method,
                    url: p.url,
                    data: param,
                    timeout: 100000,
                    dataType: p.dataType,
                    error:function (msg,error){
                		if(error=="timeout"){
                			alert(GRID_TIME_OUT);
                             $('.pReload', this.pDiv).removeClass('loading');
                            $('.pPageStat', this.pDiv).html(GRID_TIME_OUT);
                            this.loading = false;
                		}else if( error=="error"){
                            alert(GRID_NETWORK_FAIL);
                            $('.pReload', this.pDiv).removeClass('loading');
                            $('.pPageStat', this.pDiv).html(GRID_NETWORK_FAIL);
                            this.loading = false;
                        }
                        else{
                			  $('.pPageStat', this.pDiv).html(p.errormsg);
                		}
                	},
                    success: function(data) {
                        g.addData(data);
                    	 
                    }
                });
                return true;
            },
            clearUndefined:function(value){
            	if(value == undefined){
            		return "";
            	}else
            	return value;
            },
            doSearch: function () {

                if (!p.search) {
                    p.query = encodeURIComponent($('input', g.sDiv).val());
                    p.qtype = $('select[name=qtype]', g.sDiv).val();
                } else {
                    p.type = "";
                    sitems = p.searchList;
                    for (var s = 0; s < sitems.length; s++)
                    {
                        if (sitems[s].type == 'date' && sitems[s].symbol == 'between') {
                            p.query += $('#' + sitems[s].name).val() + ","
                            p.qtype += sitems[s].name + ",";
                            p.type += sitems[s].type + ",";
                            p.query += $('#' + sitems[s].name + "1").val() + ",";
                            p.qtype += sitems[s].name + ",";
                            p.type += sitems[s].type + ",";

                        } else {
                            p.query += $('#' + sitems[s].name).val() + ","
                            p.qtype += sitems[s].name + ",";
                            p.type += sitems[s].type + ",";
                        }
                    }
                    p.params = [
                        {
                            'name':'type',
                            'value':p.type
                        }
                    ];
                }
                p.newp = 1;
                this.populate();
            },
            changePage: function (ctype) { //change page
                p.search = false;
                if (this.loading) return true;

                switch (ctype)
                        {
                    case 'first': p.newp = 1; break;
                    case 'prev': if (p.page > 1) p.newp = p.page - 1; break;
                    case 'next': if (p.page < p.pages) p.newp = p.page + 1; break;
                    case 'last': p.newp = p.pages; break;
                    case 'input':                  	
                        var nv = parseInt($('.pcontrol input').val());
                        if (isNaN(nv)) nv = 1;
                        if (nv < 1) nv = 1;
                        else if (nv > p.pages) nv = p.pages;
                        $('.pcontrol input').val(nv);
                        p.newp = nv;
                        break;
                }

                if (p.newp == p.page) return false;

                if (p.onChangePage)
                    p.onChangePage(p.newp);
                else {
                    this.populate();                   
                }
                return true;
            },
            addCellProp: function ()
            {
	            var colCell,filed1,filed2;            
	            if(p.sortname.indexOf(',')!= -1){
	                 colCell = p.sortname.split(','); 
	                 if(colCell.length>0){
	                     filed1 = colCell[0].replace(" ","#").split('#')[0];
	                     filed2 = colCell[1].replace(" ","#").split('#')[0];
	                 }
	            }
                $('tbody tr td', g.bDiv).each
                        (
                                function ()
                                {
                                    var tdDiv = document.createElement('div');
                                    var n = $('td', $(this).parent()).index(this);
                                    var pth = $('th:eq(' + n + ')', g.hDiv).get(0);
                                    var sort;
                                    $(this).parent().unbind("click");
                                    if(g.getModel($(pth).attr('abbr')))sort = g.getModel($(pth).attr('abbr')).sortName;
                                    if (pth != null)
                                    {
                                        if (p.sortname == $(pth).attr('abbr') && p.sortname 
                                        		|| p.sortname == sort
                                        		|| $(pth).attr('abbr') == filed1
                                        		|| $(pth).attr('abbr') == filed2)//|| p.checkField== $(pth).attr('abbr'))
                                        {
                                            this.className = 'sorted';
                                        }
                                        $(tdDiv).css({textAlign:pth.align,width: $('div:first', pth)[0].style.width});

                                        if (pth.hide) $(this).css('display', 'none');

                                    }

                                    if (p.nowrap == false) $(tdDiv).css('white-space', 'normal');

                                    if (this.innerHTML == '') this.innerHTML = ' ';

                                    //tdDiv.value = this.innerHTML; //store preprocess value
                                    tdDiv.innerHTML = this.innerHTML;

                                    var prnt = $(this).parent()[0];
                                    var pid = false;
                                    if (prnt.id) pid = prnt.id.substr(3);

                                    if (pth != null)
                                    {
                                        if (pth.process) pth.process(tdDiv, pid);
                                    }

                                    $(this).empty().append(tdDiv).removeAttr('width'); //wrap content

                                    //add editable event here 'dblclick'

                                }
                                );

            },
            getHtml:function(sid, obj1) {
                var obj2 = g.getModel(obj1.name);
                if ($(obj1).attr('atype') == 'select') {
                    $.ajax({
                        type: p.method,
                        url: obj2.action,
                        data: {id:sid},
                        dataType: 'json',
                        success: function(data) {
                            var rs = "";
                            $.each(data[p.backList], function(i, record) {
                                if ($(obj1).val() == record["name"]) {
                                    rs += "<option selected  value='" + record["id"] + "'>" + record["name"] + "</option>";
                                } else {
                                    rs += "<option value='" + record["id"] + "'>" + record["name"] + "</option>";

                                }
                            });
                            var sel = "<select id='" + obj1.name + "' name='" + obj1.name + "'>" + rs + "</select>";
                            var pa = $(obj1).parent()[0];
                            pa.innerHTML = sel;
                        }
                    });
                } else if ($(obj1).attr('atype') == 'date') {
                    g.setDate($(obj1).offset().top + $(obj1).height(), $(obj1).offset().left);
                }
            },
            setDate:function(top, left) {
                var dateDiv = null;
                if ($('#date').attr('class') == undefined) {
                    dateDiv = document.createElement('div');
                    $('.main').append(dateDiv);
                } else {
                    dateDiv = $('#date');
                }
                dateDiv.className = 'de';
                $(dateDiv).attr('id', 'date');
                $(dateDiv).css({top:top,left:left});
                $('#date').append('<div style="width:158px;text-align:right">X</div>');
                var number = "";
                for (var i = 1; i < 31; i++) {
                    if (i % 7 == 0) {
                        number += '<div  style="float:left;width:22px;height:20px">' + i + '</div>';
                    } else {
                        number += '<div  style="float:left;width:20px">' + i + '</div>';
                    }
                }
                $('#date').html(number);
                $('#date').append('<div style="width:158px;text-align:right">close</div>');
            },
            getModel:function(modelName) {
                for (m in p.colModel)
                {
                    if (p.colModel[m]["name"] == modelName) {
                        return p.colModel[m];
                    }
                }
                return null;
            },
            editCellProp: function (op)
            {
                var dateType = "";
                $('tbody tr', g.bDiv).each
                        (
                                function ()
                                {
                                    var b = true;
                                    if (this == op) {
                                        var t = 1;
                                        $('td', op).each(function() {

                                            if (b) {
                                                b = false;

                                            } else {
                                                var tdDiv = document.createElement('div');
                                                var n = $('td', $(this).parent()).index(this);
                                                var pth = $('th:eq(' + n + ')', g.hDiv).get(0);

                                                if (pth != null)
                                                {
                                                    if (p.sortname == $(pth).attr('abbr') && p.sortname)
                                                    {
                                                        this.className = 'sorted';
                                                    }
                                                    $(tdDiv).css({textAlign:pth.align,width: $('div:first', pth)[0].style.width});

                                                    if (pth.hide) $(this).css('display', 'none');

                                                }

                                                if (p.nowrap == false) $(tdDiv).css('white-space', 'normal');

                                                if (this.innerHTML == '') this.innerHTML = '&nbsp;';

                                                //tdDiv.value = this.innerHTML; //store preprocess value
                                                $(tdDiv).append('<input type="text" atype="' + p.colModel[t].type + '" id="' + p.colModel[t].name + '" name="' + p.colModel[t].name + '" value="' + $(this).text() + '"></input>');
                                                if (p.colModel[t].type == 'date') {
                                                    dateType += p.colModel[t].name + ",";
                                                } else {
                                                    $('input', tdDiv).click(
                                                            function() {
                                                                g.getHtml(op.id.substr(3), this)
                                                            }
                                                            );
                                                }
                                                var prnt = $(this).parent()[0];
                                                var pid = false;
                                                if (prnt.id) pid = prnt.id.substr(3);

                                                if (pth != null)
                                                {
                                                    if (pth.process) pth.process(tdDiv, pid);
                                                }
                                                t++;
                                                $(this).empty().append(tdDiv).removeAttr('width'); //wrap content
                                            }
                                        });
                                    } else {
                                        $('td', this).each(function() {
                                            if (b) {
                                                b = false;

                                            } else {
                                                var findI = $('input', this).val();
                                                var findS = $('select', this).find('option:selected').text();
                                                var value = null;
                                                if (findS != undefined && findS != "") {
                                                    value = $('select', this).find('option:selected').text();
                                                }
                                                if (findI != undefined) {
                                                    value = $('input', this).val();
                                                }

                                                if (value != null) {
                                                    var tdDiv = document.createElement('div');
                                                    var n = $('td', $(this).parent()).index(this);
                                                    var pth = $('th:eq(' + n + ')', g.hDiv).get(0);

                                                    if (pth != null)
                                                    {
                                                        if (p.sortname == $(pth).attr('abbr') && p.sortname)
                                                        {
                                                            this.className = 'sorted';
                                                        }
                                                        $(tdDiv).css({textAlign:pth.align,width: $('div:first', pth)[0].style.width});

                                                        if (pth.hide) $(this).css('display', 'none');

                                                    }

                                                    if (p.nowrap == false) $(tdDiv).css('white-space', 'normal');

                                                    if (this.innerHTML == '') this.innerHTML = '&nbsp;';

                                                    //tdDiv.value = this.innerHTML; //store preprocess value
                                                    tdDiv.innerHTML = this.innerHTML = value;
                                                    var prnt = $(this).parent()[0];
                                                    var pid = false;
                                                    if (prnt.id) pid = prnt.id.substr(3);

                                                    if (pth != null)
                                                    {
                                                        if (pth.process) pth.process(tdDiv, pid);
                                                    }

                                                    $(this).empty().append(tdDiv).removeAttr('width'); //wrap content
                                                }
                                            }
                                            ;
                                        });

                                    }


                                }
                                );
                return dateType;
            },
            getCellDim: function (obj) // get cell prop for editable event
            {
                var ht = parseInt($(obj).height());
                var pht = parseInt($(obj).parent().height());
                var wt = parseInt(obj.style.width);
                var pwt = parseInt($(obj).parent().width());
                var top = obj.offsetParent.offsetTop;
                var left = obj.offsetParent.offsetLeft;
                var pdl = parseInt($(obj).css('paddingLeft'));
                var pdt = parseInt($(obj).css('paddingTop'));
                return {ht:ht,wt:wt,top:top,left:left,pdl:pdl, pdt:pdt, pht:pht, pwt: pwt};
            },
            clearCss:function() {
                $("input[name='data']").each(function () {
                    $(this).attr('checked', false);
                });
                $('tbody tr', g.bDiv).each
                        (function() {
                            $(this).removeClass('trSelected');
                        });

            },
            saveForm:function(sid) {
                var para = g.getParm();
                if (!para) {
                    return false;
                }
                var pram = $.extend(g.getParm(), sid);
                $.ajax({
                    type:"post",
                    url:p.saveAction,
                    data:pram,
                    dataType: p.saveType,
                    error: function() {
                        //alert("bn");
                    },
                    success: function(data) {
                        //alert(data);
                    }
                });
                return true;
            },
            getParm:function() {
                var prm = {};
                if (p.colModel) {
                    for (i in p.colModel)
                    {
                        if ($('#' + p.colModel[i].name).val() == undefined) {
                            prm[p.colModel[i].name] = '';
                        } else {
                            prm[p.colModel[i].name] = $('#' + p.colModel[i].name).val();
                        }
                        if ($.trim(prm[p.colModel[i].name]).length == 0 && p.colModel[i].name != 'id') {
                            var obj = $('#' + p.colModel[i].name);
                            var mleft = obj.offset().left + $('#' + p.colModel[i].name).width() / 2 - 20;
                            var mtop = $('#' + p.colModel[i].name).offset().top - $('#msg').height() - 10;
                            $('#msg').showMsg({msg: p.colModel[i].name + ' no empty',left:mleft,top:mtop,time:3000,autoClose:true});
                            return false;
                        }
                    }

                }
                return prm;
            },
            selectType:function(row) {
                var option = "";
                var keyCol = row.keyList;
                var valueCol = row.valueList;
                for (var i = 0; i < keyCol.length; i++) {
                    option += "<option value='" + valueCol[i] + "'>" + keyCol[i] + "</option>";
                }
                sel = "<select class='selectClass' id='" + row.name + "' name='" + row.name + "'" + ">" + option + "</select>";
                return sel;

            },
            dateType:function(row) {
                var result = "";
                if (row.symbol == 'between') {
                    result = "<input type='text' class='date-input' id='" + row.name + "' value=''/>&nbsp;&nbsp;to&nbsp;&nbsp;<input type='text' class='date-input' id='" + row.name + "1' value=''/>";
                } else {
                    result = "<input type='text' class='date-input' id='" + row.name + "' value=''/>";
                }

                return result;

            },
            addSearch:function() {
                if (p.searchList == undefined || p.searchList == null) {
                    return false;
                }
                if (p.search)
                {
                    var dateRecord = "";
                    var sopt = $("<ul></ul>");
                    $.each
                            (
                                    p.searchList,
                                    function(i, row)
                                    {
                                        switch (row.type) {
                                            case "text": $(sopt).append("<li>" + row.display + ":<input type='text' class='search-input' id='" + row.name + "' value=''/></li>");
                                                break;
                                            case "select":$(sopt).append("<li>" + row.display + ":" + g.checkbox(row) + "</li>");
                                                break;
                                            case "date":$(sopt).append("<li>" + row.display + ":" + g.dateType(row) + "</li>");
                                                break;
                                            default: $(sopt).append("<li>" + row.display + ":<input type='text' class='search-input' id='" + row.name + "' value=''/></li>");
                                        }

                                    });
                    $(sopt).append("<input type='button' value='query' class='search-btn'/>");
                    $(g.sDiv).append($(sopt).appendTo("<div class='sDiv2'></div>"));
                    if (p.showTableToggleBtn)
                    {
                        $(g.sDiv).append('<div class="ptogtitle" title="Minimize/Maximize Table"><span></span></div>');
                        $('div.ptogtitle', g.sDiv).click
                                (
                                        function ()
                                        {
                                            $(g.gDiv).toggleClass('hideBody');
                                            $(this).toggleClass('vsble');
                                        }
                                        );
                    }
                    $('.date-input', $(g.sDiv)).each(function() {
                        $(this).datepicker({"dateFormat":p.dateFormat});

                    });
                    $('input[value=query]', g.sDiv).click(
                            function() {
                                g.doSearch();

                            });
                    g.sDiv.className = 'sDiv';
                    $(g.mDiv).before(g.sDiv);


                }
                return true;
            },
            isExistColValue: function(sorArray,srcArray){
            	var value=false;
            	for(var i = 0;i<sorArray.length;i++){
            		var sora = sorArray[i];
    				if(sora[0] == srcArray[0]&&sora[1] == srcArray[1]){
    					 return true;
    				}  
             	}
            	return false;
            },
            findSelectData:function(obj){ 
            	var myfieldArray = new Array();
            	var i=0;
            	var className = $(obj).attr('class');
            	if(p.selectType=='radio' || p.selectType=='radio-empity') p.myArray = new Array();
            	if(className.indexOf('trSelected')!=-1){   
            		if($(obj).attr("id") != "emptyData"){
	            		$('td',obj).each(function(){ 
	            			var key = $(this).attr('abbr');
	            			var value;
	            			var isId = $('input[name="data"]',$(this));
	            			if(isId.length != 0){
	            				value = $(isId).attr('id');
	            			}else{
	            				divobj  = $('div',$(this));
	            				value = $(divobj).html();
	            			}       		
	            			myfieldArray.push(key);	
	            			myfieldArray.push(value);
	            		});   
	            		myfieldArray.push(p.url);
	            		if(!g.isExistColValue(p.myArray,myfieldArray)){
	            			p.myArray.push(myfieldArray);
	            		}
            		}
            	}else{
            		$('td:eq(0)',obj).each(function(){ 
            			var key = $(this).attr('abbr');
            			var value;
            			var isId = $('input[name="data"]',$(this));
            			if(isId.length != 0){
            				value = $(isId).attr('id');
            			}else{
            				divobj  = $('div',$(this));
            				value = $(divobj).html();
            			}
            			g.removeSelectData(key,value);
            		});         
            		
            	}

                myArray = p.myArray;
            },
            removeSelectData:function(key,value){
            	var delIndex = -1;
            	for(var i = 0;i<p.myArray.length;i++){
            		for(var j = 0;j<p.myArray[i].length;j++){
	        			if(p.myArray[i][j] == key && p.myArray[i][j+1]==value){
	        				delIndex = i;
	        			}       
            		}
            	}
            	if(delIndex!= -1){
            		p.myArray.splice(delIndex, 1);
            	}
            },
            addRowRadioClick:function(row){
                g.clearCss();
                g.addRowCheckBoxClick(row);
            },
            addRowProp: function()
            {
                $('tbody tr', g.bDiv).each(function (){$(this)
                     .click(function (e) {
                        if (p.disableSelect) {
                            if ($('div>input', $(row)).attr('disabled')) {
                                return false;
                            }
                        }
                        if ($('div>input', $(this)).length > 1) {
                            return false;
                        }
                        if(p.onSelectItemFromGrid!=undefined&&p.onSelectItemFromGrid!=''){
                        	p.onSelectItemFromGrid(this);
                        }
                        if(p.onSelectItemForAssociateCommentFromGrid!=undefined&&p.onSelectItemForAssociateCommentFromGrid!=''){
                        	p.onSelectItemForAssociateCommentFromGrid(this);
                        }
                        var clickedObj = e.target;
                        var hasSelectedClass = $(this).hasClass('trSelected');
                        var isChecked = $('div>input', $(this)).attr('checked');
                        if(clickedObj.type != undefined && clickedObj.type=="checkbox"){
                            if (p.selectType == 'checkbox') {
                                if (isChecked && !hasSelectedClass) {
                                        $(this).addClass('trSelected');
                                    } else {
                                        $(this).removeClass('trSelected');
                                    }
                            }else if (p.selectType == 'radio') {
                                g.clearCss();
                                $(this).addClass('trSelected');
                                $('div>input', $(this)).attr('checked', true);
                            }else if(p.selectType == 'radio-empity'){
                                g.clearCss();
                                if (isChecked && !hasSelectedClass) {
                                    $(this).addClass('trSelected');
                                    $('div>input', $(this)).attr('checked', true);
                                } else {
                                    $(this).removeClass('trSelected');
                                    $('div>input', $(this)).attr('checked', false);
                                }
                            }
                        }else{
                            if (p.selectType == 'checkbox') {
                                if (!hasSelectedClass) {
                                    $('div>input', $(this)).attr('checked', true);
                                    $(this).addClass('trSelected');
                                }else{
                                    $('div>input', $(this)).attr('checked', false);
                                    $(this).removeClass('trSelected');
                                }
                            }else if (p.selectType == 'radio') {
                                g.clearCss();
                                if (!hasSelectedClass) {
                                    $('div>input', $(this)).attr('checked', true);
                                    $(this).addClass('trSelected');
                                }else{
                                    $('div>input', $(this)).attr('checked', true);
                                    $(this).addClass('trSelected');
                                }
                            }else if(p.selectType == 'radio-empity'){
                                g.clearCss();
                                if (!hasSelectedClass) {
                                    $('div>input', $(this)).attr('checked', true);
                                    $(this).addClass('trSelected');
                                }
                            }
                        }
                        if (edit) {
                            var rs = g.saveForm({id:statcId});
                            if (!rs) {
                                return false;
                            }
                        }
                        edit = false;
                        if (p.oclick) {
                            edit = true;
                            statcId = row.id.substr(3);
                            if ($('div>input', $(row)).length > 1) {
                                return false;
                            }
                            g.clearCss();
                            dateType = g.editCellProp(row);
                            if (dateType != null && dateType.indexOf(',') != -1) {
                                var field = dateType.split(',');
                                for (var i in field) {
                                    $('#' + field[i]).datepicker({"dateFormat":p.dateFormat});
                                }
                            }
                            $(row).toggleClass('trSelected');
                            $('div>input', $(row)).attr('checked', true);
                        }
                        g.setAllCheck(this);
                        g.findSelectData(this);
                        return true;
                    })
                    .dblclick(function (e){
                        statcId = this.id.substr(3);
                        if (edit) {
                            var rs = g.saveForm({id:statcId});
                            if (!rs) {
                                return false;
                            }
                        }
                        if (p.editCell && p.dbclick) {
                            edit = true;
                            if ($('div>input', $(this)).length > 1) {
                                return false;
                            }
                            g.clearCss();
                            dateType = g.editCellProp(this);
                            $(this).toggleClass('trSelected');
                            $('div>input', $(this)).attr('checked', true);
                            // $('#all').attr("checked", true);
                            g.setAllCheck(this);
                            if (dateType != null && dateType.indexOf(',') != -1) {
                                var field = dateType.split(',');
                                for (var i in field) {
                                    $('#' + field[i]).datepicker({"dateFormat":p.dateFormat});
                                }
                            }
                        }return true;})
                        .mousedown(function (e) {
                                if (e.shiftKey)
                                {
                                    $(this).toggleClass('trSelected');
                                    g.multisel = true;
                                    this.focus();
                                    $(g.gDiv).noSelect();
                                }
                            }
                        )
                        .mouseup(function () {
                            if (g.multisel)
                            {
                                g.multisel = false;
                                $(g.gDiv).noSelect(false);
                            }
                        })
                        .hover(function (e){
                            if (g.multisel){
                                $(this).toggleClass('trSelected');
                            }
                        },
                        function () {
                        });
                        if ($.browser.msie && $.browser.version < 7.0){
                        $(this).hover( function (){
                                    $(this).addClass('trOver');
                                },
                                function () {
                                    $(this).removeClass('trOver');
                                });}
                });
                /*$('tbody tr', g.bDiv).each(function () {
                    var row = this;
                    $('div>input', $(row)).click(function(e) {
                        var isChecked = $(this).attr('checked');
                        if (isChecked && !$(this).hasClass('trSelected')) {
                            $(row).addClass('trSelected');
                        } else {
                            $(row).removeClass('trSelected');
                        }
                        g.bindRowClickProp(row,e);
                    });
                });*/
            },
            setAllCheck:function(current){
            	 var x = $("input[name='data']").length;
            	 var y = 0;
                 $("input[name='data']").each(function () {
                     if ($(this).attr('checked')) {
                         y++;
                     }
                 });
                 if(y==0){
                	 $(".checkbox-intemediate").attr("style","display:none");
                	 $('#all').removeAttr("style");
                	 $('#all').attr("checked", false);
                 }
                 else if(x==y){
                	 $(".checkbox-intemediate").attr("style","display:none");
                	 $('#all').removeAttr("style");
                	 $('#all').attr("checked", true);
                 }else{
                	 $(".checkbox-intemediate").removeAttr("style");
                	 $('#all').attr("style","display:none");
                 }
                 
                 
            },
            checkAll :function(obj) {
 
            	var obj = $(obj);
            	if (!$(obj).attr("checked")) {
            		 $('table tr', g.bDiv).removeClass('trSelected');
            		$('table tr input', g.bDiv).each( function() {
            			$(this).attr("checked", false);
            		});            		
            	} else {
            		$('table tr', g.bDiv).addClass('trSelected');
            		$('table tr input', g.bDiv).each( function() {
            			$(this).attr("checked", true);  
            		});           		 
            	}
            	$('table tr', g.bDiv).each( function() { 
        			g.findSelectData($(this));
        		});
            },
            htmlCheckAll:function(){
           	 var oldTh = $('tr:first th:first',$('.hDivBox'));
                var obj = '<input  type="checkbox" id="all" name="all" />';
                var image = '<span class="checkbox-intemediate" style="display:none"></span>';
                var thdiv = document.createElement('div'); 
                
                if(p.onSelect)$(thdiv).css({textAlign:'center', width: g.getModel(p.checkField).width + 'px',height:'20px'});
                $(thdiv).attr('ix',$(oldTh).attr('ix'));
                $(thdiv).parent().attr('ix',$(oldTh).attr('ix'));
                var obj = $(obj);
                $(thdiv).append($(obj).click(function(){g.checkAll(this);}));
                $(thdiv).append($(image).click(function(){
                	$(obj).attr("checked",!$(obj).attr("checked"));
                	$('#all').removeAttr("style");
                	$(".checkbox-intemediate").attr("style","display:none");
                	g.checkAll(obj);
                }));
              
                var th = document.createElement('th');               
                $(th).attr('abbr',$(oldTh).attr('abbr')).attr('axis',$(oldTh).attr('axis'));
                th.align = $(oldTh).attr('align');
              
                $('tr:first th:first',$('.hDivBox')).remove();
                $('tr:first',$('.hDivBox')).prepend($(th).append(thdiv));
           },
           
            pager: 0
        };

        //create model if any
        if (p.colModel)
        {
            thead = document.createElement('thead');
            tr = document.createElement('tr');

            for (i in p.colModel)
            {
                var cm = p.colModel[i];
                var th = document.createElement('th');
                var obj = '<input style="padding-top:2px" type="checkbox" id="all" onclick="" name="all" />';
                if (p.onSelect) {
                    if (cm.name == p.checkField) {
                       if(p.selectType=='checkbox') $(th).append($(obj));
                    } else {
                        th.innerHTML = cm.display;
                    }
                } else {
                    th.innerHTML = cm.display;
                }

                if (cm.name)
                    $(th).attr('abbr', cm.name);
             
                if (cm.ix)
                    $(th).attr('ix', cm.ix);
                
                    $(th).attr('sortable', cm.sortable);

                //th.idx = i;
                $(th).attr('axis', 'col' + i);

                if (cm.align)
                    th.align = cm.align;

                if (cm.width)
                    $(th).attr('width', cm.width);

                if (cm.hide)
                {
                    th.hide = true;
                }

                if (cm.process)
                {
                    th.process = cm.process;
                }
                $(tr).append(th);
            }

            $(thead).append(tr);
            $(t).prepend(thead);
        } // end if p.colmodel

        //init divs
        g.gDiv = document.createElement('div'); //create global container
        g.mDiv = document.createElement('div'); //create title container
        g.hDiv = document.createElement('div'); //create header container
        g.bDiv = document.createElement('div'); //create body container
        g.vDiv = document.createElement('div'); //create grip
        g.rDiv = document.createElement('div'); //create horizontal resizer
        g.cDrag = document.createElement('div'); //create column drag
        g.block = document.createElement('div'); //creat blocker
        g.nDiv = document.createElement('div'); //create column show/hide popup
        g.nBtn = document.createElement('div'); //create column show/hide button
        g.iDiv = document.createElement('div'); //create editable layer
        g.tDiv = document.createElement('div'); //create toolbar
        g.sDiv = document.createElement('div');

        if (p.usepager) g.pDiv = document.createElement('div'); //create pager container
        g.hTable = document.createElement('table');

        //set gDiv
        g.gDiv.className = 'flexigrid';
        if (p.width != 'auto') g.gDiv.style.width = p.width + 'px';

        //add conditional classes
        if ($.browser.msie)
            $(g.gDiv).addClass('ie');

        if (p.novstripe)
            $(g.gDiv).addClass('novstripe');

        $(t).before(g.gDiv);
        $(g.gDiv)
                .append(t)
                ;
       
        //set toolbar
        if (p.buttons)
        {
            g.tDiv.className = 'tDiv';
            var tDiv2 = document.createElement('div');
            tDiv2.className = 'tDiv2';

            for (i in p.buttons)
            {
                var btn = p.buttons[i];
                if (!btn.separator)
                {
                    var btnDiv = document.createElement('div');
                    btnDiv.className = 'fbutton';
                    btnDiv.innerHTML = "<div><span>" + btn.name + "</span></div>";
                    if (btn.bclass)
                        $('span', btnDiv)
                                .addClass(btn.bclass)
                                .css({paddingLeft:20})
                                ;
                    btnDiv.onpress = btn.onpress;
                    btnDiv.name = btn.name;
                    if (btn.onpress)
                    {
                        $(btnDiv).click
                                (
                                        function ()
                                        {
                                            this.onpress(this.name, g.gDiv);
                                        }
                                        );
                    }
                    $(tDiv2).append(btnDiv);
                    if ($.browser.msie && $.browser.version < 7.0)
                    {
                        $(btnDiv).hover(function() {
                            $(this).addClass('fbOver');
                        }, function() {
                            $(this).removeClass('fbOver');
                        });
                    }

                } else {
                    $(tDiv2).append("<div class='btnseparator'></div>");
                }
            }
            $(g.tDiv).append(tDiv2);
            $(g.tDiv).append("<div style='clear:both'></div>");
            $(g.gDiv).prepend(g.tDiv);
        }

        //set hDiv
        g.hDiv.className = 'hDiv';

        $(t).before(g.hDiv);
        //set hTable
        g.hTable.cellPadding = 0;
        g.hTable.cellSpacing = 0;
        $(g.hDiv).append('<div class="hDivBox"></div>');
        $('div', g.hDiv).append(g.hTable);
        var thead = $("thead:first", t).get(0);
        if (thead) $(g.hTable).append(thead);
        thead = null;
        
        if (!p.colmodel) var ci = 0;
       
        //setup thead
        $('thead tr:first th', g.hDiv).each
                (
                        function ()
                        {
                            var thdiv = document.createElement('div'); 
                            var ix =  $(this).attr('ix');
                            $(thdiv).attr('ix',ix);
                            var rs = $(this).attr('sortable');
                              if(rs == undefined || rs.indexOf('true') == 0){                              
 	                            if ($(this).attr('abbr')!= p.checkField)
	                            {
	                                $(this).click(
	                                        function (e)
	                                        {
	
	                                            if (!$(this).hasClass('thOver')) return false;
	                                            var obj = (e.target || e.srcElement);
	                                            if (obj.href || obj.type) return true;
	                                            p.sortname=$(this).attr('abbr');
	                                            g.changeSort(this);	                                           
	                                            return true;
	                                        }
	                                        )
	                                        ;
	
	                                if ($(this).attr('abbr') == p.sortname)
	                                {
	                                    this.className = 'sorted';
	                                    thdiv.className = 's' + p.sortorder;
	                                }
	                            }
                            }

                            if (this.hide) $(this).hide();

                            if (!p.colmodel)
                            {
                                $(this).attr('axis', 'col' + ci++);
                            } 
                            $(thdiv).css({textAlign:this.align, width: this.width + 'px'});
                            thdiv.innerHTML = this.innerHTML;
                           
                            $(this).empty().append(thdiv).removeAttr('width')
                                    .mousedown(function (e) {
                                g.dragStart('colMove', e, this)
                            })
                                    .hover(                                   		 
                                    function() {
                                    	 if ($(this).attr('abbr')== p.checkField)return false;
                                         if (!g.colresize && !$(this).hasClass('thMove') && !g.colCopy) $(this).addClass('thOver');

                                         if ($(this).attr('abbr') != p.sortname && !g.colCopy && !g.colresize && $(this).attr('abbr')) $('div', this).addClass('s' + p.sortorder);
                                         else if ($(this).attr('abbr') == p.sortname && !g.colCopy && !g.colresize && $(this).attr('abbr'))
                                         {
                                             var no = '';
                                             if (p.sortorder == 'asc') no = 'desc';
                                             else no = 'asc';
                                             $('div', this).removeClass('s' + p.sortorder).addClass('s' + no);
                                         }

                                         if (g.colCopy)
                                         {
                                             var n = $('th', g.hDiv).index(this);

                                             if (n == g.dcoln) return false;


                                             if (n < g.dcoln) $(this).append(g.cdropleft);
                                             else $(this).append(g.cdropright);

                                             g.dcolt = n;

                                         } else if (!g.colresize) {

                                             var nv = $('th:visible', g.hDiv).index(this);
                                             var onl = parseInt($('div:eq(' + nv + ')', g.cDrag).css('left'));
                                             if(isNaN(onl)){
                                            	 onl = 0;
                                             }
                                             var border = isNaN(parseInt($(g.nBtn).css('borderLeftWidth')))?0:parseInt($(g.nBtn).css('borderLeftWidth'));
                                             var nw = parseInt($(g.nBtn).width()) + border;
                                             nl = onl - nw + Math.floor(p.cgwidth / 2);

                                             $(g.nDiv).hide();
                                             $(g.nBtn).hide(); 
                                           
                                             btnLeft = onl-$(g.nBtn).width()/2;
                                             $(g.cDrag).css('top',btnTop);    
                                             if(isNaN(btnLeft)){
                                            	 btnLeft = 0;
                                             }
                                             if ($.browser.msie && $.browser.version < 7.0)
                                             {
                                             	
                                             	 $(g.nBtn).css({'left':btnLeft,top:btnTop}).show();
                                             }else{
                                             	
                                             	 $(g.nBtn).css({'left':btnLeft,top:g.hDiv.offsetTop}).show();
                                             }
                                             if(btnLeft>right||btnLeft<0){
                                            	 $(g.nBtn).hide();
                                            }
                                             var ndw = parseInt($(g.nDiv).width());

                                             $(g.nDiv).css({top:g.bDiv.offsetTop});

                                             if ((nl + ndw) > $(g.gDiv).width())
                                                 $(g.nDiv).css('left', onl - ndw + 1);
                                             else
                                                 $(g.nDiv).css('left', nl);

                                             if ($(this).hasClass('sorted'))
                                                 $(g.nBtn).addClass('srtd');
                                             else
                                                 $(g.nBtn).removeClass('srtd');

                                         }
                                         return true;
                                    },
                                    function() {
                                    	if ($(this).attr('abbr')== p.checkField)return false;
                                       $(this).removeClass('thOver');
                                        if ($(this).attr('abbr') != p.sortname) $('div', this).removeClass('s' + p.sortorder);
                                        else if ($(this).attr('abbr') == p.sortname)
                                        {
                                            var no = '';
                                            if (p.sortorder == 'asc') no = 'desc';
                                            else no = 'asc';

                                            $('div', this).addClass('s' + p.sortorder).removeClass('s' + no);
                                        }
                                        if (g.colCopy)
                                        {
                                            $(g.cdropleft).remove();
                                            $(g.cdropright).remove();
                                            g.dcolt = null;
                                        }
                                        return true;
                                    })
                                    
                                    
                                    ; //wrap content
                        }
                        );

        $('thead tr:first th input', g.hDiv).each(function(){
        	if(isCheckAll == true){
        		$(this).attr("checked",true);
        	}else{
        		$(this).attr("checked",false);
        	}
         
        	$(this).bind('click',function(){        		
        		g.checkAll($(this))
        	});
        	
        });
        //set bDiv
        g.bDiv.className = 'bDiv';
        $(t).before(g.bDiv);
        $(g.bDiv)
                .css({ height: (p.height == 'auto') ? 'auto' : p.height + "px"})
                .scroll(function (e) {
            g.scroll()
        })
                .append(t)
                ;

        if (p.height == 'auto')
        {
            $('table', g.bDiv).addClass('autoht');
        }


        //add td properties
        g.addCellProp();

        //add row properties
        g.addRowProp();

        //set cDrag

        var cdcol = $('thead tr:first th:first', g.hDiv).get(0);

        if (cdcol != null)
        {
            g.cDrag.className = 'cDrag';
            g.cdpad = 0;

            g.cdpad += (isNaN(parseInt($('div', cdcol).css('borderLeftWidth'))) ? 0 : parseInt($('div', cdcol).css('borderLeftWidth')));
            g.cdpad += (isNaN(parseInt($('div', cdcol).css('borderRightWidth'))) ? 0 : parseInt($('div', cdcol).css('borderRightWidth')));
            g.cdpad += (isNaN(parseInt($('div', cdcol).css('paddingLeft'))) ? 0 : parseInt($('div', cdcol).css('paddingLeft')));
            g.cdpad += (isNaN(parseInt($('div', cdcol).css('paddingRight'))) ? 0 : parseInt($('div', cdcol).css('paddingRight')));
            g.cdpad += (isNaN(parseInt($(cdcol).css('borderLeftWidth'))) ? 0 : parseInt($(cdcol).css('borderLeftWidth')));
            g.cdpad += (isNaN(parseInt($(cdcol).css('borderRightWidth'))) ? 0 : parseInt($(cdcol).css('borderRightWidth')));
            if ($.browser.version != 8.0){
	            g.cdpad += (isNaN(parseInt($(cdcol).css('paddingLeft'))) ? 0 : parseInt($(cdcol).css('paddingLeft')));
	            g.cdpad += (isNaN(parseInt($(cdcol).css('paddingRight'))) ? 0 : parseInt($(cdcol).css('paddingRight')));
            }
            $(g.bDiv).before(g.cDrag);

            var cdheight = $(g.bDiv).height();
            var hdheight = $(g.hDiv).height();

            $(g.cDrag).css({top: -hdheight + 'px'});

            $('thead tr:first th', g.hDiv).each
                    (
                            function ()
                            {
                                var cgDiv = document.createElement('div');
                                $(g.cDrag).append(cgDiv);
                                if (!p.cgwidth) p.cgwidth = $(cgDiv).width();
                               
                                $(cgDiv).css({height: cdheight + hdheight})
                                        .mousedown(function(e) {
                                    g.dragStart('colresize', e, this);
                                })
                                        ;
                                if ($.browser.msie && $.browser.version < 7.0)
                                {
        
                                    g.fixHeight($(g.gDiv).height());
                                    $(cgDiv).hover(
                                            function ()
                                            {
                                                $(g.cDrag).css('top',btnTop);
                                                $(this).addClass('dragging')
                                            },
                                            function () {
                                                if (!g.colresize) $(this).removeClass('dragging')
                                            }
                                            );
                                }
                            }
                            );

            g.rePosDrag();

        }


        //add strip
        if (p.striped)
            $('tbody tr:odd', g.bDiv).addClass('erow');

        var right;
        
        if (p.resizable && p.height != 'auto')
        {
            g.vDiv.className = 'vGrip';
            $(g.vDiv)
                    .mousedown(function (e) {
                g.dragStart('vresize', e)
            })
                    .html('<span></span>');
              
        }
        $(g.bDiv).after(g.vDiv);
        right = g.vDiv.offsetLeft+$(g.vDiv).width();
        var f = g.vDiv.offsetTop;
        var bh = $(g.bDiv).height();
        var pdiv = $(g.pDiv).height();
        btnTop += f-bh-$(g.nBtn).width()-pdiv-$(g.nBtn).width()-5;
        $(g.cDrag).css('top',btnTop);               
        var h = $(g.bDiv).height();
        if (p.resizable && p.width != 'auto'&&p.colsable)
        { 
            g.rDiv.className = 'hGrip';
            $(g.rDiv)
                    .mousedown(function (e) {
                g.dragStart('vresize', e, true);
            })
                    .html('<span></span>')
                    .css({'height': $(g.bDiv).height(),'left':right,'top': btnTop})
                    ;
            if ($.browser.msie && $.browser.version < 7.0)
            {
                $(g.rDiv).hover(function() {
                    $(this).addClass('hgOver');
                }, function() {
                    $(this).removeClass('hgOver');
                });
            }
            $(g.gDiv).append(g.rDiv);
        } 
        if (p.title)
        {
            g.mDiv.className = 'mDiv';
            g.mDiv.innerHTML = '<div class="ftitle">' + p.title + '</div>';
            $(g.gDiv).prepend(g.mDiv);

        }

        // add pager
        if (p.usepager)
        {
            g.pDiv.className = 'pDiv';
            g.pDiv.innerHTML = '<div class="pDiv2"></div>';
            $(g.bDiv).after(g.pDiv);
            var html = ' <div class="pGroup"> <div class="pFirst pButton"><span></span></div><div class="pPrev pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pcontrol">第 <input type="text" size="4" value="1" />頁/ 共 <span> 1 </span>頁</span></div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pNext pButton"><span></span></div><div class="pLast pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pReload pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pPageStat"></span></div>';
            $('div', g.pDiv).html(html);

            $('.pReload', g.pDiv).click(function() {
                p.search = false;
                g.populate();
            });
            $('.pFirst', g.pDiv).click(function() {
                g.changePage('first')
            });
            $('.pPrev', g.pDiv).click(function() {
                g.changePage('prev')
            });
            $('.pNext', g.pDiv).click(function() {
                g.changePage('next')
            });
            $('.pLast', g.pDiv).click(function() {
                g.changePage('last')
            });
           
 
            $('.pcontrol input', g.pDiv).keydown(function(e) {             	
                if (e.keyCode == 13){  
                 
                	g.changePage('input');
                }
            });
            if ($.browser.msie && $.browser.version < 7) $('.pButton', g.pDiv).hover(function() {
                $(this).addClass('pBtnOver');
            }, function() {
                $(this).removeClass('pBtnOver');
            });

            if (p.useRp)
            {
                var opt = "";
                for (var nx in p.rpOptions)
                {
                    if (p.rp == p.rpOptions[nx]) sel = 'selected="selected"'; else sel = '';
                    opt += "<option value='" + p.rpOptions[nx] + "' " + sel + " >" + p.rpOptions[nx] + "&nbsp;&nbsp;</option>";
                }
                ;
                $('.pDiv2', g.pDiv).prepend("<div class='pGroup'><select name='rp'>" + opt + "</select></div> <div class='btnseparator'></div>");
                $('select', g.pDiv).change(
                        function ()
                        {
                            if (p.onRpChange)
                                p.onRpChange(+this.value);
                            else
                            {
                                p.newp = 1;
                                p.rp = +this.value;
                                g.populate();
                            }
                        }
                        );
            }

            //add search button
            if (p.searchitems && !p.search)
            {
                $('.pDiv2', g.pDiv).prepend("<div class='pGroup'> <div class='pSearch pButton'><span></span></div> </div>  <div class='btnseparator'></div>");
                $('.pSearch', g.spDiv).click(function() {
                    $(g.sDiv).slideToggle('fast', function() {
                        $('.sDiv:visible input:first', g.gDiv).trigger('focus');
                    });
                });
                //add search box
                g.sDiv.className = 'sDiv';

                sitems = p.searchitems;

                var sopt = "";
                for (var s = 0; s < sitems.length; s++)
                {
                    if (p.qtype == '' && sitems[s].isdefault == true)
                    {
                        p.qtype = sitems[s].name;
                        sel = 'selected="selected"';
                    } else sel = '';
                    sopt += "<option value='" + sitems[s].name + "' " + sel + " >" + sitems[s].display + "&nbsp;&nbsp;</option>";
                }

                if (p.qtype == '') p.qtype = sitems[0].name;

                $(g.sDiv).append("<div class='sDiv2'>search : <input type='text' size='30' name='q' class='qsbox' /> <select name='qtype'>" + sopt
                        + "</select> <input type='button' value='query' /></div>");

                $('input[name=q],select[name=qtype]', g.sDiv).keydown(
                        function(e)
                        {
                            if (e.keyCode == 13)
                                g.doSearch();
                        }
                        );


                $('input[value=query]', g.sDiv).click(
                        function() {
                            g.doSearch();

                        });

                $(g.bDiv).after(g.sDiv);

            }
        }

        $(g.pDiv, g.sDiv).append("<div style='clear:both'></div>");

        // add title


        //setup cdrops
        g.cdropleft = document.createElement('span');
        g.cdropleft.className = 'cdropleft';
        g.cdropright = document.createElement('span');
        g.cdropright.className = 'cdropright';

        //add block
        g.block.className = 'gBlock';
        var gh = $(g.bDiv).height();
        var gtop = g.bDiv.offsetTop;
        $(g.block).css(
        {
            width: g.bDiv.style.width,
            height: gh,
            background: 'white',
            position: 'relative',
            marginBottom: (gh * -1),
            zIndex: -1,
            top: gtop,
            left: '0px'
        }
                );
        $(g.block).fadeTo(0, p.blockOpacity);

        // add column control
        if ($('th', g.hDiv).length)
        {

            g.nDiv.className = 'nDiv';
            g.nDiv.innerHTML = "<table cellpadding='0' cellspacing='0'><tbody></tbody></table>";
            $(g.nDiv).css(
            {
                marginBottom: (gh * -1),
                display: 'none',
                top: gtop
            }
                    ).noSelect()
                    ;

            var cn = 0;


            $('th div', g.hDiv).each
                    (
                            function ()
                            {
                                var kcol = $("th[axis='col" + cn + "']", g.hDiv)[0];
                                var sort = $(kcol).attr('sortable');      
                                var chk = 'checked="checked"';
                                var startlen = 0;
                                if (kcol.style.display == 'none') chk = '';
                                var totallen = this.innerHTML.length;
                                if (this.innerHTML.indexOf('&nbsp') != -1) {
                                    startlen = this.innerHTML.indexOf('&nbsp') + 6;
                                    totallen = totallen - startlen;
                                }
                                if (cn != 0 && sort!='hid') {
                                    $('tbody', g.nDiv).append('<tr><td class="ndcol1"><input type="checkbox" ' + chk + ' class="togCol" value="' + cn + '" /></td><td class="ndcol2">' + this.innerHTML.substr(startlen, totallen) + '</td></tr>');
                                }
                                cn++;                            	
                             
                            }
                            );

            if ($.browser.msie && $.browser.version < 7.0)
                $('tr', g.nDiv).hover
                        (
                                function () {
                                    $(this).addClass('ndcolover');
                                },
                                function () {
                                    $(this).removeClass('ndcolover');
                                }
                                );

            $('td.ndcol2', g.nDiv).click
                    (
                            function ()
                            {
                                if ($('input:checked', g.nDiv).length <= p.minColToggle && $(this).prev().find('input')[0].checked) return false;
                                return g.toggleCol($(this).prev().find('input').val());
                            }
                            );

            $('input.togCol', g.nDiv).click
                    (
                            function ()
                            {

                                if ($('input:checked', g.nDiv).length < p.minColToggle && this.checked == false) return false;
                                $(this).parent().next().trigger('click');
                                //return false;
                                return true;
                            }
                            );

            $(g.gDiv).prepend(g.nDiv);
            $(g.nBtn).addClass('nBtn')
                    .html('<div></div>')
                    .attr('title', hidbtn)
                    .css('top', g.hDiv.offsetTop)
                    .click
                    (
                            function ()
                            {
                                $(g.nDiv).toggle();
                               
                                return true;
                            }
                            );

            if (p.showToggleBtn) $(g.gDiv).prepend(g.nBtn);

        }

        // add date edit layer
        $(g.iDiv)
                .addClass('iDiv')
                .css({display:'none'})
                ;
        $(g.bDiv).append(g.iDiv);

        // add flexigrid events
        $(g.bDiv)
                .hover(function() {
            $(g.nDiv).hide();
            $(g.nBtn).hide();
        }, function() {
            if (g.multisel) g.multisel = false;
        })
                ;
        $(g.gDiv)
                .hover(function() {
        }, function() {
            $(g.nDiv).hide();
            $(g.nBtn).hide();
        })
                ;

        //add document events
        $(document)
                .mousemove(function(e) {
            g.dragMove(e)
        })
                .mouseup(function(e) {
            g.dragEnd()
        })
                .hover(function() {
        }, function () {
            g.dragEnd()
        })
                ;

        //browser adjustments
        if ($.browser.msie && $.browser.version < 7.0)
        {
            $('.hDiv,.bDiv,.mDiv,.pDiv,.vGrip,.tDiv, .sDiv', g.gDiv)
                    .css({width: '100%'});
            $(g.gDiv).addClass('ie6');
            if (p.width != 'auto') $(g.gDiv).addClass('ie6fullwidthbug');
        }
        if(p.onSelect){
        	if(p.selectType=='checkbox')g.htmlCheckAll();
        }
        //make grid functions accessible
        t.p = p;
        t.grid = g;
        // load data
        if (p.url && p.autoload)
        {
            g.populate();
        }
      
        return t;

    };

    var docloaded = false;

    $(document).ready(function () {
        docloaded = true
    });

    $.fn.flexigrid = function(p) {

        return this.each(function() {

            if (!docloaded)
            {
                $(this).hide();
                var t = this;
                $(document).ready
                        (
                                function ()
                                {
                                    $.addFlex(t, p);
                                    return true; }
                                );
            } else {
                $.addFlex(this, p);
            } return true;
        });

    }; //end flexigrid

    $.fn.flexReload = function(p) { // function to reload grid

        return this.each(function() {
            if (this.grid && this.p.url){
            	this.grid.populate();
            	this.grid.setAllCheck();
            }
            return true; });

    }; //end flexReload

    $.fn.flexOptions = function(p) { //function to update general options

        return this.each(function() {
            if (this.grid) $.extend(this.p, p);
            return true; });

    }; //end flexOptions

    $.fn.flexToggleCol = function(cid, visible) { // function to reload grid

        return this.each(function() {
            if (this.grid) this.grid.toggleCol(cid, visible);
            return true; });

    }; //end flexToggleCol


    $.fn.noSelect = function(p) { //no select plugin by me :-)

        if (p == null)
            prevent = true;
        else
            prevent = p;

        if (prevent) {

            return this.each(function ()
            {
                if ($.browser.msie || $.browser.safari) $(this).bind('selectstart', function() {
                    return false;
                });
                else if ($.browser.mozilla)
                {
                    $(this).css('MozUserSelect', 'none');
                    $('body').trigger('focus');
                }
                else if ($.browser.opera) $(this).bind('mousedown', function() {
                        return false;
                    });
                    else $(this).attr('unselectable', 'on');
                return true;  });

        } else {


            return this.each(function ()
            {
                if ($.browser.msie || $.browser.safari) $(this).unbind('selectstart');
                else if ($.browser.mozilla) $(this).css('MozUserSelect', 'inherit');
                else if ($.browser.opera) $(this).unbind('mousedown');
                    else $(this).removeAttr('unselectable', 'on');
                return true; });

        }

    }; //end noSelect

})(jQuery);
