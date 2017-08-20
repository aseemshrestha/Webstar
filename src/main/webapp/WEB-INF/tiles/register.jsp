<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title><tiles:insertAttribute name="title" /></title>
<div class="slice-lg has-bg-cover bg-size-cover"
	style="">
	<span class="mask mask-dark--style-2"></span>
	<div class="container">
	
		<div
			class="row cols-xs-space align-items-center text-center text-md-left">
			<div class="col-lg-6 offset-lg-3 col-md-10 offset-md-1">
				<div class="form-card form-card--style-2 z-depth-3-top">
					<div class="form-header text-center">
						<div class="form-header-icon">
							<i class="icon ion-log-in"></i>
						</div>
					</div>
					<div class="form-body">

						<div class="text-center px-2">
							<h4 class="heading heading-4 strong-400 mb-4">Sign in to
								your account</h4>
								<c:if test="${not empty registrationSuccess}">
    								<div class="alert alert-warning"><c:out value="${registrationSuccess}"/> </div>
								</c:if>
								
								
						</div>
 
						<form:form modelAttribute="userDetails" class="form-default mt-4" data-toggle="validator" role="form">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">*First name</label>
										 <small><form:errors path="firstName" style="color:red"/></small>
										 <form:input path="firstName" type="text" class="form-control form-control-lg" />
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">*Last name</label> 
										<small><form:errors path="lastName" style="color:red"/></small>
										<form:input path="lastName" type="text" class="form-control form-control-lg" />
										<form:input path="ipAddress" type="hidden" class="form-control form-control-lg" />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">*Email</label>
										 <small><form:errors path="email" style="color:red"/></small>
										 <form:input path="email" type="email" class="form-control form-control-lg" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Phone</label> 
										<form:input path="phone" type="text" class="form-control form-control-lg" />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">*Password</label> 
										<small><form:errors path="password" style="color:red"/>
										<c:if test="${not empty passwordMismatch}">
    							    	   <small><span style="color:red"><c:out value="${passwordMismatch}"/></span></small>
								        </c:if>
								</small>
										<form:input path="password" type="password" class="form-control form-control-lg" />
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group has-feedback">
										<label class="control-label">*Confirm Password</label> 
										<small><form:errors path="passwordConfirm" style="color:red"/></small>
										<form:input path="passwordConfirm" type="password" class="form-control form-control-lg"/>
									</div>
								</div>
							</div>

							<div class="mt-1 ">
								<small class="">By clicking "Sign up" you agree to our
									terms and conditions</small>
							</div>

							<button type="submit" class="btn btn-styled  btn-base-1 mt-4"> Register</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>