package ABS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ABS.Connection.ConnectionManager;
import ABS.MODEL.Appointment;

public class AppointmentDao {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static String appointmentName, appointmentDate, startTime;
	static int appointmentID, treatmentID, patientID, dentistID, staffID;
	TreatmentDao tdao = new TreatmentDao();
	DentistDao ddao = new DentistDao();
	PatientDao pdao = new PatientDao();

	public void add(Appointment appointment) {
		appointmentDate = appointment.getAppointmentDate();
		startTime = appointment.getStartTime();
		patientID = appointment.getPatientID();
		treatmentID = appointment.getTreatmentID();
		dentistID = appointment.getDentistID();
		staffID = appointment.getStaffID();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(
					"INSERT INTO APPOINTMENT (appointmentDate, startTime, patientID, treatmentID, dentistID, staffID) values (?,?,?,?,?,?)");

			ps.setString(1, appointmentDate);
			ps.setString(2, startTime);
			ps.setInt(3, patientID);
			ps.setInt(4, treatmentID);
			ps.setInt(5, dentistID);
			if(staffID == 0) {
				ps.setString(6, "");
			}
			else {
				ps.setInt(6, staffID);
			}

			ps.executeUpdate();

			System.out.println("Appointment created successfully!");
		}

		catch (Exception ex) {
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

	public List<Appointment> getAllAppointment() {
		List<Appointment> appointmentArray = new ArrayList<Appointment>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "SELECT * FROM appointment ORDER BY appointmentDate";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Appointment appointment = new Appointment();

				appointment.setAppointmentID(rs.getInt("appointmentID"));
				appointment.setAppointmentDate(rs.getString("appointmentDate"));
				appointment.setStartTime(rs.getString("startTime"));
				appointment.setPatient(pdao.getPatientById(rs.getInt("patientID")));
				appointment.setTreatment(tdao.getTreatmentById(rs.getInt("treatmentID")));
				appointment.setDentist(ddao.getDentistById(rs.getInt("dentistID")));
				appointment.setStaffID(rs.getInt("staffID"));
				
				appointmentArray.add(appointment);
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

		return appointmentArray;
	}

	public void delete(int id) {
		String deleteQuery = "DELETE FROM appointment WHERE appointmentID = " + id;

		System.out.println(deleteQuery);

		try {

			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(deleteQuery);

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

	}

	public Appointment getAppointmentById(int id) {
		Appointment appointment = new Appointment();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement("SELECT * FROM ap WHERE appointmentID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				appointment.setAppointmentID(rs.getInt("appointmentID"));
				appointment.setAppointmentDate(rs.getString("appointmentDate"));
				appointment.setStartTime(rs.getString("startTime"));
				appointment.setPatient(pdao.getPatientById(rs.getInt("patientID")));
				appointment.setTreatment(tdao.getTreatmentById(rs.getInt("treatmentID")));
				appointment.setDentist(ddao.getDentistById(rs.getInt("dentistID")));
				appointment.setStaffID(rs.getInt("staffID"));
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
		return appointment;
	}

	public void update(Appointment appointment) {

		appointmentID = appointment.getAppointmentID();
		appointmentDate = appointment.getAppointmentDate();
		startTime = appointment.getStartTime();
		patientID = appointment.getPatientID();
		treatmentID = appointment.getTreatmentID();
		dentistID = appointment.getDentistID();
		staffID = appointment.getStaffID();

		String updateQuery = "UPDATE appointment SET appointmentDate='" + appointmentDate + "' " + ", startTime='"
				+ startTime + "'" + ", patientID= " + patientID + ", treatmentID = " + treatmentID + ", dentistID= "
				+ dentistID + ", staffID = " + staffID + " WHERE appointmentID = "+ appointmentID;

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(updateQuery);
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
	}

	public List<Appointment> getAppointmentByDate(String date) {

		List<Appointment> appointmentArray = new ArrayList<Appointment>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "SELECT * FROM appointment WHERE appointmentDate = '"+date+"'";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Appointment appointment = new Appointment();

				appointment.setAppointmentID(rs.getInt("appointmentID"));
				appointment.setAppointmentDate(rs.getString("appointmentDate"));
				appointment.setStartTime(rs.getString("startTime"));
				appointment.setPatient(pdao.getPatientById(rs.getInt("patientID")));
				appointment.setTreatment(tdao.getTreatmentById(rs.getInt("treatmentID")));
				appointment.setDentist(ddao.getDentistById(rs.getInt("dentistID")));

				appointmentArray.add(appointment);
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

		return appointmentArray;
	}
	
	public List<Appointment> getAppointmentByPatient(int id) {

		List<Appointment> appointmentArray = new ArrayList<Appointment>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "SELECT * FROM appointment WHERE patientID = "+id+" ORDER BY appointmentDate";
			System.out.println(q);
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Appointment appointment = new Appointment();
				System.out.println("APPTMT: "+rs.getInt("appointmentID"));
				appointment.setAppointmentID(rs.getInt("appointmentID"));
				appointment.setAppointmentDate(rs.getString("appointmentDate"));
				appointment.setStartTime(rs.getString("startTime"));
				appointment.setPatient(pdao.getPatientById(rs.getInt("patientID")));
				appointment.setTreatment(tdao.getTreatmentById(rs.getInt("treatmentID")));
				appointment.setDentist(ddao.getDentistById(rs.getInt("dentistID")));

				appointmentArray.add(appointment);
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

		return appointmentArray;
	}
	
	public List<Appointment> getAppointmentByDentist(int id) {

		List<Appointment> appointmentArray = new ArrayList<Appointment>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "SELECT * FROM appointment WHERE dentistID = "+id+" ORDER BY appointmentDate";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Appointment appointment = new Appointment();

				appointment.setAppointmentID(rs.getInt("appointmentID"));
				appointment.setAppointmentDate(rs.getString("appointmentDate"));
				appointment.setStartTime(rs.getString("startTime"));
				appointment.setPatient(pdao.getPatientById(rs.getInt("patientID")));
				appointment.setTreatment(tdao.getTreatmentById(rs.getInt("treatmentID")));
				appointment.setDentist(ddao.getDentistById(rs.getInt("dentistID")));

				appointmentArray.add(appointment);
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

		return appointmentArray;
	}
	
	public List<Appointment> getTodaysAppointment(int id) {

		List<Appointment> appointmentArray = new ArrayList<Appointment>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "SELECT * FROM appointment WHERE dentistID = "+id+" AND appointmentDate = TO_CHAR(CAST((SELECT sysdate FROM DUAL) as date),'YYYY-MM-DD')";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Appointment appointment = new Appointment();

				appointment.setAppointmentID(rs.getInt("appointmentID"));
				appointment.setAppointmentDate(rs.getString("appointmentDate"));
				appointment.setStartTime(rs.getString("startTime"));
				appointment.setPatient(pdao.getPatientById(rs.getInt("patientID")));
				appointment.setTreatment(tdao.getTreatmentById(rs.getInt("treatmentID")));
				appointment.setDentist(ddao.getDentistById(rs.getInt("dentistID")));

				appointmentArray.add(appointment);
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

		return appointmentArray;
	}
	
	public String getDay(String date) {
		String day = "";
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "SELECT TO_CHAR(date '"+ date +"', 'DAY') day FROM dual";
			ResultSet rs = stmt.executeQuery(q);

			if (rs.next()) {
				day = rs.getString("day");
				System.out.println("HARI: "+day);
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

		return day;
	}
}
