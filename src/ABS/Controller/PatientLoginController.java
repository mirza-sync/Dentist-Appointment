package ABS.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ABS.DAO.PatientDao;
import ABS.MODEL.Patient;

@WebServlet("/PatientLoginController")
public class PatientLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String VIEW_PATIENT_HOME = "p-Index.jsp";
	private static String PATIENT_LOGIN = "p-PatientLogin.jsp";
	
	String forward;

    public PatientLoginController() 
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{     
    	try 
    	{
			Patient patient = new Patient();
			patient.setPatientEmail(request.getParameter("patientEmail"));
			patient.setPatientPassword(request.getParameter("patientPassword"));

			patient = PatientDao.login(patient);

			if(patient.isValid())
			{
				HttpSession session = request.getSession(true);
				
				forward = VIEW_PATIENT_HOME;
				session.setAttribute("currentSessionUser", patient.getPatientID());
				session.setAttribute("patient", patient);
				session.setAttribute("role", "Patient");
			}
			else
			{
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Email or password incorrect');");
				out.println("</script>");
				forward = PATIENT_LOGIN;
			}
			
		}

		catch (Throwable ex)
    	{
			System.out.println(ex);
		}
    	
    	RequestDispatcher view = request.getRequestDispatcher(forward);
		view.include(request, response);
	}
}

