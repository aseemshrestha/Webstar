<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
img.portimg {
    display: none;
    max-width: 200px;
    max-height: 200px;
}
</style>


<div id="post-ratings" title="Rate the post" style="display: none;">
	<br />
   <h3 class="heading heading-6 strong-500" id="head_rating"></h3>
 	<form action="/postrating" method="POST" class="form-base-2"
		role="form" id="rating_form" enctype="multipart/form-data" id="ratingForm">
		<div class="col-sm-13">
			<div class="form-group has-feedback">
				<div class="rating-container" style="margin-top: 12px;">
					<input type="hidden" name="rating" id="reviews-rating"
						aria-required="true"> <span class="label label-success"
						id="reviews-rating-label"></span>
				</div>
			</div>
		</div>
		 <input type="hidden" name="postId" value="" id="rating_post_id" />
	
         <input type="submit" value="RATE" id="btnRate" class="btn btn-styled btn-xs btn-base-1"/>
	  <hr />


	</form>
</div>
<script type="text/javascript">
$(document).ready( function () {
	$('#reviews-rating').rating({
        filled: "fa fa-2x fa-star",
        empty: "fa fa-2x fa-star-o",
        fractions: 2
	})
    .on('change', function () {
        $('#reviews-rating-label').html($(this).val() + " Stars");
    });
	
	$("#btnRate").on('click',function(){
	  $("#ratingForm").submit();
		
	});
	
});


</script>
