<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<title>添加节目</title>
<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />

<script src="/js/jQuery/validate/jquery.validate.js" type="text/javascript"></script>
<link href="/js/jQuery/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="/js/define.js"></script>
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
                		<label for="quarterId require" class="tit">季：<span class=" red">*</span></label>
                		<select class="c_select required" id="quarterId" name="quarterId" style="width:215px;">
		                     <option value="">请选择</option>
		                 </select>
               		</li>
               		
               		<li class="clearfix">
                		<label for="title require" class="tit">期：<span class=" red">*</span></label>
                		<input class="c_input_text required" type="text" style="width:200px;" name="title" id="title" realValue="请输入期名称" maxlength="32" />
               		</li>
               		
               		<li class="clearfix">
                		<label for="sort require digits" class="tit">排序：<span class=" red">*</span></label>
                		<input class="c_input_text required digits" type="text" style="width:200px;" name="sort" id="sort" value="0" realValue="请输入排序" maxlength="32" />
               		</li>
               		
                	<li class="clearfix">
                		<label for="state" class="tit">状态：<span class=" red">*</span></label>
               			<input type="radio" name="state" checked="checked" value="1" />启用
                		<input type="radio" name="state" value="0" />停用
                	</li>

            </ul>

        </div>

    </div>
    <div class="pop_footer">
        <a id="btn_pop_submitA" class="btn_pop_submitA" href="javascript:void(0)">确定</a>
        <a id="btn_pop_submitB" class="btn_pop_submitB" href="javascript:void(0)" onclick="art.dialog.close();">取消</a>
    </div>
</form>
<script type="text/javascript">
	var $inputForm = $("#inputForm");
	// 表单验证
	$inputForm.validate({
		rules: {
			title:{
				remote: {
	        		url: "/period/repeat-title",
	        		type:"POST",
               		data:{
                 		programId:function(){return $("#programId").val();},
                 		quarterId:function(){return $("#quarterId").val();}
               		} 
	        	}
	        }
		},
		messages: {
			title: {
	        		remote: "已存在相同的季名称"
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
<script type="text/javascript" src="/js/video/period.js"></script>
</body>
</html>