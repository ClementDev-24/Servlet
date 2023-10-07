package D360;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ClientDbUtil {

	private DataSource dataSource;

	public ClientDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Client> getClients() throws Exception {
		List<Client> clients = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();
			String sql = "SELECT * FROM Client";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);

			 while (myRs.next()) {
	                int id = myRs.getInt("id");
	                String userName = myRs.getString("userName");
	                String firstName = myRs.getString("firstName");
	                String middleName = myRs.getString("middleName");
	                String lastName = myRs.getString("lastName");
	                String email = myRs.getString("email");
	                String role = myRs.getString("role");
	                String password = myRs.getString("password");
//	                String encryptedPassword = myRs.getString("password");
//
//	                String decryptedPassword = AESUtil.decrypt(encryptedPassword);

	                Client tempClient = new Client(id, userName, firstName, middleName, lastName, email, role, password);
	                clients.add(tempClient);
	            }

	            return clients;
	        } finally {
			close(myConn, myStmt, myRs);
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close(); // doesn't really close it ... just puts back in connection pool
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addClient(Client theClient) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
            myConn = dataSource.getConnection();

            String sql = "INSERT INTO Client (userName, firstName, middleName, lastName, email, role, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theClient.getUserName());
            myStmt.setString(2, theClient.getFirstName());
            myStmt.setString(3, theClient.getMiddleName());
            myStmt.setString(4, theClient.getLastName());
            myStmt.setString(5, theClient.getEmail());
            myStmt.setString(6, theClient.getRole());

            String encryptedPassword = AESUtil.encrypt(theClient.getPassword());
            myStmt.setString(7, encryptedPassword);

            myStmt.execute();
        } finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void updateClient(Client theClient) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "UPDATE Client  SET userName=?, firstName=?, middleName=?, lastName=?, email=?, role=? WHERE id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theClient.getUserName());
			myStmt.setString(2, theClient.getFirstName());
			myStmt.setString(3, theClient.getMiddleName());
			myStmt.setString(4, theClient.getLastName());
			myStmt.setString(5, theClient.getEmail());
			myStmt.setString(6, theClient.getRole());
			myStmt.setInt(7, theClient.getId()); // Assuming you have an getId() method in Client class

			// execute SQL statement
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Client getClient(int theClientId) throws Exception {

		Client theclient;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int clientId;

		try {
			// convert student id to int
			clientId = theClientId;

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "select * from Client where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, clientId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrieve data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("id");
				String userName = myRs.getString("userName");
				String firstName = myRs.getString("firstName");
				String middleName = myRs.getString("middleName");
				String lastName = myRs.getString("lastName");
				String email = myRs.getString("email");
				String role = myRs.getString("role");

				// use the studentId during construction
				theclient = new Client(id, userName, firstName, middleName, lastName, email, role);
			} else {
				throw new Exception("Could not find student id: " + clientId);
			}

			return theclient;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
	
	public List<Client> count(String theClientId1,String theClientId2) throws Exception {
//		Client theclient;
		List<Client> clients = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			int clientId1 = Integer.parseInt(theClientId1);
			System.out.println(clientId1);
			int clientId2 = Integer.parseInt(theClientId2);
			System.out.println(clientId2);
			myConn = dataSource.getConnection();
			String sql = "SELECT * FROM Client WHERE id BETWEEN "+clientId1 + " AND " + clientId2; // Concatenate clientId directly (ensure it's sanitized)
			Statement statement = myConn.createStatement();
			myRs = statement.executeQuery(sql);


			 while (myRs.next()) {
	                int id = myRs.getInt("id");
	                String userName = myRs.getString("userName");
	                String firstName = myRs.getString("firstName");
	                String middleName = myRs.getString("middleName");
	                String lastName = myRs.getString("lastName");
	                String email = myRs.getString("email");
	                String role = myRs.getString("role");

	                Client tempClient = new Client(id, userName, firstName, middleName, lastName, email, role);
	                System.out.println(tempClient);
	                clients.add(tempClient);
	            }
			

	            return clients;
	        } finally {
			close(myConn, myStmt, myRs);
		}
	}
	
	
	public void deleteClient(int theClientId) throws Exception {
		Connection myConn = null;
		PreparedStatement mystmt = null;
		try {

			// convert id into int
			int clientId =theClientId;
			// get connection to database
			myConn = dataSource.getConnection();
			// create sql to delete student
			String sql = "delete  from Client where id=?";
			// prepare statement
			mystmt = myConn.prepareStatement(sql);
			// set params
			mystmt.setInt(1, clientId);
			// execute sql statement
			mystmt.execute();
		} finally {
			close(myConn, mystmt);
		}

	}

	private void close(Connection myConn, PreparedStatement mystmt) {

	}

}


