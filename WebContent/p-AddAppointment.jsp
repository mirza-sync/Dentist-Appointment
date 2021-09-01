<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<!-- doccure/patient-dashboard.html  30 Nov 2019 04:12:16 GMT -->
<head>
<meta charset="utf-8">
<title>Patient - New Appointment</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">

<!-- Fontawesome CSS -->
<link rel="stylesheet"
	href="assets/plugins/fontawesome/css/fontawesome.min.css">
<link rel="stylesheet" href="assets/plugins/fontawesome/css/all.min.css">

<!-- Main CSS -->
<link rel="stylesheet" href="assets/css/style.css">

</head>
<body>

	<!-- Main Wrapper -->
	<div class="main-wrapper">

		<!-- Header -->
		<header class="header">
			<nav class="navbar navbar-expand-lg header-nav">
				<div class="navbar-header">
					<a href="#" class="navbar-brand logo"> <img
						src="assets/img/Logo.png" class="img-fluid" alt="Logo">
					</a>
				</div>
				<div class="main-menu-wrapper">
					<div class="menu-header">
						<a href="index-2.html" class="menu-logo"> <img
							src="assets/img/logo.png" class="img-fluid" alt="Logo">
						</a> <a id="menu_close" class="menu-close" href="javascript:void(0);">
							<i class="fas fa-times"></i>
						</a>
					</div>

				</div>
			</nav>
		</header>
		<!-- /Header -->

		<!-- Breadcrumb -->
		<div class="breadcrumb-bar">
			<div class="container-fluid">
				<div class="row align-items-center">
					<div class="col-md-12 col-12">
						<div class="row d-flex justify-content-between">
							<h2 class="breadcrumb-title">New Appointment</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /Breadcrumb -->

		<!-- Page Content -->
		<div class="content">
			<div class="container-fluid">

				<div class="row">

					<!-- Profile Sidebar -->
					<%@include file="p-sidebar.jsp"%>
					<!-- / Profile Sidebar -->

					<div class="col-md-7 col-lg-8 col-xl-9">
						<div class="card">
							<div class="card-body pt-4">

								<!-- Tab Content -->
								<div class="tab-content pt-0">

									<!-- Appointment Tab -->
									<div id="pat_appointments" class="tab-pane fade show active">
										<div class="card card-table mb-0">
											<div class="card-title m-3">
												<fmt:parseDate pattern="yyyy-MM-dd" value="${date}" var="formattedDate" type="date" />
												<h3>Date : <fmt:formatDate value="${formattedDate}" pattern="dd-MM-yyyy"/></h3>
												<h3>Choose Time</h3>
											</div>
											<div class="card-body m-3">
												<div class="row">
													<c:forEach items="${times}" var="time" varStatus="element">
														<div class="col-3">
															<form method="post" action="AppointmentController">
															<input type="hidden" name="action" value="add">
																<div class="card shadow-sm">
																<c:choose>
																	<c:when test="${time > 0}">
																		<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${time}" var="newtime"/>
																		<div class="card-body"><h4 class="m-2">${newtime}</h4></div>
																		<hr>
																		<input type="hidden" id="time" name="time" value="${newtime}">
																		<input type="hidden" id="date" name="date" value="${date}">
																		<c:choose>
																			<c:when test="${empty times[element.index+1]}">
																				<div class="form-group mx-1">
																					<label class="d-block">Treatment</label>
																					<select id="tid" name="tid" class="w-100" required>
																						<option value="">Choose Treatment</option>
																						<c:forEach items="${treats}" var="treat">
																							<option value="${treat.treatmentID}">${treat.treatmentName} (${treat.treatmentDuration}H)</option>
																						</c:forEach>
																					</select>
																				</div>
																			</c:when>
																			<c:when test="${times[element.index+1] >= 0}">
																				<div class="form-group mx-1">
																					<label class="d-block">Treatment</label>
																					<select id="tid" name="tid" class="w-100" required>
																						<option value="">Choose Treatment</option>
																						<c:forEach items="${treats}" var="treat">
																							<option value="${treat.treatmentID}">${treat.treatmentName} (${treat.treatmentDuration}H)</option>
																						</c:forEach>
																					</select>
																				</div>
																			</c:when>
																			<c:otherwise>
																				<div class="form-group mx-1">
																					<label class="d-block">Treatment</label>
																					<select id="tid" name="tid" class="w-100" required>
																						<option value="">Choose Treatment</option>
																						<c:forEach items="${treat1H}" var="h1">
																							<option value="${h1.treatmentID}">${h1.treatmentName} (${h1.treatmentDuration}H)</option>
																						</c:forEach>
																					</select>
																				</div>
																			</c:otherwise>
																		</c:choose>
																			<div class="form-group mx-1">
																				<label class="d-block">Dentist</label>
																				<select name="dentistid" class="w-100" required>
																					<option value="">Choose Dentist</option>
																					<c:forEach items="${dentists}" var="dentist">
																						<option value="${dentist.dentistID}">${dentist.dentistName}</option>
																					</c:forEach>
																				</select>
																			</div>
																		<div class="card-footer">
																			<button type="submit" class="btn btn-sm btn-primary float-right">Book</button>
																		</div>
																	</c:when>
																	<c:otherwise>
																		<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${time}" var="newtime"/>
																		<div class="card-body"><h4 class="m-2">${newtime}</h4></div>
																		<div class="card-footer">
																			<button type="button" class="btn btn-sm btn-light float-right disabled">Booked</button>
																		</div>
																	</c:otherwise>
																</c:choose>
																</div>
															</form>
														</div>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>
									<!-- /Appointment Tab -->

								</div>
								<!-- Tab Content -->

							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
		<!-- /Page Content -->



	</div>
	<!-- /Main Wrapper -->

	<!-- jQuery -->
	<script src="assets/js/jquery.min.js"></script>

	<!-- Bootstrap Core JS -->
	<script src="assets/js/popper.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!-- Sticky Sidebar JS -->
	<script src="assets/plugins/theia-sticky-sidebar/ResizeSensor.js"></script>
	<script
		src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>

	<!-- Custom JS -->
	<script src="assets/js/script.js"></script>

</body>


</html>