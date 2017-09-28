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
						<a href="/register" class=""><img src="../img/prv/other/signup.png" class="img-center"> </a>
					</div>
						
				</div>
			</div>
       </nav>
       
		<div class="st-pusher">
			<div class="st-content">
				<div class="st-content-inner">
					<div class="top-navbar align-items-center">
						<div class="container">
							<div class="row align-items-center py-3">
								<div class="col-4">
									<form class="form-default form-inline my-2 my-md-0">
										<input class="form-control mr-sm-2" type="text" id="searchuname" placeholder="Search" onkeypress="PageWidget.doSearch(event);">
										<button class="btn btn-base-1 my-2 my-sm-0" type="submit">Search User</button>
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
										   <h5 class="heading heading-5 strong-500"><a href="javascript:void(0);" id="${category.value}" onclick="ThreadWidget.loadPostsByCategory1(event);">
										        <c:choose>
													  <c:when test="${param.category == category.value}">
													    <strong>${category.value}</strong> 
													  </c:when>
													  <c:otherwise>
													    ${category.value}
													  </c:otherwise>
													</c:choose>
										        </a>
										   </h5>
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
												    	<!-- Profile details -->
														<div class="profile-details">
															<h2 class="heading heading-3 strong-500 profile-name"><c:out value='${name}' /></h2>
															<h3 class="heading heading-6 strong-400 profile-occupation mt-3"><c:out value='${email}' /></h3>
														</div>
														<!-- Profile connect -->
														<div class="profile-connect mt-">
															<a href="/register" class="btn btn-styled btn-block btn-rounded btn-base-5">Follow</a>
															<a href="/register" class="btn btn-styled btn-block btn-rounded btn-base-2">Send message</a>
														</div>
	
														<!-- Profile stats -->
														<div class="profile-stats clearfix">
												            <div class="stats-entry">
												                <span class="stats-count">180</span>
												                <span class="stats-label text-uppercase">Posts</span>
												            </div>
												            <div class="stats-entry">
												                <span class="stats-count">1.3K</span>
												                <span class="stats-label text-uppercase">Followers</span>
												            </div>
												        </div>
												</div>
											</div>
											 
										</div>
									</div>

									<div class="col-lg-6" id="contents-div">
										<div class="widget">
											<div class="card z-depth-2-top">
											
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
					                                               <a href="javascrip:void(0)" id="" style="color:#007aff">${ recent.username } - ${ recent.firstName} ${ recent.lastName }</a> 
					                                               <small>${recent.timeLapse }</small>
					                                               <span style="float:right"> <a href="/bycategorypage?category=${recent.category}&offset=0">${recent.category}</a>
					                                               <a href="/bycategorypage?category=${recent.category}&offset=0">${recent.subcategory}</a></span>
					                                           </h3>
					                          			       <p class="mb-4" style="margin-bottom:0px;!important">${recent.contents}</p>
					                          			        <c:if test="${not empty recent.repostedBy}">
					                          			         <h3 class="heading heading-6">
																	  <a href="javascript:void(0)" style="color:#007aff">${ recent.repostedBy }</a>
																	  </h3>
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
																	        <li><a href="/register"><i class="fa fa-heart"></i></a>
																		    <span id="like${recent.post_id}">${recent.totalLikes }</span></li>
																			<li><a href="/register"><i class="fa fa-comment"  id="${recent.post_id}"></i></a>
																			<li><a href="/register"><i class="fa fa-retweet" id="${recent.post_id}"></i></a></li>
																			<li><a href="/register"><i class="fa fa-envelope"></i></a></li>
																			<li><a href="/register"><i class="fa fa-star-o fa-5" aria-hidden="true"></i></a></li>
																			 <c:if test= "${ recent.totalComments gt  0 }">
																			  <li><a href="/getcomments?postid=${recent.post_id}&offset=0">Show comments(${recent.totalComments })</a> </li>
																			</c:if>
																			
																		</ul>
																   </div>
																   </c:if>
															   </div>
													         </div>
														  </div>
													   </div>
												   </c:forEach>
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