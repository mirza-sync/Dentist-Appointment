package ABS.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ABS.DAO.PatientDao;
import ABS.MODEL.Patient;


@WebServlet("/PatientController")
public class PatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String VIEW_PATIENT ;
	private static String VIEW_PROFILE = "p-ViewPatient.jsp" ;
	private static String UPDATE_PATIENT = "p-UpdateProfile.jsp" ;
	private static String LIST_PATIENT = "s-ViewPatientList.jsp" ;
	private static String PATIENT_HOME = "p-Index.jsp";
	private static String PATIENT_LOGIN = "p-PatientLogin.jsp";

	private PatientDao daoPatient;

	String forward="";
	
    public PatientController() 
    {
        super();
        daoPatient = new PatientDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getParameter("action");

		//IF LIST CUSTOMER IS SELECTED
		if (action.equalsIgnoreCase("listPatient")) 
		{
			forward = LIST_PATIENT;
			request.setAttribute("patients", daoPatient.getAllPatient());
		}
		//IF DELETE PATIENT IS SELECTED
		else if (action.equalsIgnoreCase("delete")) 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			daoPatient.deletePatient(id);

			forward = LIST_PATIENT;
			request.setAttribute("patients", daoPatient.getAllPatient());
		} 
		//IF UPDATE PATIENT IS SELECTED
		else if (action.equalsIgnoreCase("updateProfile")) 
		{	
			int id = Integer.parseInt(request.getParameter("id"));
			
			Patient patient = new Patient();
			patient = daoPatient.getPatientById(id);
			
			forward = UPDATE_PATIENT;
			request.setAttribute("Patient", patient);
			
		}
		//IF VIEW PATIENT IS SELECTED
		else if (action.equalsIgnoreCase("viewPatient")) 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Patient patient = new Patient();
			patient = daoPatient.getPatientById(id); 
			
			forward = VIEW_PATIENT;       		
			request.setAttribute("Patient", patient);  
			
		}
		//View Profile
		else if(action.equalsIgnoreCase("viewProfile"))
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Patient patient = new Patient();
			patient = daoPatient.getPatientById(id);
			
			forward = VIEW_PROFILE;
			request.setAttribute("Patient", patient);
		}
		else if (action.equalsIgnoreCase("getPatient")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			Gson gson = new Gson();
			Patient menus = daoPatient.getPatientById(id);
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(menus));
			out.flush();
			out.close();
			return;
		}
		else {
			System.out.println("No action found");
			forward = PATIENT_HOME;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String patientName = request.getParameter("patientName");
		String patientPhoneNo = request.getParameter("patientPhoneNo");
		String patientEmail = request.getParameter("patientEmail");
		String patientPassword = request.getParameter("patientPassword");
		
		String action = request.getParameter("action");
		
		Patient patient = new Patient ();
		
		patient.setPatientName(patientName);
		patient.setPatientPhoneNo(patientPhoneNo);
		patient.setPatientEmail(patientEmail);
		patient.setPatientPassword(patientPassword);
		
		
		if(action.equalsIgnoreCase("addpatient"))
		{
			System.out.println("Adding the data");
		
			daoPatient.add(patient);
				
			response.sendRedirect("p-PatientLogin.jsp");	
		}
		else if(action.equalsIgnoreCase("updateprofile"))
		{
			int patientID = Integer.parseInt(request.getParameter("patientID"));
			patient.setPatientID(patientID);
			
			System.out.println("Patient exist. Updating...");
			daoPatient.updatePatient(patient);
			
			HttpSession session = request.getSession(true);
			session.setAttribute("patient", patient);
			
			RequestDispatcher view = request.getRequestDispatcher(VIEW_PROFILE);
			request.setAttribute("Patient", daoPatient.getPatientById(patientID));
			view.forward(request, response);
		}			
		
		
	}
}

