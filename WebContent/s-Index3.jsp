<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Staff Dashboard</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
	
	<!-- Favicons -->
	<link href="assets/img/favicon.png" rel="icon">
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	
	<!-- Fontawesome CSS -->
	<link rel="stylesheet" href="assets/plugins/fontawesome/css/fontawesome.min.css">
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
						<a href="#" class="navbar-brand logo">
							<img src="assets/img/Logo.png" class="img-fluid" alt="Logo">
						</a>
					</div>
				</nav>
			</header>
			<!-- /Header -->
			
			<!-- Breadcrumb -->
			<div class="breadcrumb-bar">
				<div class="container-fluid">
					<div class="row align-items-center">
						<div class="col-md-12 col-12">
							<h2 class="breadcrumb-title">Staff Dashboard</h2>
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
						<%@include file="s-sidebar.jsp"%>
						<!-- / Profile Sidebar -->
						
						<div class="col-md-7 col-lg-8 col-xl-9">
							<div class="card">
								<div class="card-body pt-4">
								<fieldset>
								<h3>Make New Appointment</h3>
								<hr>
								<form method="get" action="AppointmentController">
									<input type="hidden" name="action" value="create">
									<input type="hidden" name="role" value="${role}">
									<div class="form-group">
										<h4>Patient</h4>
										<select name="pid" class="form-control" required>
											<option value="">Choose Patient</option>
											<c:forEach items="${patients}" var="patient">
												<option value="${patient.patientID}">${patient.patientName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<h4>Date</h4>
										<input type="date" name="date" class="form-control" required>
									</div>
									<button class="btn btn-success float-right">Choose Date</button>
								</form>
								</fieldset>
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
        <script src="assets/plugins/theia-sticky-sidebar/theia-sticky-sidebar.js"></script>
		
		<!-- Custom JS -->
		<script src="assets/js/script.js"></script>
		
	</body>


</html>