<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="${contextRoot}/css/jquery.emojipicker.css">
<script type="text/javascript" src="${contextRoot}/js/jquery.emojipicker.js"></script>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<div id="post-comments" title="Compose your comment" style="display: none;">
	<br />
     <span id="error_post_comment" class="alert alert-danger no-margin" style="display:none"></span>    
     <form:form modelAttribute="usercomments" action="/postcomment" method="POST" class="form-base-2" role="form" id="comment_form" enctype="multipart/form-data">
    	 <div class="col-sm-13"> 
		   <div class="form-group has-feedback">
			<form:textarea path="comments" class="form-control form-control-lg selectpicker emojiable-question" cols="5" rows="5" placeholder="What's up" maxlength="200"  id="ta-comment-1"/>
			<small><span id="allowedc" style="float:right;margin-right:5px;"></span></small>
	   	</div>
		</div>
		<div class="col-sm-13">
			<div class="form-group has-feedback">
				<label for="" class="text-uppercase">Add Video Link ( if
					any -Youtube/Vimeo supported )</label> 
					<form:input type="text" path="videoUrl" id="videoLinksComment" class="form-control form-control-lg selectpicker" placeholder="Audio Video links"/>
			</div>
		</div>
		<hr />
		<div class="imgUpload">
			     <label for="fileInputComment"> 
			       <img src="../img/icons/upload.png" style="width: 30px;" />
			     </label>
				 <input name="file" type="file" id="fileInputComment" class="fileUpload" accept="image/jpeg, image/jpg,image/png, image/gif" name="postImg[]" style="display: none" /> 
				 <a href="javascript:void(0)" class="" style="float: left; margin-right: 10px;" id="emoji-2">
				   <img src="../img/icons/emoji.png" style="width: 30px;" />
				 </a>
				 <div class="upload-demo">
                      <div class="upload-demo-wrap" id="img-place-comment"><img alt="your image" class="portimg-comment" src="#"></div>
                 </div>
               <a href="javascript:void(0)" class="btn btn-styled btn-md btn-base-2" style="float: right" id="post_comment">POST</a>  
		</div>
 	       
	</form:form>
</div>

<script type="text/javascript">
	$(document).ready(function(e) {
		var textMax = 200;
		var textArea = $('#ta-comment-1');
		var allowedc = $('#allowedc');
		var isMobile = /iPhone|iPod|Android/i.test(navigator.userAgent);
		if(!isMobile){
			$("#ta-comment-1").css('width','674px');
		}else{
			$("#ta-comment-1").css('width','320px');
		}

		$('.emojiable-question').emojiPicker({
			button : false
		});
		$('#emoji-2').on('click', function(e) {
			e.preventDefault();
			textArea.emojiPicker('toggle');
		});
		allowedc.html(textMax);
		textArea.keyup(function() {
			var textlength = textArea.val().length;
			var remaining = textMax - textlength;
			allowedc.html(remaining);
		});
	});
</script>