<%@ page import="ABS.MODEL.Patient" %>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	
	if (session.getAttribute("patient") == null) {
		request.getRequestDispatcher("p-PatientLogin.jsp").forward(request, response);
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Login expired');");
		out.println("</script>");
	}
	
	Patient patient = (Patient) session.getAttribute("patient");
%>
<div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar">
	<div class="profile-sidebar">
		<div class="widget-profile pro-widget-content">
			<div class="profile-info-widget">
				<a href="#" class="booking-doc-img">
					<img src="assets/img/patients/patient.jpg" alt="User Image">
				</a>
				<div class="profile-det-info">
					<h3>${patient.patientName}</h3>
					<div class="patient-details">
						<h5 class="mb-0"><i class="fas fa-user"></i> ${role}</h5>
					</div>
				</div>
			</div>
		</div>
		<div class="dashboard-widget">
			<nav class="dashboard-menu">
				<ul>
					<li class="active">
						<a href="p-Index.jsp">												
							<span>Dashboard</span>
						</a>
					</li>
					<li>
						 <a href="PatientController?action=viewProfile&id=<%= patient.getPatientID() %>">My Profile</a>
					<li>
						<a href="AppointmentController?action=patientviewall&id=<%= patient.getPatientID() %>">											
							<span>My Appointment</span>
						</a>
					</li>
					<li>
						<a href="p-PatientLogin.jsp">
							<span>Logout</span>
						</a>
					</li>
				</ul>
			</nav>
		</div>

	</div>
</div>