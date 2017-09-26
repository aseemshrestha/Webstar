<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<section class="sct-color-1">
<div class="container-fluid no-padding">
	<div class="row row-no-padding">
		<div class="col-lg-4" id="div_properties_search">
			<div class="card card-inverse no-border no-radius">
				<div class="card-body py-5 px-4">
					<h3 class="heading heading-5 strong-500 text-capitalize">Already have an account?</h3>
					<h4 class="heading heading-sm strong-400 text-normal c-gray-light">Sign in</h4>

					<c:if test="${param.loginError ne null}">
						<div class="alert alert-warning">Either username or password is invalid.</div>
					</c:if>
					<form class="form-inverse mt-4" data-toggle="validator" role="form" method="post" action="/myhome">
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group has-feedback">
									<label class="text-uppercase">Email</label> <input class="form-control form-control-lg" type="text" name="email" placeholder="Email"> 
									<span class="help-block with-errors"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group has-feedback">
									<label class="text-uppercase">Password</label>
									 <input class="form-control form-control-lg" type="password" name="password" placeholder="Password"> 
									 <span class="help-block with-errors"></span>
								</div>
							</div>
						</div>
						<button type="submit" class="btn btn-styled btn-lg btn-block btn-base-1 mt-4">Log Me In</button>
						<br />
						<h4 class="heading heading-sm strong-400 text-normal c-gray-light">
							<a href="/forgotpassword">Forgot Password?</a>
						</h4>
						<h4 class="heading heading-sm strong-400 text-normal c-gray-light">
							<a href="/register">Open Account?</a>
						</h4>
					</form>
				</div>
			</div>
		</div>

		<div class="col-lg-8">
			<div class="swiper-js-container background-image-holder">
				<div class="swiper-container" data-swiper-autoplay="true"
					data-swiper-effect="coverflow" data-swiper-items="1"
					data-swiper-space-between="0">
					<div class="swiper-wrapper">
						<!-- Slide -->
						<div class="swiper-slide" data-swiper-autoplay="10000">
							<div
								class="slice px-3 holder-item holder-item-light has-bg-cover bg-size-cover same-height"
								data-same-height="#div_properties_search"
								style="background-image: url(../img/backgrounds/slider/img-4.jpg); background-position: bottom bottom;">
								<span class="mask mask-dark--style-2"></span>
								<div class="container d-flex align-items-center no-padding">
									<div class="col">
										<div
											class="row row-cols-xs-spaced align-items-center text-center text-md-left">
											<div class="col-12 col-md-7 col-lg-7">
												<h2 class="heading heading-2 strong-500 c-white">
													Webstar for: <span id="type_1" class="type-this c-base-5"
														data-type-this="entertainment, fun, connection, knowledge"></span>
												</h2>
												<p class="mt-3 c-white">Experience the please of Webstar</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Slide -->
						<div class="swiper-slide" data-swiper-autoplay="10000">
							<div
								class="slice px-3 holder-item holder-item-light has-bg-cover bg-size-cover same-height"
								data-same-height="#div_properties_search"
								style="background-image: url(../img/backgrounds/slider/img-2.jpg); background-position: bottom bottom;">
								<span class="mask mask-dark--style-2"></span>
								<div class="container d-flex align-items-center no-padding">
									<div class="col">
										<div
											class="row row-cols-xs-spaced align-items-center text-center text-md-left">
											<div class="col-12 col-md-7 col-lg-7">
												<h2 class="heading heading-2 strong-500 c-white">
													Webstar for: <span id="type_2" class="type-this c-base-5"
														data-type-this="sharing, community, knowledge and many more"></span>
												</h2>
												<p class="mt-3 c-white">Enjoy surfing the Webstar</p>
												<a href="/register"
													class="btn btn-styled btn-sm btn-base-1 btn-circle mt-4">Register
													Now</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Add Arrows -->
					<div class="swiper-button swiper-button-next"></div>
					<div class="swiper-button swiper-button-prev"></div>
				</div>
			   </div>
		   </div>
	    </div>
    </div>
</section>


<section class="slice sct-color-1">
<div class="container">
	<div class="section-title section-title--style-1 text-center mb-4">
		<h3 class="section-title-inner"> <span>Recently updated posts..</span></h3>
		<span class="section-title-delimiter clearfix"></span>
	</div>

	<span class="clearfix"></span>
	<div class="fluid-paragraph fluid-paragraph-sm c-gray-light strong-300 text-center">
		Let's join and let the fun begin...</div> <span class="space-xs-md"></span>
    </div>
	<div class="row cols-xs-space cols-sm-space cols-md-space">
		<c:forEach var="country" items="${recentPostsHome}">
		 
			<c:forEach var="c" items="${country.value}">
				<div class="col-lg-3 col-md-6">
					<div class="block block--style-3">
						<div class="block-image relative">
							<c:if test="${not empty c.videoUrl}">
							    <c:if test="${fn:startsWith(c.videoUrl,'$$-') }">
								  <iframe width="100%" height="215" src="https://www.youtube.com/embed/${ fn:split(c.videoUrl,'$$-')[0] }" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
								</c:if>
								
								<c:if test="${fn:startsWith(c.videoUrl,'##-') }">
									<iframe width="100%" height="215" src="https://player.vimeo.com/video/${ fn:split(c.videoUrl,'##-')[0] }" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
								</c:if>
						   </c:if>
						</div>
						<div class="block-body">
						  <span class="stars" data-rating="${c.avgRatings}" data-num-stars="5" ></span>
		                       <c:if test="${c.avgRatings gt  0 }">
		                           <small>${c.avgRatings} / 5 avg rating</small>
		                       </c:if>
		                       <c:if test="${c.avgRatings eq  0 }">
		                           <small>Not rated yet.</small>
		                       </c:if>
							 <c:if test="${c.imageUrl ne null}">
								<div class="view view-first"> <a href="#"> <img src="../${c.imageUrl}"></a></div>
							</c:if><br />
							<small class="text-italic">${c.timeLapse}</small>
							  <h3 class="heading heading-6 strong-500 text-normal"> 
							     <a href="/byuser?uid=${c.userDetails.id}&offset=0&repost=0">${c.userDetails.username} - ${c.userDetails.firstName} ${c.userDetails.lastName}</a>
							     <a href="/bycategorypage?category=${c.category}&offset=0" style="float:right">${c.category}  ${c.subcategory}</a>
							  </h3>
							
							<p>${c.contents}</p>
						</div>
						<div class="block-footer py-3 b-xs-top">
							<div class="row align-items-center">
								<div class="col-16">
									<ul class="inline-links inline-links--style-1">
										<li><a href="/register"> <i class="fa fa-heart"></i>${ c.totalLikes }</a></li>
										<li><a href="/register"> <i class="fa fa-comment"></i>${ c.totalComments }</a></li>
										<li><a href="/register"> <i class="fa fa-retweet"></i>${ c.totalReposts }</a></li>
										<li><a href="/register"> <i class="fa fa-envelope"></i></a></li>
										<li><a href="/register"><i class="fa fa-star-o fa-5" aria-hidden="true"></i></a></li>
										<c:if test= "${c.totalComments gt  0 }">
										   <li><a href="/getcomments?postid=${c.id}&offset=0">Show comments(${c.totalComments })</a> </li>
										</c:if>
									</ul>
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:forEach>
	</div>



</section>
