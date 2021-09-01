<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 
<html lang="en">
	
<!-- doccure/patient-dashboard.html  30 Nov 2019 04:12:16 GMT -->
<head>
		<meta charset="utf-8">
		<title>Doccure</title>
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
	<body class="bg-white">
			<div class="container d-flex h-100">
				<div class="row align-self-center w-100">
					<div class="col-6 offset-3">
						<div class="jumbotron shadow-sm px-5">
							<div class="text-center">
								<h2>Welcome</h2>
								<p class="lead">Pro Heal Dental Specialist</p>
							</div>
							<hr>
							<p>Choose login :</p>
							<div class="d-flex justify-content-between my-4">
								<a href="p-PatientLogin.jsp" class="btn btn-success">Patient</a>
								<a href="s-StaffLogin.jsp" class="btn btn-success">Staff</a>
								<a href="d-DentistLogin.jsp" class="btn btn-success">Dentist</a>
							</div>
							<hr>
							<div>
								<a href="p-PatientRegister.jsp" class="btn btn-info float-right">Register New Patient</a>
							</div>
						</div>
					</div>
				</div>
			</div>
	  
	</body>


</html>