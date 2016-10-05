<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.7/themes/default/default.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.7/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.7/lang/zh_CN.js"></script>

<div style="padding:10px">
	<form id="itemAddForm" class="itemForm" method="post">
		<table cellpadding="5">
			<tbody>
				<tr>
					<td>商品类目</td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">
							选择类目
						</a>
						<input type="text" name="cid" style="width: 280px;"/>
					</td>
				</tr>
				<tr>
					<td>商品标题</td>
					<td>
						<input class="easyui-numberbox" type="text" name="title" data-option="required:true" style="width: 280px;"/>
					</td>
				</tr>
				<tr>
					<td>商品卖点</td>
					<td>
						<input class="easyui-numberbox" name="sellPoint" data-option="multiline:true,validType:'length[0,150]'" style="width: 280px;"/>
					</td>
				</tr>
				<tr>
					<td>商品价格</td>
					<td>
						<input class="easyui-numberbox" type="text" name="priceView" data-option="min:1,max:99999999,precision:2" style="width: 280px;"/>
						<input type="hidden" name="price"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>