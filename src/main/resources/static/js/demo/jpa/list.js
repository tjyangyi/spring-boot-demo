$(function() {
	//1.初始化Table
	var oTable = new TableInit();
	oTable.Init();
});

function doQuery() {
	$('#jpa-demo-table').bootstrapTable('refresh');
}

var TableInit = function() {
	var oTableInit = new Object();
	//初始化Table
	oTableInit.Init = function() {
		$('#jpa-demo-table').bootstrapTable({
			url : '/demo/mybatis/queryList', //请求后台的URL（*）
			method : 'get', //请求方式（*）
			striped : true, //是否显示行间隔色
			cache : false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, //是否显示分页（*）
			sortable : false, //是否启用排序
			sortOrder : "asc", //排序方式
			queryParams : oTableInit.queryParams, //传递参数（*）
			sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, //初始化加载第一页，默认第一页
			pageSize : 10, //每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], //可供选择的每页的行数（*）
			search : true, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			showColumns : true, //是否显示所有的列
			showRefresh : true, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : true, //是否启用点击选中行
			height : 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			uniqueId : "ID", //每一行的唯一标识，一般为主键列
			showToggle : true, //是否显示详细视图和列表视图的切换按钮
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			responseHandler : function(res) {
				//在ajax获取到数据，渲染表格之前，修改数据源
				var nres = [];
				nres.push({
					total : res.total,
					rows : res.list
				});
				return nres[0];
			},
			toolbars : [
				{
					text : '添加',
					iconCls : 'glyphicon glyphicon-plus',
					handler : function() {
						$.ajax({
							type : 'POST',
							url : "/demo/jpa/toAdd",
							cache : false,
							success : function(response) {
								BootstrapDialog.show({
									message : function(dialog) {
										var $message = $('<div></div>');
										$message.html(response); // 把传回来的页面作为message返回
										return $message;
									},
									title : "添加",
									draggable : true,
									buttons : [ {
										label : '保存',
										autospin : true,
										cssClass : 'btn-primary',
										action : function(dialogRef) {
											dialogRef.enableButtons(false);
											dialogRef.setClosable(false);
											submitAddForm(function susccessCallback() {
												dialogRef.close();
												doQuery();
											});
										}
									}, {
										label : '取消',
										action : function(dialogRef) {
											dialogRef.close();
										}
									} ]
								})
							}
						});
					}
				},
				{
					text : '修改',
					iconCls : 'glyphicon glyphicon-pencil',
					handler : function() {
						var rows = $('#demo-table').bootstrapTable('getSelections');
						if (rows.length == 0 || rows.length > 1) {
							fadingTip('请选择一条需要修改的数据!');
							return false;
						}
						$.ajax({
							type : 'POST',
							url : "/demo/jpa/toEdit?id=" + rows[0].id,
							cache : false,
							success : function(response) {
								BootstrapDialog.show({
									message : function(dialog) {
										var $message = $('<div></div>');
										$message.html(response); // 把传回来的页面作为message返回
										return $message;
									},
									title : "修改",
									draggable : true,
									buttons : [ {
										label : '保存',
										autospin : true,
										cssClass : 'btn-primary',
										action : function(dialogRef) {
											dialogRef.enableButtons(false);
											dialogRef.setClosable(false);
											submitEditForm(function susccessCallback() {
												dialogRef.close();
												doQuery();
											});
										}
									}, {
										label : '取消',
										action : function(dialogRef) {
											dialogRef.close();
										}
									} ]
								})
							}
						});
					}
				},
				{
					text : '删除',
					iconCls : 'glyphicon glyphicon-remove',
					handler : function() {
						var rows = $('#demo-table').bootstrapTable('getSelections');
						if (rows.length == 0) {
							fadingTip('请选择一条需要删除的数据!');
							return false;
						}
						BootstrapDialog.confirm('是否要删除您当前所勾选的？', function(result) {
							if (result) {
								$.post("/demo/jpa/delete", {
									json : JSON.stringify(rows)
								}, function(response) {
									if (response.result) {
										fadingTip(response.msg);
										doQuery();
									}
								});
							}
						});
					}
				}
			],
			columns : [
				{
					checkbox : true
				},
				{
					field : '',
					title : '序号',
					formatter : function(value, row, index) {
						return index + 1;
					}
				},
				{
					field : 'id',
					title : 'ID',
					align : 'center',
					valign : 'middle',
					hide : false,
					sortable : true
				},
				{
					field : 'content',
					title : '内容',
					align : 'center',
					valign : 'middle',
					sortable : true
				},
				{
					field : 'num',
					title : '数字',
					align : 'center',
					valign : 'middle',
					sortable : true
				},
				{
					field : 'operations',
					title : '操作',
					align : 'center',
					valign : 'middle',
					formatter : function(value, row, index) {
						return '<a href="#" onclick="operate(\'' + row.id + '\')" >对该行的操作</a>';
					}
				} ]
		});
	};

	//得到查询的参数
	oTableInit.queryParams = function(params) {
		var temp = {
			content : $("#content").val(),
			num : $("#num").val()
		};
		return temp;
	};
	return oTableInit;
};



// 单行的操作列
function operate(id) {
	var confirmTitle = "是否操作该行？ID=" + id;
	BootstrapDialog.confirm(confirmTitle, function(result) {});
}