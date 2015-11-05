<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<title>节目列表</title>

<#include "../commons/page_css.ftl" />
<#include "../commons/page_js.ftl" />

<script type="text/javascript">

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
                    	<select class="c_select" id="quarterId" name="quarterId" style="width:150px;">
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
                    	<input name="title" class="c_input_text" type="text" realValue="输入期名称" value="${title!''}"}" style="width:150px;" />
                    </span>
                    <span class="group"><a id="searchButton" href="javascript:;" class="btn_search">搜索</a></span>
                </p>
            </div>
            <div class="con_search_btn right">
                <a class="btnA" href="javascript:;" onclick="openAddFrame('添加期');">添加期</a>
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
			<th width="6%" orderField="info.code">排序</th>
			<th width="6%" orderField="info.code">状态</th>
			<th width="6%" orderField="info.code">创建时间</th>
			<th width="6%" orderField="info.code">创建者</th>
			<th width="10%">操作</th>
        </tr>
        
        <#list page.list as info>
        <tr class="even">
       
			<td>${info.id}</td>
			<td>${info.programTitle!''}</td>
			<td>${info.quarterTitle!''}</td>
			<td>${info.title!''}</td>
			<td>${info.sort!''}</td>
            <td><#if info.state == 1>启用</#if>
                <#if info.state == 0>禁用</#if>
            </td>
            <td>${(info.createTime?string('yyyy-MM-dd'))!''}</td>
            <td>${info.creator!''}</td>
            <td>
				<a class="btn_icon btn_edit"   href="javascript:;" operatId="${info.id}" title="编辑"></a>
                <a class="btn_icon btn_detail" href="javascript:;" operatId="${info.id}" title="详情"></a>
                <a class="btn_icon btn_delete" href="javascript:;" operatId="${info.id}" title="删除"></a>                
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
<script type="text/javascript" src="/js/video/period.js"></script>
</body>
</html>