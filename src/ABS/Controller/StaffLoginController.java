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
import ABS.DAO.StaffDao;
import ABS.MODEL.Staff;

@WebServlet("/StaffLoginController")
public class StaffLoginController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private static String VIEW_STAFF_HOME = "s-Index3.jsp";
	private static String STAFF_LOGIN = "s-StaffLogin.jsp";
	
	String forward;
	
	PatientDao pdao = new PatientDao();

    public StaffLoginController() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{     
    	try 
    	{
			Staff staff = new Staff();
			staff.setStaffEmail(request.getParameter("staffEmail"));
			staff.setStaffPassword(request.getParameter("staffPassword"));

			staff = StaffDao.login(staff);

			if(staff.isValid())
			{
				HttpSession session = request.getSession(true);
				
				forward = VIEW_STAFF_HOME;
				session.setAttribute("currentSessionUser", staff.getStaffID());
				session.setAttribute("staff", staff);
				session.setAttribute("role", "Assistant Dental");
				
				// To get patient list in drop down
				request.setAttribute("patients", pdao.getAllPatient());
				
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.include(request, response);
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Email or password incorrect');");
				out.println("</script>");
				forward = STAFF_LOGIN;

				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.include(request, response);
			}
		}
		catch (Throwable ex) 
    	{
			System.out.println(ex);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}

