//加入replaceAll方法支持
String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
}

// 加入trim方法支持
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
}