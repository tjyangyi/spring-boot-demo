function modifyPassword() {
	$.ajax({
		type : 'POST',
		url : "/openModifyPassword",
		cache : false,
		success : function(response) {
			BootstrapDialog.show({
				message : function(dialog) {
					var $message = $('<div></div>');
					$message.html(response); // 把传回来的页面作为message返回
					return $message;
				},
				title : "修改密码",
				draggable : true,
				buttons : [ {
					label : '保存',
					//autospin : true,
					cssClass : 'btn-primary',
					action : function(dialogRef) {
						//						dialogRef.enableButtons(false);
						//						dialogRef.setClosable(false);
						submitEditForm(function susccessCallback() {
							dialogRef.close();
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

function logout() {
	BootstrapDialog.show({
		message : function(dialog) {
			return '确定退出登录吗?';
		},
		title : "退出登录",
		draggable : false,
		buttons : [ {
			label : '确定',
			cssClass : 'btn-primary',
			action : function(dialogRef) {
				window.location.href = '/logout'
			}
		}, {
			label : '取消',
			action : function(dialogRef) {
				dialogRef.close();
			}
		} ]
	})

}