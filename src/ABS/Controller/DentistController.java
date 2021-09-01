package ABS.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ABS.DAO.AppointmentDao;
import ABS.DAO.DentistDao;
import ABS.MODEL.Appointment;
import ABS.MODEL.Dentist;

@WebServlet("/DentistController")
public class DentistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String VIEW_DENTIST = "d-ViewDentist.jsp";
	private static String UPDATE_DENTIST = "d-UpdateProfile.jsp" ;
	private static String VIEW_APPOINTMENT = "d-ViewAppointment.jsp";
	private static String DENTIST_LOGIN = "d-DentistLogin.jsp";
	private static String D_DASHBOARD = "d-Index2.jsp";	
	private static String LIST_DENTIST = "s-ViewDentistList.jsp";

	private DentistDao daoDentist;
	private AppointmentDao adao;

	String forward="";	
       
    public DentistController() 
    {
        super();
        daoDentist = new DentistDao();
        adao = new AppointmentDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getParameter("action");
		
		//IF UPDATE DENTIST IS SELECTED
		if (action.equalsIgnoreCase("updateprofile")) 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Dentist dentist = daoDentist.getDentistById(id);
			
			forward = UPDATE_DENTIST;
			request.setAttribute("dentist", dentist);
		}
		//IF VIEW DENTIST IS SELECTED
		else if (action.equalsIgnoreCase("viewDentist")) 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Dentist dentist = daoDentist.getDentistById(id);
			if(dentist.getDentistName() == null) {
				System.out.println("NULLLLLLLLLLLL LAAAAAAAAAAAAA");
			}
			
			forward = VIEW_DENTIST;       		
			request.setAttribute("dentist", dentist);  
		}
		
		else if (action.equalsIgnoreCase("viewAppointment")) 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			List<Appointment> appointment = adao.getAppointmentByDentist(id); 
			
			forward = VIEW_APPOINTMENT;       		
			request.setAttribute("appointments", appointment);  
		}
		else if (action.equalsIgnoreCase("delete")) 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			daoDentist.deleteDentist(id);;
			
			List<Dentist> dentists = daoDentist.getAllDentist();
			
			forward = LIST_DENTIST;       		
			request.setAttribute("dentists", dentists);
		}
		else if (action.equalsIgnoreCase("dashboard")) 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			List<Appointment> appointment = adao.getTodaysAppointment(id);
			
			forward = D_DASHBOARD;
			request.setAttribute("appointments", appointment);  
		}
		else if (action.equalsIgnoreCase("listDentist")) 
		{
			List<Dentist> dentists = daoDentist.getAllDentist();
			
			forward = LIST_DENTIST;       		
			request.setAttribute("dentists", dentists);  
		}
		else {
			System.out.println("No action found");
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String dentistName = request.getParameter("dentistName");
		String dentistPhoneNo = request.getParameter("dentistPhoneNo");
		String dentistEmail = request.getParameter("dentistEmail");
		String dentistPassword = request.getParameter("dentistPassword");
		
		String action = request.getParameter("action") ;
		
		Dentist dentist = new Dentist () ;
		
		dentist.setDentistName(dentistName);
		dentist.setDentistPhoneNo(dentistPhoneNo);
		dentist.setDentistEmail(dentistEmail);
		dentist.setDentistPassword(dentistPassword);

		if(action.equalsIgnoreCase("adddentist"))
		{
			System.out.println("Adding the data");
		
			daoDentist.add(dentist);
				
			response.sendRedirect("d-DentistLogin.jsp");	
		}
		else if(action.equalsIgnoreCase("updateprofile"))
		{
			int dentistID = Integer.parseInt(request.getParameter("dentistID"));
			dentist.setDentistID(dentistID);
			
			System.out.println("Dentist exist. Updating...");
			daoDentist.updateDentist(dentist);
			
			HttpSession session = request.getSession(true);
			session.setAttribute("dentist", dentist);
			
			RequestDispatcher view = request.getRequestDispatcher(VIEW_DENTIST);
			request.setAttribute("dentist", daoDentist.getDentistById(dentistID));
			view.forward(request, response);
		}
	}
}

