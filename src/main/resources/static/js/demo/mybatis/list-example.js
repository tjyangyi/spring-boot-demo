$(function() {
	initTable();
});

function doQuery() {
	$('#demo-table').bootstrapTable('refresh');
}

// 单行的操作列
function operate(id) {
	var confirmTitle = "是否操作该行？ID=" + id;
	BootstrapDialog.confirm(confirmTitle, function(result) {});
}

function initTable() {
	$('#demo-table').bootstrapTable({
		url : "/demo/mybatis/queryList",
		height : $(window.parent.document).find("#wrapper").height() - 252,
		width : $(window).width(),
		showColumns : true,
		formId : "queryForm",
		pagination : true,
		sortName : 'id',
		sortOrder : 'desc',
		clickToSelect : true, // 单击某一行的时候选中某一条记录
		pageSize : 10,
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
}