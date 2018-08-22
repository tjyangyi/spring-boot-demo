$(function() {
	initSelect();
	initFileInput();
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
		uploadUrl : '/attendance/fileUpload?year=' + year + "&month=" + month,
		enctype : 'multipart/form-data',
		overwriteInitial : false,
		initialPreviewAsData : true
	})
}


//导入文件上传完成之后的事件
/*$("#file-input").on("fileuploaded", function(event, data, previewId, index) {
	var data = data.response.lstOrderImport;
	if (data == undefined) {
		toastr.error('文件格式类型不正确');
		return;
	}
});*/

/*function fileUpload() {
	var formData = new FormData();
	formData.append('file', $('#file')[0].files[0]);
	$.ajax({
		url : '/attendance/fileUpload',
		type : 'POST',
		cache : false,
		data : formData,
		processData : false,
		contentType : false
	}).done(function(res) {
		alert('文件上传成功');
	}).fail(function(res) {
		alert('文件上传失败');
	});
}*/