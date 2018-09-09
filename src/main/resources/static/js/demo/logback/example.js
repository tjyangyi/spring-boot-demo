function button1Clicked() {
	$.ajax({
		type : 'POST',
		url : "/demo/logback/pringAllLogs",
		cache : false,
		success : function(response) {
			alert('成功，请查看后台日志');
		}
	});
}