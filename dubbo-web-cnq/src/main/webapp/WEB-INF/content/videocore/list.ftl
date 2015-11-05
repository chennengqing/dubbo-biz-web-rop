<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<title>节目列表</title>

<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />
<link rel="stylesheet" type="text/css" href="/js/video-js/video-js.css">
<script src="/js/video-js/video.js"></script>

<script type="text/javascript">
videojs.options.flash.swf = "video-js.swf";
function popImg(imgsrc){		   
	$("#imgview").attr("src",imgsrc);
	showAndMask('pop_map');	
}

function play(url){		   
	$("#paly_id").attr("src",url);
	showAndMask('play');	
}

function closePlay(promptComBar){
	$("#paly_id").attr("src","");
	$("."+ promptComBar).hide();
	$(".promptMask").remove();
}

function callback() {
 	$("#searchButton").click();
}
</script>


</head>
<script type="text/javascript" src="/js/define.js"></script>
<body>
<!-- start of con_right_main -->
<div class="con_right_main">

	<form id="listForm" action="list" method="post">
	
    <!-- start of con_search -->
	<div class="con_search">
    	<span class="con_box_BL"></span>
        
        <!-- start of con_search_top -->
        <div class="con_search_top clearfix">
        	<div class="con_search_top_L left">
                <p>
                	<span class="group"><label>节目：</label>
                    	<select class="c_select" id="programId" name="programId" onchange="loadQuarter()" style="width:150px;">
		                     <option value="">请选择</option>
		                     <#list programList as info>
		                     	<#if info.id == programId!0>
		                     		<option selected value="${info.id}">${info.title}</option>
		                     	<#else>
		                     		<option value="${info.id}">${info.title}</option>
		                     	</#if>
		                     </#list>
		                 </select>
                    </span>
                    <span class="group"><label>季：</label>
                    	<select class="c_select" id="quarterId" name="quarterId" onchange="loadPeriod()" style="width:150px;">
		                     <option value="">请选择</option>
			                     <#list quarterList as info>
			                     	<#if info.id == quarterId!0>
			                     		<option selected value="${info.id}">${info.title}</option>
			                     	<#else>
			                     		<option value="${info.id}">${info.title}</option>
			                     	</#if>
			                     </#list>
		                 </select>
                    </span>
                    <span class="group"><label>期：</label>
                    	<select class="c_select" id="periodId" name="periodId" style="width:150px;">
		                     <option value="">请选择</option>
			                     <#list periodList as info>
			                     	<#if info.id == periodId!0>
			                     		<option selected value="${info.id}">${info.title}</option>
			                     	<#else>
			                     		<option value="${info.id}">${info.title}</option>
			                     	</#if>
			                     </#list>
		                 </select>
                    </span>
                </p>
                <p>
                	<span class="group"><label>标题：</label>
                    	<input name="title" class="c_input_text" type="text" realValue="请输入标题" value="${title!''}"}" style="width:150px;" />
                    </span>
                    <span class="group"><label>类型：</label>
                    	<select class="c_select" id="type" name="type" style="width:150px;">
		                     <option value="">请选择</option>
		                     <option value="0">正片</option>
		                     <option value="1">花絮</option>
		                 </select>
                    </span>
                    <span class="group"><a id="searchButton" href="javascript:;" class="btn_search">搜索</a></span>
                </p>
            </div>
            <div class="con_search_btn right">
                <a class="btnA" href="javascript:;" onclick="openAddFrame('添加视频');">添加视频</a>
            </div>
        </div>
        <!-- end of con_search_top -->
        
        <span class="con_box_BR"></span>
    </div>
    <!-- end of con_search -->
    
    <!-- start of table_list -->
    <table id="listTable" class="table_list list">
        <tr>
        	<th width="2%" >序号</th>
        	<th width="6%" orderField="info.code">节目</th>
        	<th width="6%" orderField="info.code">季</th>
			<th width="6%" orderField="info.code">期</th>
			<th width="6%" orderField="info.code">标题</th>
			<th width="6%" orderField="info.code">图片</th>
			<th width="6%" orderField="info.code">类型</th>
			<th width="6%" orderField="info.code">状态</th>
			<th width="6%" orderField="info.code">观看数</th>
			<th width="6%" orderField="info.code">评论数</th>
			<th width="6%" orderField="info.code">点赞数</th>
			<th width="6%" orderField="info.code">创建者</th>
			<th width="12%">操作</th>
        </tr>
        
        <#list page.list as info>
        <tr class="even">
       
			<td>${info.videoBo.id}</td>
			<td>${(info.program.title)!''}</td>
			<td>${(info.quarter.title)!''}</td>
			<td>${(info.period.title)!''}</td>
			<td>${info.videoBo.title!''}</td>
			<td><img src="${info.videoBo.titleImage!''}" width="30px" height="30px"  onClick="popImg(this.src);"></td>
            <td><#if info.videoBo.type == 1>花絮
                <#elseif info.videoBo.type == 0>正片</#if>
            </td>
            <td><#if info.videoBo.state == 0>启用
            	<#elseif info.videoBo.state == 1>禁用
            	<#elseif info.videoBo.state == 2>上传失败
            	<#elseif info.videoBo.state == 3>上传中
            	<#elseif info.videoBo.state == 4>转码失败
            	</#if>
            </td>
            <td>${info.videoBo.watchNum!''}</td>
            <td>${info.videoBo.commentNum!''}</td>
            <td>${info.videoBo.praiseNum!''}</td>
            <td>${info.videoBo.creator!''}</td>
            <td>
				<a class="btn_icon btn_edit"   href="javascript:;" operatId="${info.videoBo.id}" title="编辑"></a>
                <a class="btn_icon btn_detail" href="javascript:;" operatId="${info.videoBo.id}" title="详情"></a>
                <a class="btn_icon btn_delete" href="javascript:;" operatId="${info.videoBo.id}" title="删除"></a>
                <#if info.videoBo.state == 0>
                <a href="javascript:play('${info.videoBo.videoUrl}');">播放</a>     
                </#if>             
			</td>
        </tr>
        </#list>
        
    </table>
    <!-- end of table_list -->
    
    
    <#if (page.list?size > 0)>
    
    	<!-- start of table_bottom -->
	    <div class="table_bottom clearfix">
	    	<div class="table_bottom_checkbox left">
	    		<!--<input id="selectAll" name="" type="checkbox" value=""><a class="btn" href="#">删除选中</a>-->
	    	</div>
	        
	         <!-- start of 分页 -->
	   		<@paging pageNumber = page.pageNo totalPages = page.totalPage>
				<#include "../commons/pager.ftl">
			</@paging>
	        <!-- end of 分页 -->
	    </div>
	    <!-- end of table_bottom -->
			
	<#else>
		<div class="noRecord">没有找到任何记录!</div>
	</#if>
			
			
    </form>
