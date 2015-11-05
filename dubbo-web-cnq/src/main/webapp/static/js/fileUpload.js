var swfu;
var swfu2;
var swfuGrep;
window.onload = function() {
	swfu = new SWFUpload({
		// Backend Settings
		upload_url : upload_url,
		post_params : {
			"systemSign" : systemSign,
			"filePath" : filePath,
			"param1" : param1
		},
		//启动，用来使用querystring 传递参数，否则无法进行参数传递
		use_query_string : true,
		file_upload_limit : "0", // Zero means unlimited
		file_post_name : "filedata",
		file_queue_error_handler : fileQueueError,
		file_dialog_complete_handler : fileDialogComplete,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		//upload_success_handler: uploadSuccess,
		upload_complete_handler : uploadComplete,
		upload_success_handler : function(file, serverData) {
			var serverDataJson = eval("(" + serverData + ")");
			if(serverDataJson.code!=0){
				pop_succeed("上传提示", serverDataJson.message, function() {
				}, false);
			}else{
				$("#" + param1).val(serverDataJson.url);
				$("input[name^='versionSize']").val(serverDataJson.param1);
				$("input[name^='versionCrc']").val(serverDataJson.param2);
				$("#fsUploadProgress").empty();
				pop_succeed("上传提示", "上传成功", function() {
				}, false);
			}
			
		},
		// Button settings
		button_placeholder_id : "spanButtonPlaceHolder",
		button_width : 93, //按钮宽度
		button_height : 32, //按钮高度
		button_text : '选择上传文件',//按钮文字
		button_text_style : 'Mouse.cursor="hand"',//按钮文字样式
		button_text_top_padding : 5,//文字距按钮顶部距离
		button_text_left_padding : 5,//文字距离按钮左边距离
		button_image_url : "/images/swf.png",//按钮背景
		// Flash Settings
		flash_url : "/js/swfupload/Flash/swfupload.swf", // Relative to this file
		custom_settings : {
			//upload_target: "divFileProgressContainer"
			progressTarget : "fsUploadProgress",
			uploadButtonId : "btnUpload",
			cancelButtonId : "btnCancel"
		},
		// Debug Settings
		debug : false
	});
	
	swfu2 = new SWFUpload({
		// Backend Settings
		upload_url : upload_url,
		post_params : {
			"systemSign" : systemSign,
			"filePath" : filePath,
			"param2" : param2
		},
		//启动，用来使用querystring 传递参数，否则无法进行参数传递
		use_query_string : true,
		file_upload_limit : "0", // Zero means unlimited
		file_post_name : "filedata",
		file_queue_error_handler : fileQueueError,
		file_dialog_complete_handler : fileDialogComplete,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		//upload_success_handler: uploadSuccess,
		upload_complete_handler : uploadComplete,
		upload_success_handler : function(file, serverData) {
			var serverDataJson = eval("(" + serverData + ")");
			if(serverDataJson.code!=0){
				pop_succeed("上传提示", serverDataJson.message, function() {
				}, false);
			}else{
				$("#" + param2).val(serverDataJson.url);
				$("input[name^='largeVersionSize']").val(serverDataJson.param1);
				$("input[name^='largeVersionCrc']").val(serverDataJson.param2);
				$("#fsUploadProgress2").empty();
				pop_succeed("上传提示", "上传成功", function() {
				}, false);
			}
		},
		// Button settings
		button_placeholder_id : "spanButtonPlaceHolder2",
		button_width : 93, //按钮宽度
		button_height : 32, //按钮高度
		button_text : '选择上传文件',//按钮文字
		button_text_style : 'Mouse.cursor="hand"',//按钮文字样式
		button_text_top_padding : 5,//文字距按钮顶部距离
		button_text_left_padding : 5,//文字距离按钮左边距离
		button_image_url : "/images/swf.png",//按钮背景
		// Flash Settings
		flash_url : "/js/swfupload/Flash/swfupload.swf", // Relative to this file
		custom_settings : {
			//upload_target: "divFileProgressContainer"
			progressTarget : "fsUploadProgress2",
			uploadButtonId : "btnUpload2",
			cancelButtonId : "btnCancel2"
		},
		// Debug Settings
		debug : false,
		file_types:"*avi,*.mpg,*.mpeg,*.rm,*.rmvb,*.wmv,*.mov,*.mp4,*.3gp,*.flv"
	});
	
	
	
	swfuGrep = new SWFUpload({
		upload_url : excel_url,
		use_query_string : true,
		file_upload_limit : "0", // Zero means unlimited
		file_post_name : "filedata",
		file_queue_error_handler : fileQueueError,
		file_dialog_complete_handler : fileDialogComplete,
		upload_progress_handler : uploadProgress,
		upload_error_handler : function (file, errorCode, message) {
			switch (errorCode) {
				case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
					pop_error("导入", "网络异常", function() {}, false);
					break;
				case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
					pop_error("导入", "上传失败", function() {}, false);
					break;
				case SWFUpload.UPLOAD_ERROR.IO_ERROR:
					pop_error("导入", "请求失败", function() {}, false);
					break;
				case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
					pop_error("导入", "网络安全异常", function() {}, false);
					break;
				case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
					break;
				case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
					pop_error("导入", "文件验证失败", function() {}, false);
					break;
				case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
					pop_error("导入", "取消上传文件", function() {}, false);
					break;
				case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
					pop_error("导入", "上传停止", function() {}, false);
					break;
				default:
					pop_error("导入", "上传失败", function() {}, false);
					break;
				}
		},
		upload_complete_handler : uploadComplete,
		upload_success_handler : function(file, serverData) {
			if(serverData.length==1)
			{
				if(serverData == 1)
				{
					pop_error("导入", "导入模板错误,请重新下载模板", function() {}, false);
				}else if(serverData == 2)
				{
					pop_error("导入", "导入数据大于5000条", function() {}, false);
				}
			}else
			{
				/**if(serverData.length>20000)
				{
					serverData = serverData.substr(0,20000);
				}**/
				var oldData = $("#grayUsers").val();
				if(oldData.trim().length>0)
				{	
					serverData = oldData+","+serverData;
				}
				$("#grayUsers").val(serverData);
				$("#fsUploadProgressGrep").empty();
				pop_succeed("导入", "导入成功", function() {
				}, false);
			}
		},
		button_placeholder_id : "spanButtonPlaceHolderGrep",
		button_width : 93, //按钮宽度
		button_height : 32, //按钮高度
		button_text : '<span class="redText">选择导入文件</span>',//按钮文字
		button_text_style : '.redText { color: #FF0000;backgrouce }',//按钮文字样式
		button_text_top_padding : 5,//文字距按钮顶部距离
		button_text_left_padding : 5,//文字距离按钮左边距离
		button_image_url : "/images/swf.png",//按钮背景
		// Flash Settings
		flash_url : "/js/swfupload/Flash/swfupload.swf", // Relative to this file
		custom_settings : {
			//upload_target: "divFileProgressContainer"
			uploadButtonId : "btnUploadGrep",
			cancelButtonId : "btnCancelGrep"
		},
		// Debug Settings
		debug : false
	});
};
