<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<title>添加节目</title>
<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />


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
</head>
<body id="add_body">
<form id="inputForm" method="post" action="save">
    <div id="auditTab" class="pop_main" style="width:600px;border: 0px solid;">

       <div class="pop_grouprmation_mod">
            <ul class="pop_list merchant_type_add">
                	<li class="clearfix">
                		<label for="title require" class="tit">节目名称：<span class=" red">*</span></label>
                		<input class="c_input_text required" type="text" style="width:200px;" name="title" id="title" realValue="请输入节目名称" maxlength="32" />
               		</li>
               		
               		<li class="clearfix">
                		<label for="sort require digits" class="tit">排序：<span class=" red">*</span></label>
                		<input class="c_input_text required digits" type="text" style="width:200px;" name="sort" id="sort" value="0" realValue="请输入排序" maxlength="32" />
               		</li>
               		
                	<li class="clearfix">
                		<label for="titleImage" class="tit">图片：</label>
						<span>
                        <img src="" id="image_title1" width="120" height="90" />
                        <input type="hidden" id="image_value1"/>
                        <input type="button" value="删除1" id="title_del1" onclick="title_image_del(1)"/>
                        <br/>
                       	<input class="c_input_text" type="hidden" id="titleImage"  name="titleImage"/>
                       	<input type="hidden" value="${imageServer }" id="imageServer" name="imageServer" />
                       	<input id="imageUpload" type="file" size="45" name="imageUpload" class="input">
                       	<input id="btnupload1" type="button" style="width: 100px;" onclick="return ajaxImageUpload(1)" value="上传1">
                    	</span>
                	</li>
                	
                	
                	<li class="clearfix">
                		<label for="state" class="tit">状态：<span class=" red">*</span></label>
               			<input type="radio" name="state" value="1" checked="checked"/>启用
                		<input type="radio" name="state" value="0" />停用
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
	        		url: "/program/repeat-title"
	        	}
	        }
		},
		messages: {
			title: {
	        		remote: "已存在相同的名称"
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