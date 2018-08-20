function fadingTip(text) {
	var dialog = new BootstrapDialog({
		message : function(dialogRef) {
			var $message = $('<div style="text-align:center;">' + text + '</div>');
			return $message;
		},
		closable : false
	});
	dialog.realize();
	dialog.getModalHeader().hide();
	dialog.getModalFooter().hide();
	dialog.getModalBody().css('background-color', '#0088cc');
	dialog.getModalBody().css('color', '#fff');
	dialog.open();
	setTimeout(function() {
		dialog.close();
	}, 1000);
}