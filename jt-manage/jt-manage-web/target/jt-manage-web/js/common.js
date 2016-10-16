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


//KindEditorUtil内部方法
var TT = KindEditorUtil = {    //相当于java中定义的工具类，里面提供了很多静态方法
        //编辑器参数
        kingEditorParams : {
            filePostName : "uploadFile",
            uploadJson : "/pic/upload",
            dir : "image"
        },
        
        //格式化时间
        formatDateTime : function(val, row) {
            var now = new Date(val);
            return now.format("yyyy-MM-dd hh:mm:ss");
        },
        
        //格式化连接
        formatUrl : function (val, row) {
            if(val) {
                return "<a href='" + val + "' target='_blank'>查看</a>";
            }
            return "";
        },
        
        //格式化价格
        formatPrice : function (val, row) {
            return (val/100).toFixed(2);
        },
        
        //格式化商品的状态
        formatItemStatus : function formatStatus(val, row) {
            if(val == 1) {
                return "正常";
            } else if(val == 2) {
                return "<span style='color:red;'>下架</span>";
            } else {
                return "未知";
            }
        },
        
        init : function(data) {
            this.initPicUpload(data);
            this.initItemCat(data);
        },
        
        //初始化图片上传组件
        initPicUpload : function(data) {
            $(".picFileUpload").each(function(i, e) {
                var _ele = $(e);
                _ele.siblings("div.pices").remove();
                _ele.after("\<div class='pics'>\<ul></ul>\</div>");
                
                //回显图片
                if(data && data.pics) {
                    var imgs = data.pics.split(",");
                    for(var i in imgs) {
                        if($.trim(imgs[i]).length > 0) {
                            _ele.siblings(".pics").find("ul").append("<li><a href='" + imgs[i] + "' target='_blank'><img src='" + imgs[i] + "' width='80' height='50'></a></li>");
                        }
                    }
                } // if over
                $(e).click(function() {
                    var form = $(this).parentsUntil("form").parent("form");
                    KindEditor.editor(TT.kingEditorParams).loadPlugin("multiimage", function() {
                        var editor = this;
                        editor.plugin.multiImageDialog({
                            clickFn : function(urlList) {
                                var imgArray = [];
                                KindEditor.each(urlList, function(i, data) {
                                    imgArray.push(data.url);
                                    form.find(".pics ul").append("<li><a href='" + data.url + "' target='_blank'><img src='" + data.url + "' width='80' height='50'></a></li>");
                                });
                                //利用KindEditor弹出文件选择框，拿到本地图片路径，将它们拼接起来
                                form.find("[name=image]").val(imgArray.join(","));
                                editor.hideDialog();
                            }
                        }); 
                    }); // KindEditor.editor over
                }); // $(e).click over
            }); // $(".picFileUpload").each over
        }, // initPicUpload over
        
        //初始化商品类目
        initItemCat : function(data) {
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
        }, // initItemCat over
        
        /**
         * 创建富文本编辑框
         */
        createEditor : function(select) {
            return KindEditor.create(select, TT.kingEditorParams);
        }, // createEditor over
        
        /**
         * 创建一个窗口，关闭窗口后销毁该窗口对象。<br/>
         * 
         * 默认: <br/>
         * width : 80% <br/>
         * height : 80% <br/>
         * title : (空字符串) <br/>
         * 
         * 参数: <br/>
         * width : <br/>
         * height : <br/>
         * title : <br/>
         * url : 必填参数 <br/>
         * onLoad : function 加载完窗口后执行 <br/>
         * 
         */
        createWindow : function(params) {
            $("<div>").css({padding : "5px"}).window({
                width : params.width ? params.windth : "80%",
                height : params.height ? params.height : "80%",
                modal : true,
                href : params.url,
                onClose : function() {
                    $(this).window("destroy");
                },
                onLoad : function() {
                    if(params.onLoad) {
                        params.onLoad.call(this);
                    }
                }
                
            }).window("open");
        }, // createWindow over
        
        closeCurrentWindow : function() {
            $(".panel-tool-close").click();
        },
        
        //切换商品规格页面
        changeItemParam : function(node, formId) {
            $.getJSON("/item/param/query/itemcatid/" + node.id, function(data) {
                if(200 == data.status && data.data) {
                    $("#" + formId + " .params").show();
                    console.log(data.data.paramData);
                    var paramData = JSON.parse(data.data.paramData);
                    var html = "<ul>";
                    for(var i in paramData) {
                        var pd = paramData[i];
                        html += "<li><table>";
                        html += "<tr><td colspan=\"2\" class=\"group\">" + pd.group + "</td></tr>";
                        
                        for(var j in pd.params) {
                            var ps = pd.params[j];
                            html += "<tr><td class=\"param\"><span>" + ps + "</span>: </td>"
                                       +"<td><input autocomplete=\"off\" type=\"text\"/></td></tr>";
                        }
                        
                        html += "</li></table>";
                    }
                    html += "<ul>";
                    $("#" + formId + " .params td").eq(1).html(html);
                } else {
                    $("#" + formId + " .params").hide();
                    $("#" + formId + " .params").eq(1).empty();
                }
            }); // $.getJSON over
        }, // changeItemParam over
        
} // KindEditorUtil over










