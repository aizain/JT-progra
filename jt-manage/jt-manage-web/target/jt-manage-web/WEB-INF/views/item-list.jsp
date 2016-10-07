<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="商品列表"
       data-options="singleSelect:false, collapsible:true,
       pagination:true, url:'/item/query', method:'get',
       pageSize:30">
       <!-- , toolbar:toolbar -->
    <thead>
        <tr>
            <th data-options="field:'ck', checkbox:true"></th>
            <th data-options="field:'id', width:60">商品ID</th>
            <th data-options="field:'title', width:200">商品标题</th>
            <th data-options="field:'cid', width:100">叶子目录</th>
            <th data-options="field:'sellPoint', width:100">卖点</th><!-- formatter:KindeEditorUtil.formatPrice -->
            <th data-options="field:'price', width:70, align:'right' ">价格</th>
            <th data-options="field:'num', width:70, align:'right'">库存数量</th>
            <th data-options="field:'barcode', width:100">条形码</th><!-- formatter:KindeEditorUtil.formatItemStatus --><!-- formatter:KindeEditorUtil.formatDateTime -->
            <th data-options="field:'status', width:60, align:'center'">状态</th>
            <th data-options="field:'created', width:130, align:'center'">创建日期</th>
            <th data-options="field:'updated', width:130, align:'center'">更新日期</th>
        </tr>
    </thead>
</table>

<div id="itemEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true ,closed:true, iconCls:'icon-save', href:'/page/item-edit'">
</div>
<script>
	
	function getSelectionIds() {
	    var itemList = $("itemList");
	    var sels = itemList.datagrid("getSelections");
	    var ids = [];
	    for(var i in sels) {
	        ids.push(sels[i].id);
	    }
	    ids = ids.join(",");
	    return ids;
	}
	
</script>