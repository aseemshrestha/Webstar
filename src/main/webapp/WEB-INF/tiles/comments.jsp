<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
.block-comment:last-child {
  border-bottom: 2px solid rgba(243, 243, 243, 0.7) !important;
  }
 .block-comment .block-body {
  display: table-cell;
  vertical-align: top;
  position: relative;
  padding: 0 0 0 0.5em !important;
  }
  .card-body {
  padding: 0.1rem 0.5rem;
  }
</style>
<c:set var="name" value="${fn:split(nameEmail,'###')[1]}" />
<c:set var="email" value="${fn:split(nameEmail,'###')[0]}" />

<jsp:include page="_post.jsp" />
<jsp:include page="_comment.jsp" />

<div class="body-wrap" data-template-mode="cards"><div id="st-container" class="st-container">
	<jsp:include page="_profileimage.jsp" /><div class="st-pusher"><div class="st-content"><div class="st-content-inner">
		<jsp:include page="_search.jsp" />
		  <section class="slice sct-color-2" style="padding-top: 1em; padding-bottom: 1em;!important"><div class="profile">
			<div class="container"><div class="row cols-md-space cols-sm-space cols-xs-space"><jsp:include page="_leftcolumn.jsp" />
               <div class="col-lg-6" id="contents-div-comment"><div class="widget"><div class="card z-depth-2-top">
				 <section class="slice sct-color-1" style="padding-top: 0em; padding-bottom: 0em;!important"><div class="container container-xs">
				  <div class="text-center"><br />
					<h3 class="heading heading-6 strong-400 text-normal"> ${post} </h3>
					<h5 class="heading heading-xs c-gray-dark text-uppercase strong-500 letter-spacing-2 mb-0 mt-1">${category}</h5>
					<span class="short-delimiter short-delimiter-center short-delimiter--style-1 short-delimiter-light short-delimiter-center short-delimiter-lg"></span>
                     <ul class="inline-links inline-links--style-2 mt-1">
                        <li>${postedby}</li>
                        <li> ${totalComments} <small>comments</small></li>
                        <li><a href="javascript:void(0)"><i class="fa fa-comment"  id="${param.postid}" onclick="PageWidget.displayCommentWindow(event);"></i></a></li>
                        <li><a href="/myhomepage"><i class="fa fa-arrow-left" aria-hidden="true"></i></a></li>
                     </ul>
                  </div>
                  <c:if test="${not empty imageUrl}"> <img src="../${imageUrl}" style="width: 100%; top: -0px;" /></c:if>
					<c:if test="${not empty videoUrl}">
						<c:if test="${fn:startsWith(videoUrl,'$$-') }">
							<iframe width="100%" height="315" src="https://www.youtube.com/embed/${ fn:split(videoUrl,'$$-')[0] }"></iframe>
						</c:if>
						<c:if test="${fn:startsWith(videoUrl,'##-') }">
							<iframe width="100%" height="315" src="https://player.vimeo.com/video/${ fn:split(videoUrl,'##-')[0] }"></iframe>
						</c:if>
	               </c:if>
				 </div>
		       </section><br />
			     <c:if test="${not empty( commentsList ) }">
			      <ul class="block-post-comments block-post-comments--style-1">
			       <c:forEach var="recent" items="${commentsList}" varStatus="position">
					<div class="card-body">
					  <li><div class="block block-comment"><div class="block-image"> <img src="../img/prv/people/person-2.jpg" class="img-circle"></div><div class="block-body">
	                  <div class="block-body-inner"><h3 class="heading heading-6"><a href="#">${recent.commentedByName}</a></h3><span class="comment-date">${recent.timeLapse}</span>
	                   <p class="comment-text">${recent.comments} <c:if test="${recent.imageUrl ne null}"><img src="../${recent.imageUrl}" style="width: 100%; top: -0px;" /></c:if>
						<c:if test="${not empty recent.videoUrl}">
							<c:if test="${fn:startsWith(recent.videoUrl,'$$-') }">
								<iframe width="100%" height="315" src="https://www.youtube.com/embed/${ fn:split(recent.videoUrl,'$$-')[0] }"></iframe>
							</c:if>
							<c:if test="${fn:startsWith(recent.videoUrl,'##-') }">
								<iframe width="100%" height="315" src="https://player.vimeo.com/video/${ fn:split(recent.videoUrl,'##-')[0] }"></iframe>
							</c:if>
						  </c:if></p></div></div></div>
						 </li><hr />
					    </div>
				   	    </c:forEach>
				    </ul>
		     	      </c:if></div></div></div><jsp:include page="_rightcolumn.jsp" /></div></div></div>
					</section>
				</div>
			</div>
		</div>
	</div>
</div>