<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<!-- doccure/patient-dashboard.html  30 Nov 2019 04:12:16 GMT -->
<head>
<meta charset="utf-8">
<title>Dentist Dashboard</title>
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

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
			<script src="assets/js/html5shiv.min.js"></script>
			<script src="assets/js/respond.min.js"></script>
		<![endif]-->

</head>
<body>

	<!-- Main Wrapper -->
	<div class="main-wrapper">

		<!-- Header -->
		<header class="header">
			<nav class="navbar navbar-expand-lg header-nav">
				<div class="navbar-header">
					<a href="d-Index2.jsp" class="navbar-brand logo"> <img
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
						<nav aria-label="breadcrumb" class="page-breadcrumb"></nav>
						<h2 class="breadcrumb-title">Dentist Dashboard</h2>
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
					<%@include file="d-sidebar.jsp"%>
					<!-- / Profile Sidebar -->

					<div class="col-md-7 col-lg-8 col-xl-9">
						<div class="card">
							<div class="card-body pt-0">


								<!-- Tab Content -->
								<div class="tab-content pt-0">

									<!-- Appointment Tab -->
									<div id="pat_appointments" class="tab-pane fade show active">
										<h3 class="mt-3">Today's Appointments</h3>
										<div class="card card-table mb-0 mt-3">
											<div class="card-body">
												<div class="table-responsive">
													<table class="table table-hover table-center mb-0">
														<thead>
															<tr>
																<th>ID</th>
																<th>Appointment Date</th>
																<th>Time</th>
																<th>Patient</th>
																<th>Treatment</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${appointments}" var="appointment">
															<tr>
																<td>${appointment.appointmentID}</td>
																<td>${appointment.appointmentDate}</td>
																<td>${appointment.startTime}</td>
																<td>${appointment.patient.patientName}</td>
																<td>${appointment.treatment.treatmentName} (${appointment.treatment.treatmentDuration}H)</td>
															</tr>
															</c:forEach>
														</tbody>
													</table>
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