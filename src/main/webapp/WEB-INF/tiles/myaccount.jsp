<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<title>My ACCOUNT</title>
<c:set var="name" value="${fn:split(nameEmail,'###')[1]}" />
<c:set var="email" value="${fn:split(nameEmail,'###')[0]}" />
<h1>
	<c:out value='${name}' />
	<c:out value='${email}' />
</h1>
