Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, //month	
		"d+" : this.getDate(), //day	
		"h+" : this.getHours(), //hour	
		"m+" : this.getMinutes(), //minute	
		"s+" : this.getSeconds(), //second	
		"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter	
		"S" : this.getMilliseconds(), //millisecond	
	};
	if(/(y+)/i.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}

	for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		} 
	} 
	return format; 
}


$(function() {
	$(".tree-node").click(initItemCat);
	
});

function initItemCat() {
	var data;
	$(".selectItemCat").each(function(i, e) {
		var _ele = $(e);
		if(data && data.cid) {
			_ele.after("<span style='margin-left:10px';>" + data.cid + "</span>");
		} else {
			_ele.after("<span style='margin-left:10px';></span>");
		}
		_ele.unbind('click').click(function() {
			$("<div>").css({padding:'5px'}).html("<ul>")
			.window({
				width:'500',
				height:'450',
				modal:true,
				closed:true,
				iconCls:'icon-save',
				title:'选择类目',
				onOpen : function() { //当窗口打开后执行
					var _win = this;
					$("ul", _win).tree({
						url:'/item/cat/list',
						animate:true,
						onClick : function(node) {
							if($(this).tree("isLeaf", node.target)) { //只能选叶子节点（即小类）
								//填写到cid中
								_ele.parent().find("[name=cid]").val(node.id);
								_ele.next().text(node.text).attr("cid", node.id);
								$(_win).window('close');
								if(data && data.fun) {
									data.fun.call(this, node);
								}
							}
						}
					});
				}, // onOpen over
				onClose : function() {
					$(this).window("destroy");
				}
			}).window("open"); // window over
		}); // click over
	}); // each over
}



