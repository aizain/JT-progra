<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemParamList" title="商品规格列表"
       data-options="singleSelect:false, collapsible:true,
       pagination:true, url:'/item/param/list', method:'get',
       pageSize:30, toolbar:toolbar">
    <thead>
        <tr>
            <th data-options="field:'ck', checkbox:true"></th>
            <th data-options="field:'id', width:60">ID</th>
            <th data-options="field:'itemCatId', width:80">商品类目ID</th>
            <th data-options="field:'itemCatName', width:100">商品类目</th>
            <th data-options="field:'paramData', width:300, formatter:formatItemParamData">规格（只显示分组名称）</th>
            <th data-options="field:'created', width:130, align:'center', formatter:KindEditorUtil.formatDateTime">创建日期</th>
            <th data-options="field:'updated', width:130, align:'center', formatter:KindEditorUtil.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<!-- 此处未实现，暂时无用 zain 16/10/16 -->
<div id="itemEditWindow" class="easyui-window" title="编辑商品规格" 
     data-options="modal:true ,closed:true, iconCls:'icon-save', href:'/page/item-edit'"
     style="width:500;height:450;">
</div>
<script>

	//格式化数据
	function formatItemParamData(value, index) {
	    var json = JSON.parse(value);
	    var array = [];
	    $.each(json, function(i, e) {
	        array.push(e.group);
	    });
	    return array.join(",");
	}

	
	function getSelectionsIds() {
	    var itemList = $("#itemParamList");
	    var sels = itemList.datagrid("getSelections"); //easyUI提供，获取用户节点对象集合
	    var ids = [];
	    for(var i in sels) {
	        ids.push(sels[i].id);
	    }
	    ids = ids.join(","); //将集合转为逗号连接字符串
	    return ids;
	}
	
	var toolbar = [{
	    text : "新增",
	    iconCls : "icon-add",
	    handler : function() {
	        KindEditorUtil.createWindow({
	            url : "/page/item-param-add",
	        });
	    }
	}, {
	    text : "编辑",
	    iconCls : "icon-edit",
	    handler : function() {
			alert("该功能未实现");	// TODO 此处不再补全 zain 16/10/16
	    } // handler over
	}, { 
	        text : "删除",
	        iconCls : "icon-cancel",
	        handler : function() {
	            var ids = getSelectionsIds();
	            if(ids.length == 0) {
	                alert("未选中商品！");
	                return;
	            }
	            //$.messager.confirm("确认", "确定删除ID为" + ids + "的商品规格吗？", function(r) {
	                if(true) {
	                    var params = {"ids" : ids};
	                    $.post("/item/param/delete", params, function(data) {
	                        if(200 == data.status) {
	                          	//$.message.alert("提示", "删除商品规格成功！", undefined, function() {
		                        //});
		                        alert("删除商品规格成功！");
		                        $("#itemParamList").datagrid("reload");
	                        }
	                    });
	                } // if over
	            //}); 
	        } // handler over
	}, 
];
	
	
</script>