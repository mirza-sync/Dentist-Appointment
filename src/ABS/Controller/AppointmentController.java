package ABS.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ABS.DAO.AppointmentDao;
import ABS.DAO.DentistDao;
import ABS.DAO.PatientDao;
import ABS.DAO.TreatmentDao;
import ABS.MODEL.Appointment;
import ABS.MODEL.Dentist;
import ABS.MODEL.Patient;

@WebServlet("/AppointmentController")
public class AppointmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String INDEX = "p-Index.jsp";
	private String VIEW_APPOINTMENT = "";
	private String ADD_APPOINTMENT_P = "p-AddAppointment.jsp";
	private String ADD_APPOINTMENT_S = "s-AddAppointment.jsp";
	private String ALL_APPOINTMENT_P = "p-ViewAppointment.jsp";
	private String ALL_APPOINTMENT_S = "s-ViewAppointment.jsp";

	static String appointmentName, appointmentDate, startTime;
	static int appointmentID, treatmentID, patientID, dentistID, staffID;

	String forward;

	AppointmentDao appointmentDao = new AppointmentDao();
	TreatmentDao treatmentDao = new TreatmentDao();
	DentistDao dentistDao = new DentistDao();
	PatientDao patientDao = new PatientDao();

	public AppointmentController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		List<Double> slotList = new ArrayList<Double>();
		List<Double> weekdayList = Arrays.asList(9.30, 10.30, 11.30, 12.30, 13.30, 14.30, 15.30, 16.30, 17.30, 18.30,
				19.30);
		List<Double> saturdayList = Arrays.asList(9.30, 10.30, 11.30, 12.30, 13.30, 14.30, 15.30, 16.30);
		List<Double> sundayList = Arrays.asList(10.00, 11.00, 12.00, 13.00, 14.00);

		if (action.equals("view")) {
			appointmentID = Integer.parseInt(request.getParameter("aid"));
			Appointment a = appointmentDao.getAppointmentById(appointmentID);
			forward = VIEW_APPOINTMENT;
			request.setAttribute("appointment", a);
		} else if (action.equals("patientviewall")) {
			int id = Integer.parseInt(request.getParameter("id"));
			List<Appointment> a = appointmentDao.getAppointmentByPatient(id);
			forward = ALL_APPOINTMENT_P;
			request.setAttribute("appointments", a);

		} else if (action.equals("s-appointment")) {
			List<Appointment> a = appointmentDao.getAllAppointment();
			forward = ALL_APPOINTMENT_S;
			request.setAttribute("appointments", a);

		} else if (action.equals("delete")) {
			appointmentID = Integer.parseInt(request.getParameter("id"));
			appointmentDao.delete(appointmentID);
			forward = ALL_APPOINTMENT_S;
			request.setAttribute("appointments", appointmentDao.getAllAppointment());
		} else if (action.equals("create")) {
			// Get list of booked time appointment of that date
			// for each weekday list
			// 		for each booked time
			// 			if time of the date and booked time is equal
			// 				get the treatment duration of booked date
			// 				for length of treatment duration
			// 					multiply weekday time by -1
			// 				end for
			// 			end if
			// 		end for
			// end for
			String role = request.getParameter("role");
			String date = request.getParameter("date");
			List<Appointment> appArr = new ArrayList<Appointment>();
			appArr = appointmentDao.getAppointmentByDate(date);
			String day = appointmentDao.getDay(date);
			if(day.trim().equalsIgnoreCase("SATURDAY")) {
				slotList = saturdayList;
			}
			else if(day.trim().equalsIgnoreCase("SUNDAY")) {
				slotList = sundayList;
			}
			else {
				slotList = weekdayList;
			}
			for (Appointment app : appArr) {
				System.out.println("Booked : " + app.getStartTime());
			}
			for (int i = 0; i < slotList.size(); i++) {
				for (int j = 0; j < appArr.size(); j++) {
					System.out.println(slotList.get(i) + " vs. " + appArr.get(j).getStartTime() + ": "
							+ Double.compare(slotList.get(i), Double.parseDouble(appArr.get(j).getStartTime())));
					if (Double.compare(slotList.get(i), Double.parseDouble(appArr.get(j).getStartTime())) == 0) {
						int multi = appArr.get(j).getTreatment().getTreatmentDuration();
						int current = i;
						for (int k = 0; k < multi; k++) {
							System.out.println("Current: "+ current + " vs " + " Size: " + slotList.size());
							if(current < slotList.size()) {
								System.out.println(slotList.get(current) + " : make -ve");
								slotList.set(current, slotList.get(current) * -1);
								current++;
							}
						}
					}
				}
				System.out.println("=================");
			}
			System.out.println("Final time list : " + slotList.toString());
			request.setAttribute("times", slotList);
			request.setAttribute("date", date);
			request.setAttribute("treat1H", treatmentDao.get1HourTreatment());
			request.setAttribute("treats", treatmentDao.getAllTreatment());
			request.setAttribute("dentists", dentistDao.getAllDentist());
			
			if (role.equals("Patient")) {
				forward = ADD_APPOINTMENT_P;
			} else {
				int pid = Integer.parseInt(request.getParameter("pid"));
				request.setAttribute("patient", patientDao.getPatientById(pid));
				forward = ADD_APPOINTMENT_S;
			}
		} else {
			System.out.println("No action found");
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		HttpSession session = request.getSession(true);

		if (action.equals("add")) {
			Patient patient = (Patient) session.getAttribute("patient");
			patientID = patient.getPatientID();
			appointmentDate = request.getParameter("date");
			startTime = request.getParameter("time");
			treatmentID = Integer.parseInt(request.getParameter("tid"));
			dentistID = Integer.parseInt(request.getParameter("dentistid"));

			Appointment a = new Appointment();

			a.setAppointmentDate(appointmentDate);
			a.setStartTime(startTime);
			a.setPatientID(patientID);
			a.setTreatmentID(treatmentID);
			a.setDentistID(dentistID);
			a.setStaffID(0);

			appointmentDao.add(a);
			forward = ALL_APPOINTMENT_P;
			request.setAttribute("appointments", appointmentDao.getAppointmentByPatient(patientID));
			
		} else if (action.equals("s-add")) {
			appointmentDate = request.getParameter("date");
			startTime = request.getParameter("time");
			patientID = Integer.parseInt(request.getParameter("pid"));
			treatmentID = Integer.parseInt(request.getParameter("tid"));
			dentistID = Integer.parseInt(request.getParameter("dentistid"));
			staffID = Integer.parseInt(request.getParameter("staffid"));

			Appointment a = new Appointment();
			a.setAppointmentDate(appointmentDate);
			a.setStartTime(startTime);
			a.setPatientID(patientID);
			a.setTreatmentID(treatmentID);
			a.setDentistID(dentistID);
			a.setStaffID(staffID);

			appointmentDao.add(a);
			forward = ALL_APPOINTMENT_S;
			request.setAttribute("appointments", appointmentDao.getAllAppointment());
		}
		else {
			appointmentID = Integer.parseInt(request.getParameter("aid"));
			appointmentDate = request.getParameter("date");
			startTime = request.getParameter("start");
			patientID = Integer.parseInt(request.getParameter("pid"));
			treatmentID = Integer.parseInt(request.getParameter("tid"));
			dentistID = Integer.parseInt(request.getParameter("denstistid"));
			staffID = Integer.parseInt(request.getParameter("staffid"));

			Appointment a = new Appointment();

			a.setAppointmentID(appointmentID);
			a.setAppointmentDate(appointmentDate);
			a.setStartTime(startTime);
			a.setPatientID(patientID);
			a.setTreatmentID(treatmentID);
			a.setDentistID(dentistID);
			a.setStaffID(staffID);

			appointmentDao.update(a);
			request.setAttribute("appointments", appointmentDao.getAllAppointment());
			forward = INDEX;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
