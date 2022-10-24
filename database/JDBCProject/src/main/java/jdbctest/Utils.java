package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {

	static void connectionDB() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/JdbcSchema?user=root&passowrd=PuchiPuchi25!";
		Connection dbConnection = DriverManager.getConnection(url, "root", "PuchiPuchi25!");
		System.out.println("connessione aperta");
	}
}
