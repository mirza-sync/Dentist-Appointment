<%@ page import="ABS.MODEL.Staff" %>
<%@ page import="java.io.PrintWriter" %>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);

	if (session.getAttribute("staff") == null) {
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Login expired');");
		out.println("</script>");
		request.getRequestDispatcher("s-StaffLogin.jsp").include(request, response);
	}
	
	Staff staff = (Staff) session.getAttribute("staff");
%>

<div class="col-md-5 col-lg-4 col-xl-3 theiaStickySidebar">
	<div class="profile-sidebar">
		<div class="widget-profile pro-widget-content">
			<div class="profile-info-widget">
				<a href="#" class="booking-doc-img">
					<img src="assets/img/patients/patient.jpg" alt="User Image">
				</a>
				<div class="profile-det-info">
					<h3>${staff.staffName}</h3>
					<div class="patient-details">
						<h5 class="mb-0"><i class="fas fa-user"></i> ${role}</h5>
					</div>
				</div>
			</div>
		</div>
		<div class="dashboard-widget">
			<nav class="dashboard-menu">
				<ul>
					<li>
						<a href="StaffController?action=dashboard&id=<%= staff.getStaffID()%>">												
							<span>Dashboard</span>
						</a>
					</li>
					<li>
						<a class="collapse-item" href="StaffController?action=viewStaff&id=<%= staff.getStaffID() %>">
						 	<span>My Profile</span>
						</a>
					</li>
					<li>
						<a href="AppointmentController?action=s-appointment">													
							<span>Appointment List</span>
						</a>
					</li>
					<li>
						<a href="PatientController?action=listPatient">													
							<span>Patient List</span>
						</a>
					</li>
					<li>
						<a href="DentistController?action=listDentist">													
							<span>Dentist List</span>
						</a>
					</li>
					<li>
						<a href="TreatmentController?action=viewall">													
							<span>Treatment List</span>
						</a>
					</li>
                    <li>
						<a href="d-DentistRegister.jsp">												
							<span>Register Dentist</span>
						</a>
					</li>
					<li>
						<a href="s-StaffRegister.jsp">												
							<span>Register Staff</span>
						</a>
					</li>
					<li>
						<a href="s-StaffLogin.jsp">
							<span>Logout</span>
						</a>
					</li>
				</ul>
			</nav>
		</div>

	</div>
</div>