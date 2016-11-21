<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div>
    <ul id="contentCategory" class="easyui-tree"></ul>
</div>
<div id="contentCategoryMenu" class="easyui-menu" style="width: 120px;" data-options="onClick:menuHandler">
    <div data-options="iconCls:'icon-add', name:'add'">添加</div>
    <div data-options="iconCls:'icon-remove', name:'rename'">重命名</div>
    <div class="menu-sep"></div>
    <div data-options="iconCls:'icon-remove', name:'delete'">删除</div>
</div>
<script type="text/javascript">
	$(function() {
	    $("#contentCategory").tree({
	        url: "/content/category/list", // 访问数据库查询
	        animate: true,
	        method: "GET",
	        onContextMenu: function(e, node) { // 触发右键菜单
	            e.preventDefault();
	            $(this).tree("select", node.target);
	            $("#contentCategoryMenu").menu("show", {
	                left: e.pageX,
	                top: e.pageY
	            });
	            
	        },
	        onAfterEdit: function(node) {
	            var _tree = $(this);
	            if(node.id == 0) {
	                // 新增节点
	                $.post("/content/category/create", {parentId:node.parentId, name:node.text}, function(data) {
	                    if(data.status == 200) {
		                    _tree.tree("update", {
		                        target: node.target,
		                        id: data.data.id
		                    });
		                } else {
		                    alert("创建" + node.text + "分类失败！");
		                }
	                });
	            } else {
	                $.post("/content/category/update", {id:node.id, name:node.text});
	            }
	        }
	    });
	});
	
	function menuHandler(item) {
	    var tree = $("#contentCategory");
	    var node = tree.tree("getSelected");
	    if(item.name == "add") {
	        tree.tree("append", {
	            parent: (node?node.target:null),
	            data: [{
	                text: "新建分类",
	                id: 0,
	                parentId: node.id
	            }]
	        });
	        var _node = tree.tree("find", 0);
	        tree.tree("select", _node.target).tree("beginEdit", _node.target);
	    } else if(item.name == "rename") {
	        tree.tree("beginEdit", node.target);
	    } else if(item.name == "delete") {
	        alert("确定删除名为" + node.text + "的分类吗？");
	        $.post("/content/category/delete/", {parentId:node.parentId, id:node.id}, function() {
	            tree.tree("remove", node.target);
	        });
	    }
	    
	}
	
</script>