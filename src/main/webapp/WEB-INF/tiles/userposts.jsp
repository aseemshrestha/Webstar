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
<c:set var="uid" value="${fn:split(nameEmail,'###')[2]}" />
<jsp:include page="_post.jsp" />
<jsp:include page="_comment.jsp" />
<jsp:include page="_repost.jsp" />
<jsp:include page="_rating.jsp" />

<div class="body-wrap" data-template-mode="cards"><div id="st-container" class="st-container"><jsp:include page="_profileimage.jsp" />
       <div class="st-pusher"><div class="st-content"><div class="st-content-inner">
		 <jsp:include page="_search.jsp" />
		 	<section class="slice sct-color-2" style="padding-top: 1em; padding-bottom: 1em;!important">
				<div class="profile"><div class="container">
						<div class="row cols-md-space cols-sm-space cols-xs-space">
				        <jsp:include page="_leftcolumn.jsp" />
							<div class="col-lg-6" id="contents-div"><div class="widget"><div class="card z-depth-2-top"><jsp:include page="_tabs.jsp" />
						      <c:forEach var="recent" items="${recentPosts}" varStatus="position">
									<div class="card-body" style="padding-left:0.5em;padding-top:0.5em;!important">
								        <div class="block block-comment" style="margin-bottom: 0rem;!important">
                                            <div class="block-image">
                                                <img src="../img/prv/people/brin.jpg" class="img-square">
                                            </div>
                                          <div class="block-body">   
                                            <div class="block-body-inner">
                                            <h3 class="heading heading-6">
                                             <span class="stars" data-rating="${recent.avgRatings}" data-num-stars="5" ></span>
                                              <c:if test="${recent.avgRatings gt  0 }">
                                                <small>${recent.avgRatings} / 5 avg rating</small>
                                              </c:if>
                                              <c:if test="${recent.avgRatings eq  0 }">
                                                <small>Not rated yet.</small>
                                              </c:if>
                                             <br />
                                               <a href="/byuser?uid=${recent.uid}&offset=0&repost=0" id="" style="color:#007aff">${ recent.username } - ${ recent.firstName} ${ recent.lastName }</a> 
                                               <small>${recent.timeLapse }</small>
                                               <span style="float:right"> <a href="/bycategorypage?category=${recent.category}&offset=0">${recent.category}</a>
                                               <a href="/bycategorypage?category=${recent.category}&offset=0">${recent.subcategory}</a></span>
                                           </h3>
                          			       <p class="mb-4" style="margin-bottom:0px;!important">${recent.contents}</p>
                          			        <c:if test="${not empty recent.repostedBy}">
                          			         <h3 class="heading heading-6"><a href="javascript:void(0)" style="color:#007aff">${ recent.repostedBy }</a></h3>
												   <span style="color:#007aff"> reposted <strong><a href="/byuser?uid=${recent.repostOfId}&offset=0&repost=0" id="${recent.repostOfId}">${ recent.repostedOf}'s</a></strong> post <small> ${ recent.timeLapse } </small></span>
												   <p class="mb-4" style="margin-bottom:0px;!important">${recent.repostedPost}
												   <c:if test="${not empty recent.comments}">
												    <br /><small>with comment : </small><medium>${ recent.comments }</medium>
												   </c:if>
												</p>
											</c:if>
                          			        
											<c:if test="${recent.imageUrl ne null}">
												<img src="../${recent.imageUrl}" style="width: 100%; top: -0px;" />
											</c:if>
											<c:if test="${not empty recent.videoUrl}">
												<c:if test="${fn:startsWith(recent.videoUrl,'$$-') }">
													<iframe width="100%" height="315" src="https://www.youtube.com/embed/${ fn:split(recent.videoUrl,'$$-')[0] }"></iframe>
												</c:if>
												<c:if test="${fn:startsWith(recent.videoUrl,'##-') }">
													<iframe width="100%" height="315" src="https://player.vimeo.com/video/${ fn:split(recent.videoUrl,'##-')[0] }"></iframe>
												</c:if>
											</c:if>
											<c:if test="${ empty recent.repostedBy}">
											<div class="col-10">
												<ul class="inline-links inline-links--style-1" style='margin-left:-4%;'>
											        <li><a href="javascript:void(0)"><i class="fa fa-heart" id="${recent.post_id}" onclick="PageWidget.doLike(event);"></i></a>
												    <span id="like${recent.post_id}">${recent.totalLikes }</span></li>
													<li><a href="javascript:void(0)"><i class="fa fa-comment"  id="${recent.post_id}" onclick="PageWidget.displayCommentWindow(event);"></i></a>
													<li><a href="javascript:void(0)"><i class="fa fa-retweet" id="${recent.post_id}"  onclick="PageWidget.displayRepostWindow(event);"></i></a></li>
													<li><i class="fa fa-envelope"></i></li>
													<li><a href="javascript:void(0)"><i class="fa fa-star-o fa-5" aria-hidden="true"></i></a></li>
													 <c:if test= "${ recent.totalComments gt  0 }">
													  <li><a href="/getcomments?postid=${recent.post_id}&offset=0">Show comments(${recent.totalComments })</a> </li>
													</c:if>
												</ul>
										    </div>
										   </c:if>
										  </div></div></div></div>
								      </c:forEach></div></div></div>
						         <jsp:include page="_rightcolumn.jsp" /></div></div></div>
				        </section></div></div></div></div>
		</div>