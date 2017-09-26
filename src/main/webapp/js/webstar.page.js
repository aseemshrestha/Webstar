var PAGE_THREAD;
var PageWidget = {

	settings : {
		postComment : $("#post_comment"),
		commentForm : $('#comment_form'),
		repostForm : $('#repost_form'),
		searchuname : $("#searchuname"),
		ratingForm : $("#rating-form"),
		ratingHead : $("#head_rating"),
		ratingPostId:$("#rating_post_id")
	},

	init : function() {
		PAGE_THREAD = this.settings;
		this.bindPageActions();

	},

	displayCommentWindow : function(event) {
		var id = event.target.id;
//		PAGE_THREAD.commentForm
	//			.append('<input type="hidden" name="postId" id="post_id_comment" value="'
	//					+ id + '"></input>');
		$("#postIdc").val(id);
		ThreadWidget.buildCommentWindow.call(event);
	},
    
	
	displayRepostWindow : function(event) {
		ThreadWidget.buildRepostWindow.call(event);
	},

	bindPageActions : function() {
		PAGE_THREAD.postComment.on("click", function() {
			PageWidget.submitComment();
		});
		
	},
	
	displayRatingWindow : function(event){
	   var id = event.target.id;
	   PAGE_THREAD.ratingPostId.val(id);
	   PAGE_THREAD.ratingHead.text($("#rcc"+id).text());
	   ThreadWidget.buildRatingWindow.call(event);
	},

	submitComment : function() {
		var errorpostcomment = $("#error_post_comment");
		if ($("#ta-comment-1").val() == "") {
			errorpostcomment.html('Comment is required.').show();
			return;
		} else {
			PAGE_THREAD.commentForm.submit();
		}
	},

	doLike : function(event) {
		var id = event.target.id;
		$.ajax({
			type : "GET",
			url : "/doLikes?postId=" + id,
			success : function(data) {
				if (data == -1) {
					return;
				} else {
					$("#like" + id).html(data);
				}
			},
			error : function(e) {
				console.log("error:", e);
			}
		})
	},

	doSearch : function(event) {
		PAGE_THREAD.searchuname.autocomplete({
			source : function(request, response) {
				$.getJSON("/search", {
					term : request.term
				}, function(data) {
					response($.map(data, function(item) {
						return {
							label : item.username,
							value : item.username
						}
					}));

				})
			},
			select : function(event, ui) {
				var label = ui.item.label;
				window.location.href = '/q?un=' + label + '&offset=0';

			}
		})
	}
}
