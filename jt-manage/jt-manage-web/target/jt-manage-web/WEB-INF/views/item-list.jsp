<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="商品列表"
       data-options="singleSelect:false, collapsible:true,
       pagination:true, url:'/item/query', method:'get',
       pageSize:30, toolbar:toolbar">
    <thead>
        <tr>
            <th data-options="field:'ck', checkbox:true"></th>
            <th data-options="field:'id', width:60">商品ID</th>
            <th data-options="field:'title', width:200">商品标题</th>
            <th data-options="field:'cid', width:100">叶子目录</th>
            <th data-options="field:'sellPoint', width:100">卖点</th>
            <th data-options="field:'price', width:70, align:'right', formatter:KindEditorUtil.formatPrice">价格</th>
            <th data-options="field:'num', width:70, align:'right'">库存数量</th>
            <th data-options="field:'barcode', width:100">条形码</th>
            <th data-options="field:'status', width:60, align:'center', formatter:KindEditorUtil.formatItemStatus">状态</th>
            <th data-options="field:'created', width:130, align:'center', formatter:KindEditorUtil.formatDateTime">创建日期</th>
            <th data-options="field:'updated', width:130, align:'center', formatter:KindEditorUtil.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>

<div id="itemEditWindow" class="easyui-window" title="编辑商品" 
     data-options="modal:true ,closed:true, iconCls:'icon-save', href:'/page/item-edit'"
     style="width:500;height:450;">
</div>
<script>
	
	function getSelectionsIds() {
	    var itemList = $("#itemList");
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
	        $(".tree-title:contains('新增商品')").parent().click();
	    }
	}, {
	    text : "编辑",
	    iconCls : "icon-edit",
	    handler : function() {
	        var ids = getSelectionsIds();
	        if(ids.length == 0) {
	            alert("必须选择一个商品才能编辑！");
	            return;
	        }
	        if(ids.indexOf(",") > 0) {
	            alert("只能选择一个商品！");
	            return;
	        }
	        
	        $("#itemEditWindow").window({
	            onLoad : function() {
	                //回显数据，没有查询数据库，直接从列表中获取所需数据，性能高。可能造成数据不一致问题。有需要的话可以换成后台数据查找
	                var data = $("#itemList").datagrid("getSelections")[0]; //后面这个中括号0这个方法可以获取jQuery对象中的dom对象，很好使！zain
	            	data.priceView = KindEditorUtil.formatPrice(data.price);
	            	$("#itemEditForm").form("load", data);
	            	
	            	//加载商品
	            	$.getJSON("/item/query/item/desc/" + data.id, function(_data) {
	            	    if(_data.status == 200) {
	            	        //itemEditEditor.html(_data.data.itemDesc);
	            	    }
	            	});
	            	
	            	//加载商品规格
	            	$.getJSON("/item/param/item/query" + data.id, function(_data) {
	            	    if(_data && _data.status == 200 && _data.data && _data.data.paramData) {
	            	        $("#itemEditForm .params").show();
	            	        $("#itemEditForm [name=itemParams]").val(_data.data.paramData);
	            	        $("#itemEditForm [name=itemParamId]").val(_data.data.id);
	            	        
	            	        //回显商品规格
	            	        var paramData = JSON.parse(_data.data.paramData);
	            	        
	            	        var html = "<ul>";
	            	        for(var i in paramData) {
	            	            var pd = paramData[i];
	            	            html += "<li><table>";
	            	            html += "<tr><td colspan=\"2\" class=\"group\">" + pd.group;
	            	            
	            	            for(var j in pd.params) {
	            	                var ps = pd.params[j];
	            	                html += "<tr><td class=\"param\"><span>" + ps.k + "</span>";
	            	            } 
	            	            
	            	            html += "</li></table>";
	            	        }
	            	        html += "</ul>";
	            	        $("#itemEditForm .params td").eq(1).html(html);
	            	    } // if over
	            	});  // $.getJson over
	            	
	            	KindEditorUtil.init({
	            	    "pics" : data.image,
	            	    "cid" : data.cid,
	            	    fun : function(node) {
	            	        KindEditorUtil.changeItemParam(node,  "itemEditForm");
	            	    }
	            	});
	            	
	            } // onLoad over
	        }).window("open"); // $("#itemEditWindow").window over
	    } // handler over
	}, { 
	        text : "删除",
	        iconCls : "icon-cancel",
	        handler : function() {
	            var ids = getSelectionsIds();
	            if(ids.length == 0) {
	                return;
	            }
	        }
	    
	}];
	
	
</script>