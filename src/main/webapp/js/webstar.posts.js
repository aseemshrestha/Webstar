var APP_THREAD;
var ThreadWidget = {
	settings : {
		btnPost : $("#myhome_post"),
		postContents : $("#post-contents")

	},

	init : function() {
		APP_THREAD = this.settings;
		this.bindUIActions();
	},

	bindUIActions : function() {
		APP_THREAD.btnPost.on("click", function() {
			ThreadWidget.buildDialog(APP_THREAD.postContents);
		});
	},
	validateVideoLinks : function(url) {
		url.match(/^http:\/\/(?:.*?)\.?(youtube|vimeo)\.com\/(watch\?[^#]*v=(\w+)|(\d+)).+$/);
		return {
			provider : RegExp.$1,
			id : RegExp.$1 == 'vimeo' ? RegExp.$2 : RegExp.$3
		}
	},
	buildDialog : function(div) {
		var isMobile = /iPhone|iPod|Android/i.test(navigator.userAgent);
		div.dialog({
			modal : true,
			minHeight : 'auto',
			width : $(window).width() > 400 ? 700 : 330,
			resizable : true,
			autoOpen : true,
			position : {
				my : isMobile == true ? "top-500" : "top-200",
				at : "center",
				of : $("body"),
				within : $("body")
			},
			closeOnEscape : false,

			buttons : {}

		});
		$(window).resize(function() {
			APP_THREAD.postContents.dialog({
				width : $(window).width() > 400 ? 700 : 330,
			});
		});
	}
}
