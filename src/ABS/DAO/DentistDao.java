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
import ABS.MODEL.Dentist;

public class DentistDao {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static String dentistName, dentistPhoneNo, dentistEmail, dentistPassword;
	static int dentistID;

	// LOGIN
	public static Dentist login(Dentist dentist) throws NoSuchAlgorithmException {
		dentistEmail = dentist.getDentistEmail();
		dentistPassword = dentist.getDentistPassword();

		String searchQuery = "SELECT * FROM DENTIST WHERE dentistEmail='" + dentistEmail + "' AND dentistPassword='"
				+ dentistPassword + "'";

		System.out.println("Your email is " + dentistEmail);
		System.out.println("Your password is " + dentistPassword);
		System.out.println("Query: " + searchQuery);

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if user exists set the isValid variable to true
			if (more) {
				dentist.setDentistID(rs.getInt("dentistID"));
				dentist.setDentistName(rs.getString("dentistName"));
				dentist.setDentistPhoneNo(rs.getString("dentistPhoneNo"));
				dentist.setDentistEmail(rs.getString("dentistEmail"));

				System.out.println("Welcome dentist: " + dentist.getDentistName());
				dentist.setValid(true);
			}

			// if user does not exist set the isValid variable to false
			else if (!more) {
				System.out.println("Sorry, you are not a registered dentist! Please sign up first");
				dentist.setValid(false);
			}
		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}

		finally {
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
		return dentist;
	}
	// ADD DENTIST DATA INTO DATABASE
	public void add(Dentist dentist) {
		dentistName = dentist.getDentistName();
		dentistPhoneNo = dentist.getDentistPhoneNo();
		dentistEmail = dentist.getDentistEmail();
		dentistPassword = dentist.getDentistPassword();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(
					"INSERT INTO DENTIST (dentistName, dentistPhoneNo, dentistEmail, dentistPassword) values (?,?,?,?)");
			
			ps.setString(1, dentistName);
			ps.setString(2, dentistPhoneNo);
			ps.setString(3, dentistEmail);
			ps.setString(4, dentistPassword);

			ps.executeUpdate();

			System.out.println("Dentist created successfully!");
			System.out.println("Dentist name : " + dentistName);
		}

		catch (Exception ex) {
			System.out.println("Failed: An Exception has occured! " + ex);
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
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

	// GET ALL DENTIST DATA LIST
	public List<Dentist> getAllDentist() {
		List<Dentist> dentists = new ArrayList<Dentist>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "SELECT * FROM DENTIST";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Dentist dentist = new Dentist();

				dentist.setDentistID(rs.getInt("dentistID"));
				dentist.setDentistName(rs.getString("dentistName"));
				dentist.setDentistPhoneNo(rs.getString("dentistPhoneNo"));
				dentist.setDentistEmail(rs.getString("dentistEmail"));

				dentists.add(dentist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}

		return dentists;
	}

	// DELETE ALL DENTIST DATA FROM DATABASE
	public void deleteDentist(int id) {
		String searchQuery = "DELETE FROM DENTIST WHERE dentistID=" + id;

		System.out.println(searchQuery);

		try {

			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
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

	// GET DENTIST BASED ON ID
	public Dentist getDentistById(int id) {
		Dentist dentist = new Dentist();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement("SELECT * FROM DENTIST WHERE dentistID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				dentist.setDentistID(rs.getInt("dentistID"));
				dentist.setDentistName(rs.getString("dentistName"));
				dentist.setDentistPhoneNo(rs.getString("dentistPhoneNo"));
				dentist.setDentistEmail(rs.getString("dentistEmail"));
				dentist.setDentistPassword(rs.getString("dentistPassword"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}
				currentCon = null;
			}
		}
		return dentist;
	}

	// UPDATE DENTIST DATA FROM DATABASE
	public void updateDentist(Dentist dentist) {
		dentistID = dentist.getDentistID();
		dentistName = dentist.getDentistName();
		dentistPhoneNo = dentist.getDentistPhoneNo();
		dentistEmail = dentist.getDentistEmail();
		dentistPassword = dentist.getDentistPassword();

		String searchQuery = "UPDATE DENTIST " + "SET dentistName='" + dentistName + "' " + ", dentistPhoneNo='"
				+ dentistPhoneNo + "'" + ", dentistEmail= '" + dentistEmail + "'" + " WHERE dentistID="+ dentistID;

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
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
	
	// CHECK EMAIL TO AVOID DUPLICATE ENTRY
	public static Dentist checkEmail(Dentist dentist) {
		dentistEmail = dentist.getDentistEmail();

		String searchQuery = "SELECT * FROM DENTIST WHERE dentistEmail='" + dentistEmail + "'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			System.out.println(searchQuery);

			// if subject exists set the isValid variable to true
			if (more) {
				dentist.setValid(true);
			}

			else if (!more) {
				dentist.setValid(false);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} finally {
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
		return dentist;
	}
}
