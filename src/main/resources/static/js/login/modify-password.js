function submitEditForm(susccessCallback) {
	if ($('#newPassword').val() != $('#newPasswordRepeat').val()) {
		fadingTip('两次密码不一样');
		return;
	}
	$.ajax({
		//几个参数需要注意一下
		type : "POST", //方法类型
		dataType : "json", //预期服务器返回的数据类型
		url : "/modifyPassword", //url
		data : $('#editForm').serialize(),
		success : function(response) {
			console.log(response.result); //打印服务端返回的数据(调试用)
			if (response.result) {
				susccessCallback();
			} else {
			}
			fadingTip(response.msg);
		},
		error : function() {
			fadingTip('修改失败');
		}
	});
}
