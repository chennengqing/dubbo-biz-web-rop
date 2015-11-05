<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<title>查看视频详情</title>
<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />


</head>

<body>
	
    <div id="auditTab" class="pop_main" style="width:600px;border: 0px solid;">

       <div class="pop_grouprmation_mod">
            <ul class="pop_list merchant_type_add">
            
                	<li class="clearfix">
                		<label for="title require" class="tit">节目：<span class=" red">*</span></label>
                		<span>${(video.programTitle)!''}</span>
               		</li>
               		
               		<li class="clearfix">
                		<label for="title require" class="tit">季：<span class=" red">*</span></label>
                		<span>${(video.quarterTitle)!''}</span>
               		</li>
					
					<li class="clearfix">
                		<label for="title require" class="tit">期：<span class=" red">*</span></label>
                		<span>${(video.periodTitle)!''}</span>
               		</li>
                	
                	<li class="clearfix">
                		<label for="title require" class="tit">标题：<span class=" red">*</span></label>
                		<span>${(video.title)!''}</span>
               		</li>
				               		
                	<li class="clearfix">
                		<label for="type" class="tit">类型：<span class=" red">*</span></label>
               			<span><#if video.type == 1>花絮</#if>
                		<#if video.type == 0>正片</#if></span>
                	</li>
                	
                	<li class="clearfix">
                		<label for="titleImage" class="tit">图片：<span class=" red">*</span></label>
              			<img src="${(video.titleImage)!''}" id="image_title1" width="120" height="90" />
                	</li> 
                	
                	<li class="clearfix">
                		<label for="videoUrl" class="tit">视频：<span class=" red">*</span></label>
                       	<span>${(video.videoUrl)!''}</span>
                	</li>
					
                	<li class="clearfix">
                		<label for="state" class="tit">状态：<span class=" red">*</span></label>
               			<span>
               			<#if video.state == 0>启用
		            	<#elseif video.state == 1>禁用
		            	<#elseif video.state == 2>上传失败
		            	<#elseif video.state == 3>上传中
		            	<#elseif video.state == 4>转码失败
		            	</#if>
            	</span>
                	</li> 
                	
                	<li class="clearfix">
                		<label for="type" class="tit">是否加水印：<span class=" red">*</span></label>
               			<span>
               			<input type="checkbox" disabled <#if (1==video.isWatermark!0)>checked</#if> value="1"/>是否水印
               			</span>
                	</li>
            </ul>

        </div>

    </div>
    <div class="pop_footer">
        <a id="btn_pop_submitB" class="btn_pop_submitB" href="javascript:void(0)" onclick="art.dialog.close();">取消</a>
    </div>




</body>
</html>