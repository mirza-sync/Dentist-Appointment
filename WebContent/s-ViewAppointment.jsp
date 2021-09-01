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
	<body>

		<!-- Main Wrapper -->
		<div class="main-wrapper">
		
			<!-- Header -->
			<header class="header">
				<nav class="navbar navbar-expand-lg header-nav">
					<div class="navbar-header">
						<a href="p-Index.jsp" class="navbar-brand logo">
							<img src="assets/img/Logo.png" class="img-fluid" alt="Logo">
						</a>
					</div>
					<div class="main-menu-wrapper">
						<div class="menu-header">
							<a href="index-2.html" class="menu-logo">
								<img src="assets/img/logo.png" class="img-fluid" alt="Logo">
							</a>
							<a id="menu_close" class="menu-close" href="javascript:void(0);">
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
								<h2 class="breadcrumb-title">Dashboard</h2>
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
						<%@include file="s-sidebar.jsp"%>
						<!-- / Profile Sidebar -->
						
						<div class="col-md-7 col-lg-8 col-xl-9">
							<div class="card">
								<div class="card-body pt-4">
									<!-- Tab Content -->
									<div class="tab-content pt-0">
										
										<!-- Appointment Tab -->
										<div id="pat_appointments" class="tab-pane fade show active">
											<h3>Appointment List</h3>
											<div class="card card-table mb-0">
												<div class="card-body">
													<div class="table-responsive">
														<table class="table table-hover table-center mb-0">
													    	<thead>
													    		<tr>
														    		<td>ID</td>
														    		<td>Patient</td>
														    		<td>Date</td>
														    		<td>Time</td>
														    		<td>Treatment</td>
														    		<td>Dentist</td>
														    		<td>Fee (RM)</td>
														    		<td></td>
													    		</tr>
													    	</thead>
													    	<tbody>
													    		<c:if test="${not empty appointments}">
													    		<c:forEach items="${appointments}" var="appointment">
													    			<tr>
														    			<td><c:out value="${appointment.appointmentID}"/></td>
														    			<td>
															    			<a class="text-info font-weight-bold" onclick="getPatient(${appointment.patient.patientID})">
															    				${appointment.patient.patientName}
															    			</a>
														    			</td>
															    		<td><c:out value="${appointment.appointmentDate}"/></td>
															    		<td><c:out value="${appointment.startTime}"/></td>
															    		<td><c:out value="${appointment.treatment.treatmentName}"/></td>
															    		<td><c:out value="${appointment.dentist.dentistName}"/></td>
															    		<td><c:out value="${appointment.treatment.treatmentPrice}"/></td>
															    		<td class="text-right">
																			<div class="table-action">
																				<a href="AppointmentController?action=delete&id=${appointment.appointmentID}" 
																				class="btn btn-danger">
																					<i class="fas fa-trash"></i> Cancel
																				</a>
																			</div>
																		</td>
															    	</tr>
													    		</c:forEach>
													    		</c:if>
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
		<div class="modal fade" id="pModal" role="dialog">
		  <div class="modal-dialog modal-dialog-centered" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h3 class="modal-title">Patient : <span id="pTitle"></span></h3>
		        <button type="button" class="close" data-dismiss="modal">
		          <span>&times;</span>
		        </button>
		      </div>
		      <div class="modal-body pt-4 lead">
		        <p>Phone: <span id="pPhone"></span></p>
		        <p>Email: <span id="pEmail"></span></p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
		      </div>
		    </div>
		  </div>
		</div>
	  
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
		
<script type="text/javascript">
function getPatient(id) {
	var pid = id;
	console.log("The ID: "+pid);
	
	$.ajax({
		type: 'get',
		headers : {
			Accept : "application/json; charset=utf-8",
			"Content-Type" : "application/json; charset=utf-8"
		},
		url : 'PatientController',
		data : {
			action : "getPatient",
			id: pid
		},
		dataType: "json",
		success: function(response) {
			console.log(response);
			$('#pTitle').html(response.patientName);
			$('#pPhone').html(response.patientPhoneNo);
			$('#pEmail').html(response.patientEmail);
			$('#pModal').modal('show')
		},
	});
}
</script>
	</body>

</html>