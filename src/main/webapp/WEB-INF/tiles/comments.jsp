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

<jsp:include page="post.jsp" />
<jsp:include page="comment.jsp" />

<div class="body-wrap" data-template-mode="cards">
	<div id="st-container" class="st-container">
		<nav class="st-menu st-effect-1" id="menu-1">
			<div class="st-profile">
				<div class="st-profile-user-wrapper">
					<div class="profile-user-image">
						<img src="../img/prv/people/person-1.jpg" class="img-circle" />
					</div>
						<div class="profile-user-info">
							<span class="profile-user-name"><c:out value='${name}' /></span>
							<span class="profile-user-email"><c:out value='${email}' /></span>
						</div>
				</div>
			</div>
				<div class="st-menu-list mt-2">
					<ul>
						<li><a href="#"> <i class="ion-ios-bookmarks-outline"></i>Theme documentation</a></li>
						<li><a href="#"> <i class="ion-ios-cart-outline"></i>Purchase Tribus</a></li>
					</ul>
				</div>
				<h3 class="st-menu-title">Account</h3>
				<div class="st-menu-list">
					<ul>
						<li><a href="#"> <i class="ion-ios-person-outline"></i>
								User profile
						</a></li>
						<li><a href="#"> <i class="ion-ios-location-outline"></i>My addresses</a></li>
						<li><a href="#"> <i class="ion-card"></i> My cards</a></li>
						<li><a href="#"> <i class="ion-ios-unlocked-outline"></i>Password update</a></li>
					</ul>
				</div>
	
				<h3 class="st-menu-title">Support center</h3>
				<div class="st-menu-list">
					<ul>
						<li><a href="#"> <i class="ion-ios-information-outline"></i>About Tribus</a></li>
						<li><a href="#"> <i class="ion-ios-email-outline"></i>Contact &amp; support</a></li>
						<li><a href="#"> <i class="fa fa-camera"></i> Getting started</a></li>
					</ul>
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
							<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbar_default" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
								<i class="ion-navicon"></i>
							</button>
							<button class="global-search-toggler global-search-toggler-right" type="button" data-toggle="global-search"><i class="ion-search"></i></button>
            				<div class="collapse navbar-collapse justify-content-between align-items-center" id="navbar_default">
								<ul class="navbar-nav " data-hover="dropdown" data-animations="zoomIn zoomIn zoomIn zoomIn">
									<c:forEach var="category" items="${categories}" varStatus="position">
										<li class="nav-item" style="padding: 5px 10px;!important">
										   <h4 class="heading heading-4 strong-500"><a href="javascript:void(0);" id="${category.value}" onclick="ThreadWidget.loadPostsByCategory1(event);">
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
													
													  <div class="profile-picture profile-picture--style-3"><a href="/register" class=""><img src="../img/prv/people/people-1.png" class="img-center"> </a></div>
												 		<!-- Profile details -->
														<div class="profile-details">
															<h2 class="heading heading-3 strong-500 profile-name"><c:out value='${name}' /></h2>
															<h3 class="heading heading-6 strong-400 profile-occupation mt-3"><c:out value='${email}' /></h3>
														</div>
														<!-- Profile connect -->
														<div class="profile-connect mt-">
															<a href="#" class="btn btn-styled btn-block btn-rounded btn-base-5">Follow</a>
															<a href="#" class="btn btn-styled btn-block btn-rounded btn-base-2">Send message</a>
														</div>
														<!-- Profile stats -->
														<div class="profile-stats clearfix">
															<div class="stats-entry">
																<span class="stats-count">180</span> <span class="stats-label text-uppercase">Projects</span>
															</div>
															<div class="stats-entry">
																<span class="stats-count">1.3K</span> <span class="stats-label text-uppercase">Followers</span>
															</div>
														</div>
														<div class="profile-useful-links clearfix">
															<div class="useful-links">
																<a href="#"> <i class="icon ion-social-instagram-outline"></i></a> <a href="#"> <iclass="icon ion-social-linkedin-outline"></i>
																</a> <a href="#"> <i class="icon ion-earth"></i></a>
															</div>
														</div>
														<div class="profile-useful-links clearfix">
															<div class="useful-links">
																<a href="#"> <i class="icon ion-code-download"></i></a>
															</div>
														</div>
												</div>
											</div>
										
												<div class="widget">
													<div class="card card-inverse bg-pink">
														<div class="card-title">
															<h3 class="heading heading-6 strong-500">About me</h3>
														</div>
														<div class="card-body">
															<div class="short-info">
																<h4 class="short-info-label">Current position</h4>
																<h3 class="short-info-title">Founder, Web Developer</h3>
															</div>
															<div class="short-info">
																<h4 class="short-info-label">Previous position</h4>
																<h3 class="short-info-title">Senior Developer</h3>
															</div>
															<div class="short-info">
																<h4 class="short-info-label">Studied at</h4>
																<h3 class="short-info-title">Harvard University</h3>
															</div>
															<div class="short-info">
																<h4 class="short-info-label">Lives in</h4>
																<h3 class="short-info-title">Bucharest, Romania</h3>
															</div>
															<div class="short-info">
																<h4 class="short-info-label">Relationship</h4>
																<h3 class="short-info-title">Single</h3>
															</div>
														</div>
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
								
											<div class="col-lg-3">
												<div class="widget">
													<div class="card bg-base-2 z-depth-2-top text-center">
														<div class="card-body sct-inner">
															<h3 class="heading heading-4 strong-600">Congratulation</h3>
															<p class="mt-3">You just received 500 likes</p>
														</div>
													</div>
												</div>
												<div class="widget z-depth-2-top">
													<div class="datepicker"></div>
												</div>
												<div class="widget">
													<div class="card z-depth-2-top">
														<div class="card-body">
														  <h3 class="heading heading-4 strong-600">Trending Topics</h3>
															<div class="progress-wrapper mb-4">
																<h4 class="progress-label text-uppercase">Sports</h4>
																<h4 class="progress-percentage text-uppercase">35%</h4>
																<div class="progress progress-sm progress-inverse no-margin">
																	<div class="progress-bar bg-green" role="progressbar"
																		aria-valuenow="35" aria-valuemin="0" aria-valuemax="100"
																		style="width: 35%;"></div>
																</div>
																<small>10% more than last month</small>
															</div>
		
															<div class="progress-wrapper mb-3">
																<h4 class="progress-label text-uppercase">Women</h4>
																<h4 class="progress-percentage text-uppercase">70</h4>
																<div class="progress progress-sm progress-inverse no-margin">
																	<div class="progress-bar bg-blue" role="progressbar"
																		aria-valuenow="70" aria-valuemin="0" aria-valuemax="100"
																		style="width: 70%;"></div>
																</div>
																<small>No updates here</small>
															</div>
		
															<div class="progress-wrapper mb-3">
																<h4 class="progress-label text-uppercase">Politics</h4>
																<h4 class="progress-percentage text-uppercase">55%</h4>
																<div class="progress progress-sm progress-inverse no-margin">
																	<div class="progress-bar bg-pink" role="progressbar"
																		aria-valuenow="55" aria-valuemin="0" aria-valuemax="100"
																		style="width: 55%;"></div>
																</div>
																<small>20% more than last week</small>
															</div>
		
															<div class="progress-wrapper mb-3">
																<h4 class="progress-label text-uppercase">Gaming</h4>
		 														<h4 class="progress-percentage text-uppercase">80%</h4>
																<div
																	class="progress progress-sm progress-inverse no-margin">
																	<div class="progress-bar bg-orange" role="progressbar"
																		aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
																		style="width: 80%;"></div>
																</div>
																<small>5% more than yesterday</small>
															</div>
														</div>
													</div>
												</div>
		
												<div class="widget">
													<div class="card z-depth-2-top">
														<div class="card-body">
															<div class="short-info b-xs-bottom">
																<i class="fa fa-skype c-skype"></i>
																<div class="short-info-offset">
																	<h4 class="short-info-label">Skype</h4>
																	<h3 class="short-info-title text-normal">elisabeth.alanna</h3>
																</div>
															</div>
		
															<div class="short-info b-xs-bottom">
																<i class="fa fa-facebook-official c-facebook"></i>
																<div class="short-info-offset">
																	<h4 class="short-info-label">Facebook</h4>
																	<h3 class="short-info-title">Elisabeth Alanna</h3>
																</div>
															</div>
		
															<div class="short-info">
																<i class="fa fa-twitter c-twitter"></i>
																<div class="short-info-offset">
																	<h4 class="short-info-label">Twitter</h4>
																	<h3 class="short-info-title text-normal">elisabeth_a</h3>
																</div>
															</div>
														</div>
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