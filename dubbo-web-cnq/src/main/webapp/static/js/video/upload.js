$('#up_loading').css({'position':'absolute','left':($('#img_div').width()-$('#up_loading').width()) / 2 + 'px','top':($('#img_div').height()-$('#up_loading').height()) / 2 + 'px'});
    $("#close-win").bind('click',function(){
        $('#myModal').trigger("reveal:close");
        $('#myModal').hide();
        return false;
    });
    $("#win_confirm").on('click', function(e){
        e.preventDefault();
        var params = jcrop_api.tellSelect(), index = $("#win_index").val(),s=scale?scale:1;
        params.img = $("#win_img").attr("src");
        params.w = Math.round(params.w*s),params.h=Math.round(params.h*s);
        params.x = Math.round(params.x*s),params.y=Math.round(params.y*s);
        Util._sendMsg("/upload/cutImage", params, function(d){
            if(jcrop_api && jcrop_api.destroy) {
                jcrop_api.destroy();
                jcrop_api = undefined;
            }
            var imgUrl = d.url+"/"+d.imageName;
            $("#image_value"+index).val(imgUrl);
            $("#image_title"+index).attr("src", imgUrl);
            var img = $("#win_img");
            img.removeAttr("style");
            $('#myModal').trigger("reveal:close");
            $('#myModal').hide();
            title_image_change();
        });
    });
    var imageName = $("#titleImage").val().split(",");
    var imageSrc = $("#imageServer").val()+"/";
    for(var i=0;i<=imageName.length;i++) {
        var index = i+1;
        if(!emptyStr(imageName[i])) {
            $("#image_title"+index).attr("src", imageName[i]);
            $("#image_value"+index).val(imageSrc+imageName[i]);
        }
    }
title_image_button_control();

function ajaxImageUpload(index)
{
    var file = $("#imageUpload").val();  
    if(file==""){  
    	pop_succeed("系统提示", "请选择文件！", function() {
		}, false);
        return;  
    }  
    var fileType = file.substring(file.lastIndexOf(".")+1);  
    if(fileType!="png"&&fileType!="jpg"&&fileType!="jpeg"){  
    	pop_succeed("系统提示", "上传文件格式错误，只能传（png、jpg、jpeg）格式！", function() {
		}, false);
        return;
    } 
     
    $.ajaxFileUpload
    (
        {
            url:'/upload/imagelocal',
            secureuri:false,
            fileElementId:'imageUpload',
            dataType: 'json',
            success: function (data, status)
            {
               if(data.code == 0){
            	   var img_url = data.url;
            	   open_img(img_url, index);
            	   //$("#titleImage").val(data.imageName);
            	   //$("#image_title1").attr("src", img_url);
               }else{
            	   pop_succeed("系统提示", data.message, function() {
           		}, false);
               }
            },
            error: function (data, status, e)
            {
                //alert(e);
            }
        }
    )
    
    return false;
}

var jcrop_api,scale=1;
function open_img(img_url, index){
	var img = $("#win_img");
	img.attr("src", img_url);
	$("#win_index").val(index);
	if(jcrop_api && jcrop_api.destroy) {
		jcrop_api.destroy();
		jcrop_api = undefined;
	}
	function _ratio() {
		var result;
		if ($("#titleLayout").val() == 5) {
			result = 2;
		} else {
			result = globals_config.aspectRatio.title_img;
		}
		return result;
	}
	img.Jcrop({
		// start off with jcrop-light class
		bgOpacity : 0.5,
		bgColor : 'white',
		aspectRatio: _ratio(),
		addClass : 'jcrop-light'
	}, function() {
		jcrop_api = this;
		jcrop_api.setSelect([ 10, 10, 100, 100]);
		jcrop_api.setOptions({
			bgFade : true
		});
		jcrop_api.ui.selection.addClass('jcrop-selection');
	});
	img.show();
	var maxSize=300,w=img.width, h=img.height;

	if(w>h) {
		h = (h*maxSize)/w;
		w = maxSize;
	} else {
		w = (w*maxSize)/h;
		h = maxSize;
	}
	img.width(w);
	img.height(h);
	$("<img/>").attr("src", img_url).load(function(){
		w=this.width;
		scale = w/img.width();
	});
	$('#myModal').show();
	$('#myModal').reveal({animation:"fade", closeonbackgroundclick:false});
}
function title_image_change() {
	var value="";
	for(var i=1;i<=3;i++) {
		var tmp = $("#image_value"+i).val();
		if(emptyStr(tmp)) {
			continue;
		}
		if(value.length!=0) {
			value+=",";
		}
		value+=tmp;
	}
	$("#titleImage").val(value);
	title_image_button_control();
}

function title_image_del(index) {
	$("#image_title"+index).attr("src", "");
	$("#image_value"+index).val("");
	title_image_change();
}
function title_image_button_control() {
	var i1=$("#image_value1").val(),i2=$("#image_value2").val(),i3=$("#image_value3").val();
	if(emptyStr(i1)) {
		$("#btnupload2").attr("disabled", true);
		$("#btnupload3").attr("disabled", true);
	} else if(emptyStr(i2)) {
		$("#btnupload2").attr("disabled", false);
		$("#btnupload3").attr("disabled", true);
		$("#title_del1").attr("disabled", false);
		$("#title_del2").attr("disabled", true);
	} else if(emptyStr(i3)) {
		$("#btnupload2").attr("disabled", false);
		$("#btnupload3").attr("disabled", false);
		$("#title_del1").attr("disabled", true);
		$("#title_del2").attr("disabled", false);
	} else {
		$("#btnupload2").attr("disabled", false);
		$("#btnupload3").attr("disabled", false);
		$("#title_del1").attr("disabled", true);
		$("#title_del2").attr("disabled", true);
	}
}