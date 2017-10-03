<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="col-lg-3">
										<div class="sidebar">
											<div class="widget">
												<div class="card card-inverse bg-base-1">
													<!-- Profile picture -->
													<div class="profile-picture profile-picture--style-2">
														<img src="../img/prv/people/person-1.jpg" class="img-center"> <a href="#" class="btn-aux">
															<i class="ion ion-edit"></i>
														</a>
													</div>

														<!-- Profile details -->
													<div class="profile-details">
													    <c:if test = "${param.un eq null}">
													      <h2 class="heading heading-3 strong-500 profile-name"><c:out value='${name}' /></h2>
													    </c:if>
														<c:if test = "${param.un ne name}">
													      <h2 class="heading heading-3 strong-500 profile-name"><c:out value='${param.un}' /></h2>
													      <div class="profile-connect mt-">
															<a href="#" class="btn btn-styled btn-block btn-rounded btn-base-5">Follow</a>
															<a href="#" class="btn btn-styled btn-block btn-rounded btn-base-2">Send message</a>
													      </div>
													    </c:if>
													</div>


													

													<!-- Profile stats -->
													<div class="profile-stats clearfix">
														<div class="stats-entry">
															<span class="stats-count">180</span> <span class="stats-label text-uppercase">Projects</span>
														</div>
														<div class="stats-entry">
															<span class="stats-count">1.3K</span> <span class="stats-label text-uppercase">Followers</span>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>