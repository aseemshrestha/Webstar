var APP_THREAD;
var ThreadWidget = {
	settings : {
		btnPost : $("#myhome_post"),
		postContents : $("#post-contents"),
		postSubmit : $("#post_submit"),
		textAreaPost : $("#ta-post-1"),
		videoLinks : $("#videoLinks"),
		category : $("#cat_dd"),
		subcategory : $("#subcat_dd")

	},

	init : function() {
		APP_THREAD = this.settings;
		this.bindUIActions();
	},

	bindUIActions : function() {
		APP_THREAD.btnPost.on("click", function() {
			ThreadWidget.buildDialog(APP_THREAD.postContents);
			$.ajax({
				type : "GET",
				url : "/categories",
				success : function(data) {
					APP_THREAD.category.empty();
					APP_THREAD.category.append($("<option></option>").attr("value", '').text('Please select'));
					$.each(data, function(value, key) {
						APP_THREAD.category.append($("<option></option>").attr("value", value).text(key));
					});
				},
				error : function(e) {
					console.log("error:", e);
				}
			})
		});
		APP_THREAD.postSubmit.on("click", function() {
			ThreadWidget.submitPost();
		});
		APP_THREAD.category.on("change", function() {
			ThreadWidget.showSubCategories();
		});
	},
	showSubCategories : function() {
		var key = APP_THREAD.category.val();
		$.ajax({
			type : "GET",
			url : "/subcategories?category=" + key,
			success : function(data) {
				var items = data.split(",");
				APP_THREAD.subcategory.empty();
				APP_THREAD.subcategory.append($("<option></option>").attr("value", '').text('Please select'));
				for (var i = 0; i < items.length; i++) {
					APP_THREAD.subcategory.append($("<option></option>").attr("value", items[i]).text(items[i]));
				}
			},
			error : function(e) {
				console.log("error:", e);
			}
		})
	},

	validateVideoLinks : function(url) {
		var regU = /^(?:https?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))((\w|-){11})(?:\S+)?$/;
		var regv =  /^(http\:\/\/|https\:\/\/)?(www\.)?(vimeo\.com\/)([0-9]+)$/;
		return (url.match(regU) || url.match(regv)) ? true /* RegExp.$*1 */: false;
	},
  
	submitPost : function() {
		var errorpost = $("#error_post");
		errorpost.html('');
		if (APP_THREAD.textAreaPost.val() == "") {
			errorpost.html('Post contents is required.').show();
			return;
		}
		if (APP_THREAD.category.val() == ""
				|| APP_THREAD.subcategory.val() == "") {
			errorpost.html('Category and respective subcateogy is required.').show();
			return;
		}
		if (APP_THREAD.videoLinks.val() != "" && !ThreadWidget.validateVideoLinks(APP_THREAD.videoLinks.val())) {
			errorpost.append("Incorrect video url.").show();
			return;

		} else {
			$("#post_form").submit();
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
				my : isMobile == true ? "bottom-300" : "top",
				at : "top",
				of : $("body"),
				within : $("body")
			},
			closeOnEscape : false,
			close : function(event, ui) {
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
