package ABS.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ABS.DAO.TreatmentDao;
import ABS.MODEL.Treatment;

@WebServlet("/TreatmentController")
public class TreatmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String ADD_TREATMENT = "s-AddTreatment.jsp";
	private String UPDATE_TREATMENT = "s-UpdateTreatment.jsp";
	private String ALL_TREATMENT = "s-AllTreatments.jsp";
	
	TreatmentDao treatmentDao = new TreatmentDao();
	
	int treatmentid, treatmentDuration;
	String treatmentName;
	float treatmentPrice;
	
	String forward;
       
    public TreatmentController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("viewall")) {
			List<Treatment> t = treatmentDao.getAllTreatment();
			forward = ALL_TREATMENT;
			request.setAttribute("treatments", t);
		}
		else if(action.equals("delete")) {
			treatmentid = Integer.parseInt(request.getParameter("tid"));
			treatmentDao.delete(treatmentid);
			forward = ALL_TREATMENT;
			request.setAttribute("treatments", treatmentDao.getAllTreatment());
		}
		else if(action.equals("create")) {
			forward = ADD_TREATMENT;
		}
		else if(action.equals("edit")) {
			treatmentid = Integer.parseInt(request.getParameter("tid"));
			Treatment t = treatmentDao.getTreatmentById(treatmentid);
			
			forward = UPDATE_TREATMENT;
			request.setAttribute("treatment", t);
		}
		else {
			System.out.println("No action found");
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("add")) {
			treatmentName = request.getParameter("tname");
			treatmentDuration= Integer.parseInt(request.getParameter("tduration"));
			treatmentPrice = Float.parseFloat(request.getParameter("tprice"));
			
			Treatment t = new Treatment();
			t.setTreatmentName(treatmentName);
			t.setTreatmentDuration(treatmentDuration);
			t.setTreatmentPrice(treatmentPrice);
			
			treatmentDao.add(t);
			request.setAttribute("treatments", treatmentDao.getAllTreatment());
			forward = ALL_TREATMENT;
		}
		else {
			treatmentid = Integer.parseInt(request.getParameter("tid"));
			treatmentName = request.getParameter("tname");
			treatmentDuration= Integer.parseInt(request.getParameter("tduration"));
			treatmentPrice = Float.parseFloat(request.getParameter("tprice"));
			
			Treatment t = new Treatment();
			t.setTreatmentID(treatmentid);
			t.setTreatmentName(treatmentName);
			t.setTreatmentDuration(treatmentDuration);
			t.setTreatmentPrice(treatmentPrice);
			
			treatmentDao.update(t);
			request.setAttribute("treatments", treatmentDao.getAllTreatment());
			forward = ALL_TREATMENT;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}
}
