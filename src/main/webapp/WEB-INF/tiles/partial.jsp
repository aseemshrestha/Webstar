
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:forEach var="recent" items="${recentPosts}" varStatus="position">
	<div class="card-body">
		<medium> <strong>${ recent.userDetails.firstName} ${ recent.userDetails.lastName}</strong>
		<small>${recent.timeLapse}</small> <a href="#">${recent.category}</a>
		<a href="#">${recent.subcategory}</a> </medium>
		<p class="mb-4">
			${recent.contents} <br />
			<c:if test="${recent.imageUrl ne null}">
				<img src="../${recent.imageUrl}" style="width: 100%; top: -0px;" />
			</c:if>
			<c:if test="${not empty recent.videoUrl}">
				<c:if test="${fn:startsWith(recent.videoUrl,'$$-') }">
					<iframe width="100%" height="315"
						src="https://www.youtube.com/embed/${ fn:split(recent.videoUrl,'$$-')[0] }"></iframe>
				</c:if>
				<c:if test="${fn:startsWith(recent.videoUrl,'##-') }">
					<iframe width="100%" height="315"
						src="https://player.vimeo.com/video/${ fn:split(recent.videoUrl,'##-')[0] }"></iframe>
				</c:if>

			</c:if>
		</p>
		<hr />
	</div>
</c:forEach>