</div>
<!-- end of con_right_main -->

<div class="pop_box pop_map" style="width:600px;">
	<div class="pop_header">
    	<h3>查看</h3>
    	<a class="btn_pop_close" href="javascript:closeAndMask('pop_map')">关闭</a>
    </div>
    <div class="pop_main">
    	<div class="pop_map_show"><img src="" id="imgview"></div>
    </div>
    <div class="pop_footer">
       <a class="btn_pop_submitA" href="javascript:closeAndMask('pop_map')">关闭</a>
    </div>
</div>
<div class="pop_box play" id="play_div" style="width:600px;"">
	<div class="pop_header">
    	<h3>查看</h3>
    	<a class="btn_pop_close" href="javascript:closePlay('play')">关闭</a>
    </div>
    <div class="pop_main">
	    	<!-- <embed id="paly_id" src="" width="100%" height="400" loop="false" autostart="false"></embed> -->
	            <video id="paly_id" class="video-js vjs-default-skin" controls preload="auto" width="100%" height="400" autobuffer poster="http://video-js.zencoder.com/oceans-clip.png"  data-setup='{'example_option':false}'>
				<source src="http://zhitx-yaya-bucket.bj.bcebos.com/0836a85d-9467-4e1a-87fd-0b570a98fedd.mp4" type="video/mp4" />
				<source src="" type="video/rmvb" />
				<source src="" type="video/mpeg" />
				<source src="" type="video/avi" />
				<source src="" type="video/mpg" />
				<source src="" type="video/rm" />
				<source src="" type="video/rmvb" />
				<source src="" type="video/wmv" />
				<source src="" type="video/mov" />
				<source src="" type="video/3gp" />
	               
				<!-- 如果浏览器不兼容HTML5则使用flash播放 -->                    
				<object id="sample_video" class="vjs-flash-fallback" width="852" height="480" type="application/x-shockwave-flash" 
					data="http://releases.flowplayer.org/swf/flowplayer-3.2.1.swf">
				 	<param name="movie" value="http://releases.flowplayer.org/swf/flowplayer-3.2.1.swf" />
				 	<param name="allowfullscreen" value="true" />
				 	<param name="flashvars" value='config={"playlist":["images/sample_video_poster.png", {"url": "videos/sample_video.mp4","autoPlay":false,"autoBuffering":true}]}' /> 
				 	
				 	<img src="images/sample_video.png" width="852"
				 	height="480" alt="Poster Image" 
				 	title="No video playback capabilities." />
				</object> 
			</video>
    </div>
 </div>
<script type="text/javascript" src="/js/video/video.js"></script>
</body>
</html>