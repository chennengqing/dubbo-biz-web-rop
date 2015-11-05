function loadQuarter(search_programId){
	var param = $("#programId").val();
	if(param=="") return;
	Util._sendMsg("/quarter/findbyprogramid?programId=" + param,"",function(result){
		var quarters = result;
		    
		if(!(quarters instanceof Array)) {
			return; 
		}
		var qItem=$("#quarterId");
		qItem.empty();
		qItem.append($("<option>").val("").text("请选择"));
		for(var i=0;i<quarters.length;i++) {
			if(search_programId==quarters[i].id){
				qItem.append($("<option>").val(quarters[i].id).text(quarters[i].title).attr("selected",true));
			}else{
				qItem.append($("<option>").val(quarters[i].id).text(quarters[i].title));
			}
		}
	});
}
function loadPeriod(search_quarters){
	var param = $("#quarterId").val();
	if(param=="") return;
	Util._sendMsg("/period/findbyquarterid?quarterId=" + param,"",function(result){
		var quarters = result;
		    
		if(!(quarters instanceof Array)) {
			return; 
		}
		var qItem=$("#periodId");
		qItem.empty();
		qItem.append($("<option>").val("").text("请选择"));
		for(var i=0;i<quarters.length;i++) {
			if(search_quarters==quarters[i].id){
				qItem.append($("<option>").val(quarters[i].id).text(quarters[i].title).attr("selected",true));
			}else{
				qItem.append($("<option>").val(quarters[i].id).text(quarters[i].title));
			}
		}
	});
}

