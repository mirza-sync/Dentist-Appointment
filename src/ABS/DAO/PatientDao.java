package ABS.DAO;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ABS.Connection.ConnectionManager;
import ABS.MODEL.Patient;


public class PatientDao {
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static String patientName, patientPhoneNo, patientEmail, patientPassword;
	static int patientID;

	//LOGIN
	public static Patient login(Patient patient) throws NoSuchAlgorithmException 
	{
		patientEmail = patient.getPatientEmail();
		patientPassword = patient.getPatientPassword();

        String searchQuery = "SELECT * FROM PATIENT WHERE patientEmail='" + patientEmail + "' AND patientPassword='" + patientPassword + "'";
        try 
        {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) 
            {
				patient.setPatientID(rs.getInt("patientID"));
				patient.setPatientName(rs.getString("patientName"));
				patient.setPatientPhoneNo(rs.getString("patientPhoneNo"));
				patient.setPatientEmail(rs.getString("patientEmail"));
           
           		System.out.println("Welcome " + patient.getPatientName());
           		patient.setValid(true);
           	}
           
            // if user does not exist set the isValid variable to false
            else if (!more) 
            {
            	System.out.println("Sorry, you are not a registered patient! Please sign up first");
            	patient.setValid(false);
            }
           
        }

        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }

        finally 
        {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }

        return patient;
    }
	//ADD PATIENT DATA INTO DATABASE
	public void add(Patient patient) 
	{
		patientID = patient.getPatientID();
		patientName = patient.getPatientName();
		patientPhoneNo = patient.getPatientPhoneNo();
		patientEmail = patient.getPatientEmail();
		patientPassword = patient.getPatientPassword();
		
		try
		{
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("INSERT INTO PATIENT (patientName,patientPhoneNo,patientEmail, patientPassword) values (?,?,?,?)");
			
			ps.setString(1, patientName);
			ps.setString(2, patientPhoneNo);
			ps.setString(3, patientEmail);
			ps.setString(4, patientPassword);

			ps.executeUpdate();
		
			System.out.println("Patient created successfully!");
		}
		
		catch(Exception ex)
		{
			System.out.println("Failed: An Exception has occured! " + ex);
		}
		
		finally 
        {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }
	}

	//GET ALL PATIENT DATA LIST
	public List<Patient> getAllPatient() 
	{
		List<Patient> patients = new ArrayList<Patient>();
		  try 
		  {
		  	currentCon = ConnectionManager.getConnection();
		  	stmt = currentCon.createStatement();
		  
		  	  String q = "SELECT * FROM PATIENT";
		      ResultSet rs = stmt.executeQuery(q);
		      
		      while (rs.next()) 
		      {
		    	  Patient patient = new Patient();
		          
		    	  patient.setPatientID(rs.getInt("patientID"));
		    	  patient.setPatientName(rs.getString("patientName"));
		    	  patient.setPatientPhoneNo(rs.getString("patientPhoneNo"));
		    	  patient.setPatientEmail(rs.getString("patientEmail"));
		    	  patient.setPatientPassword(rs.getString("patientPassword"));
		  
		    	  
		    	  patients.add(patient);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }
		  finally 
	        {
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (Exception e) {
	                }
	                rs = null;
	            }

	            if (stmt != null) {
	                try {
	                    stmt.close();
	                } catch (Exception e) {
	                }
	                stmt = null;
	            }

	            if (currentCon != null) {
	                try {
	                    currentCon.close();
	                } catch (Exception e) {
	                }

	                currentCon = null;
	            }
	        }

		  return patients;
	}
	
	//DELETE ALL PATIENT DATA FROM DATABASE
	public void deletePatient(int id) 
	{
		String searchQuery = "DELETE FROM PATIENT WHERE patientID=" + id;
		
		System.out.println(searchQuery);
		
		try {
	
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);
	        
	    } 
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    }
		finally 
        {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }
		
	}
	
	//GET PATIENT BASED ON ID
	public Patient getPatientById(int id) 
	{
		Patient patient = new Patient();
		
	    try 
	    {
	    	currentCon = ConnectionManager.getConnection();
	    	
	        ps=currentCon.prepareStatement("SELECT * FROM PATIENT WHERE patientID=?");
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) 
	        {
	        	patient.setPatientID(rs.getInt("patientID"));
	        	patient.setPatientName(rs.getString("patientName"));
	        	patient.setPatientPhoneNo(rs.getString("patientPhoneNo"));
	        	patient.setPatientEmail(rs.getString("patientEmail"));
	        	patient.setPatientPassword(rs.getString("patientPassword"));
	        		
	        }
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	    finally 
        {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }
	    return patient;
	}

	//UPDATE PATIENT DATA FROM DATABASE
	public void updatePatient(Patient patient) 
	{
		patientID = patient.getPatientID();
		patientName = patient.getPatientName();
		patientPhoneNo = patient.getPatientPhoneNo();
		patientEmail = patient.getPatientEmail();
		patientPassword = patient.getPatientPassword();
		
		
	    String searchQuery = "UPDATE PATIENT "
	    		+ "SET patientName = '" + patientName  + "'"
	    		+ ", patientPhoneNo ='" + patientPhoneNo + "'"
	    		+ ", patientEmail = '" + patientEmail + "'"
	    		+ " WHERE patientID = " + patientID;
	    
		try 
		{
	        currentCon = ConnectionManager.getConnection();
	        stmt = currentCon.createStatement();
	        stmt.executeUpdate(searchQuery);   
	    } 
		catch (SQLException e) 
		{
	        e.printStackTrace();
	    }	
		finally 
        {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }
	}
	
	//CHECK EMAIL TO AVOID DUPLICATE ENTRY
	public static Patient checkEmail(Patient patient) 
	{
		patientEmail = patient.getPatientEmail();

        String searchQuery = "SELECT * FROM PATIENT WHERE patientEmail='" + patientEmail + "'";

        try 
        {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();
            
            System.out.println(searchQuery);

            // if subject exists set the isValid variable to true
            if (more) {
            	patient.setValid(true);
           	}
           
            else if (!more) {
            	patient.setValid(false);
            }
        }
        catch (Exception ex) 
        {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        finally 
        {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
                stmt = null;
            }

            if (currentCon != null) {
                try {
                    currentCon.close();
                } catch (Exception e) {
                }

                currentCon = null;
            }
        }
        return patient;
	}
}

