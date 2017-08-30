var APP_THREAD;
var ThreadWidget = {
	settings : {
		btnPost : $("#myhome_post"),
		postContents : $("#post-contents"),
		postSubmit : $("#post_submit"),
		textAreaPost : $("#ta-post-1"),
		videoLinks : $("#videoLinks")

	},

	init : function() {
		APP_THREAD = this.settings;
		this.bindUIActions();
	},

	bindUIActions : function() {
		APP_THREAD.btnPost.on("click", function() {
			ThreadWidget.buildDialog(APP_THREAD.postContents);
		});
		APP_THREAD.postSubmit.on("click", function() {
			ThreadWidget.submitPost();
		});
	},
	validateVideoLinks : function(url) {
		url.match(/^http:\/\/(?:.*?)\.?(youtube|vimeo)\.com\/(watch\?[^#]*v=(\w+)|(\d+)).+$/);
		return {
			provider : RegExp.$1,
			id : RegExp.$1 == 'vimeo' ? RegExp.$2 : RegExp.$3
		}
	},
	submitPost : function() {
		var hasErros = false;
		if (APP_THREAD.textAreaPost.val() == "") {
			$("#error_post").show();
			$("#error_video").hide()
			hasErrors = true;
		}
		
		if (APP_THREAD.videoLinks.val() != "") {
			var obj = ThreadWidget.validateVideoLinks(APP_THREAD.videoLinks.val());
			if (obj.provider == "" || obj.id == "") {
				$("#error_video").show();
				$("#error_post").hide();
                hasErrors = true; 
			}
		}
		if (hasErrors) {
			return;
		} else {
			alert("all good");
			return;
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
			close: function( event, ui ) {
				$("#error_video").hide();
				$("#error_post").hide();
			},
			buttons : {}

		});
		$(window).resize(function() {
			APP_THREAD.postContents.dialog({
				width : $(window).width() > 400 ? 700 : 330,
			});
		});
	}
}
