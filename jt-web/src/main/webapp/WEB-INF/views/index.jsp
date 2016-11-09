<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset"/>
    <title>京淘网上商城-综合网购首选(JT.COM)-正品低价、品质保障、货到付款、及时配送、放心服务、轻松购物！</title>
    <meta name="description" content="京淘JT.COM-专业的综合网上购物商城，在线销售家电、数码通讯、电脑、家具"/>
    <meta name="Keywords" content="网上购物,网上商城,手机,笔记本,电脑,MP3,CD,VCD,相机,数码,配件,平板"/>
    <link type="/css/jt.css" rel="stylesheet"/>
    <script type="text/javascript">
    	window.pageConfig = {
    		compatible: true,
    		navId: "home",
    		enableArea: true
    	}
    </script>
    <style type="text/css">
        #categorys-2013 .mc {
            display: block;
        }
        #categorys-2013 .mt {
            background: 0;
        }
    </style>
</head>
<body>
    <!-- header start -->
    <jsp:include page="../commons/header.jsp"></jsp:include>
    <!-- header end -->
    
    <div class="w">
    <div id="o-slide">
    <div class="slide" id="slide">
        <script type="text/javascript">
        	;(function(cfg, doc) {
        	    if(!cfg.DATA_MSlide) {
        	        cfg.DATA_MSlide=[];
        	    }
        	    var data = $(indexAD1);
        	    
        	    cfg.DATA_MSlide = data;
        	    
        	    //初始化一个广告信息
        	    if(cfg.DATA_MSlide.length > 5) {
        	        var first = pageConfig.FN_GetCompatibleData(cfg.DATA_MSlide[0]);
        	        var TPL = ''
        	        	+ '<ul class="slide-items">'
        	        	+ '<li clstag="homepage|keycount|home2013|09a1">'
        	        	+ '<a href="' + first.href + '" target="_blank" title="' + first.alt + '">'
        	        	+ '<img src="' + first.src + '" width="' + first.width + '" height="' + first.height + '">'
        	        	+ '</a>'
        	        	+ '</li>'
        	        	+ '</ul>'
        	        	+ '<div class="slide-controls"><span class="curr">1</span></div>';
        	        	doc.write(TPL);
        	    }
        	}) (pageConfig, document);;
        </script>
    </div><!-- slide end -->
    
    <div class="jscroll" id="mscroll">
        <div class="ctrl" id="mscroll-ctrl-prev"><b></b></div>  
        <div class="ctrl" id="mscroll-ctrl-next"><b></b></div>   
        <div class="o-list">
            <div class="list" id="mscroll-list"></div>
        </div> 
    </div><!-- mscroll end -->
    
    <script type="text/javascript">
    	pageConfig.DATA_MScroll = [
    		{
    		    "alt" : "",
    		    "href" : "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4127&unit=36312",
    		    "index" : 0,
    		    "src" : "http://img11.360buyimg.com/",
    		    "ext" : ""
    	    
    		}, 
    		{
    		    "alt" : "",
    		    "href" : "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4127&unit=36312",
    		    "index" : 1,
    		    "src" : "http://img11.360buyimg.com/",
    		    "ext" : ""
    	    
    		}, 
    		{
    		    "alt" : "",
    		    "href" : "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4127&unit=36312",
    		    "index" : 2,
    		    "src" : "http://img11.360buyimg.com/",
    		    "ext" : ""
    	    
    		}, 
    		{
    		    "alt" : "",
    		    "href" : "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4127&unit=36312",
    		    "index" : 3,
    		    "src" : "http://img11.360buyimg.com/",
    		    "ext" : ""
    	    
    		}, 
    		{
    		    "alt" : "",
    		    "href" : "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4127&unit=36312",
    		    "index" : 4,
    		    "src" : "http://img11.360buyimg.com/",
    		    "ext" : ""
    	    
    		}, 
    		{
    		    "alt" : "",
    		    "href" : "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4127&unit=36312",
    		    "index" : 5,
    		    "src" : "http://img11.360buyimg.com/",
    		    "ext" : ""
    	    
    		}, 
    		{
    		    "alt" : "",
    		    "href" : "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4127&unit=36312",
    		    "index" : 6,
    		    "src" : "http://img11.360buyimg.com/",
    		    "ext" : ""
    	    
    		}, 
    		{
    		    "alt" : "",
    		    "href" : "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4127&unit=36312",
    		    "index" : 7,
    		    "src" : "http://img11.360buyimg.com/",
    		    "ext" : ""
    	    
    		}, 
    		{
    		    "alt" : "",
    		    "href" : "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=4127&unit=36312",
    		    "index" : 8,
    		    "src" : "http://img11.360buyimg.com/",
    		    "ext" : ""
    		}
    	];
    	
    	(function(object, data) {
    	    var a = data, b = [], c = [], d, h;
    	    a.sort(function(a, b) {
    	        return a.ext - b.ext
    	    });
    	    while(a.length > 0) {
    	        d = a.shift();
    	        if(d.ext) {
    	            b.push(b);
    	        } else {
    	            c.push(d);
    	        }
    	    }
    	    c.sort(function() {
    	        return 0.5 - Math.random();
    	    });
    	    h = b.length;
    	    if(h >= 3) {
    	        for(var i=0; i<3; i++) {
    	            a.push(b.shift());
    	        }
    	    } else {
    	        for(var i=0; i<h; i++) {
    	            a.push(b.shift());
    	        }
    	    }
    	    var f = a.length, g = c.length;
    	    for(var i=0; i<10 - f;) {
    	        if(i > g - 1) {
    	            continue;
    	        }
    	        a.push(c.shift());
    	    }
    	    var e = [], x;
    	    e.push("<ul class=\"1h\">");
    	    for(var i=0; i<3; i++) {
    	        x = pageConfig.FN_GetCompatibleData(a[i]);
    	        e.push("<li class=\"item\"><a href=\"");
    	        e.push(x.href);
    	        e.push("\"><img src=\"/images/blank.gif\" style=\"background:url(");
    	        e.push(x.src);
    	        e.push(") no-repeat #fff center 0;\" alt=\"");
    	        e.push(x.alt);
    	        e.push("\" width=\"");
    	        e.push(x.width);
    	        e.push("");
    	        e.push("");
    	        e.push("");
    	    }
    	});
    </script>
    
    </div>
    </div>
</body>
</html>