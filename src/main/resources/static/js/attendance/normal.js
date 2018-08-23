$(function() {
	initSelect();
	initTable();
});

function initSelect() {
	var date = new Date;
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	$('#year_select').val(year);
	$('#month_select').val(month);
	$('#username').val($('#usernameSpan').text());
}

function initTable() {
	var year = $('#year_select').val();
	var month = $('#month_select').val();
	$('#table').bootstrapTable({
		url : "/attendance/queryAttendanceDetailsByYearMonth?year=" + year + '&month=' + month,
		height : $(window.parent.document).find("#wrapper").height() - 252,
		width : $(window).width(),
		showColumns : true,
		formId : "queryForm",
		pagination : true,
		pageSize : 300,
		columns : [
			{
				field : 'id',
				title : 'ID',
				visible : false
			},
			{
				field : 'indexNumber',
				title : '序号',
				align : 'center',
				valign : 'middle',
				sortable : false
			},
			{
				field : 'username',
				title : '工号',
				align : 'center',
				valign : 'middle',
				sortable : false
			},
			{
				field : 'realname',
				title : '姓名',
				align : 'center',
				valign : 'middle',
				sortable : false
			},
			{
				field : 'a',
				title : '系统记录</br>出勤天数A',
				align : 'center',
				valign : 'middle',
				sortable : false
			},
			{
				field : 'b',
				title : '部门修正</br>天数B',
				align : 'center',
				valign : 'middle',
				sortable : false
			},
			{
				field : 'c',
				title : '出差天数</br>C',
				align : 'center',
				valign : 'middle',
				sortable : false
			},
			{
				field : 'd',
				title : '扣减上月</br>预发天数D',
				align : 'center',
				valign : 'middle',
				sortable : false
			},
			{
				field : 'e',
				title : '预发下月</br>餐补天数E',
				align : 'center',
				valign : 'middle',
				sortable : false
			},
			{
				field : 'f',
				title : '考勤实际</br>出勤天数A',
				align : 'center',
				valign : 'middle',
				sortable : false
			},
			{
				field : 'g',
				title : '晚餐补贴</br>次数',
				align : 'center',
				valign : 'middle',
				sortable : false
			}, {
				field : 'opertation',
				title : '操作',
				align : 'center',
				valign : 'middle',
				formatter : function(value, row, index) {
					return '<a href="#" onclick="modify(\'' + row.id + '\')" >修改</a>';
				}
			}
		]
	});
}
function doQuery() {
	var year = $('#year_select').val();
	var month = $('#month_select').val();
	$("#table").bootstrapTable('refresh', {
		url : "/attendance/queryAttendanceDetailsByYearMonth?year=" + year + '&month=' + month,
	});
}

function modify(attendanceDetailId) {
}