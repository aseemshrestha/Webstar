var PAGE_THREAD;
var PageWidget = {
	
	settings : {
		postComment : $("#post_comment"),
		commentForm : $('#comment_form')
	},
	
	init : function() {
		PAGE_THREAD = this.settings;
		this.bindPageActions();
	},

	displayCommentWindow : function(event) {
		PAGE_THREAD.commentForm.append('<input type="hidden" name="postId" id="post_id_comment" value='+event.target.id+' />');
		ThreadWidget.buildCommentWindow.call(event);
	},
	
	bindPageActions : function() {
		PAGE_THREAD.postComment.on("click", function() {
			PageWidget.submitComment();
		});
	},
	
	submitComment : function() {
		var errorpostcomment = $("#error_post_comment");
		if ($("#ta-comment-1").val() == "") {
			errorpostcomment.html('Comment is required.').show();
			return;
		} else {
		   PAGE_THREAD.commentForm.submit();
		}
	}
}
