var loadimg = "./img/load2.gif"; // 加载时的loading图片 
function showMainContent(index, url) {
	$("li[id^='top_li']").removeClass("active");
	$("#top_li" + index).addClass("active");
	$('#mainContent').load(url);
}