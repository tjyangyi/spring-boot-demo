$(function() {
	var height = $(window).height() - 350;
	$('#rightContent').height(height);
	$('#a1').click();
});

var loadimg = "./img/load2.gif"; // 加载时的loading图片 
function showRightContent(index, url) {
	$("li[id^='left_li']").removeClass("active");
	$("#left_li" + index).addClass("active");
	$('#rightContent').load(url);
}