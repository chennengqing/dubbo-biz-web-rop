<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<title>查看期详情</title>
<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />


</head>

<body>
	
    <div id="auditTab" class="pop_main" style="width:600px;border: 0px solid;">

       <div class="pop_grouprmation_mod">
            <ul class="pop_list merchant_type_add">
            
                	<li class="clearfix">
                		<label for="title require" class="tit">节目：<span class=" red">*</span></label>
                		<span>${(period.programTitle)!''}</span>
               		</li>
               		
               		<li class="clearfix">
                		<label for="title require" class="tit">季：<span class=" red">*</span></label>
                		<span>${(period.quarterTitle)!''}</span>
               		</li>
					
					<li class="clearfix">
                		<label for="title require" class="tit">期：<span class=" red">*</span></label>
                		<span>${(period.title)!''}</span>
               		</li>
                	
                	<li class="clearfix">
                		<label for="sort" class="tit">排序：<span class=" red">*</span></label>
                		<span>${(period.sort)!''}</span>
                	</li>
                	
					<li class="clearfix">
                		<label for="state" class="tit">状态：<span class=" red">*</span></label>
               			<#if period.state == 1>启用</#if>
                		<#if period.state == 0>停用</#if>
                	</li>    
            
                	
            </ul>

        </div>

    </div>
    <div class="pop_footer">
        <a id="btn_pop_submitB" class="btn_pop_submitB" href="javascript:void(0)" onclick="art.dialog.close();">取消</a>
    </div>




</body>
</html>