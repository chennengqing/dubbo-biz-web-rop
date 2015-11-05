<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<title>添加节目</title>
<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />

<script type="text/javascript" src="/js/video/video.js"></script>
<script src="/js/AjaxFileUploader/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/define.js"></script>
<script src="/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
<link href="/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>

<!-- 弹出窗 -->
<script src="/js/jQuery/jquery.reveal.js" type="text/javascript"></script>
<link href="/css/jQuery/reveal.css" type="text/css" rel="stylesheet" />
<!-- 图片裁剪 -->
<script src="/js/jQuery/jquery.Jcrop.min.js" type="text/javascript"></script>
<script src="/js/jQuery/jquery.color.js" type="text/javascript"></script>
<link href="/css/jQuery/jquery.Jcrop.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/js/core.1.0.js"></script>

<link rel="stylesheet" type="text/css" href="/js/swfupload/css/default.css" />

<script type="text/javascript" src="/js/swfupload/swfupload.js"></script>
<script type="text/javascript" src="/js/swfupload/plugins/fileprogress.js"></script>
<script type="text/javascript" src="/js/swfupload/plugins/handlers.js"></script>
<script type="text/javascript">
var upload_url = "/upload/video";
var systemSign = "upgrade";
var filePath = "system";
var param1 = "videoUrl";
var code = "${(code)!0}"
if(code==(-1001)){
	SWFUpload.uploadSuccess=-1001;
}else if(code==(-1000)){
	SWFUpload.uploadSuccess=-1000;
}
</script>
<script type="text/javascript" src="/js/fileUpload.js"></script>
</head>
<body id="add_body">
<form id="inputForm" method="post" action="save">
    <div id="auditTab" class="pop_main" style="width:600px;border: 0px solid;">

       <div class="pop_grouprmation_mod">
            <ul class="pop_list merchant_type_add">
            		<li class="clearfix">
                		<label for="programId require" class="tit">节目：<span class=" red">*</span></label>
                		<select class="c_select required" id="programId" name="programId" onchange="loadQuarter()" style="width:215px;">
		                     <option value="">请选择</option>
		                     <#list programList as info>
		                     	<#if info.id == programId!0>
		                     		<option selected value="${info.id}">${info.title}</option>
		                     	<#else>
		                     		<option value="${info.id}">${info.title}</option>
		                     	</#if>
		                     </#list>
		                 </select>
               		</li>
               		<li class="clearfix">
                		<label for="quarterId" class="tit">季：</label>
                		<select class="c_select" id="quarterId" name="quarterId" onchange="loadPeriod()" style="width:215px;">
		                     <option value="">请选择</option>
		                 </select>
               		</li>
               		
               		<li class="clearfix">
                		<label for="periodId" class="tit">期：</label>
                		<select class="c_select" id="periodId" name="periodId" style="width:215px;">
		                     <option value="">请选择</option>
		                 </select>
               		</li>
               		
               		<li class="clearfix">
                		<label for="title require" class="tit">标题：<span class=" red">*</span></label>
                		<input class="c_input_text required" type="text" style="width:200px;" name="title" id="title" realValue="请输入期名称" maxlength="32" />
               		</li>
				               		
                	<li class="clearfix">
                		<label for="type" class="tit">类型：<span class=" red">*</span></label>
               			<input type="radio" name="type" checked="checked" value="0" />正片
                		<input type="radio" name="type" value="1" />花絮
                	</li>
                	
                	<li class="clearfix">
                		<label for="titleImage" class="tit">标题图片：<span class=" red">*</span></label>
						<span>
                        <img src="" id="image_title1" width="120" height="90" />
                        <input type="hidden" id="image_value1"/>
                        <input type="button" value="删除1" id="title_del1" onclick="title_image_del(1)"/>
						</br>
                       	<input class="c_input_text required" type="hidden" id="titleImage"  name="titleImage"/>
                       	<input type="hidden" value="${imageServer }" id="imageServer" name="imageServer" />
                       	<input id="imageUpload" type="file" size="45" name="imageUpload" class="input">
                       	<input id="btnupload1" type="button" style="width: 100px;" onclick="return ajaxImageUpload(1)" value="上传">
                    	</span>
                	</li>
                	
                	<li class="clearfix">
                    	<label for="introduction" class="tit">视频详情：</label>
                    	<span class="textarea_show" style="display:inline-block; position:relative;">
	                		<textarea class="c_textarea wordCount" name="introduction" cols="" id="introduction" rows="" maxlength="150" showCount="introductionLen"></textarea>
	                		<span class="in_num_text" style="color:red; position:absolute; right:5px; bottom:-5px;" id="introductionLen">0/150</span>
	                	</span>
                    </li>
                	
                	<li class="clearfix">
                		<label for="videoUrl" class="tit">视频：<span class=" red">*</span></label>
                       	<input class="c_input_text required" type="text" style="width:400px;"  readonly name="videoUrl" id="videoUrl" realValue="请输入链接地址"  />
				            <div class="clearfix" style="height:33px;">
								<span id="spanButtonPlaceHolder"></span>
								<input id="btnUpload" type="button" value="上 传" onclick="swfu.startUpload();" style="display:none;" />
								<input id="btnCancel" type="button" value="取消上传" onclick="swfu.cancelUpload();" style="display:none;" />
							</div>
						<span id="fsUploadProgress"></span>
                       	<br/>
                	</li>
					
					<li class="clearfix">
                		<label for="type" class="tit">是否加水印：</label>
               			<span>
               			<input type="checkbox" id="watermark" <#if (1==isWatermark!0)>checked</#if> name="watermark" value="1"/>是否水印
               			</span>
                	</li>
            </ul>

        </div>

    </div>
    <div class="pop_footer">
        <a id="btn_pop_submitA" class="btn_pop_submitA" href="javascript:void(0)">确定</a>
        <a id="btn_pop_submitB" class="btn_pop_submitB" href="javascript:void(0)" onclick="art.dialog.close();">取消</a>
    </div>
    
    <div id="myModal" class="reveal-modal">
		<img id="win_img" class="win-img"/>
		<input type="hidden" id="win_index" value="0"/>
		<input type="button" value="确定" id="win_confirm" />
		<a class="close-reveal-modal" id="close-win">&#215;</a>
	</div>
</form>
<script type="text/javascript">
	var $inputForm = $("#inputForm");
	// 表单验证
	$inputForm.validate({
		rules: {
			title:{
				remote: {
	        		url: "/videocore/repeat-title",
	        		type:"POST"
	        	}
	        }
		},
		messages: {
			title: {
	        		remote: "已存在相同的标题"
	        	}
		},
		submitHandler:function(form){
            form.submit();
        }
	});
	
	$("#btn_pop_submitA").click(function(){
 		$inputForm.submit();
	});
</script>
<script type="text/javascript" src="/js/video/upload.js"></script>
</body>
</html>