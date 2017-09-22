<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="${contextRoot}/css/jquery.emojipicker.css">
<script type="text/javascript" src="${contextRoot}/js/jquery.emojipicker.js"></script>
<!-- Emoji Data -->
<link rel="stylesheet" type="text/css" href="${contextRoot}/css/jquery.emojipicker.a.css">

<script type="text/javascript" src="${contextRoot}/js/jquery.emojipicker.a.js"></script>
<style>
.ui-widget {
	font-family: Verdana, Arial, sans-serif;
	font-size: .8em;
}

.ui-widget-content {
	background: #F9F9F9;
	border: 0px solid #90d93f;
	color: #222222;
}

.ui-dialog {
	left: 0;
	outline: 0 none;
	padding: 0 !important;
	position: absolute;
	top: 0;
}

.ui-dialog .ui-dialog-content {
	background: none repeat scroll 0 0 transparent;
	border: 0 none;
	overflow: auto;
	position: relative;
	padding: 10 !important;
}

.ui-widget-header {
	background: #3454D1;
	border: 0;
	color: #fff;
	font-weight: normal;
	line-height: 3em;
}

.ui-dialog .ui-dialog-titlebar {
	padding: 0.1em .5em;
	position: relative;
	font-size: 1em;
}
img.portimg-comment {
    display: none;
    max-width: 200px;
    max-height: 200px;
}
</style>
<div id="post-repost" title="Repost" style="display: none;">
	<br />
     <h3>Reposting <span id="repost-name"></span></h3>
     <form action="/doRepostPost" method="POST" class="form-base-2" role="form" id="repost_form">
         <div class="col-sm-13"> 
		   <div class="form-group has-feedback">
			 <textarea class="form-control form-control-lg selectpicker emojiable-question" name="" cols="5" rows="5" maxlength="200"  id="ta-repost-2" readonly></textarea>
	      </div>
		 </div>
         
         <div class="col-sm-13"> 
		   <div class="form-group has-feedback">
			 <textarea class="form-control form-control-lg selectpicker emojiable-question" name="repostcomment" placeholder="Do you want to comment?" cols="5" rows="5" maxlength="200"  id="ta-repost-1"></textarea>
			 <small><span id="allowedr" style="float:right;margin-right:5px;"></span></small>
	      </div>
	      <input type="hidden" name="repostpostid"  id="repost_postid" value=""/>
	    
		</div>
         
      
	   <hr />
	   <input type="submit" class="btn btn-styled btn-md btn-base-2" style="float: right" value="REPOST" />
    </form>
</div>
<script type="text/javascript">
	$(document).ready(function(e) {
		var textMax = 200;
		var textArea = $('#ta-repost-1');
		var allowedc = $('#allowedr');
		var isMobile = /iPhone|iPod|Android/i.test(navigator.userAgent);
		if(!isMobile){
			$("#ta-repost-1,#ta-repost-2").css('width','674px');
		}else{
			$("#ta-repost-1,#ta-repost-2").css('width','320px');
		}

		//$('.emojiable-question').emojiPicker({
			//button : false
	//	});
		/*$('#emoji-2').on('click', function(e) {
			e.preventDefault();
			textArea.emojiPicker('toggle');
		});*/
		allowedc.html(textMax);
		textArea.keyup(function() {
			var textlength = textArea.val().length;
			var remaining = textMax - textlength;
			allowedc.html(remaining);
		});
	});
</script>