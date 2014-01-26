function getNow(){
	var d=new Date();
	var year=d.getFullYear();
	var month=d.getMonth()+1;
	var date=d.getDate();
	var hours=d.getHours();
	var minutes=d.getMinutes();
	var seconds=d.getSeconds();
	return year+"-"+month+"-"+date+" "+hours+":"+minutes+":"+seconds;
}

function Map(){
	this.data=new Array();
	
	this.size=function(){
		return this.data.length;
	};
	
	this.isEmpty=function(){
		return this.data.length==0;
	};
	
	this.put=function(_key,_value){
		this.data.push({
			key:_key,
			value:_value
		});
	};
	
	this.remove=function(_key){
		for(var i=0;i<this.data.length;i++){
			if(this.data[i].key == _key){
				this.data.splice(i,1);
				return true;
			}
		}
	};
	
	this.get=function(_key){
		for(var i=0;i<this.data.length;i++){
			if(this.data[i].key == _key){
				return this.data[i].value;
			}
		}
	};
	
}