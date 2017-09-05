var PAGE_THREAD;
var pagehome = 0;
var PageWidget = {
	settings : {
		linkComment : $("#myhome_comment")
	},

	init : function() {
		PAGE_THREAD = this.settings;
		this.bindPageActions();
	},

	bindPageActions : function() {
		PAGE_THREAD.linkComment.on("click", function() {
			ThreadWidget.buildUIComments.call();
		});
	}
}
