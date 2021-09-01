package ABS.Controller;

import java.io.IOException;

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


@WebServlet("/StaffController")
public class StaffController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static String S_DASHBOARD = "s-Index3.jsp";
	private static String VIEW_STAFF = "s-ViewStaff.jsp";
	private static String UPDATE_STAFF= "s-UpdateStaff.jsp";
	private static String STAFF_LOGIN= "s-StaffLogin.jsp";

	private StaffDao daoStaff;
	PatientDao pdao = new PatientDao();

	String forward="";	
       
    public StaffController() 
    {
        super();
        daoStaff = new StaffDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = request.getParameter("action");
		
		//IF UPDATE STAFF IS SELECTED
		if (action.equalsIgnoreCase("updateStaff")) 
		{	
			HttpSession session = request.getSession(true);
			int id= (int) session.getAttribute("currentSessionUser");
			
			Staff staff = new Staff();
			staff = daoStaff.getStaffById(id);
			
			forward = UPDATE_STAFF;
			request.setAttribute("staff", staff);
		}
		//IF VIEW PATIENT IS SELECTED
		else if (action.equalsIgnoreCase("viewStaff")) 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Staff staff = new Staff();
			staff = daoStaff.getStaffById(id); 
			
			forward = VIEW_STAFF;       		
			request.setAttribute("staff", staff);  
		}
		else if (action.equalsIgnoreCase("delete")) 
		{
			int id = Integer.parseInt(request.getParameter("id"));
			daoStaff.deleteStaff(id);
			
			forward = STAFF_LOGIN;
		}
		
		else if (action.equalsIgnoreCase("dashboard")) 
		{
			forward = S_DASHBOARD;  		
			request.setAttribute("patients", pdao.getAllPatient());
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String staffName = request.getParameter("staffName");
		String staffPhoneNo = request.getParameter("staffPhoneNo");
		String staffEmail = request.getParameter("staffEmail");
		String staffPassword = request.getParameter("staffPassword");
		
		String action = request.getParameter("action") ;

		Staff staff = new Staff() ;
		
		staff.setStaffName(staffName);
		staff.setStaffPhoneNo(staffPhoneNo);
		staff.setStaffEmail(staffEmail);
		staff.setStaffPassword(staffPassword);
		
		if(action.equalsIgnoreCase("addstaff"))
		{
			System.out.println("Adding the data");
		
			daoStaff.add(staff);
				
			response.sendRedirect("s-StaffLogin.jsp");	
		}
		else if(action.equalsIgnoreCase("updatestaff"))
		{
			int staffID = Integer.parseInt(request.getParameter("staffID"));
			staff.setStaffID(staffID);
			
			System.out.println("Staff exist. Updating...");
			daoStaff.updatestaff(staff);
			
			HttpSession session = request.getSession(true);
			session.setAttribute("staff", staff);
			
			RequestDispatcher view = request.getRequestDispatcher(VIEW_STAFF);
			request.setAttribute("staff", daoStaff.getStaffById(staffID));
			view.forward(request, response);
		}			
			
	}
}
