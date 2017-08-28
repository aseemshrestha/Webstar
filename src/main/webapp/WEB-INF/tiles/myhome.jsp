<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="name" value="${fn:split(nameEmail,'_')[1]}" />
<h1>
	Welcome
	<c:out value='${name}' />
	<a href="javascript:void(0)" class="btn btn-styled btn-base-1 btn-circle" id="myhome_post">POST</a>
</h1>

<jsp:include page="post.jsp"/>