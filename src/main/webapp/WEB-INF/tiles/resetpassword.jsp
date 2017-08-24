<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<title><tiles:insertAttribute name="title" /></title>
<div class="slice-lg has-bg-cover bg-size-cover" style="">
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
							<h4 class="heading heading-4 strong-400 mb-4">Reset Your Password..</h4>
						</div>
						 <c:if test="${not empty resetTokenError}">
    						 <div class="alert alert-warning"><c:out value="${resetTokenError}"/> </div>
    					</c:if>
    					 <c:if test="${not empty passwordResetSuccessful}">
    						 <div class="alert alert-warning"><c:out value="${passwordResetSuccessful}"/> </div>
    					</c:if>
    					
						<form action="/reset" method="post" class="form-default mt-4" data-toggle="validator" role="form">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label">New Password</label>
										<input name="password" type="password" id="fp_pass" 
											class="form-control form-control-lg" />
									</div>
								</div>
                             <input type="hidden" name="token" value="${resetToken}"/>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label">Confirm New Password</label>
										<input name="confirmpassword" type="password" id="fp_confirmpass"
											class="form-control form-control-lg" />
									</div>
								</div>

							</div>
							<button type="submit" class="btn btn-styled  btn-base-1 mt-4">SUBMIT</button>
							<br />
						    
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>