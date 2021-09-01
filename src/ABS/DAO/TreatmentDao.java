package ABS.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ABS.Connection.ConnectionManager;
import ABS.MODEL.Treatment;

public class TreatmentDao {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	int treatmentID, treatmentDuration;
	String treatmentName;
	double treatmentPrice;

	public void add(Treatment treatment) {
		treatmentName = treatment.getTreatmentName();
		treatmentDuration = treatment.getTreatmentDuration();
		treatmentPrice = treatment.getTreatmentPrice();

		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(
					"INSERT INTO Treatment (treatmentName, treatmentDuration, treatmentPrice) values (?,?,?)");

			ps.setString(1, treatmentName);
			ps.setInt(2, treatmentDuration);
			ps.setDouble(3, treatmentPrice);
			
			ps.executeUpdate();

			System.out.println("Treatment created successfully!");
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

	public List<Treatment> getAllTreatment() {
		List<Treatment> treatmentArray = new ArrayList<Treatment>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "SELECT * FROM Treatment";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Treatment treatment = new Treatment();

				treatment.setTreatmentID(rs.getInt("treatmentID"));
				treatment.setTreatmentName(rs.getString("treatmentName"));
				treatment.setTreatmentDuration(rs.getInt("treatmentDuration"));
				treatment.setTreatmentPrice(rs.getFloat("treatmentPrice"));

				treatmentArray.add(treatment);
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

		return treatmentArray;
	}
	
	public void delete(int id) {
		String deleteQuery = "DELETE FROM Treatment WHERE treatmentID = " + id;

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

	public Treatment getTreatmentById(int id) {
		Treatment treatment = new Treatment();

		try {
			currentCon = ConnectionManager.getConnection();

			ps = currentCon.prepareStatement("SELECT * FROM treatment WHERE treatmentID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				treatment.setTreatmentID(rs.getInt("treatmentID"));
				treatment.setTreatmentName(rs.getString("treatmentName"));
				treatment.setTreatmentDuration(rs.getInt("treatmentDuration"));
				treatment.setTreatmentPrice(rs.getFloat("treatmentPrice"));
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
		return treatment;
	}

	public void update(Treatment treatment) {
		
		treatmentID = treatment.getTreatmentID();
		treatmentName = treatment.getTreatmentName();
		treatmentDuration = treatment.getTreatmentDuration();
		treatmentPrice = treatment.getTreatmentPrice();

		String updateQuery = "UPDATE Treatment SET treatmentName = '" + treatmentName + "', treatmentDuration = "
				+ treatmentDuration + ", treatmentPrice = " + treatmentPrice + " WHERE treatmentID = " + treatmentID;

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
	
	public List<Treatment> get2HourTreatment() {
		List<Treatment> treatmentArray = new ArrayList<Treatment>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "SELECT * FROM Treatment WHERE treatmentDuration = 2";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Treatment treatment = new Treatment();

				treatment.setTreatmentID(rs.getInt("treatmentID"));
				treatment.setTreatmentName(rs.getString("treatmentName"));
				treatment.setTreatmentDuration(rs.getInt("treatmentDuration"));
				treatment.setTreatmentPrice(rs.getFloat("treatmentPrice"));

				treatmentArray.add(treatment);
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

		return treatmentArray;
	}
	
	public List<Treatment> get1HourTreatment() {
		List<Treatment> treatmentArray = new ArrayList<Treatment>();

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();

			String q = "SELECT * FROM Treatment WHERE treatmentDuration = 1";
			ResultSet rs = stmt.executeQuery(q);

			while (rs.next()) {
				Treatment treatment = new Treatment();

				treatment.setTreatmentID(rs.getInt("treatmentID"));
				treatment.setTreatmentName(rs.getString("treatmentName"));
				treatment.setTreatmentDuration(rs.getInt("treatmentDuration"));
				treatment.setTreatmentPrice(rs.getFloat("treatmentPrice"));

				treatmentArray.add(treatment);
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

		return treatmentArray;
	}
}
