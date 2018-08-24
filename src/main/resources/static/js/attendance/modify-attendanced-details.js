function submitEditForm(susccessCallback) {
	$.ajax({
		//几个参数需要注意一下
		type : "POST", //方法类型
		dataType : "json", //预期服务器返回的数据类型
		url : "/attendance/modifyAttendanceDetails", //url
		data : $('#editForm').serialize(),
		success : function(response) {
			console.log(response.result); //打印服务端返回的数据(调试用)
			if (response.result) {
				susccessCallback();
				fadingTip('修改成功');
			} else {
				fadingTip('修改失败');
			}
		},
		error : function() {
			fadingTip('修改失败');
		}
	});
}

function onDayFixed() {
	var a = parseInt($('#a').val());
	var b;
	if ($('#b').val()) {
		b = parseInt($('#b').val());
	} else {
		b = 0;
	}
	var c;
	if ($('#c').val()) {
		c = parseInt($('#c').val());
	} else {
		c = 0;
	}
	var d = parseInt($('#d').val());
	var e = parseInt($('#e').val());
	var f = a + b - c - d + e;
	$('#f').val(f);
}