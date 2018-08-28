$(function() {
	initSelect();
	initTable();
	initTitleTip();
});

function initSelect() {
	var date = new Date;
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	$('#year_select').val(year);
	$('#year_select').selectpicker('refresh');
	$('#month_select').val(month);
	$('#month_select').selectpicker('refresh');
	$('#username').val($('#usernameSpan').text());
}

function initTitleTip() {
	var year = $('#year_select').val();
	var month = $('#month_select').val();
	$.ajax({
		type : 'POST',
		url : "/attendance/getTitleTip?year=" + year + "&month=" + month,
		cache : false,
		success : function(response) {
			$('#titleTip').text(response);
		}
	});

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
		striped : true, //是否显示行间隔色
		rowStyle : function(row, index) {
			//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
			if (index % 2 == 0) {
				return {
					classes : "info"
				}
			} else {
				return {
					classes : "warning"
				}
			}
		},
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
	$.ajax({
		type : 'POST',
		url : "/attendance/openModifyAttendanceDetails?attendanceDetailId=" + attendanceDetailId,
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