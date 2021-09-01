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
import ABS.MODEL.Staff;

public class StaffDao {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static String staffName, staffPhoneNo, staffAddress, staffEmail, staffPassword;
	static int staffID;

	// LOGIN
	public static Staff login(Staff staff) throws NoSuchAlgorithmException {
		staffEmail = staff.getStaffEmail();
		staffPassword = staff.getStaffPassword();

		String searchQuery = "SELECT * FROM STAFF WHERE staffEmail='" + staffEmail + "' AND staffPassword='" + staffPassword +"'";
		System.out.println(searchQuery);
		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);

			// if user exists set the isValid variable to true
			if (rs.next()) {
				staff.setStaffID(rs.getInt("staffID"));
				staff.setStaffName(rs.getString("staffName"));
				staff.setStaffPhoneNo(rs.getString("staffPhoneNo"));
				staff.setStaffEmail(rs.getString("staffEmail"));
				staff.setValid(true);
			}

			// if user does not exist set the isValid variable to false
			else {
				System.out.println("Sorry, you are not a registered staff! Please sign up first");
				staff.setValid(false);
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
		return staff;
	}
	// ADD STAFF DATA INTO DATABASE
	public void add(Staff staff) {
		staffID = staff.getStaffID();
		staffName = staff.getStaffName();
		staffPhoneNo = staff.getStaffPhoneNo();
		staffEmail = staff.getStaffEmail();
		staffPassword = staff.getStaffPassword();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(
					"INSERT INTO STAFF (staffName, staffPhoneNo, staffEmail, staffPassword) values (?,?,?,?)");

			ps.setString(1, staffName);
			ps.setString(2, staffPhoneNo);
			ps.setString(3, staffEmail);
			ps.setString(4, staffPassword);

			ps.executeUpdate();

			System.out.println("Staff created successfully!");
		}

		catch (Exception ex) {
			System.out.println("Failed: An Exception has occured! " + ex);
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
	}

	// GET ALL STAFF DATA LIST
	public List<Staff> getAllStaff() {
		List<Staff> staffs = new ArrayList<Staff>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "SELECT * FROM STAFF";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Staff staff = new Staff();

				staff.setStaffID(rs.getInt("staffID"));
				staff.setStaffName(rs.getString("staffName"));
				staff.setStaffPhoneNo(rs.getString("staffPhoneNo"));
				staff.setStaffEmail(rs.getString("staffEmail"));
				staff.setStaffPassword(rs.getString("staffPassword"));

				staffs.add(staff);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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

		return staffs;
	}

	// DELETE ALL STAFF DATA FROM DATABASE
	public void deleteStaff(int id) {
		String searchQuery = "DELETE FROM STAFF WHERE staffID=" + id;

		System.out.println(searchQuery);

		try {

			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);

		} catch (SQLException e) {
			e.printStackTrace();
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

	}

	// GET STAFF BASED ON ID
	public Staff getStaffById(int id) {
		Staff staff = new Staff();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement("SELECT * FROM STAFF WHERE staffID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				staff.setStaffID(rs.getInt("staffID"));
				staff.setStaffName(rs.getString("staffName"));
				staff.setStaffPhoneNo(rs.getString("staffPhoneNo"));
				staff.setStaffEmail(rs.getString("staffEmail"));
				staff.setStaffPassword(rs.getString("staffPassword"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		return staff;
	}

	// UPDATE PATIENT DATA FROM DATABASE
	public void updatestaff(Staff staff) {
		staffID = staff.getStaffID();
		staffName = staff.getStaffName();
		staffPhoneNo = staff.getStaffPhoneNo();
		staffEmail = staff.getStaffEmail();
		staffPassword = staff.getStaffPassword();

		String searchQuery = "UPDATE STAFF " + "SET staffName='" + staffName + "' " + ", staffPhoneNo='" + staffPhoneNo
				+ "'" + ", staffEmail= '" + staffEmail + "'" + " WHERE staffID = " + staffID;

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);
		} catch (SQLException e) {
			e.printStackTrace();
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
	}
	
	// CHECK EMAIL TO AVOID DUPLICATE ENTRY
	public static Staff checkEmail(Staff staff) {
		staffEmail = staff.getStaffEmail();

		String searchQuery = "SELECT * FROM STAFF WHERE staffEmail='" + staffEmail + "'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			System.out.println(searchQuery);

			// if subject exists set the isValid variable to true
			if (more) {
				staff.setValid(true);
			}

			else if (!more) {
				staff.setValid(false);
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
		return staff;
	}
}