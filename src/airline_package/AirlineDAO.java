package airline_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AirlineDAO {


	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException ex) {
		}
	}
	
	private Connection getConnection() throws SQLException {
		// 
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/airline?autoReconnect=true&useSSL=false",
				"john", "john");
	}
	
	private void closeConnection(Connection connection) {
		if (connection == null)
			return;
		try {
			connection.close();
		}
		catch (SQLException ex) {
		}
	}

	public int login(String username, String password) {
		int id = -1;
		String sql = "SELECT cust_id FROM customer WHERE username = '" + username +
				"' AND password = '" + password + "';";
		
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getInt("cust_id");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return id;
	}
	
	
	public List<Traveler> showPassengers() {
		List<Traveler> result = new ArrayList<Traveler>();

		String sql = "SELECT * FROM customer";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Traveler passenger = new Traveler(
						resultSet.getInt("cust_id"), 
						resultSet.getString("username"),
						resultSet.getString("password"),
						resultSet.getString("first_name"),
						resultSet.getString("last_name"),
						resultSet.getDate("dob"),
						resultSet.getString("email")
				);
				result.add(passenger);
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	
	public int addTraveler(Traveler traveler) {
		int id = -1;

		// Make sure someone doesn't already have that username
		// And return cust_id if username isn't already taken
		String getID = "SELECT cust_id FROM airline2.customer WHERE username = \"" + traveler.getUserName() + "\";";
		
		// Insert the new customer
		String insertTraveler = "INSERT INTO airline2.customer " +
						"(username, password, first_name, last_name, dob, email) " +
						"VALUES (\"" + traveler.getUserName() + 
						"\", \"" + traveler.getPassword() +
						"\", \"" + traveler.getFirstName() +
						"\", \"" + traveler.getLastName() +
						"\", '" + traveler.getDOB() +
						"', \"" + traveler.getEmail() + 
						"\");";
	
		Connection connection = null;
		try {
			connection = getConnection();
			
			PreparedStatement statement = connection.prepareStatement(getID); //  Check DB for username ------------------------
			ResultSet idTaken = statement.executeQuery();
			if (idTaken.next()) {
				return -1;   //  If there are results, username was already taken
			}
			
			statement = connection.prepareStatement(insertTraveler);  //  Insert new Traveler -----------------------------
			statement.executeUpdate();
			statement.closeOnCompletion();	// This is needed to commit the execute.
			
			statement = connection.prepareStatement(getID);  //  Get the id for the new user ---------------------------
			ResultSet cust_id = statement.executeQuery();
			if (cust_id.next()) {
				id = cust_id.getInt("cust_id");
			}			
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return id;
	}
	
//	public List<Traveler> addToDB(Traveler traveler) {
//		List<Traveler> result = new ArrayList<Traveler>();
//		int numPassengers = 0;
//
//		String sql = "SELECT COUNT(*) FROM customer";
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			ResultSet resultSet = statement.executeQuery();
//			resultSet.next();
//			numPassengers = resultSet.getInt("COUNT(*)");
//			
//			String table = "";
//			
//			System.out.println("Need to fix num passengers in addToDB()");
//			
//			if (numPassengers < 25) {
//				table = "flightinfo";
//			} else { table = "waitinglist"; }
//			
//			String insertStatement = "INSERT INTO " + table +
//					" (first_name, last_name, age, dob, departure, arrival, travel_date) VALUES (\"" + 
//					traveler.getFirstName() +
//					"\", \"" + traveler.getLastName() +
//					"\", " + traveler.getAge() +
//					", '" + traveler.getDOB() +
//					"', \"" + traveler.getDeparture() + 
//					"\", \"" + traveler.getDestination() + 
//					"\", '" + traveler.getTravelDate() +
//					"');";
//					
//			PreparedStatement prepInsert = connection.prepareStatement(insertStatement);
//			prepInsert.executeUpdate();
//
//			
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		} finally {
//			closeConnection(connection);
//		}
//		return showPassengers();
//	}
	
	public List<Traveler> cancelFlight(Traveler traveler) {
		List<Traveler> result = new ArrayList<Traveler>();
		int numPassengers = 0;

		// Add functionality so that it will remove from WAITINGLIST also
		String sql = "DELETE FROM flightinfo WHERE passenger_id = " + traveler.getID() + ";";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.executeUpdate();
			
		String insertWaiter = "INSERT INTO flightinfo (first_name, last_name, age, dob, departure, "
				+ "arrival, travel_date) "
				
				+ "SELECT first_name, last_name, age, dob, departure, "
				+ "arrival, travel_date FROM waitinglist "
				
				+ "WHERE passenger_id = (SELECT MIN(passenger_id) FROM waitinglist);";
		
		statement = connection.prepareStatement(insertWaiter);
		statement.executeUpdate();
		
		String findWaiter = "SELECT MIN(passenger_id) FROM waitinglist;";
		statement = connection.prepareStatement(findWaiter);
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		int waiterID = resultSet.getInt("MIN(passenger_id)");
		
		String deleteWaiter = "DELETE FROM waitinglist WHERE passenger_id = " + waiterID + ";";
		statement = connection.prepareStatement(deleteWaiter);
		statement.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return showPassengers();
	}
	
	
	
	
//	public void printBy(int sorter) {
//		List<Product> list = listBy(sorter);
//		for (Product product: list) {
//			System.out.println(product);
//		}
//	}
//	
//	public void buyUpdate(Product prod) {
//
//		String sql = "UPDATE products SET num_in_stock = " + prod.num_in_stock + " WHERE id = " + prod.id + ";";
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//			PreparedStatement statement = connection.prepareStatement(sql);
//			statement.executeUpdate();
//
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		} finally {
//			closeConnection(connection);
//		}
//	}

}