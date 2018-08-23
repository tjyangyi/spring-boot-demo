$(function() {
	initSelect();
	initFileInput();
	initTable();
});

function initSelect() {
	var date = new Date;
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	$('#year_select').val(year);
	$('#month_select').val(month);
}

function initFileInput() {
	var year = $('#year_select').val();
	var month = $('#month_select').val();
	$('#fileInput').fileinput({
		language : 'zh', //设置语言
		uploadUrl : '/attendance/fileUpload?year=' + year + "&month=" + month,
		enctype : 'multipart/form-data',
		overwriteInitial : false,
		initialPreviewAsData : true
	}).on("fileuploaded", function(event, data, previewId, index) {
		if (data.response.success) {
			fadingTip('上传成功');
			$('#table').bootstrapTable('refresh');
		} else {
			fadingTip(data.response.reason);
		}
	});
}

function initTable() {
	$('#table').bootstrapTable({
		url : "/attendance/queryAttendanceList",
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
				field : '',
				title : '序号',
				align : 'center',
				formatter : function(value, row, index) {
					return index + 1;
				}
			},
			{
				field : 'id',
				title : 'ID',
				align : 'center',
				valign : 'middle',
				visible : false,
				sortable : true
			},
			{
				field : 'year',
				title : '年',
				align : 'center',
				valign : 'middle',
				sortable : true
			},
			{
				field : 'month',
				title : '月',
				align : 'center',
				valign : 'middle',
				sortable : true
			},
			{
				field : 'fileName',
				title : '文件',
				align : 'center',
				valign : 'middle',
				sortable : true
			},
			{
				field : 'opertation',
				title : '操作',
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="view(\'' + row.id + '\')" >查看详细</a>' +
						' | ' +
						'<a href="/attendance/download?attendanceId='+ row.id + '">生成下载</a>';
				}
			} ]
	});
}

function view(id) {
	$.ajax({
		type : 'POST',
		url : "/attendance/openAttendanceDetails?attendanceId=" + id,
		cache : false,
		success : function(response) {
			BootstrapDialog.show({
				size : BootstrapDialog.SIZE_WIDE,
				message : function(dialog) {
					var $message = $('<div></div>');
					$message.html(response); // 把传回来的页面作为message返回
					return $message;
				},
				title : "详细",
				draggable : true
			})
		}
	});
}