<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="robots" content="index, follow">

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<script src="${contextRoot}/js/jquery.min.js"></script>
<script src="${contextRoot}/js/pace.min.js"></script>
<link rel="stylesheet" href="${contextRoot}/css/pace-minimal.css" type="text/css">

<!-- Bootstrap -->
<link rel="stylesheet" href="${contextRoot}/css/bootstrap.min.css" type="text/css">

<!-- Fonts -->
<link href="https://fonts.googleapis.com/css?family=Quicksand:400,500,700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Slabo+27px" rel="stylesheet">

<!-- Plugins -->
<link rel="stylesheet" href="${contextRoot}/css/swiper.min.css">
<link rel="stylesheet" href="${contextRoot}/css/animate.min.css" type="text/css">
<link rel="stylesheet" href="${contextRoot}/css/lightgallery.min.css">

<!-- Icons -->
<link rel="stylesheet" href="${contextRoot}/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="${contextRoot}/css/ionicons.min.css" type="text/css">
<link rel="stylesheet" href="${contextRoot}/css/line-icons.css" type="text/css">
<link rel="stylesheet" href="${contextRoot}/css/line-icons-pro.css" type="text/css">

<!-- Global style (main) -->
<link id="stylesheet" type="text/css" href="${contextRoot}/css/global-style.css" rel="stylesheet" media="screen">
<!-- Favicon -->
<link href="${contextRoot}/img/favicon.png" rel="icon" type="image/png">


<title><tiles:insertAttribute name="title" /></title>

</head>
<body>
	<div class="body-wrap body-boxed--no-margin">
		<div id="st-container" class="st-container">
			<div class="st-pusher">
				<div class="st-content">
					<div class="st-content-inner">
						<tiles:insertAttribute name="nav" />
                        <tiles:insertAttribute name="content" />
                      	<tiles:insertAttribute name="footer-nonloggedin" />
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>

<!-- SCRIPTS -->
<a href="#" class="back-to-top btn-back-to-top"></a>

<!-- Core -->

<script src="${contextRoot}/js/tether.min.js"></script>
<script src="${contextRoot}/js/bootstrap.min.js"></script>
<script src="${contextRoot}/js/jquery.easing.js"></script>
<script src="${contextRoot}/js/slidebars.js"></script>
<script src="${contextRoot}/js/classie.js"></script>

<!-- Bootstrap Extensions -->
<script src="${contextRoot}/js/bootstrap-dropdown-hover.js"></script>
<script src="${contextRoot}/js/bootstrap-growl.min.js"></script>
<script src="${contextRoot}/js/scrollpos-styler.js"></script>

<!-- Plugins -->
<script src="${contextRoot}/js/flatpickr.min.js"></script>
<script src="${contextRoot}/js/footer-reveal.min.js"></script>
<script src="${contextRoot}/js/swiper.min.js"></script>
<script src="${contextRoot}/js/paraxify.min.js"></script>
<script src="${contextRoot}/js/viewportchecker.min.js"></script>
<script src="${contextRoot}/js/jquery.countTo.js"></script>
<script src="${contextRoot}/js/jquery.countdown.min.js"></script>
<script src="${contextRoot}/js/typed.min.js"></script>
<script src="${contextRoot}/js/instafeed.min.js"></script>
<script src="${contextRoot}/js/jquery.gradientify.min.js"></script>
<script src="${contextRoot}/js/nouislider.min.js"></script>

<!-- Isotope -->
<script src="${contextRoot}/js/isotope.min.js"></script>
<script src="${contextRoot}/js/imagesloaded.pkgd.min.js"></script>

<!-- Light Gallery -->
<script src="${contextRoot}/js/lightgallery.min.js"></script>
<script src="${contextRoot}/js/lg-thumbnail.min.js"></script>
<!-- 
<script src="${contextRoot}/js/lg-video.js"></script>
-->


<!-- App JS -->
<script src="${contextRoot}/js/wpx.app.js"></script>
<script src="${contextRoot}/js/webstar.posts.js"></script>

<script src="${contextRoot}/js/webstar.global.js"></script>


</html>