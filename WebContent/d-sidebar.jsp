<%@ page import="ABS.MODEL.Dentist" %>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);

	if (session.getAttribute("dentist") == null) {
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Login expired');");
		out.println("</script>");
		request.getRequestDispatcher("d-DentistLogin.jsp").forward(request, response);
	}

	Dentist dentist = (Dentist) session.getAttribute("dentist");
%>
<div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar">
	<div class="profile-sidebar">
		<div class="widget-profile pro-widget-content">
			<div class="profile-info-widget">
				<a href="#" class="booking-doc-img"> <img
					src="assets/img/patients/patient.jpg" alt="User Image">
				</a>
				<div class="profile-det-info">
					<h3>${dentist.dentistName}</h3>
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
						<a href="DentistController?action=dashboard&id=${dentist.dentistID}"><span>Dashboard</span></a>
					</li>
					<li>
						<a href="DentistController?action=viewDentist&id=${dentist.dentistID}">My Profile</a>
					<li>
						<a href="DentistController?action=viewAppointment&id=${dentist.dentistID}"><span>My Appointments</span></a>
					</li>
					<li>
						<a href="d-DentistLogin.jsp"> <span>Logout</span></a>
					</li>
				</ul>
			</nav>
		</div>

	</div>
</div>