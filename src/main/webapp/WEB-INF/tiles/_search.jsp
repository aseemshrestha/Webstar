<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="name" value="${fn:split(nameEmail,'###')[1]}" />

<div class="top-navbar align-items-center">
	<div class="container">
		<div class="row align-items-center py-3">
			<div class="col-4">
				<form class="form-default form-inline my-2 my-md-0">
					<input class="form-control mr-sm-2" type="text" id="searchuname"
						placeholder="Search" onkeypress="PageWidget.doSearch(event);">
					<button class="btn btn-base-1 my-2 my-sm-0" type="submit">Search User</button>
				</form>
			</div>

			<div class="col-sm-3">
				<a href="javascript:void(0)"
					class="btn btn-styled btn-block btn-base-2"
					onclick="ThreadWidget.buildPostWindow(event);">SUBMIT A NEW POST</a>
			</div>

			<div class="col-sm-3">
				<div class="profile-details">
					<h2 class="heading heading-4 strong-500 profile-name">
						<small>Logged in as:</small>
						<c:out value='${name}' />
					</h2>
				</div>
			</div>
		</div>
	</div>
</div>
<nav
	class="navbar navbar-toggleable-md  navbar--style-1 navbar-light bg-default  bg-default navbar--shadow navbar--uppercase">
	<div class="container navbar-container">
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbar_default"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="ion-navicon"></i>
		</button>
		<button class="global-search-toggler global-search-toggler-right"
			type="button" data-toggle="global-search">
			<i class="ion-search"></i>
		</button>
		<div class="collapse navbar-collapse justify-content-between align-items-center"
			id="navbar_default">
			<ul class="navbar-nav " data-hover="dropdown"
				data-animations="zoomIn zoomIn zoomIn zoomIn">
				<c:forEach var="category" items="${categories}" varStatus="position">
					<li class="nav-item" style="padding: 5px 10px;!important">
						<h5 class="heading heading-5 strong-500">
							<a href="javascript:void(0);" id="${category.value}"
								onclick="ThreadWidget.loadPostsByCategory1(event);"> <c:choose>
									<c:when test="${param.category == category.value}">
										<strong>${category.value}</strong>
									</c:when>
									<c:otherwise>${category.value}</c:otherwise>
								</c:choose>
							</a>
						</h5>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</nav>