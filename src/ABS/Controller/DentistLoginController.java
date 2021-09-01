package ABS.Controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/DentistLoginController")
public class DentistLoginController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private static String D_DASHBOARD = "d-Index2.jsp" ;
	private static String DENTIST_LOGIN = "d-DentistLogin.jsp" ;

    String forward ;
    
    private AppointmentDao adao;

    public DentistLoginController() 
    {
        super();
        adao = new AppointmentDao();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
    	try 
    	{
			Dentist dentist = new Dentist();
			dentist.setDentistEmail(request.getParameter("dentistEmail"));
			dentist.setDentistPassword(request.getParameter("dentistPassword"));

			dentist = DentistDao.login(dentist);

			if(dentist.isValid())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", dentist.getDentistID());
				session.setAttribute("dentist", dentist);
				session.setAttribute("role", "Dentist");
				
				List<Appointment> appointment = adao.getTodaysAppointment(dentist.getDentistID()); 
				
				forward = D_DASHBOARD;       		
				request.setAttribute("appointments", appointment); 
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Email or password incorrect');");
				out.println("</script>");
				forward = DENTIST_LOGIN;
			}
		}

		catch (Throwable ex) 
    	{
			System.out.println(ex);
		}
    	
    	RequestDispatcher view = request.getRequestDispatcher(forward);
		view.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}


