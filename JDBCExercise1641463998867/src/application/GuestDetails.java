package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.org.slf4j.internal.LoggerFactory;

public class GuestDetails {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_exercise";

	// Provide your own database credentials
	static final String USER = "root";
	static final String PASS = "Sim@d9v1";
//	private static final Log LOGGER = LogFactory.getLog(GuestDetails.class)

	public void getAllGuests() {
		/*
		 * Implement this method to run a select query which fetches all the details of
		 * the guests
		 */
		String sql = "select * from guests";
		try {
			
			Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			Class.forName(JDBC_DRIVER);
			while(resultset.next()) {
				int id = resultset.getInt(1);
				System.out.println ("id" + id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getGuestsByName() {
		/*
		 * Implement this method to run a select query which fetches the name of all the
		 * guests
		 */

	}
}
