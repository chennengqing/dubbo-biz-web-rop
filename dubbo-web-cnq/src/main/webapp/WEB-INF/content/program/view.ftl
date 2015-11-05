<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<title>查看节目详情</title>
<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />


</head>

<body>
	
    <div id="auditTab" class="pop_main" style="width:600px;border: 0px solid;">

       <div class="pop_grouprmation_mod">
            <ul class="pop_list merchant_type_add">
            
                	<li class="clearfix">
                		<label for="title require" class="tit">节目名称：<span class=" red">*</span></label>
                		<span>${(program.title)!''}</span>
               		</li>

                	
                	<li class="clearfix">
                		<label for="sort" class="tit">排序：<span class=" red">*</span></label>
                		<span>${(program.sort)!''}</span>
                	</li>
                	
                	<li class="clearfix">
                		<label for="titleImage" class="tit">图片：<span class=" red">*</span></label>
              			<img src="${(program.titleImage)!''}" id="image_title1" width="120" height="90" />
                	</li> 
                	
					<li class="clearfix">
                		<label for="state" class="tit">状态：<span class=" red">*</span></label>
               			<#if program.state == 1>启用</#if>
                		<#if program.state == 0>停用</#if>
                	</li>
                	
            </ul>

        </div>

    </div>
    <div class="pop_footer">
        <a id="btn_pop_submitB" class="btn_pop_submitB" href="javascript:void(0)" onclick="art.dialog.close();">取消</a>
    </div>




</body>
</html>