package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");	// verificare che la classe esista e sia registrata
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ecommerce?user=test&password=test");
		System.out.println("connessione aperta");
		con.close();
	}

}
