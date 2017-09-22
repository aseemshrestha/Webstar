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
										   <h5 class="heading heading-5 strong-500"><a href="javascript:void(0);" id="${category.value}" onclick="ThreadWidget.loadPostsByCategory(event);">
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
												</div>
											</div>
										</div>
									</div>

									<div class="col-lg-6" id="contents-div">
										<div class="widget">
											<div class="card z-depth-2-top">
											      <c:forEach var="recent" items="${categoriescomments}" varStatus="position">
													<div class="card-body" style="padding-left:0.5em;padding-top:0.5em;!important">
													        <div class="block block-comment" style="margin-bottom: 0rem;!important">
					                                            <div class="block-image">
					                                                <img src="../img/prv/people/brin.jpg" class="img-square">
					                                            </div>
					                                          <div class="block-body">   
					                                            <div class="block-body-inner"><h3 class="heading heading-6"><a href="/byuser?uid=${recent.userDetails.id}&offset=0&repost=0">${ recent.userDetails.username}</a> - <a href="/byuser?uid=${recent.userDetails.id}&offset=0&repost=0">${ recent.userDetails.firstName}</a> <a href="/byuser?uid=${recent.userDetails.id}&offset=0&repost=0">${ recent.userDetails.lastName}</a> 
					                                            <small>${recent.timeLapse}</small>
					                                            <span style="float:right"> <a href="/bycategorypage?category=${recent.category}&offset=0">${recent.category}</a> <a href="/bycategorypage?category=${recent.category}&offset=0">${recent.subcategory}</a></span>
					                          			       </h3>
					                                            <p class="mb-4" style="margin-bottom:0px;!important">${recent.contents}</p>
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
																	<div class="col-10">
																		<ul class="inline-links inline-links--style-1" style='margin-left:-4%;'>
																			<li><a href="javascript:void(0)"><i class="fa fa-heart" id="${recent.id}" onclick="PageWidget.doLike(event);"></i></a>
																		    <span id="like${recent.id}">${recent.totalLikes }</span></li>
																			<li><a href="javascript:void(0)"><i class="fa fa-comment"  id="${recent.id}" onclick="PageWidget.displayCommentWindow(event);"></i></a>
																			<li><a href="javascript:void(0)"><i class="fa fa-retweet" id="${recent.id}"  onclick="PageWidget.displayRepostWindow(event);"></i></a></li>
																			<li><i class="fa fa-envelope"></i></li>
																			<li><i class="fa fa-share" aria-hidden="true"></i></li>
																			 <c:if test= "${ recent.totalComments gt  0 }">
																			  <li><a href="/getcomments?postid=${recent.id}&offset=0">Show comments(${recent.totalComments })</a> </li>
																			</c:if>
																		</ul>
																   </div>
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