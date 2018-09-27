$(function() {
	//1.初始化Table
	var oTable = new TableInit();
	oTable.Init();

	//2.初始化Button的点击事件
	var oButtonInit = new ButtonInit();
	oButtonInit.Init();
});

function doQuery() {
	$('#quartz-table').bootstrapTable('refresh');
}

var TableInit = function() {
	var oTableInit = new Object();
	//初始化Table
	oTableInit.Init = function() {
		$('#quartz-table').bootstrapTable({
			url : '/quartz/queryQuartzJobList', //请求后台的URL（*）
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
			search : false, //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
			strictSearch : true,
			showColumns : true, //是否显示所有的列
			showRefresh : true, //是否显示刷新按钮
			minimumCountColumns : 2, //最少允许的列数
			clickToSelect : true, //是否启用点击选中行
			height : 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			width : 1000,
			uniqueId : "jobId", //每一行的唯一标识，一般为主键列
			showToggle : false, //是否显示详细视图和列表视图的切换按钮
			cardView : false, //是否显示详细视图
			detailView : false, //是否显示父子表
			responseHandler : function(res) {
				//在ajax获取到数据，渲染表格之前，修改数据源
				var nres = [];
				nres.push({
					total : res.totalCount,
					rows : res.result
				});
				return nres[0];
			},
			toolbars : '#toolbar',
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
					field : 'jobGroup',
					title : '任务组名称',
					align : 'center',
					valign : 'middle',
					hide : false,
					sortable : true
				},
				{
					field : 'jobName',
					title : '任务名称',
					align : 'center',
					valign : 'middle',
					sortable : true
				},
				{
					field : 'triggerGroupName',
					title : '触发器组名称',
					align : 'center',
					valign : 'middle',
					sortable : true
				},
				{
					field : 'triggerName',
					title : '触发器名称',
					align : 'center',
					valign : 'middle',
					sortable : true
				},
				{
					field : 'cronExpression',
					title : '时间表达式',
					align : 'center',
					width : 120,
					valign : 'middle',
					sortable : true
				},
				{
					field : 'prevFireTime',
					title : '上次运行时间',
					align : 'center',
					valign : 'middle',
					formatter : dateFormat2Second,
					sortable : true
				},
				{
					field : 'nextFireTime',
					title : '下次运行时间',
					align : 'center',
					valign : 'middle',
					formatter : dateFormat2Second,
					sortable : true
				},
				{
					field : 'triggerState',
					title : '任务状态',
					align : 'center',
					valign : 'middle',
					sortable : true
				},
				{
					field : 'priority',
					title : '优先级',
					align : 'center',
					valign : 'middle',
					sortable : true
				},
				{
					field : 'startTime',
					title : '开始时间',
					align : 'center',
					valign : 'middle',
					sortable : true,
					formatter : dateFormat2Second
				},
				{
					field : 'endTime',
					title : '结束时间',
					align : 'center',
					valign : 'middle',
					sortable : true,
					formatter : dateFormat2Second
				},
				{
					field : 'jobClass',
					title : '任务类名',
					align : 'center',
					valign : 'middle',
					sortable : true
				},
				{
					field : 'jobDataMapJson',
					title : 'jobDataMap',
					align : 'center',
					valign : 'middle',
					sortable : true
				},
				{
					field : 'operations',
					title : '操作',
					align : 'center',
					width : 170,
					valign : 'middle',
					formatter : operationFormatter
				} ]
		});
	};

	//得到查询的参数
	oTableInit.queryParams = function(params) {
		var temp = {
			jobClass : $("#jobClass").val()
		};
		return temp;
	};
	return oTableInit;
};

var ButtonInit = function() {
	var oInit = new Object();
	var postdata = {};
	oInit.Init = function() {
		//初始化页面上面的按钮事件
		$('#btn_add').click(function() {
			$.ajax({
				type : 'POST',
				url : "/quartz/openAddJob",
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
								}, function failedCallback() {
									dialogRef.enableButtons(true);
									dialogRef.setClosable(true);
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
		})
	};
	return oInit;
};



//操作列
function operationFormatter(val, row, index) {
	var r = '<a href="#" style="padding-left:3px;padding-right:3px" onclick="triggerJob({row.jobName},{row.jobGroup});">运行</a>'
		+ '|'
		+ '<a href="#" style="padding-left:3px;padding-right:3px" onclick="openEditJobDialog({row.jobName},{row.jobGroup});">编辑</a>'
		+ '|';
	if (row.triggerState != 'PAUSED') {
		r += '<a href="#" style="padding-left:3px;padding-right:3px" onclick="pauseJob({row.jobName},{row.jobGroup});">暂停</a>'
			+ '|';
	} else {
		r += '<a href="#" style="padding-left:3px;padding-right:3px" onclick="resumeJob({row.jobName},{row.jobGroup});">恢复</a>'
			+ '|';
	}
	r += '<a href="#" style="padding-left:3px;padding-right:3px" onclick="deleteJob({row.jobName},{row.jobGroup},{row.triggerName},{row.triggerGroupName});">删除</a>';
	r = r.replaceAll("{row.jobName}", "'" + row.jobName + "'");
	r = r.replaceAll("{row.jobGroup}", "'" + row.jobGroup + "'");
	r = r.replaceAll("{row.triggerName}", "'" + row.triggerName + "'");
	r = r
		.replaceAll("{row.triggerGroupName}", "'" + row.triggerGroupName
			+ "'");
	return r;
}

//编辑任务对话框
function openEditJobDialog(jobName, jobGroup) {
	$.ajax({
		type : 'POST',
		url : "/quartz/openEditJob?jobName=" + jobName + "&jobGroup=" + jobGroup,
		cache : false,
		success : function(response) {
			BootstrapDialog.show({
				message : function(dialog) {
					var $message = $('<div></div>');
					$message.html(response); // 把传回来的页面作为message返回
					return $message;
				},
				title : "编辑任务",
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
						}, function failedCallback() {
							dialogRef.enableButtons(true);
							dialogRef.setClosable(true);
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

//运行任务
function triggerJob(jobName, jobGroup) {
	$.post("/quartz/triggerJob", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(response) {
		if (response.result) {
			fadingTip(response.msg);
		}
	});
}

//暂停任务
function pauseJob(jobName, jobGroup) {
	$.post("/quartz/pauseJob", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(response) {
		if (response.result) {
			fadingTip(response.msg);
			doQuery();
		}
	});
}

//恢复任务
function resumeJob(jobName, jobGroup) {
	$.post("/quartz/resumeJob", {
		jobName : jobName,
		jobGroup : jobGroup
	}, function(response) {
		if (response.result) {
			fadingTip(response.msg);
			doQuery();
		}
	});
}

//删除任务
function deleteJob(jobName, jobGroup, triggerName, triggerGroupName) {
	BootstrapDialog.confirm('是否要删除您当前所勾选的？', function(result) {
		if (result) {
			$.post("/quartz/deleteJob", {
				jobName : jobName,
				jobGroup : jobGroup,
				triggerName : triggerName,
				triggerGroupName : triggerGroupName
			}, function(response) {
				if (response.result) {
					fadingTip(response.msg);
					doQuery();
				}
			});
		}
	});
}