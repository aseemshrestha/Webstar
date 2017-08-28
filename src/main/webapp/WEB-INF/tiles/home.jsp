<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="sct-color-1">
<div class="container-fluid no-padding">
	<div class="row row-no-padding">
		<div class="col-lg-4" id="div_properties_search">
			<div class="card card-inverse no-border no-radius">
				<div class="card-body py-5 px-4">
					<h3 class="heading heading-5 strong-500 text-capitalize">Already
						have an account?</h3>
					<h4 class="heading heading-sm strong-400 text-normal c-gray-light">Sign
						in</h4>

                   <c:if test="${not empty loginError}">
    								<div class="alert alert-warning"><c:out value="${loginError}"/> </div>
					</c:if>
					<form class="form-inverse mt-4" data-toggle="validator" role="form" method="post" action="/myhome">
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group has-feedback">
									<label class="text-uppercase">Email</label> <input
										class="form-control form-control-lg" type="text" name="email"
										placeholder="Email"> <span
										class="help-block with-errors"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="form-group has-feedback">
									<label class="text-uppercase">Password</label> <input
										class="form-control form-control-lg" type="password" name="password"
										placeholder="Password"> <span
										class="help-block with-errors"></span>
								</div>
							</div>
						</div>
						

						<button type="submit"
							class="btn btn-styled btn-lg btn-block btn-base-1 mt-4">Log
							Me In</button>
						<br />
						<h4 class="heading heading-sm strong-400 text-normal c-gray-light">
							<a href="/forgotpassword">Forgot Password?</a>
						</h4>
						<h4 class="heading heading-sm strong-400 text-normal c-gray-light">
							<a href="/register">Open Account?</a>
						</h4>
						<div class="row">
							<div class="col-sm-12">
								
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								
							</div>
						</div>
						
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
								style="background-image: url(${contextRoot}/img/backgrounds/slider/img-4.jpg); background-position: bottom bottom;">
								<span class="mask mask-dark--style-2"></span>
								<div class="container d-flex align-items-center no-padding">
									<div class="col">
										<div
											class="row row-cols-xs-spaced align-items-center text-center text-md-left">
											<div class="col-12 col-md-7 col-lg-7">
											  <h2 class="heading heading-2 strong-500 c-white">
													 Webstar for:
				                                        <span id="type_1" class="type-this c-base-5" data-type-this="entertainment, fun, connection, knowledge"></span></h2>
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
								style="background-image: url(${contextRoot}/img/prv/real-estate/img-slider-2.jpg); background-position: bottom bottom;">
								<span class="mask mask-dark--style-2"></span>
								<div class="container d-flex align-items-center no-padding">
									<div class="col">
										<div
											class="row row-cols-xs-spaced align-items-center text-center text-md-left">
											<div class="col-12 col-md-7 col-lg-7">
												<h2 class="heading heading-2 strong-500 c-white">
													 Webstar for:
				                                        <span id="type_2" class="type-this c-base-5" data-type-this="sharing, community, knowledge and many more"></span></h2>
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
