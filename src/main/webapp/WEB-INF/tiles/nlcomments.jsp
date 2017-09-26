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


<div class="body-wrap" data-template-mode="cards">
	<div id="st-container" class="st-container">
		<nav class="st-menu st-effect-1" id="menu-1">
			<div class="st-profile">
				<div class="st-profile-user-wrapper">
					<div class="profile-user-image">
						<img src="../img/prv/people/person-1.jpg" class="img-circle" />
					</div>
				</div>
			</div>
		</nav>

		<div class="st-pusher">
			<div class="st-content">
				<div class="st-content-inner">
					<!-- Top bar -->
					<div class="top-navbar align-items-center">
						<div class="container">
							<div class="row align-items-center py-3">
								<div class="col-4">
									<form class="form-default form-inline my-2 my-md-0">
										<input class="form-control mr-sm-2" type="text"placeholder="Search">
										<button class="btn btn-base-1 my-2 my-sm-0" type="submit">Search</button>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- Navbar -->
					<nav class="navbar navbar-toggleable-md  navbar--style-1 navbar-light bg-default  bg-default navbar--shadow navbar--uppercase">
						<div class="container navbar-container">
							<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbar_default" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation"><i class="ion-navicon"></i></button>
							<button class="global-search-toggler global-search-toggler-right" type="button" data-toggle="global-search"><i class="ion-search"></i></button>
            				<div class="collapse navbar-collapse justify-content-between align-items-center" id="navbar_default">
								<ul class="navbar-nav " data-hover="dropdown" data-animations="zoomIn zoomIn zoomIn zoomIn">
									<c:forEach var="category" items="${categories}" varStatus="position">
										<li class="nav-item" style="padding: 5px 10px;!important">
										   <h4 class="heading heading-4 strong-500"><a href="javascript:void(0);" id="${category.value}" onclick="ThreadWidget.loadPostsByCategory(event);">
										        <c:choose>
													  <c:when test="${param.category == category.value}">
													    <strong>${category.value}</strong> 
													  </c:when>
													  <c:otherwise>
													    ${category.value}
													  </c:otherwise>
													</c:choose>
										        </a>
										   </h4>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</nav>
					<section class="slice sct-color-2" style="padding-top: 1em; padding-bottom: 1em;!important">
						<div class="profile">
							<div class="container">
								<div class="row cols-md-space cols-sm-space cols-xs-space">
									<div class="col-lg-3">
										<div class="sidebar">
											<div class="widget">
												<div class="card card-inverse bg-base-1">
													<!-- Profile picture -->
													  <div class="profile-picture profile-picture--style-3"><a href="/register" class=""><img src="../img/prv/other/signup.png" class="img-center"> </a></div>
												 		
												</div>
											</div>
										</div>
									</div>

									<div class="col-lg-6" id="contents-div-comment">
										<div class="widget">
											<div class="card z-depth-2-top">
											<section class="slice sct-color-1" style="padding-top: 0em; padding-bottom: 0em;!important">
												 <div class="container container-xs">
						                            <div class="text-center">
						                             <br />
						                              <span class="stars" data-rating="${avgRatings}" data-num-stars="5" ></span>
			                                              <c:if test="${avgRatings gt  0 }">
			                                                <small>${avgRatings} / 5 avg rating</small>
			                                              </c:if>
			                                              <c:if test="${avgRatings eq  0 }">
			                                                <small>Not rated yet.</small>
			                                              </c:if>
			                                             <br />
						                                <h3 class="heading heading-6 strong-400 text-normal"> ${post} </h3>
						                                <h5 class="heading heading-xs c-gray-dark text-uppercase strong-500 letter-spacing-2 mb-0 mt-1">${category}</h5>
						                                <span class="short-delimiter short-delimiter-center short-delimiter--style-1 short-delimiter-light short-delimiter-center short-delimiter-lg"></span>
						                                <ul class="inline-links inline-links--style-2 mt-1">
						                                   <li>${postedby}</li>
						                                   <li> ${totalComments} <small>comments</small></li>
						                                   <li><a href="/register"><i class="fa fa-comment"  id="${param.postid}"></i></a></li>
						                                   <li><a href="/"><i class="fa fa-arrow-left" aria-hidden="true"></i></a></li>
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
												</section>
												<br />
											     <c:if test="${not empty( commentsList ) }">
											     <ul class="block-post-comments block-post-comments--style-1">
											       <c:forEach var="recent" items="${commentsList}" varStatus="position">
													<div class="card-body">
													  <li>
                                                            <div class="block block-comment">
					                                            <div class="block-image"> <img src="../img/prv/people/person-2.jpg" class="img-circle"></div>
					                                            <div class="block-body">
					                                                <div class="block-body-inner">
					                                                    <h3 class="heading heading-6"><a href="#">${recent.commentedByName}</a></h3>
					                                                    <span class="comment-date">${recent.timeLapse}</span>
					                                                    <p class="comment-text">${recent.comments}
					                                                    <c:if test="${recent.imageUrl ne null}"><img src="../${recent.imageUrl}" style="width: 100%; top: -0px;" /></c:if>
																		<c:if test="${not empty recent.videoUrl}">
																			<c:if test="${fn:startsWith(recent.videoUrl,'$$-') }">
																				<iframe width="100%" height="315" src="https://www.youtube.com/embed/${ fn:split(recent.videoUrl,'$$-')[0] }"></iframe>
																			</c:if>
																			<c:if test="${fn:startsWith(recent.videoUrl,'##-') }">
																				<iframe width="100%" height="315" src="https://player.vimeo.com/video/${ fn:split(recent.videoUrl,'##-')[0] }"></iframe>
																			</c:if>
																		</c:if>
					                                                    </p>
					                                                </div>
					                                            </div>
					                                        </div>
					                                    </li>
					                                    <hr />
													 </div>
											   	    </c:forEach>
											   	    </ul>
										     	   </c:if>
											</div>
										</div>
									</div>
								
								</div>
							</div>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
</div>